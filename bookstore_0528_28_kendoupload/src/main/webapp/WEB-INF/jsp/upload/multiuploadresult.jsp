<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>

<h3>멀티파일 업로드 리스트</h3>

<div class="bs-docs-example">
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>#</th>
        <th>File Name</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${params}">
      <tr>
        <td>${item.name}</td>
        <td>${item.value}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>    
</div>
