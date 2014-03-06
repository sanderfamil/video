package be.vdab.entities;

import java.util.Date;

public class Reservatie {
	private long klantNr;
	private long filmNr;
	private Date datum;
	
	public Reservatie(long klantNr, long filmNr, Date datum){
		setKlantNr(klantNr);
		setFilmNr(filmNr);
		setDatum(datum);
	}
	public Reservatie(long klantNr, long filmNr){
		setKlantNr(klantNr);
		setFilmNr(filmNr);
	}

	public long getKlantNr() {
		return klantNr;
	}

	public void setKlantNr(long klantNr) {
		this.klantNr = klantNr;
	}

	public long getFilmNr() {
		return filmNr;
	}

	public void setFilmNr(long filmNr) {
		this.filmNr = filmNr;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
	

}
