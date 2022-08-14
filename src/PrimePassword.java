import java.util.LinkedList;
import java.util.Queue;

public class PrimePassword {
    public static void main(String[] args){
        int[] a = intToArray(1123);
        for(int data : a){
            System.out.print(data+" ");
        }
        System.out.println();

        System.out.println(arrayToInt(new int[]{3, 1, 6, 5}));

        System.out.println(primePassword(1009,1171));
        System.out.println(isPrime(10));
        System.out.println(isPrime(17));
    }
    public static int primePassword(int curPwd, int newPwd) {
        // TODO:
        // 소수이면 -1, 소수 아니면 -2
        boolean[] visited = new boolean[10000];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, curPwd});
        while(!queue.isEmpty()){
            int[] data = queue.poll();
            int count=data[0];
            int num=data[1];
            for(int i=0;i<4;i++){
                int[] digits = intToArray(num);
                for(int j=0;j<10;j++){
                    if(j!=digits[i]){
                        digits[i]=j;
                        int next = arrayToInt(digits);
                        if(next==newPwd) return count+1;
                        if(next>1000 && isPrime(next) && !visited[next]){
                            visited[next]=true;
                            queue.add(new int[]{count+1,next});
                        }
                    }

                }
            }
        }
        return -1;
    }
    public static boolean isPrime(int num){
        if(num==1) return false;
        if(num==2 || num==3) return true;
        for(int i=2;i<=Math.sqrt(num);i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }

    public static int[] intToArray(int num){
        int[] array = new int[4];
        for(int i=3;i>=0;i--){
            array[i]=num%10;
            num=(int)num/10;
        }
        return array;
    }

    public static int arrayToInt(int[] array){
        int num=0;
        for(int i=3;i>=0;i--){
            num+=Math.pow(10,3-i)*array[i];
        }
        return num;
    }
}
