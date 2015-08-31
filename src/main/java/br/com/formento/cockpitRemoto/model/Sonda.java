package br.com.formento.cockpitRemoto.model;

public class Sonda implements Movel, Comparable<Sonda> {
	
	private static final long serialVersionUID = 1L;

	private Integer ordem;
	private Posicao posicao;

	public Sonda(Posicao posicao) {
		this.posicao = posicao;
	}

	@Override
	public Integer getOrdem() {
		return ordem;
	}

	@Override
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	@Override
	public Posicao getPosicao() {
		return posicao;
	}

	@Override
	public void mover(Posicao posicao) {
		this.posicao = posicao;
	}

	@Override
	public Resultado isConsistente() {
		if (posicao == null)
			return new ResultadoImpl(TipoResultado.ERRO, "Sonda sem posição configurada");
		else
			return posicao.isConsistente();
	}

	@Override
	public String toString() {
		return "Sonda [ordem=" + ordem + ", posicao=" + posicao + "]";
	}

	@Override
	public int compareTo(Sonda o) {
		if (ordem == null)
			return -1;
		else if (o.ordem == -1)
			return 1;
		else
			return ordem.compareTo(o.ordem);
	}

	@Override
	public Sonda clone() throws CloneNotSupportedException {
		Sonda clone = (Sonda) super.clone();

		if (posicao != null)
			clone.posicao = posicao.clone();

		return clone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
		result = prime * result + ((posicao == null) ? 0 : posicao.hashCode());
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
		Sonda other = (Sonda) obj;
		if (ordem == null) {
			if (other.ordem != null)
				return false;
		} else if (!ordem.equals(other.ordem))
			return false;
		if (posicao == null) {
			if (other.posicao != null)
				return false;
		} else if (!posicao.equals(other.posicao))
			return false;
		return true;
	}

}
