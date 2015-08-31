package br.com.formento.cockpitRemoto.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.CenarioProcessamentoImpl;
import br.com.formento.cockpitRemoto.model.Direcao;
import br.com.formento.cockpitRemoto.model.Malha;
import br.com.formento.cockpitRemoto.model.MalhaRetangular;
import br.com.formento.cockpitRemoto.model.Posicao;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.Sonda;
import br.com.formento.cockpitRemoto.model.TipoResultado;

public class CenarioProcessamentoImplTest {

	private CenarioProcessamento cenarioProcessamento;

	@Before
	public void init() {
		cenarioProcessamento = new CenarioProcessamentoImpl();

		Malha malha = new MalhaRetangular(new Posicao(7, 2));

		cenarioProcessamento.setMalha(malha);
		cenarioProcessamento.setMovel(new Sonda(new Posicao(2, 1, Direcao.SUL)));
		cenarioProcessamento.configurarResultado(new ResultadoImpl(TipoResultado.ERRO, "Mensagem qualquer"));
	}

	@Test
	public void testClone() throws CloneNotSupportedException {
		CenarioProcessamento clone = cenarioProcessamento.clone();

		assertEquals(cenarioProcessamento, clone);
		assertFalse(cenarioProcessamento == clone);

		assertEquals(cenarioProcessamento.getMalha(), clone.getMalha());
		assertFalse(cenarioProcessamento.getMalha() == clone.getMalha());

		assertEquals(cenarioProcessamento.getMovel(), clone.getMovel());
		assertFalse(cenarioProcessamento.getMovel() == clone.getMovel());

		assertEquals(cenarioProcessamento.isConsistente(), clone.isConsistente());
		assertFalse(cenarioProcessamento.isConsistente() == clone.isConsistente());
	}

}
