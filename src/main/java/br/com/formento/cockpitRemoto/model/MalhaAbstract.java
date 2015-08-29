package br.com.formento.cockpitRemoto.model;

import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;

public abstract class MalhaAbstract implements Malha {

	protected Map<Posicao, Movel> moveis;
	protected Resultado resultadoIsConsistente;
	private int ordem;

	public MalhaAbstract() {
		super();
	}

	protected int getNovaOrdem() {
		return ordem++;
	}

	@Override
	public Collection<Movel> getMoveis() {
		TreeSet<Movel> moveisOrdenados = new TreeSet<>();
		moveisOrdenados.addAll(moveis.values());
		return moveisOrdenados;
	}

	@Override
	public Movel getByPosicao(Posicao posicao) {
		return moveis.get(posicao);
	}

	@Override
	public Movel removerMovel(Posicao posicao) {
		return moveis.remove(posicao);
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
			moveis.put(movel.getPosicao(), movel);
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

}