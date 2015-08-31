package br.com.formento.cockpitRemoto.model;

import java.io.Serializable;

public interface Movel extends EntidadeConsistente, Cloneable, Serializable {

	Posicao getPosicao();

	void mover(Posicao posicao);

	void setOrdem(Integer ordem);

	Integer getOrdem();

	Movel clone() throws CloneNotSupportedException;

}
