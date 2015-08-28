package br.com.formento.cockpitRemoto.service.facade;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Entrada;
import br.com.formento.cockpitRemoto.model.Resultado;

public interface SistemaCockpitFacade {

	Entrada lerEntrada();

	CenarioProcessamento criarCenarioProcessamento();

	void configurarComandos();

	Resultado executar();

}