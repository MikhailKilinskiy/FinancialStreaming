package streams;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import streams.model.StockData;
import streams.serde.StockDataSerializer;

public final class StreamProducer {

    private final Producer<String, StockData> producer;
    static final String FIRST_TOPIC = "first_topic";

    private StreamProducer(String brokers) {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StockDataSerializer.class.getName());
        producer = new KafkaProducer<>(props);
    }

    private void produce(int rate) {
        long wait = 1000L / rate;

        while(true){
            ArrayList<StockData> stocks = DataExtractor.getStockData("AAPL,MSFT,HSBA");

            for (StockData stock : stocks) {
                //System.out.println(stock);
                Future futureResult = producer.send(new ProducerRecord<>(FIRST_TOPIC, stock));
                try {
                    Thread.sleep(wait);
                    futureResult.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static void main(String[] args) {
        new StreamProducer("localhost:9092").produce(1);
    }
}
