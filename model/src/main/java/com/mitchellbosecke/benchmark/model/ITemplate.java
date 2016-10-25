package com.mitchellbosecke.benchmark.model;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public interface ITemplate {
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	public static final String PAGE_ATTRIBUTE_ITEMS = "items";
	public static final String PAGE_ATTRIBUTE_XMLRESPONSE = "xmlResponse";

	public static final String STOCKS_HTML = "html/stocks.html";
	public static final String RESPONSE_XML = "xml/response.xml";

	public static enum Template {
		stocksHtml(STOCKS_HTML), responseXml(RESPONSE_XML);
		private final String templatePath;

		Template(final String templatePath) {
			this.templatePath = templatePath;
		}

		public String getPath() {
			return templatePath;
		}
	}

	public static Map<String, Object> createDummyContext(final Template template) {
		final Map<String, Object> context = new HashMap<>();
		if (template == ITemplate.Template.stocksHtml) {
			context.put(ITemplate.PAGE_ATTRIBUTE_ITEMS, Stock.dummyItems());
		}
		if (template == ITemplate.Template.responseXml) {
			context.put(ITemplate.PAGE_ATTRIBUTE_XMLRESPONSE, XmlResponse.dummyXmlResponse);
		}
		return context;
	}
}