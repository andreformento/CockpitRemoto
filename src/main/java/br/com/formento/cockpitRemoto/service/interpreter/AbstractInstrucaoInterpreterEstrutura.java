package br.com.formento.cockpitRemoto.service.interpreter;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.command.InstrucaoInvalida;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;

public abstract class AbstractInstrucaoInterpreterEstrutura extends AbstractInstrucaoInterpreter {

	public AbstractInstrucaoInterpreterEstrutura(InstrucaoFlyweight instrucaoFlyweight) {
		super(instrucaoFlyweight);
	}

	protected abstract EstruturaInstrucao gerarEstruturaInstrucao(String input);

	protected abstract Resultado gerarInstrucao(ResultadoInterpreterInstrucao output);

	@Override
	public final void interpretar(ContextoInterpreter<String, ResultadoInterpreterInstrucao> contextoInterpreter) {
		ResultadoInterpreterInstrucao output = contextoInterpreter.getOutput();
		if (TipoResultado.AVISO.equals(output.getCenarioProcessamento().isConsistente().getTipoResultado())) {
			/**
			 * Primeiro tenta reconhecer o comando em algum padrao de instrucao
			 */

			Resultado resultado;

			EstruturaInstrucao estruturaInstrucao = gerarEstruturaInstrucao(contextoInterpreter.getInput());
			Resultado resultadoEncontrouPadrao = estruturaInstrucao.getResultadoEncontrouPadrao();
			if (TipoResultado.SUCESSO.equals(resultadoEncontrouPadrao.getTipoResultado())) {
				Resultado resultadoMontagem = estruturaInstrucao.getResultadoMontagem();
				if (resultadoMontagem.getTipoResultado().isResultadoOk()) {
					resultado = gerarInstrucao(output);
				} else {
					output.addInstrucao(getInstrucaoFlyweight().getFlyweight(InstrucaoInvalida.class));
					resultado = resultadoMontagem;
				}
			} else
				resultado = resultadoEncontrouPadrao;

			output.getCenarioProcessamento().configurarResultado(resultado);
		}
	}

}
