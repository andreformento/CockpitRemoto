package br.com.formento.cockpitRemoto.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.formento.cockpitRemoto.model.Direcao;
import br.com.formento.cockpitRemoto.model.Posicao;

public class PosicaoTest {

	private Posicao posicaoMaxima;

	@Before
	public void init() {
		posicaoMaxima = new Posicao(5, 5);
	}

	@Test
	public void testComparaToXMaior() {
		Posicao posicaoXMaior = new Posicao(6, 4);
		assertTrue(posicaoXMaior.compareTo(posicaoMaxima) > 0);
		assertTrue(posicaoMaxima.compareTo(posicaoXMaior) > 0);
	}

	@Test
	public void testComparaToYMaior() {
		Posicao posicaoYMaior = new Posicao(3, 6);
		assertTrue(posicaoYMaior.compareTo(posicaoMaxima) > 0);
		assertTrue(posicaoMaxima.compareTo(posicaoYMaior) > 0);
	}

	@Test
	public void testComparaToXYMaior() {
		Posicao posicaoXYMaior = new Posicao(8, 8);
		assertTrue(posicaoXYMaior.compareTo(posicaoMaxima) > 0);
		assertTrue(posicaoMaxima.compareTo(posicaoXYMaior) < 0);
	}

	@Test
	public void testComparaToXMenor() {
		Posicao posicaoXMenor = new Posicao(2, 5);
		assertTrue(posicaoXMenor.compareTo(posicaoMaxima) < 0);
		assertTrue(posicaoMaxima.compareTo(posicaoXMenor) > 0);
	}

	@Test
	public void testComparaToYMenor() {
		Posicao posicaoYMenor = new Posicao(5, 0);
		assertTrue(posicaoYMenor.compareTo(posicaoMaxima) < 0);
		assertTrue(posicaoMaxima.compareTo(posicaoYMenor) > 0);
	}

	@Test
	public void testComparaToXYMenor() {
		Posicao posicaoXYMenor = new Posicao(4, 4);
		assertTrue(posicaoXYMenor.compareTo(posicaoMaxima) < 0);
		assertTrue(posicaoMaxima.compareTo(posicaoXYMenor) > 0);
	}

	@Test
	public void testComparaToXYEqual() {
		Posicao posicaoXYMenor = new Posicao(5, 5);
		assertEquals(0, posicaoXYMenor.compareTo(posicaoMaxima));
		assertEquals(0, posicaoMaxima.compareTo(posicaoXYMenor));
		assertEquals(posicaoMaxima, posicaoXYMenor);
	}

	@Test
	public void testIsConsistenteSemDirecao() {
		Posicao posicaoSemDirecao = new Posicao(5, 5);
		assertFalse(posicaoSemDirecao.isConsistente().getTipoResultado().isResultadoOk());
	}

	@Test
	public void testIsConsistenteComDirecao() {
		Posicao posicaoComDirecao = new Posicao(5, 5, Direcao.LESTE);
		assertTrue(posicaoComDirecao.isConsistente().getTipoResultado().isResultadoOk());
	}

	@Test
	public void testClone() throws CloneNotSupportedException {
		Posicao posicao = new Posicao(222, 333);

		Posicao clone = posicao.clone();

		assertEquals(posicao, clone);
		assertFalse(posicao == clone);

		assertEquals(posicao.getDirecao(), clone.getDirecao());
		assertFalse(posicao == clone);

		assertEquals(posicao.isConsistente(), clone.isConsistente());
		assertFalse(posicao.isConsistente() == clone.isConsistente());
	}

}
