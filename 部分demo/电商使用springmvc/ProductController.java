package com.xunfei.console.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.xunfei.core.pojo.Brand;
import com.xunfei.core.pojo.BrandExample;
import com.xunfei.core.pojo.PageInfoPaging;
import com.xunfei.core.pojo.product.Color;
import com.xunfei.core.pojo.product.ColorCriteria;

import com.xunfei.core.pojo.product.Feature;
import com.xunfei.core.pojo.product.FeatureCriteria;
import com.xunfei.core.pojo.product.Img;
import com.xunfei.core.pojo.product.Product;
import com.xunfei.core.pojo.product.ProductCriteria;
import com.xunfei.core.pojo.product.ProductCriteria.Criteria;
import com.xunfei.core.pojo.product.Sku;
import com.xunfei.core.pojo.product.Type;
import com.xunfei.core.pojo.product.TypeCriteria;
import com.xunfei.core.service.BrandService;
import com.xunfei.core.service.ColorService;
import com.xunfei.core.service.FeatureService;
import com.xunfei.core.service.ImgService;
import com.xunfei.core.service.ProductService;
import com.xunfei.core.service.SkuService;
import com.xunfei.core.service.TypeService;

//商品模块 下面的路径是product_main.jsp中的

@Controller
public class ProductController {
@RequestMapping("/product/list.do")
public String productList(String name,Long brandId,Boolean isShow,Integer pageNo,Model model) {	
//参数判断：1加载品牌数据
	BrandExample brandExample=new BrandExample();
    brandExample.setStatus(1);
  List<Brand> brands=brandService.selectByExample(brandExample);
  model.addAttribute("brands", brands);

	
	return "product/list";
	
	
	}

//注入2个不同的service

@Autowired
TypeService typeService;

@Autowired
BrandService brandService;

@Autowired
FeatureService featureService;

@Autowired
ColorService colorService;


//不简写DAO层是为了控制事务
@Autowired
ProductService productService;

@Autowired
SkuService  skuService;

@Autowired
ImgService imgService;

	
//去添加頁面
@RequestMapping("/product/add.do")

public String add(Model model) {
	
//初始化数据，商品添加的基本信息，各项信息初始化 long加l
	
//商品类型  调方法，条件做判断，父id不为0,放model进行回显
TypeCriteria typeCriteria=new TypeCriteria();
typeCriteria.createCriteria().andParentIdNotEqualTo(0l);
List<Type> types =typeService.selectTypeByTypeCriteria(typeCriteria);	
model.addAttribute("types", types);
 
 
//商品品牌 BrandExample new 为实体类 
BrandExample brandExample=new BrandExample();
brandExample.setStatus(1);
 List<Brand> blist= brandService.selectByExample(brandExample);
model.addAttribute("brands", blist);


//材质,对应查询材质表里的东西,封装为1可以使用
FeatureCriteria featureCriteria=new FeatureCriteria();
featureCriteria.createCriteria().andIsDelEqualTo(true);//此时为boolean类型的
List<Feature> features=featureService.selectFeatureByFeatureCriteria(featureCriteria);
model.addAttribute("features", features);


	
//颜色,从数据库加载数据到jsp,排除0的大色系
ColorCriteria colorCriteria=new ColorCriteria();
colorCriteria.createCriteria().andParentIdNotEqualTo(0l);	
List<Color>colors=colorService.selectColorByColorCriteria(colorCriteria);	
model.addAttribute("colors", colors);
	
	
	
	return "product/add";
	
}

//保存数据
@RequestMapping("/product/save.do")

public String save(Product product) {
	//保存商品数据1上下架
	product.setIsShow(false);//下架状态
	product.setIsDel(false);//默认没删除，想删除改true
	product.setCreateTime(new Date());//生成时间
	
	//添加到数据库操作注入@productservice,把product对象插入数据库
	productService.insertSelective(product);
	
	//插入数据库后并如何返回id呢？在mapper中加入useGeneratedKeys="true" keyProperty="id"
	 
	//保存图片数据
   Img img=product.getImg();
   img.setIsDef(false);
   img.setProductId(product.getId());
   imgService.insertSelective(img);
   
   //保存sku，最小销售单元，颜色x尺码
     String colors= product.getColors();
	 String sizes= product.getSizes();
       
   //遍历颜色和尺码和创建sku,jsp尺码是一个复选框，字符串进行分割，逗号分隔。
	 for (String  color :  colors.split(",")) {
		 //创建sku对象,并初始化,每项对应每一列中的
		 Sku sku=new Sku();
		 sku.setColorId(Long.parseLong(color));
		 sku.setCreateTime(new Date());
		 sku.setProductId(product.getId());//设置商品id
		for (String  size : sizes.split(",")) {
			//尺码baocun
			sku.setSize(size);
			//运费
	       sku.setDeliveFee(10f);
			//市场价格
	       sku.setMarketPrice(150f);
			//售价
	       sku.setPrice(99f);
			//库存
	       sku.setStock(100);
			//购买限制,最后注入skuservice
	       sku.setUpperLimit(100);
	       
	       
	       skuService.insertSelective(sku);
	       
		}
	}
	 
       
    //这个  return"redirect:/product/list.do"; 
	return null;
}




/*商品上架,解决list.jsp中onsale函数商品上架操作
   提交的action地址*/

@RequestMapping("/product/onSale.do")
public String onSale(Long[]ids) {
	for (Long long1 : ids) {
	System.out.println(long1);
	}
	//编写商品上架的方法
	productService.onSale(ids);
	
	
	
	
	//上架成功需要重定向
	return "redirect:/product/list.do";
}



}
