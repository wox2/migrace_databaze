#include <assimp/assimp.hpp>
#include <assimp/aiScene.h>
#include <assimp/aiPostProcess.h>

#include <string.h>
#include <vector>
#include "Objects.h"
#include "pgr.h" // support classes for PGR
//#include <glm/glm.hpp> // vec3 normalize cross
#include <glm/gtc/type_ptr.hpp> // value_ptr
#include <glm/gtc/matrix_transform.hpp> //translate, rotate, scale

#include "data.h"


// class variables initialization
GLuint CDrawableObject::m_program = 0;
GLint CDrawableObject::m_PVMmatrixLoc = -1;
GLint CDrawableObject::m_posLoc = -1;
GLint CDrawableObject::m_colLoc = -1;

//----------------------------------------------------------------------------------
// CDrawableObject
//----------------------------------------------------------------------------------
CDrawableObject::CDrawableObject():m_vertexArrayObject(0), m_vertexBufferObject(0), m_nVertices(0)
{
  if(m_program == 0)
  {
    std::vector<GLuint> shaderList;

    // Push vertex shader and fragment shader
    shaderList.push_back(pgr::createShader(GL_VERTEX_SHADER, strVertexShader));
    shaderList.push_back(pgr::createShader(GL_FRAGMENT_SHADER, strFragmentShader));

    // Create the program with two shaders
    m_program = pgr::createProgram(shaderList);
    m_PVMmatrixLoc = glGetUniformLocation(m_program, "PVMmatrix");
    m_posLoc = glGetAttribLocation(m_program, "position");
    m_colLoc = glGetAttribLocation(m_program, "color");
  }

  glGenBuffers(1, &m_vertexBufferObject);
  glGenVertexArrays(1, &m_vertexArrayObject );   //PF

  init();
}


CDrawableObject::~CDrawableObject(void)
{
  glDeleteBuffers(1, &m_vertexBufferObject);
  glDeleteVertexArrays( 1, &m_vertexArrayObject );
}



//----------------------------------------------------------------------------------
// CPlaneObject
//----------------------------------------------------------------------------------
void CPlaneObject::draw(const glm::mat4 & model_matrix, const glm::mat4 & view_matrix, const glm::mat4 & projection_matrix)
{
  glm::mat4 matrix = projection_matrix * view_matrix * model_matrix;

  glUseProgram(m_program);
  glUniformMatrix4fv(m_PVMmatrixLoc, 1, GL_FALSE, glm::value_ptr(matrix));

  glBindVertexArray( m_vertexArrayObject );
  glDrawArrays(GL_TRIANGLE_STRIP, 0, m_nVertices); 
  glBindVertexArray( 0 );

}

// Create a square in the xz plane in given height
void CPlaneObject::init(void)
{  
  const GLfloat size = 5.0f;
  const GLfloat height = 0.0f;
  GLfloat vertices[] = {
	  //  x,      y,     z,     r,    g,    b 
	   size, height, -size,  0.3f, 0.8f, 0.3f,
	  -size, height, -size,  0.3f, 0.8f, 0.3f, 
	   size, height,  size,  0.3f, 0.8f, 0.3f, 
	  -size, height,  size,  0.3f, 0.8f, 0.3f, 
  };
  m_nVertices = 4;

  glBindBuffer(GL_ARRAY_BUFFER, m_vertexBufferObject);
  glBufferData( GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);
  glBindBuffer(GL_ARRAY_BUFFER, 0);

  glBindVertexArray( m_vertexArrayObject );
  glBindBuffer(GL_ARRAY_BUFFER, m_vertexBufferObject);
  glEnableVertexAttribArray(m_posLoc);
  glEnableVertexAttribArray(m_colLoc);
  glVertexAttribPointer(m_posLoc, 3, GL_FLOAT, GL_FALSE, 6*sizeof(GLfloat), (void*)0);
  glVertexAttribPointer(m_colLoc, 3, GL_FLOAT, GL_FALSE, 6*sizeof(GLfloat), (void*)(3 * sizeof(GLfloat)) );
  glBindVertexArray( 0 );
}

//----------------------------------------------------------------------------------
// CAxesObject
//----------------------------------------------------------------------------------
void CAxesObject::init(void)
{  
  const GLfloat size = 1.2f;
  GLfloat vertices[] = {
	  //  x,    y,    z,      r,    g,    b 
	   0.0f, 0.0f, 0.0f,   1.0f, 0.0f, 0.0f,
	   size, 0.0f, 0.0f,   1.0f, 0.0f, 0.0f,
	   0.0f, 0.0f, 0.0f,   0.0f, 1.0f, 0.0f,
	   0.0f, size, 0.0f,   1.0f, 1.0f, 0.0f,
	   0.0f, 0.0f, 0.0f,   0.0f, 0.0f, 1.0f,
	   0.0f, 0.0f, size,   0.0f, 0.0f, 1.0f,
  };
  m_nVertices = 6;

  glBindBuffer(GL_ARRAY_BUFFER, m_vertexBufferObject);
  glBufferData( GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);
  glBindBuffer(GL_ARRAY_BUFFER, 0);

  glBindVertexArray( m_vertexArrayObject );
  glBindBuffer(GL_ARRAY_BUFFER, m_vertexBufferObject);
  glEnableVertexAttribArray(m_posLoc);
  glEnableVertexAttribArray(m_colLoc);
  glVertexAttribPointer(m_posLoc, 3, GL_FLOAT, GL_FALSE, 6*sizeof(GLfloat), (void*)0);
  glVertexAttribPointer(m_colLoc, 3, GL_FLOAT, GL_FALSE, 6*sizeof(GLfloat), (void*)(3 * sizeof(GLfloat)) );
  glBindVertexArray( 0 );
}

void CAxesObject::draw(const glm::mat4 & model_matrix, const glm::mat4 & view_matrix, const glm::mat4 & projection_matrix)
{
  glm::mat4 matrix = projection_matrix * view_matrix * model_matrix;

  glUseProgram(m_program);
  glUniformMatrix4fv(m_PVMmatrixLoc, 1, GL_FALSE, glm::value_ptr(matrix));

  glBindVertexArray( m_vertexArrayObject );
  glDrawArrays(GL_LINES, 0, 6); 
  glBindVertexArray( 0 );

}
//----------------------------------------------------------------------------------
// CLoadedObject
//----------------------------------------------------------------------------------
void CLoadedObject::draw(const glm::mat4 & model_matrix, const glm::mat4 & view_matrix, const glm::mat4 & projection_matrix)
{
  
  // this additional transformation scales the model to appprox size +-1 and centered
  float scale = 0.025f;
  glm::mat4 model_translated = glm::translate( model_matrix, glm::vec3(0.0f, 20*scale, -11.5f*scale) );
  glm::mat4 model_scaled = glm::scale( model_translated, glm::vec3(scale) );

  //matrix.translate(Vec3f(0.0f, 20*scale, -11.5f*scale));
  //matrix.scale(Vec3f(scale, scale, scale ));

  glm::mat4 matrix = projection_matrix * view_matrix * model_scaled;

  glUseProgram(m_program);
  glUniformMatrix4fv(m_PVMmatrixLoc, 1, GL_FALSE, glm::value_ptr(matrix));

  glBindVertexArray( m_vertexArrayObject );
  glDrawArrays(GL_TRIANGLES, 0, m_nVertices); 
  glBindVertexArray( 0 );

}
/// 
/** load the mesh from file 
 * \param model_name is the model file name 
 */
bool CLoadedObject::loadMesh( std::string model_name )
{
  //m_model_name = model_name;

  Assimp::Importer imp;

  const aiScene * scn = imp.ReadFile(model_name.c_str(), aiProcess_Triangulate | aiProcess_GenSmoothNormals | aiProcess_GenUVCoords);

  if(!scn)
  {
    std::cerr << imp.GetErrorString() << std::endl;
    return false;
  }

  if(scn->mNumMeshes < 1)
  {
    std::cerr << "no meshes found in scene " << model_name << std::endl;
    return false;
  }

  std::cout << "loaded " << scn->mNumMeshes << " meshes" << std::endl;

  // merge all sub-meshes to one big mesh
  m_nVertices = 0;
  for(unsigned m = 0; m < scn->mNumMeshes; ++m)
    m_nVertices += scn->mMeshes[m]->mNumVertices;

  float * vertices = new float[7 * m_nVertices]; // 7 floats per vertex (xyz + RGBA)
  float * cur_vert = vertices;                   // all vertices first
  float * cur_col = vertices + 3 * m_nVertices;  // than all colors

  for(unsigned m = 0; m < scn->mNumMeshes; ++m)
  {
    aiMesh * mesh = scn->mMeshes[m];
    memcpy(cur_vert, mesh->mVertices, mesh->mNumVertices * sizeof(float) * 3);

    aiMaterial * material = scn->mMaterials[mesh->mMaterialIndex];
    // copy mesh material color to all mesh vertices
    for(unsigned v = 0; v < mesh->mNumVertices; ++v)
    {
      aiColor4D color;
      material->Get<aiColor4D>(AI_MATKEY_COLOR_DIFFUSE, color);
      *cur_col++ = color.r;
      *cur_col++ = color.g;
      *cur_col++ = color.b;
      *cur_col++ = color.a;
    }

    cur_vert += mesh->mNumVertices * 3;
  }

  // create vertex buffer
  glBindBuffer(GL_ARRAY_BUFFER, m_vertexBufferObject);
  //glBufferData( GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);
  glBufferData(GL_ARRAY_BUFFER, m_nVertices * sizeof(float) * 7, vertices, GL_STATIC_DRAW);
  glBindBuffer(GL_ARRAY_BUFFER, 0);

  delete [] vertices;

  // create VAO
  glBindVertexArray( m_vertexArrayObject );
  glBindBuffer(GL_ARRAY_BUFFER, m_vertexBufferObject);
  glEnableVertexAttribArray(m_posLoc);
  glEnableVertexAttribArray(m_colLoc);
  glVertexAttribPointer(m_posLoc, 3, GL_FLOAT, GL_FALSE, 0, (void*)0);
  glVertexAttribPointer(m_colLoc, 4, GL_FLOAT, GL_FALSE, 0, (void*)(3 * sizeof(float) * m_nVertices));

  glBindVertexArray( 0 );

  return true;
}


void CLoadedObject::init(void)
{ 
  // load the obj file 
  loadMesh(m_model_name);
}
