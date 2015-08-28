package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.MalhaRetangular;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.service.command.InstrucaoValidarMalha;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;

public class InstrucaoInterpreterValidarMalha extends AbstractInstrucaoInterpreterEstrutura {

	private EstruturaInstrucaoValidarMalha estruturaInstrucao;

	public InstrucaoInterpreterValidarMalha(InstrucaoFlyweight instrucaoFlyweight, CenarioProcessamento cenarioProcessamento) {
		super(instrucaoFlyweight, cenarioProcessamento);
	}

	@Override
	protected EstruturaInstrucao getEstruturaInstrucao(String input) {
		if (estruturaInstrucao == null)
			estruturaInstrucao = new EstruturaInstrucaoValidarMalha(input);
		return estruturaInstrucao;
	}

	@Override
	protected Resultado gerarInstrucao(ResultadoInterpreterInstrucao output) {
		MalhaRetangular malhaRetangular = estruturaInstrucao.getMalhaRetangular();
		getCenarioProcessamento().setMalha(malhaRetangular);
		output.addInstrucao(getInstrucaoFlyweight().getFlyweight(InstrucaoValidarMalha.class));
		return estruturaInstrucao.getResultadoMontagem();
	}

}
