<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<h3>북스토어 책 저장하기(Tile and JSTP)</h3>

<div class="bs-docs-example">
  <form id="bookForm" name="bookForm" method="post" onsubmit="return false;"
    action="<c:url value='/books/add/ajax' />" class="form-horizontal">
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
         <button type="button" class="btn btn-primary" onclick="saveBook();">저장하기</button>
       </div>
  </form>
</div>

<script type="text/javascript">
	
	function saveBook() {
		$.post("<c:url value='/books/add/ajax' />", 
				  $("#bookForm").serializeObject(), "json")
				  .done(function(data) {
					  if($.parseJSON(data).isOk) { 
						  $("#bookForm").find("input[type=text], textarea").val('');					  
					  } else {
						  alert("저장실패 잠시후 다시시도 바랍니다.");
					  }
				  })
				  .fail(function(data){
					  alert("서버오류 [" + data.statusText + "] \r\n 잠시후 다시시도 바랍니다.");
				  });
	}

	/*
	 $(function() {
			  $("#bookForm").on("submit", function(event) {
				  event.preventDefault();			  
				  console.log($(this).serialize());
				  $(this).find("input[type=text], textarea").val('');
			  }); 
			
		});
	 */

 </script>

