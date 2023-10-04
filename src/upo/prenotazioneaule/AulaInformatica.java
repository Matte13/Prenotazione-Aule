package upo.prenotazioneaule;

public class AulaInformatica extends Aula{

	/**
	 * @author Matteo Schirinzi 20035542
	 * @title AulaInformatica (Class)
	 */
	
	//******************CAMPI**********************************************************
	private static final long serialVersionUID = 7143695943176969333L;

	//******************COSTRUTTORI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title AulaInformatica - Costruttore 
	 * @param <em>nome</em> : Nome dell'aula (String)
	 * @param <em>capienza</em> : Capienza in posti dell'aula (int)
	 * @throws IllegalArgumentException
	 * AulaInformatica() - Costruttore - Aggiunge come dotazione predefinita il pc
	 */
	public AulaInformatica(String nome, int capienzaMax) throws IllegalArgumentException {
		super(nome, capienzaMax);
		super.addDotazione(Dotazione.PC);
	}

}
