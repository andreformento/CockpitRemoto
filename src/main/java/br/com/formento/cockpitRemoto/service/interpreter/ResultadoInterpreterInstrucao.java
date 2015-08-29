package br.com.formento.cockpitRemoto.service.interpreter;

import java.util.List;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.service.command.Instrucao;

public interface ResultadoInterpreterInstrucao {

	void inicializarInstrucaoList();

	List<Instrucao> getInstrucaoList();

	boolean addInstrucao(Instrucao instrucao);

	CenarioProcessamento getCenarioProcessamento();

}
