/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.io.IOException;
import java.util.List;

import org.openjdk.jmh.annotations.Benchmark;

import teb.model.Stock;
import templates.stocks;

/**
 * @see "http://www.jamon.org"
 */
public class JamonBenchmark extends BaseBenchmark {
	private stocks template;

	@Override
	public void setup() throws Exception {
		template = new templates.stocks();
	}

	@Override
	@Benchmark
	public void run() {
		@SuppressWarnings("unchecked")
		final List<Stock> items = (List<Stock>) getParams().get("items");
		try {
			template.render(getOutput(), items);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new JamonBenchmark().test();
	}
}