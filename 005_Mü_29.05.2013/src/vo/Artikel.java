package vo;

import exc.NotEnoughInStockException;

/**
 * Simple Datenstruktur f�r Artikel-Objekte
 * 
 * @author Marcel
 *
 */
public class Artikel {

	private String name;	// Bezeichnung
	private float price;	// Preis
	private int quantity;	// St�ckzahl
	
	/**
	 * Konstruktor
	 * @param name
	 * @param price
	 * @param quantity
	 */
	public Artikel(String name, float price, int quantity) {
		this.setName(name);
		this.setPrice(price);
		this.setQuantity(quantity);
	}
	
	/**
	 * Reduziert die St�ckzahl dieses Artikels um die gegebene Menge.
	 * Wirft eine Exception, wenn nicht mehr ausreichend vorhanden ist
	 * @param amount
	 * @throws NotEnoughInStockException
	 */
	public void removeQuantity(int amount) throws NotEnoughInStockException {
		if (this.getQuantity() < amount) throw new NotEnoughInStockException(this);
		
		this.setQuantity(this.getQuantity() - amount);
	}
	
	// Hier drunter nur noch Getter und Setter
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = Math.max(price, 0);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = Math.max(quantity, 0);
	}
	
}
