package com.mitchellbosecke.benchmark;

import java.io.IOException;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import com.fizzed.rocker.RenderingException;
import com.fizzed.rocker.RockerOutputFactory;
import com.fizzed.rocker.runtime.OutputStreamOutput;

import templates.rocker.response;
import templates.rocker.stocks;

/**
 * Benchmark for Rocker template engine by Fizzed.
 *
 * https://github.com/fizzed/rocker
 */
public class RockerBenchmark extends BaseBenchmark {

	@Override
	@Setup
	public void setup() throws IOException {
	}

	@Override
	@Benchmark
	public void run() {
		final RockerOutputFactory<OutputStreamOutput> out = (contentType, charsetName) -> new OutputStreamOutput(
				contentType,
				getOutputStream(),
				charsetName);

		final String templateName = getTemplateName("");
		try {
			if (TEMPLATE_XML_RESPONSE.equals(templateName)) {
				response
						.template(getContextXmlResponse())
						.render(out)
						.getStream()
						.flush();
			} else if (TEMPLATE_HTML_STOCKS.equals(templateName)) {
				stocks
						.template(getContextItems())
						.render(out)
						.getStream()
						.flush();
			} else {
				throw new IllegalArgumentException("Template Name not known: " + templateName);
			}
		} catch (RenderingException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new RockerBenchmark().test();
	}
}