package take.leetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 功能描述：2个数组合并
 *
 * @author EO
 * @date 2019/12/17 14:55
 */
public class arrMerge {
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line=br.readLine())!=null){
            int n=Integer.parseInt(line);
            String[] a=br.readLine().split(" ");
            int m=Integer.parseInt(br.readLine());
            String[] b=br.readLine().split(" ");
            TreeSet<Integer> set=new TreeSet<>();
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<n;i++){
                set.add(Integer.parseInt(a[i]));
            }
            for(int i=0;i<m;i++){
                set.add(Integer.parseInt(b[i]));
            }
            for(Integer c:set){
                sb.append(c);
            }
            System.out.println(sb);
        }
    }
}
