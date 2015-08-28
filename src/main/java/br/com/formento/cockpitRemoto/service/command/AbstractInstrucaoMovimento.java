package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Resultado;

public abstract class AbstractInstrucaoMovimento implements Instrucao {

	protected abstract Resultado executarInterno(CenarioProcessamento cenarioProcessamento);

	@Override
	public final Resultado executar(CenarioProcessamento cenarioProcessamento) {
		// TODO applicar pattern

		Resultado resultadoMalha = cenarioProcessamento.validarMalha();
		if (resultadoMalha.getTipoResultado().isResultadoOk()) {
			Resultado resultadoMovel = cenarioProcessamento.validarMovel();
			if (resultadoMovel.getTipoResultado().isResultadoOk())
				return executarInterno(cenarioProcessamento);
			else
				return resultadoMovel;
		} else
			return resultadoMalha;
	}

}
