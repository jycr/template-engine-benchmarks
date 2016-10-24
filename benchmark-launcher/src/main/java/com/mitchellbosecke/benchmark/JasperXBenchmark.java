/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package com.mitchellbosecke.benchmark;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.jasper.runtime.HttpJspBase;
import org.openjdk.jmh.annotations.Benchmark;

import com.mitchellbosecke.benchmark.jasper.DummyHttpServletRequest;
import com.mitchellbosecke.benchmark.jasper.DummyHttpServletResponse;
import com.mitchellbosecke.benchmark.jasper.DummyServletConfig;
import com.mitchellbosecke.benchmark.jasper.templates.jasper.response_xml_jspx;
import com.mitchellbosecke.benchmark.jasper.templates.jasper.stocks_html_jspx;

public class JasperXBenchmark extends BaseBenchmark {
	private HttpJspBase template;

	@Override
	public void setup() throws Exception {
		final String templateName = getTemplateName("");
		if (TEMPLATE_XML_RESPONSE.equals(templateName)) {
			template = new response_xml_jspx();
		} else if (TEMPLATE_HTML_STOCKS.equals(templateName)) {
			template = new stocks_html_jspx();
		} else {
			throw new IllegalArgumentException("Template Name not known: " + templateName);
		}
		template.init(DummyServletConfig.INSTANCE);
	}

	@Override
	@Benchmark
	public void run() {
		try (PrintWriter stream = new PrintWriter(getOutputStream())) {
			template._jspService(
					new DummyHttpServletRequest(getContext()),
					new DummyHttpServletResponse(stream));
		} catch (final IOException | ServletException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new JasperXBenchmark().test();
	}
}