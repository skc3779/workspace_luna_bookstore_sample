<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Le styles -->
    <link href="${cp}/contents/bootstrap/css/bootstrap.min.css" rel="stylesheet">        
    <style>      
      body { padding-top: 60px; } /* 60px to make the container go all the way to the bottom of the topbar */
    </style>
    <link href="${cp}/contents/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="${cp}/contents/bootstrap/css/bootstrap_docs.css" rel="stylesheet">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="${cp}/contents/bootstrap/js/bootstrap.min.js"></script>
  <title>BookStore ..</title>    
  </head>
<body>
    <header>
        <div class="navbar navbar-inverse navbar-fixed-top">
          <div class="navbar-inner">
            <div class="container">
              <button type="button" class="btn btn-navbar" data-toggle="collapse"
                data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="brand" href="#">BookStore Web</a>
              <div class="nav-collapse collapse">
                <ul class="nav">
                  <li class="active"><a href="list-j">Book(jstl)</a></li>
                  <li><a href="list-f">Book(freemarker)</a></li>
                  <li ><a href="list-v">Book(velocity)</a></li>
                </ul>
              </div>
              <!--/.nav-collapse -->
            </div>
          </div>
        </div>
    </header>   
    <section id="main">
    <div class="container">
		<h3>북 리스트 (JSTL)</h3>
		
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
		    <div class="noti_view" id="${notice.noticeId}-content" style="display:none">${notice.content}</div>
		    </c:forEach>
		    </tbody>
		  </table>
		</div>
    </div>
    </section>
</body>
</html>