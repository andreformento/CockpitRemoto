package br.com.formento.cockpitRemoto.test.service.interpreter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.interpreter.EstruturaInstrucaoComentario;

public class EstruturaInstrucaoComentarioTest {

	@Test
	public void testEntradaValida() {
		EstruturaInstrucaoComentario estrutura = new EstruturaInstrucaoComentario("#");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());
	}

	@Test
	public void testEntradaValidaEspacosAntes() {
		EstruturaInstrucaoComentario estrutura = new EstruturaInstrucaoComentario("   #");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());
	}

	@Test
	public void testEntradaValidaSemEspacos() {
		EstruturaInstrucaoComentario estrutura = new EstruturaInstrucaoComentario("#");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());
	}

	@Test
	public void testEntradaValidaSemEspacosEscrito() {
		EstruturaInstrucaoComentario estrutura = new EstruturaInstrucaoComentario("# comentário");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());
	}

	@Test
	public void testEntradaInvalida() {
		EstruturaInstrucaoComentario estrutura = new EstruturaInstrucaoComentario("comando qualquer #");

		assertEquals(TipoResultado.AVISO, estrutura.getResultadoEncontrouPadrao().getTipoResultado());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertEquals(resultadoMontagem.toString(), TipoResultado.AVISO, resultadoMontagem.getTipoResultado());
	}

	@Test
	public void testEntradaInvalidaSemCaracterEspecial() {
		EstruturaInstrucaoComentario estrutura = new EstruturaInstrucaoComentario("comando qualquer");

		assertEquals(TipoResultado.AVISO, estrutura.getResultadoEncontrouPadrao().getTipoResultado());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertEquals(resultadoMontagem.toString(), TipoResultado.AVISO, resultadoMontagem.getTipoResultado());
	}

}
