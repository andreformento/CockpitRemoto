package br.com.formento.cockpitRemoto.model;

public class MalhaRetangular extends MalhaAbstract implements Malha {

	private Posicao posicaoMaxima;

	public MalhaRetangular(Posicao posicaoMaxima) {
		this.posicaoMaxima = posicaoMaxima;

		if (posicaoMaxima == null)
			this.resultadoIsConsistente = new ResultadoImpl(TipoResultado.ERRO, "Posição máxima não configurada");
		else
			this.resultadoIsConsistente = new ResultadoImpl(TipoResultado.SUCESSO, "Está consistente");
	}

	/**
	 * Se não tiver nada naquela posição significa que está disponível
	 */
	@Override
	public Resultado isPosicaoDisponivel(Posicao posicao) {
		if (isConsistente().getTipoResultado().isResultadoOk()) {
			if (posicao.compareTo(posicaoMaxima) > 0)
				return new ResultadoImpl(TipoResultado.ERRO, "Posição superior a margem(" + posicaoMaxima.getX() + ","
						+ posicaoMaxima.getY() + " )");
			else if (moveisPorPosicao.containsKey(posicao))
				return new ResultadoImpl(TipoResultado.ERRO, "Posição ocupada (" + posicao.getX() + ", " + posicao.getY() + ")");
			else
				return new ResultadoImpl(TipoResultado.SUCESSO, "Posição disponível");
		} else
			return isConsistente();
	}

	@Override
	public String toString() {
		return "MalhaRetangular [posicaoMaxima=" + posicaoMaxima + ", moveisPorPosicao=" + moveisPorPosicao + ", resultadoIsConsistente="
				+ resultadoIsConsistente + "]";
	}

	public Posicao getPosicaoMaxima() {
		return posicaoMaxima;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((posicaoMaxima == null) ? 0 : posicaoMaxima.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MalhaRetangular other = (MalhaRetangular) obj;
		if (posicaoMaxima == null) {
			if (other.posicaoMaxima != null)
				return false;
		} else if (!posicaoMaxima.equals(other.posicaoMaxima))
			return false;
		return true;
	}

	@Override
	public MalhaRetangular clone() throws CloneNotSupportedException {
		MalhaRetangular clone = (MalhaRetangular) super.clone();

		if (posicaoMaxima != null)
			clone.posicaoMaxima = posicaoMaxima.clone();

		return clone;
	}

}
