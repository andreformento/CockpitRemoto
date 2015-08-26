package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.Resultado;

public class InstrucaoInvalida implements Instrucao {

	private Resultado resultado;

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	@Override
	public Resultado executar() {
		return resultado;
	}

}
