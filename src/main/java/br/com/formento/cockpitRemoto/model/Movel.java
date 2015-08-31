package br.com.formento.cockpitRemoto.model;

public interface Movel extends EntidadeConsistente, Cloneable {

	Posicao getPosicao();

	void mover(Posicao posicao);

	void setOrdem(Integer ordem);

	Integer getOrdem();

	Movel clone() throws CloneNotSupportedException;

}
