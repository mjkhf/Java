import java.util.Scanner;

public class MagicSquare {

public void odd(int[][] magic){

	int N = magic.length;
	int row = N-1;
	int col = N/2;
	magic[row][col] = 1;
		for(int i = 2; i<=N*N; i++){
			if(magic[(row+1)%N][(col+1)%N]== 0){
				row = (row+1)%N;
				col = (col+1)%N;
			}else{
				row = (row-1+N)%N;
			}
			magic[row][col] = i;
		}
	
}
public void doublyEven(int[][] magicsqr){
	int N = magicsqr.length;
	
	int miniSqrNum = N/4; //size of boxes
	int cnt = 1; 	      //counter 1 to N*N
	int invCnt = N*N;     //counter N*N to 1
	for(int i=0;i<N;i++){

		for(int j=0;j<N;j++){

		   if(j>=miniSqrNum && j<N-miniSqrNum){
			if(i>=miniSqrNum && i<N-miniSqrNum)
				magicsqr[i][j] = cnt;    //central box
			else 
				magicsqr[i][j] = invCnt; // up & down boxes

		   }
		   else if(i<miniSqrNum || i>=N-miniSqrNum){
			 magicsqr[i][j]=cnt;	         // 4 corners
		   }
		   else
                      magicsqr[i][j] = invCnt;  	// left & right boxes

		   cnt++;
		   invCnt--;
		}

	}
	
}
public void singlyEven(int[][] magicsqr){

	int N = magicsqr.length;
	int halfN = N/2; //size of ABCD boxes
	int k = (N-2)/4; // to get 'noses' of A & D boxes
        int temp;

        int [] swapCol = new int[N]; // columns which need to swap between C-B & A-D
     	int index=0; // index of swapCol

        int [][] miniMagic =  new int [halfN][halfN];
  	odd(miniMagic);	//creating odd magic square for A box
	
	//creating 4 magic boxes
  	for (int i=0; i<halfN; i++)
    	   for (int j=0; j<halfN; j++){
	      magicsqr[i][j] = miniMagic[i][j]; 	  		  //A box
      	      magicsqr[i+halfN][j+halfN] = miniMagic[i][j]+halfN*halfN;   //B box
              magicsqr[i][j+halfN] = miniMagic[i][j]+2*halfN*halfN;       //C box
              magicsqr[i+halfN][j] = miniMagic[i][j]+3*halfN*halfN;       //D box
           }

  
  
      for (int i=1; i<=k; i++)
        swapCol[index++] = i;

      for (int i=N-k+2; i<=N; i++)
        swapCol[index++] = i;

      //swaping values between C-B & A-D by known columns
      for (int i=1; i<=halfN; i++)
        for (int j=1; j<=index; j++){
          temp=magicsqr[i-1][swapCol[j-1]-1];
          magicsqr[i-1][swapCol[j-1]-1]=magicsqr[i+halfN-1][swapCol[j-1]-1];
          magicsqr[i+halfN-1][swapCol[j-1]-1]=temp;
        }

      //swaping noses
      temp=magicsqr[k][0]; 
      magicsqr[k][0]=magicsqr[k+halfN][0]; 
      magicsqr[k+halfN][0]=temp;


      temp=magicsqr[k+halfN][k]; 
      magicsqr[k+halfN][k]=magicsqr[k][k]; 
      magicsqr[k][k]=temp;
      //end of swaping

}
public void showMagicSqr(int[][] magicsqr){
	int N = magicsqr.length;
	 for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) 
                  System.out.print(magicsqr [i][j] + " ");
            
            System.out.println();
        }
}
public boolean isMagicSqr(int[][] magicsqr){
	int N = magicsqr.length;
	int magicConst = (N*N*N+N)/2;

	int rowsum = 0;
	int colsum = 0;
	int diag1 = 0;
	int diag2 = 0;
	for(int i=0;i<N;i++){
		for(int j=0;j<N;j++){
			rowsum += magicsqr[i][j];
			colsum += magicsqr[j][i];
		}
		diag1 += magicsqr[i][i];
		diag2 += magicsqr[i][N-i-1];
		if(rowsum!=magicConst){ return false; }
		if(colsum!=magicConst){ return false; }
		rowsum=0; colsum=0;
	}
	if(diag1!= magicConst || diag2 != magicConst) 
	return false;
	
	System.out.println("Row sum = Coulmn Sum = Diagonal Sum = "+magicConst);
	return true;
}
public static void main(String[] args) { 


       //int N = Integer.parseInt(args[0]);
	Scanner reader = new Scanner(System.in);
	System.out.println("Please enter the size of graph matrix");
	int N = reader.nextInt();
	int[][] matrix = new int[N][N];
        MagicSquare ms = new MagicSquare();

	   if(N<=2) throw new RuntimeException("Matrix size must be greater than 2");
        
	   if (N % 2==1) 
	        System.out.println("Please enter an even number "); 
	   else if(N%4 ==0) 
		ms.doublyEven(matrix);	   
           else 
		ms.singlyEven(matrix);

	
		ms.showMagicSqr(matrix);
		if(ms.isMagicSqr(matrix)) 
			System.out.println("Generated Square is a magic square"); 
		else  
			System.out.println("Generated Square is not a magic square"); 

    }
}