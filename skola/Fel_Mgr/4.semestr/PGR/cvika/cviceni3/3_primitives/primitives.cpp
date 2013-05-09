//----------------------------------------------------------------------------------------
/**
 * \file       primitives.cpp
 * \author     Petr Felkel, Tomas Barak
 * \date       2010-2012
 * \brief      Primitives exercise
 */
//----------------------------------------------------------------------------------------

#include "pgr.h"

#include "render_stuff.h"
#include "data.h"

/// Main window label.
#define TITLE  "Seminar 3 - Graphical Primitives"

/// Main window initial width in pixels.
const int WIDTH = 500;
/// Main window initial width in pixels.
const int HEIGHT = 500;
///animation time step for glutTimer
const int TIMER_STEP = 20;   // next event in [ms]

/// Small spinAngle that is used to increment the house rotation spinAngle (spinAngle variable).
const float SMALL_ANGLE = -6.28f / 100;  // minus -> CW rotation
/// spinAngle represents actual rotation of the house in radians.
float spinAngle = 0.0f;

/// Indicates whether house rotation is enabled or disabled.
int spinFlag = 1;
/// Indicates if the back face culling is enabled.
int cullFlag = 0;
/// Indicates, if the GL_LINE FILL mode is used
int lineModeFlag = 0;


// Number of the task for display
int taskNumber = 0;

//Called to update the display.
//You should call glutSwapBuffers after all of your rendering to display what you rendered.
//If you need continuous updates of the screen, call glutPostRedisplay() at the end of the function.
void display()
{
  glClearColor(0.3f, 0.3f, 0.3f, 1.0f); // color for cleaning the screen  // gDEB

  if( cullFlag )
    glEnable( GL_CULL_FACE );
  else
    glDisable( GL_CULL_FACE );

  if( lineModeFlag )
    glPolygonMode( GL_FRONT_AND_BACK, GL_LINE );
  else
    glPolygonMode( GL_FRONT_AND_BACK, GL_FILL );

  functionDraw(taskNumber);

  // bind 1-3
  glutSwapBuffers();
}

//Called whenever the window is resized. The new window size is given, in pixels.
//This is an opportunity to call glViewport or glScissor to keep up with the change in size.
void reshape (int w, int h)
{
  glViewport(0, 0, (GLsizei) w, (GLsizei) h);
}

void FuncTimerCallback(int value)
{
  if(spinFlag)
  {
    glutTimerFunc(TIMER_STEP, FuncTimerCallback, 0);  //333 (1%) -> 3 -> (14%)
    /* Change the rotation spinAngle. */
    spinAngle += SMALL_ANGLE;
  }
  glutPostRedisplay();
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
      taskNumber = (taskNumber+1) % 4;
      break;
  }

  printf("task Number = %d\n", taskNumber);
  glutPostRedisplay();  // Nutno, kdybychom nevolali prekresleni casovacem ci Idle

}

//Called after the window and OpenGL are initialized. Called exactly once, before the main loop.
void init()
{
  InitializeProgram();

  InitializeVertexBufferHouse0();
  InitializeVertexArrayHouse0();

  InitializeVertexBufferHouse1();
  InitializeVertexArrayHouse1();

  InitializeVertexBufferHouse2();
  InitializeElementIndicesHouse3();
  InitializeVertexArrayHouse2();

  glCullFace( GL_BACK );  // only back faces will be culled, if enabled
  glClearColor(0.3f, 0.3f, 0.3f, 1.0f); // color for cleaning the screen
}

void myMenu( int item )
{
  switch( item )
  {
  case 1:
  case 2:
  case 3:
  case 4:
    taskNumber = item-1;
    printf("task Number = %d\n", taskNumber);
    break;
  case 10:
    spinFlag = !spinFlag;
    if(spinFlag)
      glutTimerFunc(TIMER_STEP, FuncTimerCallback, 0);
    //glutPostRedisplay();  // in FuncTimerCallback
    break;
  case 11:
    cullFlag = !cullFlag;
    glutPostRedisplay();
    break;
  case 12:
    lineModeFlag = !lineModeFlag;
    glutPostRedisplay();
    break;
  case 99:
    exit(0);
    break;    // never reached
  }

  glutPostRedisplay();  // Nutno, kdybychom nevolali prekresleni casovacem ci Idle
}

void createMenu(void)
{
  int submenuID = glutCreateMenu( myMenu );
  glutAddMenuEntry("Task 1", 1);
  glutAddMenuEntry("Task 2", 2);
  glutAddMenuEntry("Task 3", 3);
  glutAddMenuEntry("Task 4", 4);

  /* Create main menu. */
  glutCreateMenu(myMenu);
  glutAddSubMenu("Zobrazeni", submenuID );
  glutAddMenuEntry("Rotation on/off", 10);
  glutAddMenuEntry("BackFace Culling on/off", 11);
  glutAddMenuEntry("Polygon Fill mode line/fill", 12 );
  glutAddMenuEntry("Quit", 99);

  /* Menu will be invoked by the right mouse button */
  glutAttachMenu( GLUT_RIGHT_BUTTON );
}

void myMouse(int button, int state, int x, int y )
{
  if( ( button == GLUT_LEFT_BUTTON ) && ( state == GLUT_DOWN ) )
  {
    spinFlag = !spinFlag;
    if(spinFlag)
      glutTimerFunc(TIMER_STEP, FuncTimerCallback, 0);

    glutPostRedisplay();  // Nutno, kdybychom nevolali prekresleni casovacem ci Idle
  }
}

int main( int argc, char** argv )
{
  glutInit( &argc, argv );

  glutInitContextVersion(pgr::OGL_VER_MAJOR, pgr::OGL_VER_MINOR);
  glutInitContextFlags(GLUT_FORWARD_COMPATIBLE);

  glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA | GLUT_DEPTH);
  glutInitWindowPosition(5, 5);
  glutInitWindowSize(WIDTH, HEIGHT);

  glutCreateWindow(TITLE);
  glutDisplayFunc(display);
  glutReshapeFunc(reshape);
  glutKeyboardFunc(myKeyboard);
  glutMouseFunc(myMouse);
  if(spinFlag)
    glutTimerFunc(33, FuncTimerCallback, 0);

  createMenu();

  if(!pgr::initialize(pgr::OGL_VER_MAJOR, pgr::OGL_VER_MINOR))
    pgr::dieWithError("pgr init failed, required OpenGL not supported?");

  init();

  glutMainLoop();
  return 0;
}
