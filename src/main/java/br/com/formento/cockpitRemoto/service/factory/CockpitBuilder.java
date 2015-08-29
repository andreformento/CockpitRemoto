package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.model.Cockpit;

public interface CockpitBuilder extends Builder<Cockpit> {

	void buildCenarioProcessamentoFactoryMethod();

	void buildComandoSubjectFactory();

	void buildIteradorComandoSubjectFactoryMethod();

	void buildInstrucaoFlyweightFactoryMethod();

	void buildResultadoInterpreterInstrucaoFactoryMethod();

	void buildInstance();

}
