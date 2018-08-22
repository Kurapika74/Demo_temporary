package stack.and.queues03;
import java.util.Stack;
public class Solution {

	public boolean isValid(String s) {
		//填写相应逻辑
        Stack<Character> stack = new Stack<>();
		for(int i=0;i<s.length();i++){
			//每次看一下s中第i个字符是什么样子的，存为char c
			char c=s.charAt(i);
			
			//||或  如果是左括号
			 if(c == '(' || c == '[' || c == '{')
				 //执行入栈操作
				   stack.push(c);
			 else {
				 //如果不是，是右括号的话，看一看与栈顶元素是否匹配
				 //为空返回false
				 if(stack.isEmpty()) 
				 return false;
				//当遍历右括号时
				 
				 //将栈顶元素存在一个变量中
				 char topChar = stack.pop();
				//如果c是右圆大括号，而栈顶不是左圆大括号
				
	         if(c == ')' && topChar != '(')
	                    return false;
	         if(c == ']' && topChar != '[')
	                    return false;
	         if(c == '}' && topChar != '{')
	                    return false;
			
				
			 }
		}
		return stack.isEmpty();
		//只有栈里没有元素时，或者存在元素没有匹配上时。
		//当isEmpty 为ture时，才真正结束
		
	}

public void main(String[]args) {
	  System.out.println((new Solution()).isValid("()[]{}"));
	  System.out.println((new Solution()).isValid("([)]"));
	
	  
	//(new Solution()).isValid("()[]{}");  不用把实例写出来
//调用方法返回一个 boolean 类型的返回值，可以用system打印输出出来
}
//输出true或false
}
