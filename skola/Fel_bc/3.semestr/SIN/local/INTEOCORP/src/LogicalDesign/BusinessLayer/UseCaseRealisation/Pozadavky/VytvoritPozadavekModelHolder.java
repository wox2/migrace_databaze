package BusinessLayer.UseCaseRealisation.Pozadavky;
import BusinessLayer.BusinessObjects.PozadavekBO;
import BusinessLayer.UseCaseRealisation.IModelHolderListener;

/**
 * @author martin
 * @version 1.0
 * @created 16-XII-2009 0:06:36
 */
public class VytvoritPozadavekModelHolder implements IVytvoritPozadavekModelHolder {

	public VytvoritPozadavekModelHolder(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

	public PozadavekBO vytvor(){
		return null;
	}

	/**
	 * 
	 * @param listener    listener
	 */
	public void addListener(IModelHolderListener listener){

	}

	public void refresh(){

	}

}