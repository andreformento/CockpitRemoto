package br.com.formento.cockpitRemoto.model;

public interface CenarioProcessamento extends EntidadeConsistente, Cloneable {

	void setMalha(Malha malha);

	Malha getMalha();

	void setMovel(Movel movel);

	Movel getMovel();

	Resultado validarMalha();

	Resultado validarMovel();

	void configurarResultado(Resultado resultado);

	CenarioProcessamento clone() throws CloneNotSupportedException;

	Relatorio gerarRelatorio();

}
