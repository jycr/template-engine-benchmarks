/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

import org.openjdk.jmh.annotations.Benchmark;

import httl.Engine;
import httl.Template;

public class HttlBenchmark extends BaseBenchmark {
	private Engine engine;
	private Template template;

	@Override
	public void setup() throws Exception {
		final Properties prop = new Properties();
		prop.setProperty("import.packages", "teb.model,java.util");
		prop.setProperty("compiler", "httl.spi.compilers.JavassistCompiler");
		prop.setProperty("filter", "null");
		prop.setProperty("logger", "null");
		engine = Engine.getEngine(prop);
		template = engine.getTemplate(getTemplatePath(".httl.html"));
	}

	@Override
	@Benchmark
	public void run() {
		try {
			template.render(getParams(), getOutput());
		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new HttlBenchmark().test();
	}
}