package br.com.formento.cockpitRemoto.test.service.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.formento.cockpitRemoto.service.command.Instrucao;
import br.com.formento.cockpitRemoto.service.command.InstrucaoAdicionarMovel;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweightImpl;

public class InstrucaoFlyweightTest {

	private InstrucaoFlyweight instrucaoFlyweightImpl;

	@Before
	public void init() {
		instrucaoFlyweightImpl = new InstrucaoFlyweightImpl();
	}

	@Test
	public void testInstrucaoAdicionar() {
		Instrucao instrucao = instrucaoFlyweightImpl.getFlyweight(InstrucaoAdicionarMovel.class);
		assertNotNull(instrucao);
		assertTrue(instrucao instanceof InstrucaoAdicionarMovel);
	}

	@Test
	public void testValidarSeNaoCriaMaisDeUmaClasse() {
		Instrucao instrucao1 = instrucaoFlyweightImpl.getFlyweight(InstrucaoAdicionarMovel.class);
		Instrucao instrucao2 = instrucaoFlyweightImpl.getFlyweight(InstrucaoAdicionarMovel.class);

		// testar se as duas instancias são a mesma referencia

		// por isso usou o == e não o equals

		assertEquals(instrucao1, instrucao2);
	}

}
