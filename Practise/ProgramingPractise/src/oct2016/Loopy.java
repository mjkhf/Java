package oct2016;

public class Loopy
{
	
	public static void main(String[] args)
	{
		int X =1;
		System.out.println("This is before the loop");
		while(X<4)
		{
			System.out.println("The value of X " +X);
			System.out.println("This is inside the loop");
			X = X+1;
		
		}
		System.out.println("This is after the loop");
	}
	
}