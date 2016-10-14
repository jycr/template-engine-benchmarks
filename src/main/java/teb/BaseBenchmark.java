package teb;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import teb.model.Stock;
import teb.util.DoNothingWriter;

@Fork(0)
@Warmup(iterations = 3)
@Measurement(iterations = 3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public abstract class BaseBenchmark implements Runnable {

	public static final String TEMPLATE_DIR = "templates";

	private Writer output;
	private final Map<String, Object> params = new HashMap<>();

	@Param("stocks")
	private String templateName;

	@Setup
	public void init() {
		params.put("items", Stock.dummyItems());

		output = new DoNothingWriter();

		try {
			setup();
		} catch (final Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public Writer getOutput() {
		return output;
	}

	public abstract void setup() throws Exception;

	public String getTemplateName(final String templateSuffix) {
		return templateName + "." + templateSuffix;
	}

	public String getTemplatePath(final String templateSuffix) {
		return TEMPLATE_DIR + "/" + getTemplateName(templateSuffix);
	}

	public void setTemplateName(final String templateName) {
		this.templateName = templateName;
	}

	public void setOutput(final Writer output) {
		this.output = output;
	}

	public void test(final Writer writer) {
		setTemplateName("stocks");
		init();
		setOutput(writer);
		run();
	}

	public void test() {
		try (Writer console = new OutputStreamWriter(System.out)) {
			test(console);
		} catch (final IOException e) {
			throw new RuntimeException();
		}
	}
}