package br.com.formento.cockpitRemoto.service.factory;

import java.util.List;

import br.com.formento.cockpitRemoto.service.iterator.IteradorComandoSubject;
import br.com.formento.cockpitRemoto.service.iterator.IteradorComandoSubjectImpl;
import br.com.formento.cockpitRemoto.service.observer.ComandoSubject;

public class IteradorComandoSubjectFactoryMethodImpl implements IteradorComandoSubjectFactoryMethod {

	@Override
	public IteradorComandoSubject criarInstancia(List<String> listaPercorrer, ComandoSubject comandoSubject) {
		return new IteradorComandoSubjectImpl(listaPercorrer, comandoSubject);
	}

}
