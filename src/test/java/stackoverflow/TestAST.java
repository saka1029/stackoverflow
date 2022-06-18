package stackoverflow;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class TestAST {

	interface AST { AST expand(); }

	public class Var implements AST {
		public final String name;
		public Var(String name) { this.name = name; }
		@Override public AST expand() { return this; }
		@Override public String toString() { return name; }
	}

	public abstract class Binary implements AST {
		public final AST left, right;
		Binary(AST left, AST right) { this.left = left; this.right = right; }
	}

	public class Plus extends Binary {
		public Plus(AST left, AST right) { super(left, right); }
		@Override public AST expand() { return this; }
		@Override public String toString() { return "(%s + %s)".formatted(left, right); }
	}

	public class Mult extends Binary {
		public Mult(AST left, AST right) { super(left, right); }
		@Override
		public AST expand() {
			AST l = left.expand(), r = right.expand();
			if (l instanceof Plus lp && r instanceof Plus rp)
				return new Plus(
					new Plus(new Mult(lp.left, rp.left), new Mult(lp.left, rp.right)),
					new Plus(new Mult(lp.right, rp.left), new Mult(lp.right, rp.right)));
			else if (l instanceof Plus lp)
				return new Plus(new Mult(lp.left, r), new Mult(lp.right, r));
			else if (r instanceof Plus rp)
				return new Plus(new Mult(l, rp.left), new Mult(l, rp.right));
			else 
				return new Mult(l, r);
		}
		@Override public String toString() { return "(%s * %s)".formatted(left, right); }
	}

	@Test
	public void test() {
		AST e = new Mult(new Var("a"), new Plus(new Var("b"), new Var("c")));
		System.out.println(e + " -> " + e.expand());
		AST f = new Mult(new Plus(new Var("a"), new Var("b")), new Plus(new Var("c"), new Var("d")));
		System.out.println(f + " -> " + f.expand());
	}
}
