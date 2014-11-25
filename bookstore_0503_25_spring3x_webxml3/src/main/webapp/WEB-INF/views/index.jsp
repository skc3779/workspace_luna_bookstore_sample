<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>Kendo Example</title>
<link href="${cp}/res/styles/kendo.common.min.css" rel="stylesheet" />
<link href="${cp}/res/styles/kendo.default.min.css" rel="stylesheet" />
<script src="${cp}/res/js/jquery.min.js"></script>
<script src="${cp}/res/js/kendo.all.min.js"></script>
</head>
<body>
	<div>
		WebConfiguration class에 "res" 로 설정되어 있기 때문에 kendo js/css의 url path는
		다음과 같이 설정됩니다.
		<p>/res/js/kendo.all.min.js</p>
		<p>/res/styles/kendo.common.min.css</p>
	</div>

	<div id="example" class="k-content">
		<div id="shipping">
			<label for="countries" class="info">Choose shipping
				countries:</label> <input id="countries" />
			<div class="hint">Start typing the name of an European country</div>
		</div>

		<kendo:editor name="editor" style="width:740px;height:440px">
			<kendo:editor-value>
         &lt;p&gt;
               &lt;img src="http://www.kendoui.com/Image/kendo-logo.png" alt="Editor for ASP.NET MVC logo" style="display:block;margin-left:auto;margin-right:auto;" /&gt;
            &lt;/p&gt;
            &lt;p&gt;
                Kendo UI Editor allows your users to edit HTML in a familiar, user-friendly way.&lt;br /&gt;
                In this version, the Editor provides the core HTML editing engine, which includes basic text formatting, hyperlinks, lists,
                and image handling. The widget &lt;strong&gt;outputs identical HTML&lt;/strong&gt; across all major browsers, follows
                accessibility standards and provides API for content manipulation.
            &lt;/p&gt;
            &lt;p&gt;Features include:&lt;/p&gt;
            &lt;ul&gt;
                &lt;li&gt;Text formatting &amp; alignment&lt;/li&gt;
                &lt;li&gt;Bulleted and numbered lists&lt;/li&gt;
                &lt;li&gt;Hyperlink and image dialogs&lt;/li&gt;
                &lt;li&gt;Cross-browser support&lt;/li&gt;
                &lt;li&gt;Identical HTML output across browsers&lt;/li&gt;
                &lt;li&gt;Gracefully degrades to a &lt;code&gt;textarea&lt;/code&gt; when JavaScript is turned off&lt;/li&gt;
            &lt;/ul&gt;
            &lt;p&gt;
                Read &lt;a href="http://www.kendoui.com/documentation/introduction.aspx"&gt;more details&lt;/a&gt; or send us your
                &lt;a href="http://www.kendoui.com/forums.aspx"&gt;feedback&lt;/a&gt;!
            &lt;/p&gt;
    </kendo:editor-value>
		</kendo:editor>

		<script>
			$(document).ready(
					function() {
						var data = [ "Albania", "Andorra", "Armenia",
								"Austria", "Azerbaijan", "Belarus", "Belgium",
								"Bosnia & Herzegovina", "Bulgaria", "Croatia",
								"Cyprus", "Czech Republic", "Denmark",
								"Estonia", "Finland", "France", "Georgia",
								"Germany", "Greece", "Hungary", "Iceland",
								"Ireland", "Italy", "Kosovo", "Latvia",
								"Liechtenstein", "Lithuania", "Luxembourg",
								"Macedonia", "Malta", "Moldova", "Monaco",
								"Montenegro", "Netherlands", "Norway",
								"Poland", "Portugal", "Romania", "Russia",
								"San Marino", "Serbia", "Slovakia", "Slovenia",
								"Spain", "Sweden", "Switzerland", "Turkey",
								"Ukraine", "United Kingdom", "Vatican City" ];

						//create AutoComplete UI component
						$("#countries").kendoAutoComplete({
							dataSource : data,
							filter : "startswith",
							placeholder : "Select country...",
							separator : ", "
						});
					});
		</script>
		<style scoped="scoped">
.info {
	display: block;
	line-height: 22px;
	padding: 0 5px 5px 0;
	color: #36558e;
}

#shipping {
	width: 482px;
	height: 152px;
	padding: 110px 0 0 30px;
	background: url('../../content/web/autocomplete/shipping.png')
		transparent no-repeat 0 0;
	margin: 30px auto;
}

.k-autocomplete {
	width: 250px;
	vertical-align: middle;
}

.hint {
	line-height: 22px;
	color: #aaa;
	font-style: italic;
	font-size: .9em;
	color: #7496d4;
}
</style>
	</div>
</body>

</html>