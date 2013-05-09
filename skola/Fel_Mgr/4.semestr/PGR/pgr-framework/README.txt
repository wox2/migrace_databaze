PGR-FRAMEWORK version 12.09.18 - basic libraries to support OpenGL programming
==============================================================================
Authors: Tomas Barak <baraktom@fel.cvut.cz>, Petr Felkel <felkel@fel.cvut.cz>

Help and tutorial is included in the doxygen generated manual - see the doc/ directory for more info.

Framework Structure
-------------------
bin/               - dll + exe files - should be added to PATH
data/              - common resources (models/textures/shaders...)
doc/               - generated documentation
include/           - header files - should be added to your include path
lib/               - static libraries for win32 - should be added to your linker path
prj/               - VC project and solution
src/               - source codes of pgr framework + assimp (required for non win32 platforms)
js/                - sources of the js/webgl version of the framework (required only for the webgl)
