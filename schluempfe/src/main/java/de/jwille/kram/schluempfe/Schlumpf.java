package de.jwille.kram.schluempfe;

public class Schlumpf {

	private boolean schonGeschaltet;

	public void betreteZelle(Zelle zelle) {
		if (!schonGeschaltet && !zelle.isLicht()) {
			zelle.setLicht(true);
			schonGeschaltet = true;
		}
	}

	public boolean weissDassFertig() {
		return false;
	}
}
