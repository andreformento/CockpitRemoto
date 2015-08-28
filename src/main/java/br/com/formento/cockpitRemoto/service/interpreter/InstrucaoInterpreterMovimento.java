package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.service.command.Instrucao;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;

public class InstrucaoInterpreterMovimento extends AbstractInstrucaoInterpreterEstrutura {

	private EstruturaInstrucaoMovimento estruturaInstrucao;

	public InstrucaoInterpreterMovimento(InstrucaoFlyweight instrucaoFlyweight, CenarioProcessamento cenarioProcessamento) {
		super(instrucaoFlyweight, cenarioProcessamento);
	}

	@Override
	protected EstruturaInstrucao getEstruturaInstrucao(String input) {
		if (estruturaInstrucao == null)
			estruturaInstrucao = new EstruturaInstrucaoMovimento(input);
		return estruturaInstrucao;
	}

	@Override
	protected Resultado gerarInstrucao(ResultadoInterpreterInstrucao output) {
		for (Class<? extends Instrucao> instrucaoClass : estruturaInstrucao.getInstrucaoClassList())
			output.addInstrucao(getInstrucaoFlyweight().getFlyweight(instrucaoClass));

		return estruturaInstrucao.getResultadoMontagem();
	}

}
