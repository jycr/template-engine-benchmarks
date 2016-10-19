package com.mitchellbosecke.benchmark;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
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

import com.mitchellbosecke.benchmark.model.Status;
import com.mitchellbosecke.benchmark.model.Stock;
import com.mitchellbosecke.benchmark.model.XmlResponse;
import com.mitchellbosecke.benchmark.util.ClasspathResourceUtils;
import com.mitchellbosecke.benchmark.util.DoNothingWriter;

@Fork(2)
@Warmup(iterations = 6)
@Measurement(iterations = 6)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public abstract class BaseBenchmark implements Runnable {
	public static final String TEMPLATE_HTML_STOCKS = "html/stocks.html";
	public static final String TEMPLATE_XML_RESPONSE = "xml/response.xml";

	public static final String TEMPLATE_DIR = "templates";

	private final ClasspathResourceUtils classpathResourceUtils = new ClasspathResourceUtils(this.getClass().getClassLoader());

	private Writer output;
	private final Map<String, Object> context = new HashMap<>();

	@Param({ TEMPLATE_HTML_STOCKS, TEMPLATE_XML_RESPONSE })
	private String templateName;

	@Setup
	public void init() {
		if (TEMPLATE_HTML_STOCKS.equals(templateName)) {
			context.put("items", Stock.dummyItems());
		}
		if (TEMPLATE_XML_RESPONSE.equals(templateName)) {
			final XmlResponse xmlResponse = new XmlResponse();
			xmlResponse.setUuid(UUID.fromString("8bb080f3-d384-49a8-b372-7ddffb8c5b33"));
			xmlResponse.setOrgUuid(UUID.fromString("89697e5a-15b4-4d32-b37f-ad17f6ae0fdf"));
			xmlResponse.setLastModified(ZonedDateTime.of(2016, 10, 18, 22, 28, 33, 826000000, ZoneId.from(ZoneOffset.ofHoursMinutes(1, 0))));
			xmlResponse.setStatus(Status.OK);
			context.put("xmlResponse", xmlResponse);
		}

		output = new DoNothingWriter();

		try {
			setup();
		} catch (final Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public Map<String, Object> getContext() {
		return context;
	}

	public Writer getOutput() {
		return output;
	}

	public abstract void setup() throws Exception;

	public String getTemplateName(final String templateSuffix) {
		return templateName + templateSuffix;
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

	public void setTemplateName(final String templateName) {
		this.templateName = templateName;
	}

	public void setOutput(final Writer output) {
		this.output = output;
	}

	public void test(final String templateName, final Writer writer) throws IOException {
		setTemplateName(templateName);
		init();
		setOutput(writer);
		run();
		writer.flush();
	}

	public void test() {
		try {
			test(TEMPLATE_HTML_STOCKS, new OutputStreamWriter(System.out));
			test(TEMPLATE_XML_RESPONSE, new OutputStreamWriter(System.out));
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}