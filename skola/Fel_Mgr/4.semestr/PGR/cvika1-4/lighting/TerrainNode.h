#ifndef TERRAINNODE_H
#define TERRAINNODE_H

#include <GL/glew.h>
#include <glm/glm.hpp> // vec3 normalize cross
#include <glm/gtc/type_ptr.hpp> // value_ptr
//#include <glm/gtc/matrix_transform.hpp> //translate, rotate, scale

#include "SceneNode.h"

/// Node, that draws pyramid from task3
class TerrainNode : public SceneNode
{
public:
  TerrainNode(const char * name = "terrain", SceneNode * parent = NULL);
  ~TerrainNode() {}

  /// load the terrain from file, returns 0 on success
  int load(const char * filename);

  /// reimplemented draw
  void draw(const glm::mat4 & view_matrix, const glm::mat4 & projection_matrix);

protected:
  /// identifier for the program
  static GLuint m_program;
  /// shader matrix location
  static GLint m_TERRmatrixLoc;
  /// position attribute location
  static GLint m_posLoc;
  /// color attribute location
  static GLint m_colLoc;

  /// identifier for the buffer object
  GLuint m_vertexBufferObject;
  /// identifier for the vertex array object
  GLuint m_vertexArrayObject;  // PF
  /// the vertices/color of the terrain in the grid
  float *vertexData;
  /// the number of vertices
  int m_nVertices;
};

#endif // TERRAINNODE_H


