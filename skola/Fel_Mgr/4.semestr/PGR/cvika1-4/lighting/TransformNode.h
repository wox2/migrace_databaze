#ifndef __TRANSFORMNODE_H
#define __TRANSFORMNODE_H

#include "SceneNode.h"

/// TransformNode represents transformation of scene graph subtree.
class TransformNode : public SceneNode
{
public:
  TransformNode(const char* name = "<unk>", SceneNode* parent = NULL);
  ~TransformNode() {}

  /// resets local matrix to identity
  void setIdentity();
  /// translate this node by vector tr
  void translate(const glm::vec3 &tr);
  /// rotate this node by angle a along vector vec
  void rotate(float angle, const glm::vec3 &axis);
  /// scale this and children nodes
  void scale(const glm::vec3 &scale);
};

#endif // of __TRANSFORMNODE_H




