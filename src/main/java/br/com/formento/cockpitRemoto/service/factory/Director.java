package br.com.formento.cockpitRemoto.service.factory;

public interface Director<T> {

	void construirInstancia();

	T getProduct();

}