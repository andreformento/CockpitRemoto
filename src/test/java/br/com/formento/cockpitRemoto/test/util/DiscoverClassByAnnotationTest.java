package br.com.formento.cockpitRemoto.test.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.com.formento.cockpitRemoto.service.command.InstrucaoAnnotation;
import br.com.formento.cockpitRemoto.util.DiscoverClassByAnnotation;

public class DiscoverClassByAnnotationTest {

	private DiscoverClassByAnnotation<InstrucaoAnnotation> discoverClassByAnnotation;

	@Before
	public void init() {
		discoverClassByAnnotation = new DiscoverClassByAnnotation<>(InstrucaoAnnotation.class);
	}

	@Test
	public void testClasses() {
		Set<Class<?>> classes = discoverClassByAnnotation.getClasses();
		assertNotNull(classes);
		assertFalse(classes.isEmpty());
	}

}
