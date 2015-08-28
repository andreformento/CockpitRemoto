package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Resultado;

public interface Instrucao {

	Resultado executar(CenarioProcessamento cenarioProcessamento);

}
