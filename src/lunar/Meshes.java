package lunar;

import java.util.ArrayList;

public class Meshes {

	//TODO construct mesh
	
	/**
	 * Generates a mesh in the shape of a box
	 * @param boxWidth The width of the mesh
	 * @param boxDepth The depth of the mesh
	 * @param boxHeight The height of the mesh
	 * @param columns The amount of columns
	 * @param rows The amount of rows
	 * @param layers The amount of layers
	 * @return The mesh
	 */

	public final static LMesh boxMesh(float boxWidth, float boxDepth, float boxHeight, int columns, int rows, int layers)
	{
		LMesh boxMesh = new LMesh();

		LVector step = new LVector(boxWidth/columns, boxDepth/rows, boxHeight/layers);
		int[][][] indexTable = new int[columns+1][rows+1][layers+1];

		//Create vertices
		int index = 0;		
		for (int i = 0; i < indexTable.length; i++)
		{
			for (int j = 0; j < indexTable[0].length; j++)
			{
				for (int k = 0; k < indexTable[0][0].length; k++)
				{
					if (i == 0 || j == 0 || k == 0 || i == columns || j == rows || k == layers)
					{
						boxMesh.vertices.add(new LVector(i*step.x, j*step.y, k*step.z));
						indexTable[i][j][k] = index;
						index++;
					}
				}
			}
		}
		
		//Create faces
		for (int j = 1; j<indexTable[0].length; j++)
		{
			for (int k = 1; k<indexTable[0][0].length; k++)
		    {
				//Left Plane
				boxMesh.faces.add(new LFace(indexTable[0][j-1][k-1], indexTable[0][j][k-1], indexTable[0][j-1][k]));
				boxMesh.faces.add(new LFace(indexTable[0][j][k-1], indexTable[0][j][k], indexTable[0][j-1][k]));

				//Right Plane
				int l = indexTable.length-1;
				boxMesh.faces.add(new LFace(indexTable[l][j][k-1], indexTable[l][j-1][k-1], indexTable[l][j][k]));
				boxMesh.faces.add(new LFace(indexTable[l][j-1][k], indexTable[l][j][k], indexTable[l][j-1][k-1]));
		    }
		}

		//front plane/back plane
		for (int i = 1; i<indexTable.length; i++)
		{
			for (int k = 1; k<indexTable[0][0].length; k++)
		    {
				//Front plane
		    	boxMesh.faces.add(new LFace(indexTable[i-1][0][k], indexTable[i][0][k-1], indexTable[i-1][0][k-1]));
		    	boxMesh.faces.add(new LFace(indexTable[i-1][0][k], indexTable[i][0][k], indexTable[i][0][k-1]));

		    	//Back Plane
		    	int l = indexTable[0].length-1;
		    	boxMesh.faces.add(new LFace(indexTable[i][l][k-1], indexTable[i][l][k], indexTable[i-1][l][k-1]));
		    	boxMesh.faces.add(new LFace(indexTable[i-1][l][k-1], indexTable[i][l][k], indexTable[i-1][l][k]));
		    }
		}

		//top plane/bottom plane
		for (int i = 1; i<indexTable.length; i++)
		{
			for (int j = 1; j<indexTable[0].length; j++)
		    {
				//Bottom Plane
		    	boxMesh.faces.add(new LFace(indexTable[i-1][j-1][0], indexTable[i][j][0], indexTable[i-1][j][0]));
		    	boxMesh.faces.add(new LFace(indexTable[i][j-1][0], indexTable[i][j][0], indexTable[i-1][j-1][0]));

		    	//Top Plane
		    	int l = indexTable[0][0].length-1;
		    	boxMesh.faces.add(new LFace(indexTable[i][j][l], indexTable[i][j-1][l], indexTable[i-1][j-1][l]));
		      	boxMesh.faces.add(new LFace(indexTable[i][j][l], indexTable[i-1][j-1][l],indexTable[i-1][j][l]));
		    }
		}
		
		return boxMesh;
	}	
	
	/**
	 * Generates a mesh in the shape of a plane
	 * @param planeWidth The width of the mesh
	 * @param planeHeight The height of the plane
	 * @param columns The amount of columns
	 * @param rows The amount of rows
	 * @return The mesh
	 */
	
	public final static LMesh planeMesh(float planeWidth, float planeHeight, int columns, int rows)
	{
		LMesh planeMesh = new LMesh();

		  ArrayList<ArrayList<LVector>> vectorGrid = Vectors.rectangularGrid(planeWidth, planeHeight, columns +1, rows + 1);

		  //add vertices
		  for (ArrayList<LVector> array : vectorGrid)
		  {
		    for (LVector v : array)
		    {
		      planeMesh.vertices.add(v);
		    }
		  }

		  //add faces
		  for (int i = 1; i < vectorGrid.size(); i++)
		  {
		    for (int j = 1; j < vectorGrid.get(0).size(); j++)
		    {
		      int[] indexMap = {
		        (i-1)*vectorGrid.get(0).size() + (j-1),
		        i*vectorGrid.get(0).size() + (j-1),
		        (i-1)*vectorGrid.get(0).size() + j,
		        i*vectorGrid.get(0).size() + j
		      };
		      
		      planeMesh.faces.add(new LFace(indexMap[0], indexMap[1], indexMap[2]));
		      planeMesh.faces.add(new LFace(indexMap[3], indexMap[2], indexMap[1]));
		    }
		  }
		  
		  return planeMesh;
	}
	
	//TODO mesh spehre ex
	
	/**
	 * Generates a mesh in the form of a single face
	 * @param a The first vertex
	 * @param b The second vertex
	 * @param c The third vertex
	 * @return The mesh
	 */
	
	public final static LMesh triangleMesh(LVector a, LVector b, LVector c)
	{
		LMesh mesh = new LMesh();
		
		mesh.vertices.add(a);
		mesh.vertices.add(b);
		mesh.vertices.add(c);
		
		mesh.faces.add(new LFace(0,1,2));
		
		return mesh;
	}	
	
	/**
	 * Returns the vertices that make a face
	 * @param face The input face
	 * @param parentMesh The mesh that contains the input face
	 * @return A list containing the face indices as vectors
	 */
	
	public final static ArrayList<LVector> faceVectices(LFace face, LMesh parentMesh)
	{
		ArrayList<LVector> vectors = new ArrayList<LVector>();
		
		vectors.add(parentMesh.vertices.get(face.a));
		vectors.add(parentMesh.vertices.get(face.b));
		vectors.add(parentMesh.vertices.get(face.c));
		
		return vectors;
	}
	
	//TODO deconstruct mesh
	
	/**
	 * Returns the vertices of a mesh
	 * @param mesh The input mesh
	 * @return The vertices
	 */
	
	public final static ArrayList<LVector> meshVertices(LMesh mesh)
	{
		return mesh.vertices;
	}
	
	//TODO mesh closest point
	
	//TODO mesh edges
	
	/**
	 * Returns the edges of a mesh
	 * @param mesh The input mesh
	 * @return The mesh edges as vector pairs
	 */
	
	public final static ArrayList<ArrayList<LVector>> meshEdges(LMesh mesh)
	{
		ArrayList<ArrayList<LVector>> edges = new ArrayList<ArrayList<LVector>>();
		
		for(int i = 0; i<mesh.vertices.size(); i++)
		{
			LVector a = mesh.vertices.get(i);
			
			for(int j = 0; j<mesh.vertices.size(); j++)
			{
				if(j > i)
				{
					LVector b = mesh.vertices.get(j);
					
					boolean isPartner = false;
					
					for(LFace f : mesh.faces)
					{
						boolean aFound = false;
						boolean bFound = false;
						
						if(i == f.a || i == f.b || i == f.c)
						{
							aFound = true;
						}
						
						if(j == f.a || j == f.b || j == f.c)
						{
							bFound = true;
						}
						
						if(aFound && bFound)
						{
							isPartner = true;
						}
					}
					
					if(isPartner)
					{
						ArrayList<LVector> edge = new ArrayList<LVector>();
						edge.add(a);
						edge.add(b);
						edges.add(edge);
					}
				}
			}
		}
		
		return edges;
	}
	
	//TODO mesh eval
	
	//TODO face boundaries
	
	//TODO face circles
	
	//TODO face normals
	
	//TODO mesh inclusion
	
	/**
	 * Culls the faces from a mesh
	 * @param mesh The input mesh
	 * @param pattern The cull patter
	 * @return The culled mesh
	 */
	
	static final LMesh culledFacesMesh(LMesh mesh, ArrayList<Boolean> pattern)
	{
		LMesh culledFacesMesh = mesh;
		culledFacesMesh.faces = Lists.dispatchedLists(culledFacesMesh.faces, pattern).get(0);
		return culledFacesMesh;
	}
	
	/**
	 * Culls the vertices from a mesh
	 * @param mesh The input mesh
	 * @param pattern The cull pattern
	 * @return The culled mesh
	 */
	
	static final LMesh culledVerticesMesh(LMesh mesh, ArrayList<Boolean> pattern)
	{
		LMesh culledVerticesMesh = mesh;
		
		culledVerticesMesh.vertices = Lists.dispatchedLists(culledVerticesMesh.vertices,  pattern).get(0);
		
		ArrayList<Integer> culledIndexes = new ArrayList<Integer>();
		for (int i = 0; i < culledVerticesMesh.vertices.size(); i++) culledIndexes.add(i);
		culledIndexes = Lists.dispatchedLists(culledIndexes, pattern).get(0);
		
		ArrayList<LFace> culledFaces = new ArrayList<LFace>();
		
		for (LFace f : culledVerticesMesh.faces)
		{
			boolean fixedIndexA = false;
		    boolean fixedIndexB = false;
		    boolean fixedIndexC = false;
		    
		    for (int i = 0; i < culledIndexes.size(); i++)
		    {
		    	if (culledIndexes.get(i) == f.a) 
		    	{
		    		f.a = i;
		    		fixedIndexA = true;
		    	}
		    	if (culledIndexes.get(i) == f.b) 
		    	{
		    		f.b = i;
		    		fixedIndexB = true;
		    	}
		    	if (culledIndexes.get(i) == f.c) 
		    	{
		    		f.c = i;
		    		fixedIndexC = true;
		    	}
		    }
		    
		    if (fixedIndexA && fixedIndexB && fixedIndexC)
		    {
		      culledFaces.add(new LFace(f.a, f.b, f.c));
		    }
		}
		
		culledVerticesMesh.faces = culledFaces;

		return culledVerticesMesh;		
	}
	
	/**
	 * Deletes faces from a mesh
	 * @param mesh The input mesh
	 * @param indexes The indexes of the faces to delete
	 * @return The mesh without the deleted faces
	 */
	
	static final LMesh deletedFacesMesh(LMesh mesh, ArrayList<Integer> indexes)
	{
		ArrayList<Boolean> pattern = new ArrayList<Boolean>();
		for (int i = 0; i< indexes.size(); i++)
		{
			if (indexes.get(i) == i)
		    {
				pattern.add(false);
		    } 
			else
		    {
				pattern.add(true);
		    }
		}

		return culledFacesMesh(mesh, pattern);
	}
	
	/**
	 * Deletes vertices from a mesh
	 * @param mesh The input mesh
	 * @param indexes The indexes of the vertices to delete
	 * @return The mesh without the deleted vertices
	 */
	
	static final LMesh deletedVerticesMesh(LMesh mesh, ArrayList<Integer> indexes) //Tested and works
	{
		ArrayList<Boolean> pattern = new ArrayList<Boolean>();
		for (int i = 0; i< indexes.size(); i++)
		{
			if (indexes.get(i) == i)
		    {
				pattern.add(false);
		    } 
			else
		    {
				pattern.add(true);
		    }
		}

		return culledVerticesMesh(mesh, pattern);
	}
	
	/**
	 * Joins multiple meshes into one mesh
	 * @param meshes The list of meshes to join
	 * @return The joined mesh
	 */
	
	static final LMesh joinedMesh(ArrayList<LMesh> meshes) //Tested and works
	{
		LMesh joinedMesh = new LMesh();

		for (LMesh m : meshes)
		{
			int indexSize = joinedMesh.vertices.size();

			ArrayList<ArrayList<LVector>> verticesList = new ArrayList<ArrayList<LVector>>();
			verticesList.add(joinedMesh.vertices);
			verticesList.add(m.vertices);

			joinedMesh.vertices = Lists.combinedList(verticesList);

			for (LFace f : m.faces)
			{
				LFace updatedFace = new LFace(f.a+indexSize, f.b+indexSize, f.c+indexSize);
				joinedMesh.faces.add(updatedFace);
			}
		}

		return joinedMesh;
	}
	
	/*
	static final LMesh unweldedMesh(LMesh mesh)
	{
		LMesh unweldedMesh = new LMesh();

		for (LFace f : mesh.faces)
		{
			int l = unweldedMesh.vertices.size();

			unweldedMesh.vertices.add(mesh.vertices.get(f.a));
			unweldedMesh.vertices.add(mesh.vertices.get(f.b));
			unweldedMesh.vertices.add(mesh.vertices.get(f.c));

			unweldedMesh.faces.add(new LFace(l, l+1, l+2));
		}

		return unweldedMesh;
	}*/
	
	/**
	 * Converts a mesh to a PShape
	 * @param applet The Processing applet, write "this"
	 * @param mesh The input mesh
	 * @return The mesh as a PShape
	 */
	
	static final processing.core.PShape PShape (processing.core.PApplet applet, LMesh mesh)
	{
		processing.core.PShape shape = applet.createShape();
		
		shape.beginShape(processing.core.PConstants.TRIANGLES);
		
		for(LFace f : mesh.faces)
		{
		    LVector a = mesh.vertices.get(f.a);
		    LVector b = mesh.vertices.get(f.b);
		    LVector c = mesh.vertices.get(f.c);
		    
		    LVector n = Vectors.unitVector(Vectors.crossProduct(Vectors.twoPointVector(a,c),Vectors.twoPointVector(a,b)));
		 
		    
		    shape.normal(n.x, n.y, n.z);
		    shape.vertex(a.x, a.y, a.z);
		    shape.vertex(b.x, b.y, b.z);
		    shape.vertex(c.x, c.y, c.z);
		}
		
		shape.endShape();
		
		return shape;
	}
	
}
