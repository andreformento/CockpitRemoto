package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;

@InstrucaoAnnotation(nomeInstrucao = "L")
public class InstrucaoRotacionarEsquerda extends AbstractInstrucaoMovimento {

	@Override
	public Resultado executarInterno() {
		getCenarioProcessamento().getMovel().getPosicao().rotacionarSentidoAntiHorario();

		return new ResultadoImpl(TipoResultado.SUCESSO, "Movel rotacionou sentido anti-horário");
	}

}
