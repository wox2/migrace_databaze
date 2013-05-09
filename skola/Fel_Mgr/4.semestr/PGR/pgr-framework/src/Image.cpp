
#include <IL/il.h>
#include <iostream>

#include "pgr.h"
#include "Image.h"

namespace pgr {

GLuint createTexture(const std::string &fileName, bool mipmap)
{
  // DevIL library has to be initialized (ilInit() must be called)

  // DevIL uses mechanism similar to OpenGL, each image has its ID (name)
  ILuint img_id;
  ilGenImages(1, &img_id); // generate one image ID (name)
  ilBindImage(img_id); // bind that generated id

  // set origin to LOWER LEFT corner (the orientation which OpenGL uses)
  ilEnable(IL_ORIGIN_SET);
  ilSetInteger(IL_ORIGIN_MODE, IL_ORIGIN_LOWER_LEFT);

  // this will load image data to the currently bound image
  if(ilLoadImage(fileName.c_str()) == IL_FALSE)
  {
    ilDeleteImages(1, &img_id);
    std::cerr << __FUNCTION__ << " cannot load image " << fileName << std::endl;
    return 0;
  }

  // if the image was correctly loaded, we can obtain some informatins about our image
  ILint width = ilGetInteger(IL_IMAGE_WIDTH);
  ILint height = ilGetInteger(IL_IMAGE_HEIGHT);
  ILenum format = ilGetInteger(IL_IMAGE_FORMAT);
  // there are many possible image formats and data types
  // we will convert all image types to RGB or RGBA format, with one byte per channel
  unsigned Bpp = ((format == IL_RGBA || format == IL_BGRA) ? 4 : 3);
  char * data = new char[width * height * Bpp];
  // this will convert image to RGB or RGBA, one byte per channel and store data to our array
  ilCopyPixels(0, 0, 0, width, height, 1, Bpp == 4 ? IL_RGBA : IL_RGB, IL_UNSIGNED_BYTE, data);
  // image data has been copied, we dont need the DevIL object anymore
  ilDeleteImages(1, &img_id);

  // bogus ATI drivers may require this call to work with mipmaps
  //glEnable(GL_TEXTURE_2D);

  // generate and bind one texture
  GLuint tex = 0;
  glGenTextures(1, &tex);
  glBindTexture(GL_TEXTURE_2D, tex);
  // set linear filtering
  glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, mipmap ? GL_LINEAR_MIPMAP_LINEAR : GL_LINEAR);
  glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
  // upload our image data to OpenGL
  glTexImage2D(GL_TEXTURE_2D, 0, Bpp == 4 ? GL_RGBA : GL_RGB, width, height, 0, Bpp == 4 ? GL_RGBA : GL_RGB, GL_UNSIGNED_BYTE, data);
  // create mipmaps
  if(mipmap)
    glGenerateMipmap(GL_TEXTURE_2D);

  // free our data (they were copied to OpenGL)
  delete [] data;

  // unbind the texture (just in case someone will mess up with texture calls later)
  glBindTexture(GL_TEXTURE_2D, 0);
  CHECK_GL_ERROR();
  return tex;
}

} // end namespace pgr
