package oct2016;

class DogArray{
	
	String name;
	public static void main(String[] args){
		
		DogArray dog1 = new DogArray();
		dog1.bark();
		dog1.name = "Bar1";
		
		DogArray[] myDogs = new DogArray[3];
		
		myDogs[0] = new DogArray();
		myDogs[1] = new DogArray();
		myDogs[2] = dog1;
		
		myDogs[0].name = "Fred";
		myDogs[1].name = "Chutiya";
		
		System.out.println("Dog array 2 name is "+myDogs[2].name);
		
		int x =0;
		while (x < myDogs.length){
			
			myDogs[x].bark();
			x = x+1;
		}
	
	}
	public void bark(){
		System.out.println(name + " Says Ruff ");
	}
}