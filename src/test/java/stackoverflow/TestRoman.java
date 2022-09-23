package stackoverflow;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

public class TestRoman {

    static final Map<Character, Integer> ROMANS = Map.of(
        'I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);

    public static int convertRomanToInteger(String roman) {
        int r = 0, c = 0, n;
        for (char ch : roman.toCharArray()) {
            n = ROMANS.get(ch);
            r += c >= n ? c : -c;
            c = n;
        }
        return r + c;
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
}