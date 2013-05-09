#include "pgr.h"   // includes all PGR libraries, like shader, glm, assimp ...
#include "EllipseAnimNode.h"


#ifndef M_PI
#define M_PI 3.14159f
#endif

#define DEG_TO_RAD (M_PI/180.0f)
#define RAD_TO_DEG (180.0f/M_PI)

EllipseAnimNode::EllipseAnimNode(const char* name, SceneNode* parent):
  SceneNode(name, parent), m_axis(0, 1, 0), m_angle(0), m_speed(0)
{
}

void EllipseAnimNode::update(double elapsed_time)
{

  // =============================== BEGIN OF SOLUTION - TASK 3 ====================================

     m_local_mat = glm::mat4(1.0f);  // nastaveni identity
	if(m_angle > 2*3.14159) m_angle = 0; // osetreni uhlu > 360°
	m_angle = (elapsed_time*m_speed); // nastaveni uhlu


	// cessna je v transformacim uzlu nastavena na nejakou pozici --> tato pozice je v tomto uzlu
	// brana jako pocatek (0,0,0). A prave z tohoto pocatku ted cessnu posuneme na okraj elipsy podle velikosti uhlu m_angle
	
	 // nastavime vektor posunu
	 glm::vec3 posun = glm::vec3(m_majorAxis*sin(m_angle), 0, m_minorAxis*cos(m_angle) );

	 // posuneme cessnu
	  m_local_mat = glm::translate(m_local_mat,posun);

	  // aby se cessna take natocila ve smeru pohybu, musime spocitat tecnu a uhel, ktery svira tato tecna s pocatkem
	  float tecna_x = -m_minorAxis*sin(m_angle); // pruseciky s tecnou a elipsou
	  float tecna_y = m_majorAxis*cos(m_angle);

	  // bod (tecna_x,tecna_y) zde udava vzdalenost od pocatku elipsy, pro vypocet muzeme
	  // uvazovat pravouhly trojuhelnik s odvesnami o delce tecna_x a tecna_y

	  // uhel je konvexni doplnek pravouhleho trojuhelniku s odvesnami tecna_x, tecna_y. Uhel dostaneme funkci arc_tangens
	  float uhel = 180-atan2(tecna_x,tecna_y)*RAD_TO_DEG;

	  // natoceni cessny ve smeru pohybu
	  m_local_mat = glm::rotate( m_local_mat, uhel, m_axis);





  // =============================== END OF SOLUTION - TASK 3 ======================================

  /// call inherited update (which calculates global matrix and updates children)
  SceneNode::update(elapsed_time);
}

