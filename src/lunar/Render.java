package lunar;

public class Render {

	public static final void mesh(processing.core.PApplet applet, LMesh mesh)
	{
		processing.core.PGraphics graphicsBuffer = applet.g;
		
		graphicsBuffer.beginShape(processing.core.PConstants.TRIANGLES);
		
		for(LFace f : mesh.faces)
		{
		    LVector a = mesh.vertices.get(f.a);
		    LVector b = mesh.vertices.get(f.b);
		    LVector c = mesh.vertices.get(f.c);
		    
		    LVector n = Vectors.unitVector(Vectors.crossProduct(Vectors.twoPointVector(a,c),Vectors.twoPointVector(a,b)));
		 
		    
		    graphicsBuffer.normal(n.x, n.y, n.z);
		    graphicsBuffer.vertex(a.x, a.y, a.z);
		    graphicsBuffer.vertex(b.x, b.y, b.z);
		    graphicsBuffer.vertex(c.x, c.y, c.z);
		}
		
		graphicsBuffer.endShape();
	}
	
	public static final void meshNormals(processing.core.PApplet applet, LMesh mesh, float normalLength)
	{
		processing.core.PGraphics graphicsBuffer = applet.g;

		graphicsBuffer.beginShape(processing.core.PConstants.LINES);
	
		for (LFace f : mesh.faces)
		{
			LVector a = mesh.vertices.get(f.a);
			LVector b = mesh.vertices.get(f.b);
		    LVector c = mesh.vertices.get(f.c);
		
		    LVector m = Vectors.dividedVector(Vectors.addedVector(Vectors.addedVector(a, b), c), 3);		
		    LVector n = Vectors.unitVector(Vectors.crossProduct(Vectors.twoPointVector(a, c), Vectors.twoPointVector(a, b)));		
		    LVector v = Vectors.addedVector(m, Vectors.amplitudedVector(n, normalLength));
		
		    graphicsBuffer.line(m.x, m.y, m.z, v.x, v.y, v.z);
		}

  		graphicsBuffer.endShape();
	}
	
	public static final void meshVertices(processing.core.PApplet applet, LMesh mesh)
	{
	  processing.core.PGraphics graphicsBuffer = applet.g;

	  graphicsBuffer.beginShape(processing.core.PConstants.POINTS);
	  
	  for (LVector v : mesh.vertices) graphicsBuffer.point(v.x, v.y, v.z);  
	  
	  graphicsBuffer.endShape();
	}
	
}
