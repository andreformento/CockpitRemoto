package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;
import br.com.formento.cockpitRemoto.service.iterator.IteradorInstrucaoImpl;

public class InstrucaoInterpreterExecutar extends AbstractInstrucaoInterpreter implements InstrucaoInterpreter {

	public InstrucaoInterpreterExecutar(InstrucaoFlyweight instrucaoFlyweight, CenarioProcessamento cenarioProcessamento) {
		super(instrucaoFlyweight, cenarioProcessamento);
	}

	@Override
	public void interpretar(ContextoInterpreter<String, ResultadoInterpreterInstrucao> contextoInterpreter) {
		ResultadoInterpreterInstrucao output = contextoInterpreter.getOutput();
		if (TipoResultado.SUCESSO.equals(output.isConsistente().getTipoResultado())) {
			// executar
			IteradorInstrucaoImpl iteradorInstrucaoImpl = new IteradorInstrucaoImpl(getCenarioProcessamento(), output.getInstrucaoList());
			Resultado resultado = iteradorInstrucaoImpl.percorrerLista();
			output.setResultado(resultado);
		}
	}

}
