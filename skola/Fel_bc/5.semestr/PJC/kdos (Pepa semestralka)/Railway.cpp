#include "Railway.h"

// ======== FUNCTIONS =========================================

void Tokenize(const string& str, vector<string>& tokens,
              const string& delimiters = ":")
{
    // Skip delimiters at beginning
    string::size_type lastPos = str.find_first_not_of(delimiters, 0);
    // Find first "non-delimiter"
    string::size_type pos     = str.find_first_of(delimiters, lastPos);

    while (string::npos != pos || string::npos != lastPos)
    {
        // Found a token, add it to the vector.
        tokens.push_back(str.substr(lastPos, pos - lastPos));
        // Skip delimiters.  Note the "not_of"
        lastPos = str.find_first_not_of(delimiters, pos);
        // Find next "non-delimiter"
        pos = str.find_first_of(delimiters, lastPos);
    }
}

// ======== NODE CLASS DECLARATIONS ============================

Node::Node ( City *station )
{
	_station = station;
}




// ======== RAILWAY CLASS DECLARATIONS =========================

Railway::Railway(void)
{
	_cityList = new vector<City *>;
	_trainList = new vector<Train *>;
}

Railway::~Railway(void)
{
	delete _cityList;
	delete _trainList;
}

/* 
** Adds city into the citylist
** Returns NULL if city does exist or returns City instance if 
**		   new city is Added.
*/
City* Railway::addCity ( const char *cityName )
{
	// If exists
	if ( getCityByName ( cityName ) != NULL ) return NULL;

	// If does not
	City *newCity = new City( cityName );
	_cityList->push_back( newCity );

	return newCity;
}

/* 
** Gets city from the list if exists
** Returns NULL if city does NOT exist or returns City instance if 
**		   city is found on the list.
*/
City* Railway::getCityByName ( const char *cityName )
{
	for ( unsigned int i = 0; i < _cityList->size(); i++)
	{
		if ( strcmp( _cityList->at(i)->getName(), cityName) == 0 ) return _cityList->at(i); 
	}

	return NULL;
}

Train* Railway::addTrain ( int id )
{
	// If exists
	if ( getTrainById( id ) != NULL ) return NULL;

	// If does not
	Train *newTrain = new Train( id );
	_trainList->push_back( newTrain );

	return newTrain;
}

Train* Railway::getTrainById ( int id )
{
	for ( unsigned int i = 0; i < _trainList->size(); i++)
	{
		if ( _trainList->at(i)->getID() == id ) return _trainList->at(i); 
	}

	return NULL;
}

int	Railway::getStationsCount ( void ) const
{
	return _cityList->size();
}

string Railway::getCityNameByIndex ( int index ) const
{
	return _cityList->at(index)->getName();
}

void Railway::searchWay ( City *fromCity, City *toCity, Time startTime ) const
{


	// DIJKSTRA!
}


// ======== CITY CLASS DELCARATIONS ============================

City::City ( const char *cityName )
{
	this->_name = cityName;
}

City::~City()
{
	//todo: Delete all connections
}

const char* City::getName ( void ) const 
{
	return this->_name;
}

/* 
** Adds new connection into station
** Returns NULL if city already contains this connection and adds and returns 
**		   new Connection instance if not.
*/
Connection* City::addConnection ( const City *toCity )
{
	for ( unsigned int i = 0; i < _connsList.size(); i++)
	{
		if ( strcmp( _connsList.at(i)->getToCityName(), toCity->getName() ) == 0 ) return NULL; 
	}

	Connection *newConn = new Connection( this, toCity );
	_connsList.push_back( newConn );

	return newConn;
}

int	City::getConnectionsNumber( void ) const
{
	return _connsList.size();
}

void City::printConnectionByIndex( int index ) const
{
	Connection *conn = _connsList.at( index );
	cout << "=> CONNECTION FROM " << conn->getFromCityName() << " TO " << conn->getToCityName() << ": " << endl;

	if ( conn->hasAnyLines() )
	{
		conn->printAllLines();
	} else
	{
		cout << "No lines in this connection at all" << endl;
	}
}

// ======== CONNECTION CLASS DECLARATIONS ======================

Connection::Connection ( const City *fromCity, const City *toCity )
{
	_fromCity = fromCity;
	_toCity = toCity;

	_firstLine = NULL;
}

const char*	Connection::getToCityName( void )
{
	return _toCity->getName();
}

const char*	Connection::getFromCityName( void )
{
	return _fromCity->getName();
}

/* 
** Adds new Line into the Connection
** RETURNS: new added Line
*/
Line* Connection::addLine( string departure, int rideLength, Train *train )
{
	// Times settings
	Time dptTime( departure );
	Time arrTime = dptTime.addMinutesToTime( rideLength );

	Line *newLine = new Line(dptTime, arrTime, train, this);

	// No line on this connection
	if ( _firstLine == NULL ) 
	{
		_firstLine = newLine;
		_lastLine = newLine;

		return newLine;
	}

	// Find a place in the list
	Line *temp = _firstLine;

	while ( temp != NULL )
	{
		int arrComparison = temp->getArrival().compareTo( newLine->getArrival() );
		int deppComparison = temp->getDeparture().compareTo ( newLine->getDeparture() );

		if ( arrComparison == 0 && deppComparison == 0) 
		{
			Logger::log( "Departure and arrival of this line are equal - DON'T ADD anything." );
			return newLine;
		} else if ( arrComparison == 0 && deppComparison == -1 )
		{
			// Same arrival time, existing node has got lesser department time
			return addLineToList ( temp, newLine, true ); 
		} else if ( ( arrComparison == 0 && deppComparison == 1 ) || arrComparison == 1 )
		{
			// Same arrival time, existing node has got greater department time
			// OR
			// If arrival time of existing node is greater then arrival time of new node (put 
			// it on its place)
			return addLineToList ( temp, newLine, false ); 
		} else 
		{
			if ( temp->getNextLine() != NULL ) 
			{
				// Anything else -> go on
				temp = temp->getNextLine();
			} else 
			{
				// Put the new link to end of the list
				Logger::log( "Line ADDED to the END of list of the current connection" );
				return addLineToList ( temp, newLine, true ); 
			}
		}
	}
}

/* 
** Switch pointers and adds new line on/after the place of existing one
** RETURNS: added new Line
*/
Line* Connection::addLineToList ( Line *existingLine, Line *newLine, bool isNewLineAfter )
{
	if (isNewLineAfter)
	{
		newLine->setNextLine( existingLine->getNextLine() );

		// If it's last line or not
		if ( newLine->getNextLine() == NULL )
		{
			_lastLine = newLine;
		} else
		{
			newLine->getNextLine()->setPrevLine( newLine );
		}

		newLine->setPrevLine( existingLine );
		existingLine->setNextLine( newLine );

		return newLine;
	} else
	{
		newLine->setPrevLine( existingLine->getPrevLine() );

		// If it's first line or not
		if ( newLine->getPrevLine() == NULL)
		{
			_firstLine = newLine;
		} else
		{
			newLine->getPrevLine()->setNextLine( newLine );
		}

		newLine->setNextLine( existingLine );
		existingLine->setPrevLine ( newLine );

		return newLine;
	}
}

void Connection::printAllLines ( void )
{
	Line *temp = _firstLine;

	while ( temp != NULL )
	{
		temp->printInfo();
		temp = temp->getNextLine();
	}
}

bool Connection::hasAnyLines( void )
{
	if ( _firstLine == NULL ) return false;
	else return true;
}

// ======== TRAIN CLASS DECLARATIONS ===========================

Train::Train ( int id ) 
{
	_id = id;
}

Train::~Train()
{
	//todo: Destruct all train's lines
}

int Train::getID ( void ) 
{
	return _id;
}


// ======== TIME CLASS DECLARATIONS ===========================

Time::Time( int minutes, int hours )
{
	_minutes = minutes;
	_hours = hours;
}

/* 
** Adds more minutes to current time and creates 
** new instance from it
*/
Time::Time( int minutes )
{	
	//todo: Test minutes addition
	_hours = minutes / 60;
	_minutes = minutes - (_hours * 60);

	_hours = _hours % 24;
}

/* 
** Adds Time to current time and creates 
** new instance from it
*/
Time::Time( string time )
{
	vector<string> tokens;
	Tokenize( time, tokens );

	// string to int
	istringstream buffer(tokens.at(0));
	if ( !(buffer >> _hours ) ) throw new string( "Your XML contains Time in wrong format!\n" + 
									      string ("Please, correct this (format 'H:M') and try again.") );
	buffer = istringstream(tokens.at(1));
	if ( !(buffer >> _minutes ) ) throw new string( "Your XML contains Time in wrong format!\n" + 
									      string ("Please, correct this (format 'H:M') and try again.") );
}

/* 
** Adds more minutes into current time
** Returns new time (with minutes added)
*/
Time Time::addMinutesToTime( int minutesAdd )
{
	int currentTimeMinutes = _hours*60 + _minutes;
	currentTimeMinutes += minutesAdd;

	return Time ( currentTimeMinutes );	
}

/* 
** Adds Time period into current time
** Returns new time (with Time period added)
*/
Time Time::addTime( Time addTime )
{
	int minutes = addTime._minutes + addTime._hours*60;
	return addMinutesToTime( minutes );
}

/* 
** Compares times
** Returns -1 if this time is lower than comparer
**		    1 if this time is greater than comparer
**		    0 if both times are equal
*/
int	Time::compareTo( const Time comparer ) const
{
	//todo: Overload operators! DO NOT WRITE LIKE AN ASSHOLE..
	if ( _hours < comparer._hours ) return -1;
	if ( _hours > comparer._hours ) return 1;

	if ( _hours == comparer._hours ) 
	{
		if ( _minutes < comparer._minutes ) return -1;
		if ( _minutes > comparer._minutes ) return 1;
		if ( _minutes == comparer._minutes ) return 0;
	}
}

string Time::print( void ) 
{
	stringstream hours, minutes;
	
	if ( _hours < 10 ) hours << "0";
	if ( _minutes < 10 ) minutes << "0";

	hours << _hours;
	minutes << _minutes;

	string *timeString = new string ( hours.str() + ":" + minutes.str() );
	return *timeString;
}

// ======== LINE CLASS DECLARATIONS ===========================

Line::Line ( Time departure, Time arrival, Train *train, Connection *conn ):
_departure(departure), _arrival(arrival)
{
	//todo: line creation problem
	_train = train;
	_parrentConnection = conn;

	_nextLine = NULL;
	_prevLine = NULL;

	// Overnight settings
	int timeComparison = departure.compareTo( arrival );

	switch ( timeComparison ) 
	{
		case 1: _isOvernight = true; 
			break;
		case -1: _isOvernight = false;
			break;
		case 0: throw new string ("Time of departure and arrival of the train is equal.\nPlease, correct this in your XML!");
			break;
		default: Logger::log( "Cannot create line - comparison of times between department and arrival failed!" );
			break;
	}
}

Line::~Line()
{
	if ( _nextLine != NULL ) delete _nextLine;
}

void Line::setPrevLine( Line *prevLine )
{
	_prevLine = prevLine;
}

void Line::setNextLine( Line *nextLine )
{
	_nextLine = nextLine;
}

Time Line::getDeparture( void )
{
	return _departure;
}

Time Line::getArrival( void )
{
	return _arrival;
}

Line* Line::getNextLine( void )
{
	return _nextLine;
}

Line* Line::getPrevLine( void )
{
	return _prevLine;
}

void Line::printInfo ( void )
{
	cout << "  Departure: " << _departure.print() << " -> \t Arrival: " << 
		_arrival.print() << " <- \t(train: " << _train->getID() << ")" << endl;
}


