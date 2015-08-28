package br.com.formento.cockpitRemoto.model;

import java.util.List;

public class EntradaImpl implements Entrada {

	private List<String> comandoList;

	public EntradaImpl(List<String> comandoList) {
		this.comandoList = comandoList;
	}

	@Override
	public List<String> getComandoList() {
		return comandoList;
	}

}
