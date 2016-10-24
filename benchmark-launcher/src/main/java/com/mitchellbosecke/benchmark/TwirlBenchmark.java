package com.mitchellbosecke.benchmark;

import java.io.IOException;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

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
		try {
			if (TEMPLATE_XML_RESPONSE.equals(templateName)) {
				// templates.xml.response
				// .template((XmlResponse) getContext().get("xmlResponse"))
				// .render(out)
				// .getStream()
				// .flush();
			} else if (TEMPLATE_HTML_STOCKS.equals(templateName)) {
				// stocks;
				// templates.html.stocks
				// .template((List<Stock>) getContext().get("items"))
				// .render(out)
				// .getStream()
				// .flush();
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