package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Resultado;

public abstract class AbstractInstrucao implements Instrucao {

	private CenarioProcessamento cenarioProcessamento;

	@Override
	public void configurarCenarioProcessamento(CenarioProcessamento cenarioProcessamento) {
		this.cenarioProcessamento = cenarioProcessamento;
	}

	@Override
	public CenarioProcessamento getCenarioProcessamento() {
		return cenarioProcessamento;
	}

	protected abstract Resultado executarInterno();

	@Override
	public final Resultado executar() {
		Resultado consistente = isConsistente();
		if (consistente.getTipoResultado().isResultadoOk())
			return executarInterno();
		else
			return consistente;
	}

}
