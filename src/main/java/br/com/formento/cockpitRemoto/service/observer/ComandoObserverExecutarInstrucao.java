package br.com.formento.cockpitRemoto.service.observer;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.interpreter.ContextoInstrucaoInterpreter;
import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreter;
import br.com.formento.cockpitRemoto.service.interpreter.ResultadoInterpreterInstrucao;

public class ComandoObserverExecutarInstrucao extends AbstractObserver<String> {

	private InstrucaoInterpreter instrucaoInterpreter;
	private ResultadoInterpreterInstrucao resultadoInterpreterInstrucao;

	public ComandoObserverExecutarInstrucao(Subject<String> subject, InstrucaoInterpreter instrucaoInterpreter,
			ResultadoInterpreterInstrucao resultadoInterpreterInstrucao) {
		super(subject);
		this.instrucaoInterpreter = instrucaoInterpreter;
		this.resultadoInterpreterInstrucao = resultadoInterpreterInstrucao;
	}

	@Override
	public Resultado update() {
		String input = subject.getState();
		ContextoInstrucaoInterpreter contextoInterpreter = new ContextoInstrucaoInterpreter(input, resultadoInterpreterInstrucao);

		instrucaoInterpreter.interpretar(contextoInterpreter);

		return contextoInterpreter.getOutput().getCenarioProcessamento().isConsistente();
	}

	@Override
	public TipoResultado getResultadoBreak() {
		return TipoResultado.ERRO;
	}

}
