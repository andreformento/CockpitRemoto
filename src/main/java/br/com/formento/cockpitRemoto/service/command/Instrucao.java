package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.EntidadeConsistente;
import br.com.formento.cockpitRemoto.model.Resultado;

public interface Instrucao extends EntidadeConsistente {

	void configurarCenarioProcessamento(CenarioProcessamento cenarioProcessamento);

	Resultado executar();

	CenarioProcessamento getCenarioProcessamento();

}
