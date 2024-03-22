package random;
import java.util.*;
public class bankersAlgorithm {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of processes");
		int np= sc.nextInt();

		int nr = 3;
		int count =0;
		int count2=1;

		int Allocation[][]= new int[np][nr];
		int Max[][]= new int[np][nr];
		int Need[][]= new int[np][nr];
		int Available[]= new int[nr];
		int req[] = new int[nr];
		String sequence[] = new String[np];
		String strings[] = new String[np];
		String s = "";
		String header = "Process "+"Allocation "+"  Max  "+"  Need "+"   Available "+
		"\n     	"+"A B C "+"	    A B C "+"  A B C "+"   A B C ";
		
		//Getting Allocation
		while (count!=np) {
			System.out.println(header);
			s="P"+count;
			System.out.print("Enter allocation of A of P"+ count);
			Allocation[count][0] = sc.nextInt();
			s+="\t"+Allocation[count][0];
			System.out.print("Enter allocation of B of P"+ count);
			Allocation[count][1] = sc.nextInt();
			s+=" "+Allocation[count][1];
			System.out.print("Enter allocation of C of P"+ count);
			Allocation[count][2] = sc.nextInt();
			s+=" "+Allocation[count][2];
			strings[count]=s;
			count++;}
		count =0;
		//Getting Max
		while (count!=np) {
			s="";
			System.out.print("Enter Max of A of P"+ count);
			Max[count][0] = sc.nextInt();
			s+="\t    "+Max[count][0];
			System.out.print("Enter Max of B of P"+ count);
			Max[count][1] = sc.nextInt();
			s+=" "+Max[count][1];
			System.out.print("Enter Max of C of P"+ count);
			Max[count][2] = sc.nextInt();
			s+=" "+Max[count][2];
			strings[count]=strings[count]+s;
			count++;}
		
		//Calculating Need
		count =0;
		while (count!=np) {
			s="";
			Need[count][0] = Max[count][0]-Allocation[count][0];
			s+="   "+Need[count][0];
			Need[count][1] = Max[count][1]-Allocation[count][1];
			s+=" "+Need[count][1];
			Need[count][2] = Max[count][2]-Allocation[count][2];
			s+=" "+Need[count][2];
			strings[count]=strings[count]+s;
			count++;}
		
		s="";count=0;
		System.out.print("Enter how much of A is available");
		Available[0] = sc.nextInt();
		s+="    "+Available[0];
		System.out.print("Enter how much of B is available");
		Available[1] = sc.nextInt();
		s+=" "+Available[1];
		System.out.print("Enter how much of C is available");
		Available[2] = sc.nextInt();
		s+=" "+Available[2];
		strings[count]=strings[count]+s;
		
		System.out.println(header);
		for (int i=0;i<strings.length;i++) {
		System.out.println(strings[i]);}
		int i,j;
		
		//step 1
		int work[] = new int[nr];    
		boolean finish[] = new boolean[np]; 
		
	    for (i=0;i<nr;i++){work[i] = Available[i];}
	    for (i=0;i<np;i++) {finish[i] = false;}   
	    
	    //step2
	    while (count!=np){
	        boolean flag = false;
	        for (i=0;i<np;i++){
	            if (finish[i] == false){
	            for (j=0;j<nr; j++) {
	                if (Need[i][j]>work[j])
	                	break;}
	            //step3
	            if (j==nr){
	            	for (j=0;j<nr;j++){
	            		work[j]+=Allocation[i][j];}
	            	sequence[count]="P"+i;
	            	finish[i]=true;
	            	flag=true;        
	            	count++;
	            	
	                //displaying available
	                av(work,np,count2,strings);
	                count2++;
	                System.out.println(header);
	        		for (i=0;i<strings.length;i++) {
	        		System.out.println(strings[i]);}}}
	        }
	        if (flag == false){break;}
	    }
	    //step4
	    if (count!=np){
	        System.out.println("No safe sequence");}
	    else{
	        System.out.println("A safe sequence is: ");
	        for (i=0;i<np;i++){
	            System.out.print(sequence[i] + " ");}
	    }
	    
	    while(true) {
	    System.out.println("Enter number of process to request resources");
	    int num = sc.nextInt();
	    System.out.println("Enter amount of A");
	    req[0] = sc.nextInt ();
	    System.out.println("Enter amount of B");
	    req[1] = sc.nextInt ();
	    System.out.println("Enter amount of C");
	    req[2] = sc.nextInt ();
	    
	    System.out.println(resReq(req,np,num,Need,Available,Allocation));
	}}
	
	public static void av(int[]work, int np,int count,String strings[]) {
		if (count<np) {
		String s="";
		s+="    "+work[0];
		s+=" "+work[1];
		s+=" "+work[2];
		strings[count]=strings[count]+s;}
	}
	
	public static boolean isSafe(int nr, int np, int[] Available, int[][] Allocation, int[][] Need) {
int i,j,count = 0;	 boolean issafe = false;	
		//step 1
		int work[] = new int[nr];    
		boolean finish[] = new boolean[np]; 
		
	    for (i=0;i<nr;i++){work[i] = Available[i];}
	    for (i=0;i<np;i++) {finish[i] = false;}   
	    
	    //step2
	    while (count!=np){
	        boolean flag = false;
	        for (i=0;i<np;i++){
	            if (finish[i] == false){
	            for (j=0;j<nr; j++) {
	                if (Need[i][j]>work[j])
	                	break;}
	            //step3
	            if (j==nr){
	            	for (j=0;j<nr;j++){
	            		work[j]+=Allocation[i][j];}
	            	finish[i]=true;
	            	flag=true;        
	            	count++;
	                }}
	        }
	        if (flag == false){break;}
	    }
	    //step4
	    if (count!=np){
	        issafe = false;}
	    else{
	        issafe = true;}
	    
	    return issafe;
	    }
	
	
	public static String resReq(int req[], int np, int num,int[][] Need, int[] Available, int[][] Allocation) {
		int[] tempav = new int[Available.length];
		int[][] tempalloc = new int[np][3];
		int[][] tempneed = new int[np][3];
		
		for (int i=0;i<Available.length;i++) {
			tempav[i] = Available[i];}
		for (int i=0;i<np;i++) {
			for(int j=0;j<3;j++) {
			tempalloc[i][j] = Allocation[i][j];}}
		for (int i=0;i<np;i++) {
			for(int j=0;j<3;j++) {
			tempneed[i][j] = Need[i][j];}}
		

		for (int i=0;i<3;i++) {
			if (req[i] <= Need[num][i])
				continue;
			else
				return "Process exceeded maximum claim, resources not granted";}
		
		for (int i=0;i<3;i++) {
			if (req[i] <= Available[i])
				continue;
			else 
				return "Resources not available";}
		
		
		for (int i=0;i<3;i++) {
			tempav[i]-=req[i];
		}
		for (int i=0;i<3;i++) {
			tempalloc[num][i]+=req[i];
		}
		for (int i=0;i<3;i++) {
			tempneed[num][i]-=req[i];
		}
		
		boolean isSafe = isSafe(3,np,tempav,tempalloc,tempneed);
		
		if (isSafe)
			return "Resources are granted";
		else
			return "Resources not granted, unsafe state";
	}
}
