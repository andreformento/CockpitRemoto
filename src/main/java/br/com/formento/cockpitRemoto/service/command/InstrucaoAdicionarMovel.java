package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Malha;
import br.com.formento.cockpitRemoto.model.Movel;
import br.com.formento.cockpitRemoto.model.Resultado;

@InstrucaoAnnotation(nomeInstrucao = "adicionarMovel")
public class InstrucaoAdicionarMovel extends AbstractInstrucaoMovimento {

	@Override
	protected Resultado executarInterno(CenarioProcessamento cenarioProcessamento) {
		Malha malha = cenarioProcessamento.getMalha();
		Movel movel = cenarioProcessamento.getMovel();
		return malha.adicionarMovel(movel);
	}

}
