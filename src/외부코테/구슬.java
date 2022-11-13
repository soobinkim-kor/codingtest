package 외부코테;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 구슬 {
    static ArrayList<int[]> marble= new ArrayList<>();
    public static void main(String[] args) {
        int[] test1 = {1,2,3,4,4};
        int[] test2 = {5,5,1,4};
        int[] test3 = {3,9,7,5};
        int[] test4 = {7,3,1};

        //split(test3);

//        PriorityQueue<Marble> priorityQueue = new PriorityQueue<>();
//        priorityQueue.add(new Marble(new int[]{1,2,4,3},4));
//        priorityQueue.add(new Marble(new int[]{4,3,4},2));
//        priorityQueue.add(new Marble(new int[]{2,3,4,1,4},4));
//        priorityQueue.add(new Marble(new int[]{1,4,4,2,3},4));
//        Marble m = priorityQueue.poll();
//        System.out.println(Arrays.toString(m.marbles)+" "+ m.index);
        System.out.println(Arrays.toString(solution(test1)));
        marble.clear();
        System.out.println(Arrays.toString(solution(test2)));marble.clear();
        System.out.println(Arrays.toString(solution(test3)));marble.clear();
        System.out.println(Arrays.toString(solution(test4)));marble.clear();

    }
    public static int[] solution(int[] marbles){
        if(marbles.length==1){
            return marbles;
        }
        PriorityQueue<Marble> mpq = new PriorityQueue<>();
        for(int i=1;i<=marbles.length;i++){
            perm(marbles, new int[i], new boolean[marbles.length],0,i);
        }
        for(int[] data : marble){

            Marble m = split(data);
            if(m!=null){
                mpq.add(m);
            }
        }
        if(mpq.isEmpty()){
            int max=0;
            for(int marble : marbles){
                max=Math.max(max,marble);
            }
            return new int[]{max};
        }
        Marble marble1 = mpq.poll();

        return marble1.marbles;
    }

    static void perm(int[] marbles, int[] perm, boolean[] visited, int depth, int r) {
        if (depth == r) {
            int[] deepCopy = perm.clone();
            marble.add(deepCopy);
            return;
        }

        for (int i=0; i<perm.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                perm[depth] = marbles[i];
                perm(marbles, perm, visited, depth + 1, r);
                visited[i] = false;;
            }
        }
    }

    // 배열 출력
    static void print(int[] arr) {
        for (int j : arr) System.out.print(j + " ");
        System.out.println();
    }



    public static int comp(Marble a, Marble b){
        int[] aMarble = a.marbles;

        int[] bMarble = b.marbles;

        StringBuilder sbA = new StringBuilder();
        for(int aM :aMarble){
            sbA.append(aM);
        }

        StringBuilder sbB = new StringBuilder();
        for(int bM :bMarble){
            sbB.append(bM);
        }

        return sbA.toString().compareTo(sbB.toString());
    }


    public static Marble split(int[] marbles){

        for(int i=1;i<marbles.length;i++){
            //System.out.println("_-----");
            int[] left = Arrays.copyOfRange(marbles,0,i);
            int[] right = Arrays.copyOfRange(marbles,i,marbles.length); // 중심이 구슬 사이
            int[] right2 = Arrays.copyOfRange(marbles,i+1,marbles.length); // 중심이 구슬 위
            //print(left);
            //print(right);
            //print(right2);
            if(isSameSize(left,right)){
                return new Marble(marbles,i*2-1);
            }
            if(isSameSize(left,right2)){
                return new Marble(marbles,i*2);
            }
        }
        //System.out.println("null");
        return null;
    }

    public static boolean isSameSize(int[] left, int[] right){
        int leftSum=0;
        int rightSum=0;
        for(int d : left){
            leftSum+=d;
        }
        for(int d : right){
            rightSum+=d;
        }
        return leftSum==rightSum;
    }

    public static class Marble implements Comparable<Marble> {
        public int[] marbles;
        // 중심의 위치
        public int index;

        public Marble(int[] marbles, int index){
            this.marbles=marbles;
            this.index=index;
        }


        @Override
        public int compareTo(Marble o) {

            int thisSizeDiff = diff(this);
            int oSizeDiff = diff(o);

            // 좌우 구슬 갯수 차이
            if(thisSizeDiff!=oSizeDiff){
                return thisSizeDiff-oSizeDiff;
            }
            int thisSize = this.marbles.length;
            int oSize = o.marbles.length;
            if(thisSize!=oSize){
                return oSize-thisSize;
            }

            int thisSum = 0;
            int oSum = 0;
            for(int m1 : this.marbles){
                thisSum+=1;
            }
            for(int m2 : o.marbles){
                oSum+=m2;
            }
            if(thisSum!=oSum){
                return thisSum-oSum;
            }

            else{
                return comp(this,o);
            }
        }
        public int diff(Marble marble){
            int left;
            int right;
            if(marble.index%2==0){
                left = marble.index/2;
                right = marble.marbles.length-left-1;
            }
            else{
                left= Math.round(marble.index/2);
                right = marble.marbles.length-1;
            }
            return Math.abs(left-right);
        }
    }

}
