package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.service.facade.SistemaCockpitFacadeImpl;

public class SistemaCockpitFacadeBuilderPadrao extends SistemaCockpitFacadeAbstractBuilder {

	private final EntradaBuilder entradaBuilder;

	private EntradaDirector entradaDirector;
	private CenarioProcessamentoFactoryMethod cenarioProcessamentoFactoryMethod;
	private IteradorComandoSubjectFactoryMethod iteradorComandoSubjectFactoryMethod;
	private ComandoSubjectFactoryMethodImpl comandoSubjectFactoryMethod;

	private InstrucaoFlyweightFactoryMethod instrucaoFlyweightFactoryMethod;

	public SistemaCockpitFacadeBuilderPadrao(EntradaBuilder entradaBuilder) {
		this.entradaBuilder = entradaBuilder;
	}

	@Override
	public void buildEntradaDirector() {
		entradaDirector = new EntradaDirectorImpl(entradaBuilder);
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
	public void buildInstance() {
		product = new SistemaCockpitFacadeImpl(entradaDirector, cenarioProcessamentoFactoryMethod, comandoSubjectFactoryMethod,
				iteradorComandoSubjectFactoryMethod, instrucaoFlyweightFactoryMethod);
	}

}
