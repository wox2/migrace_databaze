/* Soubor Kap08\04\Continue.java
   pøíklad na pøíkaz Continue
   Pøi vypisování pole øetìzcù pøeskoèí všechny výpisy 
   slova "necenzurovaný"
*/

public class Continue {
	String[] st= {"Toto", "je", "necenzurovaný", "øetìzec"};
	public void cenzura()
	{
		for(int i = 0; i < st.length; i++)
		{		
			if(st[i].equals("necenzurovaný")) continue;
			System.out.print(st[i]+ " ");
		}
	}
	public static void main(String[] s)
	{	
		Continue c = new Continue();
		c.cenzura();
	}
}