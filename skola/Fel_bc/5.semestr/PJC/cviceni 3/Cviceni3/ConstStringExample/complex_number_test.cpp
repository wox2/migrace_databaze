//  (C) Copyright Gennadiy Rozental 2001-2005.
//  Distributed under the Boost Software License, Version 1.0.
//  (See accompanying file LICENSE_1_0.txt or copy at 
//  http://www.boost.org/LICENSE_1_0.txt)

//  See http://www.boost.org/libs/test for the library home page.
//
//  File        : $RCSfile: const_string_test.cpp,v $
//
//  Version     : $Revision: 49313 $
//
//  Description : simple string class test
// ***************************************************************************

#define BOOST_TEST_MODULE const_string test
#include <boost/test/included/unit_test.hpp>

#include "complex_number.hpp"
using common_layer::complex_number;

BOOST_AUTO_TEST_CASE( constructors_test )
{
    complex_number cs0(2,1 );
    BOOST_CHECK_EQUAL( cs0.real(), 2 );
    BOOST_CHECK_EQUAL( cs0.complex(), 1 );
    
    complex_number cs01( cs0 ); //test konstruktoru complex_number(complex_number & cn );
    BOOST_CHECK_EQUAL( cs0.real(), 2 );
    BOOST_CHECK_EQUAL( cs0.complex(), 1 );
    
	complex_number ();
	BOOST_CHECK_EQUAL(cs0.real(), 0 );
    BOOST_CHECK( cs0.complex(), 0 );

	complex_number(1);
    BOOST_CHECK_EQUAL( cs0.real(), 1 );
    BOOST_CHECK_EQUAL( cs0.complex(), 0 );

	complex_number(1,'i');
    BOOST_CHECK_EQUAL( cs0.real(), 0 );
    BOOST_CHECK_EQUAL( cs0.complex(), 1 );
}

BOOST_AUTO_TEST_CASE( operations )
{
    complex_number c0();
	complex_number c1(1);
	complex_number c1i(1,1);
	complex_number ci(1,i);
}



// EOF
