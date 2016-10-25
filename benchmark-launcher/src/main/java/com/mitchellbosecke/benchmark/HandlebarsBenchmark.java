package com.mitchellbosecke.benchmark;

import static com.mitchellbosecke.benchmark.model.ITemplate.DEFAULT_CHARSET;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.openjdk.jmh.annotations.Benchmark;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.cache.ConcurrentMapTemplateCache;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

public class HandlebarsBenchmark extends BaseBenchmark {
	private Handlebars handlebars;
	private Template template;

	@Override
	public void setup() throws IOException {
		final TemplateLoader templateLoader = new ClassPathTemplateLoader();
		templateLoader.setPrefix("/" + TEMPLATE_DIR + "/");
		templateLoader.setSuffix("");
		handlebars = new Handlebars(templateLoader).with(new ConcurrentMapTemplateCache());
		// Handlebars is largely compatible with Mustache templates
		template = handlebars.compile(getTemplateName(".mustache"));
	}

	@Override
	@Benchmark
	public void run() {
		try (Writer writer = new OutputStreamWriter(getOutputStream(), DEFAULT_CHARSET)) {
			template.apply(getContext(), writer);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new HandlebarsBenchmark().test();
	}
}