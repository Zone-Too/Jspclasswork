<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" import="cn.edu.ujn.model.User" import="cn.edu.ujn.dao.IUserDao" import="cn.edu.ujn.dao.UserDaoImpl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户信息主界面</title>
</head>
<body>
<script type="text/javascript">
	function check(){
		var inputdate = document.getElementById("search-uid");
		if(inputdate.value==""){
			alert("请输入你要查询的id");
			return false;
		}
		return true;
	}
	
	function check2(){
		var inputdate = document.getElementById("search-uname");
		if(inputdate.value==""){
			alert("请输入你要查询的字段");
			return false;
		}
		return true;
	}
</script>
	<%
	int currentpage=1;
	int pagesize = 7;
	IUserDao userdao = new UserDaoImpl();
	int pagecount = userdao.getPageCount(pagesize);
	if(request.getParameter("currentpage")!=null){
		currentpage = Integer.parseInt(request.getParameter("currentpage"));
		currentpage = currentpage<1?1:currentpage;
	}
	%>

	<%
	if(session.getAttribute("activeuser") != null){
	out.println(session.getAttribute("activeuser")+"登录成功！");	
	ArrayList<User> userlist=(ArrayList<User>)request.getAttribute("userList");
	if(userlist!=null&&userlist.size()>0){ %>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>用户名</th>
				<th>密码</th>
				<th>性别</th>
				<th>年龄</th>
				<th>爱好</th>
				<th>邮箱</th>
				<th>地址</th>
				<th>操作</th>
			</tr>
			<% for(int i=0;i<userlist.size();i++){ %>
			<tr>
			  <td><%=userlist.get(i).getId() %></td>
			  <td><a href = "showoneuser.jsp?userid=<%=userlist.get(i).getId() %>&pagesize=<%=pagesize %>&currentpage=<%=currentpage %>"><%=userlist.get(i).getUsername() %><a/></td>
			  <td><%=userlist.get(i).getPassword() %></td>
			  <td><%=userlist.get(i).getSex() %></td>
			  <td><%=userlist.get(i).getAge() %></td>
			  <td><%=userlist.get(i).getHobit() %></td>
			  <td><%=userlist.get(i).getEmail() %></td>
			  <td><%=userlist.get(i).getAddress() %></td>
			  <td><a href = "updateuser.jsp?userid=<%=userlist.get(i).getId() %>&pagesize=<%=pagesize %>&currentpage=<%=currentpage %>">修改用户</a>&nbsp;&nbsp; <a href ="UserServlet?action=deleteoneuser&userid=<%=userlist.get(i).getId() %>&pagesize=<%=pagesize %>&currentpage=<%=currentpage %>">删除用户</a> </td>
			</tr>
		<% }
     	%>	<br/><br/>
     	<a href = "adduser.jsp?pagesize=<%=pagesize %>&currentpage=<%=pagecount %>">添加一名用户</a><br><br>
     	<a href = "logout.jsp">退出登录</a><br><br>
     	<form action="UserServlet?action=findoneuser" method="post" onsubmit="return check()">
     	<label>请输入要查询的用户ID<br><br><input type="text" name="userid" id="search-uid" /></label>&nbsp;
     	<input type="submit" value="准确查询" >
     	</form><br><br>
     	
     	<form action="UserServlet?action=findsomeuser" method="post" onsubmit="return check2()">
     	<label>请输入要查询的字段<br><br><input type="text" name="userdate" id="search-uname"/></label>&nbsp;
     	<input type="submit" value="模糊查询" >
     	</form><br><br>
		</table>	
		共<%=pagecount %>页，当前<%=currentpage %>/<%=pagecount %>页，&nbsp;&nbsp;
		<%if(currentpage<pagecount && currentpage >0){ %>
		<a href ="UserServlet?action=findallsub&currentpage=<%=currentpage - 1%>&pagesize=<%=pagesize %>">上一页</a> &nbsp;<a href ="UserServlet?action=findallsub&currentpage=<%=currentpage + 1%>&pagesize=<%=pagesize %>">下一页</a>
	<%}else if(currentpage>=pagecount){ %>
		<a href ="UserServlet?action=findallsub&currentpage=<%=currentpage - 1%>&pagesize=<%=pagesize %>">上一页</a> &nbsp;<a href ="UserServlet?action=findallsub&currentpage=<%=pagecount %>&pagesize=<%=pagesize %>">下一页</a>
	
	<% }
	}
	}else{%> 
		还没登录呢，请先去<a href="login.jsp">登录</a>。
	<%}
	%>
</body>
</html>