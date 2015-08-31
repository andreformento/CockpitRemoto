package br.com.formento.cockpitRemoto.model;

import java.util.List;
import java.util.Map;

public interface Malha extends EntidadeConsistente, Cloneable {

	Resultado isPosicaoDisponivel(Posicao posicao);

	Resultado adicionarMovel(Movel movel);

	Movel removerMovel(Posicao posicao);

	Resultado trocarPosicao(Posicao posicaoAtual, Posicao novaPosicao);

	List<Movel> getMoveisOrdemInsert();

	Map<Posicao, Movel> getMoveis();

	Movel getByPosicao(Posicao posicao);

	Malha clone() throws CloneNotSupportedException;

}
