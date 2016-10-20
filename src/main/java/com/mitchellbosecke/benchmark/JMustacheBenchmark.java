package com.mitchellbosecke.benchmark;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.openjdk.jmh.annotations.Benchmark;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

public class JMustacheBenchmark extends BaseBenchmark {

	private Mustache.Compiler compiler;
	private Template template;

	@Override
	public void setup() throws FileNotFoundException {
		compiler = Mustache.compiler();
		template = compiler.compile(getTemplateReader(".mustache"));
	}

	@Override
	@Benchmark
	public void run() {
		try (Writer writer = new OutputStreamWriter(getOutputStream(), DEFAULT_CHARSET)) {
			template.execute(getContext(), writer);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new JMustacheBenchmark().test();
	}
}