import java.util.PriorityQueue;
public class heapquestions{
    // 1. kth smallest in an unsorted array (gfg)
    // max pq 
    // O(nlogk)
    public int kthsmallest(int[]arr,int k){
        PriorityQueue<Integer>pq=new PriorityQueue<>((a,b)->{
            return b-a;
        });
        for(int ele:arr){
            pq.add(ele);
            if(pq.size()>k){
                pq.remove();
            }
        }
        return pq.peek();
    }
    // 2. kth largest in an unsorted array 
    // min pq 
    // O(nlogk)
    public int kthlargest(int[]arr,int k){
        PriorityQueue<Integer>pq=new PriorityQueue<>();
        for(int ele:arr){
            pq.add(ele);
            if(pq.size()>k){
                pq.remove();
            }
        }
        return pq.peek();
    }
    // optimisation O(nlogk)-> O(n)
    public void swap(int[]arr,int x,int y){
        int temp=arr[x];
        arr[x]=arr[y];
        arr[y]=temp;
    }
    public boolean compareTo(int[]arr,int x,int y,boolean isincreasing){
        if(isincreasing){
            return arr[x]>arr[y];
        }
        else{
            return arr[y]>arr[x];
        }
    }
    public void downheapify(int[]arr,int pi,int li,boolean isincreasing){
        int lci=2*pi+1;
        int rci=2*pi+2;
        int maxidx=pi;
        if(lci<=li && compareTo(lci,maxidx)){
            maxidx=lci;
        }
        if(rci<=li && compareTo(rci,maxidx)){
            maxidx=rci;
        }
        if(maxidx!=pi){
            swap(maxidx,pi);
            downheapify(maxidx);
        }
    }
    // O(n+klogn)
    public int kth largest(int[]arr,int k){
        int li=arr.length-1;
        boolean isincreasing=true;
        int n=arr.length;
        // create heap 
        for(int i=li;i>=0;i--){
            downheapify(i);
        }
        int K=k;
        while(li>0 && K-->0){
            swap(arr,0,li);
            li--;
            downheapify(0);
        }
        return arr[n-k];
    }
    // 3. kth largest element in a stream (leetcode 703)
    PriorityQueue<Integer>pq=new PriorityQueue<>();
    int K;
    public KthLargest(int k, int[] nums) {
        K=k;
        for(int ele:nums){
            pq.add(ele);
            if(pq.size()>K){
                pq.remove();
            }
        }
    }
    
    public int add(int val) {
        pq.add(val);
        if(pq.size()>K){
            pq.remove();
        }
        return pq.peek();
    }
    // 4. kth smallest element in a row wise col wise sorted array(leetcode 378) 
    public int kthSmallest(int[][] matrix, int k) {
        int n=matrix.length;
        int m=matrix[0].length;
        // min pq
       PriorityQueue<Integer>pq=new PriorityQueue<>((a,b)->{
           int i1=a/m;
           int j1=a%m;
           int i2=b/m;
           int j2=b%m;
           return matrix[i1][j1]-matrix[i2][j2];
       });
       for(int i=0;i<n;i++){
           pq.add(i*m+0);
       }
       int r=0;
       int c=0;
       while(k-->0){
           int idx=pq.remove();
           r=idx/m;
           c=idx%m;
           if(c+1<m){
               pq.add(r*m+(c+1));
           }
       }
       return matrix[r][c];
    }
    // 5. Top K Frequent Elements (leetcode 347)
     public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer>map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            }
            else{
                map.put(nums[i],1);
            }
        }
        PriorityQueue<int[]>pq=new PriorityQueue<>((a,b)->{
            return a[1]-b[1];
        });
        
        for(int key:map.keySet()){
            pq.add(new int[]{key,map.get(key)});
            if(pq.size()>k){
                pq.remove();
            }
        }
        int[]ans=new int[pq.size()];
        int idx=0;
        while(pq.size()!=0){
            ans[idx++]=pq.remove()[0];
        }
        return ans;
    }
}