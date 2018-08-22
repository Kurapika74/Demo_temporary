package segmentTree09;

//线段树
public class SegmentTree<E> {

	private E[]data;//私有的副本
	
	private E[]tree;//树也用数组的形式表示
	
	
	//构造函数,用户传进整个区间的范围，一个数组<e 类型的
	 public SegmentTree(E[]arr){
		//传进来后new一个新的空间
	 data=(E[])new Object[arr.length];//new出然后强转为E类型的
	 
	 tree=(E[])  new Object[4*arr.length];
	 //把数组变成线段树需要四倍的空间,初始化从零开始
	 buildSegmentTree(0,0,data.length-1);//treeIndex0,0,r-data.length-1
	 //0,0,data.length-1
	
	 
	 //在treeindex的位置创建表示区间[l-r]的线段树,根节点ondex，左右端点l和r
	 private void  buildSegmentTree(int treeIndex,int l,int r){
		 
		 //考虑递归到底的情况,只有一个元素，存储信息为元素本身
		 if(l==r) {
			 tree[treeIndex]=data[l];
		 }
		 int leftTreeIndex=leftChild(treeIndex);
		 //左孩子索引：此处调用方法传入根节点参数解决，
		 int rightTreeIndex=rightChild(treeIndex);
		 
		 int mid=(l+r)/2;//可能产生溢出
		 //创建左右子树
		 //分离出两个区间
		// ---l-mid  mid+1-r两个区间-----
		 
		 
		 
	  
	 
	 }
	/* for(int i=0;i<arr.length;i++)
		 data[i]=arr[i];//每次遍历赋值 右给左
	 }*/
	 //区间内元素多少
	 public int getSize() {
		 return data.length;
	 }
	 
	 public E get(int index) {
		 //合法性判断索引
		 if(index<0||index>=data.length)
		 throw new IllegalArgumentException("index is illegal");
	 
		 return data[index];
	 }
	 
//辅助函数1，返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子的节点的索引
	 private int leftChild(int index) {
		 return 2*index+1;
	 }
	 //对于一个节点已知它的index,左右两个孩子所在的索引就可以通过函数计算出来。
	 private int rightChild(int index) {
		 return 2*index+2;
	 }
	 
	 
	 
	 }


