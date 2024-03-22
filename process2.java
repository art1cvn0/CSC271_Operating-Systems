import java.util.*;
import java.lang.*;

import random.process.arrivalTimeSort;

/*2- Preemptive scheduler shortest remaining time using: variable arrival time, with estimate criteria.
The comparison must involve calculating Average: Turn Around Time, Waiting Time and Response Time.*/
class process2 {
		public int arrivalTime, burstTime,waitingTime,turnAroundTime,responseTime;
		public int truearrivalTime, trueburstTime;
		public static int totalTime = 0;
		public String processName;
		public static int count;
		
		//constructor
		public process2(String processName,int arrivalTime,int burstTime,int waitingTime,int turnAroundTime,int responseTime) {
			this.processName = processName;
			this.arrivalTime = arrivalTime;
			this.burstTime = burstTime;
			this.truearrivalTime = arrivalTime;
			this.trueburstTime = burstTime;
			this.waitingTime=0;
			this.turnAroundTime =0;
			this.responseTime=0;
			
			count++;totalTime += burstTime;}

		public void printProcessInfo(){System.out.println(processName+"\t\t"+truearrivalTime + "\t   " +trueburstTime
				+ "\t    " +waitingTime+"\t\t   " +turnAroundTime+"\t\t" +responseTime);}
		
		public static void rtwTime (process2[] Processes) {
			int index =0,time=0,index2=0;
			int[] response = new int[totalTime];
			int[] r = new int[Processes.length];
			process2[] pro = new process2[totalTime];
			int[] tt = new int[Processes.length];
			List<process2> arr = new ArrayList(Processes.length); 
			List<process2>queue = new ArrayList(Arrays.asList(Processes)); 
			List<process2> arr2 = new ArrayList(Processes.length); 	
			
			while (time != totalTime) {
				Collections.sort(queue, new shortestTimeSort()); 
				response[index] = time;
				pro[index] = queue.get(0);	
				
				queue.get(0).burstTime--;
				if (queue.get(0).burstTime == 0) {
					tt[index2] = time;
					arr.add(queue.get(0));
					queue.remove(0);index2++;}
				
				Collections.sort(queue, new shortestTimeSort()); 
				for (int i = 0; i <queue.size(); i++)
					if (queue.get(i).arrivalTime>0)
						queue.get(i).arrivalTime--;
	
				index++;time++;}
			
			//response time calculation
			index=0;
			for (int i=0;i<totalTime;i++) {
				if (arr2.contains(pro[i]) == false) {
					arr2.add(pro[i]);
					if (response[i]>pro[i].truearrivalTime) {
						r[index] = response[i]-pro[i].truearrivalTime;
						pro[i].responseTime = r[index];
					index++;}
					else {pro[i].responseTime = 0;index++;}}}
			
			//turnaround time calculation
			for (int i=0;i<arr.size();i++) {
				arr.get(i).turnAroundTime = tt[i] - arr.get(i).truearrivalTime +1;}
			
			//waiting time calculation	
			for (int i=0;i<totalTime;i++) {
				pro[i].waitingTime = pro[i].turnAroundTime - pro[i].trueburstTime;}
				
				
		}
		
		//average response time calculator
		public static double avgResponseTime (process2[] Processes) {
			double total = 0;
			for (int i=0;i<Processes.length;i++)
				total += Processes[i].responseTime;
			return (total/Processes.length);}
		
		//average turnaround time calculator
			public static double avgTurnaroundTime (process2[] Processes) {
				double total = 0;
				for (int i=0;i<Processes.length;i++)
					total += Processes[i].turnAroundTime;
				return (total/Processes.length);}
			
		//average waiting time calculator
			public static double avgWaitingTime (process2[] Processes) {
				double total = 0;
				for (int i=0;i<Processes.length;i++)
					total += Processes[i].waitingTime;
				return (total/Processes.length);}
			
public static void main(String args[])
{  
	Scanner sc = new Scanner(System.in);
	String header = "Process    Arrival    Burst    Waiting    Turn Around    Response\n";
	System.out.println("Enter number of processes: ");
	int totalProcesses = sc.nextInt();
	process2[] Processes = new process2[totalProcesses];
	
	for (int i=0;i<totalProcesses;i++) {
		String processName = "p"+(i+1);
		System.out.println("Enter the arrival time of "+processName);
		int arrivalTime = sc.nextInt();
		System.out.println("Enter the burst time of "+processName);
		int burstTime = sc.nextInt();
		Processes[i] = new process2(processName,arrivalTime,burstTime,0,0,0);}
	
	//printing inputs and calculations
	rtwTime(Processes); 
	System.out.println(header);
	for (int i=0;i<totalProcesses;i++) {
		Processes[i].printProcessInfo();}
	System.out.println();
	
	System.out.println("Average Waiting Time: "+avgWaitingTime(Processes));
	System.out.println("Average Turnaround Time: "+avgTurnaroundTime(Processes));
	System.out.println("Average Response Time: "+avgResponseTime(Processes));
	
}

static class shortestTimeSort implements Comparator<process2> {
	public int compare(process2 a, process2 b) {
		if (a.arrivalTime - b.arrivalTime == 0) {
			return a.burstTime - b.burstTime;}
		else 
			return a.arrivalTime - b.arrivalTime; }} 
 
}