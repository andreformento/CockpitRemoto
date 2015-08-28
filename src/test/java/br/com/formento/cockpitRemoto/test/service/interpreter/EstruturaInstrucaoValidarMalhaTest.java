package br.com.formento.cockpitRemoto.test.service.interpreter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.formento.cockpitRemoto.model.MalhaRetangular;
import br.com.formento.cockpitRemoto.model.Posicao;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.interpreter.EstruturaInstrucaoValidarMalha;

public class EstruturaInstrucaoValidarMalhaTest {

	@Test
	public void testEntradaValida4x5() {
		EstruturaInstrucaoValidarMalha estrutura = new EstruturaInstrucaoValidarMalha("4 5");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());

		MalhaRetangular malhaRetangular = estrutura.getMalhaRetangular();
		assertNotNull(malhaRetangular);
		assertTrue(malhaRetangular.isConsistente().toString(), malhaRetangular.isConsistente().getTipoResultado().isResultadoOk());

		Posicao posicao = new Posicao(4, 5);
		assertEquals(posicao, malhaRetangular.getPosicaoMaxima());
	}

	@Test
	public void testEntradaValida80x120() {
		EstruturaInstrucaoValidarMalha estrutura = new EstruturaInstrucaoValidarMalha("80 120");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());

		MalhaRetangular malhaRetangular = estrutura.getMalhaRetangular();
		assertNotNull(malhaRetangular);
		assertTrue(malhaRetangular.isConsistente().toString(), malhaRetangular.isConsistente().getTipoResultado().isResultadoOk());

		Posicao posicao = new Posicao(80, 120);
		assertEquals(posicao, malhaRetangular.getPosicaoMaxima());
	}

	@Test
	public void testEntradaValidaVariosEspacosTab() {
		EstruturaInstrucaoValidarMalha estrutura = new EstruturaInstrucaoValidarMalha("	 7   5  ");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());

		MalhaRetangular malhaRetangular = estrutura.getMalhaRetangular();
		assertNotNull(malhaRetangular);
		assertTrue(malhaRetangular.isConsistente().toString(), malhaRetangular.isConsistente().getTipoResultado().isResultadoOk());

		Posicao posicao = new Posicao(7, 5);
		assertEquals(posicao, malhaRetangular.getPosicaoMaxima());
	}

	@Test
	public void testNaoEncontrouPadrao() {
		EstruturaInstrucaoValidarMalha estrutura = new EstruturaInstrucaoValidarMalha("mover");

		assertEquals(TipoResultado.AVISO, estrutura.getResultadoEncontrouPadrao().getTipoResultado());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertEquals(resultadoMontagem.toString(), TipoResultado.AVISO, resultadoMontagem.getTipoResultado());

		MalhaRetangular malhaRetangular = estrutura.getMalhaRetangular();
		assertNull(malhaRetangular);
	}

	@Test
	public void testNaoEncontrouPadraoMuitosNumeros() {
		EstruturaInstrucaoValidarMalha estrutura = new EstruturaInstrucaoValidarMalha("5 6 7");

		assertEquals(TipoResultado.AVISO, estrutura.getResultadoEncontrouPadrao().getTipoResultado());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertEquals(resultadoMontagem.toString(), TipoResultado.AVISO, resultadoMontagem.getTipoResultado());

		MalhaRetangular malhaRetangular = estrutura.getMalhaRetangular();
		assertNull(malhaRetangular);
	}

	@Test
	public void testNaoEncontrouPadraoMuitosParametros() {
		EstruturaInstrucaoValidarMalha estrutura = new EstruturaInstrucaoValidarMalha("5 6 N");

		assertEquals(TipoResultado.AVISO, estrutura.getResultadoEncontrouPadrao().getTipoResultado());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertEquals(resultadoMontagem.toString(), TipoResultado.AVISO, resultadoMontagem.getTipoResultado());

		MalhaRetangular malhaRetangular = estrutura.getMalhaRetangular();
		assertNull(malhaRetangular);
	}

	@Test
	public void testNaoEncontrouPadraoFaltaParametro() {
		EstruturaInstrucaoValidarMalha estrutura = new EstruturaInstrucaoValidarMalha("5");

		assertEquals(TipoResultado.AVISO, estrutura.getResultadoEncontrouPadrao().getTipoResultado());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertEquals(resultadoMontagem.toString(), TipoResultado.AVISO, resultadoMontagem.getTipoResultado());

		MalhaRetangular malhaRetangular = estrutura.getMalhaRetangular();
		assertNull(malhaRetangular);
	}

	@Test
	public void testNaoEncontrouPadraoNumeroInvalido() {
		EstruturaInstrucaoValidarMalha estrutura = new EstruturaInstrucaoValidarMalha("N 5");

		assertEquals(TipoResultado.AVISO, estrutura.getResultadoEncontrouPadrao().getTipoResultado());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertEquals(resultadoMontagem.toString(), TipoResultado.AVISO, resultadoMontagem.getTipoResultado());

		MalhaRetangular malhaRetangular = estrutura.getMalhaRetangular();
		assertNull(malhaRetangular);
	}

	@Test
	public void testNaoEncontrouPadraoQuebraDeLinha() {
		EstruturaInstrucaoValidarMalha estrutura = new EstruturaInstrucaoValidarMalha("5 \n 5");

		assertEquals(TipoResultado.AVISO, estrutura.getResultadoEncontrouPadrao().getTipoResultado());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertEquals(resultadoMontagem.toString(), TipoResultado.AVISO, resultadoMontagem.getTipoResultado());

		MalhaRetangular malhaRetangular = estrutura.getMalhaRetangular();
		assertNull(malhaRetangular);
	}

}
