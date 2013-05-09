/**
 * \file Image.h
 * \author Tomas Barak, Petr Felkel
 * \brief Helper functions for image loading
 * \date 2011-2012
 */

#ifndef _IMAGE_H
#define _IMAGE_H

#include <string>
#include <GL/glew.h>

namespace pgr {

/** Simple texture loading helper
 *
 * The DevIL library is used internally. Make sure ilInit() is called before this function (for example in pgr::initialize()).
 * The function has to be called after successful creation of the OpenGL context.
 * The texture will be created with linear filtering and mip-mapping enabled by default.
 * You can disable mip-mapping and mipmap generation setting the mipmap parameter to false.
 *
 * \param fileName the texture file name
 * \param mipmap Enable and generate mipmaps for this texture
 * \warning Make sure ilInit() was called prior to this function (for example in pgr::initialize())
 * \return texture usable in OpenGL or 0 on failure
 */
GLuint createTexture(const std::string &fileName, bool mipmap = true);

} // end namespace pgr

#endif
