package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.command.InstrucaoInvalida;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;

public abstract class AbstractInstrucaoInterpreterEstrutura extends AbstractInstrucaoInterpreter {

	public AbstractInstrucaoInterpreterEstrutura(InstrucaoFlyweight instrucaoFlyweight, CenarioProcessamento cenarioProcessamento) {
		super(instrucaoFlyweight, cenarioProcessamento);
	}

	protected abstract EstruturaInstrucao getEstruturaInstrucao(String input);

	protected abstract Resultado gerarInstrucao(ResultadoInterpreterInstrucao output);

	@Override
	public final void interpretar(ContextoInterpreter<String, ResultadoInterpreterInstrucao> contextoInterpreter) {
		/**
		 * Primeiro tenta reconhecer o comando em algum padrao de instrucao
		 */

		Resultado resultado;

		EstruturaInstrucao estruturaInstrucao = getEstruturaInstrucao(contextoInterpreter.getInput());
		Resultado resultadoEncontrouPadrao = estruturaInstrucao.getResultadoEncontrouPadrao();
		if (TipoResultado.SUCESSO.equals(resultadoEncontrouPadrao.getTipoResultado())) {
			Resultado resultadoMontagem = estruturaInstrucao.getResultadoMontagem();
			if (resultadoMontagem.getTipoResultado().isResultadoOk()) {
				ResultadoInterpreterInstrucao output = contextoInterpreter.getOutput();
				resultado = gerarInstrucao(output);
			} else {
				getCenarioProcessamento().configurarResultado(resultadoMontagem);
				contextoInterpreter.getOutput().addInstrucao(getInstrucaoFlyweight().getFlyweight(InstrucaoInvalida.class));
				resultado = resultadoMontagem;
			}
		} else
			resultado = resultadoEncontrouPadrao;

		contextoInterpreter.getOutput().setResultado(resultado);
	}

}
