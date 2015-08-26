package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.Malha;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;

public class InstrucaoMoverEsquerda extends AbstractInstrucao {

	public InstrucaoMoverEsquerda(Malha malha) {
		super(malha);
	}

	@Override
	public Resultado executar() {
		getMovel().getPosicao().girarSentidoAntiHorario();

		return new ResultadoImpl(TipoResultado.SUCESSO, "Movel girou sentido anti-horário");
	}

}
