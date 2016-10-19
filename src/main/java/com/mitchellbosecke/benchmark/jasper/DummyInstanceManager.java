package com.mitchellbosecke.benchmark.jasper;

import java.lang.reflect.InvocationTargetException;

import javax.naming.NamingException;

import org.apache.tomcat.InstanceManager;

public class DummyInstanceManager implements InstanceManager {
	public static final DummyInstanceManager INSTANCE = new DummyInstanceManager();

	private DummyInstanceManager() {
	}

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
}