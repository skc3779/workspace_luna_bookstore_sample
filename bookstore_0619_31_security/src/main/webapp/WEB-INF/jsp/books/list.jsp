<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<h3>북 리스트 (Tile and JSTP)</h3>

<div class="bs-docs-example">

  <table class="table table-bordered">
    <thead>
      <tr>
        <th>#</th>
        <th><spring:message code="label_books_name"/></th>
        <th><spring:message code="label_books_author"/></th>
        <th><spring:message code="label_books_publishDate"/></th>
        <th><spring:message code="label_books_comment"/></th>
        <th><spring:message code="label_books_status"/></th>
      </tr>
    </thead>
    <tbody>
		<c:forEach var="book" items="${books}">
      <tr>
        <td>${book.id}</td>
        <td>${book.name}</td>
        <td>${book.author}</td>
        <td>${book.publishDate}</td>
        <td>${book.comment}</td>
        <td>${book.status}</td>
      </tr>		
		</c:forEach>
  	</tbody>
	</table>
	<hr/>
	<P class="">
	   <a href="<c:url value='/tiles/listexcel' />"><button id="btnexcel" class="btn btn-primary" type="button">엑셀 다운로드</button></a>
	   <a href="<c:url value='/tiles/listpdf' />"><button id="listpdf" class="btn btn-primary" type="button">PDF 다운로드</button></a>
	   <a href="<c:url value='/tiles/listjson1' />"><button id="listjson1" class="btn btn-primary" type="button">JSON 다운로드 (Book)</button></a>
	   <a href="<c:url value='/tiles/listjson2' />"><button id="listjson2" class="btn btn-primary" type="button">JSON 다운로드 (Map)</button></a>
	</P>
	<p>
	  <a href="<c:url value='/books/list' />?lang=ko">로케일 한글</a> |
    <a href="<c:url value='/books/list' />?lang=en">로케일 영어</a> |
    Current Locale : ${pageContext.response.locale}
  </p>
	<script type="text/javascript">
		$(document).ready(function(){
		});
	</script> 
</div>
