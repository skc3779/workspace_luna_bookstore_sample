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
	<title><tiles:insertAttribute name="title" ignore="true" /></title>    
  </head>
<body>
    <header>
    <tiles:insertAttribute name="header" ignore="true"/>
    </header>   
		<section id="main">
		<div class="container">
	       <tiles:insertAttribute name="body" />
	  </div>
		</section>
</body>
</html>