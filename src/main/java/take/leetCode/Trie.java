package take.leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 功能描述：实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie Trie = new Trie();
 *
 * Trie.insert("apple");
 * Trie.search("apple");   // 返回 true
 * Trie.search("app");     // 返回 false
 * Trie.startsWith("app"); // 返回 true
 * Trie.insert("app");
 * Trie.search("app");     // 返回 true
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author EO
 * 做之前看过一篇文章 https://mp.weixin.qq.com/s/ZYtU4v9y2KMLT0d2X_MIZQ
 *文章推荐hashmap，但是我这边觉得使用list更好
 * @date 2019/11/21 15:26
 */
public class Trie {
    /** Initialize your data structure here. */
  /*  List<String> list = new ArrayList<>();
    public Trie() {

    }

    *//** Inserts a word into the Trie. *//*
    public void insert(String word) {
        list.add(word);
    }

    *//** Returns if the word is in the Trie. *//*
    public boolean search(String word) {

        for (String s:list){
            if(s.equals(word)){
                return true;
            }
        }
        return false;
    }

    *//** Returns if there is any word in the Trie that starts with the given prefix. *//*
    public boolean startsWith(String prefix) {
        for (String s:list){
            if(s.substring(0,prefix.length()).equals(prefix)){
                return true;
            }
        }
        return  false;
    }*/


    //leetcode官方解  4ms
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }
    // search a prefix or whole key in trie and
    // returns the node where search ends
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.containsKey(curLetter)) {
                node = node.get(curLetter);
            } else {
                return null;
            }
        }
        return node;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

}
