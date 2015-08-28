package br.com.formento.cockpitRemoto.service.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.command.Instrucao;
import br.com.formento.cockpitRemoto.service.command.InstrucaoInvalida;
import br.com.formento.cockpitRemoto.service.singleton.ListaInstrucaoSingleton;

public class EstruturaInstrucaoMovimento extends AbstractEstruturaInstrucao {

	// encontrar caracteres maiusculos
	private static final Pattern PATTERN_PADRAO = Pattern.compile("^[ \\t\\r\\f]*[A-Z]+[ \\t\\r\\f]*$", Pattern.MULTILINE);

	private static final Pattern PATTERN_PARAMETROS = Pattern.compile("[A-Z]");

	private List<Class<? extends Instrucao>> instrucaoClassList;

	public EstruturaInstrucaoMovimento(String instrucaoEntrada) {
		super(instrucaoEntrada);
		this.instrucaoClassList = new ArrayList<>();
	}

	public List<Class<? extends Instrucao>> getInstrucaoClassList() {
		return instrucaoClassList;
	}

	@Override
	protected Pattern getPatternPadrao() {
		return PATTERN_PADRAO;
	}

	@Override
	protected Resultado getResultadoMontagemInterno() {
		Matcher matcher = PATTERN_PARAMETROS.matcher(getInstrucaoEntrada());
		while (matcher.find()) {
			String comando = matcher.group();

			Class<? extends Instrucao> instrucaoClass = ListaInstrucaoSingleton.getInstance().getInstrucaoClass(comando);

			Resultado resultado;
			if (instrucaoClass.equals(InstrucaoInvalida.class))
				resultado = new ResultadoImpl(TipoResultado.ERRO, "O comando \"" + comando + "\" é inválido: " + getInstrucaoEntrada());
			else
				resultado = new ResultadoImpl(TipoResultado.SUCESSO, "Instrução reconhecida \"" + comando + "\": " + instrucaoClass.getName());

			instrucaoClassList.add(instrucaoClass);
			if (!resultado.getTipoResultado().isResultadoOk())
				return resultado;
		}

		StringBuilder mensagem = new StringBuilder();
		TipoResultado tipoResultado;
		if (instrucaoClassList.isEmpty()) {
			mensagem.append("Nenhum comando foi encontrado: ");
			mensagem.append(getInstrucaoEntrada());

			tipoResultado = TipoResultado.ERRO;
		} else {
			mensagem.append("Quantidade de instruções: ");
			mensagem.append(instrucaoClassList.size());

			tipoResultado = TipoResultado.SUCESSO;
		}
		return new ResultadoImpl(tipoResultado, mensagem.toString());
	}

}
