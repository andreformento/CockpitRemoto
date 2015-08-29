package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.service.interpreter.ResultadoInterpreterInstrucao;

public interface ComandoSubjectFactoryMethod {

	ComandoSubjectDirector criarInstancia(InstrucaoFlyweight instrucaoFlyweight, ResultadoInterpreterInstrucao resultadoInterpreterInstrucao);

}
