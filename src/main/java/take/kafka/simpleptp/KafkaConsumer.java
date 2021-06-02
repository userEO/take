package take.kafka.simpleptp;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 功能描述：消费者
 *
 * @author EO
 * @date 2021/6/2 22:01
 */
@Component //实例化类
public class KafkaConsumer {
    // kafka监听的注解
    @KafkaListener(topics = "etopic")
    public void getMessage(ConsumerRecord<?, ?> record) {
        System.out.println("简单消费" + record.topic() + "-" + record.partition() + "-" + record.value());
    }
}
