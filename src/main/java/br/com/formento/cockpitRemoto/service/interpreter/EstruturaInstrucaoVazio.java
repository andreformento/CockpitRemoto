package br.com.formento.cockpitRemoto.service.interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;

public class EstruturaInstrucaoVazio extends AbstractEstruturaInstrucao {

	private static final Pattern PATTERN_PADRAO = Pattern.compile("^[ \\t\\r\\f]*$", Pattern.MULTILINE);

	private static final Pattern PATTERN_PARAMETROS = Pattern.compile("[ \\t\\r\\f]*");

	public EstruturaInstrucaoVazio(String instrucaoEntrada) {
		super(instrucaoEntrada);
	}

	@Override
	protected Pattern getPatternPadrao() {
		return PATTERN_PADRAO;
	}

	@Override
	protected Resultado getResultadoMontagemInterno() {
		Matcher matcher = PATTERN_PARAMETROS.matcher(getInstrucaoEntrada());

		if (matcher.find())
			return new ResultadoImpl(TipoResultado.SUCESSO, "Comando vazio");
		else
			return new ResultadoImpl(TipoResultado.ERRO, "O comando não está vazio: " + getInstrucaoEntrada());
	}

}
