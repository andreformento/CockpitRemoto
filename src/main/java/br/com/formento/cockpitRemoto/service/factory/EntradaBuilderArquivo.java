package br.com.formento.cockpitRemoto.service.factory;

import br.com.formento.cockpitRemoto.model.EntradaImpl;
import br.com.formento.cockpitRemoto.service.adapter.RepositorioInstrucaoTarget;

public class EntradaBuilderArquivo extends EntradaAbstractBuilder implements EntradaBuilder {

	private RepositorioInstrucaoTarget repositorioInstrucaoTarget;

	public EntradaBuilderArquivo(RepositorioInstrucaoTarget repositorioInstrucaoTarget) {
		this.repositorioInstrucaoTarget = repositorioInstrucaoTarget;
	}

	@Override
	public void buildComandoList() {
		try {
			repositorioInstrucaoTarget.abrir();
			comandoList = repositorioInstrucaoTarget.ler();
		} finally {
			repositorioInstrucaoTarget.fechar();
		}
	}

	@Override
	public void buildInstance() {
		product = new EntradaImpl(comandoList);
	}

}
