package take.kafka.callback;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：kafka回调
 *
 * @author EO
 * @date 2021/6/2 22:51
 */
@RestController
public class ConSumerKPC {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/kafka/ecallback/{message}")
    public void sendMessage(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("etopic", callbackMessage).addCallback(success -> {
            // 消息发送到的topic
            String topic = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
        }, fail -> {
            System.out.println("发送失败:" + fail.getMessage());
        });
    }

    /**
     * There is already 'conSumerKPC' bean method
     * 这里报了这个错误，本质上是有重复路径请求，复制粘贴请注意，看了半天
     *
     * @param callbackMessage
     */
    @GetMapping("/kafka/ecallTask/{message}")
    public void sendMessageTask(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("etopic", callbackMessage).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("发送失败" + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("发送成功" + result.getProducerRecord());
                System.out.println("发送成功" + "发送消息成功：" + result.getRecordMetadata().topic() + "-"
                        + result.getRecordMetadata().partition() + "-" + result.getRecordMetadata().offset());
            }
        });
    }

    /**
     * kafka事务方法
     */
    @GetMapping("/kafka/transaction")
    public void sendMsgTran() {
        kafkaTemplate.executeInTransaction(kafkaOperations -> {
            kafkaOperations.send("etopic", "test executeInTransaction");
            throw new RuntimeException("fail");
        });
        // 不声明事务：后面报错但前面消息已经发送成功了
        kafkaTemplate.send("topic1", "test executeInTransaction");
        throw new RuntimeException("fail");
    }
}
