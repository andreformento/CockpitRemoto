package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Resultado;

public class InstrucaoInvalida implements Instrucao {

	@Override
	public Resultado executar(CenarioProcessamento cenarioProcessamento) {
		return cenarioProcessamento.isConsistente();
	}

}
