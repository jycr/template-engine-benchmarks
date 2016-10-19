package com.mitchellbosecke.benchmark.jasper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class DummyHttpServletResponse implements HttpServletResponse {

	private final PrintWriter output;

	public DummyHttpServletResponse(final Writer output) {
		this.output = new PrintWriter(output);
	}

	@Override
	public String getCharacterEncoding() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getContentType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return output;
	}

	@Override
	public void setCharacterEncoding(final String charset) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setContentLength(final int len) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setContentLengthLong(final long len) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setContentType(final String type) {
		// throw new UnsupportedOperationException();
	}

	@Override
	public void setBufferSize(final int size) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getBufferSize() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void flushBuffer() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void resetBuffer() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCommitted() {
		return false;
	}

	@Override
	public void reset() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLocale(final Locale loc) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Locale getLocale() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addCookie(final Cookie cookie) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsHeader(final String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String encodeURL(final String url) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String encodeRedirectURL(final String url) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String encodeUrl(final String url) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String encodeRedirectUrl(final String url) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendError(final int sc, final String msg) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendError(final int sc) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendRedirect(final String location) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setDateHeader(final String name, final long date) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addDateHeader(final String name, final long date) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setHeader(final String name, final String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addHeader(final String name, final String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setIntHeader(final String name, final int value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addIntHeader(final String name, final int value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setStatus(final int sc) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setStatus(final int sc, final String sm) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getStatus() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getHeader(final String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> getHeaders(final String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> getHeaderNames() {
		throw new UnsupportedOperationException();
	}
}