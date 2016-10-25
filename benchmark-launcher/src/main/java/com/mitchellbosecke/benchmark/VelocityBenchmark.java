package com.mitchellbosecke.benchmark;

import static com.mitchellbosecke.benchmark.model.ITemplate.DEFAULT_CHARSET;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map.Entry;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.openjdk.jmh.annotations.Benchmark;

public class VelocityBenchmark extends BaseBenchmark {

	private VelocityEngine _engine;
	private Template template;

	@Override
	public void setup() {
		_engine = new VelocityEngine();
		_engine.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");

		_engine.setProperty("input.encoding", "UTF-8");
		_engine.setProperty("output.encoding", "UTF-8");

		_engine.setProperty("resource.loader", "classpath");
		_engine.setProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		try {
			_engine.init();
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}
		template = _engine.getTemplate(getTemplatePath(".vm"));
	}

	@Override
	@Benchmark
	public void run() {
		/// create context data
		final VelocityContext context = new VelocityContext();
		for (final Entry<String, Object> e : getContext().entrySet()) {
			context.put(e.getKey(), e.getValue());
		}
		try (Writer writer = new OutputStreamWriter(getOutputStream(), DEFAULT_CHARSET)) {
			template.merge(context, writer);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new VelocityBenchmark().test();
	}
}