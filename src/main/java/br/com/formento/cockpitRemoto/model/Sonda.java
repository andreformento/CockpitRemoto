package br.com.formento.cockpitRemoto.model;

public class Sonda implements Movel {

	private Posicao posicao;

	public Sonda(Posicao posicao) {
		this.posicao = posicao;
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

}
