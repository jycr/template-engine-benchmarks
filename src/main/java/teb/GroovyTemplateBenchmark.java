package teb;

import java.io.IOException;

import org.openjdk.jmh.annotations.Benchmark;

import groovy.text.GStringTemplateEngine;
import groovy.text.Template;
import teb.util.ClasspathResourceUtils;

/**
 * @see "http://docs.groovy-lang.org/latest/html/documentation/template-engines.html"
 */
public class GroovyTemplateBenchmark extends BaseBenchmark {

	private Template template;

	@Override
	public void setup() throws Exception {
		final GStringTemplateEngine engine = new GStringTemplateEngine();
		template = engine.createTemplate(
				ClasspathResourceUtils.getReader(
						getTemplatePath(".html.groovy")));
	}

	@Override
	@Benchmark
	public void run() {
		try {
			template
					.make(getParams())
					.writeTo(getOutput());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new GroovyTemplateBenchmark().test();
	}
}