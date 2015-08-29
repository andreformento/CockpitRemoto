package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;

public class InstrucaoInterpreterInicializaExecucao implements InstrucaoInterpreter {

	@Override
	public void interpretar(ContextoInterpreter<String, ResultadoInterpreterInstrucao> contextoInterpreter) {
		ResultadoInterpreterInstrucao output = contextoInterpreter.getOutput();
		if (output.getCenarioProcessamento().isConsistente().getTipoResultado().isResultadoOk()) {
			output.getCenarioProcessamento().configurarResultado(new ResultadoImpl(TipoResultado.AVISO, "Início da execução"));
			output.inicializarInstrucaoList();
		}
	}

}
