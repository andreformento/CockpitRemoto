package br.com.formento.cockpitRemoto.model;

import java.io.Serializable;
import java.util.Collection;

public interface Relatorio extends Serializable {

	Resultado getResultado();

	Collection<Movel> getListMovel();

}
