package br.com.formento.cockpitRemoto.resource.test.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.formento.cockpitRemoto.resource.service.EntryPointService;
import br.com.formento.cockpitRemoto.resource.service.EntryPointServiceImpl;

public class EntryPointServiceImplTest {

	private EntryPointService entryPointService;

	@Before
	public void init() {
		entryPointService = new EntryPointServiceImpl();
	}

	@Test
	public void testSemComandos() {
		ArrayList<String> comandos = new ArrayList<>();
		String processarComandos = entryPointService.processarComandos(comandos);
		assertNotNull(processarComandos);
	}

	@Test
	public void testComComandos() {
		ArrayList<String> comandos = new ArrayList<>();
		comandos.add("5 5");
		comandos.add("1 2 N");
		comandos.add("LMLMLMLMM");
		comandos.add("3 3 E");
		comandos.add("MMRMMRMRRM");

		String processarComandos = entryPointService.processarComandos(comandos);
		assertNotNull(processarComandos);
	}

}
