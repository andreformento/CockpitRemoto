package br.com.formento.cockpitRemoto.test.service.iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.CenarioProcessamentoImpl;
import br.com.formento.cockpitRemoto.model.MalhaRetangular;
import br.com.formento.cockpitRemoto.model.Posicao;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.command.Instrucao;
import br.com.formento.cockpitRemoto.service.command.InstrucaoValidarMalha;
import br.com.formento.cockpitRemoto.service.iterator.IteradorInstrucao;
import br.com.formento.cockpitRemoto.service.iterator.IteradorInstrucaoImpl;

public class IteradorInstrucaoImplTest {

	private IteradorInstrucao iteradorInstrucao;
	private List<Instrucao> listaPercorrer;

	@Before
	public void init() {
		CenarioProcessamento cenarioProcessamento = new CenarioProcessamentoImpl();
		listaPercorrer = new ArrayList<>();

		iteradorInstrucao = new IteradorInstrucaoImpl(cenarioProcessamento, listaPercorrer);
	}

	@Test
	public void testListaInstruacaoVazia() {
		Resultado resultado = iteradorInstrucao.percorrerLista();
		assertTrue(resultado.toString(), resultado.getTipoResultado().isResultadoOk());
	}

	// deve haver ao menos uma instrução válida
	@Test
	public void testComInstrucoesValidas() {
		iteradorInstrucao.getCenarioProcessamento().setMalha(new MalhaRetangular(new Posicao(1, 1)));
		listaPercorrer.add(new InstrucaoValidarMalha());
		Resultado resultado = iteradorInstrucao.percorrerLista();
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());
	}

	@Test
	public void testComInstrucoesInvalidas() {
		listaPercorrer.add(new InstrucaoValidarMalha());
		Resultado resultado = iteradorInstrucao.percorrerLista();
		assertFalse(resultado.toString(), resultado.getTipoResultado().isResultadoOk());
	}

}
