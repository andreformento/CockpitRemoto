package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.Posicao;
import br.com.formento.cockpitRemoto.model.Resultado;

@InstrucaoAnnotation(nomeInstrucao = "M")
public class InstrucaoMoverParaFrente extends AbstractInstrucaoMovimento {

	@Override
	public Resultado executarInterno() {
		Posicao posicaoAtual = getCenarioProcessamento().getMovel().getPosicao();
		MovimentoAndarParaFrente movimentoAndarParaFrente = posicaoAtual.getDirecao().getMovimentoAndarParaFrente();

		Posicao novaPosicao = movimentoAndarParaFrente.novaPosicao(posicaoAtual);

		return getCenarioProcessamento().getMalha().trocarPosicao(posicaoAtual, novaPosicao);
	}

}
