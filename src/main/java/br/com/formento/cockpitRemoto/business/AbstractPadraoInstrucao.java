package br.com.formento.cockpitRemoto.business;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.formento.cockpitRemoto.service.command.Instrucao;
import br.com.formento.cockpitRemoto.service.singleton.ListaInstrucaoSingleton;

public abstract class AbstractPadraoInstrucao implements PadraoInstrucao {

	protected final String instrucaoEntrada;

	private Boolean isInstrucaoValida;
	private String representacaoFabrica;

	public AbstractPadraoInstrucao(String instrucaoEntrada) {
		this.instrucaoEntrada = instrucaoEntrada;
	}

	protected abstract Pattern getPatternInstrucaoValida();

	protected abstract Pattern getPatternNomeFabrica();

	@Override
	public boolean isEncontrouPadrao() {
		if (isInstrucaoValida == null) {
			Matcher matcher = getPatternInstrucaoValida().matcher(instrucaoEntrada);
			isInstrucaoValida = matcher.find();
		}

		return isInstrucaoValida;
	}

	@Override
	public String getRepresentacaoFabrica() {
		if (representacaoFabrica == null) {
			String representacao;

			if (isEncontrouPadrao()) {
				Matcher matcher = getPatternNomeFabrica().matcher(instrucaoEntrada);

				if (matcher.find())
					representacao = matcher.group();
				else
					representacao = "";
			} else
				representacao = "";

			representacaoFabrica = representacao;
		}

		return representacaoFabrica;
	}

	@Override
	public Class<? extends Instrucao> getInstrucaoClass() {
		return ListaInstrucaoSingleton.getInstance().getInstrucaoClass(getRepresentacaoFabrica());
	}

	@Override
	public String getInstrucaoEntrada() {
		return instrucaoEntrada;
	}

}
