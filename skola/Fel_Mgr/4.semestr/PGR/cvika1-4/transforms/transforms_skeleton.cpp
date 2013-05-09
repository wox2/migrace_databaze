//----------------------------------------------------------------------------------------
/**
 * \file       transforms.cpp
 * \author     Jaroslav Sloup & Petr Felkel 
 * \date       2010/03/04
 * \brief      Application sketeton for A7B39PGR seminar on transformations.
 *
 * 2012/03/10  Rewritten for OpenGL 3.3 with glm library
 */
//----------------------------------------------------------------------------------------

#include <iostream>
#include <GL/glew.h>
//#include <GL/glut.h>      // glut (glut v3.0 functions only)
#include <GL/freeglut.h>    // glut + new freeglut functions - like OpenGL Version and profile

#include <glm/gtc/type_ptr.hpp> // value_ptr
#include <glm/gtc/matrix_transform.hpp> //translate, rotate, scale

//#include "pgr.h"
#include "objects.h"


/// Main window label.
#define TITLE  "Seminar 5 - Transformations (model and view)" 

#ifndef M_PI
#define M_PI 3.14159f
#endif

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

/// Small viewAngle that is used to increment the viewDirection 
const float VIEW_DIRECTION_ANGLE_DELTA = M_PI / 100;
/// Small spinAngle that is used to increment the object rotation spinAngle (spinAngle variable).
const float SMALL_ANGLE = -M_PI / 500;  // minus -> CW rotation

/// Indicates whether rotation is enabled or disabled. 
int spinFlag = 0;
/// Indicates if the back face culling is enabled.
int cullFlag = 0;
/// Indicates, if the GL_LINE FILL mode is used
int lineModeFlag = 0;

// Number of the task for display
int taskNumber = 1;


float aspect_ratio = 1.0f;

CPlaneObject * plane;   // = new CPlaneObject();  lze az po definici kontextu OpenGL
CLoadedObject * car;
CAxesObject * axes;

/* Drawing function */
void (*drawScene)();

// forward declarations
void applyFlags();  
void mySpecialKeyboard (int key, int x, int y); 


// =============================== START ======================================
// usefull glm library functions
//   glm::vec3 v = glm::vec3(x, y, z); 
//   glm::mat4 i = glm::mat4(1.0);  //identityMatrix();
//   glm::mat4 r = glm::rotate( matrix_in, angle, spinAxis );
//   glm::mat4 t = glm::translate( matrix_in, glm::vec3(x, y, z) );
//   glm::lookAt( eyePosition, centerPosition, upVector );	
// more on http://glm.g-truc.net/glm-0.9.3.pdf


/// radius of the model circle (distance from the scene center)
#define R  3.0f  
/// the axis of the model rotation
glm::vec3 spinAxis = glm::vec3(0.0, 1.0, 0.0);  
/// spinAngle represents actual model / camera rotation in radians.
float spinAngle = 0.0f; 

void drawScene1(void)
{
	glm::mat4 projection = glm::perspective (60.0f, aspect_ratio, 0.1f, 20.0f);

	// camera setup 
	glm::mat4 view 
		= glm::lookAt(	glm::vec3(0.0f, 2.0f, 8.0f),   //eye = camera position
						glm::vec3(0.0f, 0.0f, 0.0f),   //center = point we look at (view direction  = eye-center)
						glm::vec3(0.0f, 1.0f, 0.0f) ); //up vektor (camera orientation along the view direction)
	
	// =============================== BEGIN OF SOLUTION - TASK 1 ======================================
	   glm::mat4 model = glm::mat4(1.0);   // zacneme identitou

	   // pozor!! rotace + posunuti je otaceni kolem osy, ale posunuti + rotace je pouze rotace na posunute pozici
		model = glm::rotate(model, spinAngle*150, spinAxis); // rotace
	    model = glm::translate(model, glm::vec3(3.0f, 0.0f, 0.0f)); // posunuti



	// =============================== END OF SOLUTION - TASK 1 ======================================

	// model draw
	car->draw( model, view, projection );

	// draw plane
	glm::mat4 plane_model = glm::mat4(1.0);   ///::identityMatrix();
	plane->draw( plane_model, view, projection );

	// draw the plane and model coordinate axes
	//axes->draw( plane_model, view, projection );
	//axes->draw( model, view, projection );

}

void drawScene2(void)
{
	glm::mat4 projection = glm::perspective(60.0f, aspect_ratio, 0.1f, 20.0f);

	// =============================== BEGIN OF SOLUTION - TASK 2 ======================================

	GLfloat px = 2*cos(spinAngle); // polomer je 2, otacime kolem os X,Z
	GLfloat pz = 2*sin(spinAngle);

	glm::mat4 view = glm::mat4(1.0); // identita

	// kamera bude na pozici px, 1, pz, bude se divat do stredu (0,0,0), up vektor bude 1 v ose Y -> nebude nikam naklonena
	view	= glm::lookAt(glm::vec3(px,1.0,pz),  
						  glm::vec3(0.0f, 0.0f, 0.0f),   
					  	  glm::vec3(0.0f, 1.0f, 0.0f) ); 



	// =============================== END OF SOLUTION - TASK 2 ======================================
	glm::mat4 model = glm::mat4(1.0); /// identityMatrix();

	// model draw
	car->draw( model, view, projection );

	// draw plane
	glm::mat4 plane_model = glm::mat4(1.0); /// identityMatrix();
	plane->draw( plane_model, view, projection );

}

#define STEP_SIZE 0.2f

glm::vec3 position;      // eye (camera) position
GLfloat   viewAngle;     // left - right camera rotation
glm::vec3 moveDirection; // vector derived from the viewAngle

void drawScene3(void)
{
	glm::mat4 projection = glm::perspective(60.0f, aspect_ratio, 0.1f, 20.0f);

	// =============================== BEGIN OF SOLUTION - TASK 3 ======================================

	glm::mat4 view = glm::mat4(1.0);

		moveDirection.x = cos(viewAngle*0.2); // smer pohybu podle osy X, ta 0.2 je tam proto, aby se krokovalo v mensich jednotkach
		moveDirection.z = sin(viewAngle*0.2); // smer pohybu podle osy Z
	
		// jsme na pozici position, divame se tam, kam smeruje moveDirection, up vector je 1 -> nejsme nikam nakloneni
		view	= glm::lookAt(position, 
						  glm::vec3(position.x+moveDirection.x,position.y+moveDirection.y,position.z+moveDirection.z),  
					  	  glm::vec3(0.0f, 1.0f, 0.0f) ); 


	// =============================== END OF SOLUTION - TASK 3 ======================================
	
	glm::mat4 model = glm::mat4(1.0); ////::identityMatrix();

	// model draw
	car->draw( model, view, projection );

	// draw plane
	glm::mat4 plane_model = glm::mat4(1.0); ////::identityMatrix();
	plane->draw( plane_model, view, projection );

}

GLfloat beta; ///< elevation angle (angle to xz plane)

void drawScene4(void)
{
	glm::mat4 projection = glm::perspective(60.0f, aspect_ratio, 0.1f, 20.0f);

	// =============================== BEGIN OF SOLUTION - TASK 4 ======================================

	glm::mat4 view = glm::mat4(1.0);

	       // smer pohybu po ose X,Y,Z; beta je smer naklonu nahoru/dolu
			moveDirection.x = cos(viewAngle*0.2)*cos(beta*0.2); 
			moveDirection.y = sin(beta*0.2);
		    moveDirection.z = sin(viewAngle*0.2)*cos(beta*0.2);

			position.y = 0.5; // pro jistotu -> meli bychom zustat ve stejne vysce

			// podobne jako u prikladu 3 s tim rozdilem, ze se meni upvektor
			// up vektor je zde derivace vektoru moveDirecton podle uhlu viewAngle (vcetne toho koeficientu)
				view =    glm::lookAt(position,
						  glm::vec3(position.x+moveDirection.x,position.y+moveDirection.y,position.z+moveDirection.z),  
					  	  glm::vec3(-cos(viewAngle*0.2)*sin(beta*0.2),cos(beta*0.2),-sin(viewAngle*0.2)*sin(beta*0.2))); 


	// camera setup 



	// =============================== END OF SOLUTION - TASK 4 ======================================
	
	glm::mat4 model = glm::mat4(1.0); ////::identityMatrix();

	// model draw
	car->draw( model, view, projection );

	// draw plane
	glm::mat4 plane_model = glm::mat4(1.0); ////::identityMatrix();
	plane->draw( plane_model, view, projection );

}


void initTask(int taskNumber)
{
  switch(taskNumber) {
	case 1:
		drawScene = drawScene1;
		spinFlag = 1;
		applyFlags();
		spinAngle = 0.0;			   // model rotation angle
		//glutKeyboardFunc(NULL);
		break;
	case 2:
		drawScene = drawScene2;
		spinFlag = 1;
		applyFlags();
		spinAngle = 0.0;			   // camera rotation angle
		glutSpecialFunc(NULL);
		break;
	case 3:
		drawScene = drawScene3;
		spinFlag = 0;
		applyFlags();
		glutSpecialFunc(mySpecialKeyboard);
        position.x = 0.0f;
		position.y = 0.5f;
		position.z = 5.0f; 
		viewAngle = -7.5;   // used for moveDirection computation
		break;
	case 4:
		drawScene = drawScene4;
		glutIdleFunc(NULL);
		glutSpecialFunc(mySpecialKeyboard);
        position.x = 0.0f;
		position.y = 0.5f;
		position.z = 5.0f; 
		viewAngle = -7.5;   // used for moveDirection computation
		beta = 0.0;
		break;
  }
}




//Called to update the display.
//You should call glutSwapBuffers after all of your rendering to display what you rendered.
//If you need continuous updates of the screen, call glutPostRedisplay() at the end of the function.
void display()
{
	// clear the window
	glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );

	drawScene();  //<============ THIS IS YOUR DRAWING PROCEDURE ========================

  	glutSwapBuffers();
}

//Called whenever the window is resized. The new window size is given, in pixels.
//This is an opportunity to call glViewport or glScissor to keep up with the change in size.
void reshape (int w, int h)
{
	glViewport(0, 0, (GLsizei) w, (GLsizei) h);
	aspect_ratio = (float)w/(float)h;
}

//Called when the timer event arrived from the event queue
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
 			taskNumber = (taskNumber) % 4 +1;
			initTask(taskNumber);
			break;
 	}
	
	printf("task Number = %d\n", taskNumber);
	glutPostRedisplay();  // Nutno, kdybychom nevolali prekresleni casovacem ci Idle

}

//Called whenever a special key on the keyboard was pressed.
void mySpecialKeyboard (int key, int x, int y) {

  switch (key) {

	case GLUT_KEY_RIGHT:  // rotate the viewDirection left
	  // BEGIN OF SOLUTION - TASK 3 and 4 ========================
		viewAngle++;

	  // END OF SOLUTION - TASK 3 and 4 ========================
	  break;

	case GLUT_KEY_LEFT: // otoceni smeru pohledu doprava
	  // BEGIN OF SOLUTION - TASK 3 and 4 ========================
		viewAngle--;

	  // END OF SOLUTION - TASK 3 and 4 ========================
	  break;
	
	case GLUT_KEY_UP:  // step forward
	  // BEGIN OF SOLUTION - TASK 3 and 4 ========================
	  // position +=
	  position.x += 0.5 * moveDirection.x;
	  position.y += 0.5 * moveDirection.y;
	  position.z += 0.5 * moveDirection.z;

	  // END OF SOLUTION - TASK 3 and 4 ========================
	  break;
	
	case GLUT_KEY_DOWN:  // step backward
	  // BEGIN OF SOLUTION - TASK 3 and 4 ========================

      position.x -= 0.5 * moveDirection.x;
	  position.y -= 0.5 * moveDirection.y;
	  position.z -= 0.5 * moveDirection.z;

	  // END OF SOLUTION - TASK 3 and 4 ========================
      break;
	
	case GLUT_KEY_PAGE_UP: // raising up the view direction
	  // BEGIN OF SOLUTION - TASK 4 ========================
			beta ++;
	  // END OF SOLUTION - TASK 4 ========================
	  break;
	
	case GLUT_KEY_PAGE_DOWN: // skloneni smeru pohledu
	  // BEGIN OF SOLUTION - TASK 4 ========================
	  beta--;

	  // END OF SOLUTION - TASK 4 ========================
	  break;
	
	default:
	  return;
  }
  glutPostRedisplay();
}

// Called from main() after the window and OpenGL context are initialized. 
// Called exactly once, before the main loop start.
void init()
{
	// init Objects
	plane = new CPlaneObject();

	car = new CLoadedObject("../data/bronco.obj");

	axes = new CAxesObject();

	// init the drawing Function
	initTask(1);
	
	// init OpenGL
	glCullFace( GL_BACK );  // only back faces will be culled, if enabled
	glClearColor(0.3f, 0.3f, 0.3f, 1.0f); // color for cleaning the screen
    glEnable(GL_DEPTH_TEST);
    glDepthFunc(GL_LEQUAL);  // default value
}

//apply the flags changed by the user interface
void applyFlags()
{
	// OpenGL setup 
	if( cullFlag ) 
		glEnable( GL_CULL_FACE );
	else 
		glDisable( GL_CULL_FACE );

	if( lineModeFlag )
		glPolygonMode( GL_FRONT_AND_BACK, GL_LINE );
	else 
		glPolygonMode( GL_FRONT_AND_BACK, GL_FILL );
	
	if(spinFlag) 
		glutTimerFunc(TIMER_STEP, FuncTimerCallback, 0);  

}

//event processing of the menu commands
void myMenu( int item )
{
	switch( item ) {
	case 1:
	case 2:
	case 3:
	case 4:
		taskNumber = item;
		initTask(taskNumber);
		printf("task Number = %d\n", taskNumber);
		glutPostRedisplay();  // Nutno, kdybychom nevolali prekresleni casovacem ci Idle
    break;
	case 10: 
		spinFlag = !spinFlag;
		if(spinFlag) 
			glutTimerFunc(TIMER_STEP, FuncTimerCallback, 0);  
		//glutPostRedisplay();  // in FuncTimerCallback
		break;
	case 11:
		cullFlag = !cullFlag;  
		applyFlags();
		glutPostRedisplay();
		break;
	case 12:
		lineModeFlag = !lineModeFlag;  
		applyFlags();
		glutPostRedisplay();
		break;
	case 99:
		exit(0);
		break;    // never reached
	}


}

// menu preparation
void createMenu(void)
{
	//Uloha 4 - zacatek
	int submenuID = glutCreateMenu( myMenu );
	glutAddMenuEntry("1 - car driving along the circle", 1);
	glutAddMenuEntry("2 - camera moving around the car", 2);
	glutAddMenuEntry("3 - free camera in xz-plane (WALK mode)", 3);
	glutAddMenuEntry("Bonus - WALK mode look up and down", 4);

	/* Create main menu. */
	glutCreateMenu(myMenu); 
	glutAddSubMenu("Task number", submenuID );
	glutAddMenuEntry("Rotation on/off", 10);
	glutAddMenuEntry("BackFace Culling on/off", 11);
	glutAddMenuEntry("Polygon Fill mode line/fill", 12 );
	glutAddMenuEntry("Quit", 99);

	/* Menu will be invoked by the right mouse button */
	glutAttachMenu( GLUT_RIGHT_BUTTON );  

}

// mouse click
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
		glutTimerFunc(TIMER_STEP, FuncTimerCallback, 0);    

	createMenu();

	// load the pointers to OpenGL functions (only needed in MS Windows)
	glewInit();
	if (!GLEW_VERSION_3_0) {
		fprintf(stderr, "OpenGL 3.0 or higher not available\n");
		return 1;
	}

    // Init context and profile 
	//glutInitContextVersion( MAJOR_VERSION, MINOR_VERSION );
    //   if( (MAJOR_VERSION >= 3 ) && (MINOR_VERSION >= 2) ) 
	//  glutInitContextProfile( PROFILE );   // core or compatibiliy, as defined in OpenGL 3.2

	init();

	glutMainLoop();
	return 0;
}
