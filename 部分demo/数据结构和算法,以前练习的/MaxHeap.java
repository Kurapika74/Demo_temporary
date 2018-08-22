package bobo.algo;
import java.util.*;
import java.lang.*;
// �ڶѵ��йز����У���Ҫ�Ƚ϶���Ԫ�صĴ�С������Item��Ҫextends Comparable
public class MaxHeap<Item extends Comparable>{
//��Ϊ��������Item����ȷ����ʲô���͵ģ��Ǽ�ά���鶼��ȷ���������[]���Ѿ�ȷ����һά��
//�����Է�װ
	private Item[] data;//ָ�����ͣ��ж������ݣ�����
    private int count;
	private int capacity;
 
    //���캯��������һ���նѣ�������capacity��Ԫ��
    //��ʼ�����ٿռ䣬�������û�������so,����һ��������������
   public MaxHeap(int capacity) {
	   data = (Item[])new Object[capacity+1];
	   //Ϊʲô��һ�أ���������洢���Ǵ�����1��ʼ��
	   count=0;
	   this.capacity = capacity;
   }
	   //���ض���Ԫ�ظ���
	   public int size(){
	        return count;
	    }

   //����һ������ֵ����ʾ�����Ƿ�Ϊ��
	   public boolean isEmpty() {
		
		   return count==0;
		   
	   }
	  //�����������һ����Ԫ�� item ��item���͵�
	  public void insert( Item item ) {
	assert count+1<=capacity;//����Խ�磡��������Ƶ�����ռ䲻��ʱ��δ���
	 count++;
/*ע�⣺���������������1��ʼ��
 * ��count��Ԫ�ش�1һֱ�浽count��Щ������λ��
 * ��Ԫ�طŵ�count+1��λ��
 * countֵ����һ��Ԫ�أ�countֵ����++
 * �������ƻ����ѵĶ��壬�����÷���shiftup������
 * ��countλ�õ�Ԫ�������ƶ���
*/	
	 shiftUp(count);
		  
	  }
	 
//˽��ʵ�֣�����Ҫ�û����ã�����ͨ��extractMax���������ò���  
//������Ϊkλ�õ�Ԫ�������ƶ�����
private	void  shiftDown(int k) {
	//��ǰk�ڵ��к��ӣ���ȫ�������������ӣ��Ϳ����ж��к���
while(2*k<=count) {
	//����j???
	//�Ƚ������������ӣ�˭���˭��
	int j=2*k;// �ڴ���ѭ����,data[k]��data[j]����λ��
	//�ж���û���Һ��ӣ�����бȽ�˭�󽻻�λ��
	if(j+1<=count&& data[j+1].compareTo(data[j]) > 0 ){
	j++;
    // data[j] �� data[2*k]��data[2*k+1]�е����ֵ
	//������ڵ���ڵ��������������ӣ�����Ҫ�������������������
	if( data[k].compareTo(data[j]) >= 0 ) break;
	swap(k,j);
	k=j;//��ֵk��j
	}
}
	
	
	
	
}
	  
	  
	  
	  
	  
	  
//��������ȡ���Ѷ�Ԫ�أ����������洢��������� 
public Item  extractMax() {
assert count>0;//ȷ�ϲ�Ϊ��
Item ret=data[1];//ȡ������λ��Ϊ1��ֵ��������ֵ��ret
//����Ҫά���������ʣ���data[1]λ��Ԫ�أ������һ��λ�õ�Ԫ�ؽ���
swap(1,count);
count--;//��countλ��Ԫ�طŵ�һ��λ�ã�Ȼ�󲻿���count���λ���ˣ�������
	
shiftDown(1);//�����������������һ��λ��Ԫ��������

	
	 return ret ;
	
}
	  
	  
	  
	  
	  
	  
	  
//��ȡ�����жѶ�Ԫ��, ����һ��Item���͵Ķ�������ֵû��
	public Item getMax() {
	//���ȱ�֤�Ѳ����ڿգ���assert(count)>0��
	//Ȼ��ȡ������Ϊ1��Ԫ��	
	assert(count>0);
	return data[1];
		
		
	}
	  
	  
	  
	  
	  
	//������������Ϊi��j������Ԫ��
	  private void swap(int i,int j) {
		  Item t=data[j];
		  data[i]=data[j];
		  data[j]=t;
	  }
	  
	  
	  
	  
	  
	//���ĺ��� sift up   ����Ϊ����k��12345λ��Ԫ�أ����������ƶ�
	  private void shiftUp(int k) {
		  while( k > 1 && data[k/2].compareTo(data[k]) < 0 ){
	   swap(k,k/2);//��������λ��Ԫ��
	   k/=2;
   }
	  }
	   
	   

/*����Maxheap  �ڶ�����Ƶ
	   public static void main(String[] args) {

	        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(100);
	        System.out.println(maxHeap.size());
	    }*/
	
	//����  04��	
	public static void main(String[]args, int[] arr) {
	MaxHeap<Integer>maxHeap=new MaxHeap<Integer>(100);
	int N=100;//����Ԫ�ظ���
	int M=100;//����ȡֵ��Χ[0,M]
	for(int i=0;i<N;i++) {
		//����insert����  100���ڵ������100��
	maxHeap.insert(new Integer((int)(Math.random()*M)));	
	
	Integer[] arr = new Integer[N];
	// ��maxheap�е�������ʹ��extractMaxȡ����
	// ȡ������˳��Ӧ���ǰ��մӴ�С��˳��ȡ������
	for(int i = 0 ; i < N ; i ++ ){
		 arr[i] = maxHeap.extractMax();
         System.out.print(arr[i] + " ");
     }
	 System.out.println();
	 // ȷ��arr�����ǴӴ�С���е�
     for(int i = 1 ; i < N ; i ++ )
         assert arr[i-1] >= arr[i];
		
	}




}}




