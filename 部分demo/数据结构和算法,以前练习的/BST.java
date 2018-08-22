package binarySearch.Tree05;
import java.util.LinkedList;//������ȱ�����Ҫ�õ����к�����
//����������
//����Key��Ҫ�ܹ����бȽϣ�������Ҫextends Comparable<Key>
public class BST <Key extends Comparable<Key>, Value>{
// ���еĽڵ�Ϊ˽�е���, ��粻��Ҫ�˽�����������ڵ�ľ���ʵ��
	private class Node {
	private Key key;//Ϊʲô����д???
    private Value value;//�ࡢ�ӿڡ����顢ö�ٺ�ע�����͡�
	private Node left,right;//ָ���������͵Ľڵ���ϵ
	
	
	public Node() {
		this.key=key;
		this.value=value;
		left=right=null;
	}
	}
private Node root;//���ڵ�  Root��һ��ָ��node��ָ��
private int count;//���ֵĽڵ����


//���캯����Ĭ�Ϲ���һ�ſյĶ���������
public BST() {
	 root=null;
	 count=0;
} 
//���ض����������Ľڵ����	 
	public int size(){
		return count;
		
	}
	//���ض����������Ƿ�Ϊ��
	 public boolean isEmpty() {
		 return count==0;
}
	 // ������������в���һ���µ�(key, value)���ݶ�
	 //���ֵݹ�
	 public void insert(Key key, Value value){
		//������ע������ insert(root,key,value);����һ��root����
		 root = insert(root, key, value);//������һ���ݹ麯�������
		//��rootΪ��(���ö���������У�����key-value
		//����ֵroot�ǲ���ڵ�󣬷��صĶ����������ĸ�
		}
	 
	 //�鿴������������ �Ƿ� ���ڽ�key
	 public boolean contain(Key key) {
		 return contain(root,key);
//�ݹ����contain����������һ���ڵ�root����rootΪ���������Ƿ���key
	
	 }
	 public Value search(Key key) {
		//���õݹ麯��
//��ĳ����root  (�ڵ�) Ϊ���Ķ�����������������key��ֵ
//��һ��д�����õĵݹ麯��	
		 return  search(root,key);
		 
		 
	 }
	 
	 //ǰ�����
	 public void preOrder() {
		preOrder(root);//���õݹ麯��������ڵ㣬�Ӹ��ڵ㿪ʼ����
	 }
	 //�������
	 public void inOrder(){
	        inOrder(root);//ͬ��
	    }
	 //�������
	 public void postOrder(){
		 postOrder(root);
	 }
	// Ѱ�Ҷ�������������С�ļ�ֵ
	    public Key minimum(){
	    	//ȷ����������Ϊ��
	    	assert(count!=0);
//����һ�����ڵ㣬���ص�����Сֵ��Ӧ��node
	    Node minNode=minimum(root);//minNode�Ƕ���
	    	return minNode.key;
	    	
	    	
	    }
	 
	 //Ѱ�Ҷ������������ļ�ֵ
	    public Key maximum() {
	    	assert(count!=0);
	    	//����һ�����ڵ㣬���ص������ֵ��Ӧ��node
	    	Node maxNode=maximum(root);
	    	return maxNode.key;
	    }
	    
	    
	   //�Ӷ�����������ɾ����Сֵ�Ľڵ�
	    public void removeMin() {
	    	if(root!=null) {
	    		root=removeMin(root);
	    	}
	    }
	    
	    // �Ӷ�����������ɾ�����ֵ���ڽڵ�
	    public void removeMax(){
	    if(root!=null) {
	    	//ֻ�ڲ�Ϊ�յ�����²���
	    	root=removeMax(root);
	    }
	    }
	 
	 
	 
	 //********************
	    //* �����������ĸ�������
	    //********************
	 // ����nodeΪ���Ķ�����������, ����ڵ�(key, value), ʹ�õݹ��㷨
	 // ���ز����½ڵ��Ķ����������ĸ� 
	 private Node insert(Node node, Key key, Value value){

	        if( node == null ){
	        count ++;//�ݹ鵽�ף����ʽڵ�Ϊ�գ���ʱ����key-value���ݶ�
	        return new Node(key,value);
	        }

	        if( key.compareTo(node.key) == 0 )
	            node.value = value;//��ͬ����
	        else if( key.compareTo(node.key) < 0 )
	            node.left = insert( node.left , key, value);
	        else    // key > node->key  ����������
	            node.right = insert( node.right, key, value);

	        return node;//���ؽڵ㱾��
	    }
// �鿴��nodeΪ���Ķ������������Ƿ������ֵΪkey�Ľڵ�, ʹ�õݹ��㷨
	 private boolean contain(Node node, Key key){

	        if( node == null )//��ǰ���ʽڵ�Ϊ��
	            return false;
             //�պ����
	        if( key.compareTo(node.key) == 0 )
	            return true;
	        //С��
	        else if( key.compareTo(node.key) < 0 )
	            return contain( node.left , key );
	        else // key > node->key  ���ڣ�����������
	            return contain( node.right , key );
	    }
	
	// ����nodeΪ���Ķ����������в���key����Ӧ��value, �ݹ��㷨
	 // ��value������, �򷵻�NULL
	private Value search(Node node,Key key) {
		if(node==null) //nodeΪ��
		return null;
		if(key.compareTo(node.key)==0)
	return node.value;//���ڵ�ǰnode key������valueֵ����Ӧ��ַ������
	//������keyС�ڸ��ڵ㣬���������м�������
	else if(key.compareTo(node.key)<0)
	return search(node.left,key);
	else// key > node->key
	return search(node.right,key);	
	}	
//������ǰ������ĵݹ麯��
  //����nodeΪ���Ķ�������������ǰ�����, �ݹ��㷨
		private void preOrder(Node node){
		if(node!=null) {
	//��ӡ��node��Ե�Key��ֵ
			//���εݹ�ǰ����ӡ����
		System.out.println(node.key);	
	//����ǰ������� node������
	preOrder(node.left);
	//�Լ�node���Һ���
	preOrder(node.right);
	
		}
	}
	//��������ĵݹ麯��
	private void inOrder(Node node) {
	if(node!=null) {
		inOrder(node.left);
		//��ӡ���ӣ����Һ��ӣ���������������
		//���εݹ������ӡ����
		//�����ϵ�������������Ӧǰ�к���
		System.out.println(node.key);
	}
		inOrder(node.right);
	
	}
	//�������
	private void postOrder(Node node){
	if(node!=null) {
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.key);
	}
	}
	// �����������Ĳ������
	public void levelOrder(){
// ����ʹ��LinkedList����Ϊ���ǵĶ���	
//�������ŵ���<node>�������Ķ��󣬳���Ϊq
LinkedList<Node> q = new LinkedList<Node>();	
q.add(root);//������ǵĸ���㣬c++������push
//һ��ѭ�����ڶ���Ϊ��ʱ�Ż����
while(!q.isEmpty()) {
	//ȡ�����ֵ�Ԫ�� node*=q.front(); q.pop()����
	Node node=q.remove();
    System.out.println(node.key);//��nodeԪ�ؼ�ֵ��ӡ����
//��Node�Ƿ������Һ���
    if(node.left!=null) {
    q.add(node.left);	
   if(node.right!=null) {
	   q.add(node.right);
   
    //֮��ѭ���������У�ֱ������Ϊ��
   }}}
    
}
//������nodeΪ���Ķ�������������С��ֵ���ڵĽڵ�
private Node minimum(Node node){
    if( node.left == null )//�����ڵ�Ϊ��
        return node;

    return minimum(node.left);//���߼�������߲�������
}
	
//������nodeΪ���Ķ���������������ֵ���ڵĽڵ�
private Node maximum(Node node){
    if( node.right == null )
        return node;//node���Һ����Ƿ�Ϊ��

    return maximum(node.right);//�ݹ飿����������
}
//ɾ������nodeΪ���Ķ����������е���С�ڵ�
// ����ɾ���ڵ���µĶ����������ĸ�
	private Node removeMin(Node node) {
	if(node.left==null){
/*��ǰ�ڵ������Ƿ�Ϊ����?
Ϊ�յĻ�֤���Ѿ�����С�ˣ�������ɾ����	
Ҫ��һ������Ե��ҽڵ��Ƿ���ڴ��ڵĻ�����ȡ����ɾ���Ľڵ㣬
��Ϊ�µĶ����������ĸ�����Ϊ�µ����ӡ�
*/
		Node rightNode=node.right;
		node.right=null;
		count--;
		return rightNode;
	}
		
		//if��������������
		//removeMin��������Ѱ�ң�Ȼ��ֵ���ҽڵ�
	 node.left = removeMin(node.left);
     return node;
 }
	 // ɾ������nodeΪ���Ķ����������е����ڵ�
    // ����ɾ���ڵ���µĶ����������ĸ�
	private Node removeMax(Node node) {
	if(node.right==null) {
		//��ǰ����Ѿ������ڵ�(û���Һ��ӵ������
		//��ɾ������Ȼ��ȡ��
		Node leftNode=node.left;
		node.left=null;
		count--;
		return leftNode;
		
		
	}
		
	 node.right = removeMax(node.right);
		
		
		
		return node;
		
	}
	
	
	//ע������������ûд������������������
	 
	
	 public static void main(String[] args) {
	    }
	}