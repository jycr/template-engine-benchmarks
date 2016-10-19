/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package com.mitchellbosecke.benchmark;

import java.io.IOException;
import java.util.Properties;

import org.openjdk.jmh.annotations.Benchmark;
import org.rythmengine.RythmEngine;

public class RythmBenchmark extends BaseBenchmark {

	private RythmEngine engine;
	private String template;

	@Override
	public void setup() throws IOException {
		final Properties p = new Properties();
		p.put("log.enabled", false);
		p.put("feature.smart_escape.enabled", false);
		p.put("feature.transform.enabled", false);
		engine = new RythmEngine(p);
		template = getTemplateAsString(".rythm");
	}

	protected void shutdown() {
		engine.shutdown();
	}

	@Override
	@Benchmark
	public void run() {
		engine.render(getOutput(), template, getContext());
	}

	public static void main(final String[] args) {
		new RythmBenchmark().test();
	}
}