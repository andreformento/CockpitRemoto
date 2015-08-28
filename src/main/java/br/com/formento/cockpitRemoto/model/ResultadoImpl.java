package br.com.formento.cockpitRemoto.model;

public class ResultadoImpl implements Resultado {

	private TipoResultado tipoResultado;
	private String mensagem;

	public ResultadoImpl(TipoResultado tipoResultado, String mensagem) {
		this.tipoResultado = tipoResultado;
		this.mensagem = mensagem;
	}

	@Override
	public TipoResultado getTipoResultado() {
		return tipoResultado;
	}

	@Override
	public String getMensagem() {
		return mensagem;
	}

	@Override
	public String toString() {
		StringBuilder exibir = new StringBuilder();

		exibir.append(tipoResultado.name());
		exibir.append(" - ");
		exibir.append(mensagem);

		return exibir.toString();
	}

}
