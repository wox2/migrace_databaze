package cviceni;

/**
 * Obsahuje zamerne neefektivni implementaci faktorialu. 
 * 
 * Trida je thread safe.
 * 
 * @author mhlavaty
 */
public class Ukol1 {
	
	/**
	 * Prasacka implementace faktorialu
	 * 
	 * @param input cele cislo
	 * @return 		faktorial zadaneho vstupu.
	 * @throws IllegalArgumentException pokud je vstup mensi nez 0.
	 * 
	 * K zamysleni - jake dalsi vyjimky tento kod jeste muze vyhodit?
	 */
	public static int faktorial(int input) {
		if (input < 0) {
			throw new IllegalArgumentException("Jsou podporovany jen kladne vstupy");
		} else if (input == 0) {
			return 1;
		} else return input * faktorial(input - 1);
	}
		
}
