package br.com.formento.cockpitRemoto.service.factory;

import java.util.List;

import br.com.formento.cockpitRemoto.model.EntradaImpl;

public class EntradaBuilderList extends EntradaAbstractBuilder {

	public EntradaBuilderList(List<String> comandoList) {
		super.comandoList = comandoList;
	}

	@Override
	public void buildComandoList() {
	}

	@Override
	public void buildInstance() {
		product = new EntradaImpl(comandoList);
	}

}
