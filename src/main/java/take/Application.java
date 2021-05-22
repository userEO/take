package take;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import take.nettyserver.NettyServerListener;

/**
 * 功能描述：启动类
 *
 * @author EO
 * @date 2019/8/30 15:17
 */
//@ServletComponentScan
@MapperScan("take.mapper")
@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        NettyServerListener nettyServerListener = new NettyServerListener();
        nettyServerListener.start();
    }

    public Application(RedisTemplate redisTemplate) {
        redisTemplate.opsForValue().set("spring-r-cluster-1", 123);
        redisTemplate.opsForValue().set("spring-r-cluster-2", 456);
        redisTemplate.opsForValue().set("spring-r-cluster-3", 789);
        redisTemplate.opsForValue().set("spring-r-cluster-4", 101112);
        redisTemplate.opsForValue().set("spring-r-cluster-5", 131415);
        redisTemplate.opsForValue().set("spring-r-cluster-6", 161718);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//
//        };
//    }
}
