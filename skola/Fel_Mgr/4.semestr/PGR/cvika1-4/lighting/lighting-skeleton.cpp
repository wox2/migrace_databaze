// BI-PGR, 2011, graph classes put together by Vlastimil Havran and Tomas Barak
// A7B39PGR, 2012, update to glm library and 
//                 create a different scenegraph task by Petr Felkel and Jaroslav Sloup

#include <string>
#include <vector>
#include <cstdio>
#include <string.h>
#include <math.h>
#include <iostream>
#include <GL/glew.h>
#include <GL/glut.h>


#include "pgr.h"   // includes all PGR libraries, like shader, glm, assimp ...

#include "PyramidNode.h"     // simple object
#include "TransformNode.h"   // model transformation
#include "RotationAnimNode.h"// animation transformation
#include "EllipseAnimNode.h" // animation - elliptical movement
#include "MeshNode.h"        // model loaded from the file 
//#include "SceneNode.h"     // superclass of all graph nodes above
#include "Resources.h"


#ifndef M_PI
#define M_PI 3.14159f
#endif

#define TITLE "Seminar 6 - Scene Graph"

// TASK 1 - variables and constants
// file name used during the scene graph creation 
#define TERRAIN_FILE_NAME "../data/terrain"
#define JEEP_FILE_NAME "../data/jeep/jeep.obj"
#define CESSNA_FILE_NAME "../data/cessna.obj"

// resource manager
ModelManager* modelManager_p = NULL;

// scene graph root node
SceneNode * root_node = NULL; // scene root

// TASK 2 and 3 - variables and constants
#define RADIUS_A 25.0
#define RADIUS_B 15.0

// global variables
float g_aspect_ratio = 1.0f;
bool g_topView = true;

// -------------------------------------------------------------------
void FuncTimerCallback(int)
{
  double timed = 0.001 * (double)glutGet(GLUT_ELAPSED_TIME); // milliseconds

  if(root_node)
     root_node->update(timed);

  glutTimerFunc(33, FuncTimerCallback, 0);
  glutPostRedisplay();
}

void functionDraw()
{
  glClearColor(0.3f, 0.3f, 0.3f, 1.0f);
  glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

  glm::mat4 projection =  glm::perspective(60.0f, g_aspect_ratio, 1.0f, 10000.0f);
  glm::mat4 view;

  // front view
  if( g_topView ) {
    //top view
     view = glm::lookAt(
      glm::vec3(0.0, 50.0, 0.0), //eye
      glm::vec3(0.0, 0.0, 0.0),  //center
      glm::vec3(0.0, 0.0, -1.0) );//up
  }
  else {
    view = glm::lookAt(
      glm::vec3(0.0, 8.0, 40.0), //eye
      glm::vec3(0.0, 4.0, 0.0),  //center
      glm::vec3(0.0, 1.0, 0.0) );//up
  }
  if(root_node)
    root_node->draw(view, projection);
}

// --------------------------------------------------
// General functions

void initializeScene()
{
  // =============================== BEGIN OF SOLUTION - TASK 1 ======================================

  // create scene root node
  root_node = new SceneNode("root");

  // terrain ======================== START  ===========================
#if 1
  // terrain transform node
  TransformNode* terrain_transform = new TransformNode("terrainTranf", root_node);
  terrain_transform->translate(glm::vec3(0.5, -25, -3));
  terrain_transform->scale(glm::vec3(80.0, 45.0, 80.0) );
  
  // MeshGeometry holds the submeshes 
  MeshGeometry* mesh_p = NULL;
  if((mesh_p = ModelManager::Instance()->Get(TERRAIN_FILE_NAME)) == NULL) {
    mesh_p = ModelManager::Instance()->LoadTerrain(TERRAIN_FILE_NAME);
  }

  MeshNode* terrain_mesh_p = new MeshNode(TERRAIN_FILE_NAME, terrain_transform); 
  terrain_mesh_p->setGeometry(mesh_p);
#endif
  // terrain ======================== END  ===========================


 
  ///// OBJECT 1 //////
  // jeep ======================== START  ===========================
#if 1
  TransformNode* jeep_transform = new TransformNode("jeepTranf", root_node);
  jeep_transform->rotate(-90.0f, glm::vec3(1.0, 0.0, 0.0) );
  jeep_transform->rotate(-90.0f, glm::vec3(0.0, 0.0, 1.0) );

  // =============================== BEGIN OF SOLUTION - TASK 1,2 ======================================
  

  // =============================== END OF SOLUTION - TASK 1,2 ======================================

  MeshGeometry* meshGeom_p = NULL;
  if((meshGeom_p = ModelManager::Instance()->Get(JEEP_FILE_NAME)) == NULL) {
    meshGeom_p = ModelManager::Instance()->Load(JEEP_FILE_NAME);
  }
  MeshNode* jeep_mesh_p = new MeshNode(JEEP_FILE_NAME, jeep_transform );
  jeep_mesh_p->setGeometry(meshGeom_p);
#endif
  // jeep ======================== END  ===========================



  const float A = 25;
  const float B = 15;
   
  // cessna model ellipse
  // ======================== START  ===========================
#if 1

 // =============================== BEGIN OF SOLUTION - TASK 1,3 ======================================
  



 // =============================== END OF SOLUTION - TASK 1,3 ======================================
  
  MeshNode * cessna_mesh_p = new MeshNode(CESSNA_FILE_NAME, root_node );
  cessna_mesh_p->setGeometry(meshGeom_p);
#endif
  // cessna ellipse ==================== END  ===========================


  // cessna model rotate
  // ======================== START  ===========================
#if 1
  {
  // =============================== BEGIN OF SOLUTION - TASK 1,3 ======================================
  





  // =============================== END OF SOLUTION - TASK 1,3 ======================================
  }

#endif
  // cessna circle ==================== END  ===========================
  // =============================== END OF SOLUTION - TASK 1 ======================================

  // dump our scene graph tree for debug
  root_node->dump();
}

//Called to update the display.
//You should call glutSwapBuffers after all of your rendering to display what you rendered.
//If you need continuous updates of the screen, call glutPostRedisplay() at the end of the function.
void display()
{
  functionDraw();
  glutSwapBuffers();
}

//Called whenever the window is resized. The new window size is given, in pixels.
//This is an opportunity to call glViewport or glScissor to keep up with the change in size.
void reshape (int w, int h)
{
  glViewport(0, 0, (GLsizei) w, (GLsizei) h);
  g_aspect_ratio = (float)w/(float)h;

  // good place for perspective setup
  // put to functionDraw(), based on g_aspect_ratio
}

//Called whenever a key on the keyboard was pressed.
//The key is given by the ''key'' parameter, which is in ASCII.
//It's often a good idea to have the escape key (ASCII value 27) call glutLeaveMainLoop() to
//exit the program.
void myKeyboard(unsigned char key, int x, int y)
{
  switch (key)
  {
    case 27:
      exit(0);//glutLeaveMainLoop();
      break;
    case ' ':
      MeshNode::loadProgram();
      break;
    case 'v':
    case 'V':
      g_topView = !g_topView;
      glutPostRedisplay();
  }
}

// Called after the window and OpenGL are initialized. Called exactly once, before the main loop.
void init()
{
  initializeScene();

  glDisable(GL_CULL_FACE); // draw both back and front faces
  glEnable(GL_DEPTH_TEST);
  glDepthFunc(GL_LEQUAL);
}

void mySpecialKeyboard(int specKey, int x, int y)
{
  switch (specKey)
  {
    case GLUT_KEY_UP:
      //cessna_trans->translate(Vec3f(-1, 0, 0));
      break;
    case GLUT_KEY_DOWN:
      //cessna_trans->translate(Vec3f(1, 0, 0));
      break;
  }
}

/*
 * Entry point
 */
int main(int argc, char** argv)
{
  // initialize windonwing system
  glutInit(&argc, argv);
  glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE);
  // initial window size
  glutInitWindowSize(800, 600);
  glutInitWindowPosition(800,100);
  glutCreateWindow(TITLE);

  // register callback for drawing a window contents
  glutDisplayFunc(display);
  // register callback for change of window
  glutReshapeFunc(reshape);
  // register callback for keyboard
  glutKeyboardFunc(myKeyboard);
  glutSpecialFunc(mySpecialKeyboard);
  glutTimerFunc(33, FuncTimerCallback, 0);

  // load the pointers to OpenGL functions (only needed in MS Windows)
  glewInit();
  if (!GLEW_VERSION_3_0) {
    fprintf(stderr, "OpenGL 3.0 not available\n");
    return 1;
  }
  init();

  glutMainLoop();
  return 0;
}
