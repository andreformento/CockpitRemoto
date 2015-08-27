package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;

public abstract class AbstractInstrucaoMovimento extends AbstractInstrucao {

	@Override
	public Resultado isConsistente() {
		// TODO applicar pattern
		if (getCenarioProcessamento().getMalha() == null)
			return new ResultadoImpl(TipoResultado.ERRO, "DiscoverClassByAnnotationTest malha está nula");
		else if (getCenarioProcessamento().getMovel() == null)
			return new ResultadoImpl(TipoResultado.ERRO, "O movel está nulo");
		else {
			Resultado resultado = getCenarioProcessamento().getMalha().isConsistente();
			if (resultado.getTipoResultado().isResultadoOk())
				return getCenarioProcessamento().getMovel().isConsistente();
			else
				return resultado;
		}
	}

}
