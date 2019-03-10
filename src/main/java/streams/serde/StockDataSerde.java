package streams.serde;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.Deserializer;
import streams.model.StockData;
import streams.serde.StockDataSerializer;
import streams.serde.StockDataDeserializer;
import java.util.*;

public class StockDataSerde implements Serde<StockData> {

    private StockDataSerializer stockDataSerializer;
    private StockDataDeserializer stockDataDeserializer;

    public void configure(Map<String, ?> map, boolean b) {}

    @Override
    public void close() {
        stockDataSerializer.close();
        stockDataDeserializer.close();
    }

    @Override
    public Serializer<StockData> serializer() {
        stockDataSerializer = new StockDataSerializer();
        return stockDataSerializer;
    }

    @Override
    public Deserializer<StockData> deserializer() {
        stockDataDeserializer = new StockDataDeserializer();
        return stockDataDeserializer;
    }
}