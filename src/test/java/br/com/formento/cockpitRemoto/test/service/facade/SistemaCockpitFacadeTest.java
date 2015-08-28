package br.com.formento.cockpitRemoto.test.service.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.TipoResultado;
import br.com.formento.cockpitRemoto.service.facade.SistemaCockpitFacade;
import br.com.formento.cockpitRemoto.service.factory.EntradaBuilder;
import br.com.formento.cockpitRemoto.service.factory.EntradaBuilderList;
import br.com.formento.cockpitRemoto.service.factory.SistemaCockpitFacadeBuilder;
import br.com.formento.cockpitRemoto.service.factory.SistemaCockpitFacadeBuilderPadrao;
import br.com.formento.cockpitRemoto.service.factory.SistemaCockpitFacadeDirector;
import br.com.formento.cockpitRemoto.service.factory.SistemaCockpitFacadeDirectorImpl;

public class SistemaCockpitFacadeTest {

	private SistemaCockpitFacade sistemaCockpitFacade;
	private List<String> comandoList;

	@Before
	public void init() {
		comandoList = new ArrayList<>();

		EntradaBuilder entradaBuilder = new EntradaBuilderList(comandoList);
		SistemaCockpitFacadeBuilder builder = new SistemaCockpitFacadeBuilderPadrao(entradaBuilder);
		SistemaCockpitFacadeDirector director = new SistemaCockpitFacadeDirectorImpl(builder);

		director.construirInstancia();
		sistemaCockpitFacade = director.getProduct();
	}

	@Test
	public void testSemComandos() {
		Resultado resultado = sistemaCockpitFacade.executar();
		assertNotNull(resultado);
		assertEquals(resultado.toString(), TipoResultado.AVISO, resultado.getTipoResultado());

		// TODO fazer os outros testes (com outros comandos) e arrumar essa chamada do facade, provavelmente aplicar outro pattern
	}

}
