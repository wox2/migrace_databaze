// A7B39PGR. 2012, Petr Felkel 
//----------------------------------------------------------------------------------------
/**
 * \file       primitives.cpp
 * \author     Petr Felkel 
 * \date       2010/02/27
 * \brief      Application sketeton that will be used for solution of tasks concerning geometric primitives.
 *
*/
//----------------------------------------------------------------------------------------

// this is the example solution

#include "render_stuff.h"
#include "data_skeleton.h"


#include <iostream>
#include <GL/glew.h>
//#include <GL/glut.h>      // glut (glut v3.0 functions only)
#include <GL/freeglut.h>    // glut + new freeglut functions - like OpenGL Version and profile

/// Main window label.
#define TITLE  "Seminar 3 - Graphical Primitives"

/// OpenGL version
const int MAJOR_VERSION = 3;
const int MINOR_VERSION = 3;
const int PROFILE = GLUT_CORE_PROFILE;  // applicable from OpenGL 3.2
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
			InitializeProgram(taskNumber);
			break;
 	}
	
	printf("task Number = %d\n", taskNumber);
	glutPostRedisplay();  // Nutno, kdybychom nevolali prekresleni casovacem ci Idle

}

//Called after the window and OpenGL are initialized. Called exactly once, before the main loop.
void init()
{
	InitializeProgram(0);
	InitializeVertexBufferHouse0(); 
	InitializeVertexArrayHouse0();

	InitializeProgram(1);
	InitializeVertexBufferHouse1(); 
	InitializeVertexArrayHouse1();

	InitializeVertexBufferHouse2(); 
	InitializeElementIndicesHouse3();
	InitializeVertexArrayHouse2();


	InitializeProgram(taskNumber);
	glCullFace( GL_BACK );  // only back faces will be culled, if enabled
	glClearColor(0.3f, 0.3f, 0.3f, 1.0f); // color for cleaning the screen
}


void myMenu( int item )
{
	switch( item ) {
	case 1:
	case 2:
	case 3:
	case 4:
		taskNumber = item-1;
		InitializeProgram(taskNumber);
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
	//Uloha 4 - zacatek
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


/*
 * Entry point
 */
int main( int argc, char** argv )
{

    /* Initialize the GLUT library. */
	glutInit( &argc, argv );

 	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA | GLUT_DEPTH);
	glutInitWindowPosition(5, 5);
    glutInitWindowSize(WIDTH, HEIGHT);

    /* Create main window and set callbacks. */
    glutCreateWindow(TITLE);
	glutDisplayFunc(display);
	glutReshapeFunc(reshape);
	glutKeyboardFunc(myKeyboard);
	glutMouseFunc( myMouse );  // uloha 6
	if(spinFlag)
		glutTimerFunc(33, FuncTimerCallback, 0);    

	createMenu();

	// load the pointers to OpenGL functions (only needed in MS Windows)
	glewInit();
	if (!GLEW_VERSION_3_0) {
		fprintf(stderr, "OpenGL 3.0 or higher not available\n");
		return 1;
	}

   // Init context and profile 
	glutInitContextVersion( MAJOR_VERSION, MINOR_VERSION );
    if( (MAJOR_VERSION >= 3 ) && (MINOR_VERSION >= 2) ) 
	  glutInitContextProfile( PROFILE );   // core or compatibiliy, as defined in OpenGL 3.2

	init();

	glutMainLoop();
	return 0;
}
