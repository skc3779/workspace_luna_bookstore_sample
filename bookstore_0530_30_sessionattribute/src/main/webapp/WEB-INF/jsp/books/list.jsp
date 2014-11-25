<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<h3>북 리스트 (Tile and JSTP)</h3>

<div class="bs-docs-example">

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
        <td>${book.status}</td>
      </tr>		
		</c:forEach>
  	</tbody>
	</table>
	<hr/>
	<P class="">
	   <a href="${cp}/tiles/listexcel"><button id="btnexcel" class="btn btn-primary" type="button">엑셀 다운로드</button></a>
	   <a href="${cp}/tiles/listpdf"><button id="listpdf" class="btn btn-primary" type="button">PDF 다운로드</button></a>
	   <a href="${cp}/tiles/listjson1"><button id="listjson1" class="btn btn-primary" type="button">JSON 다운로드 (Book)</button></a>
	   <a href="${cp}/tiles/listjson2"><button id="listjson2" class="btn btn-primary" type="button">JSON 다운로드 (Map)</button></a>
	</P>
	<script type="text/javascript">
		$(document).ready(function(){
		});
	</script> 
</div>
