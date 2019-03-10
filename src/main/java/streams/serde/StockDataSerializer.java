package streams.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public final class StockDataSerializer implements Serializer{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Object data) {
        if (data == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(data).getBytes();
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public void close() {}

    @Override
    public void configure(Map configs, boolean isKey) {}
}