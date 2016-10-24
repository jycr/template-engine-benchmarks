/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package com.mitchellbosecke.benchmark;

import static com.mitchellbosecke.benchmark.model.ITemplate.DEFAULT_CHARSET;
import static com.mitchellbosecke.benchmark.model.ITemplate.PAGE_ATTRIBUTE_ITEMS;
import static com.mitchellbosecke.benchmark.model.ITemplate.PAGE_ATTRIBUTE_XMLRESPONSE;
import static com.mitchellbosecke.benchmark.model.ITemplate.Template.responseXml;
import static com.mitchellbosecke.benchmark.model.ITemplate.Template.stocksHtml;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.openjdk.jmh.annotations.Benchmark;

import com.mitchellbosecke.benchmark.model.ITemplate.Template;
import com.mitchellbosecke.benchmark.model.Stock;
import com.mitchellbosecke.benchmark.model.XmlResponse;

public class JavaNativeBenchmark extends BaseBenchmark {
	private NativeTemplate nativeTemplate;

	@Override
	public void setup() throws Exception {
		final Template template = getTemplate();
		if (stocksHtml == template) {
			nativeTemplate = StocksHtmlTemplate.INSTANCE;
		} else if (responseXml == template) {
			nativeTemplate = XmlResponseTemplate.INSTANCE;
		} else {
			throw new IllegalStateException("Template unknown: " + nativeTemplate);
		}
	}

	@Override
	@Benchmark
	public void run() {
		try {
			nativeTemplate.render(getOutputStream(), getContext());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new JavaNativeBenchmark().test();
	}

	public static interface NativeTemplate {
		public void render(final OutputStream writer, Map<String, Object> params) throws IOException;
	}

	public static final class StocksHtmlTemplate implements NativeTemplate {

		private static final byte[] TEMPLATE_01 = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<!DOCTYPE html>\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n"
				+ "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
				+ "<title>Stock Prices</title></head><body><h1>Stock Prices</h1>"
				+ "<table><thead><tr><th>symbol</th><th>name</th><th>price</th><th>change</th><th>ratio</th></tr></thead>"
				+ "<tbody>").getBytes(DEFAULT_CHARSET);

		private static final byte[] TEMPLATE_02 = "<tr><td><a href=\"/stocks/".getBytes(DEFAULT_CHARSET);

		private static final byte[] TEMPLATE_CLOSETAG = "\">".getBytes(DEFAULT_CHARSET);

		private static final byte[] TEMPLATE_04 = "</a></td><td><a href=\"".getBytes(DEFAULT_CHARSET);

		private static final byte[] TEMPLATE_05 = "</a></td><td>".getBytes(DEFAULT_CHARSET);

		private static final byte[] TEMPLATE_CELL = "</td><td>".getBytes(DEFAULT_CHARSET);

		private static final byte[] TEMPLATE_07 = "</td></tr>\n".getBytes(DEFAULT_CHARSET);

		private static final byte[] TEMPLATE_08 = "</tbody>\n</table>\n</body>\n</html>".getBytes(DEFAULT_CHARSET);

		public static final NativeTemplate INSTANCE = new StocksHtmlTemplate();

		private StocksHtmlTemplate() {
		}

		@Override
		public void render(final OutputStream writer, final Map<String, Object> params) throws IOException {
			@SuppressWarnings("unchecked")
			final List<Stock> items = (List<Stock>) params.get(PAGE_ATTRIBUTE_ITEMS);
			writer.write(TEMPLATE_01);
			for (final Stock item : items) {
				final byte[] symbol = item.getSymbol().getBytes(DEFAULT_CHARSET);
				writer.write(TEMPLATE_02);
				writer.write(symbol);
				writer.write(TEMPLATE_CLOSETAG);
				writer.write(symbol);
				writer.write(TEMPLATE_04);
				writer.write(item.getUrl().getBytes(DEFAULT_CHARSET));
				writer.write(TEMPLATE_CLOSETAG);
				writer.write(item.getName().getBytes(DEFAULT_CHARSET));
				writer.write(TEMPLATE_05);
				writer.write(String.valueOf(item.getPrice()).getBytes(DEFAULT_CHARSET));
				writer.write(TEMPLATE_CELL);
				writer.write(String.valueOf(item.getChange()).getBytes(DEFAULT_CHARSET));
				writer.write(TEMPLATE_CELL);
				writer.write(String.valueOf(item.getRatio()).getBytes(DEFAULT_CHARSET));
				writer.write(TEMPLATE_07);
			}
			writer.write(TEMPLATE_08);
		}
	}

	public static final class XmlResponseTemplate implements NativeTemplate {

		private static final byte[] TEMPLATE_01 = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<doc><header><uuid>").getBytes(DEFAULT_CHARSET);
		private static final byte[] TEMPLATE_02 = ("</uuid><lastModified>").getBytes(DEFAULT_CHARSET);
		private static final byte[] TEMPLATE_03 = ("</lastModified></header><org-info><org-uuid>").getBytes(DEFAULT_CHARSET);
		private static final byte[] TEMPLATE_04 = ("</org-uuid></org-info><info-status><status-code>").getBytes(DEFAULT_CHARSET);
		private static final byte[] TEMPLATE_05 = ("</status-code></info-status></doc>").getBytes(DEFAULT_CHARSET);

		public static final NativeTemplate INSTANCE = new XmlResponseTemplate();

		private XmlResponseTemplate() {
		}

		@Override
		public void render(final OutputStream writer, final Map<String, Object> params) throws IOException {
			final XmlResponse xmlResponse = (XmlResponse) params.get(PAGE_ATTRIBUTE_XMLRESPONSE);
			writer.write(TEMPLATE_01);
			writer.write(String.valueOf(xmlResponse.getUuid()).getBytes(DEFAULT_CHARSET));
			writer.write(TEMPLATE_02);
			writer.write(String.valueOf(xmlResponse.getLastModified()).getBytes(DEFAULT_CHARSET));
			writer.write(TEMPLATE_03);
			writer.write(String.valueOf(xmlResponse.getOrgUuid()).getBytes(DEFAULT_CHARSET));
			writer.write(TEMPLATE_04);
			writer.write(String.valueOf(xmlResponse.getStatus()).getBytes(DEFAULT_CHARSET));
			writer.write(TEMPLATE_05);
		}
	}
}