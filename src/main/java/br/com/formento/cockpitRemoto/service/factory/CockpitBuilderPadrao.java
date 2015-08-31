package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.service.templateMethod.CockpitTemplateMethodImpl;

public class CockpitBuilderPadrao extends CockpitAbstractBuilder {

	private CenarioProcessamentoFactoryMethod cenarioProcessamentoFactoryMethod;
	private IteradorComandoSubjectFactoryMethod iteradorComandoSubjectFactoryMethod;
	private ComandoSubjectFactoryMethodImpl comandoSubjectFactoryMethod;

	private InstrucaoFlyweightFactoryMethod instrucaoFlyweightFactoryMethod;

	private ResultadoInterpreterInstrucaoFactoryMethod resultadoInterpreterInstrucaoFactoryMethod;

	public CockpitBuilderPadrao() {
	}

	@Override
	public void buildCenarioProcessamentoFactoryMethod() {
		cenarioProcessamentoFactoryMethod = new CenarioProcessamentoFactoryMethodImpl();
	}

	@Override
	public void buildComandoSubjectFactory() {
		comandoSubjectFactoryMethod = new ComandoSubjectFactoryMethodImpl();
	}

	@Override
	public void buildIteradorComandoSubjectFactoryMethod() {
		iteradorComandoSubjectFactoryMethod = new IteradorComandoSubjectFactoryMethodImpl();

	}

	@Override
	public void buildInstrucaoFlyweightFactoryMethod() {
		instrucaoFlyweightFactoryMethod = new InstrucaoFlyweightFactoryMethodImpl();
	}

	@Override
	public void buildResultadoInterpreterInstrucaoFactoryMethod() {
		resultadoInterpreterInstrucaoFactoryMethod = new ResultadoInterpreterInstrucaoFactoryMethodImpl();
	}

	@Override
	public void buildInstance() {
		product = new CockpitTemplateMethodImpl(cenarioProcessamentoFactoryMethod, comandoSubjectFactoryMethod,
				iteradorComandoSubjectFactoryMethod, instrucaoFlyweightFactoryMethod, resultadoInterpreterInstrucaoFactoryMethod);
	}

}
