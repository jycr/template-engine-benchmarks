package com.mitchellbosecke.benchmark.jasper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

public class DummyHttpServletRequest implements HttpServletRequest {
	private static final String DEFAULT_HTTP_METHOD = "GET";
	private final Map<String, Object> attributes;

	public DummyHttpServletRequest(final Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public Object getAttribute(final String name) {
		return attributes.get(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getCharacterEncoding() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCharacterEncoding(final String env) throws UnsupportedEncodingException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getContentLength() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getContentLengthLong() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getContentType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getParameter(final String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<String> getParameterNames() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String[] getParameterValues(final String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getProtocol() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getScheme() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getServerName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getServerPort() {
		throw new UnsupportedOperationException();
	}

	@Override
	public BufferedReader getReader() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRemoteAddr() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRemoteHost() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setAttribute(final String name, final Object o) {
		attributes.put(name, o);
	}

	@Override
	public void removeAttribute(final String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Locale getLocale() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<Locale> getLocales() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSecure() {
		throw new UnsupportedOperationException();
	}

	@Override
	public RequestDispatcher getRequestDispatcher(final String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRealPath(final String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getRemotePort() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getLocalName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getLocalAddr() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getLocalPort() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ServletContext getServletContext() {
		throw new UnsupportedOperationException();
	}

	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		throw new UnsupportedOperationException();
	}

	@Override
	public AsyncContext startAsync(final ServletRequest servletRequest, final ServletResponse servletResponse) throws IllegalStateException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isAsyncStarted() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isAsyncSupported() {
		throw new UnsupportedOperationException();
	}

	@Override
	public AsyncContext getAsyncContext() {
		throw new UnsupportedOperationException();
	}

	@Override
	public DispatcherType getDispatcherType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getAuthType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Cookie[] getCookies() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getDateHeader(final String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getHeader(final String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<String> getHeaders(final String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getIntHeader(final String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getMethod() {
		return DEFAULT_HTTP_METHOD;
	}

	@Override
	public String getPathInfo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPathTranslated() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getContextPath() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getQueryString() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRemoteUser() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isUserInRole(final String role) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Principal getUserPrincipal() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRequestedSessionId() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRequestURI() {
		throw new UnsupportedOperationException();
	}

	@Override
	public StringBuffer getRequestURL() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getServletPath() {
		throw new UnsupportedOperationException();
	}

	@Override
	public HttpSession getSession(final boolean create) {
		throw new UnsupportedOperationException();
	}

	@Override
	public HttpSession getSession() {
		return DummyHttpSession.INSTANCE;
	}

	@Override
	public String changeSessionId() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isRequestedSessionIdFromUrl() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean authenticate(final HttpServletResponse response) throws IOException, ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void login(final String username, final String password) throws ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void logout() throws ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<Part> getParts() throws IOException, ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Part getPart(final String name) throws IOException, ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T extends HttpUpgradeHandler> T upgrade(final Class<T> handlerClass) throws IOException, ServletException {
		throw new UnsupportedOperationException();
	}
}