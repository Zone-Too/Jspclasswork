<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台登陆界面</title>
</head>
<body>
<script type="text/javascript">
	function check(){
		var inputdate = document.getElementById("username");
		if(inputdate.value==""){
			alert("请输入名字");
			return false;
		}
		inputdate = document.getElementById("password");
		if(inputdate.value==""){
			alert("请输入密码");
			return false;
		}
		return true;
	}
	
	function reget(code){
		code.src="UserServlet?action=code&time=" + new Date().getTime();
	}
</script>
<%
	int pagesize =7;
%>
 <form action="UserServlet?action=login&pagesize=<%=pagesize %>" method="post" onsubmit="return check()">
  	用户名：<input type="text" name="username" id="username"/><br/><br/><br>
  	密码：<input type="password" name="password" id="password" /><br/><br/>
  	<label> 验证码：<input type="text" name="checkcode" />&nbsp;&nbsp;&nbsp;
  	<img  src="UserServlet?action=code" onclick="reget(this)" width=100px height=35px  ></label><br><br>
  	<input type="submit" name="button" value="登录"/>
  	<input type="reset" value="重置" onclick="check()"/>
  </form>
  <br/><br/>
  还没有账号？&nbsp;点击<a href="register.jsp">这里</a>注册！
</body>
</html>