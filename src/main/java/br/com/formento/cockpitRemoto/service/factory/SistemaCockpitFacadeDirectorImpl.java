package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.service.facade.SistemaCockpitFacade;

public class SistemaCockpitFacadeDirectorImpl implements SistemaCockpitFacadeDirector {

	protected SistemaCockpitFacadeBuilder builder;

	public SistemaCockpitFacadeDirectorImpl(SistemaCockpitFacadeBuilder builder) {
		this.builder = builder;
	}

	@Override
	public void construirInstancia() {
		builder.buildEntradaDirector();

		builder.buildCenarioProcessamentoFactoryMethod();

		builder.buildComandoSubjectFactory();

		builder.buildIteradorComandoSubjectFactoryMethod();

		builder.buildInstance();
	}

	@Override
	public SistemaCockpitFacade getProduct() {
		return builder.getProduct();
	}

}
