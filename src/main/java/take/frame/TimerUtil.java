package take.frame;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2021/5/19 19:32
 */
public class TimerUtil {
    private static Timer timer = new Timer();
   private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 核心线程为2 如果核心为1那么就和timer一样了
    private static ScheduledExecutorService schedule = new ScheduledThreadPoolExecutor(2);
    public static void main(String[] args) {
        one();
        two();
        three();
        four();
    }
    private static void one(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    // timer类是单线程，所以执行第二个会在第一个执行完毕
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("当前一执行"+simpleDateFormat.format(new Date()));
            }
        },5000);

    }
    private static void two(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("当前二执行"+simpleDateFormat.format(new Date()));
            }
        },5000);
    }
    private static void three(){
        schedule.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    // ScheduledExecutorService不会阻挡第四个执行
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("当前三执行"+simpleDateFormat.format(new Date()));
            }
        }, 3000, TimeUnit.MICROSECONDS);
    }
    private static void four(){
        schedule.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    // ScheduledExecutorService不会阻挡第四个执行
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("当前四执行"+simpleDateFormat.format(new Date()));
            }
        }, 3000, TimeUnit.MICROSECONDS);
    }
}
