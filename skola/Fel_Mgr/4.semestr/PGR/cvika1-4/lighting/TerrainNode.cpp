#include <cassert>
#include <cstdio>
#include <iostream>

#include "TerrainNode.h"
//#include "util.h"
#include "pgr.h"

using namespace std;

GLuint TerrainNode::m_program = 0;
GLint TerrainNode::m_TERRmatrixLoc = -1;
GLint TerrainNode::m_posLoc = -1;
GLint TerrainNode::m_colLoc = -1;

// For all tasks - multiple colors and matrix multiplication
static const std::string strVertexShader(
  "#version 130\n"
  "uniform mat4 TERRmatrix;\n"
  "in vec4 position;\n"
  "in vec4 color;\n"
  "smooth out vec4 theColor;\n"
  "void main()\n"
  "{\n"
  "	gl_Position = TERRmatrix * position;\n"
  "	theColor = color;\n"
  "}\n"
);

static const std::string strFragmentShader(
  "#version 130\n"
  "smooth in vec4 theColor;\n"
  "out vec4 outputColor;\n"
  "void main()\n"
  "{\n"
  "	outputColor = theColor;\n"
  "}\n"
);


TerrainNode::TerrainNode(const char * name, SceneNode * parent):
  SceneNode(name, parent), 
  m_vertexBufferObject(0),
  vertexData(0),
  m_nVertices(0)
{
  if (m_program == 0)
  {
	// load the shaders for the first time
    std::vector<GLuint> shaderList;

    // Push vertex shader and fragment shader
    shaderList.push_back(pgr::createShader(GL_VERTEX_SHADER, strVertexShader));
    shaderList.push_back(pgr::createShader(GL_FRAGMENT_SHADER, strFragmentShader));

    // Create the program with two shaders
    m_program = pgr::createProgram(shaderList);
    m_TERRmatrixLoc = glGetUniformLocation(m_program, "TERRmatrix");
    m_posLoc = glGetAttribLocation(m_program, "position");
    m_colLoc = glGetAttribLocation(m_program, "color");
  }

  glGenBuffers(1, &m_vertexBufferObject);
  glGenVertexArrays(1, &m_vertexArrayObject );   //PF
}


/* Loads a terragen file as well as a texture for it,  */
/* interpretting it as a terrain. Resolution must be 513 x 513. */
int
TerrainNode::load(const char *baseFilename) {
 long int size;
 FILE* rawFile;
 char file[256];
 //char errorString[256];

  // resolution of raw file -> 513x513 
  const int _resX = 513;
  const int _resZ = 513;

  // distances between neighbour grid points along x, y, and z axis
  const float _deltaX = 1.0f/_resX;
  const float _deltaZ = 1.0f/_resZ;
  const float _deltaY = 1.0f/256.0f; // height modulation

  // size of the grid
  const float _sizeX = 1.0f;
  const float _sizeZ = 1.0f;

  // origin of the grid - move the grid to the center of coordinate system
  const float _originX = -0.5f;
  const float _originZ = -0.5f;

  // the number of floats to read for heights and colors
  size = 3L*_resX*_resZ;

  float *_vertices = 0;
  _vertices = new float[size];
  assert(_vertices);
  float *_colors = 0;
  _colors = new float[size];
  assert(_colors);

  // read the heights of terrain
  sprintf(file, "%s.raw", baseFilename);
  cout << "Loading terrain map file " << file << " [" << _resX << "x" << _resZ << "] ";
  cout.flush();
 
  rawFile = fopen(file, "rb");
  if (rawFile == NULL) {
    cerr << "CTerrain::Load(): Can't open input raw file" << file << endl;
	return -1;
  }

  typedef unsigned char BYTE;
  BYTE *buffer = new BYTE[3*_resX]; // 2 bytes per one height, 3 bytes per one color
  int counter;
  float height, *cPtr;
  BYTE low, high;

  // read a terragen file that contains grid heights  
  for(int z=0; z<_resZ; z++) {
	counter = 0;
    fread(buffer, 1, 2*_resX, rawFile);

    for(int x=0; x<_resX; x++) {
	  low = buffer[counter++];   // fread((char*)&low, 1, 1, rawFile);
	  high = buffer[counter++];  // fread((char*)&high, 1, 1, rawFile);
      height = (float) (high * 0xFFL + low);
	  // index for vertex in the array of floats
	  int i = (_resZ * z + x) * 3;

      _vertices[i] = _originX + x*_deltaX;
      _vertices[i+1] = height * _deltaY;
      _vertices[i+2] = _originZ + z*_deltaZ;
      _colors[i] = _colors[i+1] = _colors[i+2] = 0.5; // color is grey by default
    }
    cout << ".";
    cout.flush();
  }
  cout << endl;
  fclose(rawFile);

  // Read the texture with colors for vertices
  FILE* tgaFile;
  BYTE tmp[18];

  cout << "Loading terrain texture file " << file << " [" << _resX << "x" << _resZ << "] ";
  cout.flush();

  tgaFile = fopen(file, "rb");
  if (tgaFile == NULL) {
    cerr << "CTerrain::Load(): Can't open input tga file " << file << endl;
  }

  // read a targa (*.tga) file - contains a texture for terrain
  // skip header
  fread((char*)tmp, 18, 1, tgaFile);
  cPtr = _colors;
  // read colors from file
  for(int z=0; z<_resZ; z++) {
  	counter = 0;
	// one line
    fread(buffer, 1, 3*_resX, tgaFile);
    for(int x=0; x<_resX; x++) {
	  // index for vertex in the array of floats
	  int i = (_resZ * z + x) * 3;
      
  	  _colors[i+2] = (float)buffer[counter++] / 255.0f; // blue channel
  	  _colors[i+1] = (float)buffer[counter++] / 255.0f; // green channel
  	  _colors[i] = (float)buffer[counter++] / 255.0f; // red channel
    }
    cout << ".";
    cout.flush();
  }
  cout << endl;
  fclose(tgaFile);
  delete [] buffer;

  // Now create the vertices and colors for triangles by triangle strip
  // 2 triangles per cell
  // 3 vertices per triangle
  // 3 floats vertex
  int sizeV = 2 * 3 * 3 * (_resX-1) * (_resZ-1); // the size for vertices only
  vertexData = new float[2 * sizeV]; // for vertices and colors
  assert(vertexData);
  // copy the data for heights and vertices for OpenGL bufferObject
  int iv = 0; // index for vertices
  int ic = sizeV; // index for vertices

  for (int iz = 0; iz < _resZ-1; iz++) {
	for (int ix = 0; ix < _resX-1; ix++) {
		// each cell has 2 triangles - geometry + color
		// geometry vertices for the first triangle, the first vertex
		vertexData[iv++] = _vertices[(_resZ * iz + ix) * 3]; // x
		vertexData[iv++] = _vertices[(_resZ * iz + ix) * 3 + 1]; // y
		vertexData[iv++] = _vertices[(_resZ * iz + ix) * 3 + 2]; // z
		// the second vertex
		vertexData[iv++] = _vertices[(_resZ * iz + ix+1) * 3]; // x
		vertexData[iv++] = _vertices[(_resZ * iz + ix+1) * 3 + 1]; // y
		vertexData[iv++] = _vertices[(_resZ * iz + ix+1) * 3 + 2]; // z
		// the third vertex
		vertexData[iv++] = _vertices[(_resZ * (iz+1) + ix) * 3]; // x
		vertexData[iv++] = _vertices[(_resZ * (iz+1) + ix) * 3 + 1]; // y
		vertexData[iv++] = _vertices[(_resZ * (iz+1) + ix) * 3 + 2]; // z

		// geometry vertices for the second triangle, the first vertex
		vertexData[iv++] = _vertices[(_resZ * (iz+1) + ix) * 3]; // x
		vertexData[iv++] = _vertices[(_resZ * (iz+1) + ix) * 3 + 1]; // y
		vertexData[iv++] = _vertices[(_resZ * (iz+1) + ix) * 3 + 2]; // z
		// the second vertex
		vertexData[iv++] = _vertices[(_resZ * iz + ix+1) * 3]; // x
		vertexData[iv++] = _vertices[(_resZ * iz + ix+1) * 3 + 1]; // y
		vertexData[iv++] = _vertices[(_resZ * iz + ix+1) * 3 + 2]; // z
		// the third vertex
		vertexData[iv++] = _vertices[(_resZ * (iz+1) + ix+1) * 3]; // x
		vertexData[iv++] = _vertices[(_resZ * (iz+1) + ix+1) * 3 + 1]; // y
		vertexData[iv++] = _vertices[(_resZ * (iz+1) + ix+1) * 3 + 2]; // z

		assert(ic < 2* sizeV);
		// colors for the first triangle, the first vertex
		vertexData[ic++] = _vertices[(_resZ * iz + ix) * 3]; // x
		vertexData[ic++] = _vertices[(_resZ * iz + ix) * 3 + 1]; // y
		vertexData[ic++] = _vertices[(_resZ * iz + ix) * 3 + 2]; // z
		// the second vertex
		vertexData[ic++] = _vertices[(_resZ * iz + ix+1) * 3]; // x
		vertexData[ic++] = _vertices[(_resZ * iz + ix+1) * 3 + 1]; // y
		vertexData[ic++] = _vertices[(_resZ * iz + ix+1) * 3 + 2]; // z
		// the third vertex
		vertexData[ic++] = _vertices[(_resZ * (iz+1) + ix) * 3]; // x
		vertexData[ic++] = _vertices[(_resZ * (iz+1) + ix) * 3 + 1]; // y
		vertexData[ic++] = _vertices[(_resZ * (iz+1) + ix) * 3 + 2]; // z

		// colors for the second triangle, the first vertex
		vertexData[ic++] = _vertices[(_resZ * (iz+1) + ix) * 3]; // x
		vertexData[ic++] = _vertices[(_resZ * (iz+1) + ix) * 3 + 1]; // y
		vertexData[ic++] = _vertices[(_resZ * (iz+1) + ix) * 3 + 2]; // z
		// the second vertex
		vertexData[ic++] = _vertices[(_resZ * iz + ix+1) * 3]; // x
		vertexData[ic++] = _vertices[(_resZ * iz + ix+1) * 3 + 1]; // y
		vertexData[ic++] = _vertices[(_resZ * iz + ix+1) * 3 + 2]; // z
		// the third vertex
		vertexData[ic++] = _vertices[(_resZ * (iz+1) + ix+1) * 3]; // x
		vertexData[ic++] = _vertices[(_resZ * (iz+1) + ix+1) * 3 + 1]; // y
		vertexData[ic++] = _vertices[(_resZ * (iz+1) + ix+1) * 3 + 2]; // z
	} // for ix
  } // for iz

  delete [] _vertices;
  delete [] _colors;

  glBindBuffer(GL_ARRAY_BUFFER, m_vertexBufferObject);
    glBufferData(GL_ARRAY_BUFFER, sizeof(vertexData), vertexData, GL_STATIC_DRAW);
  glBindBuffer(GL_ARRAY_BUFFER, 0);  

  glBindVertexArray( m_vertexArrayObject );
    glBindBuffer(GL_ARRAY_BUFFER, m_vertexBufferObject);
    glEnableVertexAttribArray(m_posLoc);
    glEnableVertexAttribArray(m_colLoc);
    // vertices of triangles
    glVertexAttribPointer(m_posLoc, 4, GL_FLOAT, GL_FALSE, 0, 0);
    // 8 = 4 + 4 floats per vertex - color
    glVertexAttribPointer(m_colLoc, 4, GL_FLOAT, GL_FALSE, 0, (void*)(4*sizeof(vertexData)/(8)));
  glBindVertexArray( 0 );

}


void TerrainNode::draw(const glm::mat4 & view_matrix, const glm::mat4 & projection_matrix)
{
  // inherited draw - draws all children
  SceneNode::draw(view_matrix, projection_matrix);

  glm::mat4 matrix = projection_matrix * view_matrix * globalMatrix();

  glUseProgram(m_program);
  glUniformMatrix4fv(m_TERRmatrixLoc, 1, GL_FALSE, glm::value_ptr(matrix));

  glBindBuffer(GL_ARRAY_BUFFER, m_vertexBufferObject);
  glEnableVertexAttribArray(m_posLoc);
  glEnableVertexAttribArray(m_colLoc);
  // vertices of triangles
  glVertexAttribPointer(m_posLoc, 4, GL_FLOAT, GL_FALSE, 0, 0);
  // 8 = 4 + 4 floats per vertex - color
  glVertexAttribPointer(m_colLoc, 4, GL_FLOAT, GL_FALSE, 0, (void*)(4*sizeof(vertexData)/(8)));

  glDrawArrays(GL_TRIANGLES, 0, sizeof(vertexData)/(8*sizeof(float))); // 8 = 4+4 floats per vertex

  glDisableVertexAttribArray(m_posLoc);
  glDisableVertexAttribArray(m_colLoc);
}
