package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.service.observer.ComandoSubject;

public class ComandoSubjectDirectorImpl implements ComandoSubjectDirector {

	protected ComandoSubjectBuilder builder;

	public ComandoSubjectDirectorImpl(ComandoSubjectBuilder builder) {
		this.builder = builder;
	}

	@Override
	public void construirInstancia() {
		builder.buildInstance();
		builder.buildObservers();
	}

	@Override
	public ComandoSubject getProduct() {
		return builder.getProduct();
	}

}
