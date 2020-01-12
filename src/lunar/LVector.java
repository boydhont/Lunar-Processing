package lunar;

public class LVector {

	public float x;
	public float y;
	public float z;
	
	public LVector()
	{
		x = y = z = 0;
	}
	
	public LVector(float _x, float _y)
	{
		x = _x;
		y = _y;
		z = 0;
	}
	
	public LVector(float _x, float _y, float _z)
	{
		x = _x;
		y = _y;
		z = _z;
	}
	
	public LVector(float[] f)
	{
		x = f[0];
		y = f[1];
		z = f[2];
	}
	
	public LVector(java.util.ArrayList<Float> f)
	{
		x = f.get(0);
		y = f.get(1);
		z = f.get(2);
	}
	
	public LVector(processing.core.PVector v)
	{
		x = v.x;
		y = v.y;
		z = v.z;
	}
}
