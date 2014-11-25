<li><a href="${cp}/upload/kendo/uploadform">Kendo업로드</a></li><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>

<c:set var="cp" value="${pageContext.request.contextPath}" />

<h3>KENDO 파일 업로드</h3>

<div class="bs-docs-example">
	<form name="uploadItem" method="POST" 
	  action="<c:url value='/upload/kendo/uploadform' />" 
	  enctype="multipart/form-data"
	  class="form-horizontal">
	    <fieldset>
	       <legend>Upload Fields <small>(kendo compoment)</small></legend>
         <p>
             <label for="name-id">Name</label>
             <input type="text" id="name-id" name="name" />
         </p>	       
	       <div class="control-group">
	         <label class="control-label" for="name">파일 업로드</label>
	         <div class="controls">
	           <kendo:upload name="files" /> 
	         </div>
	       </div>
				<div class="form-actions">
				  <button type="submit" class="btn btn-primary">Submit</button>
				</div>
	    </fieldset>
	</form>
</div>
