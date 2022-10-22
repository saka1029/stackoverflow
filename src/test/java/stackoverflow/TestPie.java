package stackoverflow;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class TestPie {

    public List<List<Integer>> getAscendingSequences(List<Integer> numbers) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> longestArray = new ArrayList<>();
        List<Integer> currentArray = new ArrayList<>();
        for (int i = 1; i < numbers.size(); i++) {
            if (currentArray.isEmpty()) {
                currentArray.add(numbers.get(i - 1));
            }
            if (numbers.get(i) > numbers.get(i - 1)) {
                currentArray.add(numbers.get(i));
            } else {
                if (longestArray.size() < currentArray.size()) {
                    longestArray.clear();
                    longestArray.addAll(currentArray);
                }
                currentArray.clear();
            }
        }
        results.add(longestArray);
        return results;
    }
    
    @Test
    public void test() {
        var list = getAscendingSequences(List.of(1,2,1,3,7,1,2,3));
        System.out.println(list);
    }
}