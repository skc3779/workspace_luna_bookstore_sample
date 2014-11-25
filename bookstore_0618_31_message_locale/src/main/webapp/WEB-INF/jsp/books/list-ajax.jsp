<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<h3>북 리스트 (Tile and JSTP)</h3>

<div class="bs-docs-example">
  <div id="booklist">
  </div>
	<hr class="divider"/>
	<form id="bookForm" name="bookForm" method="get" onsubmit="return false;" class="form-search">
	  <input name="pageIndex" type="text" class="input-small search-query" placeholder="페이지 입력">
	  <input name="pageSize" type="text" class="input-small search-query" placeholder="사이즈 입력">
	  <button type="button" class="btn" onclick="ajaxHtmlLoad();">Search(HTML)</button>
	  <button type="button" class="btn" onclick="ajaxJsonLoad();">Search(AJAX)</button>    
	</form>
</div>
<script type="text/javascript">
  function ajaxHtmlLoad() {
	  $("#booklist").load("<c:url value='/books/list/ajaxhtml' />", $("#bookForm").serialize());
	  //json data는 작동하지 않음.
	  //console.log($("#bookForm").serializeObject());
	  //$("#booklist").load("<c:url value='/books/list/ajaxhtml' />", $("#bookForm").serializeObject());
  }
</script>
