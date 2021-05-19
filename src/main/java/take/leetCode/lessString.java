package take.leetCode;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 功能描述：最少字符
 *
 * @author EO
 * @date 2020/5/1 14:56
 */
public class lessString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String repl = s;
        Set<String> set= new HashSet<>();
        int num=0;
        for(int i=0;i<s.length();i++){
          set.add(s.substring(i,i+1));
          if(set.size()+num==i){
              num+=1;
              repl = repl.replace(s.substring(i,i+1), "");
          }
        }
        System.out.println(repl);
        if(repl.length()>0){
            System.out.println(repl.charAt(0));
        }else{
            System.out.println(-1);
        }
    }

}
