package com.mitchellbosecke.benchmark;

import static com.mitchellbosecke.benchmark.model.ITemplate.DEFAULT_CHARSET;

import java.io.IOException;
import java.io.OutputStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import com.mitchellbosecke.benchmark.model.ITemplate.Template;

import templates.twirl.html.stocks;
import templates.twirl.xml.response;

public class TwirlBenchmark extends BaseBenchmark {
	@Override
	@Setup
	public void setup() throws IOException {
	}

	@Override
	@Benchmark
	public void run() {
		final Template template = getTemplate();
		try (OutputStream output = getOutputStream()) {
			if (template == Template.responseXml) {
				output.write(
						response.apply(
								getContextXmlResponse())
								.body()
								.getBytes(DEFAULT_CHARSET));
			} else if (template == Template.stocksHtml) {
				output.write(
						stocks.apply(
								getContextItems())
								.body()
								.getBytes(DEFAULT_CHARSET));
			} else {
				throw new IllegalArgumentException("Template Name not known: " + template);
			}
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new TwirlBenchmark().test();
	}
}