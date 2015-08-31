package br.com.formento.cockpitRemoto.model;

public class ResultadoImpl implements Resultado {
	
	private static final long serialVersionUID = 1L;

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

	@Override
	public ResultadoImpl clone() throws CloneNotSupportedException {
		ResultadoImpl clone = (ResultadoImpl) super.clone();
		return clone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mensagem == null) ? 0 : mensagem.hashCode());
		result = prime * result + ((tipoResultado == null) ? 0 : tipoResultado.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultadoImpl other = (ResultadoImpl) obj;
		if (mensagem == null) {
			if (other.mensagem != null)
				return false;
		} else if (!mensagem.equals(other.mensagem))
			return false;
		if (tipoResultado != other.tipoResultado)
			return false;
		return true;
	}

}
