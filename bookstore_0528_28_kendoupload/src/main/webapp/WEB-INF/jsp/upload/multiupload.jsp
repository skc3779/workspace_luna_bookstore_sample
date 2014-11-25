<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<h3>멀티파일 업로드</h3>

<div class="bs-docs-example">
    <form name="uploadItem" method="post" action="${cp}/upload/multiuploadresult" enctype="multipart/form-data">
        <fieldset>
            <legend>Upload Fields</legend>
            <p>
                <label for="name-id">Name1</label>
                <input type="text" id="name1" name="name1" />
            </p>
            <p>
                <label for="name-id">Name2</label>
                <input type="text" id="name2" name="name2" />
            </p>
                                    <p>
                <label for="name-id">Name3</label>
                <input type="text" id="name3" name="name3" />
            </p>
            <p>
                <label for="fileData-id">파일 업로드 1</label>
                <input type="file" id="fileData1" name="fileData1" />
            </p>
            <p>
                <label for="fileData-id">파일 업로드 2</label>
                <input type="file" id="fileData2" name="fileData2"/>
            </p>    
            <p>
                <label for="fileData-id">파일 업로드 3</label>
                <input type="file" id="fileData3" name="fileData3"/>
            </p>                      
            <p>
                <input type="submit" />
            </p>
        </fieldset>
    </form>
</div>
