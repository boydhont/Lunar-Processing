import lunar.*;

//Generates a mesh from goniometric formulas

//Generate the mesh
LMesh mesh = pastaMesh(100, 100);

void setup()
{
  //Processing Environment
  size(800, 800, P3D);
  smooth(8);
}

void draw()
{
  //Look and feel
  Render.gradientBackground(this, color(230), color(240));
  cursor(CROSS);
  lights();
  ambientLight(128, 128, 128);
  Render.mouseCamera(this, new LVector(), 500);  

  //Render the mesh
  noStroke();
  Render.rainbowMesh(this, mesh);
}

//Generates a vector according to a goniometric formula
LVector ravioliVector (float u, float v)
{
  //Map the u and v to the range
  u = map(u, 0, TWO_PI, 0, TWO_PI);
  v = map(v, 0, TWO_PI, 0, PI);

  //Generate the vector
  LVector p = new LVector(
    sin(v)*cos(u), 
    sin(u), 
    v
    );

  return p;
}

//Generates the mesh
LMesh pastaMesh(float scale, int resolution)
{
  LMesh mesh = new LMesh();

  float s = scale; //scale factors
  int n = resolution; //step size

  //The mesh
  int[][] indexTable = new int[n][n]; //The indextable for the generation of the faces
  int index = 0;

  for (int i = 0; i< n; i++)
  {
    for (int j = 0; j< n; j++)
    {
      //Define the u and v values
      float u = map(i, 0, n-1, 0, TWO_PI);
      float v = map(j, 0, n-1, 0, TWO_PI);

      //Generate the vector
      LVector p = new LVector();
      p = ravioliVector(u, v);

      //Correct position and scale
      p = Vectors.addedVector(p, Vectors.zAxisVector(-PI*0.5));
      p = Vectors.multipliedVector(p, s);

      //Add the vector to the vertex list
      mesh.vertices.add(p);

      //Add the vertex index to the index table
      indexTable[i][j] = index;
      index++;
    }
  }

  //Generate faces according to the index table
  for (int i = 1; i< n; i++)
  {
    for (int j = 1; j< n; j++)
    {
      //First triangle face
      LFace fL = new LFace(
        indexTable[i-1][j], 
        indexTable[i][j-1], 
        indexTable[i-1][j-1]
        );

      //Second triangle face
      LFace fR = new LFace(
        indexTable[i][j-1], 
        indexTable[i-1][j], 
        indexTable[i][j]
        );

      //Add the faces to thse mesh
      mesh.faces.add(fL);
      mesh.faces.add(fR);
    }
  }

  return mesh;
}
