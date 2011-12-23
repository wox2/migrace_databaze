#define BOOST_TEST_MODULE abs
#include <boost/test/included/unit_test.hpp>

#include "abs.cpp"


BOOST_AUTO_TEST_CASE( )
{
    BOOST_CHECK_EQUAL(abs(2),2);
	BOOST_CHECK_EQUAL(abs(-2),2);
	BOOST_CHECK_EQUAL(abs(0),0);
	
}


BOOST_AUTO_TEST_SUITE_END(){}

