package br.com.formento.cockpitRemoto.service.iterator;

import br.com.formento.cockpitRemoto.model.CenarioProcessamento;
import br.com.formento.cockpitRemoto.service.command.Instrucao;

public interface IteradorInstrucao extends IteradorInterno<Instrucao> {

	CenarioProcessamento getCenarioProcessamento();

}
