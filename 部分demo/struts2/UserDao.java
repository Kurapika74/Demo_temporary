package com.qhit.dao;

import java.util.ArrayList;
import java.util.List;

import com.qhit.beans.User;
import com.qhit.core.DBHelper;

public class UserDao extends DBHelper{
	
	public boolean login(User user){
		String sql = "select * from t_user where account = ? and password = ?";
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1,user.getAccount());
			ps.setString(2,user.getPassword());
			rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}

	public List<User> queryUser(){
		List<User> list = new ArrayList<User>();
		String sql = "select * from t_user";
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setAccount(rs.getString("account"));
				user.setPassword(rs.getString("password"));
				user.setMobile(rs.getString("mobile"));
				user.setAge(rs.getInt("age"));
				user.setLive(rs.getString("live"));
				user.setSex(rs.getString("sex"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	
	public User getUserById(int id){
		User user = new User();
		String sql = "select * from t_user where id = ?";
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setAccount(rs.getString("account"));
				user.setPassword(rs.getString("password"));
				user.setMobile(rs.getString("mobile"));
				user.setAge(rs.getInt("age"));
				user.setLive(rs.getString("live"));
				user.setSex(rs.getString("sex"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return user;
	}
	
	public void addUser(User user){
		String sql = "insert into t_user values(sq.nextval,?,?,?,?,?,?,?)";
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getAccount());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getAge());
			ps.setString(4, user.getSex());
			ps.setString(5, user.getMobile());
			ps.setString(6, user.getName());
			ps.setString(7, user.getLive());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public void delUser(int id){
		String sql = "delete t_user where id = ?";
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public void updateUser(User user){
		String sql = "update  t_user set name = ?,mobile=?, live=? where id = ? ";
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getMobile());
			ps.setString(3, user.getLive());
			ps.setInt(4, user.getId());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	
	public static void main(String[] args) {
		// 登录
		User user = new User();
		user.setAccount("admi2n");
		user.setPassword("admin");
		UserDao dao = new UserDao();
		System.out.println(dao.login(user));
		
		//查询
		System.out.println(dao.queryUser().size());
		//插入
		user.setAge(12);
		user.setLive("学习");
		user.setName("其他");
		user.setMobile("110");
		user.setSex("1");
		dao.addUser(user);
		User user2 = dao.getUserById(245);
		System.out.println(user2.getAccount());
		
		dao.delUser(245);
		
	}
}
