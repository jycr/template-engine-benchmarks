/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.io.IOException;

import javax.servlet.ServletException;

import org.openjdk.jmh.annotations.Benchmark;

import teb.jasper.DummyHttpServletRequest;
import teb.jasper.DummyHttpServletResponse;
import teb.jasper.DummyServletConfig;
import teb.jasper.templates.stocks_jsp;

public class JasperBenchmark extends BaseBenchmark {
	private stocks_jsp template;

	@Override
	public void setup() throws Exception {
		template = new stocks_jsp();
		template.init(DummyServletConfig.INSTANCE);
	}

	@Override
	@Benchmark
	public void run() {
		try {
			template._jspService(
					new DummyHttpServletRequest(getParams()),
					new DummyHttpServletResponse(getOutput()));
		} catch (final IOException | ServletException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new JasperBenchmark().test();
	}
}