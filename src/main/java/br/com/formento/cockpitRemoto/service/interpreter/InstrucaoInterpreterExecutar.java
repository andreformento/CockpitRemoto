package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;
import br.com.formento.cockpitRemoto.service.iterator.IteradorInstrucaoImpl;

public class InstrucaoInterpreterExecutar extends AbstractInstrucaoInterpreter implements InstrucaoInterpreter {

	public InstrucaoInterpreterExecutar(InstrucaoFlyweight instrucaoFlyweight) {
		super(instrucaoFlyweight);
	}

	@Override
	public void interpretar(ContextoInterpreter<String, ResultadoInterpreterInstrucao> contextoInterpreter) {
		ResultadoInterpreterInstrucao output = contextoInterpreter.getOutput();
		if (TipoResultado.SUCESSO.equals(output.getCenarioProcessamento().isConsistente().getTipoResultado())) {
			// executar
			IteradorInstrucaoImpl iteradorInstrucaoImpl = new IteradorInstrucaoImpl(output.getCenarioProcessamento(), output.getInstrucaoList());
			Resultado resultado = iteradorInstrucaoImpl.percorrerLista();
			output.getCenarioProcessamento().configurarResultado(resultado);
		}
	}

}
