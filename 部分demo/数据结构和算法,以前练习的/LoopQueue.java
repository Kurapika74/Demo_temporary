package stack.and.queues03;
//ѭ������
public class LoopQueue<E> implements Queue<E> {
         private E[] data;//�ײ�ʵ�֣���һ��E���͵�����
//����������front,tail(λ��)��ʾָ��������ڵ��������Ͷ�β�������һ����Ԫ����ָ���λ��
         private int front,tail;
	     private int size;//�������ж��ٸ�Ԫ��  ��Ա����size
//���캯�����û�֪���Լ����������ض���Ԫ�أ������ݻ�
public LoopQueue(int capacity) {
	data=(E[])new Object[capacity + 1];//�˷�һ������һ��λ��new Object[capacity+1]
   //��ʼ����û���κ�Ԫ��
	front=0;
	tail=0;
	size=0;

}

public LoopQueue(){
	this(10);//capacity10
}
//��֪��������Գ��ض��ٸ�Ԫ�أ�
public int getCapacity(){
	return data.length-1;//ѭ����������1��������ʶ���˷ѵ��ģ�(ȫ�����ȼ�1
}
@Override
public boolean isEmpty(){
    return front == tail;//��ͷ��β�ص� Ϊ��
}
@Override
public int getSize(){
	return size;
}
@Override
//���
public void enqueue(E e) {
	//���ȿ������ǲ�������
	if((tail+1)%data.length==front)//����β�ͼ�һ�ֺ�ͷ�ص���
	resize(getCapacity()*2);//����Ϊ�������Ķ���	
     data[tail]=e;  //���Դ��һ��Ԫ��
     tail=(tail+1)%data.length;  //Խ�磿����
         size++;
}
//����
private void resize(int newCapacity){

    E[] newData = (E[])new Object[newCapacity + 1];
   //��ԭ��������Ԫ�طŵ�newData��
    for(int i = 0 ; i < size ; i ++)
    	//data�е�һ��Ԫ��front,newdata[0],newdara[1],datafront+1,һһ��Ӧ������һ��front��ƫ����
        newData[i] = data[(i + front) % data.length];
    //Խ��%data.length

    data = newData;
    front = 0;
    tail = size;
}
//����
@Override
public E dequeue(){

    if(isEmpty())
        throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
          E ret=data[front];//������Ԫ�ش���ret������
         //���ڳ���Ԫ�ر���������
          data[front]=null;
front = (front + 1) % data.length;//ά��һ��front, ����ѭ��������front++
        size--;  
       //����
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
   
   return ret;

}
//��һ�۶���Ԫ��
@Override
public E getFront(){
    if(isEmpty())
        throw new IllegalArgumentException("Queue is empty.");
      return data[front];
}
//����object�����еķ���
@Override
public String toString(){

    StringBuilder res = new StringBuilder();
    res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
    res.append("front [");
    for(int i = front ; i != tail ; i = (i + 1) % data.length){
        res.append(data[i]);
        if((i + 1) % data.length != tail)
            res.append(", ");
    }
    res.append("] tail");
    return res.toString();
}

public static void main(String[] args){

    LoopQueue<Integer> queue = new LoopQueue<>();
    for(int i = 0 ; i < 10 ; i ++){
        queue.enqueue(i);
        System.out.println(queue);

        if(i % 3 == 2){
            queue.dequeue();
            System.out.println(queue);
        }

    }
}}