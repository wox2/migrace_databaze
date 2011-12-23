#include <string>
#include <string.h>
#include <iostream>
#include <stdlib.h>
#include <fstream>
using namespace std;

struct SGen
{
	int * m_pErrors;
	std::string m_Value;
	int iErrorSize;
};

int g_iGenCount = 0;
int g_iDNASize = 0;
int g_iMaxError = 0;
bool g_bResult = false;

std::string g_Dna;
std::string g_Alphabet;

SGen * g_pGens;

void readData( )
{
	std::cin >> g_Alphabet;
	std::cin >> g_Dna;
	std::cin >> g_iGenCount;

	g_iDNASize = g_Dna.size( );

	g_pGens = new SGen[ g_iGenCount + 1 ];

	for ( int i = 1; i <= g_iGenCount; ++i )
	{
		SGen newGen;

		std::cin >> newGen.m_Value;
		newGen.iErrorSize = g_Dna.size( ) + 1;
		newGen.m_pErrors = new int[ g_Dna.size( ) + 1 ];
		memset( newGen.m_pErrors, 0, g_Dna.size( ) + 1 );

		g_pGens[ i ] = newGen;
    }

	//for ( int i = 1; i <= g_iGenCount; ++i )
	//{
	//	std::cout << g_pGens[ i ].m_Value << " " << g_pGens[ i ].iErrorSize << " " << g_pGens[ i ].m_pErrors[ 0 ] << std::endl;
 //   }

	std::cin >> g_iMaxError;
}

int genCompare( SGen newGen, int iPosition )
{
    int iError = 0;

    if ( iPosition + newGen.m_Value.size( ) > g_Dna.size( ) )
	{
        return g_iMaxError + 1;
    }

    for ( unsigned int i = 0; i < newGen.m_Value.size( ); ++i )
	{
        if ( g_Dna[ iPosition + i - 1 ] != newGen.m_Value[ i ])
		{
            ++iError;
        }
    }

    return iError;
}

void getNextGen( int iPosition, int * results, int iError, int iComputedGens )
{
    for ( int i = 1; i < g_iGenCount + 1; ++i )
	{
        if ( g_pGens[ i ].m_pErrors[ 0 ] != -1 )
		{
            int iGenError = g_pGens[ i ].m_pErrors[ iPosition ];

            if ( iGenError + iError <= g_iMaxError )
			{
                results[ iComputedGens + 1 ] = i;

                if ( iComputedGens + 1 == g_iGenCount )
				{
					g_bResult = true;

					for ( int j = 0; j < g_iGenCount + 1; ++j )
					{
						std::cout <<  results[ j ] << " ";
					}

					std::cout << std::endl;
                } else {
                    g_pGens[ i ].m_pErrors[ 0 ] = -1;

					getNextGen( iPosition + g_pGens[ i ].m_Value.size( ), results, iError + iGenError, iComputedGens + 1 );
                    
					g_pGens[i].m_pErrors[ 0 ] = 0;
                }
            }
        }
    }
}

void checkResult( )
{
    for ( unsigned int i = 1; i < g_Dna.size( ) + 1; ++i )
	{
        int * pResult = new int[ g_iGenCount + 1 ];
        pResult[ 0 ] = i;
        getNextGen( i, pResult, 0, 0 );
    }
}

void computeResult( )
{
    for ( int i = 1; i < g_iGenCount + 1; ++i )
	{
        for ( int j = 1; j < g_iDNASize + 1; ++j )
		{
            g_pGens[ i ].m_pErrors[ j ] = genCompare( g_pGens[ i ], j );
        }
    }

    checkResult( );
}

int main( )
{
	readData( );
	computeResult( );

	if ( ! g_bResult )
	{
		std:: cout << "0";
	}

	return 0;
}