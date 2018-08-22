package stack.and.queues03;
import java.util.Stack;
public class Solution {

	public boolean isValid(String s) {
		//��д��Ӧ�߼�
        Stack<Character> stack = new Stack<>();
		for(int i=0;i<s.length();i++){
			//ÿ�ο�һ��s�е�i���ַ���ʲô���ӵģ���Ϊchar c
			char c=s.charAt(i);
			
			//||��  �����������
			 if(c == '(' || c == '[' || c == '{')
				 //ִ����ջ����
				   stack.push(c);
			 else {
				 //������ǣ��������ŵĻ�����һ����ջ��Ԫ���Ƿ�ƥ��
				 //Ϊ�շ���false
				 if(stack.isEmpty()) 
				 return false;
				//������������ʱ
				 
				 //��ջ��Ԫ�ش���һ��������
				 char topChar = stack.pop();
				//���c����Բ�����ţ���ջ��������Բ������
				
	         if(c == ')' && topChar != '(')
	                    return false;
	         if(c == ']' && topChar != '[')
	                    return false;
	         if(c == '}' && topChar != '{')
	                    return false;
			
				
			 }
		}
		return stack.isEmpty();
		//ֻ��ջ��û��Ԫ��ʱ�����ߴ���Ԫ��û��ƥ����ʱ��
		//��isEmpty Ϊtureʱ������������
		
	}

public void main(String[]args) {
	  System.out.println((new Solution()).isValid("()[]{}"));
	  System.out.println((new Solution()).isValid("([)]"));
	
	  
	//(new Solution()).isValid("()[]{}");  ���ð�ʵ��д����
//���÷�������һ�� boolean ���͵ķ���ֵ��������system��ӡ�������
}
//���true��false
}
