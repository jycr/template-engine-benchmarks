package com.mitchellbosecke.benchmark;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.openjdk.jmh.annotations.Benchmark;

import com.floreysoft.jmte.Engine;

public class JmteBenchmark extends BaseBenchmark {

	private Engine engine;
	private String tmpl;

	@Override
	public void setup() throws Exception {
		engine = new Engine();
		tmpl = getTemplateAsString(".jmte");
	}

	@Override
	@Benchmark
	public void run() {
		try {
			IOUtils.write(engine.transform(tmpl, getContext()), getOutputStream());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new JmteBenchmark().test();
	}
}