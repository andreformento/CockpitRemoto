package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;

public abstract class AbstractInstrucaoInterpreter implements InstrucaoInterpreter {

	private InstrucaoFlyweight instrucaoFlyweight;

	public AbstractInstrucaoInterpreter(InstrucaoFlyweight instrucaoFlyweight) {
		this.instrucaoFlyweight = instrucaoFlyweight;
	}

	public InstrucaoFlyweight getInstrucaoFlyweight() {
		return instrucaoFlyweight;
	}

}
