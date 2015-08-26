package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.Malha;
import br.com.formento.cockpitRemoto.model.Resultado;

public class InstrucaoAdicionar extends AbstractInstrucao {

	public InstrucaoAdicionar(Malha malha) {
		super(malha);
	}

	@Override
	public Resultado executar() {
		return getMalha().adicionarMovel(getMovel());
	}

}
