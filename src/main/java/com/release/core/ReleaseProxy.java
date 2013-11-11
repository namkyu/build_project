/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.release.anno.Action;
import com.release.common.BaseType;
import com.release.handler.AbstractBuilder;

/**
 * The Class ReleaseProxy.
 */
@Deprecated
public class ReleaseProxy {

	/**
	 * <pre>
	 * createInstance
	 *
	 * <pre>
	 * @param command
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static AbstractBuilder createInstance(String command) throws Exception {
		Class<AbstractBuilder> abstractClass = (Class<AbstractBuilder>) scanAnno(command);
		AbstractBuilder abstractBuilder = abstractClass.newInstance();
		return abstractBuilder;
	}

	/**
	 * <pre>
	 * scanAnno
	 *
	 * <pre>
	 * @param command
	 * @return
	 * @throws Exception
	 */
	public static Class<?> scanAnno(String command) throws Exception {
		Class<?> targetClass = null;
		Class<?>[] classes = getClasses(BaseType.ANNOTATION_SCAN_PACKAGE);
		boolean existAnno = false;

		for (Class<?> clz : classes) {
			existAnno = clz.isAnnotationPresent(Action.class);
			if (existAnno) {
				Action action = clz.getAnnotation(Action.class);
				if (action.value().equals(command)) {
					targetClass = clz;
					break;
				}
			}
		}

		return targetClass;
	}

	/**
	 * <pre>
	 * getClasses
	 *
	 * <pre>
	 * @param packageName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static Class<?>[] getClasses(String packageName) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	/**
	 * <pre>
	 * findClasses
	 *
	 * <pre>
	 * @param directory
	 * @param packageName
	 * @return
	 * @throws ClassNotFoundException
	 */
	private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		if (directory.exists() == false) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

	public static void main(String[] args) throws Exception {
		createInstance("package");
	}
}
