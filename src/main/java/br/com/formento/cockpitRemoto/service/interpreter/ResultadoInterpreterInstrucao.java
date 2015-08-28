package br.com.formento.cockpitRemoto.service.interpreter;

import java.util.List;

import br.com.formento.cockpitRemoto.model.EntidadeConsistente;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.service.command.Instrucao;

public interface ResultadoInterpreterInstrucao extends EntidadeConsistente {

	List<Instrucao> getInstrucaoList();

	boolean addInstrucao(Instrucao instrucao);

	void setResultado(Resultado resultado);

}
