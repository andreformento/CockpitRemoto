package br.com.formento.cockpitRemoto.service.command;

public class MovimentoAndarSul extends AbstractMovimentoAndarParaFrente {

	@Override
	public Integer somarEmX() {
		return 0;
	}

	@Override
	public Integer somarEmY() {
		return -1;
	}

}
