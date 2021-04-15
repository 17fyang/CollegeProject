package kafkaDemo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/4/15 14:42
 * @Description:
 */
public class ProducerApplication {
    public static final String SERVERS = "127.0.0.1:9092";
    public static final String TOPIC = "testTopic";

    public static void main(String[] args) {
        Properties p = new Properties();
        p.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        p.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        p.put("bootstrap.servers", SERVERS);

        KafkaProducer<String, String> producer = new KafkaProducer<>(p);

        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, "hello word!");
        producer.send(record);
        producer.close();
    }
}
