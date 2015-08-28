package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;

@InstrucaoAnnotation(nomeInstrucao = "validarMalha")
public class InstrucaoValidarMalha implements Instrucao {

	@Override
	public Resultado executar(CenarioProcessamento cenarioProcessamento) {
		Resultado resultadoMalha = cenarioProcessamento.validarMalha();
		if (resultadoMalha.getTipoResultado().isResultadoOk())
			return new ResultadoImpl(TipoResultado.SUCESSO, "A malha está válida: " + cenarioProcessamento.getMalha().toString());
		else
			return resultadoMalha;
	}
}
