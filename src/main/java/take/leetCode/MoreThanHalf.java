package take.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 *
 * @author EO
 * @date 2019/12/19 12:09
 */
public class MoreThanHalf {
    public static int MoreThanHalfNum_Solution(int [] array) {
        int length = array.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                map.put(array[i], map.get(array[i])+1);

            } else {
                map.put(array[i], 1);
            }
            if (map.get(array[i]) > length/2) {
                return array[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,2,2,2,5,4,2};
        int a = MoreThanHalf.MoreThanHalfNum_Solution(arr);
        System.out.println(a);
    }
}
