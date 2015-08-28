package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.service.observer.ComandoSubject;

public interface ComandoSubjectBuilder extends Builder<ComandoSubject> {

	void buildObservers();

	void buildInstance();

}
