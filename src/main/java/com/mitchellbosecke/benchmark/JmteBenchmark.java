/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
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
			IOUtils.write(engine.transform(tmpl, getContext()), getOutput());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new JmteBenchmark().test();
	}
}