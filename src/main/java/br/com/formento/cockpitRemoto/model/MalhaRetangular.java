package br.com.formento.cockpitRemoto.model;

import java.util.HashMap;

public class MalhaRetangular extends MalhaAbstract implements Malha {

	private Posicao posicaoMaxima;

	public MalhaRetangular(Posicao posicaoMaxima) {
		this.moveis = new HashMap<>();
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
				return new ResultadoImpl(TipoResultado.ERRO, "Posição superior a margem(" + posicaoMaxima.getX() + "," + posicaoMaxima.getY() + " )");
			else if (moveis.containsKey(posicao))
				return new ResultadoImpl(TipoResultado.ERRO, "Posição ocupada (" + posicao.getX() + ", " + posicao.getY() + ")");
			else
				return new ResultadoImpl(TipoResultado.SUCESSO, "Posição disponível");
		} else
			return isConsistente();
	}

	@Override
	public String toString() {
		return "MalhaRetangular [posicaoMaxima=" + posicaoMaxima + ", moveis=" + moveis + ", resultadoIsConsistente=" + resultadoIsConsistente + "]";
	}

	public Posicao getPosicaoMaxima() {
		return posicaoMaxima;
	}

}
