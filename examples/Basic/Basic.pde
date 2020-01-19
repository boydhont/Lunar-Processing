import lunar.*;

//A basic setup for a 3d scene

void setup()
{
  size(800, 800, P3D);
}

void draw()
{
  background(230);
  stroke(230);
  fill(10, 250, 150);

  //Camera
  Render.mouseCamera(this, new LVector(50, 50, 50), 300);

  //Mesh generation
  LMesh mesh = Meshes.boxMesh(100, 100, 100, 5, 5, 5);

  //Mesh rendering
  Render.mesh(this, mesh);
}
