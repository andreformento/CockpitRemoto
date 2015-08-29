package br.com.formento.cockpitRemoto.service.facade;

import java.util.List;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Cockpit;
import br.com.formento.cockpitRemoto.model.Entrada;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.service.adapter.RepositorioInstrucaoTarget;
import br.com.formento.cockpitRemoto.service.factory.CockpitBuilder;
import br.com.formento.cockpitRemoto.service.factory.CockpitBuilderPadrao;
import br.com.formento.cockpitRemoto.service.factory.CockpitDirector;
import br.com.formento.cockpitRemoto.service.factory.CockpitDirectorImpl;
import br.com.formento.cockpitRemoto.service.factory.EntradaBuilder;
import br.com.formento.cockpitRemoto.service.factory.EntradaBuilderArquivo;
import br.com.formento.cockpitRemoto.service.factory.EntradaBuilderList;

public class CockpitFacade implements Cockpit {

	private final Cockpit cockpit;

	private Resultado resultadoExecutar;

	public CockpitFacade(Cockpit cockpit) {
		this.cockpit = cockpit;
	}

	public CockpitFacade(EntradaBuilder entradaBuilder) {
		CockpitBuilder builder = new CockpitBuilderPadrao(entradaBuilder);
		CockpitDirector director = new CockpitDirectorImpl(builder);

		director.construirInstancia();
		cockpit = director.getProduct();
	}

	public CockpitFacade(RepositorioInstrucaoTarget repositorioInstrucaoTarget) {
		this(new EntradaBuilderArquivo(repositorioInstrucaoTarget));
	}

	public CockpitFacade(List<String> comandoList) {
		this(new EntradaBuilderList(comandoList));
	}

	@Override
	public Resultado executar() {
		if (resultadoExecutar == null)
			resultadoExecutar = cockpit.executar();
		return resultadoExecutar;
	}

	@Override
	public CenarioProcessamento getCenarioProcessamento() {
		executar();
		return cockpit.getCenarioProcessamento();
	}

	@Override
	public Entrada getEntrada() {
		executar();
		return cockpit.getEntrada();
	}

}
