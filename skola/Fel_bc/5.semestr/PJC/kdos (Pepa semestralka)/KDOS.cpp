/* Version 0.111, 10.1.2011
** App created by Josef Lobotka ~ NeoGenet1c
** mail: lobotjos@fel.cvut.cz
********************************************/

#include "KDOS.h"
#include "OutputGenerator.h"
#include "TrackParser.h"
#include "Logger.h"

#include "Railway.h"

#include "iostream"
#include "string"

using namespace std;

/* 
** KDOS App starter method
** App parameter = .xml file with railway orders full PATH
*/
int main (int argc, char* argv[])
{
	// Set locales
	locale cz ("Czech");
	locale::global(cz);

	// Start logging if debug mode
	#ifdef _DEBUG 
		Logger::setLogging(true);
	#endif

	Logger::log("Program has started.");

	// Variables definition
	TrackParser			*trackParser;
	OutputGenerator		*out;
	string				command, infoCommand;
	Railway*			railwayTrack;

	// Handle app parameter arguments
	if ( argc != 2 ) 
	{
		OutputGenerator::badParameter();
		return -1;
	}

	// Try to load XML file
	try 
	{
		trackParser = new TrackParser(argv[1]);
	} catch ( exception )
	{
		OutputGenerator::badFile();
		return -1;
	}

	// Parse xml file and if OK, prepare & return railway graph
	try
	{
		railwayTrack = trackParser->createTrackModel();
	} catch ( string *ex)
	{
		OutputGenerator::errMsg( ex );
		delete ex;
		system("pause");
		return -1;
	}

	// Display output
	out = new OutputGenerator( railwayTrack );

	out->mainMenu();

	// Dereference all 
	delete out;

	system("pause");
	return 0; 
}
