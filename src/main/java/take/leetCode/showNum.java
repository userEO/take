package take.leetCode;

import java.util.Scanner;

/**
 * 功能描述：表示数字
 *
 * @author EO
 * @date 2020/5/1 13:21
 */
public class showNum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        StringBuffer stringBuffer = new StringBuffer();
        String regx = "\\d+$";
        String repl="";
        for (int i = 0; i < s.length(); i++) {
            if(s.substring(i,i+1).matches(regx)){
                repl +=s.substring(i,i+1);
            }else if(repl!=""){
                stringBuffer.append("*").append(repl).append("*").append(s.substring(i,i+1));
                repl="";
            }else {
                stringBuffer.append(s.substring(i,i+1));
            }
        }
        System.out.println(stringBuffer);
    }
}
