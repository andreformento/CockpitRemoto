package br.com.formento.cockpitRemoto.service.templateMethod;

import java.util.List;

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

	private final EntradaBuilder entradaBuilder;
	private final CenarioProcessamentoFactoryMethod cenarioProcessamentoFactoryMethod;
	private final ComandoSubjectFactoryMethod comandoSubjectFactoryMethod;
	private final IteradorComandoSubjectFactoryMethod iteradorComandoSubjectFactoryMethod;
	private final InstrucaoFlyweightFactoryMethod instrucaoFlyweightFactoryMethod;
	private final ResultadoInterpreterInstrucaoFactoryMethod resultadoInterpreterInstrucaoFactoryMethod;

	// atributos criados quando for rodado o sistema
	private ComandoSubject comandoSubject;
	private List<String> comandoList;
	private IteradorComandoSubject iteradorComandoSubject;

	public CockpitTemplateMethodImpl(EntradaBuilder entradaBuilder, CenarioProcessamentoFactoryMethod cenarioProcessamentoFactoryMethod,
			ComandoSubjectFactoryMethod comandoSubjectFactoryMethod, IteradorComandoSubjectFactoryMethod iteradorComandoSubjectFactoryMethod,
			InstrucaoFlyweightFactoryMethod instrucaoFlyweightFactoryMethod,
			ResultadoInterpreterInstrucaoFactoryMethod resultadoInterpreterInstrucaoFactoryMethod) {
		this.entradaBuilder = entradaBuilder;
		this.cenarioProcessamentoFactoryMethod = cenarioProcessamentoFactoryMethod;
		this.comandoSubjectFactoryMethod = comandoSubjectFactoryMethod;
		this.iteradorComandoSubjectFactoryMethod = iteradorComandoSubjectFactoryMethod;
		this.instrucaoFlyweightFactoryMethod = instrucaoFlyweightFactoryMethod;
		this.resultadoInterpreterInstrucaoFactoryMethod = resultadoInterpreterInstrucaoFactoryMethod;
	}

	@Override
	public Entrada lerEntrada() {
		EntradaDirector entradaDirector = new EntradaDirectorImpl(entradaBuilder);
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
		ResultadoInterpreterInstrucao resultadoInterpreterInstrucao = resultadoInterpreterInstrucaoFactoryMethod.criarInstancia(cenarioProcessamento);

		ComandoSubjectDirector comandoSubjectDirector = comandoSubjectFactoryMethod.criarInstancia(instrucaoFlyweight, resultadoInterpreterInstrucao);

		comandoSubjectDirector.construirInstancia();
		comandoSubject = comandoSubjectDirector.getProduct();

		iteradorComandoSubject = iteradorComandoSubjectFactoryMethod.criarInstancia(comandoList, comandoSubject);
	}

	@Override
	public Resultado processarLeitura() {
		return iteradorComandoSubject.percorrerLista();
	}

}
