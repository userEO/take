package take.leetCode;

import java.util.Scanner;

/**
 * 功能描述：计算a+b的和
 *
 * @author EO
 * @date 2019/12/17 16:06
 */
public class addNum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            System.out.println(input.nextInt()+input.nextInt());
        }
    }
}
