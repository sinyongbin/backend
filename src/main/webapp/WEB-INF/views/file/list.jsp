<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../login/login_check_modul.jsp"%>
<% session.setAttribute("forward_url", "file/list.do"); %>

<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>Title</title>
</head>
<body>
<center>
    <h3>파일리스트</h3>
    <a href="upload.do">업로드폼</a><br/>

    <h4>(1) 이미지파일(png)만 출력</h4>
    <c:forEach items="${fileUps}"  var="fileUp"> <!-- 컨트롤러의 model.addAttribute("fileUps", fileUps);에서 fileUps라는 컬렉션(리스트)의 각 항목을 fileUp이라는 변수에 순차적으로 할당 반복 -->
        <c:if test="${fileUp.orgnm.substring(fileUp.orgnm.lastIndexOf('.')) == '.png'}"> <!-- 현재 fileUp에 대한 조건을 검사, 파일 이름의 마지막 확장자가 '.png'인 경우를 확인 -->
            <img src="/file/images/${fileUp.id}" width="150" height="150">
            <p>${fileUp.orgnm}</p> <!-- 이미지 파일의 원본 이름을 출력 -->
        </c:if>
    </c:forEach><br/>

    <h4>(2) 일반파일 다운로드</h4>
    <c:forEach items="${fileUps}"  var="fileUp">
        <a href="/file/attach/${fileUp.id}">${fileUp.orgnm}</a><br/> <!-- 파일 다운로드 링크를 생성. ${fileUp.id}는 해당 파일의 ID를 동적으로 삽입하고, 링크의 텍스트로 파일의 원본 이름을 출력 -->
    </c:forEach>

</center>
</body>
</html>








