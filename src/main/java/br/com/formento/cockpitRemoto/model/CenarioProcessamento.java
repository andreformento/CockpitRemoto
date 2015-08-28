package br.com.formento.cockpitRemoto.model;

public interface CenarioProcessamento extends EntidadeConsistente {

	void setMalha(Malha malha);

	Malha getMalha();

	void setMovel(Movel movel);

	Movel getMovel();

	Resultado validarMalha();

	Resultado validarMovel();

	void configurarResultado(Resultado resultado);

}
