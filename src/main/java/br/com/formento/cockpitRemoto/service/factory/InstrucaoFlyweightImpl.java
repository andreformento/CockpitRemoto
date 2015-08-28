package br.com.formento.cockpitRemoto.service.factory;

import java.util.HashMap;
import java.util.Map;

import br.com.formento.cockpitRemoto.service.command.Instrucao;

public class InstrucaoFlyweightImpl implements InstrucaoFlyweight {

	protected Map<Class<? extends Instrucao>, Instrucao> flyweights;

	private InstrucaoFactoryMethod factoryMethod = new InstrucaoFactoryMethodImpl();

	public InstrucaoFlyweightImpl() {
		this.flyweights = new HashMap<>();
	}

	@Override
	public Instrucao getFlyweight(Class<? extends Instrucao> instrucaoClass) {
		if (flyweights.containsKey(instrucaoClass))
			return flyweights.get(instrucaoClass);
		else {
			Instrucao instrucao = factoryMethod.criarInstancia(instrucaoClass);

			flyweights.put(instrucaoClass, instrucao);

			return instrucao;
		}
	}

}
