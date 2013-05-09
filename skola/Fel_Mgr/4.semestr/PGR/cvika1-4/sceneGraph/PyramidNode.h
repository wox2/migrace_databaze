#ifndef PYRAMIDNODE_H
#define PYRAMIDNODE_H

#include <GL/glew.h>
#include "SceneNode.h"

/// Node, that draws pyramid from task3
class PyramidNode : public SceneNode
{
public:
  PyramidNode(const char * name = "pyramid", SceneNode * parent = NULL);
  ~PyramidNode() {}

  /// reimplemented draw
  void draw(const glm::mat4  & view_matrix, const glm::mat4  & projection_matrix);

protected:
  /// identifier for the buffer object
  static GLuint m_vertexBufferObject;
  /// identifier for the program
  static GLuint m_program;
  /// shader matrix location
  static GLint m_PVMmatrixLoc;
  /// position attribute location
  static GLint m_posLoc;
  /// color attribute location
  static GLint m_colLoc;
};

#endif

