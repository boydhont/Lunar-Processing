import lunar.*;

//Modifies a 3D mesh primitive

//Noise seed
LVector offset = new LVector();
LVector direction = new LVector(0.01, 0.01, 0.01);

//Timer animations
int timer = 0;
int interval = 180;

//Camera postion as a polar coordinate
LVector cameraPosition = new LVector(200, 0, PI/4);

//(optional) human scale model
PShape humanScaleModel;

void settings()
{
  //Processing Environment
  size(800, 800, P3D);
  smooth(8);
}

void setup()
{
  //(optional) load human scale model
  humanScaleModel = loadShape(sketchPath() + "/data/humanScaleModel.obj");
}

void draw()
{
  //Processing look and feel
  Render.gradientBackground(this, color(240), color(230));

  lights();
  ambientLight(100, 100, 100);
  cursor(CROSS);

  //3D Orthogonal Camera
  ortho(-0.3*width,0.3*width, -0.3*height, 0.3*height);
  Render.mouseCamera(this, new LVector(), 300);

  //Generate the mesh
  LMesh mesh = Meshes.boxMesh(100, 100, 100, 30, 30, 30);

  //Modify the mesh
  for (int i = 0; i<mesh.vertices.size(); i++)
  {
    LVector v = mesh.vertices.get(i);

    //Turn the box into a sphere
    v = Vectors.addedVector(v, new LVector(-50, -50, -50));    
    v = Vectors.unitVector(v);

    //Generate and apply perlin noise
    LVector n = Vectors.addedVector(v, offset);
    float noise = noise(n.x, n.y, n.z) * 100;
    v = Vectors.multipliedVector(v, noise);    

    mesh.vertices.set(i, v);
  }

  //Animate the mesh
  offset = Vectors.addedVector(offset, direction);

  timer++;
  if (timer >= interval)
  {
    direction = new LVector(random(-0.02, 0.02), random(-0.02, 0.02), random(-0.02, 0.02));
    timer = 0;
  }

  //Render the Mesh
  noStroke(); 
  Render.rainbowMesh(this, mesh);

  //(Optional) Generate and render the scene
  translate(0, 0, -100);  
  noFill();
  strokeWeight(1);
  stroke(180, 180, 180, 200*map(mouseY, 0, height*0.75, 1, 0));  
  if(mouseY < height/2) ellipse(0, 0, 250, 250);   

  translate(90, 0, 0);
  noStroke();
  shape(humanScaleModel);
}
