package com.mitchellbosecke.benchmark;

import java.io.IOException;
import java.io.OutputStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import templates.twirl.html.stocks;
import templates.twirl.xml.response;

/**
 * Benchmark for Rocker template engine by Fizzed.
 *
 * https://github.com/fizzed/rocker
 */
public class TwirlBenchmark extends BaseBenchmark {

	@Override
	@Setup
	public void setup() throws IOException {
	}

	@Override
	@Benchmark
	public void run() {
		// final RockerOutputFactory<OutputStreamOutput> out = (contentType, charsetName) -> new OutputStreamOutput(
		// contentType,
		// getOutputStream(),
		// charsetName);

		final String templateName = getTemplateName("");
		try (OutputStream output = getOutputStream()) {
			if (TEMPLATE_XML_RESPONSE.equals(templateName)) {
				output.write(
						response.apply(
								getContextXmlResponse())
								.body()
								.getBytes(DEFAULT_CHARSET));
			} else if (TEMPLATE_HTML_STOCKS.equals(templateName)) {
				output.write(
						stocks.apply(
								getContextItems())
								.body()
								.getBytes(DEFAULT_CHARSET));
			} else {
				throw new IllegalArgumentException("Template Name not known: " + templateName);
			}
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new TwirlBenchmark().test();
	}
}