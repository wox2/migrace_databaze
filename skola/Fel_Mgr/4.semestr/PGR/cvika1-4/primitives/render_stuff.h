// -------------------------------------------------------------------

void setRotationMatrix(); 
// -------------------------------------------------------------------

//void InitializeVertexBufferXXX();  // copy vertices to GPU as a byte block  
//void InitializeVertexArrayXXX();   // vertex array object VAO - assign attrib pointers to the Vertex Buffer

void InitializeVertexBufferHouse0();  
void InitializeVertexArrayHouse0(); 

void InitializeVertexBufferHouse1();
void InitializeVertexArrayHouse1(); 

void InitializeVertexBufferHouse2();  //VBO's with vertices and colors interlaced
void InitializeVertexArrayHouse2(); 
void InitializeElementIndicesHouse3();



void functionDraw(int taskNumber);

// --------------------------------------------------
// General functions

void InitializeProgram(int taskNumber);
