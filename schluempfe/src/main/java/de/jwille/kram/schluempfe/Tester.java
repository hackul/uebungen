package de.jwille.kram.schluempfe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Tester {

	private Zelle zelle;

	private List<Schlumpf> schluempfe;

	private int anzahlSchluempfe;

	private Schlumpf waehleSchlumpf() {
		int nr = holeZufallsZahl();
		return schluempfe.get(nr);
	}

	private int holeZufallsZahl() {
		return ThreadLocalRandom.current().nextInt(anzahlSchluempfe);

	}

	private void start() {
		zelle = new Zelle();
		schluempfe = new ArrayList<>(anzahlSchluempfe);
		int nrDesZaehlschlumpfs = holeZufallsZahl();
		for (int i = 0; i < anzahlSchluempfe; i++) {
			if (i == nrDesZaehlschlumpfs) {
				schluempfe.add(new Zaehlschlumpf());
			} else {
				schluempfe.add(new Schlumpf());
			}
		}

	}

	public Tester(int anzahlSchluempfe) {
		this.anzahlSchluempfe = anzahlSchluempfe;

	}

	public long zaehleTageFuerAlleSchluempfe() {
		long anzahlTage = 0;
		Schlumpf schlumpf;
		start();
		do {
			anzahlTage++;
			schlumpf = waehleSchlumpf();
			schlumpf.betreteZelle(zelle);

		} while (!schlumpf.weissDassFertig());

		return anzahlTage;
	}

}
