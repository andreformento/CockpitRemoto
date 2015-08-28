package br.com.formento.cockpitRemoto.test.service.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.CenarioProcessamentoImpl;
import br.com.formento.cockpitRemoto.model.Direcao;
import br.com.formento.cockpitRemoto.model.MalhaRetangular;
import br.com.formento.cockpitRemoto.model.Posicao;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.Sonda;
import br.com.formento.cockpitRemoto.service.command.InstrucaoAdicionarMovel;

public class InstrucaoAdicionarTest {

	private InstrucaoAdicionarMovel instrucao;
	private CenarioProcessamento cenarioProcessamento;

	@Before
	public void init() {
		cenarioProcessamento = new CenarioProcessamentoImpl();
		Posicao posicaoMaxima = new Posicao(5, 5);
		cenarioProcessamento.setMalha(new MalhaRetangular(posicaoMaxima));
		instrucao = new InstrucaoAdicionarMovel();
	}

	@Test
	public void testExecutarAdicionarResultadoSucesso() {
		cenarioProcessamento.setMovel(new Sonda(new Posicao(4, 3, Direcao.NORTE)));
		Resultado executar = instrucao.executar(cenarioProcessamento);
		assertNotNull(executar);
		assertTrue(executar.getTipoResultado().isResultadoOk());
	}

	@Test
	public void testExecutarAdicionarResultadoErroPosicao() {
		cenarioProcessamento.setMovel(new Sonda(new Posicao(6, 3, Direcao.NORTE)));
		Resultado executar = instrucao.executar(cenarioProcessamento);
		assertNotNull(executar);
		assertFalse(executar.getTipoResultado().isResultadoOk());
	}

	@Test
	public void testExecutarAdicionarResultadoErroSemDirecao() {
		cenarioProcessamento.setMovel(new Sonda(new Posicao(4, 3)));
		Resultado executar = instrucao.executar(cenarioProcessamento);
		assertNotNull(executar);
		assertFalse(executar.getTipoResultado().isResultadoOk());
	}

}
