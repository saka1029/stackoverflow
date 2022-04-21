package stackoverflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

public class TestStackoverflow {

    // Function to compute permutations
    private static <T> ArrayList<ArrayList<T>> permutations(ArrayList<T> domain,
            ArrayList<ArrayList<T>> perms, int n, int first) {

        if (first == n) {
            perms.add(new ArrayList<>(domain));
        }

        for (int i = first; i < n; i++) {
            Collections.swap(domain, first, i);
            permutations(domain, perms, n, first + 1);
            Collections.swap(domain, first, i);
        }
        return perms;
    }

    static <T> ArrayList<ArrayList<T>> permutations(ArrayList<T> domain) {
        return permutations(domain, new ArrayList<>(), domain.size(), 0);
    }


    public static void main(String... args) {
        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
        Set<Function<Integer, Integer>> bijections = bijectionsOf(a_few);
        bijections.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });
    }

    public static <T> Set<Function<T, T>> bijectionsOf(Set<T> set) {
        ArrayList<T> domain = new ArrayList<>(set);
        return permutations(domain, new ArrayList<>(), set.size(), 0).stream()
                .map(list -> (Function<T, T>) t -> list.get(domain.indexOf(t)))
                .collect(Collectors.toSet());
    }

    @Test
    public void testMain() {
        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
        Set<Function<Integer, Integer>> bijections = bijectionsOf(a_few);
        bijections.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });
    }
}
