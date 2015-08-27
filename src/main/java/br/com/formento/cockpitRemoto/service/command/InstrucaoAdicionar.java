package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.Resultado;

@InstrucaoAnnotation(nomeInstrucao = "adicionarMovel")
public class InstrucaoAdicionar extends AbstractInstrucaoMovimento {

	@Override
	public Resultado executarInterno() {
		return getCenarioProcessamento().getMalha().adicionarMovel(getCenarioProcessamento().getMovel());
	}

}
