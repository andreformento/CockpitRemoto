package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.service.interpreter.ResultadoInterpreterInstrucao;
import br.com.formento.cockpitRemoto.service.interpreter.ResultadoInterpreterInstrucaoImpl;

public class ResultadoInterpreterInstrucaoFactoryMethodImpl implements ResultadoInterpreterInstrucaoFactoryMethod {

	@Override
	public ResultadoInterpreterInstrucao criarInstancia(CenarioProcessamento cenarioProcessamento) {
		return new ResultadoInterpreterInstrucaoImpl(cenarioProcessamento);
	}

}
