package br.com.formento.cockpitRemoto.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

public abstract class MalhaAbstract implements Malha {

	protected HashMap<Posicao, Movel> moveisPorPosicao;
	protected Resultado resultadoIsConsistente;
	private Integer ordem;

	public MalhaAbstract() {
		this.moveisPorPosicao = new HashMap<Posicao, Movel>();
		this.ordem = 0;
	}

	protected Integer getNovaOrdem() {
		return ordem++;
	}

	/**
	 * Retorna a lista de Movel pela ordem de insert
	 */
	@Override
	public Collection<Movel> getMoveisOrdemInsert() {
		TreeSet<Movel> moveisOrdenados = new TreeSet<>(moveisPorPosicao.values());
		moveisPorPosicao.values();
		return moveisOrdenados;
	}

	@Override
	public Movel getByPosicao(Posicao posicao) {
		return moveisPorPosicao.get(posicao);
	}

	@Override
	public Movel removerMovel(Posicao posicao) {
		return moveisPorPosicao.remove(posicao);
	}

	@Override
	public Resultado isConsistente() {
		return resultadoIsConsistente;
	}

	@Override
	public Resultado adicionarMovel(Movel movel) {
		return adicionarMovel(movel, getNovaOrdem());
	}

	private Resultado adicionarMovel(Movel movel, Integer ordem) {
		Resultado resultadoPosicaoDisponivel = isPosicaoDisponivel(movel.getPosicao());

		if (resultadoPosicaoDisponivel.getTipoResultado().isResultadoOk()) {
			movel.setOrdem(ordem);
			moveisPorPosicao.put(movel.getPosicao(), movel);
			return new ResultadoImpl(TipoResultado.SUCESSO, "Movel colocado na posição");
		} else
			return resultadoPosicaoDisponivel;
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
				return adicionarMovel(movel, movel.getOrdem());
			}
		} else
			return resultadoPosicaoDisponivel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((moveisPorPosicao == null) ? 0 : moveisPorPosicao.hashCode());
		result = prime * result + ordem;
		result = prime * result + ((resultadoIsConsistente == null) ? 0 : resultadoIsConsistente.hashCode());
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
		MalhaAbstract other = (MalhaAbstract) obj;
		if (moveisPorPosicao == null) {
			if (other.moveisPorPosicao != null)
				return false;
		} else if (!moveisPorPosicao.equals(other.moveisPorPosicao))
			return false;
		if (ordem != other.ordem)
			return false;
		if (resultadoIsConsistente == null) {
			if (other.resultadoIsConsistente != null)
				return false;
		} else if (!resultadoIsConsistente.equals(other.resultadoIsConsistente))
			return false;
		return true;
	}

	@Override
	public MalhaAbstract clone() throws CloneNotSupportedException {
		MalhaAbstract clone = (MalhaAbstract) super.clone();

		if (moveisPorPosicao instanceof HashMap) {
			// @SuppressWarnings("unchecked")
			// HashMap<Posicao, Movel> moveisClone = (HashMap<Posicao, Movel>)
			// moveis.clone();
			// clone.moveis = moveisClone;

			clone.moveisPorPosicao = new HashMap<>();
			for (Entry<Posicao, Movel> i : moveisPorPosicao.entrySet())
				clone.moveisPorPosicao.put(i.getKey().clone(), i.getValue().clone());
		}

		if (resultadoIsConsistente != null)
			clone.resultadoIsConsistente = resultadoIsConsistente.clone();

		return clone;
	}

	@Override
	public Map<Posicao, Movel> getMoveis() {
		return moveisPorPosicao;
	}

}