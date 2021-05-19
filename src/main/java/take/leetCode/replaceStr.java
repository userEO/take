package take.leetCode;

import java.util.Scanner;

/**
 * 功能描述：空格替换
 *
 * @author EO
 * @date 2020/5/1 12:36
 */
public class replaceStr {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {

            if(s.substring(i,i+1).equals(" ")){
                stringBuffer.append("%20");
            }else{
                stringBuffer.append(s.substring(i,i+1));
            }
        }
        System.out.println(stringBuffer);
    }
}
