import java.util.*;
public class programmersTwoQueueSum {
    public static void main(String[] args){
        int[] queue1= new int[]{1,2,1,2};
        int[] queue2= new int[]{1,10,1,2};
        int result = solution(queue1,queue2);
        System.out.println(result);
    }

        public static int solution(int[] queue1, int[] queue2) {
            Queue<Integer> que1 = new LinkedList<>();
            Queue<Integer> que2 = new LinkedList<>();

            long sum1 = 0, sum2 = 0;
            for(int i = 0; i < queue1.length; i++) {
                sum1 += queue1[i];
                que1.offer(queue1[i]);
            }

            for(int i = 0; i < queue2.length; i++) {
                sum2 += queue2[i];
                que2.offer(queue2[i]);
            }


            int count = 0;
            while(sum1 != sum2) {
                count++;

                if(sum1 > sum2) {
                    int value = que1.poll();
                    sum1 -= value;
                    sum2 += value;
                    que2.offer(value);
                } else {
                    int value = que2.poll();
                    sum1 += value;
                    sum2 -= value;
                    que1.offer(value);
                }

                if(count > (queue1.length + queue2.length) * 2) return -1;
            }


            return count;
        }
    }

