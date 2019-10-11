package jmaes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import take.Application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2019/10/10 13:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class TestForList {
//    @Autowired
//    StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    RedisProperties redisProperties;
///*    对于随机访问get和set，ArrayList优于LinkedList，因为LinkedList要移动指针。
//    对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。
//    List是有序的，Set是无序的，
//    */
//    @Test
//    public void listT(){
//        List  list = new LinkedList();
//        List  list1 = new ArrayList();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        list.add("5");
//        list.add("6");
//        list.add("7");
//        list1.add("1");
//        list1.add("2");
//        list1.add("3");
//        list1.add("5");
//        list1.add("6");
//        list1.add("7");
//        System.out.println("LinkedList"+list);
//        System.out.println("ArrayList"+list1);
//    }
//
//    /*
//    List实现栈和队列
//     */
//    @Test
//    public void listfor(){
//        List<String>  list = new LinkedList();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        list.add("5");
//        list.add("6");
//        list.add("7");
//
//        stringRedisTemplate.opsForList().leftPushAll("list",list);
//
//        //栈 先进后出   foreach循环list里面的元素，取决于list父类的<>的类型
//        for (int i=list.size()-1;i>=0;i--){
//            System.out.println("栈输出"+list.get(i));
//        }
//        //队列
//        for (int i=0;i<list.size();i++){
//            System.out.println("队列输出"+list.get(i));
//        }
//        for (String stringL:list) {
//            System.out.println("输出"+stringL);
//
//        }
//
//
//    }
//
//    //队列
//    @Test
//    public void queueL(){
//        Queue queue = new LinkedList();
//        queue.add("1");
//        queue.add("1");
//        queue.add("2");
//        queue.add("3");
//        queue.add("4");
//        while(!queue.isEmpty()){
//            Object s=queue.remove();
//            System.out.println(s.toString());
//        }
//    }
//    //栈
//    @Test
//    public void listL(){
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        while(!list.isEmpty()){
//            if(list.size()>1){
//                list.remove(list.size()-1);
//                System.out.println(list);
//            }else{
//              return;
//            }
//        }
//    }
//    //队列 先进先出
//    @Test
//    public void redisqueueL(){
//        List<String>  list = new LinkedList();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        list.add("5");
//        list.add("6");
//        list.add("7");
//        stringRedisTemplate.opsForList().leftPushAll("list",list);
//        //移除指定位置的元素
//        while(true){
//            if(stringRedisTemplate.opsForList().size("list")==0){
//                return;
//            }else{
//                String s = stringRedisTemplate.opsForList().rightPop("list");
//                System.out.println("移除的元素为"+s);
//            }
//        }
//    }
//    //栈 先进后出
//    @Test
//    public void redislistL(){
//        List<String>  list = new LinkedList();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        list.add("5");
//        list.add("6");
//        list.add("7");
//        stringRedisTemplate.opsForList().leftPushAll("list",list);
//        //移除指定位置的元素
//        while(true){
//            if(stringRedisTemplate.opsForList().size("list")==0){
//                return;
//            }else{
//                String s = stringRedisTemplate.opsForList().leftPop("list");
//                System.out.println("移除的元素为"+s);
//            }
//        }
//    }

}
