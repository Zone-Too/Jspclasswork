<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户退出</title>
</head>
<body>
<%
	if(session.getAttribute("activeuser") !=null ){
		session.removeAttribute("activeuser");
		if(session.getAttribute("activeuser") ==null){
			out.println("退出成功！3秒后跳转到登录页面");
			response.setHeader("refresh", "3;url=login.jsp");
		}else{
			out.println("退出失败！");
			request.getRequestDispatcher("success.jsp").forward(request, response);
		}
	}else{
		%>
		还没登录呢，请先去<a href="login.jsp">登录</a>。
		<%
	}


%>

</body>
</html>