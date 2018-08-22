package com.qita.freemaker.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.qita.core.pojo.user.User;

import freemarker.template.Configuration;
import freemarker.template.Template;

//测试


public class FreeMakerTest {
	//框架入门
	
	
	@Test
	public void demo1() throws Exception {
	  //1创建configurtion对象
	Configuration configuration=new Configuration();
     //2设置模板存放目录,此处是属性中的绝对路径find/replace快捷键
	String templateUrl="D:/21/parent/qita/src/main/webapp/WEB-INF/template/";
     //3加载模板目录和设置读取模板的编码方式
	configuration.setDirectoryForTemplateLoading(new File(templateUrl));
	configuration.setDefaultEncoding("utf-8");
	//4获取模板对象
	Template template = configuration.getTemplate("temp.html");
	//5创建数据,如一个map集合
	Map rootMap=new HashMap<>();
	rootMap.put("name","hello freemaker");
	//6创建输出文件,一个输出流对象
	Writer out=new FileWriter("D:/freemaker/demo1.html");
	
	//7通过模板输出数据,左接受数据
	template.process(rootMap, out);
	//8关流
		out.close();
		
	}
	
	//2输出对象
	@Test
	public void demo2() throws Exception {
	//1创建configurtion对象
	Configuration configuration=new Configuration();
	//加载模板目录和设置读取模板的编码方式
	configuration.setDirectoryForTemplateLoading(new File("D:/21/parent/qita/src/main/webapp/WEB-INF/template/";));
	configuration.setDefaultEncoding("utf-8");	
	//获取模板对象
      Template template = configuration.getTemplate("temp.html");
     //输出对象
      User user=new User();
      user.setPassword("123456");
	  user.setUsername("张三");
	  Map root=new HashMap();
	  root.put("user", user);
	  //输出流对象
	  Writer out=new FileWriter("D:/freemaker/demo2.html");
	  template.process(root, out);
	  //关闭
	  out.close();
	  
	  /*取值姓名：${user.username} 密码: ${user.password}*/
	  
	}

	//输出list 失败
	
	@Test
	public void demo3() throws Exception {
	
 Configuration configuration=new  Configuration ();
 configuration.setDirectoryForTemplateLoading(new File("D:/21/parent/qita/src/main/webapp/WEB-INF/template/";));
 configuration.setDefaultEncoding("utf-8");
//获取模板对象
 Template template = configuration.getTemplate("temp.html");
	
 List users=new ArrayList();
 User user=new User();
 user.setPassword("123456");
 user.setUsername("张三");
 users.add(user);
 
 Map root=new HashMap();
 root.put("users", users);
 //输出流对象
 Writer out=new FileWriter("D:/freemaker/demo3.html");
 template.process(root, out);
 //关闭
 out.close();		
	}
	
	
	
	
}
