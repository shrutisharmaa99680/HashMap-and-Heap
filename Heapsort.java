public class Heapsort{
    public static void swap(int[]arr,int x,int y){
        int temp=arr[x];
        arr[x]=arr[y];
        arr[y]=temp;
    }
    public static boolean compareTo(int[]arr,int x,int y,boolean isincreasing){
        if(isincreasing){
            return arr[x]>arr[y];
        }
        else{
            return arr[y]>arr[x];
        }
    }
    public static void downheapify(int[]arr,int pi,int li,boolean isincreasing){
        int lci=2*pi+1;
        int rci=2*pi+2;
        int maxidx=pi;
        if(lci<=li && compareTo(arr,lci,maxidx,isincreasing)){
            maxidx=lci;
        }
        if(rci<=li && compareTo(arr,rci,maxidx,isincreasing)){
            maxidx=rci;
        }
        if(maxidx!=pi){
            swap(arr,maxidx,pi);
            downheapify(arr,pi,li,isincreasing);
        }
    }
    public static void main(String[] args){
        int[]arr={10,20,30,-2,-3,-4,5,6,7,8,9,22,11,13,14};
        boolean isincreasing=true;
        int li=arr.length-1;
        // convert it in heap 
        for(int i=li;i>=0;i--){
            downheapify(arr,0,li,isincreasing);
        }
        // sort the array 
        for(int i=li;i>=0;i--){
            swap(arr,0,li);
            li--;
            downheapify(arr,0,li,isincreasing);
        }
        for(int ele:arr){
            System.out.print(ele+" ");
        }
    }
}