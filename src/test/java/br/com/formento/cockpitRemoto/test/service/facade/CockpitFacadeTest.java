package br.com.formento.cockpitRemoto.test.service.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.formento.cockpitRemoto.model.Cockpit;
import br.com.formento.cockpitRemoto.model.Direcao;
import br.com.formento.cockpitRemoto.model.Entrada;
import br.com.formento.cockpitRemoto.model.Malha;
import br.com.formento.cockpitRemoto.model.MalhaRetangular;
import br.com.formento.cockpitRemoto.model.Movel;
import br.com.formento.cockpitRemoto.model.Posicao;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.facade.CockpitFacade;
import br.com.formento.cockpitRemoto.service.factory.EntradaBuilder;

public class CockpitFacadeTest {

	private Cockpit cockpit;

	@Mock
	private EntradaBuilder entradaBuilder;

	@Mock
	private Entrada entrada;

	private List<String> comandoList;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		comandoList = new ArrayList<String>();

		when(entrada.getComandoList()).thenReturn(comandoList);
		when(entradaBuilder.getProduct()).thenReturn(entrada);

		cockpit = new CockpitFacade();
	}

	@Test
	public void testCenarioProcessamentoEstaConfigurado() {
		assertNotNull(cockpit.getCenarioProcessamento());
	}

	@Test
	public void testSemComandos() {
		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertNotNull(resultado);
		assertEquals(resultado.toString(), TipoResultado.AVISO, resultado.getTipoResultado());
	}

	@Test
	public void testConfigurarMalha() {
		comandoList.add("5 6");

		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertNotNull(resultado);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());

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

		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());

		Movel movel = cockpit.getCenarioProcessamento().getMovel();
		assertNotNull(movel);
		assertNotNull(movel.getPosicao());
		Posicao posicao = new Posicao(1, 2, Direcao.NORTE);
		assertEquals(posicao, movel.getPosicao());
		assertEquals(posicao.getDirecao(), movel.getPosicao().getDirecao());
	}

	@Test
	public void testDesfazerLote() {
		comandoList.add("5 6");
		comandoList.add("1 2 N");

		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());

		Movel movel = cockpit.getCenarioProcessamento().getMovel();
		assertNotNull(movel);
		assertNotNull(movel.getPosicao());
		Posicao posicao = new Posicao(1, 2, Direcao.NORTE);
		assertEquals(posicao, movel.getPosicao());
		assertEquals(posicao.getDirecao(), movel.getPosicao().getDirecao());

		resultado = cockpit.desfazerExecutarLote();
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());
		assertNull(cockpit.getCenarioProcessamento().getMovel());
		assertNull(cockpit.getCenarioProcessamento().getMalha());
	}

	@Test
	public void testDesfazerApenasUltimoLote() {
		comandoList.add("5 6");
		comandoList.add("1 2 N");

		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());

		assertNotNull(cockpit.getCenarioProcessamento().getMovel());
		assertNotNull(cockpit.getCenarioProcessamento().getMovel().getPosicao());
		Posicao posicao = new Posicao(1, 2, Direcao.NORTE);
		assertEquals(posicao, cockpit.getCenarioProcessamento().getMovel().getPosicao());
		assertEquals(posicao.getDirecao(), cockpit.getCenarioProcessamento().getMovel().getPosicao().getDirecao());

		comandoList.clear();
		comandoList.add("M"); // 1 3 N
		resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());
		posicao = new Posicao(1, 3, Direcao.NORTE);
		assertEquals(posicao, cockpit.getCenarioProcessamento().getMovel().getPosicao());
		assertEquals(posicao.getDirecao(), cockpit.getCenarioProcessamento().getMovel().getPosicao().getDirecao());

		resultado = cockpit.desfazerExecutarLote();
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());
		posicao = new Posicao(1, 2, Direcao.NORTE);
		assertEquals(posicao, cockpit.getCenarioProcessamento().getMovel().getPosicao());
		assertEquals(posicao.getDirecao(), cockpit.getCenarioProcessamento().getMovel().getPosicao().getDirecao());
	}

	@Test
	public void testDesfazerApenasUltimoLoteVariosComandos() {
		comandoList.add("5 6");
		comandoList.add("1 2 N");

		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());

		assertNotNull(cockpit.getCenarioProcessamento().getMovel());
		assertNotNull(cockpit.getCenarioProcessamento().getMovel().getPosicao());
		Posicao posicao = new Posicao(1, 2, Direcao.NORTE);
		assertEquals(posicao, cockpit.getCenarioProcessamento().getMovel().getPosicao());
		assertEquals(posicao.getDirecao(), cockpit.getCenarioProcessamento().getMovel().getPosicao().getDirecao());

		comandoList.clear();
		comandoList.add("MR"); // 1 3 L
		comandoList.add("M"); // 2 3 L
		resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());
		posicao = new Posicao(2, 3, Direcao.LESTE);
		assertEquals(posicao, cockpit.getCenarioProcessamento().getMovel().getPosicao());
		assertEquals(posicao.getDirecao(), cockpit.getCenarioProcessamento().getMovel().getPosicao().getDirecao());

		resultado = cockpit.desfazerExecutarLote();
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());
		posicao = new Posicao(1, 2, Direcao.NORTE);
		assertEquals(posicao, cockpit.getCenarioProcessamento().getMovel().getPosicao());
		assertEquals(posicao.getDirecao(), cockpit.getCenarioProcessamento().getMovel().getPosicao().getDirecao());
	}

	@Test
	public void testConfiguraAdicionaAdicionaErro() {
		comandoList.add("5 6");
		comandoList.add("1 2 N");

		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());

		assertNotNull(cockpit.getCenarioProcessamento().getMovel());
		assertNotNull(cockpit.getCenarioProcessamento().getMovel().getPosicao());
		Posicao posicao = new Posicao(1, 2, Direcao.NORTE);
		assertEquals(posicao, cockpit.getCenarioProcessamento().getMovel().getPosicao());
		assertEquals(posicao.getDirecao(), cockpit.getCenarioProcessamento().getMovel().getPosicao().getDirecao());

		comandoList.clear();
		comandoList.add("3 2 N");
		comandoList.add("comando inválido");
		resultado = cockpit.executarLote(entradaBuilder);
		assertFalse(resultado.toString(), resultado.getTipoResultado().isResultadoOk());
		assertNotNull(cockpit.getCenarioProcessamento().getMovel());
		assertNotNull(cockpit.getCenarioProcessamento().getMovel().getPosicao());
		posicao = new Posicao(1, 2, Direcao.NORTE);
		assertEquals(posicao, cockpit.getCenarioProcessamento().getMovel().getPosicao());
		assertEquals(posicao.getDirecao(), cockpit.getCenarioProcessamento().getMovel().getPosicao().getDirecao());
		assertFalse(cockpit.getCenarioProcessamento().getMalha().isPosicaoDisponivel(posicao).getTipoResultado().isResultadoOk());

		posicao = new Posicao(3, 2, Direcao.NORTE);
		assertTrue(cockpit.getCenarioProcessamento().getMalha().isPosicaoDisponivel(posicao).getTipoResultado().isResultadoOk());
	}

	@Test
	public void testDesfazerApenasUltimoLoteERefazerNovamente() {
		Posicao posicao12N = new Posicao(1, 2, Direcao.NORTE);
		Posicao posicao13N = new Posicao(1, 3, Direcao.NORTE);

		comandoList.add("5 6");
		comandoList.add("1 2 N");

		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());

		assertNotNull(cockpit.getCenarioProcessamento().getMovel());
		assertNotNull(cockpit.getCenarioProcessamento().getMovel().getPosicao());
		assertEquals(posicao12N, cockpit.getCenarioProcessamento().getMovel().getPosicao());
		assertEquals(posicao12N.getDirecao(), cockpit.getCenarioProcessamento().getMovel().getPosicao().getDirecao());

		comandoList.clear();
		comandoList.add("M"); // 1 3 N

		// executa a primeira vez
		resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());
		assertEquals(posicao13N, cockpit.getCenarioProcessamento().getMovel().getPosicao());
		assertEquals(posicao13N.getDirecao(), cockpit.getCenarioProcessamento().getMovel().getPosicao().getDirecao());

		// desfaz - volta o comando de mover
		resultado = cockpit.desfazerExecutarLote();
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());
		assertEquals(posicao12N, cockpit.getCenarioProcessamento().getMovel().getPosicao());
		assertEquals(posicao12N.getDirecao(), cockpit.getCenarioProcessamento().getMovel().getPosicao().getDirecao());
		assertFalse(cockpit.getCenarioProcessamento().getMalha().isPosicaoDisponivel(posicao12N).getTipoResultado().isResultadoOk());
		assertTrue(cockpit.getCenarioProcessamento().getMalha().isPosicaoDisponivel(posicao13N).getTipoResultado().isResultadoOk());

		// refaz
		resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());
		assertFalse(cockpit.getCenarioProcessamento().getMalha().isPosicaoDisponivel(posicao13N).getTipoResultado().isResultadoOk());
		assertEquals(posicao13N, cockpit.getCenarioProcessamento().getMovel().getPosicao());
		assertEquals(posicao13N.getDirecao(), cockpit.getCenarioProcessamento().getMovel().getPosicao().getDirecao());
		assertEquals(1, cockpit.getCenarioProcessamento().getMalha().getMoveisOrdemInsert().size());
	}

	@Test
	public void testUmMovelUmComando() {
		comandoList.add("5 6");
		comandoList.add("1 2 N");
		comandoList.add("M"); // 1 3 N

		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());

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

		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());

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

		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());

		Malha malha = cockpit.getCenarioProcessamento().getMalha();

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
	public void testVariosLotes() {
		// primeiro lote de processamentos
		comandoList.add("5 6");
		comandoList.add("1 2 N");
		comandoList.add("LMLMLMLMM"); // 1 3 N

		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());

		Malha malha = cockpit.getCenarioProcessamento().getMalha();

		Posicao posicao13 = new Posicao(1, 3, Direcao.NORTE);
		Movel movel12para13 = malha.getByPosicao(posicao13);
		assertNotNull(movel12para13);
		assertNotNull(movel12para13.getPosicao());
		assertEquals(posicao13, movel12para13.getPosicao());
		assertEquals(posicao13.getDirecao(), movel12para13.getPosicao().getDirecao());

		// segundo lote de processamentos
		comandoList.clear();
		comandoList.add("3 3 E");
		comandoList.add("MMRMMRMRRM"); // 5 1 E

		resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());

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

		Resultado resultado = cockpit.executarLote(entradaBuilder);
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

		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertNotNull(resultado);
		assertFalse(resultado.toString(), resultado.getTipoResultado().isResultadoOk());

		assertNotNull(cockpit.getCenarioProcessamento());
		assertNull(cockpit.getCenarioProcessamento().getMalha());
	}

	@Test
	public void testLocalIndisponivel() {
		comandoList.add("2 2");
		comandoList.add("3 1 N");

		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertNotNull(resultado);
		assertFalse(resultado.toString(), resultado.getTipoResultado().isResultadoOk());

		assertNotNull(cockpit.getCenarioProcessamento());
		assertNull(cockpit.getCenarioProcessamento().getMalha());
	}

	@Test
	public void testLinhasEmBranco() {
		comandoList.add("  		  	");

		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertNotNull(resultado);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());
	}

	@Test
	public void testComentario() {
		comandoList.add("5 6");
		comandoList.add(" #meu comentario");
		comandoList.add("1 2 N");

		Resultado resultado = cockpit.executarLote(entradaBuilder);
		assertEquals(resultado.toString(), TipoResultado.SUCESSO, resultado.getTipoResultado());

		Movel movel = cockpit.getCenarioProcessamento().getMovel();
		assertNotNull(movel);
		assertNotNull(movel.getPosicao());
		Posicao posicao = new Posicao(1, 2, Direcao.NORTE);
		assertEquals(posicao, movel.getPosicao());
		assertEquals(posicao.getDirecao(), movel.getPosicao().getDirecao());
	}

}
