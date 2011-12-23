/* Soubor Kap13\02a\Test.java
 * Ukázka binární nekompatibility mezi C++ a Javou na PC
 * 
 * Nejprve spuste pod Windows program Test.exe. To je pøeloenı program,
 * napsanı v C++, kterı vytvoøí binární soubor data.dta a zapíše do nìj èísla
 * 0, 1, ... , 9. 
 * (Zdrojovı text programu v C++ je v souboru Test.cpp. Znáte-li Javu, 
 * nebude pro vás problém mu porozumìt.)
 * Pak spuste tento program, kterı se ona èísla pokusí pøeèíst. 
 * Vypíše jiná èísla.
 *
 * Dùvodem je binární nekompatibilita - C++, C , Pascal a jiné programovací 
 * jazyky uívají "pøirozenı" formát na daném poèítaèi, zatímco Java 
 * má formát pevnì stanovenı, kterı je stejnı na všech poèítaèích.
 * NA PC SE PØIROZENİ FORMÁT NESHODUJE S FORMÁTEM POUÍVANİM JAVOU.
 */

import java.io.*;

class Test	// Pøeète 10 èísel typu int z binárního souboru a vypíše je
{		// Pøedpokládá existenci binárního souboru data.dta
	public static void main(String[] a) throws Exception
	{
		FileInputStream fis = new FileInputStream("data.dta");
		DataInputStream dis = new DataInputStream(fis);
		int x;
		for(int i = 0; i < 10; i++)
		{
			System.out.println(dis.readInt());
		}
	}
}