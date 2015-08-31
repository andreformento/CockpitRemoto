package br.com.formento.cockpitRemoto.comparator;

import java.util.Comparator;

import br.com.formento.cockpitRemoto.model.Movel;

public class MovelComparableByOrdem implements Comparator<Movel> {

	@Override
	public int compare(Movel o1, Movel o2) {
		return o1.getOrdem().compareTo(o2.getOrdem());
	}

}
