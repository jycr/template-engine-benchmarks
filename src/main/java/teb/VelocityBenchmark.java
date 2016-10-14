/*
 * $Copyright: copyright(c) 2007-2011 kuwata-lab.com all rights reserved. $
 * $License: Creative Commons Attribution (CC BY) $
 */
package teb;

import java.util.Map.Entry;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.openjdk.jmh.annotations.Benchmark;

public class VelocityBenchmark extends BaseBenchmark {

	private VelocityEngine _engine;
	private Template template;

	@Override
	public void setup() {
		_engine = new VelocityEngine();
		_engine.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");

		_engine.setProperty("input.encoding", "UTF-8");
		_engine.setProperty("output.encoding", "UTF-8");

		_engine.setProperty("resource.loader", "classpath");
		_engine.setProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		try {
			_engine.init();
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}
		template = _engine.getTemplate(getTemplatePath("vm.html"));
	}

	@Override
	@Benchmark
	public void run() {
		/// create context data
		final VelocityContext context = new VelocityContext();
		for (final Entry<String, Object> e : getParams().entrySet()) {
			context.put(e.getKey(), e.getValue());
		}
		template.merge(context, getOutput());
	}

	public static void main(final String[] args) {
		new VelocityBenchmark().test();
	}
}