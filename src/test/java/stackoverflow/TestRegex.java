package stackoverflow;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.Test;

public class TestRegex {

    static void sieve(boolean[] primes, int max, int k) {
        for (int i = k + k; i < max; i += k)
            primes[i] = true;
    }

    static void primes(boolean[] primes) {
        int max = primes.length;
        primes[0] = primes[1] = true;
        sieve(primes, max, 2);
        for (int k = 3; k < max; k += 2)
            sieve(primes, max, k);
    }

    @Test
    public void testPrimes() {
//        int MAX = 1_000;
        int MAX = 1_000_000;
        boolean[] primes = new boolean[MAX];
        long start = System.currentTimeMillis();
        primes(primes);
        System.out.println(System.currentTimeMillis() - start + "ms");
//        for (int i = 0; i < MAX; ++i)
//            if (!primes[i])
//                System.out.print(i + " ");
    }

}
