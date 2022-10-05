package stackoverflow;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJackson {

    static class Item {
        public String key;
        public Map<String, Integer> values;

        public Item() {
        }

        public Item(String key, Map<String, Integer> values) {
            super();
            this.key = key;
            this.values = values;
        }

        public String getKey() {
            return key;
        }

        public Map<String, Integer> getValues() {
            return values;
        }

        @Override
        public String toString() {
            return "Item [key=" + key + ", values=" + values + "]";
        }
    }

    static final TypeReference<HashMap<String, Integer>> TYPEREF_MAP = new TypeReference<HashMap<String, Integer>>() {
    };

    static final Map<String, String> ITEM_VALUES0 = Map.of(
        "k1", "{\"key1\":1,\"key2\":2}",
        "k2", "{\"key1\":3,\"key2\":4}");

    static HashMap<String, Integer> toMap(String s) {
        try {
            return new ObjectMapper().readValue(s, TYPEREF_MAP);
        } catch (JsonProcessingException e) {
            return new HashMap<>();
        }
    }

    public static List<Item> getItems0() {
        return ITEM_VALUES0.entrySet().stream()
            .map(entry -> new Item(entry.getKey(), toMap(entry.getValue())))
            .collect(Collectors.toList());
    }

//    @Test
    public void test() {
        System.out.println(getItems0());
    }

    static final String ITEM_VALUES = "[{\"key\":\"k1\", \"values\":{\"key1\":1,\"key2\":2}},"
        + "{\"key\":\"k2\", \"values\":{\"key1\":3,\"key2\":4}}]";

    static final TypeReference<List<Item>> TYPEREF_LIST_ITEM = new TypeReference<List<Item>>() {
    };

    public static List<Item> getItems() throws JsonMappingException, JsonProcessingException {
        return new ObjectMapper().readValue(ITEM_VALUES, TYPEREF_LIST_ITEM);
    }

//    @Test
    public void testJso() throws JsonMappingException, JsonProcessingException {
        System.out.println(getItems());
    }

    public record TypeIdentifier(
        @JsonProperty("Type") String type,
        @JsonProperty("Identifier") String identifier) {
    }

    public record CarrierABCStatusUpdateRecord(@JsonProperty("Timestamp") String timeStamp,
        @JsonProperty("Agent") String agent,
        @JsonProperty("AgentDevice") String agentDevice,
        @JsonProperty("Trace") String trace,
        @JsonProperty("Consignment") String consignment,
//        @JsonProperty("Items") String items,
        @JsonProperty("Items") Map<String, TypeIdentifier> items,
        @JsonProperty("FreightHandler") String freightHandler,
        @JsonProperty("Signature") String signature,
        @JsonProperty("Comment") String comment,
        @JsonProperty("Depot") String depot) {
    }

    @Test
    public void testMap() throws JsonMappingException, JsonProcessingException {
        String s = "{\r\n"
            + "    \"Timestamp\": \"2022-09-15T08:35:15\",\r\n"
            + "    \"Agent\": \"JP\",\r\n"
            + "    \"AgentDevice\": \"\",\r\n"
            + "    \"Trace\": \"LOAD FOR DELIVERY\",\r\n"
            + "    \"Consignment\": \"CABC00033\",\r\n"
            + "    \"Items\": {\r\n"
            + "        \"CABC00033003\": {\r\n"
            + "            \"Type\": \"CONSIGNMENT\",\r\n"
            + "            \"Identifier\": \"\"\r\n"
            + "        },\r\n"
            + "        \"CABC00033002\": {\r\n"
            + "            \"Type\": \"CONSIGNMENT\",\r\n"
            + "            \"Identifier\": \"\"\r\n"
            + "        },\r\n"
            + "        \"CABC00033001\": {\r\n"
            + "            \"Type\": \"CONSIGNMENT\",\r\n"
            + "            \"Identifier\": \"\"\r\n"
            + "        }\r\n"
            + "    },\r\n"
            + "    \"FreightHandler\": \"\",\r\n"
            + "    \"Signature\": \"\",\r\n"
            + "    \"Comment\": \"\",\r\n"
            + "    \"Depot\": null\r\n"
            + "}";

        CarrierABCStatusUpdateRecord r = new ObjectMapper().readValue(s,
            new TypeReference<CarrierABCStatusUpdateRecord>() {});
        System.out.println(r);
    }

}
