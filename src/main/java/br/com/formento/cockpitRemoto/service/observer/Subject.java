package br.com.formento.cockpitRemoto.service.observer;

import br.com.formento.cockpitRemoto.model.Resultado;

public interface Subject<T> {

	/**
	 * Adiciona observer
	 * 
	 * @param observer
	 */
	void attach(Observer observer);

	/**
	 * Remove observer
	 * 
	 * @param indice
	 */
	void detach(int indice);

	/**
	 * @param state
	 */
	Resultado setState(T state);

	/**
	 * Retorna estado atual
	 * 
	 * @return
	 */
	T getState();

}
