package com.qhit.xmceshi.dao;


import com.qhit.xmceshi.entity.Account;
import com.qhit.xmceshi.util.DBHelper;

public class AccountDao extends DBHelper {
	//添加账户
		public int addUser(Account a){
			int num=0;
			conn=getconn();
			String sql="insert into account(aname,apwd,uid) values(?,?,?)";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, a.getAname());
				ps.setString(2, a.getApwd());
				ps.setInt(3, a.getU().getUid());
				num=ps.executeUpdate();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return num;
			
		}
		//根据用户名和密码查询
		public Account getAll(String aname,String apwd){
			Account u=null;
			conn=getconn();
			String sql="select * from account where aname=? and apwd=?";
			try{
				ps=conn.prepareStatement(sql);
				ps.setString(1, aname);
				ps.setString(2, apwd);
				rs=ps.executeQuery();
			
				while(rs.next()){
					u=new Account();
					u.setAid(rs.getInt("aid"));
					u.setAname(rs.getString("aname"));
					u.setApwd(rs.getString("apwd"));
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				close();
			}
			return u;
			
		}
}
