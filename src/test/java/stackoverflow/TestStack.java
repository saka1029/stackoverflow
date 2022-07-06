package stackoverflow;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntBinaryOperator;

import org.junit.Test;

public class TestStack {

	interface Executable {
		void execute(Context c);
	}

	static class Context {
		public int sp = 0;
		public final Executable[] stack = new Executable[100];
		public Executable pop() { return stack[--sp]; }
		public void push(Value v) { stack[sp++] = v; }
	}
	
	public static class Value implements Executable {
		@Override public void execute(Context c) { c.stack[c.sp++] = this; }
	}

	public static class Int extends Value {
		public final int value;
		Int(int value) { this.value = value; }
		public static Int of(int value) { return new Int(value); }
		@Override public String toString() { return "" + value; }
		@Override public int hashCode() { return value; }
		@Override public boolean equals(Object obj) { return obj instanceof Int i && value == i.value; }
	}
	
	public static class Block implements Executable {
		Executable[] elements;
		
		private Block(Executable[] elements) {
			this.elements = elements;
		}
		
		public static Block of(Executable... elements) {
			return new Block(elements);
		}
		
		@Override
		public void execute(Context c) {
			for (Executable e : elements)
				e.execute(c);
		}
		
		public static Builder builder() {
			return new Builder();
		}

		public static class Builder {
			List<Executable> list = new ArrayList<>();
			
			public Builder add(Executable... elements) {
				for (Executable e : elements)
                    list.add(e);
				return this;
			}
			public Block build() { return new Block(list.toArray(Executable[]::new)); }
		}
	}
	
	public static Block parse(String s) {
		Block.Builder builder = Block.builder();
		
		return builder.build();
	}

	public static Executable binary(IntBinaryOperator op) {
		return c -> {
            Executable right = c.pop(), left = c.pop();
            if (right instanceof Int r && left instanceof Int l)
                c.push(Int.of(op.applyAsInt(l.value, r.value)));
            else
                throw new UnsupportedOperationException();
		};
	}

	public static Executable EQ = binary((l, r) -> l == r ? 1 : 0);
	public static Executable NE = binary((l, r) -> l != r ? 1 : 0);
	public static Executable GT = binary((l, r) -> l > r ? 1 : 0);
	public static Executable GE = binary((l, r) -> l >= r ? 1 : 0);
	public static Executable LT = binary((l, r) -> l < r ? 1 : 0);
	public static Executable LE = binary((l, r) -> l <= r ? 1 : 0);
	public static Executable PLUS = binary((l, r) -> l + r);
	public static Executable MINUS = binary((l, r) -> l - r);
	public static Executable MULT = binary((l, r) -> l * r);
	public static Executable DIV = binary((l, r) -> l / r);
	public static Executable MOD = binary((l, r) -> l % r);

	public static Executable IF = c -> {
		Executable other = c.pop(), then = c.pop(), cond = c.pop();
		if (cond instanceof Int i && i.value != 0)
			then.execute(c);
		else
			other.execute(c);
	};

	public static Block block(Executable...executables) { return Block.of(executables); }
	public static Int i(int value) { return Int.of(value); }

	@Test
	public void testPlus() {
		Context c = new Context();
		Block block = block(i(1), i(2), PLUS);
		block.execute(c);
		Executable result = c.pop();
		assertEquals(0, c.sp);
		assertEquals(i(3), result);
	}

	@Test
	public void testIfThen() {
		Context c = new Context();
		Block block = block(i(1), i(2), i(3), IF);
		block.execute(c);
		Executable result = c.pop();
		assertEquals(0, c.sp);
		assertEquals(i(2), result);
	}

	@Test
	public void testIfElse() {
		Context c = new Context();
		Block block = block(i(0), i(2), i(3), IF);
		block.execute(c);
		Executable result = c.pop();
		assertEquals(0, c.sp);
		assertEquals(i(3), result);
	}

	@Test
	public void testIfThenBlock() {
		Context c = new Context();
		Block block = block(i(1), block(i(4), i(2), MINUS), block(i(3), i(3), PLUS), IF);
		block.execute(c);
		Executable result = c.pop();
		assertEquals(0, c.sp);
		assertEquals(i(2), result);
	}
}
