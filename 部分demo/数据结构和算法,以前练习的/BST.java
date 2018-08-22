package binarySearch.Tree05;
import java.util.LinkedList;//广度优先遍历需要用到队列和链表
//二叉搜索树
//由于Key需要能够进行比较，所以需要extends Comparable<Key>
public class BST <Key extends Comparable<Key>, Value>{
// 树中的节点为私有的类, 外界不需要了解二分搜索树节点的具体实现
	private class Node {
	private Key key;//为什么这样写???
    private Value value;//类、接口、数组、枚举和注释类型。
	private Node left,right;//指针引用类型的节点联系
	
	
	public Node() {
		this.key=key;
		this.value=value;
		left=right=null;
	}
	}
private Node root;//根节点  Root是一个指向node的指针
private int count;//树种的节点个数


//构造函数，默认构造一颗空的二分搜索树
public BST() {
	 root=null;
	 count=0;
} 
//返回二叉搜索树的节点个数	 
	public int size(){
		return count;
		
	}
	//返回二叉搜索树是否为空
	 public boolean isEmpty() {
		 return count==0;
}
	 // 向二分搜索树中插入一个新的(key, value)数据对
	 //出现递归
	 public void insert(Key key, Value value){
		//！！！注意这里 insert(root,key,value);多了一个root参数
		 root = insert(root, key, value);//调用了一个递归函数下面的
		//以root为根(整棵二叉查找树中，插入key-value
		//返回值root是插入节点后，返回的二叉搜索树的根
		}
	 
	 //查看二叉搜索树中 是否 存在建key
	 public boolean contain(Key key) {
		 return contain(root,key);
//递归调用contain函数，传入一个节点root，以root为根的树里是否有key
	
	 }
	 public Value search(Key key) {
		//调用递归函数
//在某个以root  (节点) 为根的二叉搜索树中来查找key的值
//下一步写被调用的递归函数	
		 return  search(root,key);
		 
		 
	 }
	 
	 //前序遍历
	 public void preOrder() {
		preOrder(root);//调用递归函数，传入节点，从根节点开始遍历
	 }
	 //中序遍历
	 public void inOrder(){
	        inOrder(root);//同上
	    }
	 //后序遍历
	 public void postOrder(){
		 postOrder(root);
	 }
	// 寻找二分搜索树的最小的键值
	    public Key minimum(){
	    	//确保二叉树不为空
	    	assert(count!=0);
//传入一个根节点，返回的是最小值对应的node
	    Node minNode=minimum(root);//minNode是对象？
	    	return minNode.key;
	    	
	    	
	    }
	 
	 //寻找二分搜索树最大的键值
	    public Key maximum() {
	    	assert(count!=0);
	    	//传入一个根节点，返回的是最大值对应的node
	    	Node maxNode=maximum(root);
	    	return maxNode.key;
	    }
	    
	    
	   //从二分搜索树中删除最小值的节点
	    public void removeMin() {
	    	if(root!=null) {
	    		root=removeMin(root);
	    	}
	    }
	    
	    // 从二分搜索树中删除最大值所在节点
	    public void removeMax(){
	    if(root!=null) {
	    	//只在不为空的情况下操作
	    	root=removeMax(root);
	    }
	    }
	 
	 
	 
	 //********************
	    //* 二分搜索树的辅助函数
	    //********************
	 // 向以node为根的二分搜索树中, 插入节点(key, value), 使用递归算法
	 // 返回插入新节点后的二分搜索树的根 
	 private Node insert(Node node, Key key, Value value){

	        if( node == null ){
	        count ++;//递归到底，访问节点为空，此时插入key-value数据对
	        return new Node(key,value);
	        }

	        if( key.compareTo(node.key) == 0 )
	            node.value = value;//相同覆盖
	        else if( key.compareTo(node.key) < 0 )
	            node.left = insert( node.left , key, value);
	        else    // key > node->key  左子树插入
	            node.right = insert( node.right, key, value);

	        return node;//返回节点本身
	    }
// 查看以node为根的二分搜索树中是否包含键值为key的节点, 使用递归算法
	 private boolean contain(Node node, Key key){

	        if( node == null )//当前访问节点为空
	            return false;
             //刚好相等
	        if( key.compareTo(node.key) == 0 )
	            return true;
	        //小于
	        else if( key.compareTo(node.key) < 0 )
	            return contain( node.left , key );
	        else // key > node->key  大于，返回又子树
	            return contain( node.right , key );
	    }
	
	// 在以node为根的二分搜索树中查找key所对应的value, 递归算法
	 // 若value不存在, 则返回NULL
	private Value search(Node node,Key key) {
		if(node==null) //node为空
		return null;
		if(key.compareTo(node.key)==0)
	return node.value;//等于当前node key，返回value值所对应地址？？？
	//被查找key小于根节点，在左子树中继续查找
	else if(key.compareTo(node.key)<0)
	return search(node.left,key);
	else// key > node->key
	return search(node.right,key);	
	}	
//这里是前序便利的递归函数
  //对以node为根的二叉搜索树进行前序遍历, 递归算法
		private void preOrder(Node node){
		if(node!=null) {
	//打印出node相对的Key的值
			//两次递归前做打印操作
		System.out.println(node.key);	
	//继续前序便利， node的左孩子
	preOrder(node.left);
	//以及node的右孩子
	preOrder(node.right);
	
		}
	}
	//中序遍历的递归函数
	private void inOrder(Node node) {
	if(node!=null) {
		inOrder(node.left);
		//打印左孩子，再右孩子，不过不做操作。
		//两次递归间做打印操作
		//动画上的三个点啦，对应前中后序
		System.out.println(node.key);
	}
		inOrder(node.right);
	
	}
	//后序遍历
	private void postOrder(Node node){
	if(node!=null) {
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.key);
	}
	}
	// 二分搜索树的层序遍历
	public void levelOrder(){
// 我们使用LinkedList来作为我们的队列	
//队列里存放的是<node>型这样的对象，称它为q
LinkedList<Node> q = new LinkedList<Node>();	
q.add(root);//入队我们的根结点，c++这里是push
//一个循环，在队列为空时才会结束
while(!q.isEmpty()) {
	//取出对手的元素 node*=q.front(); q.pop()出队
	Node node=q.remove();
    System.out.println(node.key);//把node元素键值打印出来
//看Node是否有左右孩子
    if(node.left!=null) {
    q.add(node.left);	
   if(node.right!=null) {
	   q.add(node.right);
   
    //之后循环继续进行，直到队列为空
   }}}
    
}
//返回以node为根的二分搜索树的最小键值所在的节点
private Node minimum(Node node){
    if( node.left == null )//如果左节点为空
        return node;

    return minimum(node.left);//或者继续向左边查找左孩子
}
	
//返回以node为根的二分搜索树的最大键值所在的节点
private Node maximum(Node node){
    if( node.right == null )
        return node;//node的右孩子是否为空

    return maximum(node.right);//递归？？继续查找
}
//删除掉以node为根的二分搜索树中的最小节点
// 返回删除节点后新的二分搜索树的根
	private Node removeMin(Node node) {
	if(node.left==null){
/*当前节点左孩子是否为空呢?
为空的话证明已经是最小了，接下来删除它	
要看一下它相对的右节点是否存在存在的话用它取代被删除的节点，
成为新的二分搜索树的根，作为新的左孩子。
*/
		Node rightNode=node.right;
		node.right=null;
		count--;
		return rightNode;
	}
		
		//if不成立继续查找
		//removeMin继续向左寻找，然后赋值给右节点
	 node.left = removeMin(node.left);
     return node;
 }
	 // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
	private Node removeMax(Node node) {
	if(node.right==null) {
		//当前如果已经是最大节点(没有右孩子的情况下
		//就删除它，然后取代
		Node leftNode=node.left;
		node.left=null;
		count--;
		return leftNode;
		
		
	}
		
	 node.right = removeMax(node.right);
		
		
		
		return node;
		
	}
	
	
	//注：测试用例还没写！！！！！！！！！
	 
	
	 public static void main(String[] args) {
	    }
	}