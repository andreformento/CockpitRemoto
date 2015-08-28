package br.com.formento.cockpitRemoto.service.iterator;

import java.util.List;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.service.command.Instrucao;

public class IteradorInstrucaoImpl extends AbstractIteradorInterno<Instrucao> implements IteradorInstrucao {

	private CenarioProcessamento cenarioProcessamento;

	public IteradorInstrucaoImpl(CenarioProcessamento cenarioProcessamento, List<Instrucao> listaPercorrer) {
		super(listaPercorrer);
		this.cenarioProcessamento = cenarioProcessamento;
	}

	@Override
	public Resultado operacao(Instrucao item) {
		return item.executar(cenarioProcessamento);
	}

	@Override
	public CenarioProcessamento getCenarioProcessamento() {
		return cenarioProcessamento;
	}

}
