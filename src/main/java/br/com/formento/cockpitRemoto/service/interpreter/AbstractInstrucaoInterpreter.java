package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;

public abstract class AbstractInstrucaoInterpreter implements InstrucaoInterpreter {

	private CenarioProcessamento cenarioProcessamento;
	private InstrucaoFlyweight instrucaoFlyweight;

	public AbstractInstrucaoInterpreter(InstrucaoFlyweight instrucaoFlyweight, CenarioProcessamento cenarioProcessamento) {
		this.instrucaoFlyweight = instrucaoFlyweight;
		this.cenarioProcessamento = cenarioProcessamento;
	}

	public CenarioProcessamento getCenarioProcessamento() {
		return cenarioProcessamento;
	}

	public InstrucaoFlyweight getInstrucaoFlyweight() {
		return instrucaoFlyweight;
	}

}
