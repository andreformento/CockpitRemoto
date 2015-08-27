package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.service.command.Instrucao;

public class InstrucaoDirectorImpl implements InstrucaoDirector {

	protected InstrucaoBuilder instrucaoBuilder;

	public InstrucaoDirectorImpl(InstrucaoBuilder instrucaoBuilder) {
		this.instrucaoBuilder = instrucaoBuilder;
	}

	@Override
	public void construirInstancia() {
		instrucaoBuilder.buildInstance();
		instrucaoBuilder.buildCenarioProcessamento();
	}

	@Override
	public Instrucao getProduct() {
		return instrucaoBuilder.getProduct();
	}

}
