package br.com.formento.cockpitRemoto.service.iterator;

import java.util.List;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.service.observer.ComandoSubject;

public class IteradorComandoSubjectImpl extends AbstractIteradorInterno<String> implements IteradorComandoSubject {

	private ComandoSubject comandoSubject;

	public IteradorComandoSubjectImpl(List<String> listaPercorrer, ComandoSubject comandoSubject) {
		super(listaPercorrer);
		this.comandoSubject = comandoSubject;
	}

	@Override
	public Resultado operacao(String item) {
		return comandoSubject.setState(item);
	}

}
