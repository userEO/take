package take.leetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 功能描述：输入一个字符串，倒置这个字符串
 *
 * @author EO
 * @date 2019/12/17 15:22
 */
public class backStr {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String read=br.readLine();
        String[] numbers=read.split(" ");

        StringBuffer str=new StringBuffer();
        for(int i=numbers.length-1;i>=0;i--){
            str.append(numbers[i]).append(" ");
        }
        String result=str.toString().trim();
        System.out.print(result);
    }
}
