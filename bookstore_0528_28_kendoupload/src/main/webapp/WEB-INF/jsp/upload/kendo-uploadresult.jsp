<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>

<h3>KENDO 파일 업로드 리스트</h3>

<div class="bs-docs-example">
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>#</th>
        <th>File Name</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="file" items="${files}">
      <tr>
        <td></td>
        <td>${file}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>    
</div>
