package stackoverflow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

public class TestPie {

	static List<List<Integer>> sum(List<Integer> in, int target) {
		int size = in.size();
		List<List<Integer>> result = new ArrayList<>();
		int[] count = new int[size];
		new Object() {
			
			void check(int sum) {
			    if (sum != target) return;
				List<Integer> t = new ArrayList<>();
				for (int i = 0; i < size; ++i)
					for (int j = 0, e = in.get(i), max = count[i]; j < max; ++j)
						t.add(e);
				result.add(t);
			}

			void find(int index, int sum) {
				if (index >= size)
				    check(sum);
				else
					for (int i = 0, e = in.get(index), newSum = sum; true; ++i, newSum += e) {
						if (newSum > target) break;
						count[index] = i;
						find(index + 1, newSum);
					}
			}
		}.find(0, 0);
		return result;
	}

	@Test
	public void testSum() {
		System.out.println(sum(List.of(2, 3, 5), 8));
	}
}