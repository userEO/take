package take.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;

/**
 * 功能描述：消费过程中肯定会出现异常情况，这个地方就需要处理异常
 *
 * @author EO
 * @date 2021/6/3 22:29
 */
public class KafkaException {

    @Bean
    public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {
        return (message, exception, consumer) -> {
            System.out.println("消费异常：" + message.getPayload());
            return null;
        };
    }
}
