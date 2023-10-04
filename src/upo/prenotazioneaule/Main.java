package upo.prenotazioneaule;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;

import upo.prenotazioneaule.controller.InserimentoAulaViewController;
import upo.prenotazioneaule.controller.InserimentoDocenteViewController;
import upo.prenotazioneaule.controller.MainViewController;
import upo.prenotazioneaule.controller.PrenotazioneAulaViewController;
import upo.prenotazioneaule.controller.ReportAulaController;
import upo.prenotazioneaule.controller.ReportDocenteController;
import upo.prenotazioneaule.controller.RicercaAuleLibereViewController;
import upo.prenotazioneaule.gui.InserimentoAulaView;
import upo.prenotazioneaule.gui.InserimentoDocenteView;
import upo.prenotazioneaule.gui.MainView;
import upo.prenotazioneaule.gui.PrenotazioneAulaView;
import upo.prenotazioneaule.gui.ReportAulaView;
import upo.prenotazioneaule.gui.ReportDocenteView;
import upo.prenotazioneaule.gui.RicercaAuleLibereView;
import upo.prenotazioneaule.model.InserimentoAulaModel;
import upo.prenotazioneaule.model.InserimentoDocenteModel;
import upo.prenotazioneaule.model.PrenotazioneAulaModel;

public class Main {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Inizializzazione MainView
					MainView mainView = new MainView();
					mainView.setVisible(true);
					
					//Inizializzazione InserimentoAulaView
					InserimentoAulaView inserimentoAulaView = new InserimentoAulaView();
					InserimentoAulaModel inserimentoAulaModel = new InserimentoAulaModel();
					inserimentoAulaView.setVisible(false);
					new InserimentoAulaViewController(inserimentoAulaView, inserimentoAulaModel, mainView);
					
					//Inizializzazione InserimentoDocenteView
					InserimentoDocenteView inserimentoDocenteView = new InserimentoDocenteView();
					InserimentoDocenteModel inserimentoDocenteModel = new InserimentoDocenteModel();
					inserimentoDocenteView.setVisible(false);
					new InserimentoDocenteViewController(inserimentoDocenteView, inserimentoDocenteModel, mainView);
					
					//Inizializzazione PrenotazioneAulaView
					PrenotazioneAulaView prenotazioneAulaView = new PrenotazioneAulaView();
					PrenotazioneAulaModel prenotazioneAulaModel = new PrenotazioneAulaModel(inserimentoAulaModel, inserimentoDocenteModel);
					prenotazioneAulaView.setVisible(false);
					new PrenotazioneAulaViewController(mainView, prenotazioneAulaView, prenotazioneAulaModel, inserimentoDocenteModel, inserimentoAulaModel);
					
					
					//Inizializzazione RicercaAuleLibereView
					RicercaAuleLibereView ricercaAuleLibereView = new RicercaAuleLibereView();
					ricercaAuleLibereView.setVisible(false);
					new RicercaAuleLibereViewController(ricercaAuleLibereView, mainView, prenotazioneAulaModel);
					
					//Inizializzazione ReportDocenteView
					ReportDocenteView reportDocenteView = new ReportDocenteView();
					reportDocenteView.setVisible(false);
					new ReportDocenteController(mainView, reportDocenteView, inserimentoDocenteModel, prenotazioneAulaModel);
					
					//Inizializzazione ReportAulaView
					ReportAulaView reportAulaView = new ReportAulaView();
					reportAulaView.setVisible(false);
					new ReportAulaController(mainView, reportAulaView, inserimentoAulaModel, prenotazioneAulaModel);
					
					//Inizializzazione file data
					File filePrenotazioni = new File("prenotazioni.dat");
					File fileAule = new File("aule.dat");
					File fileDocenti = new File("docenti.dat");
					
					new MainViewController(mainView, inserimentoAulaView, inserimentoDocenteView, ricercaAuleLibereView, prenotazioneAulaView, reportDocenteView, reportAulaView, prenotazioneAulaModel, filePrenotazioni, fileAule, fileDocenti);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}

}
