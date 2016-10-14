/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.util.Locale;

import org.openjdk.jmh.annotations.Benchmark;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class ThymeleafBenchmark extends BaseBenchmark {

	private TemplateEngine engine;
	private String template;

	@Override
	public void setup() {
		engine = new TemplateEngine();
		engine.setTemplateResolver(new ClassLoaderTemplateResolver());
		template = TEMPLATE_DIR + "/" + getTemplateName("thymeleaf");
	}

	@Override
	@Benchmark
	public void run() {
		final IContext ctx = new Context(Locale.getDefault(), getParams());
		engine.process(template, ctx, getOutput());
	}

	public static void main(final String[] args) {
		new ThymeleafBenchmark().test();
	}
}