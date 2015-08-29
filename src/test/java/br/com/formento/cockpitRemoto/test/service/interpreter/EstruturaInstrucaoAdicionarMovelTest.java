package br.com.formento.cockpitRemoto.test.service.interpreter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.interpreter.EstruturaInstrucaoAdicionarMovel;

public class EstruturaInstrucaoAdicionarMovelTest {

	@Test
	public void testEstruturaValidaSimples() {
		EstruturaInstrucaoAdicionarMovel estrutura = new EstruturaInstrucaoAdicionarMovel("5 4 N");

		Resultado resultadoEncontrouPadrao = estrutura.getResultadoEncontrouPadrao();
		assertTrue(resultadoEncontrouPadrao.toString(), resultadoEncontrouPadrao.getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());

		assertNotNull(estrutura.getMovel());
		assertTrue(estrutura.getMovel().isConsistente().getTipoResultado().isResultadoOk());
	}

	@Test
	public void testEstruturaValidaComEspacos() {
		EstruturaInstrucaoAdicionarMovel estrutura = new EstruturaInstrucaoAdicionarMovel("  587   47   N	");

		Resultado resultadoEncontrouPadrao = estrutura.getResultadoEncontrouPadrao();
		assertTrue(resultadoEncontrouPadrao.toString(), resultadoEncontrouPadrao.getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());

		assertNotNull(estrutura.getMovel());
		assertTrue(estrutura.getMovel().isConsistente().getTipoResultado().isResultadoOk());
	}

	@Test
	public void testEstruturaInvalidaFaltaDirecao() {
		EstruturaInstrucaoAdicionarMovel estrutura = new EstruturaInstrucaoAdicionarMovel("587   47");

		Resultado resultadoEncontrouPadrao = estrutura.getResultadoEncontrouPadrao();
		assertEquals(resultadoEncontrouPadrao.toString(), TipoResultado.AVISO, resultadoEncontrouPadrao.getTipoResultado());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertEquals(resultadoMontagem.toString(), TipoResultado.AVISO, resultadoMontagem.getTipoResultado());

		assertNull(estrutura.getMovel());
	}

	@Test
	public void testEstruturaInvalidaPosicaoSemY() {
		EstruturaInstrucaoAdicionarMovel estrutura = new EstruturaInstrucaoAdicionarMovel("587   N");

		Resultado resultadoEncontrouPadrao = estrutura.getResultadoEncontrouPadrao();
		assertEquals(resultadoEncontrouPadrao.toString(), TipoResultado.AVISO, resultadoEncontrouPadrao.getTipoResultado());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertEquals(resultadoMontagem.toString(), TipoResultado.AVISO, resultadoMontagem.getTipoResultado());

		assertNull(estrutura.getMovel());
	}

	@Test
	public void testEstruturaInvalidaDirecaoInvalida() {
		EstruturaInstrucaoAdicionarMovel estrutura = new EstruturaInstrucaoAdicionarMovel("5 6 Z");

		Resultado resultadoEncontrouPadrao = estrutura.getResultadoEncontrouPadrao();
		assertTrue(resultadoEncontrouPadrao.toString(), resultadoEncontrouPadrao.getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertFalse(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());

		assertNull(estrutura.getMovel());
	}

}
