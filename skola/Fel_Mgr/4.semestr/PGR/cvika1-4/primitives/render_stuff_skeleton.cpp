#include <GL/glew.h>
#include <GL/glut.h>
#include "pgr.h" // support classes for PGR
#include "data_skeleton.h"
#include "render_stuff.h"

//current spinAngle spinAngle
extern float spinAngle;

// identifier for the buffer object
GLuint vertexBufferObject = 0;
// identifier for the vetexArray object
GLuint vertexArrayObject = 0;

GLuint bufferHouse0 = 0;		//< identifier for the buffer object with vertices
GLuint vertexArrayObjectHouse0 = 0;	//< identifier for the vetexArray object 


GLuint bufferHouse1 = 0;		//< identifier for the buffer object with vertices
GLuint colorBufferHouse1 = 0; //< identifier for the buffer object with colors
GLuint vertexArrayObjectHouse1 = 0;	//< identifier for the vetexArray object 

GLuint bufferHouse2 = 0;		//< identifier for the buffer object with vertices and colors interlaced
GLuint elementBufferNameHouse2 = 0;		//< identifier for the element indices 
GLuint vertexArrayObjectHouse2 = 0;	//< identifier for the vetexArray object 

GLuint bufferHouse3 = 0;
GLuint elementBufferNameHouse3 = 0;
GLuint vertexArrayObjectHouse3 = 0;



// identifier for the program
GLuint theProgram = 0;
// vertex attributes locations
GLint pos_location = -1;          ///< vertex attributes position
GLint color_location = -1;        ///< vertex attributes color
// uniform location
GLint spinMatrix_location = -1;   ///< uniform location


// -------------------------------------------------------------------
// Rotation around Y axis

void setRotationMatrix()
{


	pgr::Matrix4f matrix = pgr::Matrix4f::FromScale(pgr::Vec3f(0.5f, 0.5f, 0.5f));
	matrix.rotate(spinAngle, pgr::Vec3f(0, 1, 0));

	// Setting the matrix to the vertex shader
	glUniformMatrix4fv(spinMatrix_location, 1, GL_FALSE, matrix);
	return;
}


// -------------------------------------------------------------------

//void InitializeVertexBuffer()
//{
//	glGenBuffers(...
//
//	glBindBuffer(...
//	  glBufferData(...
//	glBindBuffer(GL_ARRAY_BUFFER, 0);
//}

// -------------------------------------------------------------------

//void InitializeVertexArray()   // vertex array object  VAO
//{
//	glGenVertexArrays( ...
//	glBindVertexArray( ...
//	
//		glBindBuffer(GL_ARRAY_BUFFER, ...
//			glEnableVertexAttribArray(pos_location);
//			//glEnableVertexAttribArray(color_location);
//			// vertices of triangles - start at the beginning of the array
//			glVertexAttribPointer(pos_location, ...
//			// colors of vertices start after the positions
//			//glVertexAttribPointer(color_location, ...
//		glBindBuffer(GL_ARRAY_BUFFER, 0);
//
//	glBindVertexArray(0);
//}
// -------------------------------------------------------------------

void InitializeVertexBufferHouse0()  //VBO's with vertices and colors
{   
  // =============================== BEGIN OF SOLUTION - TASK 1 ==================================
	glGenBuffers(1, &bufferHouse0);  // rekneme si o identifikator bufferu, 1 znamena, ze chceme 1 identifikator, druhy parametr je ukazatel, do ktereho se ulozi ID
	glBindBuffer(GL_ARRAY_BUFFER, bufferHouse0); // prepneme se na buffer house0, budou v nem ulozena data ARRAY (stejnych hodnot)
	 
	// buffer se muze ulozit POUZE do pameti GPU nebo CPU, pouzijeme STREAM, pokud se bude menit pri kazdem prekresleni, DRAW znamena, ze se z ni bude pouze CIST (STATIC_DRAW)
	glBufferData(GL_ARRAY_BUFFER, sizeof(vertexDataHouse0), vertexDataHouse0, GL_STATIC_DRAW);  // vyplnime buffer polem vertexDataHouse0, posledni parametr je napoveda, kam ho ulozit (jak casto ho budeme menit) 
	 

  // =============================== END OF SOLUTION - TASK 1 ==================================
	glBindBuffer(GL_ARRAY_BUFFER, 0);
};

// -------------------------------------------------------------------
// Vertex Aray Objects (VAO) hold ONLY the STATE 
// They do not store the vertices attributes!!!
void InitializeVertexArrayHouse0()   // vertex array object  VAO
{
	// ten buffer se musi napojit na vstupni atributy shaderu - zatim mame jen pozici
	// pokud chceme kreslit 5 ruznych domecku, udelame si 5 ruznych arrayObjectu -> arrayObject zapouzdruje ten buffer
  // =============================== BEGIN OF SOLUTION - TASK 1 ==================================
	glGenVertexArrays(1, &vertexArrayObjectHouse0); //dame ID do vertexArrayObjectHouse
	glBindVertexArray(vertexArrayObjectHouse0); // prepneme vertex na house0
	
		glBindBuffer(GL_ARRAY_BUFFER, bufferHouse0); // prepneme buffer na house0

	    // povolime pozicovani vrcholu, jehoz atribut je v pos_location
		// pokud to povolime, muzeme pripojit pole a vykreslit ho
		glEnableVertexAttribArray(pos_location); 


		// navazeme atribut na nase vstupni pole (na buffer)
		// atribut s indexem pos_location bude tvoren 2 floaty, souradnice nebudou normalizovany (rozsah 0-1) - u pozic nema smysl,
		// posledni parametr je offset bufferu, odkud budeme zacinat zpracovavat data, pokud bychom chteli zacit od 2, museli
		// bychom napsat 2*sizeof(float)
		 glVertexAttribPointer(pos_location, 2, GL_FLOAT, GL_FALSE, sizeof(GLfloat)*2, (void*)0);


	glBindBuffer(GL_ARRAY_BUFFER, 0);

  // =============================== END OF SOLUTION - TASK 1 ==================================
	glBindVertexArray(0);
}


// -------------------------------------------------------------------

void InitializeVertexBufferHouse1()  //VBO's with vertices and colors
{   
  // =============================== BEGIN OF SOLUTION - TASK 2 ==================================


	// buffery musi byt dva
	// buffer na vrcholy
		glGenBuffers(1, &bufferHouse1); // chceme 1 identifikator, ktery ulozime do bufferHouse1
	glBindBuffer(GL_ARRAY_BUFFER, bufferHouse1); // vytvorime buffer pozic

	// v modu static_draw chceme zaregistrovat array_buffer o velikosti vertexDataHouse1, jehoz data jsou ulozeny prave v tomto poli
	// to sizeof je tady z toho duvodu, ze C++ obecne neumi zjistovat delku pole
	 glBufferData(GL_ARRAY_BUFFER, sizeof(vertexDataHouse1), vertexDataHouse1, GL_STATIC_DRAW);
	 glBindBuffer(GL_ARRAY_BUFFER, 0);
	
	 // buffer na barvicky, to same
	glGenBuffers(1, &colorBufferHouse1); 
	glBindBuffer(GL_ARRAY_BUFFER, colorBufferHouse1); // vytvorime buffer barev
	glBufferData(GL_ARRAY_BUFFER, sizeof(vertexColorHouse1), vertexColorHouse1, GL_STATIC_DRAW);

	// tohle by melo byt po kazde inicializace -> pokud se nekde zapomeneme prepnout na jiny buffer, budeme pracovat porad
	// s timto, pak by bylo tezke poznat, kde nastala chyba
	glBindBuffer(GL_ARRAY_BUFFER, 0);

	 // nyni mame data v kontejneru, jeste ale nevime, jak je vykreslit --> to je v nasledujici metode

 // =============================== END OF SOLUTION - TASK 2 ==================================

}

// -------------------------------------------------------------------
// Vertex Aray Objects (VAO) hold ONLY the STATE 
// They do not store the vertices attributes!!!
void InitializeVertexArrayHouse1()   // vertex array object  VAO
{
  // =============================== BEGIN OF SOLUTION - TASK 2 ==================================
	 
	 // vygenerujeme si identifikator na VAO -> ten bude pouzivat VBO tak, ze bude vedet, co a jak vykreslit
	glGenVertexArrays(1, &vertexArrayObjectHouse1); 
	glBindVertexArray(vertexArrayObjectHouse1); // prepneme se na nej

    // VAO bude v sobe obsahovat odkazy na 2 buffery - buffer vrcholu a buffer barev

	// bufferHouse1 je uz vytvoreny ve funkci nahore takze ho nemusime vytvaret ani bindovat,
	// staci se na nej pouze prepnout
	glBindBuffer(GL_ARRAY_BUFFER, bufferHouse1); // pripojime buffer vrchol

	// timhle rekneme programu, ze VertexArray na indexu pos_location bude smet pouzivat
	glEnableVertexAttribArray(pos_location); 
	
	// ted mame nejaky vertexArray, ktery bude mit index pos_location
	// bude mit 2 rozmery, kazdy bude udavan v GL_FLOAT, GL_FALSE = nechceme normalizovat
	// ta prvni nula znamena, ze tam neni zadny offset mezi jednotlivymi vertex objekty
	// ta druha nula znamena, ze prvni vertex objekt bude zacinat na indexu nula
	glVertexAttribPointer(pos_location, 2, GL_FLOAT, GL_FALSE, 0, 0); 

	// to same pro barvy
	glBindBuffer(GL_ARRAY_BUFFER, colorBufferHouse1);
	glEnableVertexAttribArray(color_location); // zapneme atribut shaderu, na ktery se buffer bude mapovat
    glVertexAttribPointer(color_location, 3, GL_FLOAT, GL_FALSE,0, 0); // atribut bude napojen tak, ze kazda trojice floatu bude barvou


	glBindBuffer(GL_ARRAY_BUFFER, 0);
 // =============================== END OF SOLUTION - TASK 2 ==================================
	glBindVertexArray(0);
}


// -------------------------------------------------------------------
const float elementDataHouse[] = {0,1,5,6,10,11}; // tohle nevim, proc to tady je

void InitializeVertexBufferHouse2()  //VBO's with vertices and colors interlaced
{   
 // =============================== BEGIN OF SOLUTION - TASK 3 ==================================
	

	glGenBuffers(1, &bufferHouse2); // vygenerujeme ID bufferHouse2
	glBindBuffer(GL_ARRAY_BUFFER, bufferHouse2); // prepneme se na nej

	// mame pole o 11 prvcich, kazdy prvek obsahuje 5 souradnic, na toto pole ukazuje vertexDataHouse2
	// timhle tedy vlozime data do bufferu
    glBufferData(GL_ARRAY_BUFFER, sizeof(GLfloat)*5*vertexCountHouse2, vertexDataHouse2, GL_STATIC_DRAW);
    glBindBuffer(GL_ARRAY_BUFFER, 0); // prepneme na nulovy buffer (pro jistotu)


	// buffer s indexy elementu - v nem bude jen deklarovano, na kterych indexech toho pole nahore
	// zacinaji jednotlive vrcholy, jsou to indexy 0, 1, 2, 3,.....
	glGenBuffers(1,&elementBufferNameHouse2); // opet vygenerujeme id a prepneme se na nej
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, elementBufferNameHouse2);

	// deklarujeme elementy
	glBufferData(GL_ELEMENT_ARRAY_BUFFER,sizeof(elementDataHouse2), elementDataHouse2, GL_STATIC_DRAW);
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);
    

// =============================== END OF SOLUTION - TASK 3 ==================================

}

void InitializeElementIndicesHouse3()
{
// =============================== BEGIN OF SOLUTION - TASK 4 ==================================

// Sloup sem dal jen jednu metodu - nevim proc, tak tu vytvorime VBO i VAO najednou

// je to temer stejne jako u prikladu 3, akorát jsou zde souradnice X,Y,Z a jine offsety
// VBO:::::
	glGenBuffers(1, &bufferHouse3); 
	glBindBuffer(GL_ARRAY_BUFFER, bufferHouse3); 
    glBufferData(GL_ARRAY_BUFFER, sizeof(GLfloat)*6*vertexCountHouse3, vertexDataHouse3, GL_STATIC_DRAW);
    glBindBuffer(GL_ARRAY_BUFFER, 0); 

	glGenBuffers(1,&elementBufferNameHouse3); 
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, elementBufferNameHouse3);
	glBufferData(GL_ELEMENT_ARRAY_BUFFER,sizeof(elementDataHouse3), elementDataHouse3, GL_STATIC_DRAW);
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);


// VAO:::::

	glGenVertexArrays(1,&vertexArrayObjectHouse3); 
	glBindVertexArray(vertexArrayObjectHouse3); 
	glBindBuffer(GL_ARRAY_BUFFER, bufferHouse3); 
	glEnableVertexAttribArray(pos_location); 
	glEnableVertexAttribArray(color_location); 
	glVertexAttribPointer(pos_location,3,GL_FLOAT,GL_FALSE,  6*sizeof(float), (void*)0);
	glVertexAttribPointer(color_location,3,GL_FLOAT,GL_FALSE,6*sizeof(float),(void*)(3*sizeof(float)));

	// uklizeni
	glBindBuffer(GL_ARRAY_BUFFER,0);
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0); 
	glBindVertexArray(0);
    

// =============================== END OF SOLUTION - TASK 4 ==================================

}

// Interlaced vertex data array - used in House 2 and 3
void InitializeVertexArrayHouse2()   // vertex array object  VAO
{
// =============================== BEGIN OF SOLUTION - TASK 3 ==================================
	
	glGenVertexArrays(1,&vertexArrayObjectHouse2); // vygenerujeme VAO
	glBindVertexArray(vertexArrayObjectHouse2); // prepneme se na VAO
	glBindBuffer(GL_ARRAY_BUFFER, bufferHouse2); // prepneme se na buffer
	glEnableVertexAttribArray(pos_location); // povolime indexu na pos_location
	glEnableVertexAttribArray(color_location); // povolime pole na indexu color_location

	// rekneme, ze atribut na indexu pos_location bude mit 2 souradnice
	// a JEDNOTLIVE OBJEKTY JSOU OD SEBE ODDELENY 5*sizeof(float), tzn. prvni objekt zacina
	// na indexu 0, dalsi na indexu 0+5 atd.
	glVertexAttribPointer(pos_location,2,GL_FLOAT,GL_FALSE,  5*sizeof(float), (void*)0);
	// to same pro barvy s tim rozdilem, ze kazda barva zacina od offsetu 2*sizeof(float) -> posledni parametry
	glVertexAttribPointer(color_location,3,GL_FLOAT,GL_FALSE,5*sizeof(float),(void*)(2*sizeof(float)));

	// uklizeni
	glBindBuffer(GL_ARRAY_BUFFER,0);
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0); 
	
				  
 // =============================== END OF SOLUTION - TASK 3 ==================================

	glBindVertexArray(0);
}




void functionDraw(int taskNumber)
{
	glClearColor(0.3f, 0.3f, 0.3f, 1.0f);
	glClear(GL_COLOR_BUFFER_BIT);

	glUseProgram(theProgram);

	// Setting the matrix first
	setRotationMatrix(); 
	
	switch(taskNumber)
	{
	case 0: // house by means of GL_LINE_STRIP
  // =============================== BEGIN OF SOLUTION - TASK 1 ======================================

	 glBindVertexArray(vertexArrayObjectHouse0); // musime pripojit vertex array object
     glDrawArrays(GL_LINE_STRIP, 0,vertexCountHouse0); // vykreslime tento object

  // =============================== END OF SOLUTION - TASK 1 ==================================
	glBindVertexArray(0); 
		break;
	case 1:// house by means of GL_TRIANGLE_STRIP
		glBindVertexArray( vertexArrayObjectHouse1 );
 // =============================== BEGIN OF SOLUTION - TASK 2 ======================================
		
	
     glDrawArrays(GL_TRIANGLES, 0,3); // zacina se na vrcholu 0 a zpracuji se 3 vrcholy
	 glDrawArrays(GL_TRIANGLE_STRIP, 3,4); // zacina se na vrcholu 3 a zpracuji se 4 vrcholy
	 glDrawArrays(GL_TRIANGLE_STRIP, 7,4); // zacina na vrcholu 7 a zpracuji se 4 vrcholy
	   
 // =============================== END OF SOLUTION - TASK 2 ======================================
		glBindVertexArray(0); 
		break;
	case 2: //interlaced array
 // =============================== BEGIN OF SOLUTION - TASK 3 ======================================
		
		// roof + wall

		glBindVertexArray(vertexArrayObjectHouse2);  // prepneme na objekt house2
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,elementBufferNameHouse2); // prepneme se take na elementArray, jinak by to nefungovalo!!

		// vertexArrayObjectHouse2 uz vi, kde jsou data a kde jsou barvy, staci mu jen rict, odkud se ma vykreslovat

		// pozor, tyto indexy nejsou indexy pole vertexDataHouse2, ale elementDataHouse2, kde jsou ulozeny indexy prvku
		// prave v poli vertexDataHouse

		// kreslime triangle_strip, 3 vrcholy, zaciname od nuly...pote od 3, od 7. indexu
		glDrawElements(GL_TRIANGLE_STRIP,3,GL_UNSIGNED_BYTE,0);  // vykreslime to, co zacina od nuly (strecha)
		glDrawElements(GL_TRIANGLE_STRIP,4,GL_UNSIGNED_BYTE,(void*)(3)); // obdelnik domecku zacina od 3
		glDrawElements(GL_TRIANGLE_STRIP,4,GL_UNSIGNED_BYTE,(void*)7);  // podlaha
		// chimney
 // =============================== END OF SOLUTION - TASK 3 ======================================
		glBindVertexArray(0); 
		break;
	case 3:
 // =============================== BEGIN OF SOLUTION - TASK 4 ======================================
		
		glBindVertexArray(vertexArrayObjectHouse3);  // prepneme na objekt house3
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,elementBufferNameHouse3); 

		glDrawElements(GL_TRIANGLE_STRIP,10,GL_UNSIGNED_BYTE,0); // vykresleni domu
		glDrawElements(GL_TRIANGLE_STRIP,14,GL_UNSIGNED_BYTE,(void*)10);  // vykresleni strechy

 // =============================== END OF SOLUTION - TASK 4 ======================================
		glBindVertexArray(0); 
		break;
	}
	  
	glUseProgram(0);
}

// --------------------------------------------------
// General functions

void InitializeProgram(int taskNumber)
{
	std::vector<GLuint> shaderList;

	switch( taskNumber )
	{
		case 0:
			// Push vertex shader and fragment shader
			shaderList.push_back(pgr::createShader(GL_VERTEX_SHADER, strVertexShader0));
			shaderList.push_back(pgr::createShader(GL_FRAGMENT_SHADER, strFragmentShader0));
			break;
		case 1:
		case 2:
		case 3:
			// Push vertex shader and fragment shader
			shaderList.push_back(pgr::createShader(GL_VERTEX_SHADER, strVertexShader123));
			shaderList.push_back(pgr::createShader(GL_FRAGMENT_SHADER, strFragmentShader123));
			break;
	}

	// Create the program with two shaders
	theProgram = pgr::createProgram(shaderList);

	// get attributes and uniform locations
	pos_location = glGetAttribLocation(theProgram, "position");
	if( taskNumber != 0 )
  		color_location = glGetAttribLocation(theProgram, "color");
	spinMatrix_location = glGetUniformLocation(theProgram, "spinMatrix"); // musi byt uniformni!!!, nenastavuje se to pres buffery ale PRIMO, k tomu slouzi prikazy glUniform...
}



