package br.com.formento.cockpitRemoto.service.factory;

import java.util.List;

import br.com.formento.cockpitRemoto.model.Entrada;

public abstract class EntradaAbstractBuilder extends AbstractBuilder<Entrada> implements EntradaBuilder {

	private List<String> comandoList;

	public List<String> getComandoList() {
		return comandoList;
	}

	protected void setComandoList(List<String> comandoList) {
		this.comandoList = comandoList;
	}

}
