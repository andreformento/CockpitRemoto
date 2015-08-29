package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.MalhaRetangular;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.service.command.InstrucaoValidarMalha;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;

public class InstrucaoInterpreterValidarMalha extends AbstractInstrucaoInterpreterEstrutura {

	private EstruturaInstrucaoValidarMalha estruturaInstrucao;

	public InstrucaoInterpreterValidarMalha(InstrucaoFlyweight instrucaoFlyweight) {
		super(instrucaoFlyweight);
	}

	@Override
	protected EstruturaInstrucao gerarEstruturaInstrucao(String input) {
		estruturaInstrucao = new EstruturaInstrucaoValidarMalha(input);
		return estruturaInstrucao;
	}

	@Override
	protected Resultado gerarInstrucao(ResultadoInterpreterInstrucao output) {
		MalhaRetangular malhaRetangular = estruturaInstrucao.getMalhaRetangular();
		output.getCenarioProcessamento().setMalha(malhaRetangular);
		output.addInstrucao(getInstrucaoFlyweight().getFlyweight(InstrucaoValidarMalha.class));
		return estruturaInstrucao.getResultadoMontagem();
	}

}
