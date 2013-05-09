
#include <stdlib.h>
#include <iostream>
#include <sstream>

#include "pgr.h"

namespace pgr {

bool initialize(int glVerMajor, int glVerMinor)
{
  // we have to sate experimental to work in forward comp mode
  glewExperimental = GL_TRUE;
  if(glewInit() != GLEW_OK)
  {
    std::cerr << "glew init failed (right context not created?)" << std::endl;
    return false;
  }
  GLenum err = glGetError();
  // silently ignore error caused by glewInit() - happens in forward comp.
  //if(err != GL_NONE)
  //  std::cerr << "glErr in glewInit" << std::endl;
  
  std::stringstream vers;
  vers << "GL_VERSION_" << glVerMajor << "_" << glVerMinor;
  if(!glewIsSupported(vers.str().c_str()))
  {
    std::cerr << "OpenGL " << glVerMajor << "." << glVerMinor << " or higher not available" << std::endl;
    return false;
  }

  // initialize DevIL
  ilInit();

  std::cout << "pgr: initialized, using OpenGL " << glVerMajor << "." << glVerMinor << std::endl;
  return true;
}

std::string frameworkRoot()
{
  char * root = getenv("PGR_FRAMEWORK_ROOT");
  return root ? root : "";
}

void dieWithError(const std::string & errMsg)
{
  #if defined(_WIN32) || defined(WIN32)
  MessageBoxA(NULL, errMsg.c_str(), "Fatal error", MB_ICONERROR | MB_OK);
  #else
  // will we show some nice graphic error messages on X11/Masox?
  std::cerr << "Fatal error: " << errMsg << std::endl;
  #endif
  exit(1);
}

void checkGLError(const char *where, int line)
{
  GLenum err = glGetError();
  if(err == GL_NONE)
    return;

  std::string errString = "<unknown>";
  switch(err)
  {
    case GL_INVALID_ENUM:
      errString = "GL_INVALID_ENUM";
      break;
    case GL_INVALID_VALUE:
      errString = "GL_INVALID_VALUE";
      break;
    case GL_INVALID_OPERATION:
      errString = "GL_INVALID_OPERATION";
      break;
    case GL_INVALID_FRAMEBUFFER_OPERATION:
      errString = "GL_INVALID_FRAMEBUFFER_OPERATION";
      break;
    case GL_OUT_OF_MEMORY:
      errString = "GL_OUT_OF_MEMORY";
      break;
    default:;
  }
  if(where == 0 || *where == 0)
    std::cerr << "GL error occurred: " << errString << std::endl;
  else
    std::cerr << "GL error occurred in " << where << ":" << line << ": " << errString << std::endl;
}

} // end namespace pgr
