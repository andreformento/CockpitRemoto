package br.com.formento.cockpitRemoto.service.iterator;

public interface Iterador<T> {

	void first();

	void next();

	T currentItem();

	boolean isDone();

	boolean isEmpty();

}
