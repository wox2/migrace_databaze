#include "OutputGenerator.h"

const string OutputGenerator::_infoValidParam = "\n \
========================================== \n \
Please, start KDOS by putting in FULL PATH of your XML railway map as parameter. \n \
HINT: KDOS.exe full_path_to_railway.xml\n \
There are NO MORE parameters. Try again.\n\
==========================================";

OutputGenerator::OutputGenerator(Railway * railway)
{
	_railwayTrack = railway;
	welcome();
}

OutputGenerator::~OutputGenerator(void)
{
	exit();
}

void OutputGenerator::badParameter(void)
{
	cout << "Bad parameters entered!" << _infoValidParam << endl;
}

void OutputGenerator::badFile(void)
{
	cout << "Path does not exist or file is not an openable XML!" << _infoValidParam << endl;
}

void OutputGenerator::errMsg(const string *msg)
{
	cout << "ERROR during railway track from XML parsing! \n" << *msg << endl;
}

void OutputGenerator::welcome(void)
{
	cout << "Welcome in KDOS, version 0.111! Enjoy use of this app :).." << endl;
}

void OutputGenerator::exit(void)
{
	cout << "Exiting application - thanks for using!" << endl;
}

void OutputGenerator::showMenu(void)
{
	cout << 
"==== main menu ===================================\n\
 > INFO - information about whole railway \n\
 > FIND - quickest way finder \n\
 > EXIT - exits this app \n\
==================================================\n\
Insert command from the main menu to take action: ";
}

void OutputGenerator::showInfoMenu(void)
{
	cout << 
"==== info menu ===================================\n\
 > STATIONS - information about all available stations \n\
 > TRAINS - information about all available trains \n\
 > BACK - go back to main menu \n\
==================================================\n\
Insert command from the info submenu to take action: ";
}

void OutputGenerator::showSearchMenu(void)
{
	cout << 
"==== search menu ===================================\n\
 > FIND - try to find another fastest connection \n\
 > BACK - go back to main menu \n\
==================================================\n\
Insert command from the search submenu to take action: ";
}

void OutputGenerator::searchFrom ( void )
{
	cout << 
"==== from station ================================\n\
Please, write down station you want to travel FROM\n\
Insert start destination station's name (case sensitive): ";
}

void OutputGenerator::searchTo ( void )
{
	cout << 
"==== to station ================================\n\
Please, write down station you want to travel TO\n\
Insert end destination station's name (case sensitive): ";
}

void OutputGenerator::clear(void)
{
	cout << "\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \
			 \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \
			 \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \
			 \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n";
}

void OutputGenerator::StationNamesListPrint(void)
{
	cout << "==== stations list ===============================" << endl;
	for ( int i = 0; i < _railwayTrack->getStationsCount() ; i++ )
	{
		cout << _railwayTrack->getCityNameByIndex(i) << endl;
	}
}

void OutputGenerator::menuStationsPrint( void )
{
	cout << "==================================================\n\
 > [station_name] - detailed information about station from \n \
                     the list above (case sensitive) \n\
 > MAIN - go to main menu \n\
 > BACK - go back to info menu \n\
==================================================\n\
Insert command from the submenu to take action: ";
}

void OutputGenerator::menuStationDetail( void )
{
	cout << "==================================================\n\
 > MAIN - go to main menu \n\
 > BACK - go back to stations list \n\
==================================================\n\
Insert command from the submenu to take action: ";
}

void OutputGenerator::mainMenu( void )
{
	clear();
	showMenu();

	string command;

	// Wait for commands from command-line and react appropriatelly
	while ( true )
	{
	   	getline(cin, command);

		if( command == string("INFO") || command == string("info") ){

			// Show INFO menu
			if ( menuInfo() ) 
			{
				clear();
				showMenu();
			}

		} else if( command == string("FIND") || command == string("find") ){
			
			// Show SEARCH menu
			if ( menuSearch() )
			{
				clear();
				showMenu();
			}

		} else if( command == string("EXIT") || command == string("exit") ){
			break;
		} else
		{
			cout << "BAD COMMAND ENTERED! Please, try again: ";
		}
	}
}

bool OutputGenerator::menuInfo( void )
{
	clear();
	showInfoMenu();
	string infoCommand;

	// Wait for commands from command-line and react appropriatelly
	while (1)
	{
	   	getline(cin, infoCommand);

		if( infoCommand == string("STATIONS") || infoCommand == string("stations") ){

			// Show STATIONS menu
			if ( ( menuStations () )) return true;
			else {
				clear();
				showInfoMenu();
			}

		} else if( infoCommand == string("TRAINS") || infoCommand == string("trains")){
			
			// Show TRAINS menu
			menuTrains();

		} else if( infoCommand == string("BACK") || infoCommand == string("back") ){
			return true;
		} else
		{
			cout << "BAD COMMAND ENTERED! Please, try again: ";
		}
	}

	return true;
}

bool OutputGenerator::menuTrains ( void )
{
	// todo: implement menu trains
	return true;
}

bool OutputGenerator::menuSearch( void )
{
	string fromStation, toStation;
	City *fromStationCity = NULL, *toStationCity = NULL;

	clear();
	StationNamesListPrint();
	
	// Continue searching sequence
	searchFrom();

	while ( true )
	{
		getline(cin, fromStation);

		const char *command = (const char *)fromStation.c_str();
		City *city = _railwayTrack->getCityByName( command );

		if ( city != NULL ){

			if ( fromStationCity == NULL ) 
			{
				fromStationCity = city;	
				clear();

				// Continue searching sequence
				StationNamesListPrint();
				searchTo();
			}
			else toStationCity = city;

		} else
		{
			if ( fromStationCity == NULL ) 
			{
				cout << "Invalid FROM station name entered.\n" 
					<< " Please, insert station name FROM which you want to travel again!" << endl;

			} else
			{
				cout << "Invalid TO station name entered.\n" 
					<< " Please, insert station name TO which you want to travel again!" << endl;
			}
		}

		if ( fromStationCity != NULL && toStationCity != NULL ) break;
	}

	// Find fastest & show menu
	clear();
	_railwayTrack->searchWay( fromStationCity, toStationCity, Time("12:25") );
	showSearchMenu();

	string searchMenu;

	while ( true )
	{
		getline(cin, searchMenu);

		if ( searchMenu == string ("FIND") || searchMenu == string ("find") ){
			
			cout << "Going to search for another BEST way..." << endl;
			//todo: implement iterative search
			return false;

		} else if ( searchMenu == string ("BACK") || searchMenu == string ("back") ){
						
			cout << "Going back to MAIN MENU..." << endl;
			return true;

		} else
		{
			cout << "BAD COMMAND ENTERED! Please, try again: ";
		}
	}

	return true;
}

bool OutputGenerator::menuStations( void ) 
{
	// Variables
	string stationsCommand;

	clear();
	StationNamesListPrint();
	menuStationsPrint();

	while ( true )
	{
		// Stations detail
		getline(cin, stationsCommand);

		const char *command = (const char *)stationsCommand.c_str();
		City *city = _railwayTrack->getCityByName( command );

		if ( city != NULL ){

			if ( ( stationDetailsPrint ( city ) )) return true;
			else {
				clear();
				StationNamesListPrint();
				menuStationsPrint();
			}

		} else if ( stationsCommand == string ("MAIN") || stationsCommand == string ("main")  ){
			
			cout << "Going to MAIN MENU..." << endl;
			return true;

		} else if ( stationsCommand == string ("BACK") || stationsCommand == string ("back") ){
						
			cout << "Going to back to INFO menu..." << endl;
			return false;
		} else
		{
			cout << "BAD COMMAND OR CITY ENTERED! Please, try again: ";
		}

	}

	return true;
}

bool OutputGenerator::stationDetailsPrint ( City *city )
{
	clear();
	cout << "==== details about station =======================" << endl;
	cout << "STATION: " << city->getName() << endl;

	for ( int i = 0 ; i < city->getConnectionsNumber() ; i++ )
	{
		city->printConnectionByIndex(i);
	}

	menuStationDetail();
	string stationDetailCommand;

	while ( true )
	{
		// Stations detail
		getline(cin, stationDetailCommand);

		if ( stationDetailCommand == string ("MAIN") || stationDetailCommand == string ("main") ){
			
			cout << "Going to MAIN MENU..." << endl;
			return true;

		} else if ( stationDetailCommand == string ("BACK") || stationDetailCommand == string ("back") ){
						
			cout << "Going to back to STATIONS LIST..." << endl;
			return false;

		} else
		{
			cout << "BAD COMMAND ENTERED! Please, try again: ";
		}
	}

	return true;
}
