#include "Logger.h"


// Flag indicating logging
bool Logger::isLoggingOn = false;

/*
** Write down the given message if logging is ON.
*/
void Logger::log ( string toLog )
{


	if (isLoggingOn) cout << "LOG " << toLog << endl;
}

/*
** Enable/Disable logging.
*/
void Logger::setLogging(bool isOn)
{
	if (isOn)
	{
		cout << "Sets LOGGING ON!" << endl;
	} else
	{
		cout << "Sets LOGGING OFF!" << endl;
	}
	isLoggingOn = isOn;
}
