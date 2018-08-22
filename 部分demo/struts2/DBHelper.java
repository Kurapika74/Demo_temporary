package com.qhit.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBHelper {
	
	public Connection con;
	public PreparedStatement ps;
	public ResultSet rs;
	
	//获取连接
	public Connection getCon() throws Exception{
		// 加载驱动
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//打开连接
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","admin","admin");
		return con;
	}
	
	//关闭连接
	public void close(){
		try {
			if(null != rs){
				rs.close();
			}
			if(null != ps){
				ps.close();
			}
			if(null != con){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
