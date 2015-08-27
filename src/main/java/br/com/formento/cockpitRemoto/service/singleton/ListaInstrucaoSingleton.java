package br.com.formento.cockpitRemoto.service.singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.formento.cockpitRemoto.service.command.Instrucao;
import br.com.formento.cockpitRemoto.service.command.InstrucaoAnnotation;
import br.com.formento.cockpitRemoto.service.command.InstrucaoInvalida;
import br.com.formento.cockpitRemoto.util.DiscoverClassByAnnotation;

/**
 * Pega as anotações pelo nome da classe
 * 
 * @author andre.formento
 *
 */
public class ListaInstrucaoSingleton {

	private static ListaInstrucaoSingleton instance = new ListaInstrucaoSingleton();

	public static ListaInstrucaoSingleton getInstance() {
		return instance;
	}

	private Map<String, Class<? extends Instrucao>> instrucoes;

	// TODO aplicar pattern builder
	private ListaInstrucaoSingleton() {
		instrucoes = new HashMap<>();

		DiscoverClassByAnnotation<InstrucaoAnnotation> discoverClassByAnnotation = new DiscoverClassByAnnotation<>(InstrucaoAnnotation.class);
		Set<Class<?>> classes = discoverClassByAnnotation.getClasses();

		for (Class<?> c : classes) {
			InstrucaoAnnotation instrucaoAnnotation = c.getAnnotation(InstrucaoAnnotation.class);
			if (instrucaoAnnotation != null) {
				String nomeInstrucao = instrucaoAnnotation.nomeInstrucao();

				@SuppressWarnings("unchecked")
				Class<? extends Instrucao> instrucaoClass = (Class<? extends Instrucao>) c;

				instrucoes.put(nomeInstrucao, instrucaoClass);
			}
		}
	}

	public Class<? extends Instrucao> getInstrucaoClass(String comando) {
		if (instrucoes.containsKey(comando))
			return instrucoes.get(comando);
		else
			return InstrucaoInvalida.class;
	}

}
