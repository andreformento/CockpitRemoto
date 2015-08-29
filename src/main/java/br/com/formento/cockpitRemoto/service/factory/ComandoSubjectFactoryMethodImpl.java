package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.service.interpreter.ResultadoInterpreterInstrucao;

public class ComandoSubjectFactoryMethodImpl implements ComandoSubjectFactoryMethod {

	@Override
	public ComandoSubjectDirector criarInstancia(InstrucaoFlyweight instrucaoFlyweight, ResultadoInterpreterInstrucao resultadoInterpreterInstrucao) {
		ComandoSubjectBuilder builder = new ComandoSubjectBuilderPadrao(instrucaoFlyweight, resultadoInterpreterInstrucao);
		ComandoSubjectDirector director = new ComandoSubjectDirectorImpl(builder);

		return director;
	}

}
