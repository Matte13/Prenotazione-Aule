package upo.prenotazioneaule.test;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import upo.prenotazioneaule.Aula.Dotazione;
import upo.prenotazioneaule.AulaClassica;
import upo.prenotazioneaule.AulaElettronica;
import upo.prenotazioneaule.AulaInformatica;

class TestAula {
	
	private AulaClassica aulaClassica;
	private AulaElettronica aulaElettronica;
	private AulaInformatica aulaInformatica;

	@BeforeEach
	void setUp() throws Exception {
		aulaClassica = new AulaClassica("9B", 30);
		aulaElettronica = new AulaElettronica("Lab1", 25);
		aulaInformatica = new AulaInformatica("Lab2", 40);
	}

	@Test
	void testCostruttoreClassica() {
		
		//test nome null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			aulaClassica = new AulaClassica(null, 30);
		});	
		
		//test capienza 0
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			aulaClassica = new AulaClassica("9B", 0);
		});	
		
		//test dotazione di default
		Assertions.assertNotNull(aulaClassica); //non deve essere null;
		
		Dotazione d = aulaClassica.getDotazioni().get(0);
		Assertions.assertNotNull(d); //nome non deve essere null
		Assertions.assertEquals(Dotazione.LAVAGNA, d); //le due dotazioni devono coincidere
	}
	
	@Test
	void testCostruttoreElettronica() {
		
		//test nome null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			aulaElettronica = new AulaElettronica(null, 30);
		});	
		
		//test capienza 0
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			aulaElettronica = new AulaElettronica("9B", 0);
		});	
		
		//test dotazione di default
		Assertions.assertNotNull(aulaElettronica); //non deve essere null;
		
		Dotazione d = aulaElettronica.getDotazioni().get(0);
		Assertions.assertNotNull(d); //nome non deve essere null
		Assertions.assertEquals(Dotazione.SALDATORE, d); //le due dotazioni devono coincidere
	}
	
	@Test
	void testCostruttoreInformatica() {
		
		//test nome null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			aulaInformatica = new AulaInformatica(null, 30);
		});	
		
		//test capienza 0
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			aulaInformatica = new AulaInformatica("9B", 0);
		});	
		
		//test dotazione di default
		Assertions.assertNotNull(aulaInformatica); //non deve essere null;
		
		Dotazione d = aulaInformatica.getDotazioni().get(0);
		Assertions.assertNotNull(d); //nome non deve essere null
		Assertions.assertEquals(Dotazione.PC, d); //le due dotazioni devono coincidere
	}
	
	@Test
	void testMetodiGet() {
		
		//viene utilizzata aulaClassica ma essendo comuni a tutte le classi i metodi get è indifferente
		
		Assertions.assertNotNull(aulaClassica); //non deve essere null;
		
		//test nome
		String nome = aulaClassica.getNome();
		Assertions.assertNotNull(nome); //nome non deve essere null
		Assertions.assertEquals(nome, aulaClassica.getNome()); //i due nomi devono coincidere
		
		//test capienzaMax
		int capienzaMax = aulaClassica.getCapienzaMax();
		Assertions.assertNotNull(capienzaMax); //nome non deve essere null
		Assertions.assertEquals(capienzaMax, aulaClassica.getCapienzaMax()); //le due capienze devono coincidere
		
		//test dotazioni
		ArrayList<Dotazione> d = new ArrayList<Dotazione>();
		
		Assertions.assertNotNull(d); //d non deve essere null;
		d = aulaClassica.getDotazioni();
		for(int i = 0; i < d.size() - 1; i++) {
			Assertions.assertEquals(d.get(i), aulaClassica.getDotazioni().get(i));
		}
			
			
		
	}

}
