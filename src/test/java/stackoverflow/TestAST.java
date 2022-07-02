package stackoverflow;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class TestAST {
	
	@Test
	public void testFlat() {
        int a = 2, b = 3, c = a * b;
        System.out.println("c=" + c);
        int row = 5;
        for(int out = 1; out <= row; out++) {
            for(int in = 1; in <= out; in++) {
                System.out.print(c + " ");
                c = a * b;
                a = b;
                b = c;
            }
            System.out.println();
        }
    }
}