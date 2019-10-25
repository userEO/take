package shirotest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import take.Application;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2019/10/14 10:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class LeetCode {

    @Test
    public void TwoIntegerSum(){
        ListNode sum = new ListNode(3);
        System.out.println(sum);
    }
    /**
     * 两数之和
     */
    public int[] twoSum(int[] nums, int target) {

        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    int[] end = {nums[i],nums[j]};
                    return end;
                }
            }
        }
        return null;
    }

    /**
     * 两数相加 给出两个 非空 的链表用来表示两个非负的整数。
     * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        //一开始不知道这个怎么用，但是new一个对象就知道大概需要什么数值和输入什么
        ListNode sum = new ListNode(3);
        System.out.println(sum);
        return null;
    }
    public class ListNode {
     int val;
     ListNode next;
    ListNode(int x) { val = x; }
  }
}
