package br.com.formento.cockpitRemoto.service.adapter;

import java.util.List;

public interface RepositorioInstrucaoTarget {

	void abrir();

	List<String> ler();

	void fechar();

}
