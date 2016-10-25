package com.mitchellbosecke.benchmark.jasper;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.descriptor.JspConfigDescriptor;

import org.apache.jasper.runtime.JspApplicationContextImpl;
import org.apache.tomcat.InstanceManager;

public class DummyServletContext implements ServletContext {
	public static final DummyServletContext INSTANCE = new DummyServletContext();
	private static final Map<String, Object> ATTRIBUTES;
	static {
		final Map<String, Object> attributes = new HashMap<>();
		attributes.put(JspApplicationContextImpl.class.getName(), new JspApplicationContextImpl());
		attributes.put(InstanceManager.class.getName(), new InstanceManager() {
			@Override
			public Object newInstance(final Class<?> clazz) throws IllegalAccessException, InvocationTargetException, NamingException, InstantiationException {
				throw new UnsupportedOperationException();
			}

			@Override
			public Object newInstance(final String className) throws IllegalAccessException, InvocationTargetException, NamingException, InstantiationException, ClassNotFoundException {
				throw new UnsupportedOperationException();
			}

			@Override
			public Object newInstance(final String fqcn, final ClassLoader classLoader)
					throws IllegalAccessException, InvocationTargetException, NamingException, InstantiationException, ClassNotFoundException {
				throw new UnsupportedOperationException();
			}

			@Override
			public void newInstance(final Object o) throws IllegalAccessException, InvocationTargetException, NamingException {
			}

			@Override
			public void destroyInstance(final Object o) throws IllegalAccessException, InvocationTargetException {
			}
		});
		ATTRIBUTES = Collections.unmodifiableMap(attributes);
	}

	private DummyServletContext() {
	}

	@Override
	public String getContextPath() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ServletContext getContext(final String uripath) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getMajorVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getMinorVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getEffectiveMajorVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getEffectiveMinorVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getMimeType(final String file) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> getResourcePaths(final String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public URL getResource(final String path) throws MalformedURLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public InputStream getResourceAsStream(final String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public RequestDispatcher getRequestDispatcher(final String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public RequestDispatcher getNamedDispatcher(final String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Servlet getServlet(final String name) throws ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<Servlet> getServlets() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<String> getServletNames() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void log(final String msg) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void log(final Exception exception, final String msg) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void log(final String message, final Throwable throwable) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRealPath(final String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getServerInfo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getInitParameter(final String name) {
		return null;
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		return Collections.emptyEnumeration();
	}

	@Override
	public boolean setInitParameter(final String name, final String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getAttribute(final String name) {
		return ATTRIBUTES.get(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return Collections.enumeration(ATTRIBUTES.keySet());
	}

	@Override
	public void setAttribute(final String name, final Object object) {
	}

	@Override
	public void removeAttribute(final String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getServletContextName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Dynamic addServlet(final String servletName, final String className) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Dynamic addServlet(final String servletName, final Servlet servlet) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Dynamic addServlet(final String servletName, final Class<? extends Servlet> servletClass) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T extends Servlet> T createServlet(final Class<T> clazz) throws ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public ServletRegistration getServletRegistration(final String servletName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, ? extends ServletRegistration> getServletRegistrations() {
		throw new UnsupportedOperationException();
	}

	@Override
	public javax.servlet.FilterRegistration.Dynamic addFilter(final String filterName, final String className) {
		throw new UnsupportedOperationException();
	}

	@Override
	public javax.servlet.FilterRegistration.Dynamic addFilter(final String filterName, final Filter filter) {
		throw new UnsupportedOperationException();
	}

	@Override
	public javax.servlet.FilterRegistration.Dynamic addFilter(final String filterName, final Class<? extends Filter> filterClass) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T extends Filter> T createFilter(final Class<T> clazz) throws ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public FilterRegistration getFilterRegistration(final String filterName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
		throw new UnsupportedOperationException();
	}

	@Override
	public SessionCookieConfig getSessionCookieConfig() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setSessionTrackingModes(final Set<SessionTrackingMode> sessionTrackingModes) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addListener(final String className) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T extends EventListener> void addListener(final T t) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addListener(final Class<? extends EventListener> listenerClass) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T extends EventListener> T createListener(final Class<T> clazz) throws ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JspConfigDescriptor getJspConfigDescriptor() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ClassLoader getClassLoader() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void declareRoles(final String... roleNames) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getVirtualServerName() {
		throw new UnsupportedOperationException();
	}
}