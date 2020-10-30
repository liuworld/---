package demo;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 第一种方法：先排序顺序 + 高频次计数存储器map<k,v>
 * 	先排序顺序：
 * 	1）为了更好的遍历查找，排序采用快速排序算法：左右两边可同时范围比较互换+递归调用，此算法会速度快。
 * 	计数存储器map<k,v>：
 * 	1）map只存储目前出现高频的数字，即只存一个对象，会在遍历比较中多次替换高频数字直至最大高频出现。
 *  2）key记录高频数字，value记录频次，好处例如业务场景：商品名 - 最高销售量 ，来展示高频商品与高频数。
 *  3）map可以替换为单变量只记录高频数字，但考虑到最大高频数有相同的两个问题，而map方案可解决这问题。
 * @author 留
 *
 */

public class Test1 {
	static int[] array = {3, 2, 8, 2, 13, 8, 2, 0, 10, 8};

	//1.先排序数组顺序
	//采用快速排序算法，个人了解的高效排序算法是快速排序算法，但都是个人之前的认识。
	static void quickSort(int[] arrayParam) { //重载来实现单参数方法
		 quickSort(arrayParam, 0, arrayParam.length - 1); 
	}
	static void quickSort(int[] array,int left,int right) { //被重载的方法
	    if(array == null || left>=right || array.length<=1){
	        return;
	     } 
	    int mid = partition(array,left,right);
	    quickSort(array,left,mid);
	    quickSort(array,mid+1,right);
	}
	static int partition(int[] array, int left, int right){ //获取基标方法，即获取被比较的标志数
	    int temp = array[left];
	    while(right>left){
	        while(temp<=array[right] && left<right){
	            --right;  
	        }
	        if(left<right){
	            array[left] = array[right];  
	            ++left;   
	        }
	        while(temp>=array[left] && left <right){
	            ++left;
	        }
	        if(left<right){
	            array[right] = array[left];
	            --right;
	        }
	    }
	    array[left] = temp;
	    return left;
	}
	
	//2.对有序数据进行重复数字的计数，利用map<k,v>做计数存储器
	//计数过程：边遍历边比较计数器计数的大小，直至存储到最大计数为止
	public static void main(String[] args) {
		//获取排序后的数组
		quickSort(array);
		int [] ArraySort = array;
		int initNum = 1;  //当前计数
		int temp = 1;     //累加并缓存计数，缓存次数>当前计数，则更新初始计数
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		map.put(ArraySort[0], initNum);
		for (int i = 0; i < ArraySort.length; i++) {
			if(i < ArraySort.length-1) { //防溢出
				if(ArraySort[i]==ArraySort[i+1]) {
					++temp;
				}else {
					if(temp > initNum) {
						map.clear();
						map.put(ArraySort[i-1], temp);
						initNum = temp;
					}else if(temp == initNum) {
						if(i-1 != -1) { //防溢出
							map.put(ArraySort[i-1], temp);
						}
					}
					temp = 1;
				}
			}
		}
		//遍历map输出结果
		System.out.println("数组出现最高频率的数字和频次分别是：");
		for (Integer num: map.keySet()) {
			System.out.println(num+" - "+map.get(num));
		}
	}
	
	
}
