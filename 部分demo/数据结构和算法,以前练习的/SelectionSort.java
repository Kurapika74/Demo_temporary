package selection.Sort.PX02;
//ѡ�������㷨
public class SelectionSort {
	//��̬�������ܱ�ʵ������Ҳ������ʵ������ 
	private SelectionSort(){}
//���������int�͵�arr���飬����n����������Ԫ�صĸ���
public static void sort(int[]arr) {
	
	int n=arr.length;
	
	//ѭ��      Ѱ��[i, n)ǰ�պ����������Сֵ  ������
	for(int i=0;i<n;i++) {
     int minIndex = i;
//�����洢���ǵ�ǰ���ҵ���Сֵ���洢������λ�ã���ʼ����i��λ��
//�ڶ���ѭ��
for(int j=i+1;j<n;j++) {
	//�Ƚϵ�ǰ������jλ��Ԫ���Ƿ�С�ڵ�ǰ��С��λ��Ԫ�ػ�ҪС
	if(arr[j]<arr[minIndex])
//С�ڵĻ�����λ��
		minIndex=j;
	
	//�Ѿ�����Сֵ����������minIndex�� i��λ�ã�jҲ��λ��
	//swap��iλ��Ԫ�� �� ��Сֵλ��Ԫ�ؽ���һ��λ�ý���
	swap(arr,i,minIndex);
	

}
}
	
	
}
private static void swap(int[] arr, int i, int j) {
	// TODO �Զ����ɵķ������
	 int t = arr[i];
     arr[i] = arr[j];
     arr[j] = t;
 }
//����
public static void main(String[]args) {
	int[]arr= {10,9,8,7,6,5,4,3,2,1};
SelectionSort.sort(arr);//���÷���
//���������һ�δ�ӡ
for(int i=0;i<arr.length;i++) {
	System.out.print(arr[i]);
	  System.out.print(' ');
}
System.out.println();
}
}









