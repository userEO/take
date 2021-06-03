package take.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Component;

/**
 * 功能描述：并非所有的消息都需要进行消费，所以这个时候我们就需要一个消息过滤器
 *
 * @author EO
 * @date 2021/6/3 22:35
 */
@Component
public class FilterMsg {
    @Autowired
    ConsumerFactory consumerFactory;

    @Bean
    public ConcurrentKafkaListenerContainerFactory filterContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        // 被过滤的消息将被丢弃
        factory.setAckDiscarded(true);
        // 消息过滤策略
        factory.setRecordFilterStrategy(consumerRecord -> {
            // 写一个简单的过滤规则
            if (consumerRecord.value().toString().contains("eo")) {
                return false;
            }
            //返回true消息则被过滤
            return true;
        });
        return factory;
    }
}
