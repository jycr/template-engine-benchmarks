/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.io.IOException;
import java.io.Writer;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.openjdk.jmh.annotations.Benchmark;

public class BeetlBenchmark extends BaseBenchmark {

	private Template template;

	@Override
	public void setup() {
		Configuration cfg;
		try {
			cfg = Configuration.defaultConfiguration();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
		cfg.setStatementStart("<!--:");
		cfg.setStatementEnd("-->");
		cfg.setPlaceholderStart("${");
		cfg.setPlaceholderEnd("}");
		final GroupTemplate group = new GroupTemplate(new ClasspathResourceLoader(ClassLoader.getSystemClassLoader(), "", "UTF-8"), cfg);
		template = group.getTemplate(TEMPLATE_DIR + "/" + getTemplateName("beetl"));
	}

	@Override
	@Benchmark
	public void run() {
		template.binding(getParams());
		try (Writer output = getOutput()) {
			template.renderTo(output);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new BeetlBenchmark().test();
	}
}