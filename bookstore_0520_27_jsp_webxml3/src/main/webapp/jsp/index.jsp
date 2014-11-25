<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<html>
<body>
<a href="${cp}/pages/list-j">JSP 북 리스트</a><br/>
<a href="${cp}/pages/list-f">Freemaker 북 리스트</a><br/>
<a href="${cp}/pages/list-v">velocity 북 리스트</a><br/>
<a href="${cp}/tile/list-j">Tile 북 리스트</a><br/>
</body>
</html>
