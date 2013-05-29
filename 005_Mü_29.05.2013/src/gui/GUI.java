package gui;

import java.awt.BorderLayout;
import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import exc.IncompleteArtikelDataException;
import gui.comp.ArtikelAddPanel;
import gui.comp.ArtikelTableModel;
import vo.Artikel;

/**
 * GUI mit main()-Methode (Skelett)
 * @author Marcel
 *
 */
public class GUI extends JFrame {

	private static final long serialVersionUID = -293781756492289972L;

	// Grafische Komponenten mit Event Listenern (m�ssen als Attribute
	// gespeichert werden, damit sie in verschiedenen Methoden dieser
	// Klasse zugreifbar sind)
	private MenuItem menuDateiQuit;				// Beenden-Eintrag im Datei-Men�
	private CheckboxMenuItem menuAnsichtAll;	// "Alle Artikel" im Ansicht-Men�
	private CheckboxMenuItem menuAnsichtSome;	// "Ausverkaufte" im Ansicht-Men�
	private MenuItem menuArtikelBuy;			// Markierten Artikel kaufen
	private ArtikelAddPanel addComp;			// Eigene Komponente zum Hinzuf�gen von Artikeln

	// Objekte f�r die korrekte Darstellung der Tabellenansicht
	private JTable table;
	private ArtikelTableModel tmodel;			// TableModel f�r die Artikel-Tabelle

	// Liste von Artikeln
	private List<Artikel> artikelList;

	/**
	 * Konstruktor
	 */
	public GUI() {
		super("GUI2");

		// Artikelliste initialisieren
		artikelList = new ArrayList<Artikel>();

		// Komponenten und Layout initialisieren
		this.initComponents();

		// Listener initialisieren
		this.initListeners();

		// Sichtbar schalten
		this.setVisible(true);
	}

	/**
	 * Einrichten und Hinzuf�gen der grafischen Komponenten zum Fenster
	 */
	private void initComponents() {
		// Frame-Eigenschaften setzen
		this.setSize(400, 500);
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);

		// Men�leiste einrichten
		MenuBar mbar = new MenuBar();
		
		Menu menuDatei = new Menu("Datei");
		menuDateiQuit = new MenuItem("Beenden");
		menuDatei.add(menuDateiQuit);
		mbar.add(menuDatei);

		Menu menuAnsicht = new Menu("Ansicht");
		menuAnsichtAll = new CheckboxMenuItem("Alle Artikel");
		menuAnsichtAll.setState(true);
		menuAnsicht.add(menuAnsichtAll);
		menuAnsichtSome = new CheckboxMenuItem("Ausverkaufte Artikel");
		menuAnsichtSome.setState(false);
		menuAnsicht.add(menuAnsichtSome);
		mbar.add(menuAnsicht);
		
		Menu menuArtikel = new Menu("Artikel");
		menuArtikelBuy = new MenuItem("Markierten Artikel kaufen");
		menuArtikel.add(menuArtikelBuy);
		mbar.add(menuArtikel);

		this.setMenuBar(mbar);

		// Tabelle erzeugen und Eigenschaften setzen

        table = new JTable();
        tmodel = new ArtikelTableModel(artikelList);
        table.setModel(tmodel);



		this.add(table, BorderLayout.CENTER);
		
		// Initialisiere die ScrollPane, in der die Tabelle dargestellt wird
		JScrollPane scrollPane = new JScrollPane(table);
		// Rahmen f�r die ScrollPane anlegen
		Border scrollBorder = BorderFactory.createEtchedBorder();
		scrollPane.setBorder(scrollBorder);
		// Scrollpane zum Fenster hinzuf�gen
		this.add(scrollPane, BorderLayout.CENTER);

		// Befehlsleiste unten erzeugen
		addComp = new ArtikelAddPanel();
		this.add(addComp, BorderLayout.SOUTH);
	}

	/**
	 * Definieren der Event Handler f�r verschiedene Komponenten der GUI
	 */
	private void initListeners() {
		// Finale Selbstreferenz (damit GUI-Referenz "this" auch im ActionListener-Kontext verf�gbar ist)
		final GUI gui = this;
		
		addComp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
                // 1. Möglichkeit
                //GUI.this.setVisible(false);

                // 2. Möglichkeit
                //gui.setVisible(false);

                try {
                    Artikel a = addComp.makeArtikel();
                    artikelList.add(a);
                    tmodel.fireTableDataChanged();
                } catch (IncompleteArtikelDataException e) {

                    JOptionPane.showMessageDialog(gui, e.getMessage(), "Fehler beim einfuege eines Artikels", JOptionPane.ERROR_MESSAGE);
                }

            }
		});
	}

	/**
	 * main()-Methode.
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws UnsupportedLookAndFeelException
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		
		// Look and Feel der Anwendung setzen
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		// Neues GUI-Objekt erzeugen
		new GUI();
	}
}
