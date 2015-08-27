package br.com.formento.cockpitRemoto.service.iterator;

import br.com.formento.cockpitRemoto.model.Resultado;

public interface IteradorInterno<T> extends Iterador<T> {

	void voltar();

	Resultado percorrerLista();

	Resultado operacao(T item);

}
