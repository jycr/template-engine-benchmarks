package com.mitchellbosecke.benchmark;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

import com.mitchellbosecke.benchmark.model.ITemplate;
import com.mitchellbosecke.benchmark.model.ITemplate.Template;
import com.mitchellbosecke.benchmark.model.Stock;
import com.mitchellbosecke.benchmark.model.XmlResponse;
import com.mitchellbosecke.benchmark.util.ClasspathResourceUtils;
import com.mitchellbosecke.benchmark.util.DoNothingOutputStream;

@Fork(0)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public abstract class BaseBenchmark implements Runnable {
	public static final String TEMPLATE_DIR = "templates";

	private final ClasspathResourceUtils classpathResourceUtils = new ClasspathResourceUtils(this.getClass().getClassLoader());

	private OutputStream output;
	private final Map<String, Object> context = new HashMap<>();

	@Param({ ITemplate.STOCKS_HTML, ITemplate.RESPONSE_XML })
	private String benchmarkTemplatePath;

	private Template template;

	@Setup
	public void init() {
		Locale.setDefault(Locale.ENGLISH);
		context.clear();
		context.putAll(ITemplate.createDummyContext(getTemplate()));

		output = new DoNothingOutputStream();

		try {
			setup();
		} catch (final Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public Map<String, Object> getContext() {
		return context;
	}

	public XmlResponse getContextXmlResponse() {
		return (XmlResponse) getContext().get(ITemplate.PAGE_ATTRIBUTE_XMLRESPONSE);
	}

	public List<Stock> getContextItems() {
		return (List<Stock>) getContext().get(ITemplate.PAGE_ATTRIBUTE_ITEMS);
	}

	public OutputStream getOutputStream() {
		return output;
	}

	public abstract void setup() throws Exception;

	public Template getTemplate() {
		if ((template == null) && (benchmarkTemplatePath != null)) {
			for (final Template t : Template.values()) {
				if (benchmarkTemplatePath.equals(t.getPath())) {
					template = t;
					break;
				}
			}
		}
		return template;
	}

	public String getTemplateName(final String templateSuffix) {
		return getTemplate().getPath() + templateSuffix;
	}

	public String getTemplatePath(final String templateSuffix) {
		return TEMPLATE_DIR + "/" + getTemplateName(templateSuffix);
	}

	public Reader getTemplateReader(final String templateSuffix) throws FileNotFoundException {
		return classpathResourceUtils.getReader(getTemplatePath(templateSuffix));
	}

	public String getTemplateAsString(final String templateSuffix) throws IOException {
		return classpathResourceUtils.getAsString(getTemplatePath(templateSuffix));
	}

	public void setTemplate(final Template template) {
		this.template = template;
	}

	public void setOutputStream(final OutputStream output) {
		this.output = output;
	}

	public void test(final Template template, final OutputStream output) throws IOException {
		setTemplate(template);
		init();
		setOutputStream(output);
		run();
		output.flush();
	}

	public void test() {
		try {
			for (final Template t : Template.values()) {
				test(t, System.out);
				System.out.flush();
			}
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}