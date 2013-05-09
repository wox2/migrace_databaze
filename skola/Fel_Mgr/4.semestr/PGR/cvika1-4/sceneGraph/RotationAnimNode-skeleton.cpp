#include "pgr.h"   // includes all PGR libraries, like shader, glm, assimp ...
#include "RotationAnimNode.h"


#ifndef M_PI
#define M_PI 3.14159f
#endif
#define DEG_TO_RAD (M_PI/180.0f)
#define RAD_TO_DEG (180.0f/M_PI)

RotationAnimNode::RotationAnimNode(const char* name, SceneNode* parent):
  SceneNode(name, parent), m_axis(1, 0, 0), m_angle(0), m_speed(0)
{
}

void RotationAnimNode::update(double elapsed_time)
{
  // =============================== BEGIN OF SOLUTION - TASK 2 ====================================

    m_local_mat = glm::mat4(1.0f);  // musime vzdy zacit identitou, jinak se ty transformace pri kazde iteraci budou scitat!!!
	if(m_angle > 2*3.14159) m_angle = 0; // osetreni 360°...ten uhel je ve stupnich
	m_angle = (elapsed_time*m_speed); 

	// matici priradime rotacni transformaci podle uhlu m_angle a os m_axis

	// to, ze cessna opisuje ten oblouk je zpusobeno tim, ze ji pred provedenim tohoto uzlu
	// posuneme na urc. pozici --> matice je prvotne inicializovana na identitu, proto zacne letadlo otacet kolem bodu (0,0,0)
	m_local_mat = glm::rotate( m_local_mat, m_angle*RAD_TO_DEG, m_axis);


  // =============================== END OF SOLUTION - TASK 2 ======================================
  /// call inherited update (which calculates global matrix and updates children)
  SceneNode::update(elapsed_time);
}
