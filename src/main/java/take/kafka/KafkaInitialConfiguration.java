package take.kafka;

import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：代码创建topic，避免手动进入kafka创建
 *
 * @author EO
 * @date 2021/6/2 21:51
 */
@Configuration
public class KafkaInitialConfiguration {
    // 创建一个名为testtopic的Topic并设置分区数为8，分区副本数为2
    /**
     * 创建一次之后，就不需要再次创建了
     */
//    @Bean
//    public NewTopic initialTopic() {
//        /**
//         * 如果本地没有配置多余的卡夫卡，也就是集群，副本只能为1
//         */
//        return new NewTopic("etopic", 8, (short) 1);
//    }

    // 如果要修改分区数，只需修改配置值重启项目即可
    // 修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
//    @Bean
//    public NewTopic updateTopic() {
//        return new NewTopic("etopic", 10, (short) 2);
//    }
/**
 * 用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，
 * 这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，
 * 并用于构建bean定义，初始化Spring容器。
 */

}
