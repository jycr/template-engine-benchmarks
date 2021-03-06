package com.mitchellbosecke.benchmark;

import static com.mitchellbosecke.benchmark.model.ITemplate.DEFAULT_CHARSET;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;

import org.openjdk.jmh.annotations.Benchmark;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.TemplateSpec;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class ThymeleafBenchmark extends BaseBenchmark {

	private TemplateEngine engine;
	private TemplateSpec template;

	@Override
	public void setup() {
		engine = new TemplateEngine();
		engine.setTemplateResolver(new ClassLoaderTemplateResolver(this.getClass().getClassLoader()));
		template = new TemplateSpec(getTemplatePath(".thymeleaf"), null, null, null);
	}

	@Override
	@Benchmark
	public void run() {
		final IContext ctx = new Context(Locale.getDefault(), getContext());
		try (Writer writer = new OutputStreamWriter(getOutputStream(), DEFAULT_CHARSET)) {
			engine.process(template, ctx, writer);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new ThymeleafBenchmark().test();
	}
}