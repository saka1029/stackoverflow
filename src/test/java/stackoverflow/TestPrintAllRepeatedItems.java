package stackoverflow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestPrintAllRepeatedItems {

	record Item(String key, String value) {
	}

	static void printAllRepeatedItems(Item[] items) {
		Arrays.stream(items).collect(groupingBy(Item::key, mapping(Item::value, toList()))).entrySet().stream()
				.filter(e -> e.getValue().size() > 1).forEach(
						e -> System.out.printf("duplicate items found, %s with values %s%n", e.getKey(), e.getValue()));
	}

	@Test
	public void test() {
		Item[] items = { new Item("KEY1", "123"), new Item("KEY2", "234"), new Item("KEY1", "456"), };
		printAllRepeatedItems(items);

	}

	record In(String col1, String col2, String col3, String col4) {}
	record Out(String col1, String col2, String col2Agg, String col3, String col3Agg, String col4) {}

	public static void main(String[] args) {
		List<In> list = List.of(
			new In("A", "ABC", "101", "1"),
			new In("B", "ABC", "102", "1"),
            new In("C", "ABCD", "101", "1"),
            new In("D", "ABCD", "101", "1"),
            new In("E", "ABC", "101", "1"));
		Map<String, Integer> map2 = new HashMap<>();
		Map<String, Integer> map3 = new HashMap<>();
		List<Out> result = list.stream()
            .map(i -> new Out(i.col1, i.col2,
                "(%s,%d)".formatted(i.col2, map2.compute(i.col2, (k, v) -> v == null ? 1 : v + 1)),
                i.col3,
                "(%s,%d)".formatted(i.col3, map3.compute(i.col3, (k, v) -> v == null ? 1 : v + 1)),
                i.col4))
				.toList();
		for (Out o : result)
			System.out.println(o);
	}

}
