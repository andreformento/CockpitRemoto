package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.model.Entrada;

public interface EntradaBuilder extends Builder<Entrada> {

	void buildComandoList();

	void buildInstance();

}
