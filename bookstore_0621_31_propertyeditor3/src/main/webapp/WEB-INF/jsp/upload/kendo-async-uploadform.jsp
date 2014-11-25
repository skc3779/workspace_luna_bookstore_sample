<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>

<c:set var="cp" value="${pageContext.request.contextPath}" />

<h3>KENDO 파일 업로드(비동기)</h3>

<c:url value="/upload/kendo/async/save" var="saveUrl" />
<c:url value="/upload/kendo/async/remove" var="removeUrl" />

<div class="bs-docs-example">
	<fieldset>
	  <legend>Upload Fields <small>(kendo compoment)</small></legend>
	  <div class="control-group">
	    <label class="control-label" for="name">파일 업로드</label>
	    <div class="controls">
				<kendo:upload name="files" select="onSelect" upload="onUpload" 
          success="onSuccess" error="onError" complete="onComplete" progress="onProgress">>
				  <kendo:upload-async autoUpload="true" saveUrl="${saveUrl}" removeUrl="${removeUrl}"/>
				</kendo:upload>
	    </div>
	  </div>
	 </fieldset>	 
</div>
<div class="demo-section" style="margin-top: 50px;">
    <h3 class="title">Console log</h3>
    <div class="console"></div>
</div>  

<script type='text/javascript'>
	function onSelect(e) {
	    kendoConsole.log("Select :: " + getFileInfo(e));
	}
	
	function onUpload(e) {
	    kendoConsole.log("Upload :: " + getFileInfo(e));
	}
	
	function onSuccess(e) {
	    kendoConsole.log("Success (" + e.operation + ") :: " + getFileInfo(e));
	}
	
	function onError(e) {
	    kendoConsole.log("Error (" + e.operation + ") :: " + getFileInfo(e));
	}
	
	function onComplete(e) {
	    kendoConsole.log("Complete");
	}
	
	function onCancel(e) {
	    kendoConsole.log("Cancel :: " + getFileInfo(e));
	}
	
	function onRemove(e) {
	    kendoConsole.log("Remove :: " + getFileInfo(e));
	}
	
	function onProgress(e) {
	    kendoConsole.log("Upload progress :: " + e.percentComplete + "% :: " + getFileInfo(e));
	}
	
	function getFileInfo(e) {
	    return $.map(e.files, function(file) {
	        var info = file.name;
	
	        // File size is not available in all browsers
	        if (file.size > 0) {
	            info  += " (" + Math.ceil(file.size / 1024) + " KB)";
	        }
	        return info;
	    }).join(", ");
	}
</script>
