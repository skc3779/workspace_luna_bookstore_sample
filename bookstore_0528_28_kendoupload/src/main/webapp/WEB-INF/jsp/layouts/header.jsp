<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<c:set var="cp" value="${pageContext.request.contextPath}" />
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
						<li class="active"><a href="<c:url value='/tiles/list' />"> 북 리스트</a></li>
						<li><a href="<c:url value='/books/add/html' />"> 북저장하기</a></li>
            <li><a href="<c:url value='/upload/uploaditem' />" >업로드</a></li>            						
            <li><a href="<c:url value='/upload/multiupload' />" >멀티업로드</a></li>
            <li><a href="<c:url value='/upload/kendo/uploadform' />" >Kendo업로드</a></li>
            <li><a href="<c:url value='/upload/kendo/async/uploadform' />" >Kendo업로드(비동기)</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>