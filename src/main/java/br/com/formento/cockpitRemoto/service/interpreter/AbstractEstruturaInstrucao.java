package br.com.formento.cockpitRemoto.service.interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;

public abstract class AbstractEstruturaInstrucao implements EstruturaInstrucao {

	private String instrucaoEntrada;
	private Resultado resultadoEncontrouPadrao;
	private Resultado resultadoMontagem;

	public AbstractEstruturaInstrucao(String instrucaoEntrada) {
		this.instrucaoEntrada = instrucaoEntrada;
	}

	@Override
	public String getInstrucaoEntrada() {
		return instrucaoEntrada;
	}

	protected abstract Pattern getPatternPadrao();

	@Override
	public final Resultado getResultadoEncontrouPadrao() {
		if (resultadoEncontrouPadrao == null) {
			Matcher matcher = getPatternPadrao().matcher(getInstrucaoEntrada());
			boolean find = matcher.matches();

			TipoResultado tipoResultado;
			String mensagem;

			if (find) {
				tipoResultado = TipoResultado.SUCESSO;
				mensagem = "Encontrou padrão: " + getInstrucaoEntrada();
			} else {
				tipoResultado = TipoResultado.AVISO;
				mensagem = "Não encontrou padrão: " + getInstrucaoEntrada();
			}

			resultadoEncontrouPadrao = new ResultadoImpl(tipoResultado, mensagem);
		}

		return resultadoEncontrouPadrao;
	}

	protected abstract Resultado getResultadoMontagemInterno();

	@Override
	public Resultado getResultadoMontagem() {
		if (resultadoMontagem == null) {
			Resultado result;

			if (TipoResultado.SUCESSO.equals(getResultadoEncontrouPadrao().getTipoResultado()))
				result = getResultadoMontagemInterno();
			else
				result = getResultadoEncontrouPadrao();

			resultadoMontagem = result;
		}

		return resultadoMontagem;
	}

}
