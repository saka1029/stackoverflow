package stackoverflow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestPie {

	static List<List<Integer>> sum(List<Integer> list, int target) {
		int size = list.size();
		List<List<Integer>> r = new ArrayList<>();
		List<Integer> m = list.stream().map(i -> target / i).toList();
		List<Integer> c = Arrays.asList(new Integer[size]);
		new Object() {
			void f(int index, int sum) {
				if (index >= size) {
					if (sum == target) {
						List<Integer> t = new ArrayList<>();
						for (int i = 0; i < size; ++i) {
							int v = list.get(i);
							for (int j = 0, z = c.get(i); j < z; ++j)
								t.add(v);
						}
						r.add(t);
					}
				} else {
					int v = list.get(index);
					for (int i = 0, z = m.get(index); i <= z; ++i) {
						c.set(index, i);
						f(index + 1, sum + v * i);
					}
				}
			}
		}.f(0, 0);
		return r;
	}

	@Test
	public void testSum() {
		System.out.println(sum(List.of(2, 3, 5), 8));
	}
}