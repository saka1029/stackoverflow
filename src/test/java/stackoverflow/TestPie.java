package stackoverflow;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

import org.junit.Test;

public class TestPie {

    public enum Category {
        Thing,
        Thang,
        Fizz
    }

//    @Data // using lombok to generate ctors/getters/setters/etc.
    public class LineItem {

        @Override
        public String toString() {
            return "LineItem [category=" + category + ", amount=" + amount + "]";
        }

        private Long id;
        private String name;
        private Category category;
        private BigDecimal amount;

        public Category getCategory() {
            return category;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        LineItem(Long id, String name, Category category, BigDecimal amount) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.amount = amount;
        }

    }

//    @Data
    public class PieSlice {

        @Override
        public String toString() {
            return "PieSlice [label=" + label + ", value=" + value + "]";
        }

        private String label;
        private BigDecimal value = BigDecimal.ZERO;

        PieSlice() {
        }

        PieSlice(BigDecimal value) {
            this.value = value;
        }

        public void addAmount(BigDecimal amount) {
            value = value.add(amount);
        }

    }

//    @Test
    public void test() {
        List<LineItem> lineItems = List.of(
            new LineItem(1L, "", Category.Thing, BigDecimal.valueOf(100)),
            new LineItem(2L, "", Category.Thang, BigDecimal.valueOf(200)),
            new LineItem(3L, "", Category.Fizz, BigDecimal.valueOf(300)),
            new LineItem(4L, "", Category.Thing, BigDecimal.valueOf(400)));
        Map<Category, PieSlice> sliceMap = lineItems.stream()
            .collect(
                groupingBy(LineItem::getCategory,
                    mapping(LineItem::getAmount,
                        collectingAndThen(
                            reducing(BigDecimal.ZERO, BigDecimal::add),
                            PieSlice::new))));
        sliceMap.entrySet().stream()
            .forEach(System.out::println);
    }

//@Test
    public void testSum() {
        List<BigDecimal> list = List.of(
            BigDecimal.valueOf(1),
            BigDecimal.valueOf(2),
            BigDecimal.valueOf(3),
            BigDecimal.valueOf(4));
        var sum = list.stream()
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sum);
    }

    record Node<E> (E getData, Node<E> getLeft, Node<E> getRight) {
    }

    static <E> void inrec(Node<E> root, String prefix, String suffix) {
        if (root == null)
            return;
        System.out.print(prefix);
        inrec(root.getLeft(), "", " ");
        System.out.print(root.getData());
        inrec(root.getRight(), " ", "");
        System.out.print(suffix);
    }

    static void add(Map<String, Set<String>> map, String key, String value) {
        map.computeIfAbsent(key, k -> new HashSet<>()).add(value);
    }

    static Pattern PAT = Pattern.compile("\\$");

    static String replaceWords(String s, String[] stickers) {
        Iterator<String> it = Arrays.stream(stickers).iterator();
        return PAT.matcher(s).replaceAll(m -> it.next());
    }

    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

    public static void displayHours(byte[] hours) {
        final String[] DAYS = DateFormatSymbols.getInstance().getWeekdays();    
        BigInteger bi = new BigInteger(hours);
        
        for (int day = 1; day <= 7; day++) {
            System.out.printf("Hours allowed for %s%n", DAYS[day]);
            for (int hour = 0; hour <= 24; hour++) {
                int bit = day * 7 + hour;
                boolean allowed = bi.testBit(bit);
                System.out.printf("\tLogin permitted for hour %d?: %b%n", hour, allowed);
            }
        }
    }
    @Test
    public void testFoo() {
        byte[] hours = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1}; 
        System.out.println("size=" + hours.length);
        System.out.println(bytesToHex(hours));
        displayHours(hours);
    }

}