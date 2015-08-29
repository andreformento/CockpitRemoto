package br.com.formento.cockpitRemoto.service.templateMethod;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Cockpit;
import br.com.formento.cockpitRemoto.model.Entrada;
import br.com.formento.cockpitRemoto.model.Resultado;

public abstract class CockpitTemplateMethodAbstract implements Cockpit {

	protected Entrada entrada;
	protected CenarioProcessamento cenarioProcessamento;

	public abstract Entrada lerEntrada();

	public abstract CenarioProcessamento criarCenarioProcessamento();

	public abstract void configurarComandos();

	public abstract Resultado processarLeitura();

	@Override
	public final Resultado executar() {
		entrada = lerEntrada();
		cenarioProcessamento = criarCenarioProcessamento();
		configurarComandos();
		return processarLeitura();
	}

	@Override
	public Entrada getEntrada() {
		return entrada;
	}

	@Override
	public CenarioProcessamento getCenarioProcessamento() {
		return cenarioProcessamento;
	}

}
