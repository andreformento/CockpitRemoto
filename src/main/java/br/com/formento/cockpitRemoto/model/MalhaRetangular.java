package br.com.formento.cockpitRemoto.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MalhaRetangular implements Malha {

	private Posicao posicaoMaxima;
	private Map<Posicao, Movel> moveis;
	private Resultado resultadoIsConsistente;

	public MalhaRetangular(Posicao posicaoMaxima) {
		this.moveis = new HashMap<>();
		this.posicaoMaxima = posicaoMaxima;

		if (posicaoMaxima == null)
			this.resultadoIsConsistente = new ResultadoImpl(TipoResultado.ERRO, "Posição máxima não configurada");
		else
			this.resultadoIsConsistente = new ResultadoImpl(TipoResultado.SUCESSO, "Está consistente");
	}

	@Override
	public Resultado isConsistente() {
		return resultadoIsConsistente;
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
				return new ResultadoImpl(TipoResultado.ERRO, "Posição ocupada (" + posicao.getX() + "," + posicao.getY() + " )");
			else
				return new ResultadoImpl(TipoResultado.SUCESSO, "Posição disponível");
		} else
			return isConsistente();
	}

	@Override
	public Resultado adicionarMovel(Movel movel) {
		Resultado resultadoPosicaoDisponivel = isPosicaoDisponivel(movel.getPosicao());

		if (resultadoPosicaoDisponivel.getTipoResultado().isResultadoOk()) {
			moveis.put(movel.getPosicao(), movel);
			return new ResultadoImpl(TipoResultado.SUCESSO, "Movel colocado na posição");
		} else
			return resultadoPosicaoDisponivel;
	}

	@Override
	public Movel removerMovel(Posicao posicao) {
		return moveis.remove(posicao);
	}

	@Override
	public Resultado trocarPosicao(Posicao posicaoAtual, Posicao novaPosicao) {
		Resultado resultadoPosicaoDisponivel = isPosicaoDisponivel(novaPosicao);

		if (resultadoPosicaoDisponivel.getTipoResultado().isResultadoOk()) {
			Movel movel = removerMovel(posicaoAtual);
			if (movel == null) {
				return new ResultadoImpl(TipoResultado.ERRO, "Não foi encontrado nenhum movel na posição atual");
			} else {
				movel.mover(novaPosicao);
				return adicionarMovel(movel);
			}
		} else
			return resultadoPosicaoDisponivel;
	}

	@Override
	public Collection<Movel> getMoveis() {
		return moveis.values();
	}

}
