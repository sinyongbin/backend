<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Title</title>
		<style>
			table, th, td {
			   border: 1px solid black;
			   border-collapse: collapse;
			}
			th, td {
			   padding: 5px;
			}
			a { text-decoration:none }
		</style>
	</head>

	<body style="text-align:center">
		<center>
			<h1>
				Address List Page
			</h1>
			<a href='write.do'>입력</a> &nbsp;&nbsp;&nbsp;
			<a href='../'>인덱스</a><br/>
			<table border='1' cellpadding='7' cellspacing='2' width='50%'>
                <tr>
                <th>번호</th>
                <th>이름</th>
                <th>주소</th>
                <th>날짜</th>
                <th>삭제</th>
                </tr>

			<c:if test="${empty listResult}">
                <tr>
                    <td align='center' colspan='5'>데이터가 없음 ㅠㅠ</td>
                </tr>
            </c:if>
            <c:forEach items="${listResult.list.content}"  var="address">
                <tr>
                    <td align='center'>${address.seq}</td>
                    <td>${address.name}</td>
                    <td>${address.addr}</td>
                    <td align='center'>
                        <fmt:formatDate value="${address.rdate}" pattern="yyyy-MM-dd a hh:mm:ss"/>
                    </td>
                    <td align='center'><a href='del.do?&seq=${address.seq}'>삭제</a></td>
                </tr>
            </c:forEach>
			</table>

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

		</center>
	</body>
</html>