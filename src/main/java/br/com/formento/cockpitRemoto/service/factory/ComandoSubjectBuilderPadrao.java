package br.com.formento.cockpitRemoto.service.factory;

import java.util.ArrayList;
import java.util.List;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreter;
import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreterAdicionarMovel;
import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreterExecutar;
import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreterMovimento;
import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreterValidarMalha;
import br.com.formento.cockpitRemoto.service.observer.ComandoObserverExecutarInstrucao;
import br.com.formento.cockpitRemoto.service.observer.ComandoSubjectImpl;

public class ComandoSubjectBuilderPadrao extends ComandoSubjectAbstractBuilder {

	private InstrucaoFlyweight instrucaoFlyweight;
	private CenarioProcessamento cenarioProcessamento;

	public ComandoSubjectBuilderPadrao(InstrucaoFlyweight instrucaoFlyweight, CenarioProcessamento cenarioProcessamento) {
		this.instrucaoFlyweight = instrucaoFlyweight;
		this.cenarioProcessamento = cenarioProcessamento;
	}

	@Override
	public void buildInstance() {
		product = new ComandoSubjectImpl();
	}

	@Override
	public void buildObservers() {
		List<InstrucaoInterpreter> list = new ArrayList<>();

		list.add(new InstrucaoInterpreterAdicionarMovel(instrucaoFlyweight, cenarioProcessamento));
		list.add(new InstrucaoInterpreterMovimento(instrucaoFlyweight, cenarioProcessamento));
		list.add(new InstrucaoInterpreterValidarMalha(instrucaoFlyweight, cenarioProcessamento));
		list.add(new InstrucaoInterpreterExecutar(instrucaoFlyweight, cenarioProcessamento));

		for (InstrucaoInterpreter instrucaoInterpreter : list)
			product.attach(new ComandoObserverExecutarInstrucao(product, instrucaoInterpreter));
	}

}
