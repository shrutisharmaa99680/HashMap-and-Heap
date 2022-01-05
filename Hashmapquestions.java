public class Hashmapquestions{
     // 128. Longest Consecutive Sequence
    public int longestConsecutive(int[] nums) {
        HashSet<Integer>map=new HashSet<>();
        for(int ele:nums){
            map.add(ele);
        }
        int len=0;
        for(int ele:nums){
            if(map.contains(ele)==false){
                continue;
                // part of some other sequence 
            }
            int prev=ele-1;
            int next=ele+1;
            map.remove(ele);
            while(map.contains(prev)){
                map.remove(prev);
                prev--;
            }
            while(map.contains(next)){
                map.remove(next);
                next++;
            }
            len=Math.max(len,next-prev-1);
        }
        return len;
    }
}