package cn.edu.ujn.dao;

import cn.edu.ujn.model.User;
import java.sql.*;
import java.util.ArrayList;

public class UserDaoImpl implements IUserDao {

	public boolean register(User user){
		try {
			Connection con = DBHelper.getConnection();
			String sql = "insert into user(username,password,sex,age,hobit,email,address) values(?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());	
			st.setString(3, user.getSex());
			st.setInt(4, user.getAge());
			st.setString(5, user.getHobit());
			st.setString(6, user.getEmail());
			st.setString(7, user.getAddress());
			int n = st.executeUpdate();
			st.close();
			con.close();
			if (n > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ce) {
			System.out.println(ce);
			return false;
		}

	}

	public User login(String username, String password) {
		User user = null;
		try {
			Connection con = DBHelper.getConnection(); 
			String sql = "select * from user where username=? and password=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setAge(rs.getInt("age"));
				user.setHobit(rs.getString("hobit"));
				user.setEmail (rs.getString("email"));
				user.setAddress(rs.getString("address"));
			}
			rs.close();
			st.close();
			con.close();
			return user;
		} catch (Exception ce) {
			System.out.println(ce);
			return user;
		}

	}

	@Override
	public ArrayList<User> findAllUsers() {
		// TODO Auto-generated method stub
		User user = null;
		ArrayList<User> userList=new ArrayList<User>();
		try {
			Connection con = DBHelper.getConnection(); 
			String sql = "select * from user";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setAge(rs.getInt("age"));
				user.setHobit(rs.getString("hobit"));
				user.setEmail (rs.getString("email"));
				user.setAddress(rs.getString("address"));
				userList.add(user);
			}
			rs.close();
			st.close();
			con.close();
			return userList;
		} catch (Exception ce) {
			System.out.println(ce);
			return null;
		}
	}

	public ArrayList<User> findUsers(int currentpage,int pagesize) {
		// TODO Auto-generated method stub
		User user = null;
		ArrayList<User> userList=new ArrayList<User>();
		try {
			Connection con = DBHelper.getConnection(); 
			String sql = "select * from user limit ?,?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, (currentpage-1)*pagesize);
			st.setInt(2, pagesize);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setAge(rs.getInt("age"));
				user.setHobit(rs.getString("hobit"));
				user.setEmail (rs.getString("email"));
				user.setAddress(rs.getString("address"));
				userList.add(user);
			}
			rs.close();
			st.close();
			con.close();
			return userList;
		} catch (Exception ce) {
			System.out.println(ce);
			return null;
		}
	}
	
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		try {
			Connection con = DBHelper.getConnection();
			String sql = "insert into user(username,password,sex,age,hobit,email,address) values(?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());	
			st.setString(3, user.getSex());
			st.setInt(4, user.getAge());
			st.setString(5, user.getHobit());
			st.setString(6, user.getEmail());
			st.setString(7, user.getAddress());
			int n = st.executeUpdate();
			st.close();
			con.close();
			if (n > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ce) {
			System.out.println(ce);
			return false;
		}
		
	}

	public boolean updateUser(int id,User user) {
		// TODO Auto-generated method stub
		try {
			Connection con = DBHelper.getConnection();
			String sql = "update user set username=?,password=?,sex=?,age=?,hobit=?,email=?,address=? where id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());	
			st.setString(3, user.getSex());
			st.setInt(4, user.getAge());
			st.setString(5, user.getHobit());
			st.setString(6, user.getEmail());
			st.setString(7, user.getAddress());
			st.setInt(8, id);
			int n = st.executeUpdate();
			st.close();
			con.close();
			if (n > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ce) {
			System.out.println(ce);
			return false;
		}
		
	}

	public boolean deleteUserById(int id) {
		// TODO Auto-generated method stub
		try {
			Connection con = DBHelper.getConnection(); 
			String sql = "delete from user where id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
			st.close();
			con.close();
			return true;
		} catch (Exception ce) {
			System.out.println(ce);
			return false;
		}

	}

	public ArrayList<User> getUserById(int id) {
		// TODO Auto-generated method stub
		User user = null;
		ArrayList<User> userList=new ArrayList<User>();
		try {
			Connection con = DBHelper.getConnection(); 
			String sql = "select * from user where id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setAge(rs.getInt("age"));
				user.setHobit(rs.getString("hobit"));
				user.setEmail (rs.getString("email"));
				user.setAddress(rs.getString("address"));
				userList.add(user);
			}
			rs.close();
			st.close();
			con.close();
			return userList;
		} catch (Exception ce) {
			System.out.println(ce);
			return null;
		}
	}
	
	public ArrayList<User> getSomeUser(String userdate) {
		User user = null;
		ArrayList<User> userList=new ArrayList<User>();
		try {
			Connection con = DBHelper.getConnection(); 
			String sql = "SELECT * FROM user Where username like concat('%',?,'%')";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, userdate);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setAge(rs.getInt("age"));
				user.setHobit(rs.getString("hobit"));
				user.setEmail (rs.getString("email"));
				user.setAddress(rs.getString("address"));
				userList.add(user);
			}
			rs.close();
			st.close();
			con.close();
			return userList;
		} catch (Exception ce) {
			System.out.println(ce);
			return null;
		}
	}

	
	public int getPageCount(int pagesize) {
		try {
			Connection con = DBHelper.getConnection(); 
			String sql = "SELECT count(*) FROM user";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			int records=0;
			if(rs.next()) {
				records = rs.getInt(1);
			}
				
			rs.close();
			st.close();
			con.close();
			return (records-1)/pagesize+1;
		} catch (Exception ce) {
			System.out.println(ce);
			return 0;
		}
	}
	
	public boolean checkUsername(User user) {
		try {
			Connection con = DBHelper.getConnection();
			String sql = "select * from user where username=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user.getUsername());
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				//System.out.println(rs.getString(1));
				if (rs.getString(1)!=null) {
					return true;
				}else {
					return false;
				}
			}
			rs.close();
			st.close();
			con.close();
			return false;
		} catch (Exception ce) {
			System.out.println(ce);
			return false;
		}
	}
	

}
