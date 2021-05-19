package take.leetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 功能描述：第一行输入一个数，为n，第二行输入n个数，这n个数中，如果偶数比奇数多，输出NO，否则输出YES。
 *
 * @author EO
 * @date 2019/12/17 18:24
 */
public class getNumOfParity {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int d = 0;
            int j = 0;
            for(int i=0;i<n;i++){
                if(sc.nextInt() %2 ==0){
                    d++;
                }else{
                    j++;
                }
            }
            if(d>j){
                System.out.println("NO");
            }else{
                System.out.println("YES");
            }
        }
    }
}
