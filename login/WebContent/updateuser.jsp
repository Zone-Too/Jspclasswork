<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*" import="cn.edu.ujn.model.User"
	import="cn.edu.ujn.dao.IUserDao" import="cn.edu.ujn.dao.UserDaoImpl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改用户信息</title>

</head>
<body>
	<%
		String pagesize = request.getParameter("pagesize");
		String currentpage = request.getParameter("currentpage");
		if (session.getAttribute("activeuser") != null) {
			IUserDao userDao = new UserDaoImpl();
			ArrayList<User> user = userDao.getUserById(Integer.parseInt(request.getParameter("userid")));
			
	%>
	<form action="UserServlet?action=updateuser&userid=<%=user.get(0).getId()%>&pagesize=<%=pagesize %>&currentpage=<%=currentpage %>"
		method="post">
		Id:<input type="text" name="userid"
			value="<%=user.get(0).getId()%>" readonly="readonly"/><br />
		<br /> 
		用户名：<input type="text" name="username"
			value="<%=user.get(0).getUsername()%>" /><br />
		<br /> 密码：<input type="password" name="password"
			value="<%=user.get(0).getPassword()%>" /><br />
		<br />
		<%
			if (user.get(0).getSex().equals("男")) {
		%>
		性别：<input type="radio" name="sex" value="男" checked>男 <input
			type="radio" name="sex" value="女">女<br />
		<br />
		<%
			} else {
		%>
		性别：<input type="radio" name="sex" value="男">男 <input
			type="radio" name="sex" value="女" checked>女<br />
		<br />
		<%
			}
		%>
		年龄：<input type="text" name="age" value="<%=user.get(0).getAge()%>"><br />
		<br /> 爱好：<input type="checkbox" name="hobit" value="看电影" id="moive">看电影
		<input type="checkbox" name="hobit" value="听音乐" id="music">听音乐
		<input type="checkbox" name="hobit" value="读书" id="read">读书 <input
			type="checkbox" name="hobit" value="跑步" id="run">跑步<br>
		<br> 邮箱：<input type="email" name="email"
			value="<%=user.get(0).getEmail()%>"><br />
		<br /> 家庭地址：<input type="text" name="address"
			value="<%=user.get(0).getAddress()%>"><br />
		<br /> <input type="submit" name="button" value="更新" />
	</form>
	<%
		if (user.get(0).getHobit().contains("看电影")) {
	%>
	<script type="text/javascript">
		document.getElementById("moive").checked = true;
	</script>
	<%
		}
			if (user.get(0).getHobit().contains("听音乐")) {
	%>
	<script type="text/javascript">
		document.getElementById("music").checked = true;
	</script>
	<%
		}
			if (user.get(0).getHobit().contains("读书")) {
	%>
	<script type="text/javascript">
		document.getElementById("read").checked = true;
	</script>
	<%
		}
			if (user.get(0).getHobit().contains("跑步")) {
	%>
	<script type="text/javascript">
		document.getElementById("run").checked = true;
	</script>
	<%
		}
		} else {
	%>
	还没登录呢，请先<a href="login.jsp">登录</a>。
	<%
		}
	%>

</body>
</html>