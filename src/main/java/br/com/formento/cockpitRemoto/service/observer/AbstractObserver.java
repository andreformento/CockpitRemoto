package br.com.formento.cockpitRemoto.service.observer;

public abstract class AbstractObserver<T> implements Observer {

	protected Subject<T> subject;

	public AbstractObserver(Subject<T> subject) {
		this.subject = subject;
	}

}