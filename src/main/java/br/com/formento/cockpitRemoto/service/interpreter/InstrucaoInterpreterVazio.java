package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.service.command.InstrucaoVazio;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;

public class InstrucaoInterpreterVazio extends AbstractInstrucaoInterpreterEstrutura {

	private EstruturaInstrucaoVazio estruturaInstrucao;

	public InstrucaoInterpreterVazio(InstrucaoFlyweight instrucaoFlyweight) {
		super(instrucaoFlyweight);
	}

	@Override
	protected EstruturaInstrucao gerarEstruturaInstrucao(String input) {
		estruturaInstrucao = new EstruturaInstrucaoVazio(input);
		return estruturaInstrucao;
	}

	@Override
	protected Resultado gerarInstrucao(ResultadoInterpreterInstrucao output) {
		output.addInstrucao(getInstrucaoFlyweight().getFlyweight(InstrucaoVazio.class));
		return estruturaInstrucao.getResultadoMontagem();
	}

}
