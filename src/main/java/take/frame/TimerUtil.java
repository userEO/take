package take.frame;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2021/5/19 19:32
 */
public class TimerUtil {
    private static Timer timer = new Timer();
   private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 核心线程为1
    private static ScheduledExecutorService schedule = new ScheduledThreadPoolExecutor(1);
    public static void main(String[] args) {
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
                System.out.println("当前线程一执行"+simpleDateFormat.format(new Date()));
            }
        },5000);

    }
    private static void two(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("当前线程一执行"+simpleDateFormat.format(new Date()));
            }
        },5000);

    }
}
