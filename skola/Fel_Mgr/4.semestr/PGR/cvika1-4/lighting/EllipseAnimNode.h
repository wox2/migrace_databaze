#ifndef ELLIPSE_ANIM_NODE_H
#define ELLIPSE_ANIM_NODE_H

#include "SceneNode.h"
// is in scene node #include "pgr.h"   // includes all PGR libraries, like shader, glm, assimp ...

class EllipseAnimNode : public SceneNode
{
public:
  EllipseAnimNode(const char* name = "<unk>", SceneNode* parent = NULL);
  ~EllipseAnimNode() {}

  void setSpeed(float speed) { m_speed = speed; }

  void setAxes(const float majorAxis, const float minorAxis) { 
    m_majorAxis = majorAxis; 
    m_minorAxis = minorAxis; 
  }

  void update(double elapsed_time);

protected:
  glm::vec3 m_axis;
  float m_angle;
  float m_speed;
  float m_majorAxis;  // Z direction
  float m_minorAxis;  // X dirextion
};

#endif
