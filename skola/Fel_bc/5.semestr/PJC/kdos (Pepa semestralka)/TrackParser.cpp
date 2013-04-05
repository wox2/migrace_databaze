#include "TrackParser.h"

#include "Logger.h"

#include "libs/tinystr.cpp" 
#include "libs/tinyxml.cpp" 
#include "libs/tinyxmlerror.cpp" 
#include "libs/tinyxmlparser.cpp"

#include <sstream>

template <class T>
inline string to_string (const T& t)
{
stringstream ss;
ss << t;
return ss.str();
}

TrackParser::TrackParser(char * XMLFilePath)
{
	this->_doc = TiXmlDocument(XMLFilePath);

	bool loadedOK = this->_doc.LoadFile();
	if ( loadedOK ) 
	{	
		Logger::log("XML file successfully opened." );	
	} else
	{
		Logger::log("XML file did not opened due to errors.");	
		throw exception();	
	}
}

TrackParser::~TrackParser(void)
{
}

/* 
** Parses XML and creates railway track model.
** Returns NULL if there is not available data for model.
*/
Railway *  TrackParser::createTrackModel (void)
{
	Logger::log("---- STARTED parsing track model from XML...");

	Railway				*railModel = new Railway();

	// Prepare XML parser
	TiXmlHandle			xmlHandler( &(this->_doc) );
	TiXmlElement		*root = this->_doc.RootElement();

	Logger::log ("Document root element is: " + string ( root->Value() ) );
	
	// Iterate through cities
	TiXmlElement		*elems = xmlHandler.FirstChildElement( root->Value() ).FirstChildElement("station").Element();
	
	Logger::log ( "-- Adding CITIES" );

	for ( elems; elems; elems = elems->NextSiblingElement() )
	{
		const char		*cityName = elems->Attribute("name");

		// add new city to model
		City *addedCity = railModel->addCity( cityName );

		( addedCity != NULL ) 
			? Logger::log ( "Added NEW CITY: " + string ( cityName )  ) 
			: Logger::log ( "City " + string( cityName ) + " IS ALREADY in the railway map!"  );
	}

	// garbage last elems
	TiXmlElement		*elemToDelete = elems;
	delete elemToDelete;

	Logger::log ( "-- ADDING CONNECTIONS into cities" );

	elems = xmlHandler.FirstChildElement( root->Value() ).FirstChildElement( "station" ).Element();

	for ( elems; elems; elems = elems->NextSiblingElement() )
	{
		// Iterate through connections
		TiXmlHandle			cityHndlr(elems);
		TiXmlElement		*connElems = cityHndlr.FirstChild( "departures" ).FirstChild( "to_station" ).Element();
		
		if ( !connElems )
		{
			Logger::log( "NO CONNECTIONS in city " + string ( elems->Attribute("name") ) + " ADDED." );
			continue;
		}

		City		*fromCity = railModel->getCityByName( elems->Attribute("name") );
		for ( connElems; connElems; connElems = connElems->NextSiblingElement() )
		{
			City		*toCity = railModel->getCityByName( connElems->Attribute("name") );
			
			// If final destination station does not exist from parsing below, throw err
			if ( toCity == NULL ) 
			{
				throw new string ("Station " + string ( connElems->Attribute("name") ) 
					+ " DOES NOT exist despite of the fact you defined connection to it.\nPlease, correct your XML integrity!" );
				return NULL;
			}
			Connection * addedConnection = fromCity->addConnection( toCity );

			if ( addedConnection != NULL ) 
			{
				Logger::log ( "ADDED NEW Connection FROM " + string ( fromCity->getName() ) 
						    + " TO " +  string ( toCity->getName() ) ) ;
				
			} else
			{
				Logger::log ( "Connection FROM " + string ( fromCity->getName() ) + " TO " 
							+  string ( toCity->getName() ) + " EXISTS ALREADY in the city " + string ( fromCity->getName() ) );
				continue;
			}

			
			Logger::log ( "-- ADDING TRAINS into railway" );
			
			// Iterate through every train on current connection
			TiXmlHandle			connsHndlr(connElems);
			TiXmlElement		*trainElems = connsHndlr.FirstChild( "train" ).Element();

			// No trains or departments on this connection
			if ( !trainElems )
			{
				Logger::log( "There are NO TRAINS on this connection." );
				continue;
			}

			for ( trainElems; trainElems; trainElems = trainElems->NextSiblingElement() )
			{
				int trainID;
				istringstream buffer( trainElems->Attribute("id") );
				if ( !(buffer >> trainID ) )
				{
					// todo: Check if it is digit!
				}

				Train		*addedTrain = railModel->addTrain( trainID );
				Train		*currentTrain = NULL;

				// If train exists - do not add & use existing train instance
				if ( addedTrain != NULL ) 
				{
					Logger::log ( "ADDED NEW Train ID " + string ( trainElems->Attribute("id") ) ) ;
					currentTrain = addedTrain;
				} else 
				{
					Logger::log ( "Train ID " + string ( trainElems->Attribute("id") ) + " EXISTS ALREADY in the railway system - using existing one");
					currentTrain = railModel->getTrainById ( trainID );
				}

				Logger::log ( "-- ADDING LINES into connections" );
			
				// Iterate through every line of the train
				TiXmlHandle			trainsHndlr( trainElems );
				TiXmlElement		*linesElems = trainsHndlr.FirstChild( "departure_time" ).Element();

				// Ride length of this train on connection
				TiXmlElement		*rideElement = trainsHndlr.FirstChild( "travel_time" ).Element();
				
				int rideLength = -1;
				// If there is (not) rideLength info
				if ( rideElement ) 
				{
					buffer = istringstream( rideElement->FirstChild()->Value() );
					if ( !(buffer >> rideLength ) )
					{
						// todo: Check if it is digit!
					}

					if ( rideLength < 1 ) throw new string ("Train ID " + to_string<int>( currentTrain->getID() ) 
						+ " has got his length of ride WRONGLY SPECIFIED (negative or zero value)!\nPlease, correct this in your XML!" );
				} else 
				{
					throw new string ("Train ID " + to_string<int>( currentTrain->getID() ) 
						+ " DOES NOT have his length of ride specified!\nPlease, add this info into your XML!" );
					return NULL;
				}

				// No available lines on this connection
				if ( !linesElems )
				{
					Logger::log( "This train DOES NOT have any lines" );
					continue;
				}

				for ( linesElems; linesElems; linesElems = linesElems->NextSiblingElement() )
				{
					string departmentTime = linesElems->FirstChild()->Value();

					// New line creation from current train instance, length of ride & departure time
					Line		*addedLine = addedConnection->addLine( departmentTime, rideLength, currentTrain );

					Logger::log( "ADDED line with department time of " + departmentTime );

					// Add line into its train
					//todo: add lines into train! currentTrain->addLine ( line );
				}
			}
		}
	}

	Logger::log("...FINISHED parsing track model from XML.");
	return railModel;
}
