package br.com.formento.cockpitRemoto.model;

public interface Resultado extends Cloneable {

	TipoResultado getTipoResultado();

	String getMensagem();

	Resultado clone() throws CloneNotSupportedException;

}
