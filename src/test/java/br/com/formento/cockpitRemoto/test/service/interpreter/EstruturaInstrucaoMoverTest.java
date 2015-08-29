package br.com.formento.cockpitRemoto.test.service.interpreter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.command.Instrucao;
import br.com.formento.cockpitRemoto.service.command.InstrucaoInvalida;
import br.com.formento.cockpitRemoto.service.command.InstrucaoMoverParaFrente;
import br.com.formento.cockpitRemoto.service.command.InstrucaoRotacionarDireita;
import br.com.formento.cockpitRemoto.service.command.InstrucaoRotacionarEsquerda;
import br.com.formento.cockpitRemoto.service.interpreter.EstruturaInstrucaoMovimento;

public class EstruturaInstrucaoMoverTest {

	@Test
	public void testUmaInstrucaoMoverEsquerda() {
		EstruturaInstrucaoMovimento estrutura = new EstruturaInstrucaoMovimento("L");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());

		List<Class<? extends Instrucao>> instrucaoClassList = estrutura.getInstrucaoClassList();
		assertNotNull(instrucaoClassList);
		assertFalse(instrucaoClassList.isEmpty());
		Class<? extends Instrucao> instrucaoClass = instrucaoClassList.get(0);
		assertNotNull(instrucaoClass);
		assertEquals(InstrucaoRotacionarEsquerda.class, instrucaoClass);
	}

	@Test
	public void testUmaInstrucaoMoverDireita() {
		EstruturaInstrucaoMovimento estrutura = new EstruturaInstrucaoMovimento("R");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());

		List<Class<? extends Instrucao>> instrucaoClassList = estrutura.getInstrucaoClassList();
		assertNotNull(instrucaoClassList);
		assertFalse(instrucaoClassList.isEmpty());
		Class<? extends Instrucao> instrucaoClass = instrucaoClassList.get(0);
		assertNotNull(instrucaoClass);
		assertEquals(InstrucaoRotacionarDireita.class, instrucaoClass);
	}

	@Test
	public void testUmaInstrucaoMoverParaFrente() {
		EstruturaInstrucaoMovimento estrutura = new EstruturaInstrucaoMovimento("M");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());

		List<Class<? extends Instrucao>> instrucaoClassList = estrutura.getInstrucaoClassList();
		assertNotNull(instrucaoClassList);
		assertFalse(instrucaoClassList.isEmpty());
		Class<? extends Instrucao> instrucaoClass = instrucaoClassList.get(0);
		assertNotNull(instrucaoClass);
		assertEquals(InstrucaoMoverParaFrente.class, instrucaoClass);
	}

	@Test
	public void testDuasInstrucoesValidas() {
		EstruturaInstrucaoMovimento estrutura = new EstruturaInstrucaoMovimento("ML");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertTrue(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());

		List<Class<? extends Instrucao>> instrucaoClassList = estrutura.getInstrucaoClassList();
		assertNotNull(instrucaoClassList);
		assertEquals(2, instrucaoClassList.size());

		Class<? extends Instrucao> instrucaoMoverParaFrenteClass = instrucaoClassList.get(0);
		assertNotNull(instrucaoMoverParaFrenteClass);
		assertEquals(InstrucaoMoverParaFrente.class, instrucaoMoverParaFrenteClass);

		Class<? extends Instrucao> instrucaoRotacionarEsquerdaClass = instrucaoClassList.get(1);
		assertNotNull(instrucaoRotacionarEsquerdaClass);
		assertEquals(InstrucaoRotacionarEsquerda.class, instrucaoRotacionarEsquerdaClass);
	}

	@Test
	public void testDuasInstrucoesInvalidas() {
		EstruturaInstrucaoMovimento estrutura = new EstruturaInstrucaoMovimento("MJ");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertFalse(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());

		List<Class<? extends Instrucao>> instrucaoClassList = estrutura.getInstrucaoClassList();
		assertNotNull(instrucaoClassList);
		assertEquals(2, instrucaoClassList.size());

		Class<? extends Instrucao> instrucaoMoverParaFrenteClass = instrucaoClassList.get(0);
		assertNotNull(instrucaoMoverParaFrenteClass);
		assertEquals(InstrucaoMoverParaFrente.class, instrucaoMoverParaFrenteClass);

		Class<? extends Instrucao> instrucaoInvalidaClass = instrucaoClassList.get(1);
		assertNotNull(instrucaoInvalidaClass);
		assertEquals(InstrucaoInvalida.class, instrucaoInvalidaClass);
	}

	@Test
	public void testTresInstrucoesInvalidas() {
		EstruturaInstrucaoMovimento estrutura = new EstruturaInstrucaoMovimento("MJL");

		assertTrue(estrutura.getResultadoEncontrouPadrao().getTipoResultado().isResultadoOk());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertFalse(resultadoMontagem.toString(), resultadoMontagem.getTipoResultado().isResultadoOk());

		List<Class<? extends Instrucao>> instrucaoClassList = estrutura.getInstrucaoClassList();
		assertNotNull(instrucaoClassList);
		assertEquals(2, instrucaoClassList.size());

		Class<? extends Instrucao> instrucaoMoverParaFrenteClass = instrucaoClassList.get(0);
		assertNotNull(instrucaoMoverParaFrenteClass);
		assertEquals(InstrucaoMoverParaFrente.class, instrucaoMoverParaFrenteClass);

		Class<? extends Instrucao> instrucaoInvalidaClass = instrucaoClassList.get(1);
		assertNotNull(instrucaoInvalidaClass);
		assertEquals(InstrucaoInvalida.class, instrucaoInvalidaClass);
	}

	@Test
	public void testSemInstrucao() {
		EstruturaInstrucaoMovimento estrutura = new EstruturaInstrucaoMovimento(" ");

		Resultado resultadoEncontrouPadrao = estrutura.getResultadoEncontrouPadrao();
		assertEquals(resultadoEncontrouPadrao.toString(), TipoResultado.AVISO, resultadoEncontrouPadrao.getTipoResultado());

		Resultado resultadoMontagem = estrutura.getResultadoMontagem();
		assertEquals(resultadoMontagem.toString(), TipoResultado.AVISO, resultadoMontagem.getTipoResultado());

		List<Class<? extends Instrucao>> instrucaoClassList = estrutura.getInstrucaoClassList();
		assertNotNull(instrucaoClassList);
		assertTrue(instrucaoClassList.isEmpty());
	}

}
