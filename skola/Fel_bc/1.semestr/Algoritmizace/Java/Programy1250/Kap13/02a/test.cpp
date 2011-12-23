/* Zdrojový text programu v C++, který vytvoøí binární soubor
 * a zapíše do nìj celá èísla (typ int) od 0 do 9.
 * Slouží jaké pøíklad na binárnín nekompatibilitu datových souborù vytvoøených
 * v Javì a v jiných programovacích jazycích na PC.
 */

// Pøecti si informace o standardních prostøedcích pro vstup a výstup

#include <stdio.h>
void main()								
{
 FILE * f = fopen("data.dta", "wb"); // Otevøi binárnì pro zápis
 for(int i = 0; i < 10; i++)			
	fwrite(&i, sizeof(int),1, f);// Výpis èísla 0 až 10 typu int
 fclose(f);                          // Uzavøi soubor
}