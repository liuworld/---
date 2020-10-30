package demo;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 第三种方法：数组存储转Map存储 + map.key重复累加频次
 * 	数组存储转Map存储：
 * 	1）再优的算法也打破不了过大耗时，所以转换为个人好处理高频的数据结构，算法基于数据结构，这也是有那么多结构出现的原因。
 *  2）大幅度减少了排序的多次排序比较互换带来开销，map容器存储也个人少走了算法逻辑。
 * 	map.key重复累加频次：
 * 	1）key重复，则累加value++；key不重复则存入。这方法类似hashMap的"拉链法"解决hash冲突，key相同则盖，不同则加入链表。
 *  2）最后将map遍历取出数据，判断取出最高频次的数字即可。
 *  
 *    前面题及了单变量存储的问题，所以做了优化前后方法的比较
 * 优化前
 *    1.单变量存储：只能存储一个，可能相同的最高频的两个存在，其中一个被覆盖丢失，无法灵活处理多个出现。
 *    优化后
 *    2.多变量存储: map<k,v>可以多个存在可能，不丢失任何一个最高频的数。
 *   
 * @author 留
 *
 */

public class Test3 {
	static int[] array = {3, 2, 8, 2, 13, 8, 2, 1, 10, 8};
	static Map<Integer,Integer> map = new HashMap<Integer,Integer>();
	static int initCount = 1;  
	static int temp = 0;
	static int maxCountOfnum = 0;
	//单变量存储方案：
	//int temp1 = 0;
	//int maxCountOfnum1 = 0;
	//多变量存储方案：
	static Map<Integer,Integer> tempMap = new HashMap<Integer,Integer>();
	static Integer tempMapOfmaxvalue;

	//步骤：
	//1.遍历数据，转为map<k,v>储存
	//2.用数组保存不重复数字，方便判断map.key是否重复，如果是则次数设为递增1，并覆盖原来的key
	//3.遍历一遍map边取出来边比较value的大小，直至选出最大value
	
	public static void findMaxCountOfNum() {
		map.put(array[0], initCount);
		for (int i = 0; i < array.length; i++) {
			if (i-1>-1) { //防止溢出
				if (map.containsKey(array[i])) {
					initCount = map.get(array[i])+1;
					map.put(array[i], initCount);
					initCount = 1;
				}else {
					map.put(array[i], initCount);
				}
			}
		}
		//优化前
//		resultOutput(); 
		//优化后
		resultOutput2(); 
	}
	
	//优化前方法
	public static void resultOutput() {
		//遍历map比较数字频次的高低
		for (Integer num: map.keySet()) {
				int count = map.get(num);
				if(count > temp) {
					temp = count;
					maxCountOfnum = num;
				}else if (count == temp) {
					//单变量如果应对出现两个则用两个变量存储，无法灵活多个
					//temp1 = count;
					//maxCountOfnum1= num;					  
				}
		}
		System.out.println("数组出现最高频率的数字是："+ maxCountOfnum);
//		System.out.println("数组出现最高频率的第二个数字是："+ maxCountOfnum1);
	}
	
	//优化后方法
	public static void resultOutput2() {
		for (Integer num: map.keySet()) {
			 int count =  map.get(num);
			if(tempMap.isEmpty()) {
				tempMapOfmaxvalue = num;
				tempMap.put(num, count);
			}else if ( count > tempMap.get(tempMapOfmaxvalue)) {
				tempMap.clear();
				tempMapOfmaxvalue = num;
				tempMap.put(num, count);
			}else if ( count == tempMap.get(tempMapOfmaxvalue)) {
				tempMap.put(num, count);
			}
		}
		System.out.println("数组出现最高频率的数字和频次分别是：");
		for (Integer num: tempMap.keySet()) {
			System.out.println(num+" - "+map.get(num));
		}
	}
	
	
	public static void main(String[] args) {
		findMaxCountOfNum();
	}
	
	
}
