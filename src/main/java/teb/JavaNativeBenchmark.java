/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.openjdk.jmh.annotations.Benchmark;

import teb.model.Stock;
import teb.model.XmlResponse;

public class JavaNativeBenchmark extends BaseBenchmark {
	private NativeTemplate template;

	@Override
	public void setup() throws Exception {
		final String templateName = getTemplateName("");
		if (TEMPLATE_HTML_STOCKS.equals(templateName)) {
			template = StocksHtmlTemplate.INSTANCE;
		} else if (TEMPLATE_XML_RESPONSE.equals(templateName)) {
			template = XmlResponseTemplate.INSTANCE;
		} else {
			throw new IllegalStateException("Template unknown: " + templateName);
		}
	}

	@Override
	@Benchmark
	public void run() {
		try {
			template.render(getOutput(), getParams());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new JavaNativeBenchmark().test();
	}

	public static interface NativeTemplate {
		public void render(final Writer writer, Map<String, Object> params) throws IOException;
	}

	public static final class StocksHtmlTemplate implements NativeTemplate {

		private static final char[] TEMPLATE_01 = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<!DOCTYPE html>\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n"
				+ "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
				+ "<title>Stock Prices</title></head><body><h1>Stock Prices</h1>"
				+ "<table><thead><tr><th>symbol</th><th>name</th><th>price</th><th>change</th><th>ratio</th></tr></thead>"
				+ "<tbody>").toCharArray();

		private static final char[] TEMPLATE_02 = "<tr><td><a href=\"/stocks/".toCharArray();

		private static final char[] TEMPLATE_CLOSETAG = "\">".toCharArray();

		private static final char[] TEMPLATE_04 = "</a></td><td><a href=\"".toCharArray();

		private static final char[] TEMPLATE_05 = "</a></td><td>".toCharArray();

		private static final char[] TEMPLATE_CELL = "</td><td>".toCharArray();

		private static final char[] TEMPLATE_07 = "</td></tr>\n".toCharArray();

		private static final char[] TEMPLATE_08 = "</tbody>\n</table>\n</body>\n</html>".toCharArray();

		public static final NativeTemplate INSTANCE = new StocksHtmlTemplate();

		private StocksHtmlTemplate() {
		}

		@Override
		public void render(final Writer writer, final Map<String, Object> params) throws IOException {
			@SuppressWarnings("unchecked")
			final List<Stock> items = (List<Stock>) params.get("items");
			writer.write(TEMPLATE_01);
			for (final Stock item : items) {
				final char[] symbol = item.getSymbol().toCharArray();
				writer.write(TEMPLATE_02);
				writer.write(symbol);
				writer.write(TEMPLATE_CLOSETAG);
				writer.write(symbol);
				writer.write(TEMPLATE_04);
				writer.write(item.getUrl().toCharArray());
				writer.write(TEMPLATE_CLOSETAG);
				writer.write(item.getName().toCharArray());
				writer.write(TEMPLATE_05);
				writer.write(String.valueOf(item.getPrice()).toCharArray());
				writer.write(TEMPLATE_CELL);
				writer.write(String.valueOf(item.getChange()).toCharArray());
				writer.write(TEMPLATE_CELL);
				writer.write(String.valueOf(item.getRatio()).toCharArray());
				writer.write(TEMPLATE_07);
			}
			writer.write(TEMPLATE_08);
		}
	}

	public static final class XmlResponseTemplate implements NativeTemplate {

		private static final char[] TEMPLATE_01 = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<doc><header><uuid>").toCharArray();
		private static final char[] TEMPLATE_02 = ("</uuid><lastModified>").toCharArray();
		private static final char[] TEMPLATE_03 = ("</lastModified></header><org-info><org-uuid>").toCharArray();
		private static final char[] TEMPLATE_04 = ("</org-uuid></org-info><info-status><status-code>").toCharArray();
		private static final char[] TEMPLATE_05 = ("</status-code></info-status></doc>").toCharArray();

		public static final NativeTemplate INSTANCE = new XmlResponseTemplate();

		private XmlResponseTemplate() {
		}

		@Override
		public void render(final Writer writer, final Map<String, Object> params) throws IOException {
			@SuppressWarnings("unchecked")
			final XmlResponse xmlResponse = (XmlResponse) params.get("xmlResponse");
			writer.write(TEMPLATE_01);
			writer.write(String.valueOf(xmlResponse.getUuid()).toCharArray());
			writer.write(TEMPLATE_02);
			writer.write(String.valueOf(xmlResponse.getLastModified()).toCharArray());
			writer.write(TEMPLATE_03);
			writer.write(String.valueOf(xmlResponse.getOrgUuid()).toCharArray());
			writer.write(TEMPLATE_04);
			writer.write(String.valueOf(xmlResponse.getStatus()).toCharArray());
			writer.write(TEMPLATE_05);
		}
	}
}