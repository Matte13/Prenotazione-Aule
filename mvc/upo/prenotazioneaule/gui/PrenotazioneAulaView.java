package upo.prenotazioneaule.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.List;
import java.awt.Taskbar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import upo.prenotazioneaule.Docente;
import upo.prenotazioneaule.Main;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * @author Matteo Schirinzi 20035542
 * @title PrenotazioneAulaView (Class)
 */

public class PrenotazioneAulaView extends JFrame implements PropertyChangeListener {

	//******************CAMPI**********************************************************
	private static final long serialVersionUID = -1947186219915580910L;
	private JPanel prenotazioneAulaPanel;
	private DatePicker data;
	private TimePicker oraInizio;
	private TimePicker oraFine;
	private JTextField textFieldNomeLezione;
	private JButton btnHome;
	private JButton btnPrenota;
	private JButton btnCercaAula;
	private List listAuleLibere;
	private List listDocenti;
	private JLabel lblSelezionaAula;
	private JLabel lblDocenteLista;
	private JLabel lblNomeLezione;
	private JLabel lblTitolo;
	private JLabel lblData;
	private JLabel lblOraInizio;
	private JLabel lblOraFine;

	//******************COSTRUTTORI****************************************************
	public PrenotazioneAulaView() {
		
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 491);
		Main.centreWindow(this);
		this.prenotazioneAulaPanel = new JPanel();
		this.prenotazioneAulaPanel.setBackground(Color.RED);
		this.prenotazioneAulaPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.prenotazioneAulaPanel);
		this.prenotazioneAulaPanel.setLayout(null);
		
		this.lblTitolo = new JLabel("PRENOTAZIONE AULA");
		this.lblTitolo.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
		this.lblTitolo.setForeground(Color.WHITE);
		this.lblTitolo.setBounds(241, 11, 183, 24);
		this.prenotazioneAulaPanel.add(this.lblTitolo);
		
		this.lblSelezionaAula = new JLabel("Seleziona l'Aula: ");
		this.lblSelezionaAula.setEnabled(false);
		this.lblSelezionaAula.setForeground(Color.WHITE);
		this.lblSelezionaAula.setBounds(124, 219, 111, 14);
		this.prenotazioneAulaPanel.add(this.lblSelezionaAula);
		
		this.lblData = new JLabel("Data:");
		this.lblData.setForeground(Color.WHITE);
		this.lblData.setBounds(225, 62, 92, 14);
		this.prenotazioneAulaPanel.add(lblData);
		
		this.lblOraInizio = new JLabel("Ora Inizio:");
		this.lblOraInizio.setForeground(Color.WHITE);
		this.lblOraInizio.setBounds(225, 97, 92, 14);
		this.prenotazioneAulaPanel.add(lblOraInizio);
		
		this.lblOraFine = new JLabel("Ora Fine:");
		this.lblOraFine.setForeground(Color.WHITE);
		this.lblOraFine.setBounds(225, 135, 92, 14);
		this.prenotazioneAulaPanel.add(lblOraFine);
		
		this.data = new DatePicker();
		this.data.getComponentDateTextField().setEditable(false);
		this.data.setLocation(294, 57);
		this.data.setSize(150, 20);
	    getContentPane().add(this.data);
	    
	    //Time Setting
	    TimePickerSettings timeSettings = new TimePickerSettings();
	    timeSettings.generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.ThirtyMinutes, LocalTime.of(9, 0),LocalTime.of(18, 0));
	    
	    this.oraInizio = new TimePicker(timeSettings);
	    this.oraInizio.getComponentTimeTextField().setEditable(false);
	    this.oraInizio.setLocation(294, 95);
	    this.oraInizio.setSize(150, 20);
	    getContentPane().add(this.oraInizio);
	    
	    this.oraFine = new TimePicker(timeSettings);
	    this.oraFine.getComponentTimeTextField().setEditable(false);
	    this.oraFine.setBounds(294, 133, 150, 20);
	    this.prenotazioneAulaPanel.add(this.oraFine);
	    
	    this.btnCercaAula = new JButton("Cerca Aula");
	    this.btnCercaAula.setBackground(Color.WHITE);
	    this.btnCercaAula.setBounds(294, 171, 111, 23);
	    this.prenotazioneAulaPanel.add(this.btnCercaAula);
	    
	    this.lblDocenteLista = new JLabel("Seleziona il Docente: ");
	    this.lblDocenteLista.setEnabled(false);
	    this.lblDocenteLista.setForeground(Color.WHITE);
	    this.lblDocenteLista.setBounds(445, 219, 120, 14);
	    this.prenotazioneAulaPanel.add(this.lblDocenteLista);
	    
	    this.lblNomeLezione = new JLabel("Nome Lezione:");
	    this.lblNomeLezione.setEnabled(false);
	    this.lblNomeLezione.setForeground(Color.WHITE);
	    this.lblNomeLezione.setBounds(40, 389, 82, 14);
	    this.prenotazioneAulaPanel.add(this.lblNomeLezione);
	    
	    this.textFieldNomeLezione = new JTextField();
	    this.textFieldNomeLezione.setEnabled(false);
	    this.textFieldNomeLezione.setBounds(142, 386, 155, 20);
	    this.prenotazioneAulaPanel.add(this.textFieldNomeLezione);
	    this.textFieldNomeLezione.setColumns(10);
	    
	    this.btnPrenota = new JButton("Prenota");
	    this.btnPrenota.setEnabled(false);
	    this.btnPrenota.setBackground(Color.WHITE);
	    this.btnPrenota.setBounds(332, 385, 89, 23);
	    this.prenotazioneAulaPanel.add(this.btnPrenota);
	    
	    this.btnHome = new JButton("Home");
	    this.btnHome.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    this.btnHome.setBackground(Color.WHITE);
	    this.btnHome.setBounds(567, 418, 89, 23);
	    this.prenotazioneAulaPanel.add(this.btnHome);
		
	    this.listAuleLibere = new java.awt.List();
	    this.listAuleLibere.setEnabled(false);
	    this.listAuleLibere.setBounds(57, 239, 227, 105);
	    this.prenotazioneAulaPanel.add(this.listAuleLibere);
		
	    this.listDocenti = new java.awt.List();
	    this.listDocenti.setEnabled(false);
	    this.listDocenti.setBounds(391, 239, 227, 105);
	    this.prenotazioneAulaPanel.add(this.listDocenti);
	}
	
	//******************METODI****************************************************
	//Getters
	public LocalDate getData() {
		return this.data.getDate();
	}
	
	public List getListAuleLibere() {
		return listAuleLibere;
	}

	public LocalTime getOraInizio() {
		return this.oraInizio.getTime();
	}
	
	public LocalTime getOraFine() {
		return this.oraFine.getTime();
	}

	public String getNomeAula() {
		return this.listAuleLibere.getSelectedItem().split(" ")[0];
	}
	
	public String getNomeDocente() {
		return this.listDocenti.getSelectedItem().split(" ")[0];
	}
	
	public String getCognomeDocente() {
		return this.listDocenti.getSelectedItem().split(" ")[1];
	}
	
	public String getNomeLezione() {
		return this.textFieldNomeLezione.getText();
	}
	
	public void enabled() {
		this.lblSelezionaAula.setEnabled(true);
		this.listAuleLibere.setEnabled(true);
		this.lblDocenteLista.setEnabled(true);
		this.listDocenti.setEnabled(true);
		this.lblNomeLezione.setEnabled(true);
		this.textFieldNomeLezione.setEnabled(true);
		this.btnPrenota.setEnabled(true);
	}
	
	public void disable() {
		this.lblSelezionaAula.setEnabled(false);
		this.listAuleLibere.setEnabled(false);
		this.lblDocenteLista.setEnabled(false);
		this.listDocenti.setEnabled(false);
		this.lblNomeLezione.setEnabled(false);
		this.textFieldNomeLezione.setEnabled(false);
		this.btnPrenota.setEnabled(false);
	}

	public void reset() {
		this.data.setText("");
		this.oraInizio.setText("");
		this.oraFine.setText("");
		this.listAuleLibere.removeAll();
		this.textFieldNomeLezione.setText("");
		this.listDocenti.deselect(this.listDocenti.getSelectedIndex());
	}

	//ActionListener
	public void addListener(ActionListener controller) {
		this.btnCercaAula.addActionListener(controller);
		this.btnPrenota.addActionListener(controller);
		this.btnHome.addActionListener(controller);
	}
		
	public void infoBox(String infoMessage, String titleBar){
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		@SuppressWarnings("unchecked")
		ArrayList<Docente> docenti = (ArrayList<Docente>) e.getNewValue();
		this.listDocenti.removeAll();
		for(Docente d : docenti)
			this.listDocenti.add(d.getNome()+ " " + d.getCognome());
		
	}
}
