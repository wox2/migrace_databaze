package BusinessLayer.UseCaseRealisation.Pozadavky;
import BusinessLayer.BusinessObjects.PozadavekBO;
import BusinessLayer.UseCaseRealisation.IModelHolder;

/**
 * @author martin
 * @version 1.0
 * @created 16-XII-2009 0:06:19
 */
public interface IVytvoritPozadavekModelHolder extends IModelHolder {

	public PozadavekBO vytvor();

}