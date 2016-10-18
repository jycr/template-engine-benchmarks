package teb;

import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlunit.builder.Input;
import org.xmlunit.matchers.CompareMatcher;

import teb.util.ClasspathResourceUtils;

public class StocksTemplateTest {
	private static final Logger LOG = LoggerFactory.getLogger(StocksTemplateTest.class);

	private final String templateName = "stocks";

	private Source expected;

	@Before
	public void init() throws IOException {
		expected = Input.from(ClasspathResourceUtils.getAsString(BaseBenchmark.TEMPLATE_DIR + "/" + templateName + ".html")).build();
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
	public void testBeetlBenchmark() throws Exception {
		test(new BeetlBenchmark());
	}

	@Test
	public void testRythmBenchmark() throws Exception {
		test(new RythmBenchmark());
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
	public void testJmteBenchmark() throws Exception {
		test(new JmteBenchmark());
	}

	@Test
	public void testMustacheBenchmark() throws Exception {
		test(new MustacheBenchmark());
	}

	@Test
	public void testJMustacheBenchmark() throws Exception {
		test(new JMustacheBenchmark());
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
		final StringWriter output = new StringWriter();
		b.test(templateName, output);
		final String reason = templateName + " with " + b.getClass().getSimpleName();
		final String result = output.toString();
		LOG.info("{}. Output:\n{}", reason, result);

		try {
			assertThat(
					reason,
					result,
					CompareMatcher
							.isSimilarTo(
									expected)
							.normalizeWhitespace());
		} catch (final Exception e) {
			throw new Exception("Exception when test: " + reason + "\n" + result, e);
		}
	}

	public void testTwice(final BaseBenchmark b) throws Exception {
		final StringWriter output = new StringWriter();
		b.setTemplateName(templateName);
		b.init();
		b.setOutput(output);
		b.run();
		b.run();
	}

}