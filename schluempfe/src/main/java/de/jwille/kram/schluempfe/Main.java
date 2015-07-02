package de.jwille.kram.schluempfe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	private static long getMinTage(List<Long> dauerTage) {
		long minTage = Long.MAX_VALUE;
		for (Long anzTage : dauerTage) {
			if (anzTage < minTage) {
				minTage = anzTage;
			}
		}
		return minTage;
	}

	private static long getMaxTage(List<Long> dauerTage) {
		long maxTage = 0L;
		for (Long anzTage : dauerTage) {
			if (anzTage > maxTage) {
				maxTage = anzTage;
			}
		}
		return maxTage;
	}

	public static void main(String[] args) {

		for (int anzahlLaeufe = 1; anzahlLaeufe <= Konstanten.ANZAHL_TESTLAEUFE; anzahlLaeufe += 10) {

			int anzahlSchluempfe = Konstanten.ANZAHL_SCHLUEMPFE;

			System.out.println(String.format(
					"Es dauerte bei %d Durchläufen mit  %d Schlümpfen:",
					anzahlLaeufe, anzahlSchluempfe));

			List<Tester> testerListe = new ArrayList<>(anzahlLaeufe);
			for (int i = 0; i < anzahlLaeufe; i++) {
				testerListe.add(new Tester(anzahlSchluempfe));
			}
			long start;
			List<Long> testLaeufe;
			long ende;

			// start = System.currentTimeMillis();
			// testLaeufe = testeSeq(testerListe);
			// ende = System.currentTimeMillis();

			// System.out
			// .println(String
			// .format("Der test sequentiell mit ext. Iterator: mindestens %d Tage und höchstens %d Tage. Test dauerte %d Millisekunden",
			// getMinTage(testLaeufe),
			// getMaxTage(testLaeufe), ende - start));
			//
			start = System.currentTimeMillis();
			testLaeufe = testeStream(testerListe);
			ende = System.currentTimeMillis();

			System.out
					.println(String
							.format("Der test sequentiell mit Stream: mindestens %d Tage und höchstens %d Tage. Test dauerte %d Millisekunden",
									getMinTage(testLaeufe),
									getMaxTage(testLaeufe), ende - start));

			start = System.currentTimeMillis();
			testLaeufe = testeParallelStream(testerListe);
			ende = System.currentTimeMillis();

			System.out
					.println(String
							.format("Der test Parallel mit Stream: mindestens %d Tage und höchstens %d Tage. Test dauerte %d Millisekunden",
									getMinTage(testLaeufe),
									getMaxTage(testLaeufe), ende - start));
		}
	}

	private static List<Long> testeSeq(List<Tester> testerListe) {
		List<Long> dauerTage = new ArrayList<>();

		for (Tester tester : testerListe) {
			dauerTage.add(tester.zaehleTageFuerAlleSchluempfe());
		}

		return dauerTage;
	}

	private static List<Long> testeParallelStream(List<Tester> testerListe) {

		List<Long> dauerTage = testerListe.parallelStream()
				.map(t -> t.zaehleTageFuerAlleSchluempfe())
				.collect(Collectors.toList());
		return dauerTage;
	}

	private static List<Long> testeStream(List<Tester> testerListe) {

		List<Long> dauerTage = testerListe.stream()
				.map(t -> t.zaehleTageFuerAlleSchluempfe())
				.collect(Collectors.toList());
		return dauerTage;
	}

}
