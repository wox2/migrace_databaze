/*
 *  test.c 
 *  Y36PJC - Test na pocitaci, varianta c. 5
 *  Autor: Ondrej Balaz <ondra@blami.net>
 *
 *  Na vstupu jsou cela nezaporna cisla
 *  - prvni cislo znaci pocet nacitanych cisel
 *  - vypiste pole nactenych cisel
 *  - preskupte cisla tak, aby v prvni casti pole byla cisla, ktera jsou druhou
 *    mocninou prirozeneho cisla.
 *  - vypiste cele pole v binarni reprezentaci
 *  - serdte pole:
 *	prvni cast vzestupne podle poctu jednicek v bin. reprezentaci
 *	druhou cast sestupne podle poctu jednicek v bin. reprezentaci
 *  - vypiste
 */
#include <stdio.h>
#include <stdlib.h>
#include <math.h>


int *cisla = NULL;	/* pole cisel */
int pocet_cisel = 0;	/* jeho velikost */
int delim = -1;		/* index prvku pred kterym ma byt oddelovac ve vypisu casti pole */


void 
uklid()
{
    if(cisla != NULL)
    {
	free(cisla);
	cisla = NULL;
    }
}


int  
vstup()
{
    int pocet = 1;
    int index = 0;
    int vstup;

    uklid();

    while(index < pocet)
    {
	/* nacte vstupni cislo todo ve while */
	int retscan = fscanf(stdin, "%d", &vstup);
	if (retscan == 0) {
	    fprintf(stderr, "CHYBA: program ocekava pouze cela cisla!\n");
	    exit(1);
	}

	if(pocet < 1)
	{
	    fprintf(stderr, "CHYBA: cislo musi byt vetsi nez 0!\n");
	    continue;
	}

	/* pokud jeste neni zinicializovane pole, prave cteme pocet ... */
	if(cisla == NULL)
	{
	    pocet = vstup;
	    
	    cisla = (int *)malloc(pocet * sizeof(int));

	    if(!cisla)
	    {
		fprintf(stderr, "CHYBA: nepodarilo se alokovat pamet pro vstup!\n");
		exit(1);
	    }
	    continue;
	}
	
	cisla[index] = vstup;
	
	index++;
    }

    return pocet;
}


void
vypis(int* pole, int prvku)
{
    int i;
    printf("(");

    for(i=0; i<prvku; i++)
    {
	printf("%d", pole[i]);
	
	if(i+1 != prvku)
	    delim == i+1 ? printf(" | ") : printf(", ");
    }

    printf(")\n");
}


int 
je_2mocnina_N(int n)
{
    double sqr_rt = sqrt((double)n);

    if( (sqr_rt * sqr_rt) == n)
	return 1;
    else
	return 0;
}


void
preskup()
{
    int *nove_pole = (int *)malloc(sizeof(int) * pocet_cisel);
    int idx_nove = 0;

    int i;
    for(i=0; i<pocet_cisel; i++)
    {
	if(je_2mocnina_N(cisla[i]))
	{
	    nove_pole[idx_nove] = cisla[i];
	    idx_nove++;
	    cisla[i] = -1;
	}
    }

    delim = idx_nove;
    
    for(i=0; i<pocet_cisel; i++)
    {
	if(cisla[i] != -1)
	{
	    nove_pole[idx_nove] = cisla[i];
	    idx_nove++;
	}
    }

    free(cisla);
    cisla = nove_pole;
}


void print_int_bin(int n) {
    unsigned int i;
    int prefix=1;	/* jsme v 1ckovem prefixu ? (odstraneni leading zeroes) */

    i = 1<<(sizeof(n) * 8 - 1);

    while (i > 0) {
	if (n & i)
	{
	    printf("1");
	    prefix = 0;
	}
	else
	    if(!prefix)
		printf("0");
	i >>= 1;
    }
}


void
vypis_bin(int *pole, int prvku)
{
    int i;
    printf("(");

    for(i=0; i<prvku; i++)
    {
	print_int_bin(pole[i]);
	if(i+1 != prvku)
	    delim == i+1 ? printf(" | ") : printf(", ");
    }

    printf(")\n");  
}


int 
pocet_jednicek(int n) {
    unsigned int i;
    int pocet=0;

    i = 1<<(sizeof(n) * 8 - 1);

    while (i > 0) {
	if (n & i)
	    pocet++;
	i >>= 1;
    }

    return pocet;
}


int
porovnej_vzestupne(const void* a, const void* b)
{
    int pja = pocet_jednicek(*(int *)a);
    int pjb = pocet_jednicek(*(int *)b);

    if(pja < pjb) 
	return -1;
    else if(pja == pjb)
	return 0;
    else
	return 1;
}


int
porovnej_sestupne(const void* a, const void* b)
{
    int pja = pocet_jednicek(*(int *)a);
    int pjb = pocet_jednicek(*(int *)b);

    if(pja < pjb) 
	return 1;
    else if(pja == pjb)
	return 0;
    else
	return -1;
}


void
serad()
{
    qsort(cisla, delim, sizeof(int), porovnej_vzestupne); 
    qsort(cisla + delim , pocet_cisel - (delim), sizeof(int), porovnej_sestupne);
}



/*int 
main(int argc, char** argv)
{
    pocet_cisel = vstup();
    
    vypis(cisla, pocet_cisel);
    
    preskup(); 

    vypis(cisla, pocet_cisel);

    serad();

    vypis(cisla, pocet_cisel);
    vypis_bin(cisla, pocet_cisel);

    uklid();
	system("pause");
    return 0;
} */
