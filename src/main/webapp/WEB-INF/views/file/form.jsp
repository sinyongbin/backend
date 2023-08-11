<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
  </head>
  <body>
    <h2>파일 업로드</h2>
    <form action="upload.do" method="post" enctype="multipart/form-data">
      <h4>단일 파일 업로드</h4>
      <input type="file" name="file">

      <!-- 파일 첨부시 특정 확장자만 선택가능하게 하기 예들 -->

      <!--
      <input type="file" name="file" accept=".gif, .jpg, .png">
      <input type="file" name="file" accept="image/gif, image/jpeg, image/png">
      <input type="file" name="file" accept="audio/*">
      <input type="file" name="file" accept="video/*">
      <input type="file" name="file" accept="image/*">
      -->

      <h4>다중 파일 업로드</h4>
      <input type="file" multiple="multiple" name="files">
      <input type="submit"/> <!-- 이게 제출 버튼인듯? -->
    </form>
  </body>
</html>