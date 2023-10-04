package upo.prenotazioneaule.test;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import upo.prenotazioneaule.Docente;

class TestDocente {

	private Docente d;

	@BeforeEach
	void setUp() throws Exception {
		d = new Docente("Matteo", "Schirinzi", 13, 01, 2001, "SCHMTT01A13F205G");
	}

	
	@Test
	void testCostruttore() {
		//test nome null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    d = new Docente(null, "Schirinzi", 13, 01, 2001, "SCHMTT01A13F205G");
		});
		
		//test cognome null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    d = new Docente("Matteo", null, 13, 01, 2001, "SCHMTT01A13F205G");
		});
		
		//test data nascita null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    d = new Docente("Matteo", "Schirinzi", null, "SCHMTT01A13F205G");
		});
		
		//test data nascita futura
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    d = new Docente("Matteo", "Schirinzi", 13, 01, 2023, "SCHMTT01A13F205G");
		});
		
		//test cf nullo
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    d = new Docente("Matteo", "Schirinzi", 13, 01, 2001, null);
		});
		
		//test cf non valido
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    d = new Docente("Matteo", "Schirinzi", 13, 01, 2001, "SCHMTT01A13");
		});
	}
	
	
	@Test
	void testMetodiGet() {
		
		Assertions.assertNotNull(d); //d non deve essere null;
		
		//test nome
		String nome = d.getNome();
		Assertions.assertNotNull(nome); //nome non deve essere null
		Assertions.assertEquals(nome, d.getNome()); //i due nomi devono coincidere
		
		//test cognome
		String cognome = d.getCognome();
		Assertions.assertNotNull(cognome); //cognome non deve essere null
		Assertions.assertEquals(cognome, d.getCognome()); //i due cognomi devono coincidere
		
		//test cf
		String cf = d.getCf();
		Assertions.assertNotNull(cf); //cf non deve essere null
		Assertions.assertEquals(cf, d.getCf()); //i due cf devono coincidere
		
		//test dataNascita
		LocalDate data = d.getDataNascita();
		Assertions.assertNotNull(data); //data non deve essere null
		Assertions.assertEquals(data, d.getDataNascita()); //le due date devono coincidere
		
		//test matricola
		int matricola = d.getMatricola();
		Assertions.assertNotNull(matricola); //matricola non deve essere null
		Assertions.assertEquals(matricola, d.getMatricola()); //le due matricole devono coincidere
	}
	
	@Test
	void testEquals() {
		Assertions.assertNotNull(d); //d non deve essere null;
		Assertions.assertTrue(d.equals(d)); // d deve essere uguale a se stesso
		Docente d2 = new Docente("Mario", "Rossi", 13, 01, 2001, "SCHMTT01A13F205G");
		Assertions.assertFalse(d.equals(d2));// d deve essere diverso da d2
	}
}
