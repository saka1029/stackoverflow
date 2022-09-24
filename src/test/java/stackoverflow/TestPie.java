package stackoverflow;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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

        PieSlice() {}
        PieSlice(BigDecimal value) { this.value = value; }
        public void addAmount(BigDecimal amount) {
            value = value.add(amount);
        }

    }
    @Test
    public void test() {
    List<LineItem> lineItems = List.of(
        new LineItem(1L, "", Category.Thing, BigDecimal.valueOf(100)),
        new LineItem(2L, "", Category.Thang, BigDecimal.valueOf(200)),
        new LineItem(3L, "", Category.Fizz, BigDecimal.valueOf(300)),
        new LineItem(4L, "", Category.Thing, BigDecimal.valueOf(400))
    );
    Map<Category, PieSlice> sliceMap = lineItems.stream()
        .collect(
            groupingBy(LineItem::getCategory,
                mapping(LineItem::getAmount,
                    collectingAndThen(
                        reducing(BigDecimal.ZERO, BigDecimal::add),
                        PieSlice::nejw))));
    sliceMap.entrySet().stream()
        .forEach(System.out::println);
}

//@Test
public void testSum() {
    List<BigDecimal> list = List.of(
         BigDecimal.valueOf(1),
         BigDecimal.valueOf(2),
         BigDecimal.valueOf(3),
         BigDecimal.valueOf(4)
    );
    var sum = list.stream()
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    System.out.println(sum);
}

}
