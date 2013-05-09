#version 130

#define GOURAUD

// I/O structures_____________________________________________________________
struct Smaterial {
   vec3  ambient;
   vec3  diffuse;
   vec3  specular;
   float shininess;
};

// Interface__________________________________________________________________
smooth in vec4 theColor;     // diffuse color
smooth in vec3 theNormal;    // camera space normal 
smooth in vec3 thePosition;  // camera space fragment position

uniform Smaterial material;  // material of this vertex
uniform float time;          // used for simulationof moving lights (such as sun)

out vec4 outputColor;

// Localy defined light_______________________________________________________
// - must match the same in vertex program 
const float s = 0.6;  // light specular coefficient
const vec3  lightSpecular = vec3(s, s, s);
const float lightSpotCosCutoff = 0.0;//0.9;
const float lightSpotExponent = 1.0;

// ___________________________________________________________________________
void main()
{ 
   	float NdotH;      // normal * light-viewer half vector
	vec3  halfVector; // half vector between light direction and viewer direction
    vec3  L;          // direction to the light, light direction is in light.position (the w coord would be = 0.0) 
	vec3  V;          // eye (viewer) direction
	vec3  N;          // normalized normal  
	float pf;         // specular power factor
    float spotDot;    // cosine of angle between spotlight
	float spotAttenuation; // exponent for light distribution inside the cone

    // point light in camera position - specular component only
	//---------------------------------------------------------
	L = normalize( -thePosition );  // light in camera position => L = [0,0,0] - thePosition
	V = L;                          // camera also :=)  
	N = normalize( theNormal );
    const vec3 lightSpotDirection = vec3(0.0, 0.0, -1.0);// normalized
	
	//halfVector = L;   //normalize(L + V); V = L => HalfVector = L
	NdotH = max( 0.0, dot( N, L) );

 	if( NdotH == 0 ) {  
		pf = 0.0; 
	} 
	else {
		pf = pow(NdotH, material.shininess);
	}
	
	// See if point on surface is inside cone of illumination
    spotDot = dot(-L, lightSpotDirection); 

    if (spotDot < lightSpotCosCutoff)
    {
        spotAttenuation = 0.0; // light adds no contribution
    }
    else
    {
        spotAttenuation = pow(spotDot, lightSpotExponent);
    }
#ifdef GOURAUD
    outputColor = min(vec4(1.0), theColor );
#else
    outputColor = min(vec4(1.0), theColor + vec4(material.specular * lightSpecular * pf * spotAttenuation, 1.0));
#endif
}