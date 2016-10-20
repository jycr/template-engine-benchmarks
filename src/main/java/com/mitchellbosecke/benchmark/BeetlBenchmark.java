/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package com.mitchellbosecke.benchmark;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.beetl.core.Configuration;
import org.beetl.core.ErrorHandler;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.exception.BeetlException;
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
		cfg.setStrict(true);
		cfg.setNativeCall(true);
		cfg.setErrorHandlerClass(BeetlBenchmarkErrorHandler.class.getName());
		final GroupTemplate group = new GroupTemplate(new ClasspathResourceLoader(this.getClass().getClassLoader(), "", "UTF-8"), cfg);
		template = group.getTemplate(getTemplatePath(".beetl"));
	}

	@Override
	@Benchmark
	public void run() {
		template.binding(getContext());
		try (OutputStream output = getOutputStream()) {
			template.renderTo(output);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new BeetlBenchmark().test();
	}

	public static final class BeetlBenchmarkErrorHandler implements ErrorHandler {
		@Override
		public void processExcption(final BeetlException e, final Writer writer) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}