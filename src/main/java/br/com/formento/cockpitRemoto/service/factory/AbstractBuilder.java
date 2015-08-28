package br.com.formento.cockpitRemoto.service.factory;

public abstract class AbstractBuilder<T> implements Builder<T> {

	protected T product;

	@Override
	public T getProduct() {
		return product;
	}

}
