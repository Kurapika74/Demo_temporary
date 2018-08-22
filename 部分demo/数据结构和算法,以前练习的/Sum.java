package recursion05;
//数组求和，采用递归
public class Sum {
//创建一个函数，传入参数arr，为用户设计,用户使用，用户不需要知道递归过程
	 public static int sum(int[] arr){
		
		 return sum(arr, 0);//调用下面的sum方法,从0到n-1 
	 }

	 //数组从哪里到最后进行求和，规模在逐渐减小
	 
   //这个sum函数  计算arr[l...n)这个区间内所有数字的和,左边界的点为l,
     private static int sum(int[]arr,int l) {
    	
         if(l == arr.length)//左边界为空,空数组
              return 0;
          //或者
    	  return arr[l] + sum(arr, l + 1);//最左位置元素取出，从l+1到n-1的和
    
    	  //从l-n到l+1到n，缩小了问题规模
     
     }
 
    	 //用户不用知道递归函数的存在
     public static void main(String[] args) {

         int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
         System.out.println(sum(nums));
     }
 

//想象成一个子函数，子过程如何帮助解决问题


}




