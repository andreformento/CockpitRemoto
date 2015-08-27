package br.com.formento.cockpitRemoto.model;

import java.util.Collection;

public interface Malha extends EntidadeConsistente {

	Resultado isPosicaoDisponivel(Posicao posicao);

	Resultado adicionarMovel(Movel movel);

	Movel removerMovel(Posicao posicao);

	Resultado trocarPosicao(Posicao posicaoAtual, Posicao novaPosicao);

	Collection<Movel> getMoveis();

}
