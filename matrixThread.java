package random;
import java.util.*;

public class matrixThread extends Thread {
	public int[][] mat1;
	public int[][] mat2;
	public static int[][] res;
	public static int resrows;
	public static int rescols;
	public static int kmax;
	public int row;
	public int col;
	
	public matrixThread(int matrix1[][], int[][] matrix2, int[][] result, int i, int j) {
		mat1 = matrix1;
		mat2 = matrix2;
		res = result;
		col = j;
		row = i;
	}
	
	//Result matrix calculation
	public void run()
	{  
		for(int i=0; i<resrows; i++) {
            for(int j=0; j<rescols; j++) {
            	res[i][j]=0;
            	for(int k=0;k<kmax;k++) {
            		res[i][j] += mat1[i][k]*mat2[k][j];
                     }}}}
	
	//Result matrix display function
	public static void printResult() {
		String s3 = "";
		System.out.println("\nResult Matrix");
		for (int i=0;i<resrows;i++) {
			for (int j=0;j<rescols;j++) {
				s3 += res[i][j]+"\t";
			}
			s3+="\n";}
		System.out.println(s3);
		}	  
	
	public static void main(String args[])
	{ 
		Scanner sc = new Scanner(System.in);
		String s1 = ""; String s2 ="";
		/*To perform matrix multiplication, 
		 * the first matrix must have the same number of columns as the second matrix has rows. col1=row2
		 * The number of rows of the resulting matrix equals the number of rows of the first matrix,  
		 * and the number of columns of the resulting matrix equals the number of columns of the second matrix. res[row1][col2]
		 */
		
		//Matrix1 getting input + display
		System.out.println("Enter the number of rows of the first matrix");
		int row1 = sc.nextInt();
		resrows = row1;
		System.out.println("Enter the number of columns of the first matrix");
		int col1 = sc.nextInt();
		kmax = col1;
		int[][] matrix1 = new int[row1][col1];
		
		System.out.println("Enter the elements of Matrix 1\n");
		System.out.println("Matrix 1"+"\n----------");
		for(int i=0; i<row1; i++) {
            for(int j=0; j<col1; j++) {
                    matrix1[i][j] = sc.nextInt();
                    s1 = s1 + matrix1[i][j] + "\t";
                    System.out.println(s1);}
            s1+="\n";}
		
		//Matrix2 getting input + display
		//System.out.println("Enter the number of rows of the second matrix");
		//int row2 = sc.nextInt();
		System.out.println("Number of rows of second matrix: " + col1);
		System.out.println("Enter the number of columns of the second matrix");
		int col2 = sc.nextInt();
		rescols = col2;
		int[][] matrix2 = new int[col1][col2];
		
		System.out.println("Enter the elements of Matrix 2\n");
		System.out.println("Matrix 2"+"\n---------");
		for(int i=0; i<col1; i++) {
            for(int j=0; j<col2; j++) {
                    matrix2[i][j] = sc.nextInt();
                    s2 = s2 + matrix2[i][j] + "\t";
                    System.out.println(s2);   }
            s2=s2+"\n";}
		
		//forming threads
		int[][] result = new int[row1][col2];
		Thread[][] myThreads = new Thread[row1][col2];
		
		for (int i=0;i<row1;i++) {
			for (int j=0;j<col2;j++) {
			myThreads[i][j] = new matrixThread(matrix1,matrix2,result,i,j);
			myThreads[i][j].start();
		}
		}
		
		//printing result
		printResult();
	}
}
