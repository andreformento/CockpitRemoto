package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.service.facade.SistemaCockpitFacade;

public interface SistemaCockpitFacadeBuilder extends Builder<SistemaCockpitFacade> {

	void buildEntradaDirector();

	void buildCenarioProcessamentoFactoryMethod();

	void buildComandoSubjectFactory();

	void buildIteradorComandoSubjectFactoryMethod();
	
	void buildInstrucaoFlyweightFactoryMethod();

	void buildInstance();

}
