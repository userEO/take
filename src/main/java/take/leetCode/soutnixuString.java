package take.leetCode;

import java.util.Scanner;

/**
 * 功能描述：
 *
 * @author EO 字符逆序
 * @date 2020/5/1 13:10
 */
public class soutnixuString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = s.toCharArray();
        for (int i = chars.length; i > 0; i--) {

            stringBuffer.append(chars[i-1]);
        }
        System.out.println(stringBuffer);
    }
}
