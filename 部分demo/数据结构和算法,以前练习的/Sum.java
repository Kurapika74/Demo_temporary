package recursion05;
//������ͣ����õݹ�
public class Sum {
//����һ���������������arr��Ϊ�û����,�û�ʹ�ã��û�����Ҫ֪���ݹ����
	 public static int sum(int[] arr){
		
		 return sum(arr, 0);//���������sum����,��0��n-1 
	 }

	 //��������ﵽ��������ͣ���ģ���𽥼�С
	 
   //���sum����  ����arr[l...n)����������������ֵĺ�,��߽�ĵ�Ϊl,
     private static int sum(int[]arr,int l) {
    	
         if(l == arr.length)//��߽�Ϊ��,������
              return 0;
          //����
    	  return arr[l] + sum(arr, l + 1);//����λ��Ԫ��ȡ������l+1��n-1�ĺ�
    
    	  //��l-n��l+1��n����С�������ģ
     
     }
 
    	 //�û�����֪���ݹ麯���Ĵ���
     public static void main(String[] args) {

         int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
         System.out.println(sum(nums));
     }
 

//�����һ���Ӻ������ӹ�����ΰ����������


}




