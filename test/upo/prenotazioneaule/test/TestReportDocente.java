package upo.prenotazioneaule.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import upo.prenotazioneaule.Aula;
import upo.prenotazioneaule.AulaClassica;
import upo.prenotazioneaule.Docente;
import upo.prenotazioneaule.Prenotazione;
import upo.prenotazioneaule.Prenotazione.ReportDocente;

class TestReportDocente {
	
	private Docente docente;
	private LocalDate data = LocalDate.now().plusDays(2);
	private LocalTime oraInizio = LocalTime.of(9, 0);
	private LocalTime oraFine = LocalTime.of(11, 0);
	private ReportDocente report;
	private Prenotazione pren;
	private Aula a;

	@BeforeEach
	void setUp() throws Exception {
		this.docente = new Docente("Matteo", "Schirinzi", 13, 01, 2001, "SCHMTT01A13F205C");
		this.report = new ReportDocente(this.data, this.docente);
		this.a = new AulaClassica("1B", 25);
		this.pren = new Prenotazione(this.a, this.docente, this.data, this.oraInizio, this.oraFine, "Paradigmi");
	}

	@Test
	void testCostruttore() {
		assertNotNull(report);
	}
	
	@Test
	void testGetDataReport() {
		assertEquals(this.data, this.report.getDataReport());
	}
	
	@Test
	void testGetDocente() {
		assertEquals(this.docente, this.report.getDocente());
	}
	
	@Test
	void testEquals() {
		assertTrue(this.report.equals(this.report));
	}
	
	@Test 
	void testIsEmpty(){
		assertTrue(this.report.isEmpty());
	}
	
	@Test
	void testAddRecord() {
		this.report.addRecord(pren);
		for(Prenotazione p : this.report.getAllRecord())
			assertTrue(p.equals(pren));
	}

}
