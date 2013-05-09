#include <iostream>
#include <map>
#include <stdexcept>
#include <string>
#include <iostream>

#include <assimp/assimp.hpp>
#include <assimp/aiScene.h>
#include <assimp/aiPostProcess.h>


#include "Resources.h"
#include "MeshNode.h"


//--------------------------------------------------------------------------------------------------
//-------------------------------------- ResourceManager -------------------------------------------
//--------------------------------------------------------------------------------------------------

template <class T>
ResourceManager<T>::~ResourceManager() {
 typename ResourceMap::iterator it;

  for(it = resources.begin(); it != resources.end(); ++it) {
    delete it->second;
  }

  resources.clear();
}

template <class T>
void ResourceManager<T>::Register(const std::string & name, T * resource) {
  std::pair<std::string,T*> resourcePair;

  resourcePair = make_pair(name, resource);
  resources.insert(resourcePair);
  //resources.insert( pair<string,T*>( name, resource ) );
}

template <class T>
void ResourceManager<T>::UnRegister(const std::string & name) {        
 typename ResourceMap::iterator it = resources.find( name );

  if(it != resources.end()) {
    resources.erase(it);
  }
}



//--------------------------------------------------------------------------------------------------
//--------------------------------------- ImageManager ---------------------------------------------
//--------------------------------------------------------------------------------------------------

ImageManager* ImageManager::m_pInstance = NULL; 

/** This function is called to create an instance of the class.
    Calling the constructor publicly is not allowed. The constructor
    is private and is only called by this Instance function.
*/
ImageManager* ImageManager::Instance() {

	if (m_pInstance == NULL)   // Only allow one instance of class to be generated.
		m_pInstance = new ImageManager();

	return m_pInstance;
}

Image * ImageManager::Load(const std::string & path) {
 int width; int height;
 BYTE * rgbdata;

 //LoadPNG( &width, &height, &rgbdata ); // load PNG data as RGB triplet

  Image * image = new Image(width, height, rgbdata);

  this->Register(path, image);

  return image;
 }

 void ImageManager::Free(std::string const & name ) {

  this->UnRegister( name );
}

//--------------------------------------------------------------------------------------------------
//--------------------------------------- ModelManager ---------------------------------------------
//--------------------------------------------------------------------------------------------------

ModelManager* ModelManager::m_pInstance = NULL; 

/** This function is called to create an instance of the class.
    Calling the constructor publicly is not allowed. The constructor
    is private and is only called by this Instance function.
*/
ModelManager* ModelManager::Instance() {

	if (m_pInstance == NULL)   // Only allow one instance of class to be generated.
		m_pInstance = new ModelManager();

	return m_pInstance;
}

MeshGeometry * ModelManager::Load(const std::string & path) {

  MeshGeometry* meshGeometry_p = NULL; 
  
  //// Load asset from the file - for the reference - will be removed soon
  //const aiScene * scn = importer.ReadFile(path.c_str(), 
  //    aiProcess_SortByPType             // Sort by primitive type into groups. Remove unrequested (all except triangles and polygons).
  //  | aiProcess_Triangulate             // Triangulate polygons (if any).
  //  | aiProcess_PreTransformVertices    // Transforms scene hierarchy into one root with geometry-leafs only. For more see Doc.
  //  | aiProcess_GenSmoothNormals        // Calculate normals per vertex. 
  //  | aiProcess_GenUVCoords             // Force regenerate spherical/cylindrical/planar... coords into UVs.     

#define VERBOSE

  //*** SETUP ASSET LOADER ***
  Assimp::Importer importer;   ///< asset loader
#ifdef VERBOSE
  importer.SetExtraVerbose(true);
#endif

  //  // What primitives to remove from the loaded asset
  //importer.SetPropertyInteger(AI_CONFIG_PP_SBP_REMOVE,
  //      aiPrimitiveType_POINT         // Remove points
  //    | aiPrimitiveType_LINE          // Remove lines
  //    // Polygons will be triangulated. We want only triangles.
  //);

  // Unitize object in size
  importer.SetPropertyInteger(AI_CONFIG_PP_PTV_NORMALIZE, 1); //used by aiProcess_PreTransformVertices to normalize vertices to fit into (-1..1)^3


  // Load asset from the file
  const aiScene * scn = importer.ReadFile(path.c_str(), 0
    //| aiProcess_SortByPType             // Sort by primitive type into groups. Remove unrequested (all except triangles and polygons).
    | aiProcess_Triangulate             // Triangulate polygons (if any).
    | aiProcess_PreTransformVertices    // Transforms scene hierarchy into one root with geometry-leafs only. For more see Doc.
      //| aiProcess_GenSmoothNormals        // Calculate normals per vertex. 
//    | aiProcess_GenUVCoords             // Force regenerate spherical/cylindrical/planar... coords into UVs.     
      //| aiProcess_JoinIdenticalVertices    
      //| aiProcess_RemoveComponent         // Removes above defined components
      //| aiProcess_PreTransformVertices    // Transforms scene hierarchy into one root with geometry-leafs only. For more see Doc.
      //| aiProcess_ImproveCacheLocality    
      //| aiProcess_RemoveRedundantMaterials
      //| aiProcess_FindDegenerates         // Find degenerated polys and convert them into lines/points, which are then removed.
      //| aiProcess_FindInvalidData         // In this step we can lost normals if they are incorrectly exported from the modeler. 
      //                                    // Thats why we generate them.
      //| aiProcess_GenUVCoords             // Force regenerate spherical/cylindrical/planar... coords into UVs.
      //| aiProcess_TransformUVCoords       // Pretransform UV coords with texture matrix. Like in vertex shaders using gl_TextureMatrix.
      //| aiProcess_OptimizeMeshes
      //| aiProcess_CalcTangentSpace        // Calculate tangents/bitangents per vertex.
  
    );
  

  if(!scn)
  {
    std::cerr << importer.GetErrorString() << std::endl;
    return meshGeometry_p;
  }

  // Collapse obtained (postprocessed) hierarchy into one root node with array of meshes.
  // This step must be separated because of use [aiProcess_PreTransformVertices] which creates
  // only one root with leafs children - one mesh per child.
  //importer.ApplyPostProcessing(aiProcess_OptimizeGraph);

  if(scn->mNumMeshes < 1)
  {
    std::cerr << "no meshes found in scene " << path << std::endl;
    return meshGeometry_p;
  }

  std::cout << "loaded " << scn->mNumMeshes << " meshes" << std::endl;

  
  //*** CREATE AN INTERNAL STRUCTURE (preparations for rendering) ***//
  // merge all sub-meshes to one big mesh
  
  const unsigned FACE_VERT_COUNT = 3;  // triangles

  unsigned int m_nVertices = 0;  // vertex counter
  unsigned int m_nIndices  = 0;  // indices counter

  // sum all vertices and indices for all meshes
  for(unsigned m = 0; m < scn->mNumMeshes; ++m) {                 // count the overall 
    m_nVertices += scn->mMeshes[m]->mNumVertices;                 //   number of vertices 
    m_nIndices  += scn->mMeshes[m]->mNumFaces * FACE_VERT_COUNT;  //   and indices in all THE meshes            
  }

  float * vertices = new float[3 * m_nVertices]; // 3 floats per vertex (xyx)  
  float * cur_vert = vertices;

  float * normals = new float[3 * m_nVertices]; // 3 floats per vertex (nor)  
  float * cur_normal = normals;

  //TODO: just texture 0 for now
  float * textureCoords = new float[3 * m_nVertices];  //3 floats per vertex (str)
  float * cur_textureCoord = textureCoords;

  unsigned * indices = new unsigned[m_nIndices];   // indices to the vertices of the faces

  ////////// create MeshGeometry /////
  meshGeometry_p = new MeshGeometry();          // complete geometry (vertices with normals and indices for all subMeshes)

  unsigned startIndex = 0;  // for face indexing - index in the array of indexes 
  unsigned baseVertex = 0;  // for vertices block (base vertex is added to rellative index in the submesh to get absolute index in the array of vertices )

  for(unsigned m = 0; m < scn->mNumMeshes; ++m)
  {
    const aiMesh     *mesh = scn->mMeshes[m];

    // copy the vertices
    if( mesh->HasPositions() ) {
      memcpy(cur_vert, mesh->mVertices, mesh->mNumVertices * sizeof(float) * 3);
      cur_vert += mesh->mNumVertices * 3;
    }
    else 
      std::cerr << "no vertices found in mesh[" << m << "] in the scene " << path << std::endl;

    // copy the normals
    if( mesh->HasNormals() ) {
      memcpy(cur_normal, mesh->mNormals, mesh->mNumVertices * sizeof(float) * 3);
      cur_normal += mesh->mNumVertices * 3;
    }

    // copy texture coordinates
    for( unsigned i=0; i<1; ++i ) {   // TODO> up to 4 textures can be loaded 
      if( mesh->HasTextureCoords(i) ) {
        memcpy( cur_textureCoord, mesh->mTextureCoords[i], mesh->mNumVertices * sizeof(float) * 3);  
        cur_textureCoord += mesh->mNumVertices * 2;
      }
    }
    // copy the face index tripplets (we use triangular faces) to indices
    for (unsigned j = 0; j < mesh->mNumFaces; ++j) {
      memcpy( &indices[startIndex + j*FACE_VERT_COUNT], mesh->mFaces[j].mIndices, FACE_VERT_COUNT * sizeof(unsigned) );
    }


    // copy the material info to SSubMesh structure
    const aiMaterial *mat  = scn->mMaterials[mesh->mMaterialIndex];  
    // the material vertices are grouped together (done by mesh processing step), so we cycle through all meshes and add their materials 
    
    SSubMesh* subMesh_p = new SSubMesh;  // submesh from one material type
    aiColor3D color;
    aiString name;

    //for(unsigned i=0; i< mat->mNumProperties; i++ )
    //{
    //  std::cout << "Property[" << i << "] = " << mat->mProperties[i]->mKey.data << " = " << mat->mProperties[i]->mData << std::endl;
    //}

    //Get returns: aiReturn_SUCCESS 0 | aiReturn_FAILURE -1 | aiReturn_OUTOFMEMORY -3
    mat->Get(AI_MATKEY_NAME, name); // may be "" after the input mesh processing. Must be aiString type!
    subMesh_p->name = name.data;
       
    //mat->Get<aiColor3D>(AI_MATKEY_COLOR_AMBIENT, color);
    mat->Get(AI_MATKEY_COLOR_AMBIENT, color);
    subMesh_p->ambient[0] = color.r;
    subMesh_p->ambient[1] = color.g;
    subMesh_p->ambient[2] = color.b;

    mat->Get<aiColor3D>(AI_MATKEY_COLOR_DIFFUSE, color);
    subMesh_p->diffuse[0] = color.r;
    subMesh_p->diffuse[1] = color.g;
    subMesh_p->diffuse[2] = color.b;

    mat->Get<aiColor3D>(AI_MATKEY_COLOR_SPECULAR, color);
    subMesh_p->specular[0] = color.r;
    subMesh_p->specular[1] = color.g;
    subMesh_p->specular[2] = color.b;

    float shininess;
    //mat->Get<float>(AI_MATKEY_SHININESS, subMesh_p->shininess );
    mat->Get<float>(AI_MATKEY_SHININESS, shininess );
    subMesh_p->shininess = shininess / 4.0f;  // shininess divisor-not descibed anywhere  

    mat->Get<aiString>(AI_MATKEY_TEXTURE(aiTextureType_DIFFUSE, 0) , name );
    subMesh_p->textureName = name.data;


    // We ignore AI_MATKEY  OPACITY, REFRACTI, SHADING_MODEL

    // - indices to the element array
    subMesh_p->nIndices = mesh->mNumFaces * FACE_VERT_COUNT;
    subMesh_p->startIndex = startIndex;
    subMesh_p->baseVertex = baseVertex;

    startIndex += mesh->mNumFaces * FACE_VERT_COUNT;
    baseVertex += mesh->mNumVertices;   

    meshGeometry_p->addSubMesh(subMesh_p);  // add material to the MeshGeometry SubMeshList

  }
    
  // Finish the mesh by creating the buffer objects holding all vertices and indices
  meshGeometry_p->setMesh(m_nVertices, vertices, normals, NULL, m_nIndices, indices);   //TODO textures
   
  delete [] vertices;
  delete [] normals;
  delete [] textureCoords;
  delete [] indices;

  this->Register(path, meshGeometry_p);

  return meshGeometry_p;
}


/******************** todo textury ----
    if (material->GetTextureCount(aiTextureType_DIFFUSE) > 0) {
      // Get path to the texture image
      aiString fp;
      material->GetTexture(aiTextureType_DIFFUSE, 0, &fp);
      std::string texFilePath(fp.data);

      //// If image cannot be found, try to correct the path to be relative to 3Dmodel file
      //if (!FileExists(texFilePath)) { 
      //    if(verbose) cout << "      Invalid texture path found. Trying to recover." << endl;
      ////    texFilePath = MergeFilePaths(filePath, texFilePath);
      //}


      // If we have texture, load texture UV coords from the first channel
      // For now, only one diffuse texture per mesh is supported
      //float * texCoords = new GLfloat[2*m_nVertices];            //st
      //memcpy( texCoords, mesh->mTextureCoords[0], 3*m_nVertices * sizeof(GL_float) );

      //// Create texture
      //GLuint tex = 0;
      //sf::Image img;

      //if(VERBOSE) cout << "      Loading object [" << i << "] texture: " << texFilePath << endl;
      //if (!img.LoadFromFile(texFilePath)) {
      //    cout << "      Failed loading texture: " << texFilePath << endl;
      //    std::cin.get();
      //    exit(EXIT_FAILURE);
      //}

   //   glGenTextures(1, &tex);
   //   if (glewIsSupported("GL_VERSION_1_3 GL_ARB_multitexture")) glActiveTexture(GL_TEXTURE0);
   //   glBindTexture(GL_TEXTURE_2D, tex);
   //   //gluBuild2DMipmaps(GL_TEXTURE_2D, 3, (GLint)img.GetWidth(), (GLint)img.GetHeight(), GL_RGBA, GL_UNSIGNED_BYTE, img.GetPixelsPtr());
	  //glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, (GLint)img.GetWidth(), (GLint)img.GetHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, img.GetPixelsPtr());
   //   glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE);
   //   glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER, GL_LINEAR);
   //   glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER, GL_LINEAR);

   //   glBindTexture(GL_TEXTURE_2D, 0);

//      subMesh_p->material.texture = tex;
      
    }
***** konec todo textury**/
  //  meshGeometry_p->addSubMesh(subMesh_p);
  //}vv

void ModelManager::Free(const std::string & name ) {

  this->UnRegister( name );
}

MeshGeometry * ModelManager::LoadTerrain(const std::string & path) {

  MeshGeometry* meshGeometry_p = NULL; 
  
  long int m_nVertices;
  FILE* rawFile;
  char file[256];

  // resolution of raw file -> 513 x 513 grid
  const int _resX = 513;
  const int _resZ = 513;

  // distances between neighbour grid points along x, y, and z axis
  const float _deltaX = 1.0f / (_resX - 1);
  const float _deltaZ = 1.0f / (_resZ - 1);
  const float _deltaY = 1.0f / (_resX - 1) / (_resZ - 1); //256.0f; // height modulation

  // size of the grid
  const float _sizeX = 1.0f;
  const float _sizeZ = 1.0f;

  // origin of the grid - move the grid to the center of coordinate system
  const float _originX = -0.5f;
  const float _originZ = -0.5f;

  // the number of floats to read for heights and colors
  m_nVertices = _resX*_resZ;

  float *m_pVertices = NULL;
  m_pVertices = new float[3*m_nVertices];
  assert(m_pVertices);

  //float *_colors = NULL;
  //_colors = new float[size];
  //assert(_colors);

  // read the heights of terrain
  sprintf(file, "%s.raw", path.c_str());
  std::cout << "Loading terrain map file " << file << " [" << _resX << "x" << _resZ << "] ";
  std::cout.flush();
 
  rawFile = fopen(file, "rb");
  if (rawFile == NULL) {
    std::cerr << "ModelManager::LoadTerrain(): Can't open input raw file" << file << std::endl;
	return meshGeometry_p;
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

      m_pVertices[i] = _originX + x*_deltaX;
      m_pVertices[i+1] = height * _deltaY;
      m_pVertices[i+2] = _originZ + z*_deltaZ;
      //_colors[i] = _colors[i+1] = _colors[i+2] = 0.5; // color is grey by default
    }
    std::cout << ".";
    std::cout.flush();
  }
  std::cout << std::endl;
  fclose(rawFile);

/*
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
*/

  delete [] buffer;

  unsigned int m_nIndices = 2 * 3 * (_resX-1) * (_resZ-1); 
  unsigned int* m_pIndices = NULL;
  m_pIndices = new unsigned int[m_nIndices];
  int triIndex;

  // build array of indices
  // asi by to chtelo zvazit, zda nepouzit tringle strip (mit na vyber jak kreslit mesh - tringles nebo strip)
  counter = 0;
  for (int iz = 0; iz < _resZ-1; iz++) {
	triIndex = iz*_resX;
	for (int ix = 0; ix < _resX-1; ix++) {
		m_pIndices[counter++] = triIndex;
		m_pIndices[counter++] = triIndex+_resX;
		m_pIndices[counter++] = triIndex+1;

		m_pIndices[counter++] = triIndex+1;
		m_pIndices[counter++] = triIndex+_resX;
		m_pIndices[counter++] = triIndex+_resX+1;

		triIndex++;
	}
  }

  // TODO: load texture
  // TODO: generate texture coords
  // TODO: generate normals

  ////////// create MeshGeometry /////
  meshGeometry_p = new MeshGeometry();          // complete geometry (vertices with normals and indices for all subMeshes)

  SSubMesh* subMesh_p = new SSubMesh;  // submesh from one material type

  subMesh_p->ambient[0] = 1.0f;
  subMesh_p->ambient[1] = 1.0f;
  subMesh_p->ambient[2] = 1.0f;

  subMesh_p->diffuse[0] = 1.0f;
  subMesh_p->diffuse[1] = 1.0f;
  subMesh_p->diffuse[2] = 0.0f;

  subMesh_p->specular[0] = 1.0f;
  subMesh_p->specular[1] = 1.0f;
  subMesh_p->specular[2] = 1.0f;

  subMesh_p->shininess = 10.0f;

  // - indices to the element array
  subMesh_p->nIndices = m_nIndices;
  subMesh_p->startIndex = 0;
  subMesh_p->baseVertex = 0;

  meshGeometry_p->addSubMesh(subMesh_p);  // add material to the MeshGeometry SubMeshList
   
  // Finish the mesh by creating the buffer objects holding all vertices and indices
  meshGeometry_p->setMesh(m_nVertices, m_pVertices, NULL, NULL, m_nIndices, m_pIndices);  
   
  delete [] m_pVertices;
 // delete [] normals;
 // delete [] textureCoords;
  delete [] m_pIndices;

  this->Register(path, meshGeometry_p);

  return meshGeometry_p;
}



// ukazka pouziti:
//
//  ImageManager *imageManager;
//  Image *img = NULL;
//
//    if((image=imageManager->Get(filename)) == NULL) {
//		imageManager->Load(filename);
//	}


