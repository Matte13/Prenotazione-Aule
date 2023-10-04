package upo.prenotazioneaule.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Image;
import java.awt.Taskbar;
import upo.prenotazioneaule.Main;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;

/**
 * @author Matteo Schirinzi 20035542
 * @title MainView (Class)
 */

public class MainView extends JFrame {
	
	//******************CAMPI**********************************************************
	private static final long serialVersionUID = 7913662204278092498L;
	private JPanel MainPanel;
	private JButton btnInserimentoAula;
	private JButton btnRicercaAula;
	private JButton btnPrenotazione;
	private JButton btnAggiungiDoc;
	private JButton btnReportAula;
	private JButton btnReportPerDocente;
	private JButton btnSalvaDati;
	private JButton btnCaricaDati;
	private JLabel labelImg;

	//******************COSTRUTTORI****************************************************
	public MainView() {
		
		//Caricamento dell'immagine
		final Image image = new ImageIcon(this.getClass().getResource("/img/icon.jpg")).getImage();

        //Nuovo JDK 9
        final Taskbar taskbar = Taskbar.getTaskbar();

        try {
            //icon per mac os e per altri sistemi che supportano questo metodo
            taskbar.setIconImage(image);
        } catch (final UnsupportedOperationException e) {
            System.out.println("The os does not support: 'taskbar.setIconImage'");
        } catch (final SecurityException e) {
            System.out.println("There was a security exception for: 'taskbar.setIconImage'");
        }

        //icon per windows e per altri sistemi che supportano questo metodo
        setIconImage(image);
		
		setTitle("UPO - Prenotazione Aule");  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 491);
		//Center window
		Main.centreWindow(this);
		this.MainPanel = new JPanel();
		this.MainPanel.setBackground(Color.RED);
		this.MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.MainPanel);
		this.MainPanel.setLayout(null);
		
		this.btnInserimentoAula = new JButton("Inserimento Aula");
		this.btnInserimentoAula.setIcon(null);
		this.btnInserimentoAula.setSelectedIcon(null);
		this.btnInserimentoAula.setBackground(Color.WHITE);
		this.btnInserimentoAula.setBounds(117, 244, 187, 30);
		this.MainPanel.add(this.btnInserimentoAula);
		
		this.btnRicercaAula = new JButton("Ricerca Aule Libere");
		this.btnRicercaAula.setBackground(Color.WHITE);
		this.btnRicercaAula.setBounds(117, 277, 187, 30);
		this.MainPanel.add(this.btnRicercaAula);
		
		this.btnPrenotazione = new JButton("Prenota Aula");
		this.btnPrenotazione.setBackground(Color.WHITE);
		this.btnPrenotazione.setBounds(117, 311, 187, 30);
		this.MainPanel.add(this.btnPrenotazione);
		
		this.btnAggiungiDoc = new JButton("Aggiungi Docente");
		this.btnAggiungiDoc.setBackground(Color.WHITE);
		this.btnAggiungiDoc.setBounds(117, 345, 187, 30);
		this.MainPanel.add(this.btnAggiungiDoc);
		
		this.btnReportAula = new JButton("Report Aula");
		this.btnReportAula.setBackground(Color.WHITE);
		this.btnReportAula.setBounds(348, 244, 187, 30);
		this.MainPanel.add(this.btnReportAula);
		
		this.btnReportPerDocente = new JButton("Report Docente");
		this.btnReportPerDocente.setBackground(Color.WHITE);
		this.btnReportPerDocente.setBounds(348, 278, 187, 30);
		this.MainPanel.add(this.btnReportPerDocente);
		
		
		this.labelImg = new JLabel(new ImageIcon(getClass().getResource("/img/upo-logo.png")));
		this.labelImg.setLocation(92, 31);
		this.labelImg.setSize(481, 174);
		this.MainPanel.add(this.labelImg);
		
		this.btnSalvaDati = new JButton("Salva Dati");
		this.btnSalvaDati.setBackground(Color.WHITE);
		this.btnSalvaDati.setBounds(348, 312, 187, 30);
		this.MainPanel.add(this.btnSalvaDati);
		
		this.btnCaricaDati = new JButton("Carica Dati");
		this.btnCaricaDati.setBackground(Color.WHITE);
		this.btnCaricaDati.setBounds(348, 346, 187, 30);
		this.MainPanel.add(this.btnCaricaDati);
	}

	//******************METODI****************************************************
	
	public void infoBox(String infoMessage, String titleBar){
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	
	//ActionListener
	public void addListener(ActionListener controller) {
		this.btnInserimentoAula.addActionListener(controller);
		this.btnRicercaAula.addActionListener(controller);
		this.btnPrenotazione.addActionListener(controller);
		this.btnAggiungiDoc.addActionListener(controller);
		this.btnReportAula.addActionListener(controller);
		this.btnReportPerDocente.addActionListener(controller);
		this.btnReportAula.addActionListener(controller);
		this.btnSalvaDati.addActionListener(controller);
		this.btnCaricaDati.addActionListener(controller);
	}
}
