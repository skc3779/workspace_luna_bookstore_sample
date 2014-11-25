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
						<li class="active"><a href="${cp}/tiles/list">북리스트</a></li>
            <li><a href="${cp}/upload/uploadform">업로드</a></li>            						
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>