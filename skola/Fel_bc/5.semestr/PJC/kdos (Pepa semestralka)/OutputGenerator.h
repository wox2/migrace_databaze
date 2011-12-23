/* Version 0.111, 10.1.2011
** App created by Josef Lobotka ~ NeoGenet1c
** mail: lobotjos@fel.cvut.cz
********************************************/

#include "iostream"
#include "string"
#include "Railway.h"

using namespace std;

class OutputGenerator
{

private:
	const static	string _infoValidParam;
	Railway	*		_railwayTrack;
	
	void			welcome(void);
	void			exit(void);

public:

	OutputGenerator(Railway *railwayTrack);
	~OutputGenerator(void);
	
	static void		badParameter(void);
	static void		badFile(void);
	static void		errMsg( const string *msg);
	
	void			clear(void);

	void			showMenu(void);
	void			showInfoMenu(void);
	void			showSearchMenu(void);
	void			menuStationsPrint ( void );
	bool			stationDetailsPrint ( City *city );
	void			searchFrom ( void );
	void			searchTo ( void );

	void			mainMenu ( void );
	bool			menuSearch ( void );
	bool			menuInfo ( void );
	bool			menuStations ( void );
	void			menuStationDetail( void );
	bool			menuTrains ( void );

	void			StationNamesListPrint(void);

};

