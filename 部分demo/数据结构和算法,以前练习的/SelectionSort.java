package selection.Sort.PX02;
//选择排序算法
public class SelectionSort {
	//静态方法不能被实例化，也不允许被实例化！ 
	private SelectionSort(){}
//传入参数是int型的arr数组，还有n描述数组中元素的个数
public static void sort(int[]arr) {
	
	int n=arr.length;
	
	//循环      寻找[i, n)前闭后开区间里的最小值  的索引
	for(int i=0;i<n;i++) {
     int minIndex = i;
//它所存储的是当前所找到最小值所存储的索引位置，初始化在i的位置
//第二次循环
for(int j=i+1;j<n;j++) {
	//比较当前所看到j位置元素是否小于当前最小的位置元素还要小
	if(arr[j]<arr[minIndex])
//小于的话交换位置
		minIndex=j;
	
	//已经将最小值索引存在了minIndex中 i是位置，j也是位置
	//swap将i位置元素 和 最小值位置元素进行一次位置交换
	swap(arr,i,minIndex);
	

}
}
	
	
}
private static void swap(int[] arr, int i, int j) {
	// TODO 自动生成的方法存根
	 int t = arr[i];
     arr[i] = arr[j];
     arr[j] = t;
 }
//测试
public static void main(String[]args) {
	int[]arr= {10,9,8,7,6,5,4,3,2,1};
SelectionSort.sort(arr);//调用方法
//将结果进行一次打印
for(int i=0;i<arr.length;i++) {
	System.out.print(arr[i]);
	  System.out.print(' ');
}
System.out.println();
}
}









