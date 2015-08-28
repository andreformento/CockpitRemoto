package br.com.formento.cockpitRemoto.service.facade;

import java.util.List;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Entrada;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.service.factory.CenarioProcessamentoFactoryMethod;
import br.com.formento.cockpitRemoto.service.factory.ComandoSubjectDirector;
import br.com.formento.cockpitRemoto.service.factory.ComandoSubjectFactoryMethod;
import br.com.formento.cockpitRemoto.service.factory.EntradaDirector;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweight;
import br.com.formento.cockpitRemoto.service.factory.InstrucaoFlyweightFactoryMethod;
import br.com.formento.cockpitRemoto.service.factory.IteradorComandoSubjectFactoryMethod;
import br.com.formento.cockpitRemoto.service.iterator.IteradorComandoSubject;
import br.com.formento.cockpitRemoto.service.observer.ComandoSubject;

public class SistemaCockpitFacadeImpl implements SistemaCockpitFacade {

	private final EntradaDirector entradaDirector;
	private final CenarioProcessamentoFactoryMethod cenarioProcessamentoFactoryMethod;
	private final ComandoSubjectFactoryMethod comandoSubjectFactoryMethod;
	private final IteradorComandoSubjectFactoryMethod iteradorComandoSubjectFactoryMethod;
	private final InstrucaoFlyweightFactoryMethod instrucaoFlyweightFactoryMethod;

	// atributos criados quando for rodado o sistema
	private CenarioProcessamento cenarioProcessamento;
	private Entrada entrada;
	private ComandoSubject comandoSubject;
	private List<String> comandoList;
	private IteradorComandoSubject iteradorComandoSubject;

	public SistemaCockpitFacadeImpl(EntradaDirector entradaDirector, CenarioProcessamentoFactoryMethod cenarioProcessamentoFactoryMethod,
			ComandoSubjectFactoryMethod comandoSubjectFactoryMethod, IteradorComandoSubjectFactoryMethod iteradorComandoSubjectFactoryMethod,
			InstrucaoFlyweightFactoryMethod instrucaoFlyweightFactoryMethod) {
		this.entradaDirector = entradaDirector;
		this.cenarioProcessamentoFactoryMethod = cenarioProcessamentoFactoryMethod;
		this.comandoSubjectFactoryMethod = comandoSubjectFactoryMethod;
		this.iteradorComandoSubjectFactoryMethod = iteradorComandoSubjectFactoryMethod;
		this.instrucaoFlyweightFactoryMethod = instrucaoFlyweightFactoryMethod;
	}

	@Override
	public Entrada lerEntrada() {
		entradaDirector.construirInstancia();
		entrada = entradaDirector.getProduct();
		comandoList = entrada.getComandoList();

		return entrada;
	}

	@Override
	public CenarioProcessamento criarCenarioProcessamento() {
		cenarioProcessamento = cenarioProcessamentoFactoryMethod.criarInstancia();
		return cenarioProcessamento;
	}

	@Override
	public void configurarComandos() {
		InstrucaoFlyweight instrucaoFlyweight = instrucaoFlyweightFactoryMethod.criarInstancia();
		
		ComandoSubjectDirector comandoSubjectDirector = comandoSubjectFactoryMethod.criarInstancia(instrucaoFlyweight, cenarioProcessamento);

		comandoSubjectDirector.construirInstancia();
		comandoSubject = comandoSubjectDirector.getProduct();

		iteradorComandoSubject = iteradorComandoSubjectFactoryMethod.criarInstancia(comandoList, comandoSubject);
	}

	@Override
	public Resultado executar() {
		return iteradorComandoSubject.percorrerLista();
	}

}
