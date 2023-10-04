package upo.prenotazioneaule.gui;

import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.Taskbar;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;

import upo.prenotazioneaule.Docente;
import upo.prenotazioneaule.Main;
import java.awt.Color;

/**
 * @author Matteo Schirinzi 20035542
 * @title ReportDocenteView (Class)
 */

public class ReportDocenteView extends JFrame implements PropertyChangeListener{

	//******************CAMPI**********************************************************
	private static final long serialVersionUID = 1L;
	private JPanel reportDocente;
	private DatePicker data;
	private List listDocenti;
	private List listReport;
	private JButton btnHome;
	private JButton btnCreaReport;
	private JLabel lblReport;
	private JLabel lblTitolo;
	private JLabel labelData;
	private JLabel lblSelezionaUnDocente;

	//******************COSTRUTTORI****************************************************
	public ReportDocenteView() {
		
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
		this.reportDocente = new JPanel();
		this.reportDocente.setBackground(Color.RED);
		this.reportDocente.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.reportDocente.setLayout(null);
		
		this.lblTitolo = new JLabel("REPORT DOCENTE");
		this.lblTitolo.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
		this.lblTitolo.setForeground(Color.WHITE);
		this.lblTitolo.setBounds(255, 48, 155, 24);
		this.reportDocente.add(this.lblTitolo);
		
		this.labelData = new JLabel("Data:");
		this.labelData.setForeground(Color.WHITE);
		this.labelData.setBounds(69, 111, 48, 20);
		this.reportDocente.add(this.labelData);
		
		this.data = new DatePicker();
		this.data.getComponentDateTextField().setEditable(false);
		this.data.setLocation(127, 112);
		this.data.setSize(144, 20);
		this.reportDocente.add(this.data);
		
		this.listDocenti = new java.awt.List();
		this.listDocenti.setBounds(64, 185, 227, 158);
		this.reportDocente.add(this.listDocenti);
		
		this.btnCreaReport = new JButton("Crea Report");
		this.btnCreaReport.setBackground(Color.WHITE);
		this.btnCreaReport.setBounds(111, 372, 118, 23);
		this.reportDocente.add(this.btnCreaReport);
		    
		this.btnHome = new JButton("Home");
		this.btnHome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.btnHome.setBackground(Color.WHITE);
		this.btnHome.setBounds(567, 418, 89, 23);
		this.reportDocente.add(this.btnHome);

		setContentPane(this.reportDocente);
		
		this.lblSelezionaUnDocente = new JLabel("Seleziona un docente:");
		this.lblSelezionaUnDocente.setForeground(Color.WHITE);
		this.lblSelezionaUnDocente.setBounds(111, 159, 134, 20);
		this.reportDocente.add(this.lblSelezionaUnDocente);
		
		this.lblReport = new JLabel("Report:");
		this.lblReport.setForeground(Color.WHITE);
		this.lblReport.setBounds(496, 125, 48, 20);
		this.reportDocente.add(this.lblReport);
		
		this.listReport = new List();
		this.listReport.setBounds(403, 159, 227, 210);
		this.reportDocente.add(this.listReport);
	}
	
	//******************METODI****************************************************
	//ActionListener
	public void addListener(ActionListener controller) {
		this.btnCreaReport.addActionListener(controller);
		this.btnHome.addActionListener(controller);
	}

	//Getters
	public LocalDate getData() {
		return data.getDate();
	}

	public String getNomeDocente() {
		if(this.listDocenti.getSelectedItem() != null && this.listDocenti.getSelectedItem() != " ")
			return this.listDocenti.getSelectedItem().split(" ")[0];
		else
			return null;
	}
	
	public String getCognomeDocente() {
		if(this.listDocenti.getSelectedItem() != null && this.listDocenti.getSelectedItem() != " ")
			return this.listDocenti.getSelectedItem().split(" ")[1];
		else
			return null;
	}
	
	public void addListReport(ArrayList<String> array) {
		for(String s : array)
			this.listReport.add(s);
	}
	
	public void reset() {
		this.listReport.removeAll();
		this.listDocenti.deselect(this.listDocenti.getSelectedIndex());
		this.data.setText("");
	}

	public void infoBox(String infoMessage, String titleBar){
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		@SuppressWarnings("unchecked")
		ArrayList<Docente> docenti = (ArrayList<Docente>) e.getNewValue();
		if(docenti == null || docenti.isEmpty())
			this.infoBox("Non ci sono docenti, prima aggiungi un docente", null);
		else {
			this.listDocenti.removeAll();
			for(Docente d : docenti)
				this.listDocenti.add(d.getNome()+ " " + d.getCognome());
		}
		
	}
}
