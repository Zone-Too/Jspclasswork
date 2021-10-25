package cn.edu.ujn.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.ujn.dao.IUserDao;
import cn.edu.ujn.dao.UserDaoImpl;
import cn.edu.ujn.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		IUserDao userDao = new UserDaoImpl();
		String action = request.getParameter("action");
//		登录
		if (action.equals("login")) {
			User user=userDao.login(username, password);
			String pagesize =request.getParameter("pagesize");
			String picture_code = request.getSession().getAttribute("picture_code").toString();
			String checkcode = request.getParameter("checkcode");
			if (user != null && checkcode.equals(picture_code)) {
				request.getSession().setAttribute("activeuser", user.getUsername());
				response.sendRedirect("UserServlet?action=findall&pagesize="+pagesize);
			} else {
				response.sendRedirect("login.jsp");
			}
		}
//      注册
		if (action.equals("register")) {
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			String[] hobits = request.getParameterValues("hobit");
			String hobit = "";
			for(int i = 0; i<hobits.length-1; i++) {
				hobit = hobit + hobits[i] + ",";
			}
			hobit = hobit + hobits[hobits.length-1];
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			User user = new User();
			user.setPassword(password);
			user.setUsername(username);
			user.setSex(sex);
			user.setAge(age);
			user.setHobit(hobit);
			user.setEmail(email);
			user.setAddress(address);
			if (userDao.checkUsername(user)) {
				response.getWriter().println("用户名已存在，即将为您重新跳转至登录册页面");
				response.setHeader("refresh", "3;url=login.jsp");
			}else {
				try {
					if (userDao.register(user)) {
						response.getWriter().println("注册成功，3秒后返回登录页面");
						response.setHeader("refresh", "3;url=login.jsp");
					} else
						response.getWriter().println("注册失败！");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
//		查询所有信息
		if(action.equals("findall")) {
			ArrayList<User> userlist=userDao.findUsers(1,Integer.parseInt(request.getParameter("pagesize")));
			request.setAttribute("userList", userlist);
			request.getRequestDispatcher("success.jsp").forward(request, response);
		}
//		分页查询
		if(action.equals("findallsub")) {
			int currentpage;
			if(Integer.parseInt(request.getParameter("currentpage"))<=0) {
				currentpage = 1;
			}else {
				currentpage = Integer.parseInt(request.getParameter("currentpage"));
			}
			ArrayList<User> userlist=userDao.findUsers(currentpage,Integer.parseInt(request.getParameter("pagesize")));
			request.setAttribute("userList", userlist);
			request.getRequestDispatcher("success.jsp").forward(request, response);
		}
		
		if(action.equals("adduser")) {
			String pagesize = request.getParameter("pagesize");
			String currentpage = request.getParameter("currentpage");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			String[] hobits = request.getParameterValues("hobit");
			String hobit = "";
			for(int i = 0; i<hobits.length-1; i++) {
				hobit = hobit + hobits[i] + ",";
			}
			hobit = hobit + hobits[hobits.length-1];
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			User user = new User();
			user.setPassword(password);
			user.setUsername(username);
			user.setSex(sex);
			user.setAge(age);
			user.setHobit(hobit);
			user.setEmail(email);
			user.setAddress(address);
			try {
				if (userDao.addUser(user)) {
					response.getWriter().println("插入成功，3秒后返回全部信息页面");
					response.sendRedirect("UserServlet?action=findallsub&pagesize="+pagesize+"&currentpage="+currentpage);
				} else
					response.getWriter().println("插入失败！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(action.equals("findoneuser")) {
			if(request.getParameter("userid")!=null && !request.getParameter("userid").equals("")) {
			ArrayList<User> user=userDao.getUserById(Integer.parseInt(request.getParameter("userid")));
			if(user != null && user.size()!=0) {
				request.setAttribute("userList", user);
				request.getRequestDispatcher("success.jsp").forward(request, response);
			}else {
				ArrayList<User> userlist=userDao.findAllUsers();
				request.setAttribute("userList", userlist);
				request.getRequestDispatcher("success.jsp").forward(request, response);
			}
			}
		}
		
		if(action.equals("updateuser")) {
			String pagesize = request.getParameter("pagesize");
			String pagecount = request.getParameter("currentpage");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			String[] hobits = request.getParameterValues("hobit");
			String hobit = "";
			for(int i = 0; i<hobits.length-1; i++) {
				hobit = hobit + hobits[i] + ",";
			}
			hobit = hobit + hobits[hobits.length-1];
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			User user = new User();
			user.setPassword(password);
			user.setUsername(username);
			user.setSex(sex);
			user.setAge(age);
			user.setHobit(hobit);
			user.setEmail(email);
			user.setAddress(address);
			try {
				if (userDao.updateUser(Integer.parseInt(request.getParameter("userid")),user)) {
					response.getWriter().println("更新成功，3秒后返回全部信息页面");
					response.sendRedirect("UserServlet?action=findallsub&pagesize="+pagesize+"&currentpage="+pagecount);
				} else
					response.getWriter().println("更新失败！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(action.equals("findsomeuser")) {
			ArrayList<User> user=userDao.getSomeUser(request.getParameter("userdate"));
			if(user != null && user.size()!=0) {
				request.setAttribute("userList", user);
				request.getRequestDispatcher("success.jsp").forward(request, response);
			}else {
				ArrayList<User> userlist=userDao.findAllUsers();
				request.setAttribute("userList", userlist);
				request.getRequestDispatcher("success.jsp").forward(request, response);
			}
		}
		
		
		
		if(action.equals("deleteoneuser")) {
			String pagesize = request.getParameter("pagesize");
			String currentpage = request.getParameter("currentpage");
			try {
				if (userDao.deleteUserById(Integer.parseInt(request.getParameter("userid")))) {
					response.sendRedirect("UserServlet?action=findallsub&pagesize="+pagesize+"&currentpage="+currentpage);
				} else
					response.getWriter().println("删除失败！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 验证码
				if (action.equals("code")) {
					// BufferedImage类用来处理和操纵图像数据，所有 BufferedImage 对象的左上角坐标为（0,0）
					// 定义BufferedImage对象
					BufferedImage bImage = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);// 图像类型不透明
					// 获得Graphics对象
					Graphics graphics = bImage.getGraphics();
					// 构建一个颜色
					Color color = new Color(255, 255, 255);
					// 设置颜色
					graphics.setColor(color);
					// 填充区域
					graphics.fillRect(0, 0, 68, 22);
					// 定义一个数组来存放用来随机的字母和数字
					char[] ch = "QWERTYUIOPLKJHGFDSAZXCVBNM0123456789".toCharArray();
					// 定义一个随机对象
					Random r = new Random();

					int len = ch.length;
					int index;
					String str = "";
					for (int i = 0; i < 4; i++) {
						// 产生一个随机数
						index = r.nextInt(len);
						// 随机设置一个颜色,跟背景颜色相区别
						graphics.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
						// 使用Graphics绘制字符串
						graphics.drawString(ch[index] + "", (i * 15) + 5, 16);
						str += ch[index];
					}
					// 将验证码信息存入session中
					request.getSession().setAttribute("picture_code", str);
					// 使用ImageIO输出图片
					ImageIO.write(bImage, "JPG", response.getOutputStream());

				}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
