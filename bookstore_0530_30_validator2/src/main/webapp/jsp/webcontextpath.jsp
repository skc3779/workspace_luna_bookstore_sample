<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>JSP - Context Path</title>
</head>
<body>
  <body>
    Web Application Context Path = ${pageContext.request.contextPath} <br/>
    Web Application Context Path = <c:out value="${contextPath}"/> <br/>
    Web Application Context Path = ${contextPath}
  </body>
</body>
</html>