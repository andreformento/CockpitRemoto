package br.com.formento.cockpitRemoto.service.observer;

import java.util.ArrayList;
import java.util.List;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;

public class SubjectImpl<T> implements Subject<T> {

	protected List<Observer> observers;
	protected T state;

	public SubjectImpl() {
		observers = new ArrayList<Observer>();
	}

	@Override
	public void attach(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void detach(int indice) {
		observers.remove(indice);
	}

	@Override
	public Resultado setState(T state) {
		this.state = state;
		return notifyObservers();
	}

	private Resultado notifyObservers() {
		for (Observer observer : observers) {
			Resultado resultado = observer.update();
			if (observer.equals(resultado.getTipoResultado()))
				return resultado;
		}

		return new ResultadoImpl(TipoResultado.SUCESSO, "Status atualizado");
	}

	@Override
	public T getState() {
		return state;
	}

}
