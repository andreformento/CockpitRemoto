package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.service.command.Instrucao;

public class InstrucaoFactoryMethodImpl implements InstrucaoFactoryMethod {

	@Override
	public Instrucao criarInstancia(Class<? extends Instrucao> instrucaoClass) {
		try {
			return instrucaoClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}
