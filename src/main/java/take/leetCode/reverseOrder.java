package take.leetCode;

import jdk.nashorn.internal.ir.WhileNode;

import java.util.Scanner;

/**
 * 功能描述：输入任意4个字符(如：abcd)， 并按反序输出(如：dcba)
 * 注：题目可能包含多组用例，每组用例占一行，包含4个任意的字符。
 *
 * @author EO
 * @date 2019/12/17 18:53
 */
public class reverseOrder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str ="";
        while ((scanner.hasNext())){
            char[] arr = scanner.nextLine().toCharArray();
            for(int i=arr.length-1;i>=0;i--){
                str = str+arr[i];
                if(i==0){
                    System.out.println(str);
                    str="";
                    break;
                }
            }
        }
    }

}
