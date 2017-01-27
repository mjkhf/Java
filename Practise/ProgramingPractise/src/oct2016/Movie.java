package oct2016;

class Movie{
	String title;
	String genre;
	int rating;
	
	void playit(){
		System.out.println("Playing the movie "+title+" "+genre+" "+rating+" ");
	}
}

class MovieTestDrive{
	public static void main(String[] args){
		Movie one = new Movie();
		one.title = "Kuch kuch hota h";
		one.genre = "Romance";
		one.rating = 5;
		one.playit();
		
		Movie two = new Movie();
		two.title = "chudail";
		two.genre = "Horror";
		two.rating = 2;
		two.playit();
	}
}