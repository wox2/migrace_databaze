
#include "pgr.h"

#include "data.h"
#include "render_stuff.h"

#include <stdlib.h>
#include <iostream>
#include <sstream>

//current spinAngle spinAngle
extern float spinAngle;

// identifier for the buffer object
GLuint vertexBufferObject = 0;
// identifier for the vetexArray object
GLuint vertexArrayObject = 0;

GLuint bufferHouse0 = 0;            //< identifier for the buffer object with vertices
GLuint vertexArrayObjectHouse0 = 0; //< identifier for the vetexArray object


GLuint bufferHouse1 = 0;            ///< identifier for the buffer object with vertices
GLuint colorBufferHouse1 = 0;       ///< identifier for the buffer object with colors
GLuint vertexArrayObjectHouse1 = 0; ///< identifier for the vetexArray object

GLuint bufferHouse2 = 0;            ///< identifier for the buffer object with vertices and colors interlaced
GLuint elementBufferHouse2 = 0;     ///< identifier for the element indices
GLuint vertexArrayObjectHouse2 = 0; ///< identifier for the vetexArray object

// identifier for the program
GLuint theProgram = 0;

// vertex attributes locations
GLint pos_location = -1;            ///< vertex attributes position
GLint color_location = -1;          ///< vertex attributes color
// uniform location
GLint spinMatrix_location = -1;     ///< uniform location

void setRotationMatrix()
{
  glm::mat4 matrix = glm::scale(glm::mat4(1.0f), glm::vec3(0.5f, 0.5f, 0.5f));
  matrix = glm::rotate(matrix, spinAngle / float(M_PI) * 180.0f, glm::vec3(0, 1, 0));

  // Setting the matrix to the vertex shader
  glUniformMatrix4fv(spinMatrix_location, 1, GL_FALSE, glm::value_ptr(matrix));
  return;
}

void checkBuffer(const char * where,  GLuint buffer, GLuint size,  GLenum target, GLenum pname)
{
  GLboolean bufferOK = GL_TRUE;
  GLint value;

  const char *BUFFER;
  if( target == GL_ARRAY_BUFFER )
    BUFFER = "GL_ARRAY_BUFFER ";
  else
    BUFFER = "GL_ELEMENT_ARRAY_BUFFER ";
 
  CHECK_GL_ERROR();

  std::cerr << std::endl;
  std::cerr << "Task " << where << ", buffer object " << buffer <<  ": " << std::endl;


  //  buffer name test - glGenBuffers(1, &bufferHouse0);
  if( buffer == 0 )
  {
    std::cerr << "  Buffer name was not generated (is equal to 0)" << std::endl;
    bufferOK = GL_FALSE;
  }

  if( glIsBuffer(buffer) == GL_FALSE)
  {
    std::cerr << "  Buffer name " << buffer << " does not correspond to a buffer object. " << std::endl;
    bufferOK = GL_FALSE;
  }

  CHECK_GL_ERROR();


  //  buffer binding test - glBindBuffer(GL_ARRAY_BUFFER, bufferHouse0);
  GLint  bufferBinding;
  glGetIntegerv( pname, &bufferBinding );
  CHECK_GL_ERROR();
  if( bufferBinding == 0 )
  {
    std::cerr << BUFFER  << buffer << " is not bound" << std::endl;
    bufferOK = GL_FALSE;
  } 
  else
  {
    if( bufferBinding != buffer )
    {
      std::cerr << BUFFER  << buffer << " is bound wrong, " << bufferBinding << " is bound instead of it" << std::endl;
      bufferOK = GL_FALSE;
    } 
    else
    {
      // GL_BUFFER_SIZE
      glGetBufferParameteriv( target, GL_BUFFER_SIZE,   &value );  //56 pro task 0, GL_INVALID_OOPERATION
      CHECK_GL_ERROR();
      if( value == 0 )
      {
        std::cerr << BUFFER  << buffer << " has no data"  << std::endl;
        bufferOK = GL_FALSE;
      }
      if( value != size )
      {
        std::cerr << BUFFER  << buffer << " size should be " << size  << std::endl;
        bufferOK = GL_FALSE;
      }


      // GL_BUFFER_USAGE
      glGetBufferParameteriv( target, GL_BUFFER_USAGE,  &value );  //35044 GL_STATIC_DRAW
      CHECK_GL_ERROR();
      if( value != GL_STATIC_DRAW )
      {
        std::cerr << BUFFER  << buffer << " usage should be " << "GL_STATIC_DRAW"  << std::endl;
        bufferOK = GL_FALSE;
      }
    }
  }

  if( bufferOK ) 
    std::cerr << "  OK"  << std::endl;


}

#define CHECK_VBO(buffer, size) do { checkBuffer(__FUNCTION__, buffer, size, GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING); } while(0)
#define CHECK_EAO(buffer, size) do { checkBuffer(__FUNCTION__, buffer, size, GL_ELEMENT_ARRAY_BUFFER, GL_ELEMENT_ARRAY_BUFFER_BINDING); } while(0)

void checkVAO( GLuint vertexArray, GLuint posBuffer, GLuint colorBuffer )
{
  GLboolean bufferOK = GL_TRUE;
  GLint value;
 
  CHECK_GL_ERROR();

  std::cerr << std::endl;
  std::cerr << "VertexArrrayObject (VAO) " << vertexArray << ": " << std::endl;


  //  vertexArray name test - glGenVertexArrays()
  if( vertexArray == 0 )
  {
    std::cerr << "  Vertex Array Object was not generated (is equal to 0)" << std::endl;
    bufferOK = GL_FALSE;
  }

  if( glIsVertexArray(vertexArray) == GL_FALSE)
  {
    std::cerr << "  Vertex Array Object " << vertexArray << " does not correspond to any vertex array object " << std::endl;
    bufferOK = GL_FALSE;
  }

  CHECK_GL_ERROR();


  //glBindVertexArray -- to poznat neumim

 
  // bind VBOs
  
  // binding to locations
  // glEnableVertexAttribArray()
  
  //POSITION
  if( posBuffer != 0 )
  {
    std::cerr << "  Location "  << pos_location; 

    glGetVertexAttribiv( pos_location, GL_VERTEX_ATTRIB_ARRAY_ENABLED, &value );   
    if( value == GL_FALSE )
    {
      std::cerr << " is not enabled" << std::endl;
      bufferOK = GL_FALSE;
    }
    else 
      std::cerr << " is enabled" << std::endl;

    glGetVertexAttribiv( pos_location, GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING, &value ); 
    if( value != posBuffer )  // bounded buffer object name
    {
      std::cerr << "    Buffer "  << value << " bounded instead of buffer " << posBuffer << std::endl;
      bufferOK = GL_FALSE;
    }
  }

  //COLOR
  if( colorBuffer != 0 )
  {
    std::cerr << "  Location "  << color_location; 

    glGetVertexAttribiv( color_location, GL_VERTEX_ATTRIB_ARRAY_ENABLED, &value );   
    if( value == GL_FALSE )
    {
      std::cerr << " is not enabled" << std::endl;
      bufferOK = GL_FALSE;
    }
    else 
      std::cerr << " is enabled" << std::endl;

    glGetVertexAttribiv( color_location, GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING, &value ); 
    if( value != colorBuffer )  // bounded buffer object name
    {
      std::cerr << "    Buffer "  << value << " bounded instead of buffer " << colorBuffer << std::endl;
      bufferOK = GL_FALSE;
    }
  }

/*
  glGetVertexAttribiv( pos_location, GL_VERTEX_ATTRIB_ARRAY_SIZE, &value ); 
  glGetVertexAttribiv( pos_location, GL_VERTEX_ATTRIB_ARRAY_STRIDE, &value ); 
  glGetVertexAttribiv( pos_location, GL_VERTEX_ATTRIB_ARRAY_TYPE, &value ); //GL_FLOAT 0x1406
  glGetVertexAttribiv( pos_location, GL_VERTEX_ATTRIB_ARRAY_NORMALIZED, &value ); 
  glGetVertexAttribiv( pos_location, GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING, &value ); 
*/
  
  // todo
  void * ptr;
  glGetVertexAttribPointerv( pos_location, GL_VERTEX_ATTRIB_ARRAY_POINTER, &ptr );

  if( (int*) ptr  != 0 ) {
    std::cerr << "    Buffer offset "<< ((int*) ptr) << " differs from zero"  << std::endl;
    bufferOK = GL_FALSE;
  }

  if( bufferOK ) 
    std::cerr << "  OK"  << std::endl;
}

void InitializeVertexBufferHouse0()  //VBO's with vertices and colors
{
  // ======== BEGIN OF SOLUTION - TASK 1-2 ======== //
  // use the global variable bufferHouse0 to store the buffer id, you have stored vertex data to vertexDataHouse0 
  // buffer with vertices 

  // ========  END OF SOLUTION - TASK 1-2  ======== //

  CHECK_GL_ERROR();
  CHECK_VBO(bufferHouse0, sizeof(vertexDataHouse0));
  glBindBuffer(GL_ARRAY_BUFFER, 0);
}

void InitializeVertexArrayHouse0()   // vertex array object  VAO
{
  // ======== BEGIN OF SOLUTION - TASK 1-3 ======== //
  // use the global variable vertexArrayObjectHouse0 to store the VAO id, attach bufferHouse0 
  // the position attribute id is stored in the pos_location variable 

  // ========  END OF SOLUTION - TASK 1-3  ======== //

  CHECK_GL_ERROR();
  checkVAO( vertexArrayObjectHouse0, bufferHouse0, 0 );
  glBindVertexArray(0);
}

void InitializeVertexBufferHouse1()  //VBO's with vertices and colors
{
  // ======== BEGIN OF SOLUTION - TASK 2-3 ======== //
  // use the global variables bufferHouse1 and colorBufferHouse1 to store the buffer ids 
  // buffer with vertices 

  // ========  END OF SOLUTION - TASK 2-3  ======== //

  CHECK_VBO(bufferHouse1, sizeof(vertexDataHouse1));
  glBindBuffer(GL_ARRAY_BUFFER, 0);

  // ======== BEGIN OF SOLUTION - TASK 2-4 ======== //
  // buffer with colors 

  // ========  END OF SOLUTION - TASK 2-4  ======== //

  CHECK_VBO(colorBufferHouse1, sizeof(vertexColorHouse1));
  glBindBuffer(GL_ARRAY_BUFFER, 0);
  CHECK_GL_ERROR();
}

void InitializeVertexArrayHouse1()   // vertex array object  VAO
{
  // ======== BEGIN OF SOLUTION - TASK 2-5 ======== //
  // use the global variable vertexArrayObjectHouse1 to store the VAO id, attach bufferHouse1 and colorBufferHouse1 
  // the position attribute id is stored in the pos_location variable 
  // the color attribute id is stored in the color_location variable 

  // ========  END OF SOLUTION - TASK 2-5  ======== //

  CHECK_GL_ERROR();
  checkVAO( vertexArrayObjectHouse1, bufferHouse1, colorBufferHouse1 );
  glBindVertexArray(0);
}

void InitializeVertexBufferHouse2()  //VBO's with vertices and colors interlaced
{
  // ======== BEGIN OF SOLUTION - TASK 3-2 ======== //
  // use the global variables bufferHouse2 to store the buffer id 
  // buffer with vertices and colors interlaced 

  // ========  END OF SOLUTION - TASK 3-2  ======== //

  CHECK_GL_ERROR();
  CHECK_VBO(bufferHouse2, sizeof(vertexDataHouse2));
  glBindBuffer(GL_ARRAY_BUFFER, 0);
}

void InitializeElementIndicesHouse3()
{
  // ======== BEGIN OF SOLUTION - TASK 4-2 ======== //
  // use the global variables elementBufferHouse2 to store the buffer id 
  // buffer with element indices 

  // ========  END OF SOLUTION - TASK 4-2  ======== //

  CHECK_GL_ERROR();
  CHECK_EAO(elementBufferHouse2, sizeof(elementDataHouse2));
  glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, 0);
}

// Interlaced vertex data array - used in House 2 and 3
void InitializeVertexArrayHouse2()   // vertex array object  VAO
{
  // ======== BEGIN OF SOLUTION - TASK 3-3 ======== //
  // use the global variable vertexArrayObjectHouse2 to store the VAO id, attach bufferHouse2 and use elementBufferHouse2 
  // the position attribute id is stored in the pos_location variable 
  // the color attribute id is stored in the color_location variable 

  // ========  END OF SOLUTION - TASK 3-3  ======== //

  CHECK_GL_ERROR();
  checkVAO( vertexArrayObjectHouse2, bufferHouse2, bufferHouse2 );
  glBindVertexArray(0);
}

void functionDraw(int taskNumber)
{
  glClear(GL_COLOR_BUFFER_BIT);

  glUseProgram(theProgram);

  // Setting the matrix first
  setRotationMatrix();

  switch(taskNumber)
  {
  case 0: // house by means of GL_LINE_STRIP
    // ======== BEGIN OF SOLUTION - TASK 1-4 ======== //
    // use the VAO you have created before and stored in the vertexArrayObjectHouse0 
    // ========  END OF SOLUTION - TASK 1-4  ======== //
    break;
  case 1:// house by means of GL_TRIANGLE_STRIP
    // ======== BEGIN OF SOLUTION - TASK 2-6 ======== //
    // ========  END OF SOLUTION - TASK 2-6  ======== //
    break;
  case 2: //interlaced array
    // ======== BEGIN OF SOLUTION - TASK 3-4 ======== //
    // ========  END OF SOLUTION - TASK 3-4  ======== //
    break;
  case 3:
    // ======== BEGIN OF SOLUTION - TASK 4-3 ======== //
    // ========  END OF SOLUTION - TASK 4-3  ======== //
    break;
  }

  CHECK_GL_ERROR();
  glBindVertexArray(0);
  glUseProgram(0);
}

void InitializeProgram()
{
  GLuint shaders[] = {
    pgr::createShaderFromSource(GL_VERTEX_SHADER, strVertexShader),
    pgr::createShaderFromSource(GL_FRAGMENT_SHADER, strFragmentShader),
    0
  };
  theProgram = pgr::createProgram(shaders);

  pos_location = glGetAttribLocation(theProgram, "position");
  color_location = glGetAttribLocation(theProgram, "color");
  spinMatrix_location = glGetUniformLocation(theProgram, "spinMatrix");

  glUseProgram(theProgram);
  // this is not correct, but more shaders would be too complicated
  if(pos_location > -1)
    glVertexAttrib2f(pos_location, 0.0f, 0.0f);
  if(color_location > -1)
    glVertexAttrib3f(color_location, 1.0f, 0.0f, 1.0f);

  CHECK_GL_ERROR();
  glUseProgram(0);
}
