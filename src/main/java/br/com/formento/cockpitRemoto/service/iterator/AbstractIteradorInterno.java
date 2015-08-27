package br.com.formento.cockpitRemoto.service.iterator;

import java.util.List;

import br.com.formento.cockpitRemoto.model.Resultado;
import br.com.formento.cockpitRemoto.model.ResultadoImpl;
import br.com.formento.cockpitRemoto.model.TipoResultado;

public abstract class AbstractIteradorInterno<T> implements IteradorInterno<T> {

	private List<T> listaPercorrer;
	private int contador;

	public AbstractIteradorInterno(List<T> listaPercorrer) {
		this.listaPercorrer = listaPercorrer;
		this.contador = 0;
	}

	@Override
	public void first() {
		contador = 0;
	}

	@Override
	public void next() {
		contador++;
	}

	@Override
	public void voltar() {
		contador--;
	}

	@Override
	public boolean isDone() {
		return contador == listaPercorrer.size();
	}

	@Override
	public T currentItem() {
		if (isDone())
			contador = listaPercorrer.size() - 1;
		else if (contador < 0)
			contador = 0;

		return listaPercorrer.get(contador);
	}

	@Override
	public boolean isEmpty() {
		return listaPercorrer.isEmpty();
	}

	@Override
	public Resultado percorrerLista() {
		for (first(); !isDone(); next()) {
			Resultado resultadoOperacao = operacao(currentItem());
			if (!resultadoOperacao.getTipoResultado().isResultadoOk())
				return resultadoOperacao;
		}

		return new ResultadoImpl(TipoResultado.SUCESSO, "Execução concluida com sucesso");
	}

}
