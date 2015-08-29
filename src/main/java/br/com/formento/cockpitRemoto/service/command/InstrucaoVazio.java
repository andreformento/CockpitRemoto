package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;

@InstrucaoAnnotation(nomeInstrucao = "vazio")
public class InstrucaoVazio implements Instrucao {

	@Override
	public Resultado executar(CenarioProcessamento cenarioProcessamento) {
		return new ResultadoImpl(TipoResultado.SUCESSO, "Instrução vazia");
	}
}
