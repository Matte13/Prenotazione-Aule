package upo.prenotazioneaule.gui;

import java.awt.Font;
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.github.lgooddatepicker.components.DatePicker;
import upo.prenotazioneaule.Main;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

/**
 * @author Matteo Schirinzi 20035542
 * @title InserimentoDocenteView (Class)
 */

public class InserimentoDocenteView extends JFrame {

	//******************CAMPI**********************************************************
	private static final long serialVersionUID = 1L;
	private JPanel InserimentoDocentePanel;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JTextField textFieldCF;
	private JButton btnInserisci;
	private JButton btnHome;
	private DatePicker dataNascita;
	private JLabel labelCF;
	private JLabel labelDataNascita;
	private JLabel labelCognome;
	private JLabel labelNome;
	private JLabel lblTitolo;

	/**
	 * Create the frame.
	 */
	//******************COSTRUTTORI****************************************************
	public InserimentoDocenteView() {
		
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
		
		setTitle("InserimentoDocenteView");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 491);
		//Center window
		Main.centreWindow(this);
		this.InserimentoDocentePanel = new JPanel();
		this.InserimentoDocentePanel.setBackground(Color.RED);
		this.InserimentoDocentePanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.InserimentoDocentePanel);
		this.InserimentoDocentePanel.setLayout(null);
		
		this.lblTitolo = new JLabel("INSERIMENTO NUOVO DOCENTE");
		this.lblTitolo.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
		this.lblTitolo.setForeground(Color.WHITE);
		this.lblTitolo.setBounds(190, 63, 285, 22);
		this.InserimentoDocentePanel.add(this.lblTitolo);
		
		this.labelNome = new JLabel("Nome:");
		this.labelNome.setForeground(Color.WHITE);
		this.labelNome.setBounds(199, 143, 69, 14);
		this.InserimentoDocentePanel.add(this.labelNome);
		
		this.labelCognome = new JLabel("Cognome:");
		this.labelCognome.setForeground(Color.WHITE);
		this.labelCognome.setBounds(199, 189, 69, 14);
		this.InserimentoDocentePanel.add(this.labelCognome);
		
		this.labelDataNascita = new JLabel("Data di nascita:");
		this.labelDataNascita.setForeground(Color.WHITE);
		this.labelDataNascita.setBounds(199, 233, 96, 14);
		this.InserimentoDocentePanel.add(this.labelDataNascita);
		
		this.labelCF = new JLabel("Codice Fiscale:");
		this.labelCF.setForeground(Color.WHITE);
		this.labelCF.setBounds(199, 281, 96, 14);
		this.InserimentoDocentePanel.add(this.labelCF);
		
		this.textFieldNome = new JTextField();
		this.textFieldNome.setBounds(295, 140, 150, 20);
		this.InserimentoDocentePanel.add(this.textFieldNome);
		this.textFieldNome.setColumns(10);
		
		this.textFieldCognome = new JTextField();
		this.textFieldCognome.setColumns(10);
		this.textFieldCognome.setBounds(295, 186, 150, 20);
		this.InserimentoDocentePanel.add(this.textFieldCognome);
		
		this.textFieldCF = new JTextField();
		this.textFieldCF.setColumns(10);
		this.textFieldCF.setBounds(295, 278, 150, 20);
		this.InserimentoDocentePanel.add(this.textFieldCF);
		
		this.dataNascita = new DatePicker();
		this.dataNascita.getComponentDateTextField().setEditable(false);
		this.dataNascita.setLocation(295, 231);
		this.dataNascita.setSize(150, 20);
	    getContentPane().add(this.dataNascita);
	    
	    this.btnInserisci = new JButton("Inserisci");
	    this.btnInserisci.setBackground(Color.GREEN);
	    this.btnInserisci.setBounds(285, 355, 96, 29);
	    this.InserimentoDocentePanel.add(this.btnInserisci);
	    
	    this.btnHome = new JButton("Home");
	    this.btnHome.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    this.btnHome.setBackground(Color.WHITE);
	    this.btnHome.setBounds(567, 418, 89, 23);
	    this.InserimentoDocentePanel.add(this.btnHome);
		
	}
	
	//******************METODI****************************************************
	public String getNome() {
		return textFieldNome.getText();
	}
	
	public String getCognome() {
		return textFieldCognome.getText();
	}

	public String getCF() {
		return textFieldCF.getText();
	}
	
	public LocalDate getDataNascita() {
		return dataNascita.getDate();
	}

	public void infoBox(String infoMessage, String titleBar){
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void reset() {
		this.textFieldNome.setText("");
		this.textFieldCognome.setText("");
		this.textFieldCF.setText("");
		this.dataNascita.setText("");
	}

	//ActionListener
	public void addListener(ActionListener controller) {
		this.btnInserisci.addActionListener(controller);
		this.btnHome.addActionListener(controller);
	}
}
