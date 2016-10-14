/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.io.IOException;

import org.openjdk.jmh.annotations.Benchmark;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

public class FreemarkerBenchmark extends BaseBenchmark {

	private Configuration cfg;
	private Template template;

	@Override
	public void setup() {
		cfg = new Configuration(new Version(2, 3, 23));
		cfg.setTemplateLoader(new ClassTemplateLoader(ClassLoader.getSystemClassLoader(), TEMPLATE_DIR));
		try {
			template = cfg.getTemplate(getTemplateName("ftl"));
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Benchmark
	@Override
	public void run() {
		try {
			template.process(getParams(), getOutput());
		} catch (TemplateException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new FreemarkerBenchmark().test();
	}
}