package take.leetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * 功能描述：输入N个（N<=10000）数字，求出这N个数字中的最大值和最小值。每个数字的绝对值不大于1000000。
 *
 * @author EO
 * @date 2019/12/17 15:52
 */
public class getMaxAndMin {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str = reader.readLine())!=null) {
            int N = Integer.parseInt(str);
            String[] s = reader.readLine().split(" ");
            int[] a = new int[N];
            int max,min;
            for(int i=0;i<N;i++) {
                a[i] = Integer.parseInt(s[i]);
            }
            //先赋值第一个初始
            max=a[0];
            min=a[0];
            for(int i=1;i<N;i++) {
                if(a[i]<=min) min = a[i];
                if(a[i]>max) max = a[i];
            }
            System.out.println(max+" "+min);

        }
    }
}
