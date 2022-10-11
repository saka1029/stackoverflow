package stackoverflow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestPie {

	static List<List<Integer>> sum(List<Integer> list, int target) {
		int size = list.size();
		List<List<Integer>> result = new ArrayList<>();
		int[] count = new int[size];
		new Object() {
			
			void found() {
				List<Integer> t = new ArrayList<>();
				for (int i = 0; i < size; ++i) {
					int e = list.get(i);
					for (int j = 0, max = count[i]; j < max; ++j)
						t.add(e);
				}
				result.add(t);
			}

			void find(int index, int sum) {
				if (index >= size) {
					if (sum == target) found();
				} else {
					int e = list.get(index);
					for (int i = 0, newSum = sum; true; ++i, newSum += e) {
						if (newSum > target) break;
						count[index] = i;
						find(index + 1, newSum);
					}
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