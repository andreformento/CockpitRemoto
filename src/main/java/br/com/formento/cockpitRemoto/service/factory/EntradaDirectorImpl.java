package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.model.Entrada;

public class EntradaDirectorImpl implements EntradaDirector {

	protected EntradaBuilder builder;

	public EntradaDirectorImpl(EntradaBuilder builder) {
		this.builder = builder;
	}

	@Override
	public void construirInstancia() {
		builder.buildComandoList();
		builder.buildInstance();
	}

	@Override
	public Entrada getProduct() {
		return builder.getProduct();
	}

}
