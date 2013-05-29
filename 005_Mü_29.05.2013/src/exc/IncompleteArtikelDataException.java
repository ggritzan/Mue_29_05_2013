package exc;

/**
 * Simple Exception, die geworfen wird, wenn die Hinzuf�gen-Komponente
 * nicht zul�ssig mit Werten einen Artikel erzeugen konnte
 * @author Marcel
 *
 */
public class IncompleteArtikelDataException extends Exception {
	
	private static final long serialVersionUID = -7247597799871112130L;

	/**
	 * Konstruktor
	 */
	public IncompleteArtikelDataException() {
		super("Es wurde beim Erzeugen eines Artikels nicht jedes Feld korrekt ausgef�llt.");
	}
}
