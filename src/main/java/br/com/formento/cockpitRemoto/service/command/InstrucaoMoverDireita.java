package br.com.formento.cockpitRemoto.service.command;

import br.com.formento.cockpitRemoto.model.Malha;
import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;

public class InstrucaoMoverDireita extends AbstractInstrucao {

	public InstrucaoMoverDireita(Malha malha) {
		super(malha);
	}

	@Override
	public Resultado executar() {
		getMovel().getPosicao().girarSentidoHorario();

		return new ResultadoImpl(TipoResultado.SUCESSO, "Movel girou sentido horário");
	}

}
