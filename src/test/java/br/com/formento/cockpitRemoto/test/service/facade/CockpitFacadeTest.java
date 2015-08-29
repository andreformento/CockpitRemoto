package br.com.formento.cockpitRemoto.test.service.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Cockpit;
import br.com.formento.cockpitRemoto.model.Direcao;
import br.com.formento.cockpitRemoto.model.Malha;
import br.com.formento.cockpitRemoto.model.MalhaRetangular;
import br.com.formento.cockpitRemoto.model.Movel;
import br.com.formento.cockpitRemoto.model.Posicao;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.facade.CockpitFacade;

public class CockpitFacadeTest {

	private Cockpit cockpit;
	private List<String> comandoList;

	@Before
	public void init() {
		comandoList = new ArrayList<>();
		cockpit = new CockpitFacade(comandoList);
	}

	@Test
	public void testSemComandos() {
		Resultado resultado = cockpit.executar();
		assertNotNull(resultado);
		assertEquals(resultado.toString(), TipoResultado.AVISO, resultado.getTipoResultado());
	}

	@Test
	public void testConfigurarMalha() {
		comandoList.add("5 6");

		Resultado resultado = cockpit.executar();

		assertNotNull(resultado);
		assertTrue(resultado.toString(), resultado.getTipoResultado().isResultadoOk());
		assertNotNull(cockpit.getCenarioProcessamento());
		assertNotNull(cockpit.getCenarioProcessamento().getMalha());
		assertTrue(cockpit.getCenarioProcessamento().getMalha() instanceof MalhaRetangular);
		MalhaRetangular malhaRetangular = (MalhaRetangular) cockpit.getCenarioProcessamento().getMalha();
		assertNotNull(malhaRetangular.getPosicaoMaxima());
		assertEquals(new Posicao(5, 6), malhaRetangular.getPosicaoMaxima());
	}

	@Test
	public void testAdicionarMovel() {
		comandoList.add("5 6");
		comandoList.add("1 2 N");

		Resultado resultado = cockpit.executar();

		assertTrue(resultado.toString(), resultado.getTipoResultado().isResultadoOk());
		Movel movel = cockpit.getCenarioProcessamento().getMovel();
		assertNotNull(movel);
		assertNotNull(movel.getPosicao());
		Posicao posicao = new Posicao(1, 2, Direcao.NORTE);
		assertEquals(posicao, movel.getPosicao());
		assertEquals(posicao.getDirecao(), movel.getPosicao().getDirecao());
	}

	@Test
	public void testUmMovelUmComando() {
		comandoList.add("5 6");
		comandoList.add("1 2 N");
		comandoList.add("M"); // 1 3 N

		Resultado resultado = cockpit.executar();

		assertTrue(resultado.toString(), resultado.getTipoResultado().isResultadoOk());
		Movel movel = cockpit.getCenarioProcessamento().getMovel();
		assertNotNull(movel);
		assertNotNull(movel.getPosicao());
		Posicao posicao = new Posicao(1, 3, Direcao.NORTE);
		assertEquals(posicao, movel.getPosicao());
		assertEquals(posicao.getDirecao(), movel.getPosicao().getDirecao());
	}

	@Test
	public void testUmMovelVariosComandos() {
		comandoList.add("5 6");
		comandoList.add("1 2 N");
		comandoList.add("LMLMLMLMM"); // 1 3 N

		Resultado resultado = cockpit.executar();

		assertTrue(resultado.toString(), resultado.getTipoResultado().isResultadoOk());
		Movel movel = cockpit.getCenarioProcessamento().getMovel();
		assertNotNull(movel);
		assertNotNull(movel.getPosicao());
		Posicao posicao = new Posicao(1, 3, Direcao.NORTE);
		assertEquals(posicao, movel.getPosicao());
		assertEquals(posicao.getDirecao(), movel.getPosicao().getDirecao());
	}

	@Test
	public void testVariosMoveisVariosComandos() {
		comandoList.add("5 6");
		comandoList.add("1 2 N");
		comandoList.add("LMLMLMLMM"); // 1 3 N
		comandoList.add("3 3 E");
		comandoList.add("MMRMMRMRRM"); // 5 1 E

		Resultado resultado = cockpit.executar();

		assertTrue(resultado.toString(), resultado.getTipoResultado().isResultadoOk());

		CenarioProcessamento cenarioProcessamento = cockpit.getCenarioProcessamento();
		Malha malha = cenarioProcessamento.getMalha();

		Posicao posicao13 = new Posicao(1, 3, Direcao.NORTE);
		Movel movel12para13 = malha.getByPosicao(posicao13);
		assertNotNull(movel12para13);
		assertNotNull(movel12para13.getPosicao());
		assertEquals(posicao13, movel12para13.getPosicao());
		assertEquals(posicao13.getDirecao(), movel12para13.getPosicao().getDirecao());

		Posicao posicao51 = new Posicao(5, 1, Direcao.LESTE);
		Movel movel33para51 = malha.getByPosicao(posicao51);
		assertNotNull(movel33para51);
		assertNotNull(movel33para51.getPosicao());
		assertEquals(posicao51, movel33para51.getPosicao());
		assertEquals(posicao51.getDirecao(), movel33para51.getPosicao().getDirecao());
	}

	@Test
	public void testPrimeiroComandoInvalido() {
		comandoList.add("inválido");

		Resultado resultado = cockpit.executar();

		assertNotNull(resultado);
		assertFalse(resultado.toString(), resultado.getTipoResultado().isResultadoOk());
		assertNotNull(cockpit.getCenarioProcessamento());
		assertNull(cockpit.getCenarioProcessamento().getMalha());
		assertNull(cockpit.getCenarioProcessamento().getMovel());
	}

	@Test
	public void testSegundoComandoInvalido() {
		comandoList.add("10 302");
		comandoList.add("inválido");
		comandoList.add("1 2 N"); // nao deve chegar nesse comando

		Resultado resultado = cockpit.executar();

		assertNotNull(resultado);
		assertFalse(resultado.toString(), resultado.getTipoResultado().isResultadoOk());
		assertNotNull(cockpit.getCenarioProcessamento());
		assertNotNull(cockpit.getCenarioProcessamento().getMalha());
		assertTrue(cockpit.getCenarioProcessamento().getMalha() instanceof MalhaRetangular);
		Malha malha = cockpit.getCenarioProcessamento().getMalha();
		MalhaRetangular malhaRetangular = (MalhaRetangular) malha;
		assertNotNull(malhaRetangular.getPosicaoMaxima());
		assertEquals(new Posicao(10, 302), malhaRetangular.getPosicaoMaxima());

		assertTrue(String.valueOf(malha.getMoveis()), malha.getMoveis().isEmpty());
	}

	@Test
	public void testLocalIndisponivel() {
		comandoList.add("2 2");
		comandoList.add("3 1 N");

		Resultado resultado = cockpit.executar();

		assertNotNull(resultado);
		assertFalse(resultado.toString(), resultado.getTipoResultado().isResultadoOk());
		assertNotNull(cockpit.getCenarioProcessamento());
		assertNotNull(cockpit.getCenarioProcessamento().getMalha());
		assertTrue(cockpit.getCenarioProcessamento().getMalha() instanceof MalhaRetangular);
		Malha malha = cockpit.getCenarioProcessamento().getMalha();
		MalhaRetangular malhaRetangular = (MalhaRetangular) malha;
		assertNotNull(malhaRetangular.getPosicaoMaxima());
		assertEquals(new Posicao(2, 2), malhaRetangular.getPosicaoMaxima());

		assertTrue(String.valueOf(malha.getMoveis()), malha.getMoveis().isEmpty());
	}

	@Test
	public void testLinhasEmBranco() {
		comandoList.add("  		  	");

		Resultado resultado = cockpit.executar();
		assertNotNull(resultado);
		assertTrue(resultado.toString(), resultado.getTipoResultado().isResultadoOk());
	}

	@Test
	public void testComentario() {
		comandoList.add("5 6");
		comandoList.add(" #meu comentario");
		comandoList.add("1 2 N");

		Resultado resultado = cockpit.executar();

		assertTrue(resultado.toString(), resultado.getTipoResultado().isResultadoOk());
		Movel movel = cockpit.getCenarioProcessamento().getMovel();
		assertNotNull(movel);
		assertNotNull(movel.getPosicao());
		Posicao posicao = new Posicao(1, 2, Direcao.NORTE);
		assertEquals(posicao, movel.getPosicao());
		assertEquals(posicao.getDirecao(), movel.getPosicao().getDirecao());
	}

}
