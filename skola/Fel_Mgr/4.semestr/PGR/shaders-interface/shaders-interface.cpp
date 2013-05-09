/**
 * \file shaders-interface.cpp
 * \brief Shader interfaces practice.
 * \author Tomas Barak
 */

#include <cmath>
#include <iostream>
#include <string>

#include "pgr.h"
#include "ogl_types.h" // translation table for opengl types

const int WIN_WIDTH = 800;
const int WIN_HEIGHT = 500;
const char * WIN_TITLE = "PGR - Shaders Interface";
const unsigned int REFRESH_INTERVAL = 33;
double elapsedTime = 0.0;
glm::mat4 projection = glm::mat4(1.0f);
glm::mat4 model = glm::mat4(1.0f);
GLuint cubeShaderProgram = 0;
GLuint cubeVbo = 0;
GLuint cubePtsVao = 0;
GLuint cubeLinesEao = 0;
GLuint cubeLinesVao = 0;
GLuint cubeTrianglesEao = 0;
GLuint cubeTrianglesVao = 0;
GLenum displayMode = GL_LINES;

// interleaved array of cube vertices
const unsigned nCubeVertices = 8;
const unsigned nCubeAttribsPerVertex = 6; // x,y,z position, r, g, b color
const float cubeVertices[nCubeVertices * nCubeAttribsPerVertex] = {
// x      y      z       r     g     b
  -1.0f, -1.0f, -1.0f,   0.0f, 0.0f, 0.0f, // 0
  -1.0f, -1.0f,  1.0f,   0.0f, 0.0f, 1.0f, // 1
  -1.0f,  1.0f, -1.0f,   0.0f, 1.0f, 0.0f, // 2
  -1.0f,  1.0f,  1.0f,   0.0f, 1.0f, 1.0f, // 3
   1.0f, -1.0f, -1.0f,   1.0f, 0.0f, 0.0f, // 4
   1.0f, -1.0f,  1.0f,   1.0f, 0.0f, 1.0f, // 5
   1.0f,  1.0f, -1.0f,   1.0f, 1.0f, 0.0f, // 6
   1.0f,  1.0f,  1.0f,   1.0f, 1.0f, 1.0f, // 7
};

// indices used to draw cube as GL_LINES
const unsigned nCubeLineIdx = 24;
const unsigned short cubeLineIdx[nCubeLineIdx] = {
  0, 4, // lower face
  4, 5,
  5, 1,
  1, 0,
  2, 6, // upper face
  6, 7,
  7, 3,
  3, 2,
  0, 2, // side lines
  4, 6,
  1, 3,
  5, 7
};

// indices used to draw cube as GL_TRIANGLES
const unsigned nCubeTriangleIdx = 36;
const unsigned short cubeTriangleIdx[nCubeTriangleIdx] = {
  0, 2, 4,   4, 2, 6, // back face
  0, 1, 2,   2, 1, 3, // left face
  1, 5, 3,   3, 5, 7, // front face
  5, 4, 7,   7, 4, 6, // right face
  3, 7, 2,   2, 7, 6, // upper face
  0, 4, 1,   1, 4, 5, // lower face
};

std::string vertexShaderSrc =
  "#version 140\n"
  "uniform mat4 matrix;\n"
  "in vec3 vertex;\n"
  "in vec3 color;\n"
  "out vec3 color_v;\n"
  "void main()\n"
  "{\n"
  "  color_v = color;\n"
  "  gl_Position = matrix * vec4(vertex, 1.0f);\n"
  "}\n"
;

std::string fragmentShaderSrc =
  "#version 140\n"
  "in vec3 color_v;\n"
  "out vec3 color_f;\n"
  "void main()\n"
  "{\n"
  "  color_f = color_v;\n"
  "}\n"
;

// ======== BEGIN OF SOLUTION - TASK 1-1 ======== //
// design and implement structure describing one vertex attribute (name it VertexAttrib)
// implement function for generating vertex array objects, you CAN use the following signature:
// GLuint createVAO(GLuint shaderProgram, int numAttribs, const VertexAttrib * attribs, GLuint elementBuffer = 0);
// place all newly created code in your namespace (equal to your university login)
namespace YOUR_LOGIN
{
// ========  END OF SOLUTION - TASK 1-1  ======== //

// ======== BEGIN OF SOLUTION - TASK 3-1 ======== //
/// this function dumps useful information about given shader program interface
void printShaderInfo(GLuint program)
{
  // TODO: implement this, print at least all active attributes and uniforms
  // HINT: checkout glGetProgramiv, glGetActive{Uniform,Attrib} functions
}
// ========  END OF SOLUTION - TASK 3-1  ======== //

} // end namespace

void init()
{
  // opengl setup
  glClearColor(0.3f, 0.3f, 0.3f, 1.0f);
  glEnable(GL_CULL_FACE);
  glEnable(GL_DEPTH_TEST);
  glPointSize(5.0f);

  // load shader
  std::vector<GLuint> shaders;
  shaders.push_back(pgr::createShaderFromSource(GL_VERTEX_SHADER, vertexShaderSrc));
  shaders.push_back(pgr::createShaderFromSource(GL_FRAGMENT_SHADER, fragmentShaderSrc));
  cubeShaderProgram = pgr::createProgram(shaders);
  // ======== BEGIN OF SOLUTION - TASK 3-2 ======== //
  // call your printShaderInfo function on cubeShaderProgram to obtain some information about the shader
  // ========  END OF SOLUTION - TASK 3-2  ======== //

  // buffer for vertices
  glGenBuffers(1, &cubeVbo);
  glBindBuffer(GL_ARRAY_BUFFER, cubeVbo);
  glBufferData(GL_ARRAY_BUFFER, sizeof(cubeVertices), cubeVertices, GL_STATIC_DRAW);
  glBindBuffer(GL_ARRAY_BUFFER, 0);

  // buffer for line indices
  glGenBuffers(1, &cubeLinesEao);
  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, cubeLinesEao);
  glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(cubeLineIdx), cubeLineIdx, GL_STATIC_DRAW);
  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

  // buffer for triangle indices
  glGenBuffers(1, &cubeTrianglesEao);
  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, cubeTrianglesEao);
  glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(cubeTriangleIdx), cubeTriangleIdx, GL_STATIC_DRAW);
  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

  // ======== BEGIN OF SOLUTION - TASK 2-1 ======== //
  // get rid of this tedious code, use your newly designed approach (createVAO + array of VertexAttrib structures)
  // handles to vertex shader inputs
  GLint vertexLoc = glGetAttribLocation(cubeShaderProgram, "vertex");
  GLint colorLoc = glGetAttribLocation(cubeShaderProgram, "color");

  // we do not use indexing for points
  glGenVertexArrays(1, &cubePtsVao);
  glBindVertexArray(cubePtsVao);
  glBindBuffer(GL_ARRAY_BUFFER, cubeVbo);
  glEnableVertexAttribArray(vertexLoc);
  glEnableVertexAttribArray(colorLoc);
  glVertexAttribPointer(vertexLoc, 3, GL_FLOAT, GL_FALSE, nCubeAttribsPerVertex * sizeof(float), (void *)(0));
  glVertexAttribPointer(colorLoc, 3, GL_FLOAT, GL_FALSE, nCubeAttribsPerVertex * sizeof(float), (void *)(3 * sizeof(float)));
  // ========  END OF SOLUTION - TASK 2-1  ======== //

  // indexed lines
  // ======== BEGIN OF SOLUTION - TASK 2-2 ======== //
  // get rid of this tedious code, use your newly designed approach (createVAO + array of VertexAttrib structures)
  glGenVertexArrays(1, &cubeLinesVao);
  glBindVertexArray(cubeLinesVao);
  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, cubeLinesEao);
  glBindBuffer(GL_ARRAY_BUFFER, cubeVbo);
  glEnableVertexAttribArray(vertexLoc);
  glEnableVertexAttribArray(colorLoc);
  glVertexAttribPointer(vertexLoc, 3, GL_FLOAT, GL_FALSE, nCubeAttribsPerVertex * sizeof(float), (void *)(0));
  glVertexAttribPointer(colorLoc, 3, GL_FLOAT, GL_FALSE, nCubeAttribsPerVertex * sizeof(float), (void *)(3 * sizeof(float)));
  // ========  END OF SOLUTION - TASK 2-2  ======== //

  // indexed triangles
  // ======== BEGIN OF SOLUTION - TASK 2-3 ======== //
  glGenVertexArrays(1, &cubeTrianglesVao);
  glBindVertexArray(cubeTrianglesVao);
  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, cubeTrianglesEao);
  glBindBuffer(GL_ARRAY_BUFFER, cubeVbo);
  glEnableVertexAttribArray(vertexLoc);
  glEnableVertexAttribArray(colorLoc);
  glVertexAttribPointer(vertexLoc, 3, GL_FLOAT, GL_FALSE, nCubeAttribsPerVertex * sizeof(float), (void *)(0));
  glVertexAttribPointer(colorLoc, 3, GL_FLOAT, GL_FALSE, nCubeAttribsPerVertex * sizeof(float), (void *)(3 * sizeof(float)));
  // ========  END OF SOLUTION - TASK 2-3  ======== //

  glBindVertexArray(0);
  CHECK_GL_ERROR();
}

// deletes allocated buffers
void cleanup()
{
  glDeleteVertexArrays(1, &cubePtsVao);
  glDeleteVertexArrays(1, &cubeLinesVao);
  glDeleteVertexArrays(1, &cubeTrianglesVao);
  glDeleteBuffers(1, &cubeLinesEao);
  glDeleteBuffers(1, &cubeTrianglesEao);
  glDeleteBuffers(1, &cubeVbo);
  pgr::deleteProgramAndShaders(cubeShaderProgram);
}

void switchMode()
{
  switch(displayMode)
  {
    case GL_POINTS: displayMode = GL_LINES; break;
    case GL_LINES: displayMode = GL_TRIANGLES; break;
    case GL_TRIANGLES: displayMode = GL_POINTS; break;
    default:;
  }
}

void refreshCb(int)
{
  glutTimerFunc(REFRESH_INTERVAL, refreshCb, 0);
  elapsedTime = 0.001 * (double)glutGet(GLUT_ELAPSED_TIME); // milliseconds => seconds

  float rotAngleDeg = elapsedTime * 60.0f; // rotate 60 degrees per second
  model = glm::translate(glm::mat4(1.0f), glm::vec3(0.0f, 0.0f, -5.0f)); // move cube 5 units back
  model = glm::rotate(model, 30.0f, glm::vec3(1.0f, 0.0f, 0.0f)); // tilt cube by 30 degrees
  model = glm::rotate(model, rotAngleDeg, glm::vec3(0.0f, 1.0f, 0.0f)); // the animation

  glutPostRedisplay();
}

void displayCb()
{
  glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

  glm::mat4 matrix = projection * model;
  glUseProgram(cubeShaderProgram);
  glUniformMatrix4fv(glGetUniformLocation(cubeShaderProgram, "matrix"), 1, GL_FALSE, glm::value_ptr(matrix));

  switch(displayMode)
  {
    case GL_POINTS:
      glBindVertexArray(cubePtsVao);
      glDrawArrays(GL_POINTS, 0, nCubeVertices);
      break;

    case GL_LINES:
      glBindVertexArray(cubeLinesVao);
      glDrawElements(GL_LINES, nCubeLineIdx, GL_UNSIGNED_SHORT, 0);
      break;

    case GL_TRIANGLES:
      glBindVertexArray(cubeTrianglesVao);
      glDrawElements(GL_TRIANGLES, nCubeTriangleIdx, GL_UNSIGNED_SHORT, 0);
      break;

    default:;
  }

  glutSwapBuffers();
}

void reshapeCb(int w, int h)
{
  glViewport(0, 0, w, h);
  projection =  glm::perspective(60.0f, float(w) / float(h) , 1.0f, 10.0f);
}

void keyboardCb(unsigned char key, int x, int y)
{
  switch (key)
  {
    case 27:
      cleanup();
      glutLeaveMainLoop();
      break;
    case ' ':
      switchMode();
      break;
  }
}

int main(int argc, char** argv)
{
  glutInit(&argc, argv);

  glutInitContextVersion(pgr::OGL_VER_MAJOR, pgr::OGL_VER_MINOR);
  glutInitContextFlags(GLUT_FORWARD_COMPATIBLE);

  glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
  glutInitWindowSize(WIN_WIDTH, WIN_HEIGHT);
  glutCreateWindow(WIN_TITLE);

  glutDisplayFunc(displayCb);
  glutReshapeFunc(reshapeCb);
  glutKeyboardFunc(keyboardCb);
  glutTimerFunc(REFRESH_INTERVAL, refreshCb, 0);

  if(!pgr::initialize(pgr::OGL_VER_MAJOR, pgr::OGL_VER_MINOR))
    pgr::dieWithError("pgr init failed, required OpenGL not supported?");

  init();

  std::cout << "Use space to cycle through modes, Esc to quit." << std::endl;

  glutMainLoop();
  return 0;
}
