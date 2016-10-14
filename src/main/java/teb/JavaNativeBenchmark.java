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

	private static final String TEMPLATE_STOCKS_01 = ""
			+ "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n"
			+ "          \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
			+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n"
			+ " <head>\n"
			+ "  <title>Stock Prices</title>\n"
			+ "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
			+ "  <meta http-equiv=\"Content-Style-Type\" content=\"text/css\" />\n"
			+ "  <meta http-equiv=\"Content-Script-Type\" content=\"text/javascript\" />\n"
			+ "  <link rel=\"shortcut icon\" href=\"/images/favicon.ico\" />\n"
			+ "  <link rel=\"stylesheet\" type=\"text/css\" href=\"/css/style.css\" media=\"all\" />\n"
			+ "  <script type=\"text/javascript\" src=\"/js/util.js\"></script>\n"
			+ "  <style type=\"text/css\">\n"
			+ "  /*<![CDATA[*/\n"
			+ "\n"
			+ "body {\n"
			+ "    color: #333333;\n"
			+ "    line-height: 150%;\n"
			+ "}\n"
			+ "\n"
			+ "thead {\n"
			+ "    font-weight: bold;\n"
			+ "    background-color: #CCCCCC;\n"
			+ "}\n"
			+ "\n"
			+ ".odd {\n"
			+ "    background-color: #FFCCCC;\n"
			+ "}\n"
			+ "\n"
			+ ".even {\n"
			+ "    background-color: #CCCCFF;\n"
			+ "}\n"
			+ "\n"
			+ ".minus {\n"
			+ "    color: #FF0000;\n"
			+ "}\n"
			+ "\n"
			+ "  /*]]>*/\n"
			+ "  </style>\n"
			+ "\n"
			+ " </head>\n"
			+ "\n"
			+ " <body>\n"
			+ "\n"
			+ "  <h1>Stock Prices</h1>\n"
			+ "\n"
			+ "  <table>\n"
			+ "   <thead>\n"
			+ "    <tr>\n"
			+ "     <th>#</th><th>symbol</th><th>name</th><th>price</th><th>change</th><th>ratio</th>\n"
			+ "    </tr>\n"
			+ "   </thead>\n"
			+ "   <tbody>\n";

	private static final String TEMPLATE_STOCKS_02 = ""
			+ "    <tr class=\"";

	private static final String TEMPLATE_STOCKS_03 = ""
			+ "\">\n"
			+ "     <td style=\"text-align: center\">";

	private static final String TEMPLATE_STOCKS_04 = ""
			+ "</td>\n"
			+ "     <td>\n"
			+ "      <a href=\"/stocks/";

	private static final String TEMPLATE_STOCKS_05 = ""
			+ "\">";

	private static final String TEMPLATE_STOCKS_06 = ""
			+ "</a>\n"
			+ "     </td>\n"
			+ "     <td>\n"
			+ "      <a href=\"";

	private static final String TEMPLATE_STOCKS_07 = ""
			+ "\">";

	private static final String TEMPLATE_STOCKS_08 = ""
			+ "</a>\n"
			+ "     </td>\n"
			+ "     <td>\n"
			+ "      <strong>";

	private static final String TEMPLATE_STOCKS_09 = ""
			+ "</strong>\n"
			+ "     </td>\n";

	private static final String TEMPLATE_STOCKS_10 = ""
			+ "     <td class=\"minus\">";

	private static final String TEMPLATE_STOCKS_11 = ""
			+ "</td>\n"
			+ "     <td class=\"minus\">";

	private static final String TEMPLATE_STOCKS_12 = ""
			+ "</td>\n";

	private static final String TEMPLATE_STOCKS_13 = ""
			+ "     <td>";

	private static final String TEMPLATE_STOCKS_14 = ""
			+ "</td>\n"
			+ "     <td>";

	private static final String TEMPLATE_STOCKS_15 = ""
			+ "</td>\n";

	private static final String TEMPLATE_STOCKS_16 = ""
			+ "    </tr>\n";

	private static final String TEMPLATE_STOCKS_17 = ""
			+ "   </tbody>\n"
			+ "  </table>\n"
			+ "\n"
			+ " </body>\n"
			+ "</html>\n";

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
		int i = 0;
		for (final Stock item : items) {
			writer.write(TEMPLATE_STOCKS_02);
			writer.write((++i % 2) == 1 ? "odd" : "even");
			writer.write(TEMPLATE_STOCKS_03);
			writer.write(i);
			writer.write(TEMPLATE_STOCKS_04);
			writer.write(item.getSymbol());
			writer.write(TEMPLATE_STOCKS_05);
			writer.write(item.getSymbol());
			writer.write(TEMPLATE_STOCKS_06);
			writer.write(item.getUrl());
			writer.write(TEMPLATE_STOCKS_07);
			writer.write(item.getName());
			writer.write(TEMPLATE_STOCKS_08);
			writer.write(String.valueOf(item.getPrice()));
			writer.write(TEMPLATE_STOCKS_09);
			if (item.getChange() < 0.0) {
				writer.write(TEMPLATE_STOCKS_10);
				writer.write(String.valueOf(item.getChange()));
				writer.write(TEMPLATE_STOCKS_11);
				writer.write(String.valueOf(item.getRatio()));
				writer.write(TEMPLATE_STOCKS_12);
			} else {
				writer.write(TEMPLATE_STOCKS_13);
				writer.write(String.valueOf(item.getChange()));
				writer.write(TEMPLATE_STOCKS_14);
				writer.write(String.valueOf(item.getRatio()));
				writer.write(TEMPLATE_STOCKS_15);
			}
			writer.write(TEMPLATE_STOCKS_16);
		}
		writer.write(TEMPLATE_STOCKS_17);
	}

	public static void main(final String[] args) {
		new JavaNativeBenchmark().test();
	}
}