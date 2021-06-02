package take.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：重写kafka分区规则
 * <p>
 * 但是我们也需要自带默认的规则
 * ① 若发送消息时指定了分区（即自定义分区策略），则直接将消息append到指定分区；
 * <p>
 * ② 若发送消息时未指定 patition，但指定了 key（kafka允许为每条消息设置一个key），
 * 则对key值进行hash计算，根据计算结果路由到指定分区，这种情况下可以保证同一个 Key 的所有消息都进入到相同的分区；
 * <p>
 * ③patition 和 key 都未指定，则使用kafka默认的分区策略，轮询选出一个 patition；
 *
 * @author EO
 * @date 2021/6/2 23:11
 */
public class CustomizePartitioner implements Partitioner {
    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        // 获取topic中partition数量
        List<PartitionInfo> partitionInfoList = cluster.availablePartitionsForTopic(s);
        int partitionCount = partitionInfoList.size();
        // 根据key的hash值计取模，计算出在哪个分区中
        int numPartitions = Math.abs(String.valueOf(o).hashCode()) % partitionCount;
        return numPartitions;
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> map) {
    }
}
