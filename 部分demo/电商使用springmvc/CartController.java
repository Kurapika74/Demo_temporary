package com.xunfei.partal;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xunfei.core.common.Constants;
import com.xunfei.core.common.LocalSessionProvider;
import com.xunfei.core.pojo.cart.UserCart;
import com.xunfei.core.pojo.cart.UserItem;
import com.xunfei.core.pojo.product.Sku;
import com.xunfei.core.service.SkuService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
public class CartController {
	
	@Autowired
	SkuService Skuservice;
	
	@Autowired 
	LocalSessionProvider localSessionProvider;
	
	@Autowired
	JedisPool jedispool;
	
	ObjectMapper om=new ObjectMapper();
	
  //登录成功后点击购买
	@RequestMapping("/buy/shopping.html")
	public String cart(Long skuId,Integer amount,Model model,HttpServletRequest request,HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		//json对象序列化
	
	om.setSerializationInclusion(Inclusion.NON_NULL);//把null的值过滤掉
	
	
//创建购物车，List集合对象  request.getCookies这个方法只返回此次请求发送的Cookie对象，另外你可以遍历得到的Cookie，查看其中的内容。
	UserCart userCart=null;//为空要放入cookie中
     Cookie[] cookies = request.getCookies();
	
     //到cookies里查找购物车
    userCart= findUserCartByCookies(cookies);// 方法返回一个实例
     
	//判断购物车是否为空
    if(userCart==null) {
    	//初始化购物车
    	userCart=new UserCart();
    }
  //判断用户是否登录
  String username=localSessionProvider.getAttribute(request, response, Constants.USER_NAME);
  if(username!=null) {
	  //不为空用户已经登录
  }else {
	  //未登录下购物车的处理
	  Jedis jedis = jedispool.getResource();
	  //把cookie中信息保存到redis中
	  List<UserItem>items=userCart.getItems();
	  for (UserItem userItem : items) {
	jedis.lpush("userCart"+username,userItem.getSku().getId().toString());
	//usename做唯一标识,后面是key,value,1保存购物项
	//2保存购物项中数据
	jedis.hset("userItem:"+username,userItem.getSku().getId().toString(),userItem.getAmount().toString());
	  }
	  //清空购物车和cookie信息
	  userCart.clearCart();
	  clearCart(request,response);
  
   //添加购物车信息到redis中,key与value
    if(skuId !=null) {
    	//保存购物项
    	jedis.lpush("userCart:"+username, skuId.toString());
    	//保存购物车中数据
    	jedis.hset("userItem"+username, skuId.toString(), amount.toString());
    }
    //把redis中数据遍历加载到购物车中
    List<String> keys = jedis.lrange("userCart:"+username,0, -1);
    if(keys!=null) {
    	for (String skid : keys) {
			
		//创建sku
    	Sku sku=new Sku();
    	sku.setId(Long.parseLong(skid));
    	//创建购物项
    	UserItem userItem=new UserItem();
   userItem.setAmount(Integer.parseInt(jedis.hget("userItem"+username, skid)));
    
    	//添加购物车
    	userCart.addUserItem(userItem);
    	
  
  }
  }
  }
   //把购买的商品添加到购物车
    if(skuId!=null) {
    	//把商品追加到购物车
    	userItemToCart(userCart,skuId,amount,response);
    }
    
//页面需要显示购物车信息，需要把购物车中数据全部加载出来，然后返回到页面进行显示
    showCart(userCart);
    
 //购物车中数据排序，对集合
    Collections.sort(userCart.getItems(),new Comparator<UserItem>() {

    	
    	//对集合进行倒序
		@Override
		public int compare(UserItem o1, UserItem o2) {
			// TODO 自动生成的方法存根
			return -1;
		}
	});//传一个集合对象
    
    
    
    
    //把购物车放入页面,放model中
    model.addAttribute("userCart", userCart);
		
		
		return "product/cart";//返回product包下的cart.jsp
		
		
	}
 
	//实现 把商品添加到购物车中的方法，回写Cookie
	private void userItemToCart(UserCart userCart, Long skuId, Integer amount, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		// TODO 自动生成的方法存根
		
		//创建sku对象
		Sku sku=new Sku();
		sku.setId(skuId);
		//创建购物项a类对象
		UserItem userItem=new UserItem();
		userItem.setAmount(amount);
		userItem.setSku(sku);
		
		//把购物项添加到购物车
		userCart.addUserItem(userItem);
		
		//写回浏览器
		//先把数据放到内存中。再写回到浏览器
		om.setSerializationInclusion(Inclusion.NON_NULL);
		StringWriter w=new StringWriter();
		om.writeValue(w, userCart);//把购物车转换成json,数据写到内存中
		
		//回写浏览器
		
		//key是常量，值是json串
		Cookie cookie=new Cookie(Constants.USER_CART, w.toString());
	     cookie.setMaxAge(60*60*2*7);//保存一周
	     cookie.setPath("/");
	     response.addCookie(cookie);
	     /*response.addCookie(cookie);这不是先放到response中了么?
	      * 然后去客户端.放到客户端的机器上.然后它要是再访问你的服务器时就会带着这些cookie并在request中放置着来找你.然后你就可以在request中找到这些cookie*/
	}

	/**
	 * 
	 * 从Cookie中查找购物车,传入cookies
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * 
	 **/
	private UserCart findUserCartByCookies(Cookie[] cookies) throws JsonParseException, JsonMappingException, IOException {
		if(cookies!=null && cookies.length>0) {
			
			for (Cookie cookie : cookies) {
				//判断常量作为Cooie的key是否匹配
				if(Constants.USER_CART.equals(cookie.getName())) {
					String value=cookie.getValue();//取得Cookie的值
				//我们把字符串value改为购物车对象	
					return om.readValue(value, UserCart.class);
				}
			}
		}
		
		
		return null;
	}
	//购物车数据加载回显
	 private void showCart(UserCart userCart) {
		
		 List<UserItem>userItems = userCart.getItems();
			if(userItems!=null) {
				//通过sku把里面东西都加载出来
				for (UserItem userItem : userItems) {
				Sku sku=Skuservice.loadSkuById(userItem.getSku().getId());
				userItem.setSku(sku);
				
				}
			}
		}
//	 @RequestMapping("/userItem/addAmount.html")
//	 public void userItemAddamount(long skuId,Integer amount  ,HttpServletRequest request,HttpServletResponse response){
//		 
//	 UserCart userCart = new UserCart();
//		
//		//判断用户是否登录
//		String username = localSessionProvider.getAttribute(request, response, Constants.USER_NAME);
//		if(username !=null) {
//			Jedis jedis = JedisPool.getResource();
//			//数量追加
//			jedis.hincrBy("userItem:"+username,skuId.toString(),Long.parseLong(amount.toString()));
//			
//			//封装购物车数据
//			List<String> keys = jedis.lrange("userCart:"+username, 0, -1);
//			for (String key : keys) {
//				Sku sku = new Sku();
//				sku.setId(Long.parseLong(key));
//				UserItem userItem = new UserItem();
//				userItem.setSku(sku);
//				userItem.setAmount(Integer.parseInt(jedis.hget("userItem:"+username, key.toString())));
//				userCart.addUserItem(userItem);
//			}
//		}else {
//			om.setSerializationInclusion(Inclusion.NON_NULL);
//			userCart = findUserCartByCookies(request.getCookies());
//			// 追加商品的时候，如果是重复会对数量进行叠加
//			userItemToCart(userCart, skuId, amount, response);
//		}
////		
////		// 回显数据
////		showCart(userCart);
//		// 重新计算 小计，价格 运费 总价格
//		JSONObject jo = new JSONObject();
//		jo.put("allProductAmount", userCart.getAllProductAmount());
//		jo.put("price", userCart.getPrice());
//		jo.put("extra", userCart.getExtra());
//		jo.put("allPrice", userCart.getAllPrice());
//		response.getWriter().write(jo.toString());
//	}

	 
	//清空购物车
	 @RequestMapping("/cart/clearCart,html") 
	public String clearCart(HttpServletRequest request,HttpServletResponse response) {
		//清空Cookie
	Cookie cookie=new Cookie(Constants.USER_CART,null);//key常量，vaalue为null
		 cookie.setMaxAge(0);
		 cookie.setPath("/");
		 response.addCookie(cookie);
		 return "redirect:/buy/shopping.html";
		 
	 }
	 
	 
	 
	 
	 
	 
}
