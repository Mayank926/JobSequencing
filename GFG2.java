// { Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class Job {
    int id, profit, deadline;
    Job(int x, int y, int z){
        this.id = x;
        this.deadline = y;
        this.profit = z; 
    }
}

class GfG {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //testcases
		int t = Integer.parseInt(br.readLine().trim());
		while(t-->0){
            String inputLine[] = br.readLine().trim().split(" ");
            
            //size of array
            int n = Integer.parseInt(inputLine[0]);
            Job[] arr = new Job[n];
            inputLine = br.readLine().trim().split(" ");
            
            //adding id, deadline, profit
            for(int i=0, k=0; i<n; i++){
                arr[i] = new Job(Integer.parseInt(inputLine[k++]), Integer.parseInt(inputLine[k++]), Integer.parseInt(inputLine[k++]));
            }
            
            Solution ob = new Solution();
            
            //function call
            int[] res = ob.JobScheduling(arr, n);
            System.out.println (res[0] + " " + res[1]);
        }
    }
}// } Driver Code Ends


class Solution
{
    //Function to find the maximum profit and the number of jobs done.
     int maxDeadline;
     int minDeadline;
    int[] JobScheduling(Job arr[], int n)
    {
        if(arr.length==1)
            return new int[]{1,arr[0].profit};
        int profit=0;
        int countOfSelectedJobs=0;
        maxDeadline=Integer.MIN_VALUE;
        /*
        System.out.println();
        for(Job j : arr)
            System.out.print(" "+j.profit);
        */
        mergeSort(arr,0,n-1);
        /*
        System.out.println();
        for(Job j : arr)
            System.out.print(" "+j.profit);
        */
        int selectedJobsSize=maxDeadline;
        int[] selectedJobs = new int[selectedJobsSize+1];
        Arrays.fill(selectedJobs,-1);
        //System.out.println();
        for(int i =0;i<arr.length;i++){
            Job curMax=arr[i];
            int curDeadline = curMax.deadline;
            while(selectedJobs[curDeadline]!=-1 & curDeadline>=1)
                curDeadline--;
            if(curDeadline==0 && curMax.deadline==selectedJobsSize)
                break;
            if(curDeadline>0){
                selectedJobs[curDeadline]=curMax.profit;
                profit+=curMax.profit;
                countOfSelectedJobs++;
            }
            //Arrays.stream(selectedJobs).forEach(e -> System.out.print(" "+e));
            //System.out.println();
        }
        return new int[]{countOfSelectedJobs,profit};
    }
    
    void mergeSort(Job[] arr,int l, int r){
        if(l<r){
            int m = l + (r-l)/2;
            mergeSort(arr,l,m);
            mergeSort(arr,m+1,r);
            merge(arr,l,m,r);
        }
    }
    
    void merge(Job[] arr, int l, int m, int r){
        Job[] b = new Job[r-l+1];
        int s = 0;
        int i=l;
        int j=m+1;
        while(i<=m && j<=r){
            if(arr[i].profit>=arr[j].profit)
                b[s++]=arr[i++];
            else
                b[s++]=arr[j++];
        }
        if(i>m)
            while(j<=r)
                b[s++]=arr[j++];
        if(j>r)
            while(i<=m)
                b[s++]=arr[i++];
        for(int x=0;x<s;x++){
            if(b[x].deadline>maxDeadline)
                maxDeadline=b[x].deadline;
            //if(b[x].deadline<minDeadline)
              //  minDeadline=b[x].deadline;
            arr[l++]=b[x];
        }
    }
}
