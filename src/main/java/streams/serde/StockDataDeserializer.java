package streams.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.common.serialization.Deserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import streams.model.StockData;

import java.io.IOException;
import java.util.Map;

public final class StockDataDeserializer implements Deserializer {

    private ObjectMapper mapper = new ObjectMapper();
    //mapper.setSerializationInclusion(Include.NON_NULL);
    @Override
    public StockData deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            return mapper.readValue(data, StockData.class);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void close() {}

    @Override
    public void configure(Map configs, boolean isKey) {}
}