/* Version 0.111, 10.1.2011
** App created by Josef Lobotka ~ NeoGenet1c
** mail: lobotjos@fel.cvut.cz
********************************************/

#include "iostream"
#include "string"

using namespace std;

#ifndef LOGGER_H
#define LOGGER_H

class Logger
{

private:
	
	static bool		isLoggingOn;

public:

	static void		log(string toLog);
	static void		setLogging(bool isOn);
};

#endif
