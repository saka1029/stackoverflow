package stackoverflow;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class TestIntersect {

	static Map<Integer, Long> histogram(int... ints) {
		return IntStream.of(ints)
			.boxed()
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}
	
	static int[] intersect(int[] nums1, int[] nums2) {
		Map<Integer, Long> hist1 = histogram(nums1);
		return histogram(nums2).entrySet().stream()
			.flatMapToInt(e ->
				IntStream.range(0, (int)Math.min(e.getValue(), hist1.getOrDefault(e.getKey(), 0L)))
					.map(i -> e.getKey()))
			.toArray();
	}
	
	@Test
	public void test() {
		System.out.println(histogram(1,2,2,1,3));
		assertArrayEquals(new int[] {2, 2}, intersect(new int[] {1, 2, 2, 1}, new int[] {2, 2}));
	}

}
