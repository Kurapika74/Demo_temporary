package com.xunfei.partal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xunfei.core.common.Constants;
import com.xunfei.core.common.LocalSessionProvider;
import com.xunfei.core.pojo.user.User;
import com.xunfei.core.service.LoginService;


//处理登录的controler

@Controller
public class LoginController {
	
	//注入还没补充
	@Autowired
	LocalSessionProvider localSessionProvider;
	
	@Autowired
	LoginService  loginservice;
    
	
//1用户跳转的实现，处理get请求
	@RequestMapping(value="/login.html",method=RequestMethod.GET)
	public String Login(Model model,String url) {
	System.out.println(url);
	model.addAttribute("url", url);
	return "buyer/login";//返回登录页面
	
}
	//处理post请求,根据用户名密码判断是否登录成功,还有验证码的参数
	@RequestMapping(value="/login.html",method=RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse response,Model model,String username,String password,String code,String url){
	//1判断验证码是否输入
		if(code!=null) {
		//不为空,获取验证码并匹配
		String scode=localSessionProvider.getAttribute(request, response,Constants.USER_CODE);
		if(scode.toLowerCase().equals(code.toLowerCase())){	
		//.toLowerCase()方法将所有在此字符串中的字符使用默认语言环境的规则转化为小写	
		//2判断用户名和密码是否正确
	if(username !=null&&username.trim().length()>0 && password !=null && password.trim().length()>0) {
	//是获取name的值并且去除空格之后的长度.$.trim() 是去除空格的。	
	
		//去数据库中查询，创建service,返回当前用户
		User cuser = loginservice.getUserByUsernameAndPassword(username, password);
		
		//判断
		if(cuser!=null) {
		//不为空，把用户名放session中
			localSessionProvider.setAttribute(request, response, Constants.USER_NAME, cuser.getUsername());
			
			//跳转到点击登录的页面，登陆成功返回点击页面
			return "redicect:"+url;
		}else {
			model.addAttribute("error","用户名或密码不存在");
			
		}
		
		
	}else {
		model.addAttribute("error","账户或密码错误");
		}
		
		
		
		}else {
			model.addAttribute("error", "验证码输入错误");
		}
		}else {
			model.addAttribute("error", "验证码不存在");
		
	
		
		
		
	return "buyer/login";//返回登录页面
	
		}
	
}
	//验证码
	@RequestMapping(value="/login/getCode.html")
	public void getCode() {
		
		// 生成验证码
		@RequestMapping(value = "/login/getCode.html")
		public void getCode(HttpServletRequest request, HttpServletResponse response) {
			BufferedImage img = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
			Graphics g = img.getGraphics();

			Random r = new Random();

			Color c = new Color(200, 150, 255);

			g.setColor(c);

			g.fillRect(0, 0, 68, 22);

			StringBuffer sb = new StringBuffer();

			char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

			int index, len = ch.length;

			for (int i = 0; i < 4; i++) {

				index = r.nextInt(len);

				g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt

				(255)));

				g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));

				g.drawString("" + ch[index], (i * 15) + 3, 18);

				sb.append(ch[index]);

			}
			// 把上面生成的验证码放到Session域中
			localSessionProvider.setAttribute(request, response, Constants.USER_CODE,sb.toString());
			try {
				ImageIO.write(img, "JPG", response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		
		
	}
	
	
	
	
	
	
	
}