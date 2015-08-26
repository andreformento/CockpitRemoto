package br.com.formento.cockpitRemoto.model;

public class Posicao implements Comparable<Posicao> {

	private Integer x;
	private Integer y;
	private Direcao direcao;

	public Posicao(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public Posicao(Integer x, Integer y, Direcao direcao) {
		this(x, y);
		this.direcao = direcao;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public void girarSentidoHorario() {
		this.direcao = direcao.getProximoSentidoHorario();
	}

	public void girarSentidoAntiHorario() {
		this.direcao = direcao.getProximoSentidoAntiHorario();
	}

	public Direcao getDirecao() {
		return direcao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicao other = (Posicao) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Posicao [x=" + x + ", y=" + y + ", direcao=" + direcao + "]";
	}

	/**
	 * Se X ou Y for maior que o atual, já deve considerar maior
	 */
	@Override
	public int compareTo(Posicao o) {
		int compareToX = getX().compareTo(o.getX());
		int compareToY = getY().compareTo(o.getY());

		if (compareToX == 0 && compareToY == 0)
			return 0;
		else if (compareToX <= 0 && compareToY <= 0)
			return -1;
		else
			return 1;
	}

}