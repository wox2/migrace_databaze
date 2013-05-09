#ifndef __SCENENODE_H
#define __SCENENODE_H

#include <vector>
#include <string>

#include "pgr.h"   // includes all PGR libraries, like shader, glm, assimp ...

/** Basic scene graph node
 *
 * You can derive this class and reimplement update() and draw() methods.
 */
class SceneNode
{
public:
  typedef std::vector<SceneNode *> Children;

  SceneNode(const char* name = "<unk>", SceneNode* parent = NULL);

  /// destroy children
  virtual ~SceneNode();

  /// recalculates global matrix and updates all children
  virtual void update(double elapsed_time);

  /// calls draw on child nodes
  virtual void draw(const glm::mat4 & view_matrix, const glm::mat4 & projection_matrix);

  const SceneNode* parentNode() const { return m_parent; }
  SceneNode* parentNode() { return m_parent; }

  /// re-parent this node
  void setParentNode(SceneNode * new_parent);

  void addChildNode(SceneNode* node);

  /// removes child node (in O(n))
  void removeChildNode(SceneNode* node);

  /// Returns node name.
  const std::string & nodeName() const { return m_name; }

  /// calculated global matrix (valid after update() call)
  const glm::mat4 & globalMatrix() const { return m_global_mat; }

  /// local matrix
  const glm::mat4  & localMatrix() const { return m_local_mat; }

  /// dumps the node + subtree to stdout (you can reimplement this to display additional stuff)
  virtual void dump(unsigned indent = 0);

protected:
  std::string m_name;    ///< node name
  SceneNode*  m_parent;
  Children    m_children;
  float       m_time;  // updated in update()
  glm::mat4   m_global_mat; ///< final global model matrix, calculated in update()
  glm::mat4   m_local_mat;  ///< local model matrix, derived transformation nodes should calculate it
};

#endif // of __SCENENODE_H
