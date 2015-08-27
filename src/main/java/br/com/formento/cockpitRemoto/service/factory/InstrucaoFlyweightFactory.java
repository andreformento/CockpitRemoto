package br.com.formento.cockpitRemoto.service.factory;

import java.util.HashMap;
import java.util.Map;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.service.command.Instrucao;

public class InstrucaoFlyweightFactory {
	protected Map<Class<? extends Instrucao>, Instrucao> flyweights;
	private CenarioProcessamento cenarioProcessamento;

	public InstrucaoFlyweightFactory(CenarioProcessamento cenarioProcessamento) {
		this.flyweights = new HashMap<>();
		this.cenarioProcessamento = cenarioProcessamento;
	}

	public Instrucao getFlyweight(Class<? extends Instrucao> classInstrucao) {
		if (flyweights.containsKey(classInstrucao))
			return flyweights.get(classInstrucao);
		else {
			InstrucaoBuilder instrucaoBuilder = new InstrucaoBuilderImpl(classInstrucao, cenarioProcessamento);
			InstrucaoDirector instrucaoDirector = new InstrucaoDirectorImpl(instrucaoBuilder);

			instrucaoDirector.construirInstancia();
			Instrucao instrucao = instrucaoDirector.getProduct();

			flyweights.put(classInstrucao, instrucao);

			return instrucao;
		}
	}

}
