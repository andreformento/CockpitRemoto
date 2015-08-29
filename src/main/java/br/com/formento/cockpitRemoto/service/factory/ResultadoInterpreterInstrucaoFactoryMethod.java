package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.service.interpreter.ResultadoInterpreterInstrucao;

public interface ResultadoInterpreterInstrucaoFactoryMethod {

	ResultadoInterpreterInstrucao criarInstancia(CenarioProcessamento cenarioProcessamento);

}
