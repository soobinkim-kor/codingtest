import Leetcode.BinarySearch704;
import Leetcode.FirstBadVersion278;
import Leetcode.SearchInsertPosition35;
import Leetcode.ValidPerfectSquare367;

import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static void main(String[] args){
        ValidPerfectSquare367 solution = new ValidPerfectSquare367();
        System.out.println(solution.isPerfectSquare(168));
    }

    public static int stacksolution(String dartResult){
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<dartResult.length();i++){
            char ch = dartResult.charAt(i);
            int sum=ch-'0';
            if(Character.isDigit(ch)){
                if(dartResult.charAt(i+1)=='0'){
                    sum=10;
                    i++;
                }
                stack.push(sum);
            }else{
                int prev = stack.pop();

                if (ch == 'D') {
                    prev *= prev;
                } else if (ch == 'T') {
                    prev = prev * prev * prev;
                } else if (ch == '*') {
                    if (!stack.isEmpty()) {
                        int val = stack.pop() * 2;
                        stack.push(val);
                    }
                    prev *= 2;
                } else if (ch == '#') {
                    prev *= (-1);
                }
                // System.out.println(prev);
                stack.push(prev);
            }
        }
        System.out.println(stack);
        return 0;
    }
    public static int solution(String dartResult) {
    /*
        S = 1제곱
        D = 2제곱
        T = 3제곱
    */
        ArrayList<String> a = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        boolean flag = false;
        for(int i=0;i<dartResult.length();i++){
            char ch = dartResult.charAt(i);
            if(48<=ch && ch<=57) {
                if(flag){
                    a.add(sb.toString());
                    sb.setLength(0);

                    flag=false;
                }
                sb.append(ch);

            }else{
                flag=true;
                sb.append(ch);
            }

        }

        a.add(sb.toString());

        int answer=0;

        int[] mult = new int[]{1,1,1};
        int[] scores = new int[3];
        for(int i=0;i<a.size();i++){
            String data = a.get(i);
            int score = Integer.parseInt(data.replaceAll("[^\\d]",""));
            if(data.contains("S")){
                score=(int)Math.pow(score,1);
            }else if(data.contains("D")){
                score=(int)Math.pow(score,2);
            }else if(data.contains("T")){
                score=(int)Math.pow(score,3);
            }
            if(data.contains("#")){
                score=score*(-1);
            }
            if(data.contains("*")){
                mult[i]=mult[i]*2;
                if(i!=0){
                    mult[i-1]=mult[i-1]*2;
                }
            }
            scores[i]=score;
        }

        for(int i=0;i<3;i++){
            answer+=scores[i]*mult[i];
        }
        return answer;
    }
}
