package br.com.formento.cockpitRemoto.model;

import java.io.Serializable;

public interface Resultado extends Cloneable, Serializable {

	TipoResultado getTipoResultado();

	String getMensagem();

	Resultado clone() throws CloneNotSupportedException;

}
