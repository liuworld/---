package demo;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 第二种方法：格式化为字符类型 + 数组两趟遍历
 * 	格式化为字符类型：
 * 	1）减少了对时间的排序步骤工作，提高时间，也避开算法处理不优的地方。
 *  2）唯一不足的是当数组量大时，缓存字符串存储量过大，带来性能过大开销，以空间换时间的代价。
 * 	数组两趟遍历：
 * 	1）第一趟，控制单个字符与字符串依次比较，相等则累加出现频次。
 *  2）第二趟，控制单个字符的位移。
 * @author 留
 *
 */
public class Test2 {
	
		static int[] array = {3, 2, 8, 2, 13, 8, 2, 0, 10, 8};
		
		public static void findMaxCountOfNum() {
			int count = 1;
			int temp = 1;
			//高频数字用map<k,v>存储好处： 
			//1.map对象可存多个重复的频次数字 2.除了记录高频数，也可以记录频次，例如业务场景：商品名 - 最高销售量
			Map<Integer,Integer> map = new HashMap<Integer,Integer>();
			map.put(array[0], count);
			StringBuffer strBuf = new StringBuffer();
			
			//数组存储转为字符存储
			for (int num : array) {
				strBuf.append(num+"");
			}
			//遍历比较频次
			for (int i = 0; i < array.length-1; i++) {
				String iStr = array[i]+"";
				for (int j = i+1; j < array.length; j++) {
					String jStr = array[j]+"";
					if (iStr.equals(jStr)) {
						//计数累加
						++temp;
					}
					if (j == array.length-1) {
						if (temp > count) {
							map.clear();
							//记录目前最高频的数字和频次
							map.put(array[i], temp);
							count = temp;
						}else if(temp == count) {
							//防止出现相同频次的数字
							map.put(array[i], temp);
						}
					}
				} 
				//清除缓存计数
				temp = 1;
			}
			
			//遍历map输出结果
			System.out.println("数组出现最高频率的数字和频次分别是：");
			for (Integer num: map.keySet()) {
				System.out.println(num+" - "+map.get(num));
			}
	
		}
	
	public static void main(String[] args) {
		findMaxCountOfNum();
		
	}
	
}
