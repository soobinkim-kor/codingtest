import java.util.Scanner;

public class baek2195 {
    public static void main(String[] args) {

        /*
        * P에서 1개 짜리부터 시작 - > 길이를 1씩 늘려가면서 패턴이 일치하는 문자열이 있는지 확인
        * 패턴이 가장 긴 문자열로 제작
        * xy0z <- S
        * zzz0yyy0xxx <- P
        * P로 만든 substring의 길이를 1씩 늘려 가면서 S 에서 맞는 게 있는지 확인하기
        * */
        Scanner scan = new Scanner(System.in);
        String S = scan.next();
        String P = scan.next();
        if(S.length()==1){
            System.out.println(P.length());
            return;
        }
        int count = 0;
        int index = 0;    // 작업을 시작할 index

        while(index<=P.length()){
            //System.out.println(index);
            String lastMatch = "";
            for(int i=1;i<P.length();i++){
                if(i+index>P.length()){
                    System.out.println(count+1);
                    return;
                }
                String subP = P.substring(index,index+i);
                if(matches(subP,S)==-1){
                    count++;
                    break;
                }else{
                    lastMatch = subP;
                }
            }
            //System.out.println(lastMatch);
            index+=lastMatch.length();

        }
        //System.out.println(count);
    }

    // A가 B랑 매치될때의 A의 인덱스
    public static int matches(String A, String B){
        for(int i=0;i<B.length();i++){
            if(B.startsWith(A, i)){
                return i;
            }
        }
        return -1;
    }
}
