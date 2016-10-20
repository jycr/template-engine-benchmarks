package com.mitchellbosecke.benchmark;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlunit.builder.Input;
import org.xmlunit.matchers.CompareMatcher;

import com.mitchellbosecke.benchmark.util.ClasspathResourceUtils;

@RunWith(Parameterized.class)
public class ExpectedOutputTest {
	private static final Logger LOG = LoggerFactory.getLogger(ExpectedOutputTest.class);

	private final String templateName;

	private Source expected;

	@Parameters
	public static Collection<String> templateNames() {
		return Arrays.asList("html/stocks.html", "xml/response.xml");
	}

	@BeforeClass
	public static void beforeClass() {
		Locale.setDefault(Locale.ENGLISH);
	}

	public ExpectedOutputTest(final String templateName) {
		this.templateName = templateName;
	}

	@Before
	public void init() throws IOException {
		expected = Input.from(new ClasspathResourceUtils(getClass().getClassLoader()).getAsString(BaseBenchmark.TEMPLATE_DIR + "/" + templateName)).build();
	}

	@Test
	public void testRockerBenchmark() throws Exception {
		test(new RockerBenchmark());
	}

	@Test
	public void testJasperBenchmark() throws Exception {
		test(new JasperBenchmark());
	}

	@Test
	public void testJasperXBenchmark() throws Exception {
		test(new JasperXBenchmark());
	}

	@Test
	public void testTrimouBenchmark() throws Exception {
		test(new TrimouBenchmark());
	}

	@Test
	public void testXsltBenchmark() throws Exception {
		test(new XsltBenchmark());
	}

	@Test
	public void testGroovyTemplateBenchmark() throws Exception {
		test(new GroovyTemplateBenchmark());
	}

	@Test
	public void testJavaNativeBenchmark() throws Exception {
		test(new JavaNativeBenchmark());
	}

	@Test
	public void testFreemarkerBenchmark() throws Exception {
		test(new FreemarkerBenchmark());
	}

	@Test
	public void testVelocityBenchmark() throws Exception {
		test(new VelocityBenchmark());
	}

	@Test
	public void testJamonBenchmark() throws Exception {
		test(new JamonBenchmark());
	}

	@Test
	public void testHttlBenchmark() throws Exception {
		test(new HttlBenchmark());
	}

	@Test
	public void testMustacheBenchmark() throws Exception {
		test(new MustacheBenchmark());
	}

	@Test
	public void testHandlebarsBenchmark() throws Exception {
		test(new HandlebarsBenchmark());
	}

	@Test
	public void testThymeleafBenchmark() throws Exception {
		test(new ThymeleafBenchmark());
	}

	public void test(final BaseBenchmark b) throws Exception {
		testSingle(b);
		testTwice(b);
	}

	public void testSingle(final BaseBenchmark b) throws Exception {
		String reason = null;
		String result = null;
		try (final ByteArrayOutputStream output = new ByteArrayOutputStream()) {
			b.test(templateName, output);
			output.flush();
			output.close();
			result = output.toString(BaseBenchmark.DEFAULT_CHARSET.toString());
		} catch (final Exception e) {
			throw new Exception("Exception when test: " + reason + "\n" + result, e);
		}
		assertNotNull(result);
		assertFalse(result.isEmpty());
		reason = templateName + " with " + b.getClass().getSimpleName();
		LOG.debug("{}. Output:\n{}", reason, result);
		assertThat(
				reason,
				result,
				CompareMatcher
						.isSimilarTo(expected)
						.normalizeWhitespace());
	}

	public void testTwice(final BaseBenchmark b) throws Exception {
		final ByteArrayOutputStream output = new ByteArrayOutputStream();
		b.setTemplateName(templateName);
		b.init();
		b.setOutputStream(output);
		b.run();
		b.run();
	}
}