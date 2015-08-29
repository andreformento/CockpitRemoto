package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.CenarioProcessamentoImpl;

public class CenarioProcessamentoFactoryMethodImpl implements CenarioProcessamentoFactoryMethod {

	@Override
	public CenarioProcessamento criarInstancia() {
		return new CenarioProcessamentoImpl();
	}

}
