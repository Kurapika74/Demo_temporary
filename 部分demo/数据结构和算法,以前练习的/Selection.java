package cn.two.sort;
import java.util.*;
public class SelectionSort1 {
//我们的算法不允许产生任何实例
	//这个是为了实现多态，举个例子，A extends B，那么子类A的任何对象都可以看作是B的对象；
	//同理，A implements B，B是一个接口，A的任何对象均可以看作是B的对象。
	//类型为arr？？
	public static void sort(Comparable[]arr) {
		//将数组长度赋值给变量a
		int n=arr.length;
		for(int i=0;i<n;i++) {
		//寻找【i-n)之间最小值的索引
			int minIdex=i;//现在能找到的最小值存于这个地方
		for(int j=i+1;j<n;j++) {
		//使用comparable方法比较两个comparable对象的大小
			if(arr[j].compareTo(arr[minIdex])<0)
			minIdex=j;
			swap(arr,i,minIdex);
		}
	}

}

	private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
	
	//测试类一个main方法
	public static void main(String[] args) {
		 // 测试Integer
		Integer[] a = {10,9,8,7,6,5,4,3,2,1};
		SelectionSort1.sort(a);//在方法的操作中过一遍a内元素
		for(int i=0;i<a.length;i++) {
		System.out.print(a[i]);	
		System.out.print(' ');
		}
		//double测试
		Double[] b = {4.4, 3.3, 2.2, 1.1};
        SelectionSort1.sort(b);
        for( int i = 0 ; i < b.length ; i ++ ){
            System.out.print(b[i]);
            System.out.print(' ');
        }
        System.out.println();

	
        //测试String类型
        String[]c= {"D","C","B","A"};
        SelectionSort1.sort(c);
        for(int i=0;i<c.length;i++) {
        	 System.out.print(c[i]);
             System.out.print(' ');
         
         System.out.println();
     
         //测试自定义的student类
         Student[]d=new Student[4];
  d[0]=new Student("D",90);
  d[1]=new Student("C",100);
  d[2]=new Student("B",95);
  d[3]=new Student("A",95);
  d[4]=new Student("E",99);
  
  
 SelectionSort1.sort(d);
 
 for(int i1 = 0 ; i1 < d.length ; i1 ++ ) {
	 
	 System.out.println(d[i1]);
	 
	 
	

     // 测试排序算法辅助函数
     int N = 20000;
     Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
     SelectionSort1.sort( arr );
     SortTestHelper.printArray(arr);

     return;
 }
	 
	 
 }
 
        }
        
        
		
	}
	
	
	
	
	
	
	
