package br.com.formento.cockpitRemoto.test.service.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.CenarioProcessamentoImpl;
import br.com.formento.cockpitRemoto.model.Direcao;
import br.com.formento.cockpitRemoto.model.Malha;
import br.com.formento.cockpitRemoto.model.MalhaRetangular;
import br.com.formento.cockpitRemoto.model.Posicao;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.Sonda;
import br.com.formento.cockpitRemoto.service.command.InstrucaoRotacionarDireita;

public class InstrucaoRotacionarDireitaTest {

	private InstrucaoRotacionarDireita instrucao;
	private CenarioProcessamento cenarioProcessamento;

	@Before
	public void init() {
		Posicao posicaoMaxima = new Posicao(5, 5);
		Malha malha = new MalhaRetangular(posicaoMaxima);

		cenarioProcessamento = new CenarioProcessamentoImpl();
		cenarioProcessamento.setMalha(malha);
		instrucao = new InstrucaoRotacionarDireita();
	}

	@Test
	public void testExecutarAdicionarResultadoSucesso() {
		cenarioProcessamento.setMovel(new Sonda(new Posicao(4, 3, Direcao.NORTE)));
		Resultado executar = instrucao.executar(cenarioProcessamento);
		assertNotNull(executar);
		assertTrue(executar.getTipoResultado().isResultadoOk());
	}

	@Test
	public void testExecutarAdicionarResultadoErro() {
		cenarioProcessamento.setMovel(new Sonda(new Posicao(6, 3)));
		Resultado executar = instrucao.executar(cenarioProcessamento);
		assertNotNull(executar);
		assertFalse(executar.getTipoResultado().isResultadoOk());
	}

}
