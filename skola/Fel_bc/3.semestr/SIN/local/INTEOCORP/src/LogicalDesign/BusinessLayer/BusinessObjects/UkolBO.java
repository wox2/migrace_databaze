package BusinessLayer.BusinessObjects;

/**
 * @author Martin Svoboda
 * @version 1.0
 * @created 16-XII-2009 0:06:27
 */
public class UkolBO {

	private int casovaNarocnost;
	private java.util.Date datumUkonceni;
	private java.util.Date datumVytvoreni;
	private String nazev;
	private String popis;
	public TypUkoluBO m_TypUkoluBO;
	public StavUkoluBO m_StavUkoluBO;

	public UkolBO(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

}