package be.vdab.entities;

import java.math.BigDecimal;

public class Film {
	private long filmNr;
	private long genreNr;
	private String titel;
	private int voorraad;
	private int gereserveerd;
	private BigDecimal prijs;
	
	public Film(long filmNr,long genreNr, String titel, int voorraad, int gereserveerd, BigDecimal prijs){
		setFilmNr(filmNr);
		setGenreNr(genreNr);
		setTitel(titel);
		setVoorraad(voorraad);
		setGereserveerd(gereserveerd);
		setPrijs(prijs);
	}

	public long getFilmNr() {
		return filmNr;
	}

	public void setFilmNr(long filmNr) {
		this.filmNr = filmNr;
	}

	public long getGenreNr() {
		return genreNr;
	}

	public void setGenreNr(long genreNr) {
		this.genreNr = genreNr;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public int getVoorraad() {
		return voorraad;
	}

	public void setVoorraad(int voorraad) {
		this.voorraad = voorraad;
	}

	public int getGereserveerd() {
		return gereserveerd;
	}

	public void setGereserveerd(int gereserveerd) {
		this.gereserveerd = gereserveerd;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}
	

}
