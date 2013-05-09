#version 130

uniform mat4 PVMmatrix;    // Projection * View * Model  --> model to clip coordinates
uniform mat4 Vmatrix;      // View                       --> world to eye coordinates
uniform mat4 Mmatrix;      // Model                      --> model to world coordinates
uniform mat4 NormalMatrix; // inverse transposed VMmatrix

in vec3 position;     // vertex position in world space
in vec3 normal;       // vertex normal

smooth out vec3 theNormal;    // camera space normal
smooth out vec3 thePosition;  // camera space fragment position

in vec2 texCoord;			// incoming texture coordinates
smooth out vec2 vTexCoord;	// outgoing texture coordinates


void main() {

  // vertex position after the projection (gl_Position is predefined output variable)
  gl_Position = PVMmatrix * vec4(position, 1);   // out:v vertex in clip coordinates

  // eye-coordinate position of vertex
  vec4 VMposition = Vmatrix * Mmatrix * vec4(position, 1);              //vertex in eye coordinates
  vec3 VMnormal   = normalize( NormalMatrix * vec4(normal, 0.0) ).xyz;  //normal in eye coordinates by NormalMatrix

  // outputs entering the fragment shader
  theNormal   = VMnormal;
  thePosition = VMposition.xyz;

  vTexCoord = texCoord;
}
