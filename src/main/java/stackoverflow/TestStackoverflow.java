package stackoverflow;

import java.util.HashMap;
import java.util.Map;

public class TestStackoverflow {

static class Map2d<K1, K2, V> {
    Map<K1, Map<K2,V>> map = new HashMap<>();

    public void put(K1 k1, K2 k2, V v) {
        map.computeIfAbsent(k1, k -> new HashMap<>()).put(k2, v);
    }

    @Override
    public String toString() {
        return map.toString();
    }
}

public static void main(String[] args) {
    Map2d<Integer, String, Object> map = new Map2d<>();
    map.put(1, "名前", "田中");
    map.put(1, "国語", 100);
    map.put(1, "国語", 33);
    map.put(1, "数学", 10);
    map.put(2, "名前","高橋");
    map.put(2, "国語", 63);
    map.put(2, "数学", 62);
    System.out.println(map);
}
}
