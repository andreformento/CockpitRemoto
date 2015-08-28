package br.com.formento.cockpitRemoto.service.interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.formento.cockpitRemoto.model.MalhaRetangular;
import br.com.formento.cockpitRemoto.model.Posicao;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;

public class EstruturaInstrucaoValidarMalha extends AbstractEstruturaInstrucao {

	// encontrar dois numeros separados por espaço
	private static final Pattern PATTERN_PADRAO = Pattern.compile("^[ \\t\\r\\f]*[\\d]+[ \\t\\r\\f]+[\\d]+[ \\t\\r\\f]*$", Pattern.MULTILINE);

	private static final Pattern PATTERN_PARAMETROS = Pattern.compile("[\\d]+");

	private MalhaRetangular malhaRetangular;

	public EstruturaInstrucaoValidarMalha(String instrucaoEntrada) {
		super(instrucaoEntrada);
	}

	@Override
	protected Pattern getPatternPadrao() {
		return PATTERN_PADRAO;
	}

	public MalhaRetangular getMalhaRetangular() {
		return malhaRetangular;
	}

	@Override
	protected Resultado getResultadoMontagemInterno() {
		Matcher matcher = PATTERN_PARAMETROS.matcher(getInstrucaoEntrada());

		if (matcher.find()) {
			Integer x = Integer.valueOf(matcher.group());

			if (matcher.find()) {
				Integer y = Integer.valueOf(matcher.group());

				malhaRetangular = new MalhaRetangular(new Posicao(x, y));
				return new ResultadoImpl(TipoResultado.SUCESSO, "Malha criada: " + malhaRetangular.toString());
			} else
				return new ResultadoImpl(TipoResultado.ERRO, "Não encontrou o parâmetro y: " + getInstrucaoEntrada());
		} else {
			return new ResultadoImpl(TipoResultado.ERRO, "Não encontrou o parâmetro x: " + getInstrucaoEntrada());
		}
	}

}
