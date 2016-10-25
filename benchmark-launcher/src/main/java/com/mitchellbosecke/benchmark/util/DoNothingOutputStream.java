package com.mitchellbosecke.benchmark.util;

import java.io.IOException;

public class DoNothingOutputStream extends java.io.OutputStream {

	@Override
	public void write(final int b) throws IOException {
	}

	@Override
	public void write(final byte[] bs) throws IOException {
	}

	@Override
	public void write(final byte b[], final int off, final int len) throws IOException {
	}

}
