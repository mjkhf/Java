package oct2016;

public class PhraseOMatic
{
	public static void main(String[] args)
	{
	String[] wordListOne = {"Computer Security" , "Computer Algorithms" , "Computer Networks" };
	String[] wordListTwo = {"CS6600", "CS5200", "CS5600"};
	String[] wordListThree = {"85","75","89"};
	
	int oneLength = wordListOne.length;
	int twoLength = wordListTwo.length;
	int threeLength = wordListThree.length;
	
	int rand1 = (int) (Math.random() * oneLength);
	int rand2 = (int) (Math.random() * twoLength);
	int rand3 = (int) (Math.random() * threeLength);
	
	String phrase = wordListOne[rand1] + " " + wordListTwo[rand2] + " " + wordListThree[rand3];
	System.out.println("Random 1 is "+rand1 + "random 2 is " + rand2 + "random 3 is " + rand3);
	System.out.println("What we need is a "+phrase);
	
	}
}