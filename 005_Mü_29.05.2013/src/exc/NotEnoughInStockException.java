package exc;

import vo.Artikel;

/**
 * Exception, die geworfen wird, wenn beim Kaufvorgang eines Artikels nicht ausreichend Einheitenei
 * vorhanden sind. Sie soll zeigen, dass Exceptions mehr sein k�nnen als String-Parameter
 * mit Aufrufe zum Superkonstruktor.
 * @author Marcel
 *
 */
public class NotEnoughInStockException extends Exception {
	
	private static final long serialVersionUID = -5528882314161074548L;
	private Artikel artikel;
	
	/**
	 * Konstruktor der sch�nen Version
	 * @param a	Artikel-Objekt, um das es geht
	 */
	public NotEnoughInStockException(Artikel a) {
		this.artikel = a;
	}

	/**
	 * �berschriebene Methode, die Informationen �ber die Exception gibt
	 * @return
	 */
	public String getMessage() {
		return "Es gibt nicht mehr so viele " + artikel.getName() + "!";
	}
}
