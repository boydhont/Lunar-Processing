package lunar;

public class LFace {

	public int a;
	public int b;
	public int c;
	//public bool flippedNormal;
	
	public LFace(int _a, int _b, int _c)
	{
		a = _a;
		b = _b;
		c = _c;
	}
	
	public LFace(int[] d)
	{
		a = d[0];
		b = d[1];
		c = d[2];
	}
	
	public LFace(java.util.ArrayList<Integer> d)
	{
		a = d.get(0);
		b = d.get(1);
		c = d.get(2);
	}
	
}
