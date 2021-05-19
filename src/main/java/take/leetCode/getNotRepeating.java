package take.leetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.String.valueOf;

/**
 * 功能描述：输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
 *
 * @author EO
 * @date 2019/12/17 15:39
 */
public class getNotRepeating {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
       char[] arr = str1.toCharArray();
       String str = "";
       for(int i=arr.length-1;i>=0;i--){
           if(str.indexOf(arr[i])==-1){
               str=str+arr[i];
           }
       }
        System.out.println(str);
    }
}
