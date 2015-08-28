package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.service.command.Instrucao;

public interface InstrucaoFlyweight {

	Instrucao getFlyweight(Class<? extends Instrucao> instrucaoClass);

}