<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<c:set var="cp" value="${pageContext.request.contextPath}" />
<h3>북스토어 책 수정하기(Tile and JSTP)</h3>

<div class="bs-docs-example">
  <form:form modelAttribute="book" name="book" method="post" cssClass="form-horizontal" action="${cp}/books/edit/html">
      <fieldset>
         <legend>북스토어 책 수정하기(Spring Form 이용), <spring:message code="form.name.book"></spring:message></legend>
         <div class="control-group">
          <form:label path="name" cssClass="control-label" for="name">제목</form:label>
           <div class="controls">
            <form:input path="name" cssClass="input-xlarge" value="${book.name}" placeholder="제목을 넣어주세요"/>
            <form:errors path="name" cssClass="alert alert-error" element="span"></form:errors>
           </div>
         </div>
         <div class="control-group">
          <form:label path="author" cssClass="control-label" for="author">저자</form:label>
           <div class="controls">
            <form:input path="author" cssClass="input-xlarge" value="${book.author}" placeholder="저자를 넣어주세요"/>
            <form:errors path="author" cssClass="alert alert-error" element="span"></form:errors>
           </div>
         </div>
         <div class="control-group">
          <form:label path="publishDate" cssClass="control-label" for="publishDate">출판일</form:label>
           <div class="controls">
            <form:input path="publishDate" cssClass="input-xlarge"  value="${book.publishDateString}" placeholder="출판일 예시) 2012-01-01"/>
            <form:errors path="publishDate" cssClass="alert alert-error" element="span"></form:errors>
           </div>
         </div>
         <div class="control-group">
          <form:label path="comment" cssClass="control-label" for="comment">설명</form:label>
           <div class="controls">
            <form:input path="comment" cssClass="input-xlarge" value="${book.comment}" placeholder="설명을 넣어주세요"/>
           </div>
         </div>
         <input type="hidden" name="id" id="id" value="${book.id}" />
      </fieldset>         
       <div class="form-actions">
         <button type="submit" class="btn btn-primary">저장하기</button>
       </div>
  </form:form>
</div>

