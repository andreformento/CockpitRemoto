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
			if (observer.getResultadoBreak().equals(resultado.getTipoResultado()))
				return resultado;
		}

		TipoResultado tipoResultado;
		StringBuilder mensagem = new StringBuilder();

		if (observers.isEmpty()) {
			mensagem.append("Nenhum notificação realizada");
			tipoResultado = TipoResultado.AVISO;
		} else {
			mensagem.append("Notificações realizadas: ");
			mensagem.append(observers.size());
			tipoResultado = TipoResultado.SUCESSO;
		}
		return new ResultadoImpl(tipoResultado, mensagem.toString());
	}

	@Override
	public T getState() {
		return state;
	}

}
