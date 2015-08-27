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
import br.com.formento.cockpitRemoto.service.command.InstrucaoAdicionar;

public class InstrucaoAdicionarTest {

	private InstrucaoAdicionar instrucao;

	@Before
	public void init() {
		CenarioProcessamento cenarioProcessamento = new CenarioProcessamentoImpl();
		Posicao posicaoMaxima = new Posicao(5, 5);
		cenarioProcessamento.setMalha(new MalhaRetangular(posicaoMaxima));
		instrucao = new InstrucaoAdicionar();
		instrucao.configurarCenarioProcessamento(cenarioProcessamento);
	}

	@Test
	public void testExecutarAdicionarResultadoSucesso() {
		instrucao.getCenarioProcessamento().setMovel(new Sonda(new Posicao(4, 3, Direcao.NORTE)));
		Resultado executar = instrucao.executar();
		assertNotNull(executar);
		assertTrue(executar.getTipoResultado().isResultadoOk());
	}

	@Test
	public void testExecutarAdicionarResultadoErroPosicao() {
		instrucao.getCenarioProcessamento().setMovel(new Sonda(new Posicao(6, 3, Direcao.NORTE)));
		Resultado executar = instrucao.executar();
		assertNotNull(executar);
		assertFalse(executar.getTipoResultado().isResultadoOk());
	}

	@Test
	public void testExecutarAdicionarResultadoErroSemDirecao() {
		instrucao.getCenarioProcessamento().setMovel(new Sonda(new Posicao(4, 3)));
		Resultado executar = instrucao.executar();
		assertNotNull(executar);
		assertFalse(executar.getTipoResultado().isResultadoOk());
	}

}
