package br.com.formento.cockpitRemoto.service.factory;

import java.util.ArrayList;
import java.util.List;

import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreter;
import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreterAdicionarMovel;
import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreterComentario;
import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreterExecutar;
import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreterInicializaExecucao;
import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreterMovimento;
import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreterNaoEncontrada;
import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreterValidarMalha;
import br.com.formento.cockpitRemoto.service.interpreter.InstrucaoInterpreterVazio;
import br.com.formento.cockpitRemoto.service.interpreter.ResultadoInterpreterInstrucao;
import br.com.formento.cockpitRemoto.service.observer.ComandoObserverExecutarInstrucao;
import br.com.formento.cockpitRemoto.service.observer.ComandoSubjectImpl;

public class ComandoSubjectBuilderPadrao extends ComandoSubjectAbstractBuilder {

	private InstrucaoFlyweight instrucaoFlyweight;
	private ResultadoInterpreterInstrucao resultadoInterpreterInstrucao;

	public ComandoSubjectBuilderPadrao(InstrucaoFlyweight instrucaoFlyweight, ResultadoInterpreterInstrucao resultadoInterpreterInstrucao) {
		this.instrucaoFlyweight = instrucaoFlyweight;
		this.resultadoInterpreterInstrucao = resultadoInterpreterInstrucao;
	}

	@Override
	public void buildInstance() {
		product = new ComandoSubjectImpl();
	}

	@Override
	public void buildObservers() {
		List<InstrucaoInterpreter> list = new ArrayList<>();

		list.add(new InstrucaoInterpreterInicializaExecucao());

		list.add(new InstrucaoInterpreterAdicionarMovel(instrucaoFlyweight));
		list.add(new InstrucaoInterpreterMovimento(instrucaoFlyweight));
		list.add(new InstrucaoInterpreterValidarMalha(instrucaoFlyweight));

		list.add(new InstrucaoInterpreterVazio(instrucaoFlyweight));
		list.add(new InstrucaoInterpreterComentario(instrucaoFlyweight));

		list.add(new InstrucaoInterpreterExecutar(instrucaoFlyweight));

		list.add(new InstrucaoInterpreterNaoEncontrada(instrucaoFlyweight));

		for (InstrucaoInterpreter instrucaoInterpreter : list)
			product.attach(new ComandoObserverExecutarInstrucao(product, instrucaoInterpreter, resultadoInterpreterInstrucao));
	}

}
