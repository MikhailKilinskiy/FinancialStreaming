package streams;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import java.util.Properties;

import streams.model.StockData;
import streams.serde.StockDataSerde;
import sun.security.krb5.internal.tools.Ktab;

public final class StreamProcessor {

    private final String brokers;
    static final String FIRST_TOPIC = "first_topic";

    private StreamProcessor(String brokers) {
        this.brokers = brokers;
    }

    private void process() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, StockData> stream = streamsBuilder.stream(FIRST_TOPIC);


        KStream<String, Double> dataBySymbol = stream
                .selectKey((k, v) -> v.getSymbol())
                .mapValues(v -> v.getPrice());


        /*
        KTable<Windowed<String>, Long> stockGrowth = stream
                .map((ignoredKey, v) -> new KeyValue<>(v.getSymbol(), v.getPrice()))
                .groupByKey()
                .windowedBy(TimeWindows.of(Duration.ofMinutes(1).toMillis()))
                .count();
                //.reduce((v1, v2) -> v1+v1);
        */

        KTable<String, Double> stockGrowth = stream
                .map((ignoredKey, v) -> new KeyValue<>(v.getSymbol(), v.getPrice()))
                .groupByKey(Serialized.with(Serdes.String(), Serdes.Double()))
                //.count(Materialized.as("queryable-store-name"));
                .reduce((v1, v2) -> v2-v1,
                        Materialized.as("queryable-store-name"));
    /*
        KStream<String, Double> stockGrowthStream = stockGrowth
                .toStream()
                .map((windowedStock, count) -> new KeyValue<>(windowedStock.toString(), count));

        //stockGrowthTest.to("test_topic");


        stockGrowthStream.foreach((k, v) -> {
            System.out.println("JOIN STREAM: SYMBOL="+k+", VALUE= "+v.toString());
        });
        */

        KStream<String, String> joinedStream = dataBySymbol.leftJoin(stockGrowth,
                (leftPrice, rightGrowth) -> "price="+leftPrice+", growth="+rightGrowth,
                //JoinWindows.of(TimeUnit.MINUTES.toMillis(5)),
                Joined.with(
                        Serdes.String(),  //key
                        Serdes.Double(), //left value
                        Serdes.Double() //right value
                )
        );


        joinedStream.foreach((k, v) -> {
            System.out.println("JOIN STREAM: SYMBOL="+k+", VALUE= "+v);
        });


        Topology topology = streamsBuilder.build();
        Properties props = new Properties();
        props.put("bootstrap.servers", this.brokers);
        props.put("application.id", "streams");
        props.put("auto.offset.reset", "latest");
        props.put("commit.interval.ms", 0);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, StockDataSerde.class);
        KafkaStreams streams = new KafkaStreams(topology, props);
        streams.start();
    }

    public static void main(String[] args) {
        (new StreamProcessor("localhost:9092")).process();
    }
}
