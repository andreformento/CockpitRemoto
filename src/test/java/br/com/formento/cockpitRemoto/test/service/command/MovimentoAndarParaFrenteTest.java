package br.com.formento.cockpitRemoto.test.service.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.formento.cockpitRemoto.model.Direcao;
import br.com.formento.cockpitRemoto.model.Posicao;
import br.com.formento.cockpitRemoto.service.command.MovimentoAndarParaFrente;

public class MovimentoAndarParaFrenteTest {

	@Test
	public void testSentidoNorte() {
		Posicao posicao = new Posicao(1, 1, Direcao.NORTE);
		MovimentoAndarParaFrente movimentoAndar = posicao.getDirecao().getMovimentoAndarParaFrente();
		posicao = movimentoAndar.novaPosicao(posicao);
		assertEquals(Integer.valueOf(1), posicao.getX());
		assertEquals(Integer.valueOf(2), posicao.getY());
	}

	@Test
	public void testSentidoLeste() {
		Posicao posicao = new Posicao(1, 1, Direcao.LESTE);
		MovimentoAndarParaFrente movimentoAndar = posicao.getDirecao().getMovimentoAndarParaFrente();
		posicao = movimentoAndar.novaPosicao(posicao);
		assertEquals(Integer.valueOf(2), posicao.getX());
		assertEquals(Integer.valueOf(1), posicao.getY());
	}

	@Test
	public void testSentidoSul() {
		Posicao posicao = new Posicao(1, 1, Direcao.SUL);
		MovimentoAndarParaFrente movimentoAndar = posicao.getDirecao().getMovimentoAndarParaFrente();
		posicao = movimentoAndar.novaPosicao(posicao);
		assertEquals(Integer.valueOf(1), posicao.getX());
		assertEquals(Integer.valueOf(0), posicao.getY());
	}

	@Test
	public void testSentidoOeste() {
		Posicao posicao = new Posicao(1, 1, Direcao.OESTE);
		MovimentoAndarParaFrente movimentoAndar = posicao.getDirecao().getMovimentoAndarParaFrente();
		posicao = movimentoAndar.novaPosicao(posicao);
		assertEquals(Integer.valueOf(0), posicao.getX());
		assertEquals(Integer.valueOf(1), posicao.getY());
	}

}
