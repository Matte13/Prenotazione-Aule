package upo.prenotazioneaule.gui;

import java.awt.Color;
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

import upo.prenotazioneaule.Aula;
import upo.prenotazioneaule.Main;

/**
 * @author Matteo Schirinzi 20035542
 * @title ReportAulaView (Class)
 */

public class ReportAulaView extends JFrame implements PropertyChangeListener{

	//******************CAMPI**********************************************************
	private static final long serialVersionUID = 1L;
	private JPanel reportAula;
	private DatePicker data;
	private List listAule;
	private List listReport;
	private JButton btnHome;
	private JButton btnCreaReport;
	private JLabel lblReport;
	private JLabel lblTitolo;
	private JLabel labelData;
	private JLabel lblSelezionaAula;

	//******************COSTRUTTORI****************************************************
	public ReportAulaView() {
		
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
		this.reportAula = new JPanel();
		this.reportAula.setBackground(Color.RED);
		this.reportAula.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.reportAula.setLayout(null);

		setContentPane(this.reportAula);
		
		this.lblTitolo = new JLabel("REPORT AULA");
		this.lblTitolo.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
		this.lblTitolo.setForeground(Color.WHITE);
		this.lblTitolo.setBounds(255, 48, 155, 24);
		reportAula.add(this.lblTitolo);
		
		this.labelData = new JLabel("Data:");
		this.labelData.setForeground(Color.WHITE);
		this.labelData.setBounds(69, 111, 48, 20);
		this.reportAula.add(this.labelData);
		
		this.data = new DatePicker();
		this.data.getComponentDateTextField().setEditable(false);
		this.data.setLocation(127, 112);
		this.data.setSize(144, 20);
		this.reportAula.add(this.data);
		
		this.listAule = new java.awt.List();
		this.listAule.setBounds(64, 185, 227, 158);
		this.reportAula.add(this.listAule);
		
		this.btnCreaReport = new JButton("Crea Report");
		this.btnCreaReport.setBackground(Color.WHITE);
		this.btnCreaReport.setBounds(111, 372, 118, 23);
		this.reportAula.add(this.btnCreaReport);
		    
		this.btnHome = new JButton("Home");
		this.btnHome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.btnHome.setBackground(Color.WHITE);
		this.btnHome.setBounds(567, 418, 89, 23);
		this.reportAula.add(this.btnHome);

		setContentPane(this.reportAula);
		
		this.lblSelezionaAula = new JLabel("Seleziona un'aula:");
		this.lblSelezionaAula.setForeground(Color.WHITE);
		this.lblSelezionaAula.setBounds(111, 159, 134, 20);
		this.reportAula.add(this.lblSelezionaAula);
		
		this.lblReport = new JLabel("Report:");
		this.lblReport.setForeground(Color.WHITE);
		this.lblReport.setBounds(496, 125, 48, 20);
		this.reportAula.add(this.lblReport);
		
		this.listReport = new List();
		this.listReport.setBounds(403, 159, 227, 210);
		this.reportAula.add(this.listReport);
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

	public String getNomeAula() {
		if(this.listAule.getSelectedItem() != null && this.listAule.getSelectedItem() != " ")
			return this.listAule.getSelectedItem().split(" ")[0];
		else
			return null;
	}
		
	public void addListReport(ArrayList<String> array) {
		for(String s : array)
			this.listReport.add(s);
	}
	
	public void reset() {
		this.listReport.removeAll();
		this.listAule.deselect(this.listAule.getSelectedIndex());
		this.data.setText("");
	}

	public void infoBox(String infoMessage, String titleBar){
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		@SuppressWarnings("unchecked")
		ArrayList<Aula> aule = (ArrayList<Aula>) e.getNewValue();
		if(aule == null || aule.isEmpty())
			this.infoBox("Non ci sono Aule, prima inserisci un'aula", null);
		else {
			this.listAule.removeAll();
			for(Aula a : aule)
				this.listAule.add(a.getNome()+ " cap.: " + a.getCapienzaMax() + " dotaz.: " + a.getDotazioni());
		}
		
	}

}
