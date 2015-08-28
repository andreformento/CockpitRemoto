package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;

public class ComandoSubjectFactoryMethodImpl implements ComandoSubjectFactoryMethod {

	@Override
	public ComandoSubjectDirector criarInstancia(InstrucaoFlyweight instrucaoFlyweight, CenarioProcessamento cenarioProcessamento) {
		ComandoSubjectBuilder builder = new ComandoSubjectBuilderPadrao(instrucaoFlyweight, cenarioProcessamento);
		ComandoSubjectDirector director = new ComandoSubjectDirectorImpl(builder);

		return director;
	}

}
