package linked.List04;
//<E>֧�ַ���       �����ʵ�������Ǹ����ݽṹ
public class LinkedList<E> {
//�����������ɽڵ���ɵ�  ��ƽڵ���Ϊ�ڲ���  �û���֪�������ʵ�֣�ʹ�ü���
	private class Node{
		//��������Ա����
		public E e;//���Ԫ��
		public Node next;//һ��ָ��node������
	
		
		//���캯�����û����Դ��붫��
		public Node(E e,Node next){
			this.e=e;//����ǰ�ڵ��e��ֵ���û�������e���û�������e���ڵ�
			this.next=next;//����ǰ�ڵ��next��ֵ���û�������next
		}
		//�û�ֻ������һ��e
		public Node(E e) {
			this(e,null);//nextΪ��
		}
		//ʲô������
	public Node() {
		this(null,null);//e:null next:null
		//ֻ����nodeʱnextΪ��
		
	}
	//���Ǹ����з���
	 @Override
     public String toString(){
         return e.toString();
     }
 }

//����������Ա����
	private Node dummyHead;//���º�head���dummyhead,����ͷ�ڵ�
	int size; //����¼�������ж���Ԫ��
	
	//��Ӧ�Ĺ��캯��
	
	public LinkedList() {
		//��ʼ��Ϊ�գ�������һ��Ԫ��Ҳû
		dummyHead=new Node();//next=0    ԭhead-nulL;
		size=0;
	
	}
	//��ȡ������Ԫ�ظ���
	public int getSize() {
		return size;
	}
	//���������Ƿ�Ϊ��
	public boolean isEmpty(){
		return size==0;//����size�Ƿ�Ϊ0
	}
  //Ϊ�������Ԫ��   ������ͷ���Ԫ�غܷ���
	
	//������ͷ���Ԫ��e �������E e
	public void addFirst(E e) {
	//����һ���½ڵ�  Node���ڲ���
		
		add(0,e);
		//Node node=new Node(e);
		//node.next=head;//�½ڵ�ָ��head
		//head =node;
		//���£�head=node λ�ø���
	
	   //��һ��д��,�����ŵ�
		//head=new Node(e,head);
		
	size++;//ά��size
	
	}
	//index�Ǵ��㿪ʼ�ǵ�
	// �������index(0-based)λ������µ�Ԫ��e
    // �������в���һ�����õĲ�������ϰ�ã���ͨ��ѡ�����������
	public void add( int index,E e) {
		//�жϺϷ���
		if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
		//���indexΪ0��������ͷ��ӣ�ֱ�ӵ��÷���
		if(index == 0)
	            addFirst(e);
		else {
			Node prev=dummyHead;
			//dummyָ�����λ��0֮ǰ��Ԫ�ص�λ��
			//prev��λ�ô�head��ʼ,����ΪdummyHead��ԭindex-1
			//����Ԫ�أ�����Ŀ��indexλ�õ�ǰһ��Ԫ��
			for(int i=0;i<index;i++) {
		//prev���յ�index��ǰһ��Ԫ��λ��		
prev=prev.next;	//ÿ�ΰѵ�ǰ��Ľڵ����һ���ڵ�Ž�prev�����У�һֱ�ƶ�
			 Node node=new Node(e);
			 node.next=prev.next;
			 prev.next=node;
			
			//��д �����ŵ�
			 prev.next=new Node(e,prev.next);
			
			 size++;//ά��
			}
		}
	}
		 // ������ĩβ����µ�Ԫ��e  
		public void addLast(E e){
			add(size,e);
			//��size���������λ�����Ԫ��e
		}
	
		// �������ĵ�index(0-based)��λ�õ�Ԫ��
	    // �������в���һ�����õĲ�������ϰ�ã��� 
		//����Ԫ��E���ͱ���  Ѱ��indexλ��Ԫ�أ���0��ʼ����index-1λ�ã���dummyhead��ʼ
		 public E get(int index) {
			//�жϺϷ���  
			 if(index < 0 || index >= size)
		            throw new IllegalArgumentException("Get failed. Illegal index.");
		         
		 //����Ϳ��Ա�������������
		 //��index����Ϊ0��Ԫ�ؿ�ʼ
        Node cur=dummyHead.next;
        //ѭ�������Ĺ���ִ��index��
        for(int i = 0 ; i < index ; i ++)
        	cur=cur.next;//ÿ��cur=
        return cur.e;//������e������Ҫ�ҵ�

}
//�������ĵ�һ��Ԫ��  ����������ͬadd�����ں��ķ���
		 public E getFirst(){
			return get(0); 
		 }
//������һ��Ԫ��
		 public E getLast(){
			return get(size-1); 
		 }

		// �޸�����ĵ�index(0-based)��λ�õ�Ԫ��Ϊe
		// �������в���һ�����õĲ�������ϰ�ã���
		 //����Ϊ�µ�e
		 //ֻ������indexλ�õ�Ԫ�أ�����
		 public void set(int index,E e) {
			  if(index < 0 || index > size)
		            throw new IllegalArgumentException("Add failed. Illegal index."); 
		 //�Ϸ�����б������ҵ�indexλ�õ�Ԫ��
			  Node cur=dummyHead.next;//��0��ʼ����
		   for(int i=0;i<index;i++)
			   cur=cur.next;//ÿ��
		     cur.e=e;//�����λ�õ�Ԫ�ص����µ�Ԫ��
		 
		 }
		// �����������Ƿ���Ԫ��e
//û����������֪��Ҫ�ҵڼ���Ԫ�أ���Ҫ��ͷ��������б���,��֪��������whileѭ��
		 public boolean contains(E e){
		Node cur=dummyHead.next;
			while(cur!=null) {
	//��Ϊ��Ϊ��Ч�ڵ�,�жϴ˽ڵ��Ƿ����û������Ľڵ�һ��
			if(cur.e.equals(e))
			return true;
			//������һ���ڵ�,(�ƶ�)ֱ��curΪ�գ����������������
			cur=cur.next;
			}
			//�������껹δ�ҵ���booleanΪflase
			return false; 
			 
		
		 }
		 
		 //ɾ��
	 // ��������ɾ��index(0-based)λ�õ�Ԫ��, ����ɾ����Ԫ��
	// �������в���һ�����õĲ�������ϰ�ã���
		 
		 public E remove(int index){
	 //1��index���м�飬ȷ��index�ĺϷ���
			 if(index < 0 || index >= size)
		            throw new IllegalArgumentException("Remove failed. Index is illegal.");
		
			 //2�ҵ���ɾ����index֮ǰ�Ľڵ�
			 Node prev=dummyHead;//��ʼ��dummyhead��ʼ
			 for(int i=0;i<index;i++) 
				 prev=prev.next;//ÿ�Σ��ƶ�
				 //��ʱ���˴�ɾ���ڵ�֮ǰ�Ľڵ�
				 
				 //���ش�ɾ���Ľڵ�
				Node retNode=prev.next; 
				
				//����,����nextָ��
				prev.next=retNode.next;
				//���ڻ��գ�ָ��Ϊ��
				retNode.next=null;
				
				//ά��size
				size--;
				 
			return retNode.e;
			 
			 
			
			 
		 }
		 //��������ɾ����һ��Ԫ�أ�������ɾ���ķ���
		 public E removeFirst() {
			 return remove(0);
			 //�������remove����������index 0
		 }
		 
		// ��������ɾ�����һ��Ԫ��, ����ɾ����Ԫ��
		 public E removeLast(){
		        return remove(size - 1);
		    }

		 
		 //����object��ķ�����������������
		 @Override
		    public String toString(){
		        StringBuilder res = new StringBuilder();
			
		       
		        //whileѭ��
		        
		        
        //     Node cur = dummyHead.next;
       //		        while(cur != null){
      //		            res.append(cur + "->");
     //		            cur = cur.next;
		      
		       //forѭ��
		        for(Node cur = dummyHead.next ; cur != null ; cur = cur.next)
		            res.append(cur + "->");
		        res.append("NULL");
		        
		        
		        
		        
		        
		        return res.toString();


}
}
	
	

