package stackoverflow;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

import org.junit.Test;

public class TestPrintAllRepeatedItems {
	
	record Item(String key, String value) {}
	
	static void printAllRepeatedItems(Item[] items) {
		Arrays.stream(items)
			.collect(groupingBy(Item::key, mapping(Item::value, toList())))
			.entrySet().stream()
			.filter(e -> e.getValue().size() > 1)
			.forEach(e -> System.out.printf("duplicate items found, %s with values %s%n",
				e.getKey(), e.getValue()));
	}

	@Test
	public void test() {
    Item[] items = {
        new Item("KEY1", "123"), 
        new Item("KEY2", "234"),
        new Item("KEY1", "456"),
    };
    printAllRepeatedItems(items);
}

}
