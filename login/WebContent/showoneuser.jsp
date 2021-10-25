<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*" import="cn.edu.ujn.model.User"
	import="cn.edu.ujn.dao.IUserDao" import="cn.edu.ujn.dao.UserDaoImpl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%
	String pagesize = request.getParameter("pagesize");
	String currentpage = request.getParameter("currentpage");
	if (session.getAttribute("activeuser") != null) {
		IUserDao userDao = new UserDaoImpl();
		ArrayList<User> user = userDao.getUserById(Integer.parseInt(request.getParameter("userid")));
%>
<title><%=user.get(0).getUsername()%>的个人主页</title>
</head>
<body>
	姓名：<%=user.get(0).getUsername()%><br>
	<br> 密码：<%=user.get(0).getPassword()%><br>
	<br> 性别：<%=user.get(0).getSex()%><br>
	<br> 年龄：<%=user.get(0).getAge()%><br>
	<br> 爱好：<%=user.get(0).getHobit()%><br>
	<br> 邮箱：<%=user.get(0).getEmail()%><br>
	<br> 地址：<%=user.get(0).getAddress()%><br>
	<br>
	<a
		href="UserServlet?action=findallsub&pagesize=<%=pagesize%>&currentpage=<%=currentpage%>">返回主界面</a>
	<%
		} else {
	%>
	还没登录呢，请先<a href="login.jsp">登录</a>。
	<%
		}
	%>
</body>
</html>