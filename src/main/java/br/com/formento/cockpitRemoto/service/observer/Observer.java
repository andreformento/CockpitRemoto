package br.com.formento.cockpitRemoto.service.observer;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;

public interface Observer {

	Resultado update();

	/**
	 * 
	 * @return Tipo de resultado que faz parar as notificações
	 */
	TipoResultado getResultadoBreak();

}