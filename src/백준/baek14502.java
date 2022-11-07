package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class baek14502 {
    static int[] dx = {-1,  0,  1,  0};
    static int[] dy = { 0,  1,  0,  -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sizes = br.readLine().split(" ");

        // 입력값
        int m = Integer.parseInt(sizes[0]);
        int n = Integer.parseInt(sizes[1]);
        int[][] lab = new int[m][n];

        // 바이러스의 위치를 담을 리스트
        ArrayList<int[]> viruses = new ArrayList<>();
        ArrayList<int[]> emptySpace = new ArrayList<>();
        // 빈 공간의 갯수. 3개의 벽을 세울 것이므로, -3 부터 시작한다.
        int secure = -3;

        // 연구소의 좌표의 값을 업데이트 하면서, 바이러스의 위치와 빈 공간의 갯수를 찾는다.
        for(int i=0;i<m;i++){
            String[] virus = br.readLine().split(" ");
            for(int j=0;j<n;j++){
                int space = Integer.parseInt(virus[j]);
                lab[i][j]=space;
                // 빈 공간 갯수 추가
                if(space==0){
                    secure++;
                    emptySpace.add(new int[]{i,j});
                }
                // 바이러스 위치 저장
                if(space==2){
                    viruses.add(new int[]{i,j});
                }
            }
        }

        // 벽을 세울 위치를 갖고있는 배열을 담을 리스트
        ArrayList<ArrayList<int[]>> wall = new ArrayList<>();

        for(int i=0; i<emptySpace.size(); i++){
            for(int j=i+1; j<emptySpace.size() ; j++){
                for(int k=j+1 ; k<emptySpace.size() ; k++){
                    ArrayList<int[]> w = new ArrayList<>();
                    w.add(emptySpace.get(i));
                    w.add(emptySpace.get(j));
                    w.add(emptySpace.get(k));
                    wall.add(w);
                }
            }
        }


//         3개의 벽을 설치해야 하므로, 3개의 좌표가 담긴 쌍을 만들어서 wall 리스트에 추가한다.
//         (0,1,2), (0,1,3), (0,1,4) 순으로 만들어진다. 각 숫자는 n으로 나눈 몫이 행의 좌표, 나머지가 열의 좌표가 된다.
//        for(int i=0; i<n*m; i++){
//            for(int j=i+1; j<n*m ; j++){
//                for(int k=j+1 ; k<n*m ; k++){
//                    if( lab[i/n][i%n]==0 &&  lab[j/n][j%n] == 0 &&  lab[k/n][k%n] == 0){
//                        ArrayList<int[]> w = new ArrayList<>();
//                        w.add(new int[]{i/n,i%n});
//                        w.add(new int[]{j/n,j%n});
//                        w.add(new int[]{k/n,k%n});
//                        wall.add(w);
//                    }
//                }
//            }
//        }

        int answer = 0;

        // 세운 벽의 좌표를 기준으로 반복 실행
        for(ArrayList<int[]> walls : wall){
            // 벽 위치를 새로 세울 때 마다 방문 초기화

            // 연구소의 바이러스가 퍼지기 전, 벽을 세우기 전 좌표를 깊은 복사를 통해 만든다.
            int[][] labCopy = copy(lab);

            // 벽을 세울 위치의 값을 1로 바꾼다.
            for(int[] data : walls){
                labCopy[data[0]][data[1]]=1;
            }

            // bfs 를 실행한다. result 는 바이러스가 다 퍼지고 난 후의 빈 공간 갯수이다.
            int result = bfs(viruses,m,n,secure,labCopy);

            // 최댓값을 갱신한다.
            answer = Math.max(answer, result);

        }
        System.out.println(answer);

    }

    // 연구소 좌표의 깊은 복사
    public static int[][] copy(int[][] source){
        int[][] copy = new int[source.length][source[0].length];
        for(int i=0;i<source.length;i++){
            System.arraycopy(source[i], 0, copy[i], 0, source[0].length);
        }
        return copy;
    }

    // bfs 를 이용한 바이러스 퍼뜨리기
    public static int bfs(ArrayList<int[]> viruses, int m, int n, int secure, int[][] lab){

        Queue<int[]> queue = new LinkedList<>(viruses);
        while(!queue.isEmpty()){

            // 현재 큐에 담겨있는 위치를 뽑아낸다.
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // 4가지 방향으로 찾는다.
            for(int i=0;i<4;i++){
                int newX = x+dx[i];
                int newY = y+dy[i];

                // 새로운 좌표가 연구소를 벗어나지 않는지 확인
                if(newX>=0 && newY>=0 && newX<m && newY<n){
                    // 방문하지 않은 위치인지, 빈 공간인지 확인
                    if(lab[newX][newY]==0){
                        // 해당 위치를 바이러스가 있는 것으로 표기
                        lab[newX][newY]=2;
                        // 새로운 좌표를 큐에 추가
                        queue.add(new int[]{newX,newY});
                        // 빈 공간이 줄어들었음
                        secure--;
                    }
                }
            }
        }
        return secure;
    }
}
