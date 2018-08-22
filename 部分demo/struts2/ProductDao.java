package com.qhit.xmceshi.dao;

import java.util.ArrayList;
import java.util.List;

import com.qhit.xmceshi.entity.Product;
import com.qhit.xmceshi.util.DBHelper;

public class ProductDao extends DBHelper {
	//查询所有商品
	public List<Product> getAll(){
		List<Product> plist=new ArrayList<Product>();
		try{
			conn=getconn();
			String sql="select * from product";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Product p=new Product();
				p.setPid(rs.getInt("pid"));
				p.setPname(rs.getString("pname"));
				p.setPcount(rs.getInt("pcount"));
				p.setPrice(rs.getDouble("price"));
				p.setImg(rs.getString("img"));
				plist.add(p);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return plist;
	}
	//根据ID查询商品
	public Product getOne(int id){
		Product p=null;
		try {
			conn=getconn();
			String sql="select * from product where pid=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				p=new Product();
				p.setPcount(rs.getInt("pcount"));
				p.setPid(rs.getInt("pid"));
				p.setPname(rs.getString("pname"));
				p.setPrice(rs.getDouble("price"));
				p.setImg(rs.getString("img"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	//分页查询
		public List<Product> getByPage(int indexpage,int pagesize){
			List<Product> plist=new ArrayList<>();
			try{
				conn=getconn();
				String sql="select * from product where pid limit ?,?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1,(indexpage-1) * pagesize );
				ps.setInt(2, pagesize);
				rs=ps.executeQuery();
				while(rs.next()){
					Product p=new Product();
					p.setPcount(rs.getInt("pcount"));
					p.setPid(rs.getInt("pid"));
					p.setPname(rs.getString("pname"));
					p.setPrice(rs.getDouble("price"));
					p.setImg(rs.getString("img"));
					plist.add(p);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				close();
			}
			return plist;
			
		}
		//根据id删除商品
				public int delete(int pid){
					int num=0;
					conn=getconn();
					String sql="delete from product where pid=?";
					try {
						ps=conn.prepareStatement(sql);
						ps.setInt(1, pid);
						num=ps.executeUpdate();
					} catch (Exception e) {
						
						e.printStackTrace();
					}finally{
						close();
					}
					return num;
				}
				
				//添加一个商品信息
				public int addProduct(Product p){
					int num=0;
					conn=getconn();
					String sql="insert into product(pname,pcount,price,img) values(?,?,?,?)";
					try {
						ps=conn.prepareStatement(sql);
						ps.setString(1, p.getPname());
						ps.setInt(2, p.getPcount());
						ps.setDouble(3, p.getPrice());
						ps.setString(4, p.getImg());
						num=ps.executeUpdate();
						
					} catch (Exception e) {
						
						e.printStackTrace();
					}finally{
						close();
					}
					return num;
				}
				//修改商品信息
				public int update(Product p){
					int num=0;
					conn=getconn();
					String sql="update product set pname=?,pcount=?,price=?,img=? where pid=?";
					try {
						ps=conn.prepareStatement(sql);
						ps.setString(1, p.getPname());
						ps.setInt(2, p.getPcount());
						ps.setDouble(3, p.getPrice());
						ps.setString(4, p.getImg());
						ps.setInt(5, p.getPid());
						num=ps.executeUpdate();
					} catch (Exception e) {
						
						e.printStackTrace();
					}finally{
						close();
					}
					return num;
					
				}

}
