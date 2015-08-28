package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.Resultado;

public interface EstruturaInstrucao {

	Resultado getResultadoMontagem();

	String getInstrucaoEntrada();

	Resultado getResultadoEncontrouPadrao();

}
