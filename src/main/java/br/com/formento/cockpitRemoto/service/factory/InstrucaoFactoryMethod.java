package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.service.command.Instrucao;

public interface InstrucaoFactoryMethod {

	Instrucao criarInstancia(Class<? extends Instrucao> instrucaoClass);

}
