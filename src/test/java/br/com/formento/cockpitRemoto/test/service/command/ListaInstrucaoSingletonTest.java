package br.com.formento.cockpitRemoto.test.service.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.formento.cockpitRemoto.service.command.Instrucao;
import br.com.formento.cockpitRemoto.service.command.InstrucaoRotacionarEsquerda;
import br.com.formento.cockpitRemoto.service.singleton.ListaInstrucaoSingleton;

public class ListaInstrucaoSingletonTest {

	@Test
	public void testRecuperarInstrucaoRotacionarEsquerdaAtravesDoComando() {
		Class<? extends Instrucao> instrucaoClass = ListaInstrucaoSingleton.getInstance().getInstrucaoClass("L");
		assertNotNull(instrucaoClass);
		assertEquals(InstrucaoRotacionarEsquerda.class, instrucaoClass);
	}

}
