package br.com.formento.cockpitRemoto.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.formento.cockpitRemoto.model.Malha;
import br.com.formento.cockpitRemoto.model.MalhaRetangular;
import br.com.formento.cockpitRemoto.model.Posicao;
import br.com.formento.cockpitRemoto.model.Sonda;

public class MalhaRetangularTest {

	private Malha malha;
	private Posicao posicao44;
	private Sonda sonda44;

	@Before
	public void init() {
		malha = new MalhaRetangular(new Posicao(5, 5));
		posicao44 = new Posicao(4, 4);
		sonda44 = new Sonda(posicao44);
		malha.adicionarMovel(sonda44);
	}

	@Test
	public void testIsPosicaoDisponivel() {
		assertTrue(malha.isPosicaoDisponivel(new Posicao(5, 5)).getTipoResultado().isResultadoOk());
		assertTrue(malha.isPosicaoDisponivel(new Posicao(0, 0)).getTipoResultado().isResultadoOk());
		assertTrue(malha.isPosicaoDisponivel(new Posicao(1, 1)).getTipoResultado().isResultadoOk());
	}

	@Test
	public void testIsPosicaoNaoDisponivel() {
		assertFalse(malha.isPosicaoDisponivel(new Posicao(6, 5)).getTipoResultado().isResultadoOk());
		assertFalse(malha.isPosicaoDisponivel(new Posicao(0, 6)).getTipoResultado().isResultadoOk());
		assertFalse(malha.isPosicaoDisponivel(posicao44).getTipoResultado().isResultadoOk());
	}

	@Test
	public void testAdicionarMovel() {
		assertTrue(malha.adicionarMovel(new Sonda(new Posicao(1, 2))).getTipoResultado().isResultadoOk());
	}

	@Test
	public void testAdicionarMovelNaoDisponivel() {
		assertFalse(malha.adicionarMovel(new Sonda(posicao44)).getTipoResultado().isResultadoOk());
	}

	@Test
	public void testRemoverMovelNaoDisponivel() {
		assertEquals(sonda44, malha.removerMovel(posicao44));
		assertTrue(malha.isPosicaoDisponivel(posicao44).getTipoResultado().isResultadoOk());
	}

	@Test
	public void testTrocarPosicao() {
		Posicao posicao32 = new Posicao(3, 2);
		assertTrue(malha.trocarPosicao(posicao44, posicao32).getTipoResultado().isResultadoOk());
		assertTrue(malha.isPosicaoDisponivel(posicao44).getTipoResultado().isResultadoOk());
		assertFalse(malha.isPosicaoDisponivel(posicao32).getTipoResultado().isResultadoOk());
	}

	@Test
	public void testGetMoveis() {
		assertFalse(malha.getMoveis().isEmpty());
	}

}
