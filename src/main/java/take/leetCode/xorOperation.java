package take.leetCode;

import java.util.Arrays;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2021/5/11 21:16
 */
public class xorOperation {
    public int xorOperation(int n, int start) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = start + 2 * i;
        }
        Integer sum = Arrays.asList(arr).stream().reduce(0, (a, b) -> a ^ b);
        return sum;
    }
}

