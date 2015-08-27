package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.Malha;

public abstract class AbstractInstrucaoInterpreter implements InstrucaoInterpreter {

	private Malha malha;

	public AbstractInstrucaoInterpreter(Malha malha) {
		this.malha = malha;
	}

	@Override
	public void interpretar(ContextoInterpreter<String, ResultadoInterpreterInstrucao> contextoInterpreter) {
		validar(contextoInterpreter);
	}

	protected abstract void validar(ContextoInterpreter<String, ResultadoInterpreterInstrucao> contextoInterpreter);

	public Malha getMalha() {
		return malha;
	}

}
