package gui.comp;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import exc.IncompleteArtikelDataException;
import vo.Artikel;

/**
 * Diese Klasse ist ein Beispiel f�r eine selbstgebaute Komponente.
 * Sie besitzt verschiedene Textfelder, die einen neuen Artikel definieren k�nnen,
 * und hat au�erdem die M�glichkeit, diesen neuen Artikel direkt zu erzeugen und
 * an die GUI zur�ckzuliefern
 * 
 * @author Marcel
 *
 */
public class ArtikelAddPanel extends JPanel {
	
	private static final long serialVersionUID = 6711014827062243842L;
	
	private JTextField artikelName;		// Textfeld f�r Artikelname
	private JTextField artikelPrice;	// Textfeld f�r Preis
	private JTextField artikelQty;		// Textfeld f�r St�ckzahl
	private JButton button;				// Button zum Erzeugen des Objektes
	
	/**
	 * Konstruktor
	 */
	public ArtikelAddPanel() {
		super();
		
		artikelName = new JTextField();
		artikelPrice = new JTextField();
		artikelQty = new JTextField();
		button = new JButton("Hinzuf�gen");
		
		// Eigenschaften
		Dimension d = new Dimension(640, 75);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setMaximumSize(d);
		
		// Layout
		GridLayout layout = new GridLayout(2, 4);
		layout.setHgap(5);		// Lass' 5px Abstand zwischen den Komponenten
		this.setLayout(layout);	// 2 Zeilen, 4 Spalten
		
		// Rahmen
		Border b = BorderFactory.createTitledBorder("Neuen Artikel anlegen");
		this.setBorder(b);
		
		// Hinzuf�gen der Unterkomponenten (von oben links nach unten rechts)
		this.add(new JLabel("Bezeichnung"));
		this.add(new JLabel("Preis"));
		this.add(new JLabel("Stueckzahl"));
		this.add(new JLabel());
		this.add(artikelName);
		this.add(artikelPrice);
		this.add(artikelQty);
		this.add(button);
	}
	
	/**
	 * F�gt dem Button den gegebenen ActionListener hinzu
	 * @param a
	 */
	public void addActionListener(ActionListener a) {
		button.addActionListener(a);
	}
	
	/**
	 * Erzeugt den Artikel aus den Werten in den Textfeldern der Komponente und gibt ihn zur�ck
	 * @return
	 * @throws IncompleteArtikelDataException
	 */
	public Artikel makeArtikel() throws IncompleteArtikelDataException {
		try {
			// Baue den Artikel zusammen
			String name  = artikelName.getText();
			float price  = Float.parseFloat(artikelPrice.getText().replaceAll(",", "."));
			int quantity = Integer.parseInt(artikelQty.getText());
			
			// L�sche die Eintr�ge aus den Textfeldern
			artikelName.setText("");
			artikelPrice.setText("");
			artikelQty.setText("");
			
			// Erzeuge den Artikel und gebe ihn zur�ck
			return new Artikel(name, price, quantity);
			
		} catch (NumberFormatException e) {
			// Bei einem Parse-Fehler wirf eine besser verst�ndliche Exception weiter
			throw new IncompleteArtikelDataException();
		}
	}
}
