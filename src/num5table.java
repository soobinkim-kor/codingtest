import java.util.*;
public class num5table {
    static String[][] table;
    static ArrayList<int[]> merge;

    public static void main(String[] args) {
        String[] commands = new String[]{
                "UPDATE 1 1 menu",
                "UPDATE 1 2 category",
                "UPDATE 2 1 bibimbap",
                "UPDATE 2 2 korean",
                "UPDATE 2 3 rice",
                "UPDATE 3 1 ramyeon",
                "UPDATE 3 2 korean",
                "UPDATE 3 3 noodle",
                "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"};
        String[] a = solution(commands);

        //String[] com = {"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"};
        //String[] b = solution(com);

    }


    public static String[] solution(String[] commands) {
        String[] answer = {};
        table = new String[50][50];
        for(int i=0;i<50;i++){
            for(int j=0;j<50;j++){
                table[i][j]="EMPTY";
            }
        }
        merge = new ArrayList<>();
        ArrayList<String> ans = new ArrayList<>();
        for(String command : commands){
            System.out.println(command);
            String[] com = command.split(" ");

            if(com[0].equals("UPDATE")){
                if(com.length==4){      // update by r c
                    updateByRC(Integer.parseInt(com[1])-1,Integer.parseInt(com[2])-1,com[3]);
                }else if(com.length==3){                  // update by value
                    updateByValue(com[1],com[2]);
                }
            }

            else if(com[0].equals("MERGE")){
                merge(Integer.parseInt(com[1])-1,Integer.parseInt(com[2])-1,Integer.parseInt(com[3])-1,Integer.parseInt(com[4])-1);
            }

            else if(com[0].equals("UNMERGE")){
                unmerge(Integer.parseInt(com[1])-1,Integer.parseInt(com[2])-1);
            }

            else if(com[0].equals("PRINT")){
                ans.add(print(Integer.parseInt(com[1])-1,Integer.parseInt(com[2])-1));
            }

            printMerge();
            printTable();
        }
        System.out.println(ans);
        return answer;
    }

    public static void printMerge(){
        for(int[] data : merge){
            for(int d : data){
                System.out.print(d+1+" ");
            }
            System.out.println();
        }
    }
    public static void updateByValue(String value, String to){
        for(int i=0;i<50;i++){
            for(int j=0;j<50;j++){
                if(table[i][j].equals(value)){
                    table[i][j]=to;
                }
            }
        }
    }
    public static void updateByRC(int r, int c, String value){
        if(merge.size()==0){                        // 존재하는 병합이 없으면, 해당 좌표만 바꾸고 끝
            table[r][c]=value;
            return;
        }

        Queue<int[]> findLink = findLink(r,c);    // 병합에 대한 변경을 실행 할 좌표가 담긴 큐
        if(findLink.isEmpty()){
            table[r][c]=value;
        }else{
            for(int[] data : findLink){
                table[data[0]][data[1]] = value;
            }
        }
    }

    public static void merge(int r1, int c1, int r2, int c2){
        String to = table[r1][c1]; // 해당 값으로 모든 merge 변경
        if(to.equals("EMPTY")){
            to=table[r2][c2];
        }

        merge.add(new int[]{r1,c1,r2,c2});
        Queue<int[]> findLink = findLink(r1,c1);

        for(int[] data : findLink){
            table[data[0]][data[1]] = to;
        }
    }

    public static void unmerge(int r, int c){
        String to = table[r][c];
        Queue<int[]> findLink = findLink(r,c);      // 연관된 모든 링크 가져옴 {2,3},{3,4} 이거 다 지워야함
        ArrayList<int[]> newMerge = new ArrayList<>();      // merge = {1,2,2,3},{1,2,3,4},{3,3,1,2}

        for(int[] m : merge){
            int flag=0;
            for(int[] fl : findLink){
                if(fl[0]==m[0] && fl[1]==m[1]){
                    flag=1;
                }
                if(fl[0]==m[2] && fl[1]==m[3]){
                    flag=1;
                }
            }
            if(flag!=1){
                newMerge.add(m);
            }

        }

        for(int[] data : findLink){
            table[data[0]][data[1]]="EMPTY";
        }
        merge=newMerge;
        table[r][c]=to;
    }

    public static String print(int r, int c){
        return table[r][c];
    }

    public static void printTable(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                System.out.print(table[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static Queue<int[]> findLink(int r, int c){      // 병합된 셀 리스트 찾는 메소드
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> findLink = new LinkedList<>();
        boolean[] visited = new boolean[merge.size()];
        findLink.add(new int[]{r,c});
        while(!findLink.isEmpty()){
            int[] info = findLink.poll();
            int row = info[0];
            int col = info[1];
            for(int i=0;i<merge.size();i++){
                int[] data = merge.get(i);
                if(data[0]==row && data[1]==col && !visited[i]){
                    visited[i]=true;
                    findLink.add(new int[]{data[2],data[3]});
                    queue.add(new int[]{data[2],data[3]});
                }
                if(data[2]==row && data[3]==col && !visited[i]){
                    visited[i]=true;
                    findLink.add(new int[]{data[0],data[1]});
                    queue.add(new int[]{data[0],data[1]});
                }
            }
        }
        queue.add(new int[]{r,c});
        return queue;
    }
}