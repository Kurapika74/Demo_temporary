package segmentTree09;

//�߶���
public class SegmentTree<E> {

	private E[]data;//˽�еĸ���
	
	private E[]tree;//��Ҳ���������ʽ��ʾ
	
	
	//���캯��,�û�������������ķ�Χ��һ������<e ���͵�
	 public SegmentTree(E[]arr){
		//��������newһ���µĿռ�
	 data=(E[])new Object[arr.length];//new��Ȼ��ǿתΪE���͵�
	 
	 tree=(E[])  new Object[4*arr.length];
	 //���������߶�����Ҫ�ı��Ŀռ�,��ʼ�����㿪ʼ
	 buildSegmentTree(0,0,data.length-1);//treeIndex0,0,r-data.length-1
	 //0,0,data.length-1
	
	 
	 //��treeindex��λ�ô�����ʾ����[l-r]���߶���,���ڵ�ondex�����Ҷ˵�l��r
	 private void  buildSegmentTree(int treeIndex,int l,int r){
		 
		 //���ǵݹ鵽�׵����,ֻ��һ��Ԫ�أ��洢��ϢΪԪ�ر���
		 if(l==r) {
			 tree[treeIndex]=data[l];
		 }
		 int leftTreeIndex=leftChild(treeIndex);
		 //�����������˴����÷���������ڵ���������
		 int rightTreeIndex=rightChild(treeIndex);
		 
		 int mid=(l+r)/2;//���ܲ������
		 //������������
		 //�������������
		// ---l-mid  mid+1-r��������-----
		 
		 
		 
	  
	 
	 }
	/* for(int i=0;i<arr.length;i++)
		 data[i]=arr[i];//ÿ�α�����ֵ �Ҹ���
	 }*/
	 //������Ԫ�ض���
	 public int getSize() {
		 return data.length;
	 }
	 
	 public E get(int index) {
		 //�Ϸ����ж�����
		 if(index<0||index>=data.length)
		 throw new IllegalArgumentException("index is illegal");
	 
		 return data[index];
	 }
	 
//��������1��������ȫ�������������ʾ�У�һ����������ʾ��Ԫ�ص����ӵĽڵ������
	 private int leftChild(int index) {
		 return 2*index+1;
	 }
	 //����һ���ڵ���֪����index,���������������ڵ������Ϳ���ͨ���������������
	 private int rightChild(int index) {
		 return 2*index+2;
	 }
	 
	 
	 
	 }


