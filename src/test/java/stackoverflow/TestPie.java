package stackoverflow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class TestPie {

    public static List<List<Integer>> rotate(List<List<Integer>> matrix, int n) {
//        matrix.forEach(row -> {
            for (int i = 0, j = n - 1; i < j; i++, j--)
                swap(matrix, i, matrix, j);
//        });
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            for (int ii = i, jj = j, k = 0, l = n - 1; ii >= 0; ii--, jj++, k++, l--) {
                List<Integer> rowSrc = matrix.get(ii);
                List<Integer> rowDest = matrix.get(l);
                swap(rowSrc, k, rowDest, jj);
            }
        }
        return matrix;
    }

    private static <T> void swap(List<T> one, int i, List<T> two, int j) {
        T tmp = one.get(i);
        one.set(i, two.get(j));
        two.set(j, tmp);
    }
    
    @Test
    public void testSwap() {
        List<List<Integer>> m = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5, 6),
            Arrays.asList(7, 8, 9)
        );
        rotate(m, 3);
        for (List<Integer> row : m)
            System.out.println(row);
    }
    
    @Test
    public void testSwap2() {
        List<List<Integer>> m = Arrays.asList(
            Arrays.asList(1, 2),
            Arrays.asList(3, 4)
        );
        rotate(m, 2);
        for (List<Integer> row : m)
            System.out.println(row);
    }
    
    static <T> void rotate90Clockwise(List<List<T>> a) {
        int n = a.size();
        for (int i = 0, mi = n / 2, ni = n - 1; i < mi; i++, ni--) {
            for (int j = i, mj = n - i - 1, nj = mj; j < mj; j++, nj--) {
                System.out.println("i=" + i + " mi=" + mi + " ni=" + ni + " j=" + j + " mj=" + mj + " nj=" + nj);
                T temp = a.get(i).get(j);
                a.get(i).set(j, a.get(nj).get(i));
                a.get(nj).set(i, a.get(ni).get(nj));
                a.get(ni).set(nj, a.get(j).get(ni));
                a.get(j).set(ni, temp);
            }
        }
    }

    @Test
    public void testCopy() {
        List<List<Integer>> m = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5, 6),
            Arrays.asList(7, 8, 9)
        );
        rotate90Clockwise(m);
        for (List<Integer> r : m)
            System.out.println(r);
    }
}