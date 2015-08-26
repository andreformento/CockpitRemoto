package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.Malha;
import br.com.formento.cockpitRemoto.model.Movel;

public abstract class AbstractInstrucao implements Instrucao {

	private Malha malha;
	private Movel movel;

	public AbstractInstrucao(Malha malha) {
		this.malha = malha;
	}

	public Malha getMalha() {
		return malha;
	}

	public Movel getMovel() {
		return movel;
	}

	public void setMovel(Movel movel) {
		this.movel = movel;
	}

}
