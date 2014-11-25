<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
${pageContext.request.contextPath}
BODY TEST ... <c:out value="${hello}" default="empty" />
