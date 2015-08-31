package br.com.formento.cockpitRemoto.resource.service;

import java.util.List;

import br.com.formento.cockpitRemoto.model.Cockpit;
import br.com.formento.cockpitRemoto.model.Relatorio;
import br.com.formento.cockpitRemoto.service.facade.CockpitFacade;
import br.com.formento.cockpitRemoto.service.factory.EntradaBuilder;
import br.com.formento.cockpitRemoto.service.factory.EntradaBuilderList;

import com.google.gson.Gson;

public class EntryPointServiceImpl implements EntryPointService {

	private Cockpit cockpit;

	public EntryPointServiceImpl() {
		cockpit = new CockpitFacade();
	}

	@Override
	public String processarComandos(List<String> comandos) {
		EntradaBuilder entradaBuilder = new EntradaBuilderList(comandos);
		cockpit.executarLote(entradaBuilder);
		Relatorio relatorio = cockpit.getCenarioProcessamento().gerarRelatorio();

		Gson gson = new Gson();
		String json = gson.toJson(relatorio);

		return json;
	}
}
