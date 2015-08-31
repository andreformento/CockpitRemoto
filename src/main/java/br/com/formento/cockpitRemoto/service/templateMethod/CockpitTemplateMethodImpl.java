package br.com.formento.cockpitRemoto.service.templateMethod;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Entrada;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.service.factory.CenarioProcessamentoFactoryMethod;
import br.com.formento.cockpitRemoto.service.factory.ComandoSubjectDirector;
import br.com.formento.cockpitRemoto.service.factory.ComandoSubjectFactoryMethod;
import br.com.formento.cockpitRemoto.service.factory.EntradaBuilder;
import br.com.formento.cockpitRemoto.service.factory.EntradaDirector;
import br.com.formento.cockpitRemoto.service.factory.EntradaDirectorImpl;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweightFactoryMethod;
import br.com.formento.cockpitRemoto.service.factory.IteradorComandoSubjectFactoryMethod;
import br.com.formento.cockpitRemoto.service.factory.ResultadoInterpreterInstrucaoFactoryMethod;
import br.com.formento.cockpitRemoto.service.interpreter.ResultadoInterpreterInstrucao;
import br.com.formento.cockpitRemoto.service.iterator.IteradorComandoSubject;
import br.com.formento.cockpitRemoto.service.observer.ComandoSubject;

public class CockpitTemplateMethodImpl extends CockpitTemplateMethodAbstract {

	private final CenarioProcessamentoFactoryMethod cenarioProcessamentoFactoryMethod;
	private final ComandoSubjectFactoryMethod comandoSubjectFactoryMethod;
	private final IteradorComandoSubjectFactoryMethod iteradorComandoSubjectFactoryMethod;
	private final InstrucaoFlyweightFactoryMethod instrucaoFlyweightFactoryMethod;
	private final ResultadoInterpreterInstrucaoFactoryMethod resultadoInterpreterInstrucaoFactoryMethod;

	// atributos criados quando for rodado o sistema
	private ComandoSubject comandoSubject;
	private IteradorComandoSubject iteradorComandoSubject;
	private InstrucaoFlyweight instrucaoFlyweight;

	public CockpitTemplateMethodImpl(CenarioProcessamentoFactoryMethod cenarioProcessamentoFactoryMethod,
			ComandoSubjectFactoryMethod comandoSubjectFactoryMethod,
			IteradorComandoSubjectFactoryMethod iteradorComandoSubjectFactoryMethod,
			InstrucaoFlyweightFactoryMethod instrucaoFlyweightFactoryMethod,
			ResultadoInterpreterInstrucaoFactoryMethod resultadoInterpreterInstrucaoFactoryMethod) {
		this.cenarioProcessamentoFactoryMethod = cenarioProcessamentoFactoryMethod;
		this.comandoSubjectFactoryMethod = comandoSubjectFactoryMethod;
		this.iteradorComandoSubjectFactoryMethod = iteradorComandoSubjectFactoryMethod;
		this.instrucaoFlyweightFactoryMethod = instrucaoFlyweightFactoryMethod;
		this.resultadoInterpreterInstrucaoFactoryMethod = resultadoInterpreterInstrucaoFactoryMethod;
	}

	@Override
	public Entrada lerEntrada(EntradaBuilder entradaBuilder) {
		EntradaDirector entradaDirector = new EntradaDirectorImpl(entradaBuilder);
		entradaDirector.construirInstancia();
		Entrada entrada = entradaDirector.getProduct();

		return entrada;
	}

	@Override
	public CenarioProcessamento criarCenarioProcessamento() {
		return cenarioProcessamentoFactoryMethod.criarInstancia();
	}

	@Override
	protected void checklistDeInstancia() {
		instrucaoFlyweight = instrucaoFlyweightFactoryMethod.criarInstancia();
	}

	@Override
	protected void checklistDeExecucao(Entrada entrada) {
		ResultadoInterpreterInstrucao resultadoInterpreterInstrucao = resultadoInterpreterInstrucaoFactoryMethod
				.criarInstancia(getCenarioProcessamento());

		ComandoSubjectDirector comandoSubjectDirector = comandoSubjectFactoryMethod.criarInstancia(instrucaoFlyweight,
				resultadoInterpreterInstrucao);

		comandoSubjectDirector.construirInstancia();
		comandoSubject = comandoSubjectDirector.getProduct();

		iteradorComandoSubject = iteradorComandoSubjectFactoryMethod.criarInstancia(entrada.getComandoList(), comandoSubject);
	}

	@Override
	public Resultado processarLeitura() {
		return iteradorComandoSubject.percorrerLista();
	}

}
