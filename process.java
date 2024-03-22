import java.util.*;

/* 1- Preemptive scheduler using: variable arrival time, with process priority (1 being highest priority). 
 * Also, processes of similar priorities use shortest remaining time first.
*/
class process{
	public int arrivalTime,priority, burstTime,waitingTime,turnAroundTime,responseTime;
	public int truearrivalTime, trueburstTime;
	public static int totalTime = 0;
	public String processName;
	public static int count;
	
	//constructor
	public process(String processName,int arrivalTime, int priority,int burstTime,int waitingTime,int turnAroundTime,int responseTime) {
		this.processName = processName;
		this.arrivalTime = arrivalTime;
		this.priority = priority;
		this.burstTime = burstTime;
		this.truearrivalTime = arrivalTime;
		this.trueburstTime = burstTime;
		this.waitingTime=0;
		this.turnAroundTime =0;
		this.responseTime=0;
		
		count++;totalTime += burstTime;}
	
	//methods to display process info
	public void printProcessInfo(){System.out.println(processName+"\t    "+priority+"\t\t"+truearrivalTime + "\t   " +trueburstTime
			+ "\t    " +waitingTime+"\t\t   " +turnAroundTime+"\t\t" +responseTime);}
	
	public void printName() {System.out.println(processName);}
	
	//calculator of response time of each process
	public static void rtwTime (process[] Processes) {
		int index =0,time=0,index2=0;
		int[] response = new int[totalTime];
		int[] r = new int[Processes.length];
		process[] pro = new process[totalTime];
		int[] tt = new int[Processes.length];
		List<process> arr = new ArrayList(Processes.length); 
		List<process> arr2 = new ArrayList(Processes.length); 
		List<process>queue = new ArrayList(Arrays.asList(Processes)); 
		
		while (time != totalTime) {
		Collections.sort(queue, new arrivalTimeSort()); 
			response[index] = time;
			pro[index] = queue.get(0);
			
		queue.get(0).burstTime--;
		if (queue.get(0).burstTime == 0) {
			tt[index2] = time;
			arr2.add(queue.get(0));
			queue.remove(0);
			index2++;}

		for (int i = 0; i <queue.size(); i++)
			if (queue.get(i).arrivalTime>0)
				queue.get(i).arrivalTime--;
		//Collections.sort(queue, new arrivalTimeSort());
		index++;time++;}
		
		//response time calculation
		index=0;
		
		for (int i=0;i<totalTime;i++) {
			if (arr.contains(pro[i]) == false) {
				arr.add(pro[i]);
				if (response[i]>pro[i].truearrivalTime) {
					r[index] = response[i]-pro[i].truearrivalTime;
					pro[i].responseTime = r[index];
				index++;}
				else {pro[i].responseTime = 0;index++;}}}
		
		//turnaround time calculation
		for (int i=0;i<arr2.size();i++) {
			arr2.get(i).turnAroundTime = tt[i] - arr2.get(i).truearrivalTime +1;
			}
		
		//waiting time calculation	
		for (int i=0;i<totalTime;i++) {
			pro[i].waitingTime = pro[i].turnAroundTime - pro[i].trueburstTime;}
	}

	//average response time calculator
	public static double avgResponseTime (process[] Processes) {
		double total = 0;
		for (int i=0;i<Processes.length;i++)
			total += Processes[i].responseTime;
		return (total/Processes.length);}
	
	//average turnaround time calculator
		public static double avgTurnaroundTime (process[] Processes) {
			double total = 0;
			for (int i=0;i<Processes.length;i++)
				total += Processes[i].turnAroundTime;
			return (total/Processes.length);}
		
	//average waiting time calculator
		public static double avgWaitingTime (process[] Processes) {
			double total = 0;
			for (int i=0;i<Processes.length;i++)
				total += Processes[i].waitingTime;
			return (total/Processes.length);}	
	
public static void main(String args[])
	{  
		Scanner sc = new Scanner(System.in);
		String header = "Process    Priority    Arrival    Burst    Waiting    Turn Around    Response\n";
		
		//getting inputs
		System.out.println("Enter number of processes: ");
		int totalProcesses = sc.nextInt();
		process[] Processes = new process[totalProcesses];
		
		for (int i=0;i<totalProcesses;i++) {
			String processName = "p"+(i+1);
			System.out.println("Enter the priority of "+processName);
			int priority = sc.nextInt();
			System.out.println("Enter the arrival time of "+processName);
			int arrivalTime = sc.nextInt();
			System.out.println("Enter the burst time of "+processName);
			int burstTime = sc.nextInt();
			Processes[i] = new process(processName,arrivalTime,priority,burstTime,0,0,0);}
		
		//printing inputs and calculations
		rtwTime(Processes); 
		System.out.println(header);
		for (int i=0;i<totalProcesses;i++) {
			Processes[i].printProcessInfo();}
		System.out.println();
		
		//calculating & printing averages
		System.out.println("Average Waiting Time: "+avgWaitingTime(Processes));
		System.out.println("Average Turnaround Time: "+avgTurnaroundTime(Processes));
		System.out.println("Average Response Time: "+avgResponseTime(Processes));
	}

static class arrivalTimeSort implements Comparator<process> {
	public int compare(process a, process b) {
		if (a.arrivalTime - b.arrivalTime == 0) {
			if (a.priority-b.priority == 0) {
				return a.burstTime - b.burstTime;}
			else {return a.priority-b.priority;}}
		else 
			return a.arrivalTime - b.arrivalTime;
    }
}

}
