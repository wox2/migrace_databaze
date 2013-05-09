#ifndef DATA_H
#define DATA_H

/// Half of house depth (house size along the z-axis).
const float z = 0.6f;

// For all tasks
const std::string strVertexShader(
  "#version 140\n"
  "uniform mat4 spinMatrix;\n"
  "in vec2 position;\n"
  "in vec3 color;\n"
  "smooth out vec3 theColor;\n"
  "void main()\n"
  "{\n"
  "  gl_Position = spinMatrix * vec4(position, 0.0f, 1.0f);\n"
  "  theColor = color;\n"
  "}\n"
);

const std::string strFragmentShader(
  "#version 140\n"
  "smooth in vec3 theColor;\n"
  "out vec4 outputColor;\n"
  "void main()\n"
  "{\n"
  "  outputColor = vec4(theColor, 1.0f);\n"
  "}\n"
);

//       C   F I   //      4   10 16  //       5   11 17
//      /  \ G |   //     /  \ 12 |   //      /  \ 13 |
//     /    \  H   //    /    \   14  //     /    \   15
//    A------B     //   0------2      //    3------1
//    |      |     //   |      |      //    |      |
//    |      |     //   |      |      //    |      |
//    D------E     //   6------8      //    9------7

const unsigned int vertexCountHouse0 = 7;        // number of vertices in this array
const float vertexDataHouse0[vertexCountHouse0][2] = {  // repeated points A abd B
  // ======== BEGIN OF SOLUTION - TASK 1-1 ======== //
  // ========  END OF SOLUTION - TASK 1-1  ======== //
};

// LINE_LOOP ABC, ADEB, FGHI
// TRIANGLE_STRIP CAB, ABDE, FGIH
const unsigned int vertexCountHouse1 = 11;        // number of vertices in this array
const float vertexDataHouse1[vertexCountHouse1][2] = {  // repeated points A abd B
  // ======== BEGIN OF SOLUTION - TASK 2-1 ======== //
  // ========  END OF SOLUTION - TASK 2-1  ======== //
};


const float vertexColorHouse1[vertexCountHouse1][3] = {
  // ======== BEGIN OF SOLUTION - TASK 2-2 ======== //
  // ========  END OF SOLUTION - TASK 2-2  ======== //
};

// -------------------------------------------------------------------
const unsigned int vertexCountHouse2 = 11;        // number of vertices in this array
const float vertexDataHouse2[vertexCountHouse2][5] = {  // repeated points A abd B
  // ======== BEGIN OF SOLUTION - TASK 3-1 ======== //
  // ========  END OF SOLUTION - TASK 3-1  ======== //
};

const unsigned int elementCountHouse2 = 11;
const unsigned char elementDataHouse2[elementCountHouse2] = {
  // ======== BEGIN OF SOLUTION - TASK 4-1 ======== //
  // ========  END OF SOLUTION - TASK 4-1  ======== //
};

const unsigned int vertexCountHouse4 = 18;          // number of vertices in this array
const float vertexDataHouse4[vertexCountHouse4][3] = {
  // ======== BEGIN OF SOLUTION - TASK 5-1 ======== //
  // ========  END OF SOLUTION - TASK 5-1  ======== //
};


//TODO...
//const unsigned int elementCountHouse3= 11;
//const unsigned char elementDataHouse3[elementCountHouse3] =
//{
//  4, 0, 2, 0, 1, 2,        // roof
//    3, 4, 5, 6,     // wall
//  7, 8, 9, 10     // chimney
//};

#endif // DATA_H
