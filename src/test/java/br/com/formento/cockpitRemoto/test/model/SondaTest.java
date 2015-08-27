package br.com.formento.cockpitRemoto.test.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.formento.cockpitRemoto.model.Direcao;
import br.com.formento.cockpitRemoto.model.Posicao;
import br.com.formento.cockpitRemoto.model.Sonda;

public class SondaTest {

	private Sonda sonda;

	@Before
	public void init() {
		Posicao posicao = new Posicao(5, 5, Direcao.NORTE);
		sonda = new Sonda(posicao);
	}

	@Test
	public void testIsConsistenteCorreta() {
		assertTrue(sonda.isConsistente().getTipoResultado().isResultadoOk());
	}

	@Test
	public void testIsConsistenteSemPosicao() {
		Sonda sondaSemDirecao = new Sonda(null);
		assertFalse(sondaSemDirecao.isConsistente().getTipoResultado().isResultadoOk());
	}

	@Test
	public void testIsConsistenteSemDirecao() {
		Sonda sondaSemPosicao = new Sonda(new Posicao(5, 5));
		assertFalse(sondaSemPosicao.isConsistente().getTipoResultado().isResultadoOk());
	}

}
