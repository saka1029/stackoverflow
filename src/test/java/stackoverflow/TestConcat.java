package stackoverflow;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class TestConcat {

    @Test
    public void test1() {
        String[] column1Array = "あああ いいい ううう".split(" ");
        String[] column2Array = "えええ おおお".split(" ");
        List<String> list = Stream.concat(
                Arrays.stream(column1Array),
                Arrays.stream(column2Array))
            .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void test2() {
        String[] column1Array = "あああ いいい ううう".split(" ");
        String[] column2Array = "えええ おおお".split(" ");
        String[] column3Array = "かかか ききき".split(" ");
        List<String> list = Stream.of(
                column1Array,
                column2Array,
                column3Array)
            .flatMap(array -> Arrays.stream(array))
            .collect(Collectors.toList());
        System.out.println(list);
    }

}
