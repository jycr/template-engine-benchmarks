/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.openjdk.jmh.annotations.Benchmark;

import teb.model.Stock;

public class JavaNativeBenchmark extends BaseBenchmark {

	private static final String TEMPLATE_STOCKS_01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<!DOCTYPE html>\n"
			+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n"
			+ " <head>\n"
			+ "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
			+ "  <title>Stock Prices</title>\n"
			+ " </head>\n"
			+ " <body>\n"
			+ "  <h1>Stock Prices</h1>\n"
			+ "  <table>\n"
			+ "   <thead>\n"
			+ "    <tr><th>symbol</th><th>name</th><th>price</th><th>change</th><th>ratio</th></tr>\n"
			+ "   </thead>\n"
			+ "   <tbody>\n";

	private static final String TEMPLATE_STOCKS_02 = "<tr><td><a href=\"/stocks/";

	private static final String TEMPLATE_STOCKS_CLOSETAG = "\">";

	private static final String TEMPLATE_STOCKS_06 = "</a></td><td><a href=\"";

	private static final String TEMPLATE_STOCKS_08 = "</a></td><td>";

	private static final String TEMPLATE_STOCKS_09 = "</td><td>";

	private static final String TEMPLATE_STOCKS_12 = "</td></tr>\n";

	private static final String TEMPLATE_STOCKS_17 = "</tbody>\n</table>\n</body>\n</html>\n";

	private String templateName;

	@Override
	public void setup() throws Exception {
		templateName = getTemplateName("native.html");
	}

	@Override
	@Benchmark
	public void run() {
		if ("stocks.native.html".equals(templateName)) {
			try {
				renderStocks(getOutput());
			} catch (final IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			throw new RuntimeException("Unable to render: " + templateName);
		}
	}

	public void renderStocks(final Writer writer) throws IOException {
		@SuppressWarnings("unchecked")
		final List<Stock> items = (List<Stock>) getParams().get("items");
		writer.write(TEMPLATE_STOCKS_01);
		for (final Stock item : items) {
			writer.write(TEMPLATE_STOCKS_02);
			writer.write(item.getSymbol());
			writer.write(TEMPLATE_STOCKS_CLOSETAG);
			writer.write(item.getSymbol());
			writer.write(TEMPLATE_STOCKS_06);
			writer.write(item.getUrl());
			writer.write(TEMPLATE_STOCKS_CLOSETAG);
			writer.write(item.getName());
			writer.write(TEMPLATE_STOCKS_08);
			writer.write(String.valueOf(item.getPrice()));
			writer.write(TEMPLATE_STOCKS_09);
			writer.write(String.valueOf(item.getChange()));
			writer.write(TEMPLATE_STOCKS_09);
			writer.write(String.valueOf(item.getRatio()));
			writer.write(TEMPLATE_STOCKS_12);
		}
		writer.write(TEMPLATE_STOCKS_17);
	}

	public static void main(final String[] args) {
		new JavaNativeBenchmark().test();
	}
}