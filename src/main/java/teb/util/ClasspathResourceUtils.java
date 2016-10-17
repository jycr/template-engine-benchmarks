package teb.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

public class ClasspathResourceUtils {
	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	public static Reader getReader(final String name, final String encoding) throws FileNotFoundException {
		return new InputStreamReader(getStream(name), Charset.forName(encoding));
	}

	public static Reader getReader(final String name) throws FileNotFoundException {
		return new InputStreamReader(getStream(name), DEFAULT_CHARSET);
	}

	public static String getAsString(final String name) throws IOException {
		try (final InputStream inputStream = getStream(name)) {
			return IOUtils.toString(inputStream, DEFAULT_CHARSET);
		}
	}

	public static String getAsString(final String name, final String encoding) throws IOException {
		try (final InputStream inputStream = getStream(name)) {
			return IOUtils.toString(inputStream, Charset.forName(encoding));
		}
	}

	private static InputStream getStream(final String name) throws FileNotFoundException {
		InputStream result = ClasspathResourceUtils.class.getClassLoader().getResourceAsStream(name);
		if (result != null) {
			return result;
		}
		result = ClassLoader.getSystemResourceAsStream(name);
		if (result != null) {
			return result;
		}
		throw new FileNotFoundException(name);
	}
}