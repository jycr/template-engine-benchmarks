package com.mitchellbosecke.benchmark;

import static com.mitchellbosecke.benchmark.model.ITemplate.DEFAULT_CHARSET;
import static com.mitchellbosecke.benchmark.model.ITemplate.Template.responseXml;
import static com.mitchellbosecke.benchmark.model.ITemplate.Template.stocksHtml;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.openjdk.jmh.annotations.Benchmark;

import com.mitchellbosecke.benchmark.model.ITemplate.Template;

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
			final Template template = getTemplate();
			if (responseXml == template) {
				new response_xml().render(writer, getContextXmlResponse());
			} else if (stocksHtml == template) {
				new stocks_html().render(writer, getContextItems());
			} else {
				throw new IllegalArgumentException("Template Name not known: " + template);
			}
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new JamonBenchmark().test();
	}
}