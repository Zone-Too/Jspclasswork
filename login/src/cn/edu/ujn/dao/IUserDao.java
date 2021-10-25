package cn.edu.ujn.dao;

import java.util.ArrayList;

import cn.edu.ujn.model.User;

public interface IUserDao {
//	�û�ע�����
	public boolean register(User user);
//	�û���¼����
	public User login(String username, String password);
//	��ѯ�����û�
	public ArrayList<User> findAllUsers();
//	��ҳ��ѯ�û�
	public ArrayList<User> findUsers(int currentpage,int pagesize);
//	���һ���û�
	public boolean addUser(User user);
//	�û��޸�
	public boolean updateUser(int id,User user);
//	�û�ɾ��
	public boolean deleteUserById(int id);
//	��ѯһ���û�
	public ArrayList<User> getUserById(int id);
//	ģ����ѯ
	public ArrayList<User> getSomeUser(String userdate);
//	��ȡҳ��
	public int getPageCount(int pagesize);
//  �����û�����
	public boolean checkUsername(User user);
}
