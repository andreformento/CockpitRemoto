package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.service.command.Instrucao;

public interface InstrucaoBuilder extends Builder<Instrucao> {

	void buildInstance();

	void buildCenarioProcessamento();

}
