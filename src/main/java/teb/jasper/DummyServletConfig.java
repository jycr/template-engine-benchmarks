package teb.jasper;

import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class DummyServletConfig implements ServletConfig {
	public static final DummyServletConfig INSTANCE = new DummyServletConfig();

	private DummyServletConfig() {
	}

	@Override
	public String getServletName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ServletContext getServletContext() {
		return DummyServletContext.INSTANCE;
	}

	@Override
	public String getInitParameter(final String name) {
		return null;
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		return Collections.emptyEnumeration();
	}
}