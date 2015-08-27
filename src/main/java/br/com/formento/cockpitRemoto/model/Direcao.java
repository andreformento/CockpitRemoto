package br.com.formento.cockpitRemoto.model;

import br.com.formento.cockpitRemoto.service.command.MovimentoAndarLeste;
import br.com.formento.cockpitRemoto.service.command.MovimentoAndarNorte;
import br.com.formento.cockpitRemoto.service.command.MovimentoAndarOeste;
import br.com.formento.cockpitRemoto.service.command.MovimentoAndarParaFrente;
import br.com.formento.cockpitRemoto.service.command.MovimentoAndarSul;

public enum Direcao {
	// a ordem dos elementos deve ficar sempre no sentido horario

	NORTE("N", new MovimentoAndarNorte()),

	LESTE("E", new MovimentoAndarLeste()),

	SUL("S", new MovimentoAndarSul()),

	OESTE("W", new MovimentoAndarOeste());

	private String sigla;
	private Direcao proximoSentidoHorario;
	private Direcao proximoSentidoAntiHorario;
	private MovimentoAndarParaFrente movimentoAndarParaFrente;

	// lista duplamente encadeada
	private Direcao(String sigla, MovimentoAndarParaFrente movimentoAndarParaFrente) {
		this.sigla = sigla;
		this.movimentoAndarParaFrente = movimentoAndarParaFrente;
	}

	public String getSigla() {
		return sigla;
	}

	public static Direcao getPorSigla(String sigla) {
		for (Direcao d : values())
			if (d.sigla.equals(sigla))
				return d;

		return null;
	}

	public Direcao getProximoSentidoHorario() {
		if (proximoSentidoHorario == null) {
			int indiceProximo = ordinal() + 1;
			indiceProximo = indiceProximo < values().length ? indiceProximo : 0;
			proximoSentidoHorario = values()[indiceProximo];
		}

		return proximoSentidoHorario;
	}

	public Direcao getProximoSentidoAntiHorario() {
		if (proximoSentidoAntiHorario == null) {
			int indiceProximo = ordinal() - 1;
			indiceProximo = indiceProximo < 0 ? values().length - 1 : indiceProximo;
			proximoSentidoAntiHorario = values()[indiceProximo];
		}

		return proximoSentidoAntiHorario;
	}

	public MovimentoAndarParaFrente getMovimentoAndarParaFrente() {
		return movimentoAndarParaFrente;
	}

}
