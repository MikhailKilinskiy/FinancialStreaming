package streams;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import streams.model.StockData;
import streams.serde.StockDataDeserializer;

// $ kafka-topics --zookeeper localhost:2181 --create --topic uptimes --replication-factor 1 --partitions 4

public final class test {
    private Consumer<String, StockData> consumer;
    static final String FIRST_TOPIC = "first_topic";
    static final String TEST_TOPIC = "test_topic";

    public test(String brokers) {
        Properties consumerProps = new Properties();
        consumerProps.put("bootstrap.servers", brokers);
        consumerProps.put("group.id", "test-consumer-group");
        consumerProps.put("key.deserializer", StringDeserializer.class);
        consumerProps.put("value.deserializer", StockDataDeserializer.class);
        consumer = new KafkaConsumer<>(consumerProps);
    }

    public final void process() {
        consumer.subscribe(Collections.singletonList(TEST_TOPIC));
        while(true) {
            ConsumerRecords records = consumer.poll(Duration.ofSeconds(1L));
            for(Object record : records) {
                ConsumerRecord it = (ConsumerRecord) record;
                //StockData value = (StockData) it.value();
                System.out.println(it.key());
                System.out.println(it.value());
            }
        }
    }

    public static void main( String[] args) {
        new test("localhost:9092").process();
    }
}
