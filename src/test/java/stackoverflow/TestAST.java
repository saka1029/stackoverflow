package stackoverflow;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class TestAST {

	static final List<String> definedOrder = List.of(
		"Initial","Final","Deleted","Keep","Exception");
	static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    record Rec(int key0, Date key1, List<String> row) {}

	static Date parseDate(String input) {
		try {
			return simpleDateFormat.parse(input);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		List<List<String>> outputToStore = Arrays.asList(
            Arrays.asList("Final", "331", "M", "22/03/2020 00:00:00"), 
            Arrays.asList("Initial", "335", "M", "22/06/2022 00:00:00"), 
            Arrays.asList("Exception", "335", "M", "22/05/2022 00:00:00"), 
            Arrays.asList("Final", "335", "M", "20/06/2022 00:00:00"), 
            Arrays.asList("Keep", "335", "M", "02/06/2022 11:00:00"), 
            Arrays.asList("Final", "335", "M", "10/04/2022 02:00:00"), 
            Arrays.asList("Deleted", "335", "M", "22/06/2022 15:55:10"),
            Arrays.asList("Exception", "335", "M", "22/06/2022 15:55:09"), 
            Arrays.asList("Final", "335", "M", "22/06/2022 15:56:00"), 
            Arrays.asList("Initial", "335", "M", "11/06/2022 00:00:00")
        );

		List<List<String>> sorted = outputToStore.stream()
			.map(e -> new Rec(definedOrder.indexOf(e.get(0)), parseDate(e.get(3)), e))
			.sorted(Comparator.comparingInt(Rec::key0).thenComparing(Rec::key1))
			.map(Rec::row)
			.toList();

		for (List<String> row : sorted)
			System.out.println(row);
	}
}