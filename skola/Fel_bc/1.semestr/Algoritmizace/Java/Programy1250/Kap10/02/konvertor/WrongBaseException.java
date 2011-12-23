package konvertor;

/*
 * Tøída pro pøenos informací o výjimce
 * pøi špatném základu soustavy
 */

public class WrongBaseException extends RuntimeException {
  public WrongBaseException() {}
  public WrongBaseException(String s) {super(s);}
}