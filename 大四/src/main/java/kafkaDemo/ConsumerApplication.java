package kafkaDemo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/4/15 15:13
 * @Description:
 */
public class ConsumerApplication {
    public static void main(String[] args) {
        Properties p = new Properties();
        p.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        p.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        p.put("bootstrap.servers", ProducerApplication.SERVERS);
        p.put("group.id", "demoGroupId");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(p);
        consumer.subscribe(Collections.singletonList(ProducerApplication.TOPIC));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMinutes(3));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }
        }

    }
}
