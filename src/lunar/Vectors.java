package lunar;

import java.util.ArrayList;

public class Vectors {
	
	/**
	 * Creates a vector from xyz components
	 * @param x the x component
	 * @param y the y component
	 * @param z the z component
	 * @return a three-dimensional vector object
	 */
	
	static final public LVector constructVector(float x, float y, float z) //Tested and works
	{
		return new LVector(x,y,z);
	}
	
	/**
	 * Deconstructs a vector into its component parts
	 * @param v the input vector
	 * @return the xyz components as an array
	 */
	
	static final public float[] deconstructVector(LVector v) //Tested and works
	{
		return new float[]{v.x,v.y, v.z};
	}
	
	/**
	 * Returns the unitized vector
	 * @param v the input vector
	 * @return the unitized vector
	 */
	
	static final public LVector unitVector(LVector v) //Tested and works
	{
		return dividedVector(v, vectorLength(v));
	}
	
	/**
	 * Returns a vector parallel to the x-axis
	 * @param amplitude the length of the vector
	 * @return a vector parallel to the x-axis
	 */
	
	static final public LVector xAxisVector(float amplitude) //Tested and works
	{
		return new LVector(amplitude,0,0);
	}
	
	/**
	 * Returns a vector parallel to the y-axis
	 * @param amplitude the length of the vector
	 * @return a vector parallel to the y-axis
	 */
	
	static final public LVector yAxisVector(float amplitude) //Tested and works
	{
		return new LVector(0,amplitude,0);
	}
	
	/**
	 * Returns a vector parallel to the z-axis
	 * @param amplitude the length of the vector
	 * @return a vector parallel to the z-axis
	 */
	
	static final public LVector zAxisVector(float amplitude) //Tested and works
	{
		return new LVector(0,0,amplitude);
	}
	
	/**
	 * Returns the vector between two points
	 * @param a the root vector
	 * @param b the target vector
	 * @return the vector between the two points
	 */
	
	static final public LVector twoPointVector(LVector a, LVector b) //Tested and works
	{
		return new LVector(b.x-a.x, b.y-a.y, b.z-a.z);
	}
	
	/**
	 * Sets the amplitude of a vector
	 * @param v the input vector
	 * @param amplitude the amplitude
	 * @return the amplituded vector
	 */
	
	static final public LVector amplitudedVector(LVector v, float amplitude) //Tested and works
	{
		LVector u = unitVector(v);
		return multipliedVector(u, amplitude);
	}
	
	/**
	 * Computes the angle between two vector
	 * @param a the first vector
	 * @param b the second vector
	 * @return the angle between the vectors in radial
	 */
	
	static final public float angleBetweenVectors(LVector a, LVector b) //Tested and works
	{
		float d = dotProduct(a,b);
		return (float) Math.acos(d/(vectorLength(a)*vectorLength(b)));
	}
	
	/**
	 * Computes the cross product of two vectors
	 * @param a the first vector
	 * @param b the second vector
	 * @return the cross product as a vector
	 */
	
	static final public LVector crossProduct(LVector a, LVector b) //Tested and works
	{
		LVector c = new LVector
		(
				(a.y*b.z) - (a.z*b.y),
				(a.z*b.x) - (a.x*b.z),
				(a.x*b.y) - (a.y*b.x)
		);
		
		return c;
	}
	
	/**
	 * Returns a vector-scalar division
	 * @param v the input vector
	 * @param factor the division factor
	 * @return the scaled vector
	 */
	
	static final public LVector dividedVector(LVector v, float factor) //Tested and works
	{
		return multipliedVector(v, (1/factor));
	}
	
	/**
	 * Returns the vector dot product
	 * @param a the first vector
	 * @param b the second vector
	 * @return the dot product
	 */
	
	static final public float dotProduct(LVector a, LVector b) //Tested and works
	{
		return (a.x*b.x) + (a.y*b.y) + (a.z*b.z);
	}
	
	/**
	 * Computes the amplitude of a vector
	 * @param v the input vector
	 * @return the amplitude of a vector
	 */
	
	static final public float vectorLength(LVector v) //Tested and works
	{
		return (float) (Math.pow((Math.pow(v.x, 2)+Math.pow(v.y, 2)+Math.pow(v.z,2)), .5));
	}
	
	/**
	 * Performs vector-scalar multiplication
	 * @param v the input vector
	 * @param factor the multiplication factor
	 * @return the multiplied vector
	 */
	
	static final public LVector multipliedVector(LVector v, float factor) //Tested and works
	{
		return new LVector(v.x*factor, v.y*factor, v.z*factor);
	}
	
	/**
	 * Reverses a vector
	 * @param v the input vector
	 * @return the reversed vector
	 */
	
	static final public LVector reversedVector(LVector v) //Tested and works
	{
		return multipliedVector(v,-1);
	}
	
	/**
	 * Rotates a vector around an axis
	 * @param v the input vector
	 * @param axis the rotation axis
	 * @param angleInRadial the rotation in radial
	 * @return the rotated vector
	 */
	
	static final public LVector rotatedVector(LVector v, LVector axis, float angleInRadial) //Tested and works
	{
		LVector u = unitVector(axis);
		
		float c = (float) (Math.cos(angleInRadial));
		float s = (float) (Math.sin(angleInRadial));
		float n = (float) (1 - Math.cos(angleInRadial));
		
		float xA = c + (u.x*u.x*n);
		float xB = (u.x*u.y*n) - (u.z*s);
		float xC = (u.x*u.z*n) + (u.y*s);

		float yA = (u.y*u.x*n) + (u.z*s);
		float yB = c + (u.y*u.y*n);
		float yC = (u.y*u.z*n) + (u.x*s);

		float zA = (u.z*u.x*n) - (u.y*s);
		float zB = (u.z*u.y*n) + (u.x*s);
		float zC = c + (u.z*u.z*n);

		float x = (v.x*xA)+(v.y*xB)+(v.z*xC);
		float y = (v.x*yA)+(v.y*yB)+(v.z*yC);
		float z = (v.x*zA)+(v.y*zB)+(v.z*zC);
		
		x = (float) (Math.round(x*Math.pow(10,5))/Math.pow(10,5));
		y = (float) (Math.round(y*Math.pow(10,5))/Math.pow(10,5));
		z = (float) (Math.round(z*Math.pow(10,5))/Math.pow(10,5));
		
		return new LVector(x, y, z);
	}
	
	/**
	 * Performs vector-vector addition
	 * @param a the first vector
	 * @param b the second vector
	 * @return the sum of the vectors
	 */
	
	static final public LVector addedVector(LVector a, LVector b) //Tested and works
	{
		return new LVector(a.x+b.x, a.y+b.y, a.z+b.z);
	}
	
	/**
	 * Converts a vector into a PVector
	 * @param v the input vector
	 * @return the vector as PVector
	 */
	
	static final public processing.core.PVector PVector(LVector v) //Tested and works
	{
		return new processing.core.PVector(v.x, v.y, v.z);
	}
	
	//TODO Projectpoint
	
	/**
	 * Returns the closest vector in a vector collection
	 * @param v the input vector
	 * @param collection the vector collection
	 * @return the vector that is closest to the input vector
	 */
	
	static final public LVector closestVector(LVector v, ArrayList<LVector> collection) //Tested and works
	{
		LVector closestVector = collection.get(0);
		float smallestDistance = vectorDistance(closestVector, v) + 1;
		
		for(LVector o : collection)
		{
			float distance = vectorDistance(v,o);
			if(distance < smallestDistance && distance != 0)
			{
				closestVector = o;
				smallestDistance = distance;
			}
		}
		
		return closestVector;
	}
	
	/**
	 * Returns closest vectors in a vector collection
	 * @param v the input vector
	 * @param collection the vector collection
	 * @param tolerance the maximum distance that catches vector candidates
	 * @return the vectors that are located in a the given tolerance as a list
	 */
	
	static final public ArrayList<LVector> closestVectors(LVector v, ArrayList<LVector> collection, float tolerance) //Tested and works
	{
		ArrayList<LVector> closestVectors = new ArrayList<LVector>();
		float smallestDistance = vectorDistance(collection.get(0), v) + 1;
		
		for(LVector o : collection)
		{
			float distance = vectorDistance(v, o);
			if(distance >= (smallestDistance - tolerance) && distance <= (smallestDistance + tolerance))
			{
				closestVectors.add(o);
			}
			if(distance < (smallestDistance - tolerance) && distance != 0)
			{
				closestVectors = new ArrayList<LVector>();
				closestVectors.add(o);
				smallestDistance = distance;
			}
		}
		
		return closestVectors;
	}
	
	//TODO cullduplicates
	
	/**
	 * Returns the distance between two vectors
	 * @param a the first vector
	 * @param b the second vector
	 * @return the distance between the two vectors
	 */
	
	static final public float vectorDistance(LVector a, LVector b) //Tested and works
	{
		return vectorLength(twoPointVector(a,b));
	}	
	
	/*static final public ArrayList<LVector> numbersToVectors(ArrayList<ArrayList<Float>> numbers) //ERROR
	{
		ArrayList<LVector> vectors = new ArrayList<LVector>();
		
		ArrayList<Float> x = numbers.get(0);
		ArrayList<Float> y = numbers.get(1);
		ArrayList<Float> z = numbers.get(2);
		  
		int[] listSizes = new int[]{x.size(),y.size(),z.size()};
		java.util.Arrays.sort(listSizes);
		int maxSize = listSizes[2];
		
		while(x.size() < maxSize)
		{
			for(float f: x)
			{
				x.add(f);
			}
		}
		
		while(y.size() < maxSize)
		{
			for(float f: y)
			{
				y.add(f);
			}
		}
		
		while(y.size() < maxSize)
		{
			for(float f: z)
			{
				z.add(f);
			}
		}
		
		x = Lists.subList(x,0,maxSize);
		y = Lists.subList(x,0,maxSize);
		z = Lists.subList(x,0,maxSize);
		
		for(int i = 0; i < maxSize; i++)
		{
			vectors.add(new LVector(x.get(i), y.get(i), z.get(i)));
		}
		
		return vectors;		
	}*/
	
	//TODO pointBaryCentric
	
	/**
	 * Converts a cylindrical vector to a Cartesian vector
	 * @param v the input vector in cylindrical coordinates
	 * @return the vector in Cartesian coordinates
	 */
	
	static final public LVector cylindricalToCartesianVector(LVector v) //Tested and works
	{
		return new LVector((float)(v.x*Math.cos(v.y)), (float)(v.x*Math.sin(v.y)), v.z);
	}
	
	//TODO cartesian to cylindrical
	
	/*static final public ArrayList<ArrayList<LVector>> groupedVectors(ArrayList<LVector> vectors, float tolerance) //ERROR
	{
		ArrayList<ArrayList<LVector>> vectorGroups = new ArrayList<ArrayList<LVector>>();
		
		ArrayList<Integer> sortedIndexes = new ArrayList<Integer> ();
		sortedIndexes.add(-1);
		
		for(int i = 0; i < vectors.size(); i++){
			ArrayList<LVector> vectorGroup = new ArrayList<LVector>();
			for(int k : sortedIndexes)
			{
				if(k != i)
				{
					LVector a = vectors.get(i);
					for(int j = 0; k < vectors.size(); j++)
					{
						if(k != j && i != j)
						{
							LVector b = vectors.get(j);
							if(vectorDistance(a,b) <= tolerance)
							{
								vectorGroup.add(b);
								sortedIndexes.add(j);
							}
						}
					}
				}
			}
			vectorGroups.add(vectorGroup);
		}
		
		return vectorGroups;
	}*/
	
	//TODO pointoriented
	
	/**
	 * Converts a polar vector to a Cartesian vector
	 * @param v the input vector in polar coordinates
	 * @return the vector in Cartesian coordinates
	 */
	
	static final public LVector polarToCartesianVector(LVector v) //Tested and works
	{
		float x = (float)(v.x*Math.sin(v.y)*Math.cos(v.z));
		float y = (float)(v.x*Math.sin(v.y)*Math.sin(v.z));
		float z = (float)(v.x*Math.cos(v.y));
		
		return new LVector(x,y,z);
	}
	
	/*static final public LVector cartesianToPolarVector(LVector v) //ERROR
	{
		float r = vectorLength(v);
		float theta = (float)(Math.atan(v.y/v.x));
		float phi = (float)(Math.acos(v.z/r));
		
		return new LVector(r, theta, phi);
	}*/
	
	/*static final public ArrayList<ArrayList<Float>> vectorsToNumbers(ArrayList<LVector> vectors) //ERROR
	{
		ArrayList<ArrayList<Float>> numbers = new ArrayList<ArrayList<Float>>();
		
		for(LVector v: vectors)
		{
			ArrayList<Float> number = new ArrayList<Float>();
			number.add(v.x);
			number.add(v.y);
			number.add(v.z);
			
			numbers.add(number);
		}
		
		return numbers;
	}*/
	
	//TODO pullpoint
	
	//TODO sortpoint
	
	//TODO sort along curve
	
	/**
	 * Populates a given region with random vectors
	 * @param regionWidth The width of the region
	 * @param regionDepth The depth of the region
	 * @param regionHeight The height of the region
	 * @param amount The amount of vectors in the list
	 * @return A list with random vectors
	 */
	
	public final static ArrayList<LVector> randomVectors(float regionWidth, float regionDepth, float regionHeight, int amount)
	{
		ArrayList<LVector> randomVectors = new ArrayList<LVector>();

		for (int i = 0; i<amount; i++) 
		{
			LVector v = new LVector(
				(float) (java.lang.Math.random()*regionWidth), 
		    	(float) (java.lang.Math.random()*regionDepth), 
		    	(float) (java.lang.Math.random()*regionHeight)
		    );

		    randomVectors.add(v);
		}

		return randomVectors;
	}
	
	//TODO Populate Geometry
	
	//TODO hexagonal
	
	//TODO radial
	
	/**
	 * Generates a two-dimensional rectangular grid
	 * @param planeWidth the total width of the grid
	 * @param planeHeight the total height of the grid
	 * @param columns the amount of columns
	 * @param rows the amount of rows
	 * @return the vector grid as a nested list
	 */
	
	public final static ArrayList<ArrayList<LVector>> rectangularGrid(float planeWidth, float planeHeight, int columns, int rows) //Tested and works
	{
		ArrayList<ArrayList<LVector>> vectorGrid = new ArrayList<ArrayList<LVector>>();
		
		float stepWidth = planeWidth/(columns-1);
		float stepHeight = planeHeight/(rows-1);
		
		for(int i = 0; i<rows; i++)
		{
			ArrayList<LVector> row = new ArrayList<LVector>();
			for(int j = 0; j < columns; j++)
			{
				row.add(new LVector(j*stepWidth, i*stepHeight));
			}
			vectorGrid.add(row);
		}
		
		return vectorGrid;
	}
	
	//TODO square
	
	//TODO triangular
	
}
