package stackoverflow;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

public class TestRoman {

    static final Map<Character, Integer> ROMANS = Map.of(
        'I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);

    public static int convertRomanToInteger(String roman) {
        int result = 0, cur = 0;
        for (char ch : roman.toCharArray()) {
            int next = ROMANS.get(ch);
            result += cur >= next ? cur : -cur;
            cur = next;
        }
        return result + cur;
    }

    @Test
    public void testRoman() {
        assertEquals(1, convertRomanToInteger("I"));
        assertEquals(2, convertRomanToInteger("II"));
        assertEquals(3, convertRomanToInteger("III"));
        assertEquals(4, convertRomanToInteger("IV"));
        assertEquals(5, convertRomanToInteger("V"));
        assertEquals(6, convertRomanToInteger("VI"));
        assertEquals(7, convertRomanToInteger("VII"));
        assertEquals(8, convertRomanToInteger("VIII"));
        assertEquals(9, convertRomanToInteger("IX"));
        assertEquals(10, convertRomanToInteger("X"));
        assertEquals(11, convertRomanToInteger("XI"));
        assertEquals(12, convertRomanToInteger("XII"));
        assertEquals(13, convertRomanToInteger("XIII"));
        assertEquals(14, convertRomanToInteger("XIV"));
        assertEquals(15, convertRomanToInteger("XV"));
        assertEquals(16, convertRomanToInteger("XVI"));
        assertEquals(17, convertRomanToInteger("XVII"));
        assertEquals(18, convertRomanToInteger("XVIII"));
        assertEquals(19, convertRomanToInteger("XIX"));
        assertEquals(1994, convertRomanToInteger("MCMXCIV"));
    }

    public static void sortedlist(int[] l, int r) {
        for (int i = 0, max = l.length; i < max; ++i)
            if (l[i] >= 0)
                for (int j = i + 1; j < max && j <= i + r; ++j)
                    l[i] += l[j];
    }
    
    @Test
    public void testContains() {
        int[] a = {1, 2, -3, 4, 5, 4};
        sortedlist(a, 3);
        System.out.println(Arrays.toString(a));
    }
}
