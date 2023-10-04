package upo.prenotazioneaule;

/**
 * @author Matteo Schirinzi 20035542
 * @title AulaElettronica (Class)
 */


public class AulaElettronica extends Aula{

	//******************CAMPI**********************************************************
	private static final long serialVersionUID = 8439763305320977283L;

	//******************COSTRUTTORI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title AulaElettronica - Costruttore 
	 * @param <em>nome</em> : Nome dell'aula (String)
	 * @param <em>capienza</em> : Capienza in posti dell'aula (int)
	 * @throws IllegalArgumentException
	 * AulaElettronica() - Costruttore - Aggiunge come dotazione predefinita il saldatore
	 */
	public AulaElettronica(String nome, int capienzaMax) throws IllegalArgumentException {
		super(nome, capienzaMax);
		super.addDotazione(Dotazione.SALDATORE);
	}

}
