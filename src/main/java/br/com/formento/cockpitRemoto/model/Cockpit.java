package br.com.formento.cockpitRemoto.model;

import br.com.formento.cockpitRemoto.service.factory.EntradaBuilder;

public interface Cockpit {

	Resultado executarLote(EntradaBuilder entradaBuilder);

	Resultado desfazerExecutarLote();

	CenarioProcessamento getCenarioProcessamento();

}
