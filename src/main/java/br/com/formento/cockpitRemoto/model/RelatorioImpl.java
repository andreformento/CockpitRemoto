package br.com.formento.cockpitRemoto.model;

import java.util.List;

public class RelatorioImpl implements Relatorio {

	private static final long serialVersionUID = 1L;

	private Resultado resultado;

	private List<Movel> listMovel;

	public RelatorioImpl(Resultado resultado, List<Movel> listMovel) {
		this.resultado = resultado;
		this.listMovel = listMovel;
	}

	@Override
	public Resultado getResultado() {
		return resultado;
	}

	@Override
	public List<Movel> getListMovel() {
		return listMovel;
	}

	@Override
	public String toString() {
		return "RelatorioImpl [resultado=" + resultado + ", listMovel=" + listMovel + "]";
	}

}
