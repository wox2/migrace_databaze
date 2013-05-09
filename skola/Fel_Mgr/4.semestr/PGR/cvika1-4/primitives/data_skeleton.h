#ifndef DATA_H
#define DATA_H

#include <string>
#include <vector>

/// Half of house depth (house size along the z-axis).
const float z = 0.6f;

// For the first task

// dva vstupni atributy shaderu - vec4 = datovy typ vektor 4 floatu
const std::string strVertexShader0(
	"#version 130\n"
	"uniform mat4 spinMatrix;\n"
	"in vec4 position;\n"
	"void main()\n"
	"{\n"
	"	gl_Position = spinMatrix * position;\n"
	"}\n"
);

const std::string strFragmentShader0(
	"#version 130\n"
	"out vec4 outputColor;\n"
	"void main()\n"
	"{\n"
	"	outputColor = vec4(1.0f, 1.0f, 1.0f, 1.0f);\n"
	"}\n"
);


// For tasks 2,3,4 - multiple colors and matrix multiplication
const std::string strVertexShader123(
	"#version 130\n"
	"uniform mat4 spinMatrix;\n"
	"in vec4 position;\n"
	"in vec4 color;\n"
	"smooth out vec4 theColor;\n"	
	"void main()\n"
	"{\n"
	"	gl_Position = spinMatrix * position;\n"
	"	theColor = color;\n"
	"}\n"
);

const std::string strFragmentShader123(
	"#version 130\n"
	"smooth in vec4 theColor;\n"
	"out vec4 outputColor;\n"
	"void main()\n"
	"{\n"
	"	outputColor = theColor;\n"
	"}\n"
);

  //	   C   F I     //	   4   10 16  //	   5   11 17
  //      /  \ G |     //     /  \ 12 |   //      /  \ 13 |
  //     /    \	 H     //    /    \	 14   //     /    \   15
  //	A------B       //	0------2      //	3------1
  //    |      |       //   |      |      //    |      |
  //	|      |       //	|      |      //	|      |
  //	D------E       //	6------8      //	9------7

const unsigned int vertexCountHouse0 = 7;				// number of vertices in this array
const float vertexDataHouse0[vertexCountHouse0][2] = {	// repeated points A abd B
  // =============================== BEGIN OF SOLUTION - TASK 1 ======================================
		// roof 0..2
		{ -0.6f,  0.6f,},	// B	 
		{ -0.6f,  -0.6f,},
		{ 0.6f,  -0.6f,},
		{ 0.6f,  0.6f,},
		{ 0.0f,  0.9f,},
		{ -0.6f,  0.6f,},
		{ 0.6f,  0.6f,}
		// wall 3..6
  // =============================== END OF SOLUTION - TASK 1 ==================================
};

// LINE_LOOP ABC, ADEB, FGHI
// TRIANGLE_STRIP CAB, ABDE, FGIH
const unsigned int vertexCountHouse1 = 11;				// number of vertices in this array
const float vertexDataHouse1[vertexCountHouse1][2] = {	// repeated points A abd B
  // =============================== BEGIN OF SOLUTION - TASK 2 ==================================
	{-0.6f,0.5f},	
	{0.6f,0.5f},	
	{0.0f,0.9f},	

	{-0.3f,-0.5f},	
	{0.3f,-0.5f},	
		{-0.3f,0.5f},
	{0.3f,0.5f},		

	{-0.6f,-0.5f},	
	{0.6f,-0.5f},	
	{-0.55f,-0.7f},
	{0.65f,-0.7f}
	
	// roof 0..2
		// wall 3..6
		// chimney 7..10
 };


const float vertexColorHouse1[vertexCountHouse1][3] =
{
	// roof 0..2
	//   r     g     b
	{1.0f,0.0f, 0.0f},	// 0 - 255 se mapuje do 0 - 1 ve float
	{1.0f,0.0f, 0.0f},	
	{1.0f,0.0f, 0.0f},	

	{1.0f,1.0f, 0.0f},	
	{1.0f,1.0f, 0.0f},	
	{1.0f,1.0f, 0.0f},	
	{1.0f,1.0f, 0.0f},	

	{0.0f,0.0f, 1.0f},	
	{0.0f,0.0f, 1.0f},	
	{0.0f,0.0f, 1.0f},	
	{0.0f,0.0f, 1.0f}
	// wall 3..6

	// chimney 7..10
// =============================== END OF SOLUTION - TASK 2 ==================================
};

// -------------------------------------------------------------------
const unsigned int vertexCountHouse2 = 11;				// number of vertices in this array
const float vertexDataHouse2[vertexCountHouse2][5] = {	// repeated points A abd B
// =============================== BEGIN OF SOLUTION - TASK 3 ==================================
		// roof 0..2
	    //   x      y     r     g     b
	{-0.6f,0.5f, 1.0f, 0.0f, 0.0f},	
	{ 0.6f,0.5f, 1.0f, 0.0f, 0.0f},		
	 {0.0f,0.9f, 1.0f, 0.0f, 0.0f},		

	{-0.3f,-0.5f, 1.0f, 1.0f, 0.0f},	
	 {0.3f,-0.5f, 1.0f, 1.0f, 0.0f},		
    {-0.3f, 0.5f, 1.0f, 1.0f, 0.0f},	
	{ 0.3f ,0.5f, 1.0f, 1.0f, 0.0f},			

	{-0.6f,- 0.5f, 0.0f, 0.0f, 1.0f},	
	{ 0.6f,- 0.5f, 0.0f, 0.0f, 1.0f},	
	{-0.55f,-0.7f, 0.0f, 0.0f, 1.0f},	
	{ 0.65f,-0.7f, 0.0f, 0.0f, 1.0f}
	
		
// =============================== END OF SOLUTION - TASK 3 ==================================
};

const unsigned int elementCountHouse2 = 11;
const unsigned char elementDataHouse2[elementCountHouse2] =
{
	0,1,2,
	3,4,5,6,
	7,8,9,10
};

// kreslime pomoci triangle strip, takze musime jit po uhloprickach, prvni stenu
// definujeme pomoci 4 vrcholu a ty ostatni uz jen pomoci dvou prave diky triangle strip

const unsigned int vertexCountHouse3 = 24;				// number of vertices in this array
const float vertexDataHouse3[vertexCountHouse3][6] = {	// repeated points A abd B
// =============================== BEGIN OF SOLUTION - TASK 4 ==================================
	//   x      y    z      r     g     b
    {-0.6f,-0.6f, 0.6,    0.0f, 0.0f, 1.0f},	// predni stena
	{-0.6f, 0.6f, 0.6,    0.0f, 0.0f, 1.0f},		
	{ 0.6f,-0.6f, 0.6,    0.0f, 0.0f, 1.0f},
	{ 0.6f, 0.6f, 0.6,    0.0f, 0.0f, 1.0f},

	{ 0.6f,-0.6f,-0.6,    0.0f, 0.0f, 1.0f},    // prava stena
	{ 0.6f, 0.6f,-0.6,    0.0f, 0.0f, 1.0f},

	{-0.6f,-0.6f,-0.6,    0.0f, 0.0f, 1.0f},    // zadni stena
	{-0.6f, 0.6f,-0.6,    0.0f, 0.0f, 1.0f},

	{-0.6f,-0.6f, 0.6,    0.0f, 0.0f, 1.0f},    // leva stena
	{-0.6f, 0.6f, 0.6,    0.0f, 0.0f, 1.0f},

	{-0.6f, 0.6f, 0.6,    1.0f, 0.0f, 0.0f},	// strecha predek
	{ 0.6f, 0.6f, 0.6,    1.0f, 0.0f, 0.0f},		
	{ 0.0f, 0.9f, 0.6,    1.0f, 0.0f, 0.0f},

	{-0.6f, 0.6f,-0.6,    1.0f, 0.0f, 0.0f},	// strecha zadni
	{ 0.6f, 0.6f,-0.6,    1.0f, 0.0f, 0.0f},		
	{ 0.0f, 0.9f,-0.6,    1.0f, 0.0f, 0.0f},

	{-0.6f, 0.6f, 0.6,    1.0f, 0.0f, 0.0f},    // strecha levy bok	
	{ 0.0f, 0.9f, 0.6,    1.0f, 0.0f, 0.0f},		
	{ 0.0f, 0.9f,-0.6,    1.0f, 0.0f, 0.0f},
	{-0.6f, 0.6f,-0.6,    1.0f, 0.0f, 0.0f},

	{ 0.6f, 0.6f, 0.6,    1.0f, 0.0f, 0.0f},	// strecha pravy bok
	{ 0.0f, 0.9f, 0.6,    1.0f, 0.0f, 0.0f},		
	{ 0.0f, 0.9f,-0.6,    1.0f, 0.0f, 0.0f},
	{ 0.6f, 0.6f,-0.6,    1.0f, 0.0f, 0.0f}


	
		
// =============================== END OF SOLUTION - TASK 4 ==================================
};

const unsigned int elementCountHouse3 = 24;
const unsigned char elementDataHouse3[elementCountHouse3] =
{
	0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23
};


#endif DATA_H