package upo.prenotazioneaule.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import upo.prenotazioneaule.Main;
import upo.prenotazioneaule.Aula.Dotazione;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.List;
import java.awt.Taskbar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Image;

/**
 * @author Matteo Schirinzi 20035542
 * @title InserimentoAulaView (Class)
 */

public class InserimentoAulaView extends JFrame {

	//******************CAMPI**********************************************************
	private static final long serialVersionUID = 1L;
	private JPanel InserimentoAulaPanel;
	private JTextField textFieldNome;
	private JTextField textFieldCapienza;
	private List listTipologie;
	private List listDotazioni;
	private JButton btnInserisci;
	private JButton btnHome;
	private JLabel lblTitolo;
	private JLabel labelDotazioni;
	private JLabel labelTipoAula;
	private JLabel labelCapienza;
	private JLabel labelNomeAula;

	/**
	 * Create the frame.
	 */
	//******************COSTRUTTORI****************************************************
	public InserimentoAulaView() {
		
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
		
		setTitle("InserimentoAuleView");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 491);
		//Center Windows
		Main.centreWindow(this);
		this.InserimentoAulaPanel = new JPanel();
		this.InserimentoAulaPanel.setBackground(Color.RED);
		this.InserimentoAulaPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.InserimentoAulaPanel);
		this.InserimentoAulaPanel.setLayout(null);
		
		this.labelNomeAula = new JLabel("Nome Aula:");
		this.labelNomeAula.setForeground(Color.WHITE);
		this.labelNomeAula.setBounds(90, 148, 103, 22);
		this.InserimentoAulaPanel.add(this.labelNomeAula);
		
		this.labelCapienza = new JLabel("Capienza: ");
		this.labelCapienza.setForeground(Color.WHITE);
		this.labelCapienza.setBounds(90, 209, 103, 22);
		this.InserimentoAulaPanel.add(this.labelCapienza);
		
		this.textFieldNome = new JTextField();
		this.textFieldNome.setBounds(174, 149, 118, 20);
		this.InserimentoAulaPanel.add(this.textFieldNome);
		this.textFieldNome.setColumns(10);
		
		this.textFieldCapienza = new JTextField();
		this.textFieldCapienza.setColumns(10);
		this.textFieldCapienza.setBounds(174, 210, 118, 20);
		this.InserimentoAulaPanel.add(this.textFieldCapienza);
		
		this.labelTipoAula = new JLabel("Tipologia:");
		this.labelTipoAula.setForeground(Color.WHITE);
		this.labelTipoAula.setBounds(90, 274, 73, 22);
		this.InserimentoAulaPanel.add(this.labelTipoAula);
		
		this.listTipologie = new List(3, false);
		this.listTipologie.setBounds(174, 279, 118, 60);
		this.listTipologie.add("Aula Classica");
		this.listTipologie.add("Lab Informatico");
		this.listTipologie.add("Lab Elettronico");
		this.InserimentoAulaPanel.add(this.listTipologie);
		
		this.labelDotazioni = new JLabel("Dotazioni:");
		this.labelDotazioni.setForeground(Color.WHITE);
		this.labelDotazioni.setBounds(348, 148, 73, 22);
		this.InserimentoAulaPanel.add(this.labelDotazioni);
		
		this.listDotazioni = new List();
		this.listDotazioni.setBounds(426, 148, 128, 126);
		for(Dotazione d : java.util.Arrays.asList(Dotazione.values()))
			this.listDotazioni.add(d+"");
		this.listDotazioni.setMultipleMode(true);
		this.InserimentoAulaPanel.add(this.listDotazioni);
		
		this.btnInserisci = new JButton("Inserisci");
		this.btnInserisci.setBackground(Color.GREEN);
		this.btnInserisci.setBounds(458, 310, 96, 29);
		this.InserimentoAulaPanel.add(this.btnInserisci);
		
		this.lblTitolo = new JLabel("INSERIMENTO NUOVA AULA");
		this.lblTitolo.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
		this.lblTitolo.setForeground(Color.WHITE);
		this.lblTitolo.setBounds(212, 63, 241, 22);
		this.InserimentoAulaPanel.add(this.lblTitolo);
		
		this.btnHome = new JButton("Home");
		this.btnHome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.btnHome.setBackground(Color.WHITE);
		this.btnHome.setBounds(567, 418, 89, 23);
		this.InserimentoAulaPanel.add(this.btnHome);
	}
	
	//******************METODI****************************************************
	//ActionListener
	public void addListener(ActionListener controller) {
		this.btnInserisci.addActionListener(controller);
		this.btnHome.addActionListener(controller);
	}
	
	//Message creator
	public void infoBox(String infoMessage, String titleBar){
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	
	//Getter and Setter
	public String getNome() {
		return this.textFieldNome.getText();
	}
	
	public String getCapienza() {
		return this.textFieldCapienza.getText();
	}
	
	public String getTipologia() {
		return this.listTipologie.getSelectedItem();
	}

	public JTextField getTextFieldNome() {
		return textFieldNome;
	}

	public JTextField getTextFieldCapienza() {
		return textFieldCapienza;
	}
	
	public String[] getListDotazioni(){
		return listDotazioni.getSelectedItems();
	}

	public void reset() {
		this.textFieldCapienza.setText("");
		this.textFieldNome.setText("");
		this.listTipologie.deselect(this.listTipologie.getSelectedIndex());
		for(int i : this.listDotazioni.getSelectedIndexes())
			this.listDotazioni.deselect(i);	
	}
}
