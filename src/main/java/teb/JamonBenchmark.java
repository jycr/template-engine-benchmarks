/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.io.IOException;
import java.util.List;

import org.openjdk.jmh.annotations.Benchmark;

import teb.model.Stock;
import teb.model.XmlResponse;
import templates.html.stocks_html;
import templates.xml.response_xml;

/**
 * @see "http://www.jamon.org"
 */
public class JamonBenchmark extends BaseBenchmark {
	@Override
	public void setup() throws Exception {
	}

	@SuppressWarnings("unchecked")
	@Override
	@Benchmark
	public void run() {
		try {
			final String templateName = getTemplateName("");
			if (TEMPLATE_XML_RESPONSE.equals(templateName)) {
				new response_xml().render(getOutput(), (XmlResponse) getParams().get("xmlResponse"));
			} else if (TEMPLATE_HTML_STOCKS.equals(templateName)) {
				new stocks_html().render(getOutput(), (List<Stock>) getParams().get("items"));
			} else {
				throw new IllegalArgumentException("Template Name not known: " + templateName);
			}
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new JamonBenchmark().test();
	}
}