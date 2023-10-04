package upo.prenotazioneaule.gui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import upo.prenotazioneaule.Main;
import upo.prenotazioneaule.Aula.Dotazione;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.List;
import java.awt.Taskbar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

/**
 * @author Matteo Schirinzi 20035542
 * @title RicercaAuleLibereView (Class)
 */

public class RicercaAuleLibereView extends JFrame {

	//******************CAMPI**********************************************************
	private static final long serialVersionUID = 9083549374648069436L;
	private JPanel RicercaAulaPanel;
	private JTextField textFieldCapienza;
	private JButton btnCerca;
	private JButton btnHome;
	private List listAuleLibere;
	private List listDotazioni;
	private DatePicker data;
	private TimePicker oraInizio;
	private TimePicker oraFine;
	private JLabel lblTitolo;
	private JLabel labelData;
	private JLabel labelOraInizio;
	private JLabel lblOraFine;
	private JLabel lblCapienzaMassima;
	private JLabel lblDotazione;
	private JLabel lblAuleLibere;

	//******************COSTRUTTORI****************************************************
	public RicercaAuleLibereView() {
		
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
		this.RicercaAulaPanel = new JPanel();
		this.RicercaAulaPanel.setBackground(Color.RED);
		this.RicercaAulaPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.RicercaAulaPanel);
		this.RicercaAulaPanel.setLayout(null);
		
		this.lblTitolo = new JLabel("RICERCA AULE LIBERE");
		this.lblTitolo.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
		this.lblTitolo.setForeground(Color.WHITE);
		this.lblTitolo.setBounds(230, 43, 205, 22);
		this.RicercaAulaPanel.add(this.lblTitolo);
		
		this.labelData = new JLabel("Data:");
		this.labelData.setForeground(Color.WHITE);
		this.labelData.setBounds(69, 111, 48, 20);
		this.RicercaAulaPanel.add(this.labelData);
		
		this.labelOraInizio = new JLabel("Ora inizio:");
		this.labelOraInizio.setForeground(Color.WHITE);
		this.labelOraInizio.setBounds(69, 159, 48, 20);
		this.RicercaAulaPanel.add(this.labelOraInizio);
		
		this.lblOraFine = new JLabel("Ora fine:");
		this.lblOraFine.setForeground(Color.WHITE);
		this.lblOraFine.setBounds(69, 201, 48, 20);
		this.RicercaAulaPanel.add(this.lblOraFine);
		
		this.lblCapienzaMassima = new JLabel("Capienza massima:");
		this.lblCapienzaMassima.setForeground(Color.WHITE);
		this.lblCapienzaMassima.setBounds(69, 245, 107, 20);
		this.RicercaAulaPanel.add(this.lblCapienzaMassima);
		
		this.lblDotazione = new JLabel("Dotazione:");
		this.lblDotazione.setForeground(Color.WHITE);
		this.lblDotazione.setBounds(69, 289, 71, 20);
		this.RicercaAulaPanel.add(this.lblDotazione);
		
		this.data = new DatePicker();
		this.data.getComponentDateTextField().setEditable(false);
		this.data.setLocation(127, 112);
		this.data.setSize(144, 20);
	    getContentPane().add(this.data);
	    
	    //Time Setting
	    TimePickerSettings timeSettings = new TimePickerSettings();
	    timeSettings.generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.ThirtyMinutes, LocalTime.of(9, 0),LocalTime.of(18, 0));
	    
	    this.oraInizio = new TimePicker(timeSettings);
	    this.oraInizio.getComponentTimeTextField().setEditable(false);
	    this.oraInizio.setLocation(127, 160);
	    this.oraInizio.setSize(144, 20);
	    getContentPane().add(this.oraInizio);
	    
	    this.oraFine = new TimePicker(timeSettings);
	    this.oraFine.getComponentTimeTextField().setEditable(false);
	    this.oraFine.setLocation(127, 202);
	    this.oraFine.setSize(144, 20);
	    getContentPane().add(this.oraFine);
	    
	    this.textFieldCapienza = new JTextField();
	    this.textFieldCapienza.setBounds(200, 245, 71, 20);
	    this.RicercaAulaPanel.add(this.textFieldCapienza);
	    this.textFieldCapienza.setColumns(10);
	    
	    this.listDotazioni = new List();
	    this.listDotazioni.setBounds(139, 291, 132, 77);
	    for(Dotazione d : java.util.Arrays.asList(Dotazione.values()))
	    	this.listDotazioni.add(d+"");
	    this.RicercaAulaPanel.add(this.listDotazioni);
	    
	    this.lblAuleLibere = new JLabel("Aule Libere");
	    this.lblAuleLibere.setForeground(Color.WHITE);
	    this.lblAuleLibere.setBounds(466, 83, 71, 14);
	    this.RicercaAulaPanel.add(this.lblAuleLibere);
	    
	    this.listAuleLibere = new List();
	    this.listAuleLibere.setBounds(370, 113, 254, 259);
	    this.RicercaAulaPanel.add(this.listAuleLibere);
	    
	    this.btnCerca = new JButton("Cerca");
	    this.btnCerca.setBackground(Color.WHITE);
	    this.btnCerca.setBounds(118, 394, 89, 23);
	    this.RicercaAulaPanel.add(btnCerca);
	    
	    this.btnHome = new JButton("Home");
	    this.btnHome.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    this.btnHome.setBackground(Color.WHITE);
	    this.btnHome.setBounds(567, 418, 89, 23);
	    this.RicercaAulaPanel.add(this.btnHome);
	    
	}
	
	//******************METODI****************************************************
	//Getters
	public List getListAuleLibere() {
		return listAuleLibere;
	}

	public List getListDotazione() {
		return listDotazioni;
	}
	
	public LocalDate getData() {
		return this.data.getDate();
	}
	
	public LocalTime getOraInizio() {
		return this.oraInizio.getTime();
	}
	
	public LocalTime getOraFine() {
		return this.oraFine.getTime();
	}
	
	public String getCapienza() {
		return this.textFieldCapienza.getText();
	}

	public String getDotazione() {
		return listDotazioni.getSelectedItem();
	}
	
	public void clearField() {
		this.listDotazioni.deselect(this.listDotazioni.getSelectedIndex());
		this.textFieldCapienza.setText("");
		this.data.setText("");
		this.oraInizio.setText("");
		this.oraFine.setText("");
	}

	public static void infoBox(String infoMessage, String titleBar){
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	
	//ActionListener
	public void addListener(ActionListener controller) {
		this.btnCerca.addActionListener(controller);
		this.btnHome.addActionListener(controller);
	}
}
