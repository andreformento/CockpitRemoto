package br.com.formento.cockpitRemoto.service.facade;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Cockpit;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.service.factory.CockpitBuilder;
import br.com.formento.cockpitRemoto.service.factory.CockpitBuilderPadrao;
import br.com.formento.cockpitRemoto.service.factory.CockpitDirector;
import br.com.formento.cockpitRemoto.service.factory.CockpitDirectorImpl;
import br.com.formento.cockpitRemoto.service.factory.EntradaBuilder;

public class CockpitFacade implements Cockpit {

	private final Cockpit cockpit;

	public CockpitFacade() {
		CockpitBuilder builder = new CockpitBuilderPadrao();
		CockpitDirector director = new CockpitDirectorImpl(builder);

		director.construirInstancia();
		this.cockpit = director.getProduct();
	}

	@Override
	public Resultado executarLote(EntradaBuilder entradaBuilder) {
		return cockpit.executarLote(entradaBuilder);
	}

	@Override
	public CenarioProcessamento getCenarioProcessamento() {
		return cockpit.getCenarioProcessamento();
	}

	@Override
	public Resultado desfazerExecutarLote() {
		return cockpit.desfazerExecutarLote();
	}

}
