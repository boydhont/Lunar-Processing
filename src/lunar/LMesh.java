package lunar;

public class LMesh {

	public java.util.ArrayList<LVector> vertices;
	public java.util.ArrayList<LFace> faces;
	
	public java.util.ArrayList<LMesh> children;
	
	public LMesh()
	{
		vertices = new java.util.ArrayList<LVector>();
		faces = new java.util.ArrayList<LFace>();
		children = new java.util.ArrayList<LMesh>();
	}
	
	public LMesh(java.util.ArrayList<LVector> _vertices, java.util.ArrayList<LFace> _faces)
	{
		vertices = _vertices;
		faces = _faces;
		children = new java.util.ArrayList<LMesh>();
	}
	
	public LMesh(java.util.ArrayList<LMesh> _children)
	{
		vertices = new java.util.ArrayList<LVector>();
		faces = new java.util.ArrayList<LFace>();
		children = new java.util.ArrayList<LMesh>();
	}
	
}
