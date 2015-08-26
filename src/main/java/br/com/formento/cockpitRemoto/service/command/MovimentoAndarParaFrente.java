package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.Posicao;

public interface MovimentoAndarParaFrente {

	Posicao novaPosicao(Posicao posicaoAtual);

}
