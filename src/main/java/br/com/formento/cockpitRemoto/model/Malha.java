package br.com.formento.cockpitRemoto.model;

import java.util.Collection;
import java.util.Map;

public interface Malha extends EntidadeConsistente, Cloneable {

	Resultado isPosicaoDisponivel(Posicao posicao);

	Resultado adicionarMovel(Movel movel);

	Movel removerMovel(Posicao posicao);

	Resultado trocarPosicao(Posicao posicaoAtual, Posicao novaPosicao);

	Collection<Movel> getMoveisOrdemInsert();

	Map<Posicao, Movel> getMoveis();

	Movel getByPosicao(Posicao posicao);

	Malha clone() throws CloneNotSupportedException;

}
