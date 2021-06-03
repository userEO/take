package take.kafka.simpleptp;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 功能描述：消费者
 *
 * @author EO
 * @date 2021/6/2 22:01
 */
@Component //实例化类
public class KafkaConsumer {
    // kafka监听的注解
    @KafkaListener(topics = "etopic", errorHandler = "consumerAwareErrorHandler")
    public void getMessage(ConsumerRecord<?, ?> record) {
        System.out.println("简单消费" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

    /**
     * 订阅多个主题
     * <p>
     * ① id：消费者ID；
     * <p>
     * ② groupId：消费组ID；
     * <p>
     * ③ topics：监听的topic，可监听多个；
     * <p>
     * ④ topicPartitions：可配置更加详细的监听信息，可指定topic、parition、offset监听。
     * <p>
     * 含义：监听topic1的0号分区，同时监听topic2的0号分区和topic2的1号分区里面offset从8开始的消息。
     * <p>
     * 注意：topics和topicPartitions不能同时使用；
     */
    @KafkaListener(id = "eConsumer", groupId = "eo", topicPartitions = {
            @TopicPartition(topic = "etopic", partitions = {"7"})
    }, errorHandler = "consumerAwareErrorHandler")
    public void getMessageM(ConsumerRecord<?, ?> record) {
        System.out.println("简单消费" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

    /**
     * 批量消费
     *
     * @param recordList recordList
     *                   errorHandler 异常处理
     *                   containerFactory 过滤
     */
    @KafkaListener(id = "eConsumer", groupId = "eo", topics = {"etopic"}, errorHandler = "consumerAwareErrorHandler",
            containerFactory = "filterContainerFactory")
    public void getListMsg(List<ConsumerRecord> recordList) {
        recordList.forEach(x -> System.out.println(x.partition() + ":" + x.value()));
    }

    /**
     * 定时器kafka消费
     */
    @KafkaListener(id = "eConsumer", groupId = "eo", topics = {"etopic"}, errorHandler = "consumerAwareErrorHandler",
            containerFactory = "delayContainerFactory")
    public void timeMsg(ConsumerRecord<?, ?> record) {
        System.out.println("简单消费" + record.topic() + "-" + record.partition() + "-" + record.value());
    }
}
