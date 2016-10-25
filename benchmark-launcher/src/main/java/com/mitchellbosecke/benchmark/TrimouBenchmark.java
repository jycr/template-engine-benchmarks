/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package com.mitchellbosecke.benchmark;

import java.io.PrintStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.trimou.Mustache;
import org.trimou.engine.MustacheEngine;
import org.trimou.engine.MustacheEngineBuilder;
import org.trimou.engine.locator.ClassPathTemplateLocator;

public class TrimouBenchmark extends BaseBenchmark {

	private Mustache template;

	@Override
	public void setup() {
		final MustacheEngine engine = MustacheEngineBuilder
				.newBuilder()
				.addTemplateLocator(new ClassPathTemplateLocator(1, TEMPLATE_DIR, "mustache", this.getClass().getClassLoader(), true))
				.build();
		template = engine.getMustache(getTemplateName(""));
	}

	@Override
	@Benchmark
	public void run() {
		template.render(new PrintStream(getOutputStream()), getContext());
	}

	public static void main(final String[] args) {
		new TrimouBenchmark().test();
	}
}