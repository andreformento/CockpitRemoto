package br.com.formento.cockpitRemoto.model;

public enum TipoResultado {
	SUCESSO(true), AVISO(true), ERRO(false);

	private boolean isResultadoOk;

	private TipoResultado(boolean isResultadoOk) {
		this.isResultadoOk = isResultadoOk;
	}

	public boolean isResultadoOk() {
		return this.isResultadoOk;
	}
}
