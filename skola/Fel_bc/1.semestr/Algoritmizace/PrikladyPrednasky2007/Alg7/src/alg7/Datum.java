package alg7;

import java.util.Currency;
import java.util.Date;
import java.util.Calendar;

public class Datum {
  public static void main(String[] args) {
    Calendar c = Calendar.getInstance();
    long t = System.currentTimeMillis();
    Date d = new Date(t);
    c.setTime(d);
    System.out.println("èas:"+t+" "+d+", "+c);
  }
}