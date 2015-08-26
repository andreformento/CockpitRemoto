package br.com.formento.cockpitRemoto.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.com.formento.cockpitRemoto.model.Direcao;

public class DirecaoTest {

	@Test
	public void testDirecaoPorSiglaExistente() {
		Direcao d = Direcao.getPorSigla("N");
		assertNotNull(d);
		assertEquals(Direcao.NORTE, d);
	}

	@Test
	public void testDirecaoPorSiglaNaoExistente() {
		Direcao d = Direcao.getPorSigla("kkk");
		assertNull(d);
	}

	@Test
	public void testProximoSentidoHorario() {
		assertEquals(Direcao.LESTE, Direcao.NORTE.getProximoSentidoHorario());
		assertEquals(Direcao.SUL, Direcao.LESTE.getProximoSentidoHorario());
		assertEquals(Direcao.NORTE, Direcao.OESTE.getProximoSentidoHorario());
	}

	@Test
	public void testProximoSentidoAntiHorario() {
		assertEquals(Direcao.OESTE, Direcao.NORTE.getProximoSentidoAntiHorario());
		assertEquals(Direcao.NORTE, Direcao.LESTE.getProximoSentidoAntiHorario());
		assertEquals(Direcao.LESTE, Direcao.SUL.getProximoSentidoAntiHorario());
	}

}
