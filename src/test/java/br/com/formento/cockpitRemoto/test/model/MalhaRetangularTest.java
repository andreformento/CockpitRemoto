package br.com.formento.cockpitRemoto.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

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
	public void testGetMoveisOrdemDeInsert() {
		MalhaRetangular malhaRetangularOrdemDeInsert = new MalhaRetangular(posicao44);

		List<Movel> listaAssert = new ArrayList<>();

		listaAssert.add(new Sonda(new Posicao(4, 3)));
		malhaRetangularOrdemDeInsert.adicionarMovel(listaAssert.get(0));
		listaAssert.add(new Sonda(new Posicao(3, 4)));
		malhaRetangularOrdemDeInsert.adicionarMovel(listaAssert.get(1));
		listaAssert.add(new Sonda(new Posicao(2, 1)));
		malhaRetangularOrdemDeInsert.adicionarMovel(listaAssert.get(2));
		listaAssert.add(new Sonda(new Posicao(2, 2)));
		malhaRetangularOrdemDeInsert.adicionarMovel(listaAssert.get(3));

		Collection<Movel> ordemDeInsert = malhaRetangularOrdemDeInsert.getMoveisOrdemInsert();
		assertFalse(ordemDeInsert.isEmpty());
		Object[] movelOrdenado = ordemDeInsert.toArray();
		assertEquals(listaAssert.size(), movelOrdenado.length);

		for (int i = 0; i < listaAssert.size(); i++)
			assertEquals("Erro no índice: " + i, listaAssert.get(i), movelOrdenado[i]);
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

	@Test
	public void testClone() throws CloneNotSupportedException {
		MalhaRetangular malhaClonar = new MalhaRetangular(new Posicao(7, 2));
		malhaClonar.adicionarMovel(new Sonda(new Posicao(3, 1, Direcao.NORTE)));

		MalhaRetangular clone = malhaClonar.clone();

		assertEquals(malhaClonar, clone);
		assertFalse(malhaClonar == clone);

		assertEquals(malhaClonar.getMoveis(), clone.getMoveis());
		assertFalse(malhaClonar.getMoveis() == clone.getMoveis());

		assertEquals(malhaClonar.getMoveis().size(), clone.getMoveis().size());
		for (Entry<Posicao, Movel> item : malhaClonar.getMoveis().entrySet()) {
			Movel movelClone = clone.getMoveis().get(item.getKey());
			assertNotNull(movelClone);

			assertEquals(item.getValue(), movelClone);
			assertFalse(item.getValue() == movelClone);
		}

		assertEquals(malhaClonar.getPosicaoMaxima(), clone.getPosicaoMaxima());
		assertFalse(malhaClonar.getPosicaoMaxima() == clone.getPosicaoMaxima());

		assertEquals(malhaClonar.isConsistente(), clone.isConsistente());
		assertFalse(malhaClonar.isConsistente() == clone.isConsistente());
	}

}
