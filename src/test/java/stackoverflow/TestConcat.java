package stackoverflow;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class TestConcat {

    public static List<String> sortOrders(List<String> orderList) {
        return orderList.stream()
            .map(str -> new Object() {
                String org = str;
                String key;
                {
                    key = str.replaceFirst("^.* ", "");
                    if (key.matches(".*\\d.*")) key = "\uffff";
                }})
            .sorted(Comparator.comparing(obj -> obj.key))
            .map(obj -> obj.org)
            .toList();
    }

    public static List<String> sortOrders0(List<String> orderList) {
        // Write your code here
       Collections.sort( orderList,
       (a, b) -> a.split(" *", 2)[1].compareTo( b.split(" *", 2)[1] )
       );
       
       return orderList;
    }

    @Test
    public void test1() {
    List<String> list = Arrays.asList(
        "19th apple orange",
        "17th admin 7th",
        "19th apple table");
    System.out.println(sortOrders(list));
}

}