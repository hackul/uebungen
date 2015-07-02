package de.jwille.kram.schluempfe;

public class Zaehlschlumpf extends Schlumpf {
	private int wieOftAusgeschaltet = 0;

	@Override
	public void betreteZelle(Zelle zelle) {
		if (zelle.isLicht()) {
			wieOftAusgeschaltet++;
			zelle.setLicht(false);
		}

	}

	@Override
	public boolean weissDassFertig() {
		return wieOftAusgeschaltet >= Konstanten.ANZAHL_SCHLUEMPFE - 1;
	}

}
