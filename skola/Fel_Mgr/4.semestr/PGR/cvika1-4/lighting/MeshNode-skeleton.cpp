

#include <string.h>
#include <iostream>  // cerr cout

#include "pgr.h"   // includes all PGR libraries, like shader, glm, assimp ...

#include "MeshNode.h"

GLuint MeshNode::m_program = 0;

// transformation matrix uniforms
GLint MeshNode::m_PVMmatrixLoc = -1;
GLint MeshNode::m_VmatrixLoc      = -1;
GLint MeshNode::m_MmatrixLoc      = -1;
GLint MeshNode::m_NormalMatrixLoc = -1;
// vertex attributes
GLint MeshNode::m_posLoc = -1;
GLint MeshNode::m_normalLoc = -1;
GLint MeshNode::m_texCoordLoc = -1;
// material uniforms
GLint MeshNode::m_diffuseLoc = -1;
GLint MeshNode::m_ambientLoc = -1;
GLint MeshNode::m_specularLoc = -1;
GLint MeshNode::m_shininessLoc = -1;
// timer uniform
GLint MeshNode::m_timeLoc = -1;  // elapsed time in milliseconds

//carLights uniforms
/// jeep position in model coordinates uniform
GLint MeshNode::m_jeepLightLPosLoc = -1;
GLint MeshNode::m_jeepLightRPosLoc = -1;
/// jeep direction in model coordinates uniforms
GLint MeshNode::m_jeepDirectionLoc = -1;

glm::vec4 MeshNode::m_jeepDirection = glm::vec4(0.0);
glm::vec4 MeshNode::m_jeepLightLPos = glm::vec4(0.0);
glm::vec4 MeshNode::m_jeepLightRPos = glm::vec4(0.0);


//--------------------------------------------------------------------------------------------------
//---------------------------------------- MeshNode --------------------------------------------
//--------------------------------------------------------------------------------------------------
MeshNode::MeshNode(const char* file_name, SceneNode* parent):
  SceneNode(file_name, parent), m_mesh(NULL)
{
  if(m_program == 0)
  {
    loadProgram();
  }

  glGenVertexArrays(1, &m_vertexArrayObject );
}

MeshNode::~MeshNode()
{
  glDeleteVertexArrays( 1, &m_vertexArrayObject );
  pgr::deleteProgramAndShaders( m_program );
}


void MeshNode::loadProgram()
{
  pgr::deleteProgramAndShaders( m_program );
  std::vector<GLuint> shaderList;

  // Push vertex shader and fragment shader
  shaderList.push_back(pgr::createShader(GL_VERTEX_SHADER,   "MeshNode-skeleton.vp"));
  shaderList.push_back(pgr::createShader(GL_FRAGMENT_SHADER, "MeshNode.fp"));

  // Create the program with two shaders
  m_program = pgr::createProgram(shaderList);

  // if the shader does not have this uniform - return -1
  m_posLoc          =  glGetAttribLocation( m_program, "position");
  m_normalLoc       =  glGetAttribLocation( m_program, "normal");
  m_texCoordLoc     =  glGetAttribLocation( m_program, "texCoord");

  m_PVMmatrixLoc    =  glGetUniformLocation(m_program, "PVMmatrix");
  m_VmatrixLoc      =  glGetUniformLocation(m_program, "Vmatrix");
  m_MmatrixLoc      =  glGetUniformLocation(m_program, "Mmatrix");
  m_NormalMatrixLoc =  glGetUniformLocation(m_program, "NormalMatrix");

  m_ambientLoc      =  glGetUniformLocation(m_program, "material.ambient");
  m_diffuseLoc      =  glGetUniformLocation(m_program, "material.diffuse");
  m_specularLoc     =  glGetUniformLocation(m_program, "material.specular");
  m_shininessLoc    =  glGetUniformLocation(m_program, "material.shininess");

  m_timeLoc         =  glGetUniformLocation(m_program, "time" ); 

  m_jeepLightLPosLoc= glGetUniformLocation(m_program, "jeepLightLPos" );
  m_jeepLightRPosLoc= glGetUniformLocation(m_program, "jeepLightRPos" );
  m_jeepDirectionLoc= glGetUniformLocation(m_program, "jeepDirection" );

}

void MeshNode::setGeometry(MeshGeometry* mesh_p) {

  if(mesh_p == NULL)
	return;

  m_mesh = mesh_p; 

  glBindVertexArray( m_vertexArrayObject );
    glBindBuffer(GL_ARRAY_BUFFER, mesh_p->getVertexBuffer());
    glEnableVertexAttribArray(m_posLoc);
    glVertexAttribPointer(m_posLoc, 3, GL_FLOAT, GL_FALSE, 0, 0);

	if(m_mesh->normalsUsed() == true) {
      glBindBuffer(GL_ARRAY_BUFFER, mesh_p->getNormalBuffer());
      glEnableVertexAttribArray(m_normalLoc);
      glVertexAttribPointer(m_normalLoc, 3, GL_FLOAT, GL_FALSE, 0, 0);
	}

    // todo: up to 4 texture coordinates can be there
	if(m_mesh->texCoordsUsed() == true) {
      glBindBuffer(GL_ARRAY_BUFFER, mesh_p->getTexCoordBuffer());
      glEnableVertexAttribArray(m_texCoordLoc);
      glVertexAttribPointer(m_texCoordLoc, 3, GL_FLOAT, GL_FALSE, 0, 0);   //(str)
	}

    glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, mesh_p->getElementBuffer() );
    
  glBindVertexArray( 0 );
}

void MeshNode::draw(const glm::mat4 & view_matrix, const glm::mat4 & projection_matrix)
{
  //glPolygonMode( GL_FRONT_AND_BACK, GL_LINE );
  glPolygonMode( GL_FRONT_AND_BACK, GL_FILL );
  // inherited draw - draws all children
  SceneNode::draw(view_matrix, projection_matrix);

 
  glm::mat4 PVMmatrix = projection_matrix  * view_matrix * globalMatrix();
  glm::mat4   Vmatrix = view_matrix;
  glm::mat4   Mmatrix = globalMatrix();

  glUseProgram(m_program);

  glUniformMatrix4fv(m_PVMmatrixLoc, 1, GL_FALSE, glm::value_ptr(PVMmatrix) ); // model-view-projection
  glUniformMatrix4fv(  m_VmatrixLoc, 1, GL_FALSE, glm::value_ptr(  Vmatrix) ); // view
  glUniformMatrix4fv(  m_MmatrixLoc, 1, GL_FALSE, glm::value_ptr(  Mmatrix) ); // model
  // glm::mat4 NormalMatrix = glm::transpose( glm::inverse( Vmatrix * Mmatrix )); // should be this way, but inverse returns bad matrix
  // glUniformMatrix4fv(m_NormalMatrixLoc, 1, GL_FALSE, glm::value_ptr(NormalMatrix) );    // correct matrix for non-rigid transf
  glUniformMatrix4fv(m_NormalMatrixLoc, 1, GL_FALSE, glm::value_ptr( Vmatrix * Mmatrix) ); // previous version without normal matrix

  glUniform1f( m_timeLoc, m_time );        // in seconds 

  glUniform3fv( m_jeepDirectionLoc, 1, glm::value_ptr(view_matrix * m_jeepDirection) );
  glUniform3fv( m_jeepLightLPosLoc, 1, glm::value_ptr(view_matrix * m_jeepLightLPos) );
  glUniform3fv( m_jeepLightRPosLoc, 1, glm::value_ptr(view_matrix * m_jeepLightRPos) );


  glBindVertexArray( m_vertexArrayObject );
 
  // draw all submeshes = all material groups from SubMeshList
  SSubMesh* subMesh_p = NULL;

  for(unsigned mat=0; mat<m_mesh->getSubMeshCount(); mat++) {

    subMesh_p = m_mesh->getSubMesh(mat);

	glUniform3fv(m_diffuseLoc,  1, subMesh_p->diffuse);  // 2nd parameter must be 1 - it declares number of vectors in the vector array
	glUniform3fv(m_ambientLoc,  1, subMesh_p->ambient);
	glUniform3fv(m_specularLoc, 1, subMesh_p->specular);
	glUniform1f(m_shininessLoc,    subMesh_p->shininess);
    
	//glDrawElements( GL_TRIANGLES, subMesh_p->nIndices, GL_UNSIGNED_INT, (void *) (subMesh_p->startIndex * sizeof(unsigned int)));
    // base vertex must be added to the indices for each block (as they are rellative inside the submesh and start from 0)
    // do it in Resources::Load() and use DrawElements, or use glDrawElementsBaseVertex
    glDrawElementsBaseVertex( GL_TRIANGLES, subMesh_p->nIndices, GL_UNSIGNED_INT, (void *) (subMesh_p->startIndex * sizeof(unsigned int)), subMesh_p->baseVertex );
  }
   
  glBindVertexArray( 0 );
}


// store direction and position of the jeep in world coordinates 
void MeshNode::storePositionAndDirection()
{
  MeshNode::m_jeepDirection = glm::normalize( m_global_mat * glm::vec4( 0.0,  -1.0, 0.0, 0.0) );  // direction of the jeep car
  MeshNode::m_jeepLightLPos =                 m_global_mat * glm::vec4(-0.28,  0.0, 0.0, 1.0);
  MeshNode::m_jeepLightRPos =                 m_global_mat * glm::vec4( 0.28,  0.0, 0.0, 1.0);

}


//--------------------------------------------------------------------------------------------------
//--------------------------------------------- MeshGeometry -------------------------------------------
//--------------------------------------------------------------------------------------------------
MeshGeometry::MeshGeometry(void) : m_nVertices(0), m_nIndices(0) {

  glGenBuffers(1, &m_vertexBufferObject);
  glGenBuffers(1, &m_normalBufferObject);
  glGenBuffers(1, &m_texCoordBufferObject);
  glGenBuffers(1, &m_elementArrayBufferObject);
}

MeshGeometry::~MeshGeometry() {

  glDeleteBuffers(1, &m_vertexBufferObject);
  glDeleteBuffers(1, &m_normalBufferObject);
  glDeleteBuffers(1, &m_texCoordBufferObject);
  glDeleteBuffers(1, &m_elementArrayBufferObject );
    
  SubMeshList::iterator it;

  for(it = m_subMeshList.begin(); it != m_subMeshList.end(); ++it) {
    delete (*it);
  }

  m_subMeshList.clear();
}

// pass the data as blocks of bytes to OpenGL buffers
void MeshGeometry::setMesh(unsigned int verticesCount, float* vertices, float* normals, float* texCoords, unsigned int indicesCount, GLuint* indices) {

  // TODO: asssert if vertices == NULL or indices == NULL

  m_nVertices = verticesCount;

  glBindBuffer(GL_ARRAY_BUFFER, m_vertexBufferObject);
  glBufferData(GL_ARRAY_BUFFER, m_nVertices * 3 * sizeof(float), vertices, GL_STATIC_DRAW);    // xyz
  glBindBuffer(GL_ARRAY_BUFFER, 0);

  if(normals != NULL) {
	m_useNormals = true;
    glBindBuffer(GL_ARRAY_BUFFER, m_normalBufferObject);
    glBufferData(GL_ARRAY_BUFFER, m_nVertices * 3 * sizeof(float), normals, GL_STATIC_DRAW);   // nor
    glBindBuffer(GL_ARRAY_BUFFER, 0);
  }

  if(texCoords != NULL) {
	m_useTexCoords = true;
    glBindBuffer(GL_ARRAY_BUFFER, m_texCoordBufferObject);
    glBufferData(GL_ARRAY_BUFFER, m_nVertices * 2 * sizeof(float), texCoords, GL_STATIC_DRAW); // st
    glBindBuffer(GL_ARRAY_BUFFER, 0);
  }

  m_nIndices = indicesCount;

  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, m_elementArrayBufferObject);
  glBufferData(GL_ELEMENT_ARRAY_BUFFER, m_nIndices * sizeof(unsigned int), indices, GL_STATIC_DRAW); 
  glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
}
