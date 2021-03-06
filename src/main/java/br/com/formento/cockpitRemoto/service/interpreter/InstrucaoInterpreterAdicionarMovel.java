package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.Movel;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.service.command.InstrucaoAdicionarMovel;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;

public class InstrucaoInterpreterAdicionarMovel extends AbstractInstrucaoInterpreterEstrutura {

	private EstruturaInstrucaoAdicionarMovel estruturaInstrucao;

	public InstrucaoInterpreterAdicionarMovel(InstrucaoFlyweight instrucaoFlyweight) {
		super(instrucaoFlyweight);
	}

	@Override
	protected EstruturaInstrucao gerarEstruturaInstrucao(String input) {
		estruturaInstrucao = new EstruturaInstrucaoAdicionarMovel(input);
		return estruturaInstrucao;
	}

	@Override
	protected Resultado gerarInstrucao(ResultadoInterpreterInstrucao output) {
		Movel movel = estruturaInstrucao.getMovel();
		output.getCenarioProcessamento().setMovel(movel);
		output.addInstrucao(getInstrucaoFlyweight().getFlyweight(InstrucaoAdicionarMovel.class));
		return estruturaInstrucao.getResultadoMontagem();
	}

}
