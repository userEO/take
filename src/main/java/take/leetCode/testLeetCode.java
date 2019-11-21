package take.leetCode;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2019/11/21 15:38
 */
public class testLeetCode {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();    //获取开始时间

        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println( trie.search("app"));

        long endTime = System.currentTimeMillis();    //获取结束时间

        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }
}
