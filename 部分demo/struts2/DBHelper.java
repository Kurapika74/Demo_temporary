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
	
	//��ȡ����
	public Connection getCon() throws Exception{
		// ��������
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//������
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","admin","admin");
		return con;
	}
	
	//�ر�����
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
