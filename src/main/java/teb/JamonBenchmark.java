/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.io.IOException;
import java.util.List;

import teb.model.Stock;

public class JamonBenchmark extends BaseBenchmark {
	@Override
	public void setup() throws Exception {
	}

	@Override
	public void run() {
		final List<Stock> items = (List<Stock>) getParams().get("items");
		try {
			new jamon.stocks().render(getOutput(), items);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new JamonBenchmark().test();
	}
}