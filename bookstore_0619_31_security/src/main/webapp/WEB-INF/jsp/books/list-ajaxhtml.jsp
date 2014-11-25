<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
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
      <td><joda:format value="${book.publishDate}" pattern="yyyy-MM-dd"/></td>
      <td>${book.comment}</td>
      <td>${book.bookStatus}</td>
    </tr>   
  </c:forEach>
  </tbody>
</table>
