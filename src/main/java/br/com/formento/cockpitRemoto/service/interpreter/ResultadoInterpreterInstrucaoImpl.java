package br.com.formento.cockpitRemoto.service.interpreter;

import java.util.ArrayList;
import java.util.List;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.service.command.Instrucao;

public class ResultadoInterpreterInstrucaoImpl implements ResultadoInterpreterInstrucao {

	private List<Instrucao> instrucaoList;
	private CenarioProcessamento cenarioProcessamento;

	public ResultadoInterpreterInstrucaoImpl(CenarioProcessamento cenarioProcessamento) {
		this.cenarioProcessamento = cenarioProcessamento;
	}

	@Override
	public List<Instrucao> getInstrucaoList() {
		return instrucaoList;
	}

	@Override
	public boolean addInstrucao(Instrucao instrucao) {
		return instrucaoList.add(instrucao);
	}

	@Override
	public CenarioProcessamento getCenarioProcessamento() {
		return cenarioProcessamento;
	}

	@Override
	public void inicializarInstrucaoList() {
		this.instrucaoList = new ArrayList<>();
	}

}
