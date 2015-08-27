package br.com.formento.cockpitRemoto.service.iterator;

import java.util.List;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.service.command.Instrucao;

public class IteradorInstrucaoImpl extends AbstractIteradorInterno<Instrucao> implements IteradorInstrucao {

	public IteradorInstrucaoImpl(List<Instrucao> listaPercorrer) {
		super(listaPercorrer);
	}

	@Override
	public Resultado operacao(Instrucao item) {
		return item.executar();
	}

}
