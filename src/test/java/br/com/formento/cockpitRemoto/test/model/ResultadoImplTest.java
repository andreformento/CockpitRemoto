package br.com.formento.cockpitRemoto.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;

public class ResultadoImplTest {

	@Test
	public void testClone() throws CloneNotSupportedException {
		Resultado resultado = new ResultadoImpl(TipoResultado.ERRO, "mensagem de erro");
		Resultado clone = resultado.clone();

		assertEquals(resultado, clone);
		assertFalse(resultado == clone);
	}

}
