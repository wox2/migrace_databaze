#version 130

struct Smaterial {
   vec3  ambient;
   vec3  diffuse;
   vec3  specular;
   float shininess;
};

struct Slight {
   vec3  ambient;
   vec3  diffuse;
   vec3  specular;
   vec3  position;  // should be vec4, while position[3] = 0 for vector and 1 for position
   vec3  spotDirection;
   float spotCosCutoff;
   float spotExponent;
};

// used for light 0 to 4 - it is impossile to create an array of stuctures
Slight light;


smooth in vec3 theNormal;    // camera space normal
smooth in vec3 thePosition;  // camera space fragment position

uniform float     time;         // used for simulation of moving lights (such as sun)

uniform mat4      Vmatrix;      // View                       --> world to eye coordinates

uniform Smaterial material;  // material of this vertex

uniform sampler2D texSampler;		// texture sampler
smooth in vec2 vTexCoord;			// fragment texture coordinates

uniform bool       useTexture;

out vec4 outputColor;

void main()
{
  vec3 VMlightDirection =  (Vmatrix *  vec4(  1.0f,  1.0f, 1.0f, 0.0f )).xyz;

  float NdotL = max( 0.0, dot( normalize(theNormal), normalize(VMlightDirection) ));

  outputColor = vec4(NdotL * material.diffuse, 1.0);

  if(useTexture == true) {
    outputColor =  outputColor * texture(texSampler, vTexCoord);
  }

  outputColor = clamp( outputColor, 0.0, 1.0 );   // min(color, 1.0) should suffice
}
