package br.com.formento.cockpitRemoto.business;

import java.util.regex.Pattern;

public class PadraoInstrucaoQuantidadeComando extends AbstractPadraoInstrucao {
	private static final Pattern PATTERN_INSTRUCAO_VALIDA = Pattern.compile("^[\\t ]*[\\d]+ [a-zA-z_$]+[a-zA-z_$\\d\\t ]*$", Pattern.MULTILINE);
	private static final Pattern PATTERN_NOME_FABRICA = Pattern.compile("[a-zA-z_$]+[a-zA-z_$\\d]*");

	public PadraoInstrucaoQuantidadeComando(String instrucaoEntrada) {
		super(instrucaoEntrada);
	}

	@Override
	protected Pattern getPatternInstrucaoValida() {
		return PATTERN_INSTRUCAO_VALIDA;
	}

	@Override
	protected Pattern getPatternNomeFabrica() {
		return PATTERN_NOME_FABRICA;
	}

}
