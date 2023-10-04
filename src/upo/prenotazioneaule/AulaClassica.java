package upo.prenotazioneaule;

/**
 * @author Matteo Schirinzi 20035542
 * @title AulaClassica (Class)
 */

public class AulaClassica extends Aula {
	
	//******************CAMPI**********************************************************
	private static final long serialVersionUID = -5309203756695176135L;

	//******************COSTRUTTORI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title AulaClassica - Costruttore 
	 * @param <em>nome</em> : Nome dell'aula (String)
	 * @param <em>capienza</em> : Capienza in posti dell'aula (int)
	 * @throws IllegalArgumentException
	 * AulaClassica() - Costruttore - Aggiunge come dotazione predefinita la lavagna
	 */
	public AulaClassica(String nome, int capienzaMax) throws IllegalArgumentException {
		super(nome, capienzaMax);
		super.addDotazione(Dotazione.LAVAGNA);
	}

}
