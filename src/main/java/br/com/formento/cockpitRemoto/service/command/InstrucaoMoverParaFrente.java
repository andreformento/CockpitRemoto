package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.Malha;
import br.com.formento.cockpitRemoto.model.Posicao;
import br.com.formento.cockpitRemoto.model.Resultado;

public class InstrucaoMoverParaFrente extends AbstractInstrucao {

	public InstrucaoMoverParaFrente(Malha malha) {
		super(malha);
	}

	@Override
	public Resultado executar() {
		Posicao posicaoAtual = getMovel().getPosicao();
		MovimentoAndarParaFrente movimentoAndarParaFrente = posicaoAtual.getDirecao().getMovimentoAndarParaFrente();

		Posicao novaPosicao = movimentoAndarParaFrente.novaPosicao(posicaoAtual);

		return getMalha().trocarPosicao(posicaoAtual, novaPosicao);
	}

}
