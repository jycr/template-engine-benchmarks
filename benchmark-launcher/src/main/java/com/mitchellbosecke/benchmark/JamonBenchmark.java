package com.mitchellbosecke.benchmark;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.openjdk.jmh.annotations.Benchmark;

import templates.jamon.response_xml;
import templates.jamon.stocks_html;

/**
 * @see "http://www.jamon.org"
 */
public class JamonBenchmark extends BaseBenchmark {
	@Override
	public void setup() throws Exception {
	}

	@SuppressWarnings("unchecked")
	@Override
	@Benchmark
	public void run() {
		try (Writer writer = new OutputStreamWriter(getOutputStream(), DEFAULT_CHARSET)) {
			final String templateName = getTemplateName("");
			if (TEMPLATE_XML_RESPONSE.equals(templateName)) {
				new response_xml().render(writer, getContextXmlResponse());
			} else if (TEMPLATE_HTML_STOCKS.equals(templateName)) {
				new stocks_html().render(writer, getContextItems());
			} else {
				throw new IllegalArgumentException("Template Name not known: " + templateName);
			}
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new JamonBenchmark().test();
	}
}