package upo.prenotazioneaule.test;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import upo.prenotazioneaule.Aula;
import upo.prenotazioneaule.AulaClassica;
import upo.prenotazioneaule.Docente;
import upo.prenotazioneaule.Prenotazione;

class TestPrenotazione {

	Prenotazione p;
	Aula a;
	Docente d;
	
	@BeforeEach
	void setUp() throws Exception {
		d = new Docente("Matteo", "Schirinzi", 13, 01, 2001, "SCHMTT01A13F205G");
		a = new AulaClassica("1B", 25);
		p = new Prenotazione(a, d, LocalDate.now().plusDays(2), LocalTime.now(), LocalTime.now().plusHours(2), "Paradigmi");
	}

	@Test
	void testCostruttore() {
		//test aula null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			p = new Prenotazione(null, d, LocalDate.now().plusDays(2), LocalTime.now(), LocalTime.now().plusHours(2), "Paradigmi");
		});
		
		//test docente null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			p = new Prenotazione(a, null, LocalDate.now().plusDays(2), LocalTime.now(), LocalTime.now().plusHours(2), "Paradigmi");
		});
		
		//test data null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			p = new Prenotazione(a, d, null, LocalTime.now(), LocalTime.now().plusHours(2), "Paradigmi");
		});
		
		//test ora inizio null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			p = new Prenotazione(a, d, LocalDate.now().plusDays(2), null, LocalTime.now().plusHours(2), "Paradigmi");
		});
		
		//test ora fine null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			p = new Prenotazione(a, d, LocalDate.now().plusDays(2), LocalTime.now(), null, "Paradigmi");
		});	
		
		//test nome lezione null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			p = new Prenotazione(a, d, LocalDate.now().plusDays(2), LocalTime.now(), LocalTime.now().plusHours(2), null);
		});	
		
		//test data odierna
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			p = new Prenotazione(a, d, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2), "Paradigmi");
		});
		
		//test data passta
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			p = new Prenotazione(a, d, LocalDate.now().minusDays(2), LocalTime.now(), LocalTime.now().plusHours(2), "Paradigmi");
		});
		
		//test ora inzio maggiore di fine
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			p = new Prenotazione(a, d, LocalDate.now(), LocalTime.now().plusHours(2), LocalTime.now(), "Paradigmi");
		});
		
		
	}

	@Test
	void testMetodiGet() {
		
		//test getAula()
		a = p.getAula();
		Assertions.assertNotNull(a); //aula non deve essere null
		Assertions.assertEquals(a, p.getAula()); //le due aule devono coincidere
		
		//test getDocente()
		d = p.getDocente();
		Assertions.assertNotNull(d); //docente non deve essere null
		Assertions.assertEquals(d, p.getDocente()); //i due docenti devono coincidere
		
		//test getData()
		LocalDate data = p.getData();
		Assertions.assertNotNull(data); //data non deve essere null
		Assertions.assertEquals(data, p.getData()); //le due date devono coincidere	
		
		//test getNome()
		String nome = p.getNomeLezione();
		Assertions.assertNotNull(nome); //nome non deve essere null
		Assertions.assertEquals(nome, p.getNomeLezione()); //i due nomi devono coincidere	
		
		//test getInizio()
		LocalTime inizio = p.getoraInizio();
		Assertions.assertNotNull(inizio); //data non deve essere null
		Assertions.assertEquals(inizio, p.getoraInizio()); //le due date devono coincidere	
		
		//test getFine()
		LocalTime fine = p.getoraFine();
		Assertions.assertNotNull(fine); //data non deve essere null
		Assertions.assertEquals(fine, p.getoraFine()); //le due date devono coincidere					
		
	}

	@Test
	void testEquals() {
		Assertions.assertNotNull(p); //p non deve essere null;
		Assertions.assertTrue(p.equals(p)); // p deve essere uguale a se stesso
		Prenotazione p2 = new Prenotazione(a, d, LocalDate.now().plusDays(1), LocalTime.now(), LocalTime.now().plusHours(4), "Basi di Dati");
		Assertions.assertFalse(p.equals(p2));// p deve essere diverso da p2
	}
}
