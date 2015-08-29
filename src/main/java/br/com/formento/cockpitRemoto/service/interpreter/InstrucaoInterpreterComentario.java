package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.service.command.InstrucaoComentario;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;

public class InstrucaoInterpreterComentario extends AbstractInstrucaoInterpreterEstrutura {

	private EstruturaInstrucaoComentario estruturaInstrucao;

	public InstrucaoInterpreterComentario(InstrucaoFlyweight instrucaoFlyweight) {
		super(instrucaoFlyweight);
	}

	@Override
	protected EstruturaInstrucao gerarEstruturaInstrucao(String input) {
		estruturaInstrucao = new EstruturaInstrucaoComentario(input);
		return estruturaInstrucao;
	}

	@Override
	protected Resultado gerarInstrucao(ResultadoInterpreterInstrucao output) {
		output.addInstrucao(getInstrucaoFlyweight().getFlyweight(InstrucaoComentario.class));
		return estruturaInstrucao.getResultadoMontagem();
	}

}
