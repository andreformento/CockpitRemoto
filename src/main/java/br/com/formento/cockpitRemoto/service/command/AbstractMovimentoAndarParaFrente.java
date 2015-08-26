package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.Posicao;

public abstract class AbstractMovimentoAndarParaFrente implements MovimentoAndarParaFrente {

	public abstract Integer somarEmX();

	public abstract Integer somarEmY();

	public Posicao novaPosicao(Posicao posicaoAtual) {
		return new Posicao(posicaoAtual.getX() + somarEmX(), posicaoAtual.getY() + somarEmY());
	}

}
