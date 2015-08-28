package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;

public interface ComandoSubjectFactoryMethod {

	ComandoSubjectDirector criarInstancia(InstrucaoFlyweight instrucaoFlyweight, CenarioProcessamento cenarioProcessamento);

}
