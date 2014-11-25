<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<h3>북스토어 책 저장하기(Tile and JSTP)</h3>

<div class="bs-docs-example">
  <form name="book" method="post" action="${cp}/books/add/html" class="form-horizontal">
      <fieldset>
         <legend>북스토어 책 저장하기</legend>
         <div class="control-group">
           <label class="control-label" for="name">제목</label>
           <div class="controls">
             <input type="text" name="name" id="name" class="input-xlarge" placeholder="제목을 넣어주세요"> 
           </div>
         </div>
         <div class="control-group">
           <label class="control-label" for="author">저자</label>
           <div class="controls">
             <input type="text" name="author" id="author" class="input-xlarge" placeholder="저자를 넣어주세요"> 
           </div>
         </div>
         <div class="control-group">
           <label class="control-label" for="publishDate">출판일</label>
           <div class="controls">
             <input type="text" name="publishDate" id="publishDate" class="input-xlarge" placeholder="출판일 예시) 2012-01-01"> 
           </div>
         </div>
         <div class="control-group">
           <label class="control-label" for="comment">설명</label>
           <div class="controls">
             <input type="text" name="comment" id="comment" class="input-xlarge" placeholder="설명을 넣어주세요"> 
           </div>
         </div>
      </fieldset>         
       <div class="form-actions">
         <button type="submit" class="btn btn-primary">저장하기</button>
       </div>
  </form>
</div>

