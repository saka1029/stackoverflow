package stackoverflow;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestSort {

	static class Params {}

	static class ConcreteParams extends Params {}

	interface Processor<T extends Params> {
	    void process(T params);
	}

	static class ConcreteProcessor<T extends Params> implements Processor<T> {
	    public void process(final Params params) { }
	}

	static class ProcessorFactory {
	    public Processor<Params> get() {
	        return new ConcreteProcessor<>();
	        // Error: java: incompatible types: ConcreteProcessor cannot be converted to Processor<T>
	    }
	}
	@Test
	public void test() {
		Processor<Params> p = new ProcessorFactory().get();
    }
}
