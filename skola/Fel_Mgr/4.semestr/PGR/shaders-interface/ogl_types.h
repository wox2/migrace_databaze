/**
 * \file ogl_types.h
 * \brief Translates opengl type enum to associated shader type in string.
 * \author Tomas Barak
 */

#ifndef OGL_TYPES_H
#define OGL_TYPES_H

#include "pgr.h"

/// Translates opengl type enum to associated shader type in string.
const char * oglTypeName(GLenum glType);

#endif // OGL_TYPES_H
