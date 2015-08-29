package br.com.formento.cockpitRemoto.test.service.interpreter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.interpreter.EstruturaInstrucaoVazio;

public class EstruturaInstrucaoVazioTest {

	@Test
	public void testEntradaValidaEspacosETab() {
		EstruturaInstrucaoVazio estrutura = new EstruturaInstrucaoVazio("  		  ");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());
	}

	@Test
	public void testEntradaValidaEspacos() {
		EstruturaInstrucaoVazio estrutura = new EstruturaInstrucaoVazio("   ");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());
	}

	@Test
	public void testEntradaValidaSemEspacos() {
		EstruturaInstrucaoVazio estrutura = new EstruturaInstrucaoVazio("");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());
	}

	@Test
	public void testEntradaInvalida() {
		EstruturaInstrucaoVazio estrutura = new EstruturaInstrucaoVazio("mover");

		assertEquals(TipoResultado.AVISO, estrutura.getResultadoEncontrouPadrao().getTipoResultado());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertEquals(resultadoMontagem.toString(), TipoResultado.AVISO, resultadoMontagem.getTipoResultado());
	}

}
