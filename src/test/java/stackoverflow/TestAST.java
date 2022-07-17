package stackoverflow;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestAST {
	
	/*
	 * syntax
	 * body   = name [ '(' [ body { ',' body } ] ')' ]
	 */
	static final Pattern PAT = Pattern.compile("[^(),]+|.");

	static void parse(String s) {
		new Object() {
            Matcher m = PAT.matcher(s);
            String token = get();
            String get() { return token = m.find() ? m.group() : null; }
            boolean isName() { return token != null && "(),".indexOf(token) < 0; }
            boolean eat(String s) {
            	if (!Objects.equals(token, s))
            		return false;
            	get();
            	return true;
            }
            void body() {
            	int start = m.start();
            	String name = token;
//            	System.out.println("name: " + token);
            	get(); //skip name
            	if (eat("(")) {
                    if (isName()) {
                        body();
                        while (eat(","))
                            body();
                    }
                    if (!eat(")"))
                        throw new RuntimeException(") expected");
            	}
                int end = token == null ? s.length() : m.start();
                System.out.println("body: " + s.substring(start, end));
            }
            void parse() {
            	if (!isName())
                    throw new RuntimeException("name expected");
                body();
            }
		}.parse();
	}
	
	public static void main(String[] args) {
		String s = "max(sum(1, max(1,2,sum(2,5)), 3, 5, mult(3,3)))";
		parse(s);
	}
}