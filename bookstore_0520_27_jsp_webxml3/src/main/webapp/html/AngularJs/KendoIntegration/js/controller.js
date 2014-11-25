/**
 * todoApp Module
 * 
 * Description
 */
var todoApp = angular.module('todoApp', [ 'ngResource' ]);

todoApp.controller('AppCtrl', [ '$scope', 'Item', function($scope, Item) {

	// fetch model
	var items = Item.query();
	$scope.items = items;

	$scope.remaining = function() {
		return items.reduce(function(count, item) {
			return item.done ? count : count + 1;
		}, 0);
	};

	$scope.add = function(newItem) {
		var item = new Item({
			text : newItem.text
		});
		items.push(item);
		newItem.text = "";
		item.$save();
	};

	$scope.archive = function() {
		items = $scope.items = items.filter(function(item) {
			if (item.done) {
				item.$remove();
				return false;
			}
			return true;
		});
	};
} ]);

todoApp.constant('apiKey', '50728d46e4b088be4c29ea02');

todoApp
		.factory(
				'Item',
				function($resource, apiKey) {
					var Item = $resource(
							'https://api.mongolab.com/api/1/databases/angular-todo/collections/todos/:id',
							{
								apiKey : apiKey
							}, {
								update : {
									method : 'PUT'
								}
							});

					Item.prototype.$remove = function() {
						Item.remove({
							id : this._id.$oid
						});
					};

					Item.prototype.$update = function() {
						return Item.update({
							id : this._id.$oid
						}, angular.extend({}, this, {
							_id : undefined
						}));
					};

					Item.prototype.done = false;

					return Item;
				});
