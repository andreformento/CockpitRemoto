package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.model.Cockpit;

public class CockpitDirectorImpl implements CockpitDirector {

	protected CockpitBuilder builder;

	public CockpitDirectorImpl(CockpitBuilder builder) {
		this.builder = builder;
	}

	@Override
	public void construirInstancia() {
		builder.buildCenarioProcessamentoFactoryMethod();

		builder.buildComandoSubjectFactory();

		builder.buildIteradorComandoSubjectFactoryMethod();

		builder.buildInstrucaoFlyweightFactoryMethod();

		builder.buildResultadoInterpreterInstrucaoFactoryMethod();

		builder.buildInstance();
	}

	@Override
	public Cockpit getProduct() {
		return builder.getProduct();
	}

}
