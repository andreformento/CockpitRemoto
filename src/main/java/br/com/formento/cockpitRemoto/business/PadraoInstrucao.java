package br.com.formento.cockpitRemoto.business;

import br.com.formento.cockpitRemoto.service.command.Instrucao;

public interface PadraoInstrucao {

	String getInstrucaoEntrada();

	boolean isEncontrouPadrao();

	String getRepresentacaoFabrica();

	Class<? extends Instrucao> getInstrucaoClass();

}
