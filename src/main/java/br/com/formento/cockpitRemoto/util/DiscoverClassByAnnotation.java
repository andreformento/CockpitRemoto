package br.com.formento.cockpitRemoto.util;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.reflections.Reflections;

public class DiscoverClassByAnnotation<T extends Annotation> {

	private static final String PACKAGE_NAME = "br.com.formento.cockpitRemoto";

	private Class<T> annotation;

	public DiscoverClassByAnnotation(Class<T> annotation) {
		this.annotation = annotation;
	}

	public Set<Class<?>> getClasses() {
		Reflections reflections = new Reflections(PACKAGE_NAME);
		Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(annotation);
		return typesAnnotatedWith;
	}

}
