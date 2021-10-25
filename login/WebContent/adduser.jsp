<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>增加用户</title>
<script type="text/javascript">
	function check(){
		var inputdate = document.getElementById("username");
		if(inputdate.value==""){
			alert("请输入用户名");
			return false;
		}
		inputdate = document.getElementById("password");
		if(inputdate.value==""){
			alert("请输入密码");
			return false;
		}
		inputdate = document.getElementById("age");
		if(inputdate.value==""){
			alert("请输入年龄");
			return false;
		}
		inputdate = document.getElementById("email");
		if(inputdate.value==""){
			alert("请输入邮箱");
			return false;
		}
		inputdate = document.getElementById("address");
		if(inputdate.value==""){
			alert("请输入地址");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<%
	String currentpage = request.getParameter("currentpage");
	String pagesize = request.getParameter("pagesize");
%>
<%if(session.getAttribute("activeuser")!=null){ %>
<form action="UserServlet?action=adduser&pagesize=<%=pagesize %>&currentpage=<%=currentpage %>" method="post" onsubmit="return check()">
  	用户名：<input type="text" name="username" id="username"/><br/><br/>
  	密码：<input type="password" name="password" id="password" /><br/><br/>
  	性别：<input type="radio" name ="sex" value ="男">男
  		<input type="radio" name ="sex" value ="女">女<br/><br/>
  	年龄：<input type="text" name ="age"  id="age" ><br/><br/>
  	爱好：<input type ="checkbox" name ="hobit" value = "看电影">看电影
  		<input type ="checkbox" name ="hobit" value = "听音乐">听音乐
  		<input type ="checkbox" name ="hobit" value = "读书">读书
  		<input type ="checkbox" name ="hobit" value = "跑步">跑步<br><br>
  	邮箱：<input type="email" name = "email" id="email"  ><br/><br/>
  	家庭地址：<input type ="text" name ="address" id="address"><br/><br/>
  	<input type="submit" name="button" value="插入用户"/>
</form>
<%}else{%>
	还没登录呢，请先去<a href="login.jsp">登录</a>。
<%}
%>
</body>
</html>