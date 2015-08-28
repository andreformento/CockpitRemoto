package br.com.formento.cockpitRemoto.service.observer;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.interpreter.ContextoInstrucaoInterpreter;
import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreter;
import br.com.formento.cockpitRemoto.service.interpreter.ResultadoInterpreterInstrucao;
import br.com.formento.cockpitRemoto.service.interpreter.ResultadoInterpreterInstrucaoImpl;

public class ComandoObserverExecutarInstrucao extends AbstractObserver<String> {

	private InstrucaoInterpreter instrucaoInterpreter;

	public ComandoObserverExecutarInstrucao(Subject<String> subject, InstrucaoInterpreter instrucaoInterpreter) {
		super(subject);
		this.instrucaoInterpreter = instrucaoInterpreter;
	}

	@Override
	public Resultado update() {
		String input = subject.getState();
		ResultadoInterpreterInstrucao output = new ResultadoInterpreterInstrucaoImpl();
		ContextoInstrucaoInterpreter contextoInterpreter = new ContextoInstrucaoInterpreter(input, output);

		instrucaoInterpreter.interpretar(contextoInterpreter);

		// continua executando enquanto for aviso, ou seja, nao encontrado
		return contextoInterpreter.getOutput().isConsistente();
	}

	@Override
	public TipoResultado getTipoResultadoPermiteContinuar() {
		return TipoResultado.AVISO;
	}

}
