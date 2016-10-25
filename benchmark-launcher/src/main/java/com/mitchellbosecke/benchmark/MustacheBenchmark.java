package com.mitchellbosecke.benchmark;

import static com.mitchellbosecke.benchmark.model.ITemplate.DEFAULT_CHARSET;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.openjdk.jmh.annotations.Benchmark;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.github.mustachejava.resolver.ClasspathResolver;

public class MustacheBenchmark extends BaseBenchmark {

	private MustacheFactory mustacheFactory;
	private Mustache template;

	@Override
	public void setup() {
		mustacheFactory = new DefaultMustacheFactory(new ClasspathResolver());
		template = mustacheFactory.compile(getTemplatePath(".mustache"));
	}

	@Override
	@Benchmark
	public void run() {
		try (Writer writer = new OutputStreamWriter(getOutputStream(), DEFAULT_CHARSET)) {
			template.execute(writer, getContext());
		} catch (final IOException e) {
			new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new MustacheBenchmark().test();
	}
}