package streams;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import streams.model.StockData;

public class DataExtractor {

    private static final String API_KEY = "TjzgAHYLDDOsnScZujl3NkqO2fgGZglfDwnSmNDCDnNLC4FIUdM82mFl6EEk";//"YOUR API_KEY_VALUE";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static ArrayList<StockData> getStockData(String symbols) {

        final ArrayList<StockData> data = new ArrayList<StockData>();

        try {
            final String apiURL = "https://www.worldtradingdata.com/api/v1/stock?symbol="+symbols+"L.&api_token="+API_KEY;
            final URL url = new URL(apiURL);
            final JsonNode root = MAPPER.readTree(url);
            final JsonNode node = root.path("data");
            System.out.println(node.toString());
            StockData[] tmp = MAPPER.readValue(node.traverse(), StockData[].class);
            data.addAll(Arrays.asList(tmp));
            Logger.getLogger(DataExtractor.class.getName()).log(Level.INFO, null, apiURL);

            return data;

        } catch (IOException ex) {
            Logger.getLogger(DataExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

}
