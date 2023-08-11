<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title> Spring Board </title>
	<meta charset="utf-8">
	<style>
		a{text-decoration:none}
	</style>
</head>
<body>
<center>
<font color='gray' size='4' face='휴먼편지체'>
<hr width='600' size='2' color='gray' noshade>
<h3> Spring Board Page </h3>
<font color='gray' size='4' face='휴먼편지체'>
<a href='../'>인덱스</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href='write.do'>글쓰기</a><br/>
</font>
<hr width='600' size='2' color='gray' noshade>

<TABLE border='2' width='600' align='center' noshade>

<TR size='2' align='center' noshade bgcolor='AliceBlue'>
	<th bgcolor='AliceBlue'>no</th>
	<th color='gray'>writer</th>
	<th color='gray'>e-mail</th>
	<th color='gray'>subject</th>
	<th color='gray'>date</th>
</TR>
    <c:if test="${empty listResult}">
        <tr>
            <td align='center' colspan='5'>데이터가 없음 ㅠㅠ</td>
        </tr>
    </c:if>

    <c:forEach items = "${listResult.list.content}"  var = "board">
    <!-- listResult 객체의 list 필드의 content 컬렉션에서 데이터를 가져와서 각 데이터를 board라는 변수에 할당하여 반복적으로 사용하겠다는 것을 의미 -->
        <TR align='center' noshade>
          <TD>${board.seq}</TD> <!-- board 객체의 seq 속성의 값을 출력 -->
          <TD>${board.writer}</TD>
          <TD>${board.email}</TD>
           <TD>
             <a href="content.do?seq=${board.seq}">
              ${board.subject}
            </a>
          </TD>
          <TD>${board.rdate}</TD>
          </TR>

      </c:forEach>
</TABLE>

<hr width='600' size='2' color='gray' noshade>
<font color='gray' size='3' face='휴먼편지체'>
    (총페이지수 : ${listResult.totalPageCount})
    &nbsp;&nbsp;&nbsp;
    <c:forEach begin="0" end="${listResult.totalPageCount-1}" var="i">
        <a href="list.do?page=${i}">
            <c:choose>
                <c:when test="${i==listResult.page}">
                    <strong>${i+1}</strong>
                </c:when>
                <c:otherwise>
                    ${i+1}
                </c:otherwise>
            </c:choose>
        </a>&nbsp;
    </c:forEach>
    ( TOTAL : ${listResult.totalCount} )
    
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       페이지 싸이즈 : 
    <select id="psId" name="ps" onchange="f(this)">

    	<c:choose>
    		<c:when test="${listResult.size == 3}">
    		   <option value="3" selected>3</option>
		       <option value="5">5</option>
		       <option value="10">10</option>
    		</c:when>
    		<c:when test="${listResult.size == 5}">
               <option value="3">3</option>
               <option value="5" selected>5</option>
               <option value="10">10</option>
            </c:when>
            <c:when test="${listResult.size == 10}">
               <option value="3">3</option>
               <option value="5">5</option>
               <option value="10" selected>10</option>
            </c:when>
    	</c:choose>
    </select>
    
    <script language="javascript">
       function f(select){
           //var el = document.getElementById("psId");
           var ps = select.value;
           //alert("ps : " + ps);
           location.href="list.do?size="+ps;
       }
    </script>
    
</font>
<hr width='600' size='2' color='gray' noshade>
<!--
<form action="">
      <select name="catgo">
        <option value="subject">제목</option>
        <option value="writer">글쓴이</option>
        <option value="content">내용</option>
      </select>
      <input type="text" name="keyword" size="40" required="required" /> <button>검색</button>
    </form> 
-->
</center>
</body>
</html>