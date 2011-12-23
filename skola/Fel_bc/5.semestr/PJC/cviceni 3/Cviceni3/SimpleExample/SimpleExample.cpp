#define BOOST_TEST_MODULE example
#include <boost/test/included/unit_test.hpp>
#include "MyClass.hpp"

MyClass myClass;

BOOST_AUTO_TEST_CASE( test_case1 ){
  	BOOST_CHECK( not myClass.empty());
}

