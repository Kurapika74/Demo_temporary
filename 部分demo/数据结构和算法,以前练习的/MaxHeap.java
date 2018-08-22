package bobo.algo;
import java.util.*;
import java.lang.*;
// 在堆的有关操作中，需要比较堆中元素的大小，所以Item需要extends Comparable
public class MaxHeap<Item extends Comparable>{
//因为这个数组的Item还不确定是什么类型的，是几维数组都不确定，如果用[]就已经确定是一维了
//把属性封装
	private Item[] data;//指针类型，有多少数据？？？
    private int count;
	private int capacity;
 
    //构造函数，创建一个空堆，可容纳capacity个元素
    //初始化开辟空间，多少让用户决定，so,传入一个参数（容量）
   public MaxHeap(int capacity) {
	   data = (Item[])new Object[capacity+1];
	   //为什么加一呢？整个数组存储堆是从索引1开始的
	   count=0;
	   this.capacity = capacity;
   }
	   //返回堆中元素个数
	   public int size(){
	        return count;
	    }

   //返回一个布尔值，表示堆中是否为空
	   public boolean isEmpty() {
		
		   return count==0;
		   
	   }
	  //向最大堆中添加一个新元素 item 是item类型的
	  public void insert( Item item ) {
	assert count+1<=capacity;//处理越界！！！看视频，当空间不足时如何处理？
	 count++;
/*注意：这里的数组索引从1开始，
 * 有count个元素从1一直存到count这些索引的位置
 * 新元素放到count+1的位置
 * count值多了一个元素，count值可以++
 * 但可能破坏最大堆的定义，所以用方法shiftup来尝试
 * 把count位置的元素向上移动。
*/	
	 shiftUp(count);
		  
	  }
	 
//私有实现，不需要用户调用，而是通过extractMax函数来调用操作  
//将索引为k位置的元素向下移动？？
private	void  shiftDown(int k) {
	//当前k节点有孩子，完全二叉树下有左孩子，就可以判断有孩子
while(2*k<=count) {
	//左结点j???
	//比较左右两个孩子，谁大和谁换
	int j=2*k;// 在此轮循环中,data[k]和data[j]交换位置
	//判断有没有右孩子，如果有比较谁大交换位置
	if(j+1<=count&& data[j+1].compareTo(data[j]) > 0 ){
	j++;
    // data[j] 是 data[2*k]和data[2*k+1]中的最大值
	//如果父节点大于等于它的两个孩子，不需要交换，否则操作交换。
	if( data[k].compareTo(data[j]) >= 0 ) break;
	swap(k,j);
	k=j;//赋值k到j
	}
}
	
	
	
	
}
	  
	  
	  
	  
	  
	  
//从最大堆中取出堆顶元素，即堆中所存储的最大数据 
public Item  extractMax() {
assert count>0;//确认不为空
Item ret=data[1];//取出索引位置为1的值，把它赋值给ret
//还需要维护最大堆性质，将data[1]位置元素，与最后一个位置的元素交换
swap(1,count);
count--;//将count位置元素放第一个位置，然后不考虑count这个位置了！！！！
	
shiftDown(1);//调用这个函数，将第一个位置元素向下移

	
	 return ret ;
	
}
	  
	  
	  
	  
	  
	  
	  
//获取最大堆中堆顶元素, 返回一个Item类型的对象，输入值没有
	public Item getMax() {
	//首先保证堆不等于空，即assert(count)>0，
	//然后取出索引为1的元素	
	assert(count>0);
	return data[1];
		
		
	}
	  
	  
	  
	  
	  
	//交换堆中索引为i和j的两个元素
	  private void swap(int i,int j) {
		  Item t=data[j];
		  data[i]=data[j];
		  data[j]=t;
	  }
	  
	  
	  
	  
	  
	//核心函数 sift up   参数为索引k如12345位置元素，尝试向上移动
	  private void shiftUp(int k) {
		  while( k > 1 && data[k/2].compareTo(data[k]) < 0 ){
	   swap(k,k/2);//交换两个位置元素
	   k/=2;
   }
	  }
	   
	   

/*测试Maxheap  第二节视频
	   public static void main(String[] args) {

	        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(100);
	        System.out.println(maxHeap.size());
	    }*/
	
	//测试  04课	
	public static void main(String[]args, int[] arr) {
	MaxHeap<Integer>maxHeap=new MaxHeap<Integer>(100);
	int N=100;//堆中元素个数
	int M=100;//队中取值范围[0,M]
	for(int i=0;i<N;i++) {
		//调用insert方法  100以内的随机数100个
	maxHeap.insert(new Integer((int)(Math.random()*M)));	
	
	Integer[] arr = new Integer[N];
	// 将maxheap中的数据逐渐使用extractMax取出来
	// 取出来的顺序应该是按照从大到小的顺序取出来的
	for(int i = 0 ; i < N ; i ++ ){
		 arr[i] = maxHeap.extractMax();
         System.out.print(arr[i] + " ");
     }
	 System.out.println();
	 // 确保arr数组是从大到小排列的
     for(int i = 1 ; i < N ; i ++ )
         assert arr[i-1] >= arr[i];
		
	}




}}




