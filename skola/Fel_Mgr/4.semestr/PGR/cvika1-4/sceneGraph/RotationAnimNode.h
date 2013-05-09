#ifndef ROTATION_ANIM_NODE_H
#define ROTATION_ANIM_NODE_H

#include "SceneNode.h"
// is in scene node #include "pgr.h"   // includes all PGR libraries, like shader, glm, assimp ...

class RotationAnimNode : public SceneNode
{
public:
  RotationAnimNode(const char* name = "<unk>", SceneNode* parent = NULL);
  ~RotationAnimNode() {}

  void setSpeed(float speed) { m_speed = speed; }
  void setAxis(const glm::vec3 & axis) { m_axis = axis; }

  void update(double elapsed_time);

protected:
  glm::vec3 m_axis;
  float m_angle;
  float m_speed;
};

#endif
