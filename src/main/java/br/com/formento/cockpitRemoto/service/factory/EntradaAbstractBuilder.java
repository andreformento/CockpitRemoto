package br.com.formento.cockpitRemoto.service.factory;

import java.util.List;

import br.com.formento.cockpitRemoto.model.Entrada;

public abstract class EntradaAbstractBuilder extends AbstractBuilder<Entrada> implements EntradaBuilder {

	protected List<String> comandoList;

}
