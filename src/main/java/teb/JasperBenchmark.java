/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.jasper.runtime.HttpJspBase;
import org.openjdk.jmh.annotations.Benchmark;

import teb.jasper.DummyHttpServletRequest;
import teb.jasper.DummyHttpServletResponse;
import teb.jasper.DummyServletConfig;
import teb.jasper.templates.html.stocks_html_jsp;
import teb.jasper.templates.xml.response_xml_jsp;

public class JasperBenchmark extends BaseBenchmark {
	private HttpJspBase template;

	@Override
	public void setup() throws Exception {
		final String templateName = getTemplateName("");
		if (TEMPLATE_XML_RESPONSE.equals(templateName)) {
			template = new response_xml_jsp();
		} else if (TEMPLATE_HTML_STOCKS.equals(templateName)) {
			template = new stocks_html_jsp();
		} else {
			throw new IllegalArgumentException("Template Name not known: " + templateName);
		}
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