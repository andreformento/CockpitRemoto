package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.service.command.Instrucao;

public class InstrucaoBuilderImpl implements InstrucaoBuilder {

	private Instrucao product;
	private CenarioProcessamento cenarioProcessamento;
	private Class<? extends Instrucao> classe;

	public InstrucaoBuilderImpl(Class<? extends Instrucao> classe, CenarioProcessamento cenarioProcessamento) {
		this.classe = classe;
		this.cenarioProcessamento = cenarioProcessamento;
	}

	@Override
	public void buildInstance() {
		try {
			product = classe.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void buildCenarioProcessamento() {
		product.configurarCenarioProcessamento(cenarioProcessamento);
	}

	@Override
	public Instrucao getProduct() {
		return product;
	}

}
