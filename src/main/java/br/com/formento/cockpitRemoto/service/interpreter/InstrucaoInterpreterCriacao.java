package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.Malha;

public class InstrucaoInterpreterCriacao extends AbstractInstrucaoInterpreter {

	public InstrucaoInterpreterCriacao(Malha malha) {
		super(malha);
	}

	@Override
	protected void validar(ContextoInterpreter<String, ResultadoInterpreterInstrucao> contextoInterpreter) {
		/**
		 * Primeiro tenta reconhecer o comando em algum padrao de instrucao
		 */
		
	}

}
