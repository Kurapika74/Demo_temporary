package com.qhit.xmceshi.dao;

import com.qhit.xmceshi.entity.Account;
import com.qhit.xmceshi.entity.Super;
import com.qhit.xmceshi.util.DBHelper;

public class SuperDao extends DBHelper {
		//��ӳ�������Ա
	public int addSuper(Super s){
		int num=0;
		conn=getconn();
		String sql="insert into super(sname,sid) values(?,?)";
		try{
			ps=conn.prepareStatement(sql);
			ps.setString(1, s.getSname());
			ps.setString(2, s.getSpwd());
			num=ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return num;
		
	}
	//��ѯ�û�������
	public Super getAll(String sname,String spwd){
		Super u=null;
		conn=getconn();
		String sql="select * from super where sname=? and spwd=?";
		try{
			ps=conn.prepareStatement(sql);
			ps.setString(1, sname);
			ps.setString(2, spwd);
			rs=ps.executeQuery();
		
			while(rs.next()){
				u=new Super();
				u.setSid(rs.getInt("sid"));
				u.setSname(rs.getString("sname"));
				u.setSpwd(rs.getString("spwd"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return u;
		
	}
}
