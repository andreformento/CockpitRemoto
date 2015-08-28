package br.com.formento.cockpitRemoto.service.interpreter;

import java.util.ArrayList;
import java.util.List;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.command.Instrucao;

public class ResultadoInterpreterInstrucaoImpl implements ResultadoInterpreterInstrucao {

	private final List<Instrucao> instrucaoList;
	private Resultado resultado;

	public ResultadoInterpreterInstrucaoImpl(Resultado resultado) {
		this.instrucaoList = new ArrayList<>();
		this.resultado = resultado;
	}

	public ResultadoInterpreterInstrucaoImpl() {
		this(new ResultadoImpl(TipoResultado.ERRO, "Resultado não identificado"));
	}

	@Override
	public Resultado isConsistente() {
		return resultado;
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
	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

}
