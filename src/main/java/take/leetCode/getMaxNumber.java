package take.leetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 功能描述：输入10个整数，要求输出其中的最大值。
 *
 * @author EO
 * @date 2019/12/19 11:21
 */
public class getMaxNumber {
    public static void main(String[]args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String line=null;
        while((line=br.readLine())!=null){
            String[]num=line.split(" ");
            int max=Integer.parseInt(num[0]);
            for(String a:num){
                max=Math.max(max,Integer.parseInt(a));
            }System.out.println("max="+max);
        }br.close();
    }
}
