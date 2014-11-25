<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
