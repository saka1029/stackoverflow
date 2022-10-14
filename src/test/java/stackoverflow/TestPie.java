package stackoverflow;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class TestPie {

    static int triplet0(int[] a, int n) {
        int size = a.length, count[] = {0};
        new Object() {
            void search(int index, int selected, int sum) {
                if (index >= size) {
                    if (selected == 3 && sum == n)
                        ++count[0];
                } else {
                    search(index + 1, selected, sum);
                    if (selected < 3 && sum < n)
                        search(index + 1, selected + 1, sum + a[index]);
                }
            }
        }.search(0, 0, 0);
        return count[0];
    }

    static int triplet(int[] a, int n) {
        Map<Integer, Integer> reduced = new TreeMap<>();
        for (int i : a)
            reduced.compute(i, (k, v) -> v == null ? 1 : v + 1);
        int size = reduced.size();
        int[] values = new int[size], counts = new int[size];
        int[][] perms = new int[size][3 + 1];
        int i = 0;
        for (Entry<Integer, Integer> e : reduced.entrySet()) {
            values[i] = e.getKey();
            int c = counts[i] = e.getValue();
            perms[i][0] = 1;
            for (int j = 1, u = c, v = u, d = 1, f = d; j <= 3; ++j, v *= --u, f *= ++d)
                perms[i][j] = v / f;
            ++i;
        }
        int[] result = {0};
        new Object() {
            void find(int index, int selected, int perm, int sum) {
                if (index >= size) {
                    if (selected == 3 && sum == n)
                        result[0] += perm;
                } else {
                    for (int i = 0, c = Math.min(counts[index], 3); i <= c; ++i) {
                        find(index + 1, selected + i,
                            perm * perms[index][i], sum + values[index] * i);
                    }
                }
            }
        }.find(0, 0, 1, 0);
        return result[0];
    }

//    @Test
    public void testTriplet() {
//	    int[] a = {1, 3, 3, 3, 3, 3, 3};
//	    int[] a = {1, 2, 3, 4, 5, 6, 7};
        int[] a = {3, 3, 3, 3, 3, 3, 3, 3, 3};
        System.out.println(triplet(a, 9));
    }

//	@Test
    public void test0() {
        int[] a = {1, 3, 3, 3, 3, 3, 3};
        System.out.println(triplet0(a, 9));
    }

    public static int tripletSum0(int[] arr, int num) {
        Arrays.sort(arr);
        int n = arr.length;
        int count = 0;

        for (int i = 0; i < n - 2; i++) {
            int sum = num - arr[i];

            int j = i + 1;
            int k = n - 1;

            while (j < k) {
                if (arr[j] + arr[k] == sum) {
                    count++;
                    k--;
                } else if (arr[j] + arr[k] > sum) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return count;
    }

    public static int tripletSum(int[] arr, int num) {
        Arrays.sort(arr);
        int n = arr.length;
        int count = 0;

        for (int i = 0; i < n - 2; i++) {
            int sum = num - arr[i];

            int j = i + 1;
            int k = n - 1;

            while (j < k) {
                if (arr[j] + arr[k] == sum) {
                    if (arr[j] == arr[k]) {
                        count += (k - j + 1) * (k - j) / 2;
                        break;
                    } else {
                        count++;
                    }
                    k--;
                } else if (arr[j] + arr[k] > sum) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return count;
    }
    
    public static int tripletSumSimple(int[] arr, int num) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k) {
                    if (arr[i] + arr[j] + arr[k] == num)
                        ++count;
                }
            }
        }
        return count;
    }
    
    
    static int tripletSumMap(int[] arr, int num) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i : arr)
            map.compute(i, (k, v) -> v == null ? 1 : v + 1);
        int size = map.size(), elements[] = new int[size], counts[] = new int[size];
        int p = 0;
        for (Entry<Integer, Integer> e : map.entrySet()) {
            elements[p] = e.getKey();
            counts[p] = e.getValue();
            ++p;
        }
        int count = 0;
        for (int i = 0; i < size - 2; ++i) {
            int sum = num - elements[i];
            for (int j = i + 1, k = size - 1; j < k;) {
                int ej = elements[j], ek = elements[k], sum2 = ej + ek;
                if (sum2 == sum) {
                    count += counts[i] * counts[j] * counts[k];
                    k--;
                } else if (sum2 > sum) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return count;
    }

    static void testTripletSum(int[] arr, int num) {
        int expected = tripletSumSimple(arr, num);
        int actual = tripletSumMap(arr, num);
        if (actual != expected)
            System.out.println(Arrays.toString(arr) + " " + num);
        assertEquals(expected, actual);
    }

    @Test
    public void testTripletSum() {
        testTripletSum(new int[] {1, 2, 3, 4, 5}, 6);
        testTripletSum(new int[] {1, 2, 3, 4, 5}, 9);
        testTripletSum(new int[] {1, 2, 3, 4, 5}, 12);
        testTripletSum(new int[] {3, 3, 3, 3}, 9);
        testTripletSum(new int[] {1, 3, 3, 3, 3, 3, 3}, 9);
        Random r = new Random(1);
        for (int i = 0; i < 1000; ++i)
            testTripletSum(r.ints(8, 0, 10).toArray(), r.nextInt(0, 20));
    }
}