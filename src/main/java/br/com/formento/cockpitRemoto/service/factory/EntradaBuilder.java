package br.com.formento.cockpitRemoto.service.factory;

import java.util.List;

import br.com.formento.cockpitRemoto.model.Entrada;

public interface EntradaBuilder extends Builder<Entrada> {

	List<String> getComandoList();

	void buildComandoList();

	void buildInstance();

}
