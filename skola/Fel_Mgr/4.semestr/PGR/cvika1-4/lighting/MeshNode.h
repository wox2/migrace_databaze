#ifndef MESH_HODE_H
#define MESH_HODE_H

#include <GL/glew.h>
#include "SceneNode.h"
#include <string>


struct SSubMesh {
  /// material name, if availavle
  std::string name;
  /// material ambient component
  GLfloat ambient[3];
  /// material diffuse component
  GLfloat diffuse[3];
  /// material specular component
  GLfloat specular[3];
  /// material shininess
  GLfloat shininess;

  /// jak s texturou???? a co kdyz jich bude vic??? povolime pouze jednu
  // Diffuse texture[0] only
  std::string textureName; 

  /// number of indices in this submesh
  GLuint nIndices;
  /// first index in array of indices
  GLuint startIndex;
  ///vertex in array of vertices added to index in the index buffer
  GLuint baseVertex;
};

//--------------------------------------------------------------------------------------------------
//--------------------------------------------- MeshGeometry ---------------------------------------
//--------------------------------------------------------------------------------------------------
// holds the complete geometry of the mesh, grouped to materialGroups with single material each
// holds all vertices from all submeshes packed to buffer objects 
//       called m_vertexBufferObject, m_normalBufferObject and m_texCoordBufferObject
//
class MeshGeometry {

  typedef std::vector<SSubMesh*> SubMeshList;

public:

  MeshGeometry(void);
  ~MeshGeometry();

  void setMesh(
	unsigned int verticesCount,
	float* vertices,
	float* normals,
	float* texCoords,
	unsigned int indicesCount,
	GLuint* indices
  );

  // put submeshes (storedin SSubMesh structure) to the SubMeshList
  void addSubMesh(SSubMesh* subMesh_p) {
    m_subMeshList.push_back(subMesh_p);
  }

  GLuint getSubMeshCount(void) {
    return m_subMeshList.size();
  }

  SSubMesh* getSubMesh(unsigned index) {
    if(index < m_subMeshList.size())
      return m_subMeshList[index];
	else
	  return NULL;
  }

  GLuint getVertexBuffer(void) {
	return m_vertexBufferObject;
  }

  GLuint getNormalBuffer(void) {
	return m_normalBufferObject;
  }

  GLuint getTexCoordBuffer(void) {
	return m_texCoordBufferObject;
  }

  GLuint getElementBuffer(void) {
	return m_elementArrayBufferObject;
  }

  GLuint getVerticesCount(void) {
    return m_nVertices;
  }

  GLuint getIndicesCount(void) {
	return m_nIndices;
  }

  bool normalsUsed(void) {
	return m_useNormals;
  }

  bool texCoordsUsed(void) {
	return m_useTexCoords;
  }

protected:

  /// identifier for the buffer object for vertices
  GLuint m_vertexBufferObject;
  /// identifier for the buffer object for normals
  GLuint m_normalBufferObject;
  /// identifier for the buffer object for texture coordinates
  GLuint m_texCoordBufferObject;

  /// list of materials
  //std::vector<SSubMesh*> m_subMeshList;
  SubMeshList m_subMeshList;

  /// identifier for the buffer object for indices
  GLuint m_elementArrayBufferObject;
  /// count of vertices af all the submeshes together
  GLuint m_nVertices;
  /// count of indices af all the submeshes together
  GLuint m_nIndices;

  ///
  bool m_useNormals;
  bool m_useTexCoords;
};

//--------------------------------------------------------------------------------------------------
//---------------------------------------- MeshNode ------------------------------------------------
//--------------------------------------------------------------------------------------------------
class MeshNode : public SceneNode {

public:
  MeshNode(const char* name = "<unk>", SceneNode* parent = NULL);
	//(const char* file_name = "", SceneNode* parent = NULL);
  ~MeshNode();

  void setGeometry(MeshGeometry* mesh);

  /// reimplemented draw
  void draw(const glm::mat4 & view_matrix, const glm::mat4 & projection_matrix);

  /// static method for meshonode shader program reload
  static void loadProgram();

  /// store the jeepPosition and direction - hack for jeep
  void storePositionAndDirection();

protected:

  /// identifier for the program
  static GLuint m_program;

  // uniforms
  /// shader model-view-projection matrix location
  static GLint m_PVMmatrixLoc;
  /// shader view                  matrix location
  static GLint m_VmatrixLoc;
  /// shader model                 matrix location
  static GLint m_MmatrixLoc;
  /// inverse transposed VMmatrix
  static GLint m_NormalMatrixLoc;
  
  /// material locations
  static GLint m_diffuseLoc;
  static GLint m_ambientLoc;
  static GLint m_specularLoc;
  static GLint m_shininessLoc;
    
  /// elapsed time in miliseconds location
  static GLint m_timeLoc;
  
  /// jeep position in model coordinates uniform
  static GLint m_jeepLightLPosLoc;
  static GLint m_jeepLightRPosLoc;
  /// jeep direction in model coordinates uniforms
  static GLint m_jeepDirectionLoc;

  static glm::vec4 m_jeepDirection; 
  static glm::vec4 m_jeepLightLPos;
  static glm::vec4 m_jeepLightRPos;

  // attributes
  /// position attribute location
  static GLint m_posLoc; 
  static GLint m_normalLoc;
  static GLint m_texCoordLoc;

  /// identifier for the vertex array object
  GLuint m_vertexArrayObject;

  MeshGeometry* m_mesh;
};



#endif

