package be.vdab.entities;

public class Klant {
	private long klantnr;
	private String naam;
	private String voornaam;
	private String straat;
	private int postcode;
	private String gemeente;
	
	public Klant(long klantnr, String naam, String voornaam, String straat, int postcode, String gemeente){
		setKlantnr(klantnr);
		setNaam(naam);
		setVoornaam(voornaam);
		setStraat(straat);
		
		setPostcode(postcode);
		setGemeente(gemeente);
		
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}




	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getGemeente() {
		return gemeente;
	}

	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}

	public long getKlantnr() {
		return klantnr;
	}

	public void setKlantnr(long klantnr) {
		this.klantnr = klantnr;
	}
	
	

}
