package br.com.formento.cockpitRemoto.model;

public class CenarioProcessamentoImpl implements CenarioProcessamento {

	private Malha malha;
	private Movel movel;
	private Resultado resultado;

	public CenarioProcessamentoImpl() {
		resultado = new ResultadoImpl(TipoResultado.AVISO, "Nenhuma instrução executada");
	}

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

	@Override
	public Resultado isConsistente() {
		return resultado;
	}

	@Override
	public Resultado validarMalha() {
		if (malha == null)
			return new ResultadoImpl(TipoResultado.ERRO, "A malha está nula");
		else
			return malha.isConsistente();
	}

	@Override
	public Resultado validarMovel() {
		if (movel == null)
			return new ResultadoImpl(TipoResultado.ERRO, "O movel está nulo");
		else
			return movel.isConsistente();
	}

	@Override
	public void configurarResultado(Resultado resultado) {
		this.resultado = resultado;
	}

}
