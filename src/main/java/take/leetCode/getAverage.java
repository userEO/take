package take.leetCode;

import java.util.Scanner;

/**
 * 功能描述：班上有学生若干名，给出每名学生的年龄（整数），求班上所有学生的平均年龄，保留到小数点后两位
 *
 * @author EO
 * @date 2019/12/17 18:45
 */
public class getAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum =0;
        for(int i=0;i<n;i++){
            sum = sum + scanner.nextInt();
        }
        System.out.println(String.format("%.2f", Double.valueOf(sum)/n));
    }
}
