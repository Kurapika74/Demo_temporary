package linked.List04;
//<E>支持泛型       这个类实现链表那个数据结构
public class LinkedList<E> {
//由于链表是由节点组成的  设计节点类为内部类  用户不知道其具体实现，使用即可
	private class Node{
		//有两个成员变量
		public E e;//存放元素
		public Node next;//一个指向node的引用
	
		
		//构造函数，用户可以传入东西
		public Node(E e,Node next){
			this.e=e;//将当前节点的e赋值成用户传来的e（用户传来的e给节点
			this.next=next;//将当前节点的next赋值成用户传来的next
		}
		//用户只传来了一个e
		public Node(E e) {
			this(e,null);//next为空
		}
		//什么都不传
	public Node() {
		this(null,null);//e:null next:null
		//只传入node时next为空
		
	}
	//覆盖父类中方法
	 @Override
     public String toString(){
         return e.toString();
     }
 }

//声明基本成员变量
	private Node dummyHead;//更新后head变成dummyhead,虚拟头节点
	int size; //来记录链表中有多少元素
	
	//对应的构造函数
	
	public LinkedList() {
		//初始化为空，链表里一个元素也没
		dummyHead=new Node();//next=0    原head-nulL;
		size=0;
	
	}
	//获取链表中元素个数
	public int getSize() {
		return size;
	}
	//返回链表是否为空
	public boolean isEmpty(){
		return size==0;//返回size是否为0
	}
  //为链表添加元素   在链表头添加元素很方便
	
	//在链表头添加元素e 传入参数E e
	public void addFirst(E e) {
	//创建一个新节点  Node是内部类
		
		add(0,e);
		//Node node=new Node(e);
		//node.next=head;//新节点指向head
		//head =node;
		//更新，head=node 位置更新
	
	   //另一种写法,更优雅的
		//head=new Node(e,head);
		
	size++;//维护size
	
	}
	//index是从零开始记的
	// 在链表的index(0-based)位置添加新的元素e
    // 在链表中不是一个常用的操作，练习用：）通常选链表而不索引
	public void add( int index,E e) {
		//判断合法性
		if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
		//如果index为0，在链表头添加，直接调用方法
		if(index == 0)
	            addFirst(e);
		else {
			Node prev=dummyHead;
			//dummy指向从零位置0之前的元素的位置
			//prev的位置从head开始,更改为dummyHead，原index-1
			//遍历元素，查找目标index位置的前一个元素
			for(int i=0;i<index;i++) {
		//prev最终到index的前一个元素位置		
prev=prev.next;	//每次把当前存的节点的下一个节点放进prev变量中，一直移动
			 Node node=new Node(e);
			 node.next=prev.next;
			 prev.next=node;
			
			//简写 更优雅的
			 prev.next=new Node(e,prev.next);
			
			 size++;//维护
			}
		}
	}
		 // 在链表末尾添加新的元素e  
		public void addLast(E e){
			add(size,e);
			//在size这个索引的位置添加元素e
		}
	
		// 获得链表的第index(0-based)个位置的元素
	    // 在链表中不是一个常用的操作，练习用：） 
		//返回元素E类型变量  寻找index位置元素，从0开始，而index-1位置，从dummyhead开始
		 public E get(int index) {
			//判断合法性  
			 if(index < 0 || index >= size)
		            throw new IllegalArgumentException("Get failed. Illegal index.");
		         
		 //否则就可以遍历整个链表了
		 //从index索引为0的元素开始
        Node cur=dummyHead.next;
        //循环遍历的过程执行index次
        for(int i = 0 ; i < index ; i ++)
        	cur=cur.next;//每次cur=
        return cur.e;//里面存的e是我们要找的

}
//获得链表的第一个元素  这两个方法同add依托于核心方法
		 public E getFirst(){
			return get(0); 
		 }
//获得最后一个元素
		 public E getLast(){
			return get(size-1); 
		 }

		// 修改链表的第index(0-based)个位置的元素为e
		// 在链表中不是一个常用的操作，练习用：）
		 //更新为新的e
		 //只遍历到index位置的元素！！！
		 public void set(int index,E e) {
			  if(index < 0 || index > size)
		            throw new IllegalArgumentException("Add failed. Illegal index."); 
		 //合法后进行遍历，找到index位置的元素
			  Node cur=dummyHead.next;//从0开始遍历
		   for(int i=0;i<index;i++)
			   cur=cur.next;//每次
		     cur.e=e;//让这个位置的元素等于新的元素
		 
		 }
		// 查找链表中是否有元素e
//没有索引，不知道要找第几个元素，需要从头对链表进行遍历,不知道次数用while循环
		 public boolean contains(E e){
		Node cur=dummyHead.next;
			while(cur!=null) {
	//不为空为有效节点,判断此节点是否与用户传来的节点一致
			if(cur.e.equals(e))
			return true;
			//否则看下一个节点,(移动)直到cur为空，把整个链表遍历完
			cur=cur.next;
			}
			//若遍历完还未找到，boolean为flase
			return false; 
			 
		
		 }
		 
		 //删除
	 // 从链表中删除index(0-based)位置的元素, 返回删除的元素
	// 在链表中不是一个常用的操作，练习用：）
		 
		 public E remove(int index){
	 //1对index进行检查，确保index的合法性
			 if(index < 0 || index >= size)
		            throw new IllegalArgumentException("Remove failed. Index is illegal.");
		
			 //2找到待删除的index之前的节点
			 Node prev=dummyHead;//初始从dummyhead开始
			 for(int i=0;i<index;i++) 
				 prev=prev.next;//每次（移动
				 //此时存了待删除节点之前的节点
				 
				 //返回待删除的节点
				Node retNode=prev.next; 
				
				//跳过,更改next指向
				prev.next=retNode.next;
				//便于回收，指向为空
				retNode.next=null;
				
				//维护size
				size--;
				 
			return retNode.e;
			 
			 
			
			 
		 }
		 //从链表中删除第一个元素，并返回删除的方法
		 public E removeFirst() {
			 return remove(0);
			 //这里调用remove方法，传入index 0
		 }
		 
		// 从链表中删除最后一个元素, 返回删除的元素
		 public E removeLast(){
		        return remove(size - 1);
		    }

		 
		 //覆盖object类的方法，遍历整个数组
		 @Override
		    public String toString(){
		        StringBuilder res = new StringBuilder();
			
		       
		        //while循环
		        
		        
        //     Node cur = dummyHead.next;
       //		        while(cur != null){
      //		            res.append(cur + "->");
     //		            cur = cur.next;
		      
		       //for循环
		        for(Node cur = dummyHead.next ; cur != null ; cur = cur.next)
		            res.append(cur + "->");
		        res.append("NULL");
		        
		        
		        
		        
		        
		        return res.toString();


}
}
	
	

