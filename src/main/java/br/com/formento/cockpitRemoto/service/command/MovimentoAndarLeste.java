package br.com.formento.cockpitRemoto.service.command;

public class MovimentoAndarLeste extends AbstractMovimentoAndarParaFrente {

	@Override
	public Integer somarEmX() {
		return 1;
	}

	@Override
	public Integer somarEmY() {
		return 0;
	}

}
