#include "pgr.h"   // includes all PGR libraries, like shader, glm, assimp ...
#include "EllipseAnimNode.h"


#ifndef M_PI
#define M_PI 3.14159f
#endif

#define DEG_TO_RAD (M_PI/180.0f)
#define RAD_TO_DEG (180.0f/M_PI)

EllipseAnimNode::EllipseAnimNode(const char* name, SceneNode* parent):
  SceneNode(name, parent), m_axis(1, 0, 0), m_angle(0), m_speed(0)
{
}

void EllipseAnimNode::update(double elapsed_time)
{
  m_angle = m_speed * (float) elapsed_time;


  // submarine position on the ellipse (given by angle _rotationAngle)
  float posX = m_majorAxis*cos( m_angle );
  float posZ = m_minorAxis*sin(-m_angle );


  // start with identity
  // translate submarine to the position on ellipse according to the angle _rotationAngle
  m_local_mat = glm::translate( glm::mat4(1.0), glm::vec3(posX, 0.0, posZ) ); 

  //// rotate the submarine (align the submarine with the tangent to the ellipse)
  double angle = atan2(m_minorAxis*cos( m_angle ), 
                      -m_majorAxis*sin(-m_angle ));
  angle *= RAD_TO_DEG;
  angle = 90.0 - angle;

  m_local_mat = glm::rotate( m_local_mat, (float)angle, glm::vec3(0.0, 1.0, 0.0) );


  /// call inherited update (which calculates global matrix and updates children)
  SceneNode::update(elapsed_time);
}

