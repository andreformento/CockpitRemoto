package br.com.formento.cockpitRemoto.service.factory;

public class InstrucaoFlyweightFactoryMethodImpl implements InstrucaoFlyweightFactoryMethod {

	@Override
	public InstrucaoFlyweight criarInstancia() {
		return new InstrucaoFlyweightImpl();
	}

}
