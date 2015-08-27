package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;

public class InstrucaoInvalida implements Instrucao {

	private Resultado resultado;
	private CenarioProcessamento cenarioProcessamento;

	public InstrucaoInvalida() {
		this.resultado = new ResultadoImpl(TipoResultado.ERRO, "Instrução inválida");
	}

	@Override
	public void configurarCenarioProcessamento(CenarioProcessamento cenarioProcessamento) {
		this.cenarioProcessamento = cenarioProcessamento;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	@Override
	public Resultado isConsistente() {
		return resultado;
	}

	@Override
	public Resultado executar() {
		return resultado;
	}

	@Override
	public CenarioProcessamento getCenarioProcessamento() {
		return cenarioProcessamento;
	}

}
