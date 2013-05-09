#pragma once

#include <string>
#include <iostream>

#include "pgr.h"  // shader functions
#include <glm/glm.hpp> // vec3 normalize cross
#include <glm/gtc/matrix_transform.hpp> // perspective

///using namespace pgr;

/*!
 * \brief virtual class for a drawable object
 *
 */
class CDrawableObject
{
public:
	CDrawableObject(void);
	virtual ~CDrawableObject(void);

	//virtual void draw(const Matrix4f & model_matrix, const Matrix4f & view_matrix, const Matrix4f & projection_matrix) = 0;
	virtual void draw(const glm::mat4 & model_matrix, const glm::mat4 & view_matrix, const glm::mat4 & projection_matrix) = 0;
	virtual void init(void) {}

protected:	
  /// \name Class variables (single set for all class instances)
  /// @{

  /// identifier for the program
  static GLuint m_program;
  /// shader matrix location
  static GLint m_PVMmatrixLoc;
  /// position attribute location
  static GLint m_posLoc;
  /// color attribute location
  static GLint m_colLoc;

  /// @}

  /// \name Instance variables (single set for each class instance)
  /// @{

  /// identifier for the buffer object
  GLuint m_vertexBufferObject;
  /// identifier for the vertex array object
  GLuint m_vertexArrayObject;  // PF
  /// count of vertices
  GLuint m_nVertices;

  /// @}
   
};


class CPlaneObject : public CDrawableObject 
{
	public:
		CPlaneObject(void):CDrawableObject(){
			init();
		};
	
	    virtual void draw(const glm::mat4 & model_matrix, const glm::mat4 & view_matrix, const glm::mat4 & projection_matrix);
	    virtual void init(void);


};

class CAxesObject : public CDrawableObject 
{
	public:
		CAxesObject(void):CDrawableObject(){
			init();
		};
	
	    virtual void draw(const glm::mat4 & model_matrix, const glm::mat4 & view_matrix, const glm::mat4 & projection_matrix);
	    virtual void init(void);


};

class CLoadedObject : public CDrawableObject 
{
	public:
		//CLoadedObject(void):CDrawableObject(), m_model_name("data/bronco.obj"){};
		CLoadedObject(std::string model_name):CDrawableObject(), m_model_name(model_name){
			init();
		};
	
	    virtual void draw(const glm::mat4 & model_matrix, const glm::mat4 & view_matrix, const glm::mat4 & projection_matrix);
	    virtual void init(void);

    protected:
		bool loadMesh(std::string model_name);

       /// path and filename of the loaded model 
       std::string m_model_name;  
     
};


