package com.mitchellbosecke.benchmark.jasper;

import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

public class DummyHttpSession implements HttpSession {
	public static final HttpSession INSTANCE = new DummyHttpSession();

	private DummyHttpSession() {
	}

	@Override
	public long getCreationTime() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getId() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getLastAccessedTime() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ServletContext getServletContext() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setMaxInactiveInterval(final int interval) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getMaxInactiveInterval() {
		throw new UnsupportedOperationException();
	}

	@Override
	public HttpSessionContext getSessionContext() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getAttribute(final String name) {
		return null;
	}

	@Override
	public Object getValue(final String name) {
		return null;
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return Collections.emptyEnumeration();
	}

	private static final String[] EMPTY = new String[0];

	@Override
	public String[] getValueNames() {
		return EMPTY;
	}

	@Override
	public void setAttribute(final String name, final Object value) {
	}

	@Override
	public void putValue(final String name, final Object value) {
	}

	@Override
	public void removeAttribute(final String name) {
	}

	@Override
	public void removeValue(final String name) {
	}

	@Override
	public void invalidate() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isNew() {
		throw new UnsupportedOperationException();
	}
}