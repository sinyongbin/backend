<%@ page contentType="text/html;charset=utf-8" import="sin.backend.service.MemberLoginConst"%>
<script>
    if(${result} == <%=MemberLoginConst.NO_ID%>){ <!-- 1대신에 식을써서 <%=MemberLoginConst.NO_ID%>로 넣어준다 -->
        alert("그런 이메일을 가진 회원이 없어요");
        //location.href="form.do";
        history.back();
    }else if(${result} == <%=MemberLoginConst.NO_PWD%>){
        alert("비밀번호가 틀렸어요");
        //location.href="form.do";
        history.back();
    }else{
        alert("로긴 성공");
        if(${empty sessionScope.forward_url}){
            location.href="../";
        }else{
            location.href="../${sessionScope.forward_url}";
            <% session.removeAttribute("forward_url"); %>
            //alert("제거후: ${sessionScope.forward_url}");
        }
    }
</script>