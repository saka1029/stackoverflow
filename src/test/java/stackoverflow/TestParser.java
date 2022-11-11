package stackoverflow;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * <pre>
 * program → statements
 * statements→ statement | statementsemi_colonstatements
 * statement → identassignment_opexpression
 * expression → termterm_tail
 * term_tail → add_optermterm_tail | ε
 * term → factor factor_tail
 * factor_tail → mult_opfactorfactor_tail | ε
 * factor → left_parenexpressionright_paren | ident | const
 * const → any decimal numbers
 * ident → any names conforming to C identifier rules
 * assignment_op → :=
 * semi_colon → ;
 * add_operator → + | -
 * mult_operator → * | /
 * left_paren → (
 * right_paren →)
 * </pre>
 *
 */
public class TestParser {

    static Map<String, Double> parse(String source) {
        return new Object() {
            int index, nextIndex = 0, ch;
            Map<String, Double> variables = new HashMap<>();

            int get() {
                index = nextIndex;
                return ch = nextIndex < source.length() ? source.charAt(nextIndex++) : -1;
            }

            void spaces() { while (Character.isWhitespace(ch)) get(); }

            boolean eat(int expect) {
                spaces();
                if (ch == expect) {
                    get();
                    return true;
                }
                return false;
            }

            void integer() { while (Character.isDigit(ch)) get(); }

            double factor() {
                if (eat('(')) {
                    double e = expression();
                    if (!eat(')'))
                        throw new RuntimeException("')' expected");
                    return e;
                } else if (Character.isLetter(ch)) {
                    int start = index;
                    do {
                        get();
                    } while (Character.isLetterOrDigit(ch));
                    String name = source.substring(start, index);
                    if (!variables.containsKey(name))
                        throw new RuntimeException("variable '" + name + "' undefined");
                    return variables.get(name);
                } else if (ch == '-' || Character.isDigit(ch)) {
                    int start = index;
                    get();
                    integer();
                    if (eat('.'))
                        integer();
                    if (eat('E') || eat('e')) {
                        eat('-');
                        integer();
                    }
                    return Double.parseDouble(source.substring(start, index));
                } else
                    throw new RuntimeException("unknown char '" + ((char) ch) + "'");
            }

            double term() {
                double e = factor();
                while (true)
                    if (eat('*'))
                        e *= factor();
                    else if (eat('/'))
                        e /= factor();
                    else
                        break;
                return e;
            }

            double expression() {
                double e = term();
                while (true)
                    if (eat('+'))
                        e += term();
                    else if (eat('-'))
                        e -= term();
                    else
                        break;
                return e;
            }

            void statement() {
                spaces();
                if (!Character.isLetter(ch))
                    throw new RuntimeException("ident expected");
                int start = index;
                do {
                    get();
                } while (Character.isLetterOrDigit(ch));
                String name = source.substring(start, index);
                if (!eat(':') || !eat('='))
                    throw new RuntimeException("':=' expected");
                variables.put(name, expression());
            }

            void statements() {
                do {
                    statement();
                } while (eat(';'));
            }

            Map<String, Double> parse() {
                get();
                statements();
                if (ch != -1)
                    throw new RuntimeException("extra string '" + source.substring(index) + "'");
                return variables;
            }
        }.parse();
    }

    @Test
    public void testParser() {
        Map<String, Double> variables = parse("x1 := 1 + 2.4 * 3; y := x1 + 3; z := 1 / -3");
        System.out.println(variables);
    }
}
