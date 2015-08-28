package br.com.formento.cockpitRemoto.service.interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.formento.cockpitRemoto.model.Direcao;
import br.com.formento.cockpitRemoto.model.Movel;
import br.com.formento.cockpitRemoto.model.Posicao;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.Sonda;
import br.com.formento.cockpitRemoto.model.TipoResultado;

public class EstruturaInstrucaoAdicionarMovel extends AbstractEstruturaInstrucao {

	// encontrar dois numeros separados por espaço e uma letra maiuscula
	private static final Pattern PATTERN_PADRAO = Pattern.compile("^[\\s]*[\\d]+[ \\t\\r\\f]+[\\d]+[ \\t\\r\\f]+[A-Z]+[\\s]*$", Pattern.MULTILINE);

	private static final Pattern PATTERN_NUMERO = Pattern.compile("[\\d]+");
	private static final Pattern PATTERN_DIRECAO = Pattern.compile("[A-Z]+");

	private Movel movel;

	public EstruturaInstrucaoAdicionarMovel(String instrucaoEntrada) {
		super(instrucaoEntrada);
	}


	@Override
	protected Pattern getPatternPadrao() {
		return PATTERN_PADRAO;
	}

	public Movel getMovel() {
		return movel;
	}


	@Override
	protected Resultado getResultadoMontagemInterno() {
		Matcher matcherNumero = PATTERN_NUMERO.matcher(getInstrucaoEntrada());

		if (matcherNumero.find()) {
			Integer x = Integer.valueOf(matcherNumero.group());

			if (matcherNumero.find()) {
				Integer y = Integer.valueOf(matcherNumero.group());

				Matcher matcherDirecao = PATTERN_DIRECAO.matcher(getInstrucaoEntrada());
				if (matcherDirecao.find()) {
					String sigla = matcherDirecao.group();

					Direcao direcao = Direcao.getPorSigla(sigla);

					if (direcao == null) {
						return new ResultadoImpl(TipoResultado.ERRO, "O parâmetro direção está inválido (" + sigla + "): "
								+ getInstrucaoEntrada());
					} else {
						movel = new Sonda(new Posicao(x, y, direcao));

						return new ResultadoImpl(TipoResultado.SUCESSO, "Movel criado: " + movel.toString());
					}
				} else
					return new ResultadoImpl(TipoResultado.ERRO, "Não encontrou o parâmetro direção: " + getInstrucaoEntrada());
			} else
				return new ResultadoImpl(TipoResultado.ERRO, "Não encontrou o parâmetro y: " + getInstrucaoEntrada());
		} else {
			return new ResultadoImpl(TipoResultado.ERRO, "Não encontrou o parâmetro x: " + getInstrucaoEntrada());
		}
	}

}
