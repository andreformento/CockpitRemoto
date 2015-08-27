package br.com.formento.cockpitRemoto.model;

public class CenarioProcessamentoImpl implements CenarioProcessamento {
	
	private Malha malha;
	private Movel movel;

	public Malha getMalha() {
		return malha;
	}

	public void setMalha(Malha malha) {
		this.malha = malha;
	}

	public Movel getMovel() {
		return movel;
	}

	public void setMovel(Movel movel) {
		this.movel = movel;
	}

}
