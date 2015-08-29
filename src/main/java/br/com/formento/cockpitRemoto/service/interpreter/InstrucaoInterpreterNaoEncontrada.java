package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.command.InstrucaoInvalida;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;

public class InstrucaoInterpreterNaoEncontrada extends AbstractInstrucaoInterpreter implements InstrucaoInterpreter {

	public InstrucaoInterpreterNaoEncontrada(InstrucaoFlyweight instrucaoFlyweight) {
		super(instrucaoFlyweight);
	}

	@Override
	public void interpretar(ContextoInterpreter<String, ResultadoInterpreterInstrucao> contextoInterpreter) {
		ResultadoInterpreterInstrucao output = contextoInterpreter.getOutput();
		if (TipoResultado.AVISO.equals(output.getCenarioProcessamento().isConsistente().getTipoResultado())) {
			ResultadoImpl resultado = new ResultadoImpl(TipoResultado.ERRO, "Instrução não encontrada: " + contextoInterpreter.getInput());
			output.getCenarioProcessamento().configurarResultado(resultado);
			output.addInstrucao(getInstrucaoFlyweight().getFlyweight(InstrucaoInvalida.class));
		}
	}

}
