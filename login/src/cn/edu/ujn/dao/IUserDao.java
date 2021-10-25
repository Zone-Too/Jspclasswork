package cn.edu.ujn.dao;

import java.util.ArrayList;

import cn.edu.ujn.model.User;

public interface IUserDao {
//	用户注册程序
	public boolean register(User user);
//	用户登录程序
	public User login(String username, String password);
//	查询所有用户
	public ArrayList<User> findAllUsers();
//	分页查询用户
	public ArrayList<User> findUsers(int currentpage,int pagesize);
//	添加一个用户
	public boolean addUser(User user);
//	用户修改
	public boolean updateUser(int id,User user);
//	用户删除
	public boolean deleteUserById(int id);
//	查询一个用户
	public ArrayList<User> getUserById(int id);
//	模糊查询
	public ArrayList<User> getSomeUser(String userdate);
//	获取页数
	public int getPageCount(int pagesize);
//  检验用户存在
	public boolean checkUsername(User user);
}
