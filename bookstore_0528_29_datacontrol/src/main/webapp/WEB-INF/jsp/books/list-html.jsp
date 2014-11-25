<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<h3>북 리스트 (Tile and JSTP)</h3>

<div class="bs-docs-example">
  <div>
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>#</th>
        <th>Name</th>
        <th>Author</th>
        <th>Date</th>
        <th>Comment</th>
        <th>Status</th>
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
        <td>${book.bookStatus}</td>
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
	</form> 	
</div>

