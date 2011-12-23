/* Version 0.111, 10.1.2011
** App created by Josef Lobotka ~ NeoGenet1c
** mail: lobotjos@fel.cvut.cz
********************************************/

#include <vector>
#include <iostream>
#include <string>
#include <sstream>
#include <algorithm>
#include <iterator>

#include "Logger.h"

using namespace std;

#ifndef RAILWAY_H
#define RAILWAY_H

class Node;
class Railway;
class Time;
class City;
class Connection;
class Train;
class Line;

class Node
{
private:

	City		*_station;

public:
				Node( City *station );


};

class Railway
{
private:
	
	vector<City *>	*_cityList;
	vector<Train *>	*_trainList;

public:

	Railway(void);
	~Railway(void);
	
	City*		addCity( const char *cityName );
	City*		getCityByName( const char *cityName );
	string		getCityNameByIndex( int index ) const;

	Train*		addTrain( int id );
	Train*		getTrainById( int id );

	void		searchWay ( City *fromCity, City *toCity, Time startTime ) const;

	int			getStationsCount ( void ) const;

};

class Time
{
protected:

	int			_hours;
	int			_minutes;

public:

				Time( string time );
				Time( int minutes, int hours );
				Time( int minutes );
				
	Time		addMinutesToTime( int minutes );
	Time		addTime( Time addTime );

	string		print ( void );

	int			compareTo( const Time comparer ) const;
};

class City
{
private:
	
	const char		*_name;
	vector<Connection*> _connsList;

public:

					City( const char *cityName );
					~City();
	const char*		getName() const;
	int				getConnectionsNumber( void ) const;
	Connection*		addConnection( const City *toCity );

	void			printConnectionByIndex( int index ) const;
};


class Connection
{
private:

	const City	*_fromCity;
	const City	*_toCity;
	Line		*_firstLine;
	Line		*_lastLine;

	Line*		addLineToList ( Line *existingLine, Line *newLine, bool isNewLineNext);

public:
	
				Connection ( const City *fromCity, const City *toCity );

	const char*	getToCityName( void );
	const char*	getFromCityName( void );

	void		printAllLines ( void );

	Line*		addLine( string departure, int rideLength, Train *train );
	bool		hasAnyLines( void );
};


class Train
{
private:

	int			_id;
	vector <Line*> _startLinesList;

public:

				Train( int id );
				~Train();

	int			getID( void );
};

class Line
{

private:

	Line		*_prevLine;
	Line		*_nextLine;
	Train		*_train;
	Time		_departure;
	Time		_arrival;
	Connection	*_parrentConnection;
	bool		_isOvernight;

public:
	
				Line ( Time departure, Time arrival, Train *train, Connection *conn );
				~Line ();
	Time		getDeparture ( void );
	Time		getArrival ( void );
	Line*		getNextLine ( void );
	Line*		getPrevLine ( void );
	
	void		printInfo ( void );

	void		setNextLine( Line *nextLine );
	void		setPrevLine( Line *prevLine );
		
};

#endif
