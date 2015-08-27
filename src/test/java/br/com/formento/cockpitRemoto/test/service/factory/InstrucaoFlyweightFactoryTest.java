package br.com.formento.cockpitRemoto.test.service.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.CenarioProcessamentoImpl;
import br.com.formento.cockpitRemoto.service.command.Instrucao;
import br.com.formento.cockpitRemoto.service.command.InstrucaoAdicionar;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweightFactory;

public class InstrucaoFlyweightFactoryTest {

	private InstrucaoFlyweightFactory instrucaoFlyweightFactory;

	@Before
	public void init() {
		CenarioProcessamento cenarioProcessamento = new CenarioProcessamentoImpl();
		instrucaoFlyweightFactory = new InstrucaoFlyweightFactory(cenarioProcessamento);
	}

	@Test
	public void testInstrucaoAdicionar() {
		Instrucao instrucao = instrucaoFlyweightFactory.getFlyweight(InstrucaoAdicionar.class);
		assertNotNull(instrucao);
		assertTrue(instrucao instanceof InstrucaoAdicionar);
	}

	@Test
	public void testValidarSeNaoCriaMaisDeUmaClasse() {
		Instrucao instrucao1 = instrucaoFlyweightFactory.getFlyweight(InstrucaoAdicionar.class);
		Instrucao instrucao2 = instrucaoFlyweightFactory.getFlyweight(InstrucaoAdicionar.class);

		// testar se as duas instancias são iguais (por isso usou o == e não o
		// equals)
		assertEquals(instrucao1, instrucao2);
	}

}
