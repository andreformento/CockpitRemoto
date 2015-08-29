package br.com.formento.cockpitRemoto.model;

public class Sonda implements Movel, Comparable<Sonda> {

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

}
