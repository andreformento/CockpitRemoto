package br.com.formento.cockpitRemoto.service.factory;

import java.util.List;

import br.com.formento.cockpitRemoto.model.EntradaImpl;

public class EntradaBuilderList extends EntradaAbstractBuilder implements EntradaBuilder {

	private List<String> comandoListParam;

	public EntradaBuilderList(List<String> comandoList) {
		this.comandoListParam = comandoList;
	}

	@Override
	public void buildComandoList() {
		super.setComandoList(comandoListParam);
	}

	@Override
	public void buildInstance() {
		product = new EntradaImpl(getComandoList());
	}

}
