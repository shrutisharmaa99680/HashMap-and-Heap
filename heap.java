import java.util.ArrayList;
public class heap{
    private ArrayList<Integer>arr;
    // o(n+nlogn)->o(n)
    public heap(int[]arr){
        this.arr=new ArrayList<>();
        // ArrayList ko new bhi toh karna hai 
        for(int a:arr){
            this.arr.add(a);
        }
        for(int i=this.arr.size()-1;i>=0;i--){
            downheapify(i);
        }
    }
    // O(1)
    private void swap(int x,int y){
        int v1=this.arr.get(x);
        int v2=this.arr.get(y);
        this.arr.set(x,v2);
        this.arr.set(y,v1);
    }
    // O(1)
    private boolean compareTo(int x,int y){
        return this.arr.get(x)>this.arr.get(y);
    }
    // O(1)
    public int size(){
        return this.arr.size();
    }
    // O(logn)
    private void downheapify(int pi){
        int lci=2*pi+1;
        int rci=2*pi+2;
        int maxidx=pi;
        if(lci<arr.size() && compareTo(lci,maxidx)){
            maxidx=lci;
        }
        if(rci<arr.size() && compareTo(rci,maxidx)){
            maxidx=rci;
        }
        if(maxidx!=pi){
            swap(maxidx,pi);
            downheapify(maxidx);
        }
    }
    // O(logn)
    private void upheapify(int ci){
        int pi=(ci-1)/2;
        if(pi>=0 && compareTo(ci,pi)){
            swap(pi,ci);
            upheapify(pi);
        }
    }
    // O(logn)
    public void add(int val){
        this.arr.add(val);
        upheapify(this.arr.size()-1);
    }
    // O(logn)
    public int remove(){
        int re=this.arr.get(0);
        swap(0,this.arr.size()-1);
        this.arr.remove(this.arr.size()-1);
        downheapify(0);
        return re;
    }
    // O(1)
    public int peek(){
        return this.arr.get(0);
    }
}