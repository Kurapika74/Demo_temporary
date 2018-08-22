package com.qingniao.core.dao;

import java.util.List;

import com.xunfei.core.pojo.Brand;
import com.xunfei.core.pojo.BrandExample;

//品牌dao
public interface BrandMapper {

	public void insertBrand(Brand brand); 
	public List<Brand> selectByExample(BrandExample brandExample);
    
	public List<Brand> queryForPage(Brand brand);
	
	public void batchDelete(Long[] ids);
     
	public Brand selectById(Long id);//查询前的搜索
	
	public void editSave(Brand brand);
	
	//不分页版本的
	public List<Brand> selectAllByExample(BrandExample brandExample);
	
	
	
}
