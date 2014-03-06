package be.vdab.entities;

public class Genre {
	private long genreNr;
	private String naam;
	
	public Genre(long genreNr, String naam){
		setGenreNr(genreNr);
		setNaam(naam);
	}
	
	public long getGenreNr() {
		return genreNr;
	}
	public void setGenreNr(long genreNr) {
		this.genreNr = genreNr;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}

}
