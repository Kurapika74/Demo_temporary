package cn.two.sort;
import java.util.*;
public class SelectionSort1 {
//���ǵ��㷨����������κ�ʵ��
	//�����Ϊ��ʵ�ֶ�̬���ٸ����ӣ�A extends B����ô����A���κζ��󶼿��Կ�����B�Ķ���
	//ͬ��A implements B��B��һ���ӿڣ�A���κζ�������Կ�����B�Ķ���
	//����Ϊarr����
	public static void sort(Comparable[]arr) {
		//�����鳤�ȸ�ֵ������a
		int n=arr.length;
		for(int i=0;i<n;i++) {
		//Ѱ�ҡ�i-n)֮����Сֵ������
			int minIdex=i;//�������ҵ�����Сֵ��������ط�
		for(int j=i+1;j<n;j++) {
		//ʹ��comparable�����Ƚ�����comparable����Ĵ�С
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
	
	//������һ��main����
	public static void main(String[] args) {
		 // ����Integer
		Integer[] a = {10,9,8,7,6,5,4,3,2,1};
		SelectionSort1.sort(a);//�ڷ����Ĳ����й�һ��a��Ԫ��
		for(int i=0;i<a.length;i++) {
		System.out.print(a[i]);	
		System.out.print(' ');
		}
		//double����
		Double[] b = {4.4, 3.3, 2.2, 1.1};
        SelectionSort1.sort(b);
        for( int i = 0 ; i < b.length ; i ++ ){
            System.out.print(b[i]);
            System.out.print(' ');
        }
        System.out.println();

	
        //����String����
        String[]c= {"D","C","B","A"};
        SelectionSort1.sort(c);
        for(int i=0;i<c.length;i++) {
        	 System.out.print(c[i]);
             System.out.print(' ');
         
         System.out.println();
     
         //�����Զ����student��
         Student[]d=new Student[4];
  d[0]=new Student("D",90);
  d[1]=new Student("C",100);
  d[2]=new Student("B",95);
  d[3]=new Student("A",95);
  d[4]=new Student("E",99);
  
  
 SelectionSort1.sort(d);
 
 for(int i1 = 0 ; i1 < d.length ; i1 ++ ) {
	 
	 System.out.println(d[i1]);
	 
	 
	

     // ���������㷨��������
     int N = 20000;
     Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
     SelectionSort1.sort( arr );
     SortTestHelper.printArray(arr);

     return;
 }
	 
	 
 }
 
        }
        
        
		
	}
	
	
	
	
	
	
	
