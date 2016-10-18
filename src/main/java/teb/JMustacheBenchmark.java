package teb;

import java.io.FileNotFoundException;

import org.openjdk.jmh.annotations.Benchmark;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

public class JMustacheBenchmark extends BaseBenchmark {

	private Mustache.Compiler compiler;
	private Template template;

	@Override
	public void setup() throws FileNotFoundException {
		compiler = Mustache.compiler();
		template = compiler.compile(getTemplateReader(".mustache"));
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