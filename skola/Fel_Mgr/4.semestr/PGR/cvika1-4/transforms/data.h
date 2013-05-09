#ifndef DATA_H
#define DATA_H

#include <string>
#include <vector>

const std::string strVertexShader(
	"#version 130\n"
	"uniform mat4 PVMmatrix;\n"
	"in vec4 position;\n"
	"in vec4 color;\n"
	"smooth out vec4 theColor;\n"	
	"void main()\n"
	"{\n"
	"	gl_Position = PVMmatrix * position;\n"
	"	theColor = color;\n"
	"}\n"
);

const std::string strFragmentShader(
	"#version 130\n"
	"smooth in vec4 theColor;\n"
	"out vec4 outputColor;\n"
	"void main()\n"
	"{\n"
	"	outputColor = theColor;\n"
	"}\n"
);

#endif DATA_H