package teb;

import org.openjdk.jmh.annotations.Benchmark;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.github.mustachejava.resolver.ClasspathResolver;

public class MustacheBenchmark extends BaseBenchmark {

	private MustacheFactory mustacheFactory;
	private Mustache template;

	@Override
	public void setup() {
		mustacheFactory = new DefaultMustacheFactory(new ClasspathResolver());
		template = mustacheFactory.compile(getTemplatePath(".mustache.html"));
	}

	@Override
	@Benchmark
	public void run() {
		template.execute(getOutput(), getParams());
	}

	public static void main(final String[] args) {
		new MustacheBenchmark().test();
	}
}