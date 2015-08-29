package br.com.formento.cockpitRemoto.model;

public interface Cockpit {

	Resultado executar();

	CenarioProcessamento getCenarioProcessamento();

	Entrada getEntrada();

}
