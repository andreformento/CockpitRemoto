package br.com.formento.cockpitRemoto.service.factory;

import java.util.List;

import br.com.formento.cockpitRemoto.service.iterator.IteradorComandoSubject;
import br.com.formento.cockpitRemoto.service.observer.ComandoSubject;

public interface IteradorComandoSubjectFactoryMethod {

	IteradorComandoSubject criarInstancia(List<String> listaPercorrer, ComandoSubject comandoSubject);

}
