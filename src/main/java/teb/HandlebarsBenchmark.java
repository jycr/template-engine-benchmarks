package teb;

import java.io.IOException;

import org.openjdk.jmh.annotations.Benchmark;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.cache.ConcurrentMapTemplateCache;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

public class HandlebarsBenchmark extends BaseBenchmark {
	private Handlebars handlebars;
	private Template template;

	@Override
	public void setup() throws IOException {
		final TemplateLoader templateLoader = new ClassPathTemplateLoader();
		templateLoader.setPrefix("/" + TEMPLATE_DIR + "/");
		templateLoader.setSuffix("");
		handlebars = new Handlebars(templateLoader).with(new ConcurrentMapTemplateCache());
		template = handlebars.compile(getTemplateName("handlebars.html"));
	}

	@Override
	@Benchmark
	public void run() {
		try {
			template.apply(getParams(), getOutput());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(final String[] args) {
		new HandlebarsBenchmark().test();
	}
}