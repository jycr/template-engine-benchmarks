package teb;

import org.openjdk.jmh.annotations.Benchmark;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import teb.util.ClasspathResourceUtils;

public class JMustacheBenchmark extends BaseBenchmark {

	private Mustache.Compiler compiler;
	private Template template;

	@Override
	public void setup() {
		compiler = Mustache.compiler();
		template = compiler.compile(ClasspathResourceUtils.getReader(TEMPLATE_DIR + "/" + getTemplateName("mustache")));
	}

	@Override
	@Benchmark
	public void run() {
		template.execute(getParams(), getOutput());
	}

	public static void main(final String[] args) {
		new JMustacheBenchmark().test();
	}
}