package 외부코테;

import java.util.*;
public class num1privacy {
    public static void main(String[] args) {

    }
    public int[] solution(String today, String[] terms, String[] privacies) {

        HashMap<String,Integer> hashMap = new HashMap<>();
        for(String term : terms){
            String[] info = term.split(" ");
            hashMap.put(info[0],Integer.parseInt(info[1]));
        }
        int todayInt = calcDate(today);

        ArrayList<Integer> array = new ArrayList<>();

        for(int i=0;i<privacies.length;i++){
            String[] data = privacies[i].split(" ");
            int date = calcDate(data[0]);
            int add = hashMap.get(data[1])*28;
            if(todayInt>=date+add){
                array.add(i+1);
            }
        }

        int[] answer = new int[array.size()];
        for(int i=0;i<array.size();i++){
            answer[i]=array.get(i);
        }
        return answer;
    }

    public static int calcDate(String day){
        String[] data = day.split("[.]");
        int year = Integer.parseInt(data[0])-2000;
        int month = Integer.parseInt(data[1]);
        int date = Integer.parseInt(data[2]);
        return date+month*28+year*12*28;

    }
}
