package br.com.formento.cockpitRemoto.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.formento.cockpitRemoto.model.Direcao;
import br.com.formento.cockpitRemoto.model.Malha;
import br.com.formento.cockpitRemoto.model.MalhaRetangular;
import br.com.formento.cockpitRemoto.model.Movel;
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
		Malha malhaTrocar = new MalhaRetangular(new Posicao(4, 5));

		Posicao posicaoTrocar32 = new Posicao(3, 2, Direcao.NORTE);
		Posicao posicaoTrocar33 = new Posicao(3, 3, Direcao.NORTE);

		assertTrue(malhaTrocar.isPosicaoDisponivel(posicaoTrocar32).getTipoResultado().isResultadoOk());
		assertTrue(malhaTrocar.isPosicaoDisponivel(posicaoTrocar33).getTipoResultado().isResultadoOk());

		Movel sonda = new Sonda(posicaoTrocar32);
		assertTrue(malhaTrocar.adicionarMovel(sonda).getTipoResultado().isResultadoOk());
		assertFalse(malhaTrocar.isPosicaoDisponivel(posicaoTrocar32).getTipoResultado().isResultadoOk());
		assertTrue(malhaTrocar.isPosicaoDisponivel(posicaoTrocar33).getTipoResultado().isResultadoOk());

		assertTrue(malhaTrocar.trocarPosicao(posicaoTrocar32, posicaoTrocar33).getTipoResultado().isResultadoOk());
		assertTrue(malhaTrocar.isPosicaoDisponivel(posicaoTrocar32).getTipoResultado().isResultadoOk());
		assertFalse(malhaTrocar.isPosicaoDisponivel(posicaoTrocar33).getTipoResultado().isResultadoOk());
	}

	@Test
	public void testGetMoveis() {
		assertFalse(malha.getMoveis().isEmpty());
	}

	@Test
	public void testByPosicao() {
		Movel movelbyPosicao = malha.getByPosicao(posicao44);
		assertNotNull(movelbyPosicao);
		assertEquals(posicao44, movelbyPosicao.getPosicao());
		assertEquals(posicao44.getDirecao(), movelbyPosicao.getPosicao().getDirecao());
	}

	@Test
	public void testValidandoOrdemDeInsercao() {
		Malha malhaOrdem = new MalhaRetangular(new Posicao(5, 5));
		Sonda movel0 = new Sonda(new Posicao(3, 4, Direcao.NORTE));
		assertTrue(malhaOrdem.adicionarMovel(movel0).getTipoResultado().isResultadoOk());
		Sonda movel1 = new Sonda(new Posicao(0, 1, Direcao.SUL));
		assertTrue(malhaOrdem.adicionarMovel(movel1).getTipoResultado().isResultadoOk());
		Sonda movel2 = new Sonda(new Posicao(1, 0, Direcao.LESTE));
		assertTrue(malhaOrdem.adicionarMovel(movel2).getTipoResultado().isResultadoOk());
		Sonda movel3 = new Sonda(new Posicao(3, 2, Direcao.OESTE));
		assertTrue(malhaOrdem.adicionarMovel(movel3).getTipoResultado().isResultadoOk());
		Sonda movel4 = new Sonda(new Posicao(4, 3, Direcao.SUL));
		assertTrue(malhaOrdem.adicionarMovel(movel4).getTipoResultado().isResultadoOk());

		assertEquals(Integer.valueOf(0), movel0.getOrdem());
		assertEquals(Integer.valueOf(1), movel1.getOrdem());
		assertEquals(Integer.valueOf(2), movel2.getOrdem());
		assertEquals(Integer.valueOf(3), movel3.getOrdem());
		assertEquals(Integer.valueOf(4), movel4.getOrdem());
	}

}
