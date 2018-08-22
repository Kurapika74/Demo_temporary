package stack.and.queues03;
//循环队列
public class LoopQueue<E> implements Queue<E> {
         private E[] data;//底层实现，有一个E类型的数组
//两个变量，front,tail(位置)表示指向队首所在的索引，和队尾的入队下一个新元素所指向的位置
         private int front,tail;
	     private int size;//队列中有多少个元素  成员变量size
//构造函数，用户知道自己的容器承载多少元素，传入容积
public LoopQueue(int capacity) {
	data=(E[])new Object[capacity + 1];//浪费一个，加一的位置new Object[capacity+1]
   //初始化，没有任何元素
	front=0;
	tail=0;
	size=0;

}

public LoopQueue(){
	this(10);//capacity10
}
//想知道数组可以承载多少个元素？
public int getCapacity(){
	return data.length-1;//循环队列其中1个是有意识被浪费掉的）(全部长度减1
}
@Override
public boolean isEmpty(){
    return front == tail;//当头和尾重叠 为空
}
@Override
public int getSize(){
	return size;
}
@Override
//入队
public void enqueue(E e) {
	//首先看队列是不是满的
	if((tail+1)%data.length==front)//最终尾巴加一又和头重叠了
	resize(getCapacity()*2);//扩容为现容量的二倍	
     data[tail]=e;  //可以存放一个元素
     tail=(tail+1)%data.length;  //越界？？？
         size++;
}
//扩容
private void resize(int newCapacity){

    E[] newData = (E[])new Object[newCapacity + 1];
   //把原来数组中元素放到newData中
    for(int i = 0 ; i < size ; i ++)
    	//data中第一个元素front,newdata[0],newdara[1],datafront+1,一一对应，具有一个front的偏移量
        newData[i] = data[(i + front) % data.length];
    //越界%data.length

    data = newData;
    front = 0;
    tail = size;
}
//出队
@Override
public E dequeue(){

    if(isEmpty())
        throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
          E ret=data[front];//将队首元素存在ret变量中
         //由于出队元素本质是引用
          data[front]=null;
front = (front + 1) % data.length;//维护一下front, 不是循环队列是front++
        size--;  
       //缩容
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
   
   return ret;

}
//看一眼队首元素
@Override
public E getFront(){
    if(isEmpty())
        throw new IllegalArgumentException("Queue is empty.");
      return data[front];
}
//覆盖object父类中的方法
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