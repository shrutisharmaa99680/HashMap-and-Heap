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
    // 973. K Closest Points to Origin
    // index added in pq and sorted on basis of dis from origin 
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Integer>pq=new PriorityQueue<>((a,b)->{
            int d1=points[a][0]*points[a][0]+points[a][1]*points[a][1];
            int d2=points[b][0]*points[b][0]+points[b][1]*points[b][1];
            return d2-d1;
        });
        for(int i=0;i<points.length;i++){
            pq.add(i);
            if(pq.size()>k){
                pq.remove();
            }
        }
        int[][]ans=new int[k][];
        int li=0;
        while(pq.size()!=0){
            int idx=pq.remove();
            ans[li++]=points[idx];
        }
        return ans;
    }
    // x and y coordinates added in pq and sorted on the basis of dis from origin 
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]>pq=new PriorityQueue<>((a,b)->{
            int d1=a[0]*a[0]+a[1]*a[1];
            int d2=b[0]*b[0]+b[1]*b[1];
            return d2-d1;
        });
        for(int[]p:points){
            pq.add(new int[]{p[0],p[1]});
            if(pq.size()>k){
                pq.remove();
            }
        }
        int[][]ans=new int[k][];
        int idx=0;
        while(pq.size()!=0){
            int[]d=pq.remove();
            ans[idx++]=d;
        }
        return ans;
    }
// 692. Top K Frequent Words
// O(NlogK)
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String,Integer>map=new HashMap<>();
        for(String s:words){
            if(map.containsKey(s)){
                map.put(s,map.get(s)+1);
            }
            else{
                map.put(s,1);
            }
        }
        PriorityQueue<String>pq=new PriorityQueue<>((a,b)->{
            if(map.get(a)==map.get(b)){
                return b.compareTo(a);
            }
            return map.get(a)-map.get(b);
        });
        for(String key:map.keySet()){
            pq.add(key);
            if(pq.size()>k){
                pq.remove();
            }
        }
        List<String>ans=new LinkedList<>();
        while(pq.size()!=0){
            ans.add(0,pq.remove());
        }
        return ans;
    }
     public List<String> topKFrequent(String[] words, int k) {
        HashMap<String,Integer>map=new HashMap<>();
        for(String s:words){
            if(map.containsKey(s)){
                map.put(s,map.get(s)+1);
            }
            else{
                map.put(s,1);
            }
        }
        PriorityQueue<String>pq=new PriorityQueue<>((a,b)->{
            if(map.get(a)==map.get(b)){
                return b.compareTo(a);
            }
            return map.get(a)-map.get(b);
        });
        for(String key:map.keySet()){
            pq.add(key);
            if(pq.size()>k){
                pq.remove();
            }
        }
        LinkedList<String>ans=new LinkedList<>();
        while(pq.size()!=0){
            ans.addFirst(pq.remove());
        }
        return ans;
    }
    // 778. Swim in Rising Water
    public int swimInWater(int[][] grid) {
        int[][]dir={{0,1},{1,0},{0,-1},{-1,0}};
        int n=grid.length;
        int m=grid[0].length;
        PriorityQueue<Integer>pq=new PriorityQueue<>((a,b)->{
            int i1=a/m;
            int j1=a%m;
            int i2=b/m;
            int j2=b%m;
            return grid[i1][j1]-grid[i2][j2];
        });
        int minheight=0;
        boolean[][]vis=new boolean[n][m];
        pq.add(0);
        vis[0][0]=true;
        while(pq.size()!=0){
            int idx=pq.remove();
            int i=idx/m;
            int j=idx%m;
            int height=grid[i][j];
            minheight=Math.max(minheight,height);
            if(i==n-1 && j==m-1){
                break;
                // reached dest 
            }
            for(int d=0;d<4;d++){
                int r=i+dir[d][0];
                int c=j+dir[d][1];
                if(r>=0 && c>=0 && r<n && c<m && vis[r][c]==false){
                    vis[r][c]=true;
                    pq.add(r*m+c);
                }
            }
        }
        return minheight;
    }
}