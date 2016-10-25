/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package com.mitchellbosecke.benchmark;

import static com.mitchellbosecke.benchmark.model.ITemplate.Template.responseXml;
import static com.mitchellbosecke.benchmark.model.ITemplate.Template.stocksHtml;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.jasper.runtime.HttpJspBase;
import org.openjdk.jmh.annotations.Benchmark;

import com.mitchellbosecke.benchmark.jasper.DummyHttpServletRequest;
import com.mitchellbosecke.benchmark.jasper.DummyHttpServletResponse;
import com.mitchellbosecke.benchmark.jasper.DummyServletConfig;
import com.mitchellbosecke.benchmark.model.ITemplate.Template;

import templates.jasper.response_xml_jspx;
import templates.jasper.stocks_html_jspx;

public class JasperXBenchmark extends BaseBenchmark {
	private HttpJspBase jspTemplate;

	@Override
	public void setup() throws Exception {
		final Template template = getTemplate();
		if (responseXml == template) {
			jspTemplate = new response_xml_jspx();
		} else if (stocksHtml == template) {
			jspTemplate = new stocks_html_jspx();
		} else {
			throw new IllegalArgumentException("Template Name not known: " + template);
		}
		jspTemplate.init(DummyServletConfig.INSTANCE);
	}

	@Override
	@Benchmark
	public void run() {
		try (PrintWriter stream = new PrintWriter(getOutputStream())) {
			jspTemplate._jspService(
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