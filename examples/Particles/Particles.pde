import lunar.*;

//Creates a simple 2D particle system with mouse interaction

//Canvas size
LVector windowSize = new LVector(800, 800);
LVector canvasSize = new LVector(windowSize.x*pow(0.625, 2), windowSize.y*0.625);
LVector canvasOffset = new LVector((windowSize.x-canvasSize.x)*0.5, (windowSize.y-canvasSize.y)*0.5);

//Points and their movement
ArrayList<LVector> points;
ArrayList<LVector> acceleration;

void settings()
{
  //Processing Environment
  size((int)windowSize.x, (int)windowSize.y, P2D);
  smooth(8);
}

void setup()
{
  //Generate point field and movement
  points = Vectors.randomVectors(canvasSize.x, canvasSize.y, 255, 200);
  acceleration = Vectors.randomVectors(2, 2, 10, points.size());

  //Correct position and accelerations of the points
  LVector d = new LVector(canvasOffset.x, canvasOffset.y);  
  LVector s = new LVector(-1, -1, -5);
  for (int i = 0; i < points.size(); i++)
  {
    LVector v = points.get(i);
    v = Vectors.addedVector(v, d);
    points.set(i, v);

    LVector a = acceleration.get(i);
    a = Vectors.addedVector(a, s);
    acceleration.set(i, a);
  }
}

void draw()
{  
  //Processing look and feel
  background(240);
  cursor(CROSS);

  //(optional) Generate and render the scene
  noStroke();
  rectMode(CENTER);

  for (int i = -9; i<20; i++)
  {
    if (i < 0)fill(255, 255, 255, map(i, -9, 0, 0, 50));
    else fill(0, 0, 0, map(i, 0, 9, 50, 0));
    rect(width*0.5+i, height*0.5+i, canvasSize.x, canvasSize.y, 20, 20, 20, 20);
  }

  fill(240);  
  rect(width*0.5, height*0.5, canvasSize.x, canvasSize.y, 20, 20, 20, 20);

  //Animate points
  for (int i = 0; i< points.size(); i++)
  {
    LVector v = points.get(i);
    LVector a = acceleration.get(i);

    if (v.x < canvasOffset.x || v.x > canvasOffset.x + canvasSize.x) a.x = Vectors.reversedVector(a).x;
    if (v.y < canvasOffset.y || v.y > canvasOffset.y + canvasSize.y) a.y = Vectors.reversedVector(a).y;
    if (v.z < 0 || v.z > 255) a.z = Vectors.reversedVector(a).z;

    v = Vectors.addedVector(v, a);

    points.set(i, v);
    acceleration.set(i, a);
  }

  //Render points
  for (LVector v : points)
  {
    stroke(100, 100, 100, map(v.z, 0, 255, 0, 255));
    strokeWeight(map(v.z, 0, 255, 2, 5));
    point(v.x, v.y);
  };

  //Calculate points close to the mouse
  LVector mousePosition = new LVector(mouseX, mouseY);
  ArrayList<LVector> flattedPoints = new ArrayList<LVector>();
  for (LVector v : points) flattedPoints.add(new LVector(v.x, v.y));
  ArrayList<LVector> highlightedPoints = Vectors.closestVectors(mousePosition, flattedPoints, 40);

  //Render points close to the mouse    
  noFill();

  for (LVector v : highlightedPoints)
  {
    stroke
      (
      map(mousePosition.x, canvasOffset.x, canvasOffset.x + canvasSize.x, 0, 255), 
      map(mousePosition.y, canvasOffset.y, canvasOffset.y + canvasSize.y, 0, 255), 
      map(mousePosition.y, canvasOffset.y, canvasOffset.y + canvasSize.y, 255, 0), 
      map(v.z, 0, 255, 100, 255)
      );

    strokeWeight(map(v.z, 0, 255, 5, 15));
    point(v.x, v.y);

    strokeWeight(1);
    ellipse(v.x, v.y, map(v.z, 0, 255, 10, 30), map(v.z, 0, 255, 10, 30));
  }  
}
