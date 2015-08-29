package br.com.formento.cockpitRemoto.service.interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;

public class EstruturaInstrucaoComentario extends AbstractEstruturaInstrucao {

	private static final Pattern PATTERN_PADRAO = Pattern.compile("^[ \\t\\r\\f]*[#]+[^\\n]*$", Pattern.MULTILINE);

	private static final Pattern PATTERN_PARAMETROS = Pattern.compile("[#]+[^\n]*");

	public EstruturaInstrucaoComentario(String instrucaoEntrada) {
		super(instrucaoEntrada);
	}

	@Override
	protected Pattern getPatternPadrao() {
		return PATTERN_PADRAO;
	}

	@Override
	protected Resultado getResultadoMontagemInterno() {
		Matcher matcher = PATTERN_PARAMETROS.matcher(getInstrucaoEntrada());

		if (matcher.find()) {
			StringBuilder comentario = new StringBuilder();
			comentario.append(matcher.group());
			comentario.delete(0, 1);
			
			return new ResultadoImpl(TipoResultado.SUCESSO, comentario.toString());
		} else
			return new ResultadoImpl(TipoResultado.ERRO, "Comentário inválido: " + getInstrucaoEntrada());
	}

}
