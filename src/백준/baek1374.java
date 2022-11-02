package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baek1374 {

    /*
    강의를 추가하면서
    해당 강의가 이전에 추가된 애들과 겹치지 않는 애가 있는지 확인
    없으면 강의실 사이즈 +1
    있으면 해당 강의를 합침 (2,14) , (15,21) -> (2,21)
    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Lecture> lecturePriorityQueue = new PriorityQueue<>();
        ArrayList<Lecture> lectures = new ArrayList<>();

        for(int i=0;i<N;i++){
            String[] info = br.readLine().split(" ");
            int start = Integer.parseInt(info[1]);
            int end = Integer.parseInt(info[2]);

            lecturePriorityQueue.add(new Lecture(start,end));
        }

        Lecture lecture = lecturePriorityQueue.poll();
        lectures.add(lecture);

        while(!lecturePriorityQueue.isEmpty()){

            // 여기서 빼서 lectures 에 넣어야 함
            lecture = lecturePriorityQueue.poll();
            boolean flag = false;
            for (Lecture lec : lectures) {

                if (lec.end < lecture.start) {
                    lec.setEnd(lecture.end);
                    flag = true;
                    break;
                }
            }
            if(!flag){
                lectures.add(lecture);
            }

        }
        System.out.println(lectures.size());
    }

    public static class Lecture implements Comparable<Lecture>{
        private int start;
        private int end;

        public void setEnd(int end){
            this.end = end;
        }

        public Lecture(int start, int end){
            this.start=start;
            this.end=end;
        }

        @Override
        public int compareTo(Lecture o) {
            if(this.start == o.start){
                return this.end-o.end;
            }
            return this.start-o.start;
        }
    }
}
