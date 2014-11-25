<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%-- Joda Time - JSP tags 참고 http://joda-time.sourceforge.net/contrib/jsptags/index.html --%>

<c:set var="cp" value="${pageContext.request.contextPath}" />
<h3>북 리스트 (Tile and JSTP)</h3>

<div class="bs-docs-example">
  <div>
  <table class="table table-bordered">
    <thead>
      <tr>
	      <th>#</th>
	      <th><spring:message code="label_books_name"/></th>
	      <th><spring:message code="label_books_author"/></th>
	      <th><spring:message code="label_books_publishDate"/></th>
	      <th><spring:message code="label_books_comment"/></th>
	      <th><spring:message code="label_books_status"/></th>
        <th><spring:message code="label_button_editor"/></th>
      </tr>
    </thead>
    <tbody>
		<c:forEach var="book" items="${books}">
      <tr>
        <td>${book.id}</td>
        <td>${book.name}</td>
        <td>${book.author}</td>
        <td><joda:format value="${book.publishDate}" pattern="yyyy-MM-dd"/></td>
        <td>${book.comment}</td>
        <td>${book.bookStatus}</td>
        <td><a href="<c:url value='/books/edit/html/${book.id}' />" ><i class="icon-pencil"></i></a></td>
      </tr>
		</c:forEach>
  	</tbody>
	</table>
	</div>
	<hr class="divider"/>
	<form name="bookForm" method="get" action="<c:url value='/books/list/html' />" class="form-search">
	   <div class="input-append">
	     <input name="pageIndex" type="text" class="span2" placeholder="페이지 입력">
	     <input name="pageSize" type="text" class="span2" placeholder="사이즈 입력">
	     <button type="submit" class="btn">Search</button>
	   </div>
	   <a href="<c:url value='/books/add/html' />" class="btn btn-primary pull-right" >북 등록하기(HTML)</a>
	</form> 	
</div>

