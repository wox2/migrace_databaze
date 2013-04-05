/* Version 0.111, 10.1.2011
** App created by Josef Lobotka ~ NeoGenet1c
** mail: lobotjos@fel.cvut.cz
********************************************/

#include "Railway.h"

#include "libs\tinyxml.h"
#include "libs\tinystr.h"

#include "iostream"
#include "string"

class TrackParser
{
private:

	TiXmlDocument		_doc;

public:

	TrackParser(char * XMLFilePath);
	~TrackParser(void);

	Railway *		createTrackModel (void);

};

