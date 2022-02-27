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
    int[] JobScheduling(Job arr[], int n)
    {
        int profit=0;
        int countOfSelectedJobs=0;
        int maxDeadline=getMaxDeadLine(arr);
        int[] selectedJobs = new int[maxDeadline+1];
        Arrays.fill(selectedJobs,-1);
        for(int i =0;i<arr.length;i++){
            Job curMax=getMaxProfitJob(arr);
            if(curMax==null)
                break;
            int curDeadline = curMax.deadline;
            while(selectedJobs[curDeadline]!=-1 && curDeadline>=1)
                curDeadline--;
            if(curDeadline==0 && curMax.deadline==maxDeadline)
                break;
            if(curDeadline>0){
                selectedJobs[curDeadline]=1;
                profit+=curMax.profit;
                countOfSelectedJobs++;
            }
            curMax.profit=Integer.MIN_VALUE;
        }
        return new int[]{countOfSelectedJobs,profit};
    }
    
    Job getMaxProfitJob(Job[] arr){
        int max = Integer.MIN_VALUE;
        Job result = null;
        for(int i =0;i<arr.length;i++){
            if(arr[i].profit>max){
                result= arr[i];
                max=arr[i].profit;
            }
        }
        return result;
    }
    
    int getMaxDeadLine(Job[] arr){
        int max = Integer.MIN_VALUE;
         for(int i =0;i<arr.length;i++){
            if(arr[i].deadline>max){
                max=arr[i].deadline;
            }
         }
         return max;
    }
}
