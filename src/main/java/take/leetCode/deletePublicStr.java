package take.leetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 功能描述：输入2个字符串，从第一字符串中删除第二个字符串中所有的字符
 *
 * @author EO
 * @date 2019/12/17 15:13
 */
public class deletePublicStr {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String x = br.readLine().toString();
        String y = br.readLine().toString();
        StringBuffer result = new StringBuffer();
        for(int i=0 ; i<x.length() ; i++){
            char c=x.charAt(i);
            if(y.indexOf(c)==-1)
                result.append(c);
        }
        System.out.println(result.toString());
        }
    }


