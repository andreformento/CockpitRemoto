package br.com.formento.cockpitRemoto.test.service.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.formento.cockpitRemoto.model.Entrada;
import br.com.formento.cockpitRemoto.service.adapter.RepositorioInstrucaoTarget;
import br.com.formento.cockpitRemoto.service.factory.EntradaBuilder;
import br.com.formento.cockpitRemoto.service.factory.EntradaBuilderArquivo;
import br.com.formento.cockpitRemoto.service.factory.EntradaDirector;
import br.com.formento.cockpitRemoto.service.factory.EntradaDirectorImpl;

// usando mock para dependências

public class EntradaBuilderArquivoTest {

	@Mock
	private RepositorioInstrucaoTarget repositorioInstrucaoTarget;

	private EntradaBuilder entradaBuilder;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		entradaBuilder = new EntradaBuilderArquivo(repositorioInstrucaoTarget);
	}

	@Test
	public void testListaMock() {
		List<String> listaComandos = new ArrayList<>();
		listaComandos.add("comando 1");
		listaComandos.add("comando 2");
		listaComandos.add("comando 3");

		when(repositorioInstrucaoTarget.ler()).thenReturn(listaComandos);

		EntradaDirector entradaDirector = new EntradaDirectorImpl(entradaBuilder);
		entradaDirector.construirInstancia();
		Entrada entrada = entradaDirector.getProduct();

		assertNotNull(entrada);
		assertNotNull(entrada.getComandoList());
		assertFalse(entrada.getComandoList().isEmpty());
	}

}
