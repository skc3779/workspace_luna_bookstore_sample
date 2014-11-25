<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<h3>파일 업로드</h3>

<div class="bs-docs-example">
    <form name="uploadItem" method="post" action="${cp}/upload/index" enctype="multipart/form-data">
        <fieldset>
            <legend>Upload Fields</legend>
            <p>
                <label for="name-id">Name</label>
                <input type="text" id="name-id" name="name" />
            </p>
            <p>
                <label for="fileData-id">파일 업로드 1</label>
                <input type="file" id="fileData-id" name="fileData" type="file" />
            </p>
            <p>
                <label for="fileData-id">파일 업로드 2</label>
                <input type="file" id="fileData-id" name="fileData2" type="file" />
            </p>            
            <p>
                <input type="submit" />
            </p>
        </fieldset>
    </form>
</div>
