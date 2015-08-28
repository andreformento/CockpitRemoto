package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Posicao;
import br.com.formento.cockpitRemoto.model.Resultado;

@InstrucaoAnnotation(nomeInstrucao = "M")
public class InstrucaoMoverParaFrente extends AbstractInstrucaoMovimento {

	@Override
	protected Resultado executarInterno(CenarioProcessamento cenarioProcessamento) {
		Posicao posicaoAtual = cenarioProcessamento.getMovel().getPosicao();
		MovimentoAndarParaFrente movimentoAndarParaFrente = posicaoAtual.getDirecao().getMovimentoAndarParaFrente();

		Posicao novaPosicao = movimentoAndarParaFrente.novaPosicao(posicaoAtual);

		return cenarioProcessamento.getMalha().trocarPosicao(posicaoAtual, novaPosicao);
	}

}
