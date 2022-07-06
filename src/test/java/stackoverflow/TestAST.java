package stackoverflow;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class TestAST {
	
    // Checks if character is an operator
    public boolean isOperator(char c){
        switch (c){
            case '+':
            case '-':
            case'*':
            case'/':
            case'$':
                return true;
        }
        return false;
    }

    // Ignores white space
    public boolean isBlank(char c){
        switch (c){
        case ' ':
        return true;
        }
        return false;
    }

    // Method to convert Prefix expression to Postfix expression
    public String preToPost (String prefix_exp){

        // Create a new stack with length of the prefix string
        int size = prefix_exp.length();
        Stack<String> expression_stack = new Stack<>();

        // Read expression from right to left
        for (int i = size -1; i >=0 ; i-- ){

            if (isOperator(prefix_exp.charAt(i))){

                // Pop two operands from the stack
                String op1 = expression_stack.peek();
                expression_stack.pop();
                String op2 = expression_stack.peek();
                expression_stack.pop();

                // Concatenate the operands and the operator
                String temp = op1 + op2 + prefix_exp.charAt(i);

                // Push the result back onto the stack
                expression_stack.push(temp);
            }

            else if(isBlank(prefix_exp.charAt(i))){
                // Skip to next character
            }

            // If the symbol is an operand
            else {
                // Push the operand onto the stack
                expression_stack.push(prefix_exp.charAt(i) + "");
            }
        }

        return expression_stack.peek();
    }

	@Test
	public void testFlat() {
		System.out.println(preToPost("$ + - A B C  + D - EF"));
    }
}