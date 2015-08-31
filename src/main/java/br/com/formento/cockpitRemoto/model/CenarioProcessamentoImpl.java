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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((malha == null) ? 0 : malha.hashCode());
		result = prime * result + ((movel == null) ? 0 : movel.hashCode());
		result = prime * result + ((resultado == null) ? 0 : resultado.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CenarioProcessamentoImpl other = (CenarioProcessamentoImpl) obj;
		if (malha == null) {
			if (other.malha != null)
				return false;
		} else if (!malha.equals(other.malha))
			return false;
		if (movel == null) {
			if (other.movel != null)
				return false;
		} else if (!movel.equals(other.movel))
			return false;
		if (resultado == null) {
			if (other.resultado != null)
				return false;
		} else if (!resultado.equals(other.resultado))
			return false;
		return true;
	}

	@Override
	public CenarioProcessamentoImpl clone() throws CloneNotSupportedException {
		CenarioProcessamentoImpl clone = (CenarioProcessamentoImpl) super.clone();

		if (malha != null)
			clone.malha = malha.clone();

		if (movel != null)
			clone.movel = movel.clone();

		if (resultado != null)
			clone.resultado = resultado.clone();

		return clone;
	}

}
