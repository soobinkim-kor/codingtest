import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class baek1647 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] data = br.readLine().split(" ");
        int houses = Integer.parseInt(data[0]);
        int roads = Integer.parseInt(data[1]);
        ArrayList<Node>[] arrayList = new ArrayList[houses+1]; // 인덱스가 시작점이 됨


        for(int i=1;i<=houses;i++){
            arrayList[i]=new ArrayList<>();   // 미리 초기화 0부터 road+1
        }


        for(int i=0;i<roads;i++){
            String[] info = br.readLine().split(" ");
            int from = Integer.parseInt(info[0]);
            int to = Integer.parseInt(info[1]);
            int price = Integer.parseInt(info[2]);
            arrayList[from].add(new Node(to,price));
            arrayList[to].add(new Node(from,price));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[houses+1];
        int count = 0;
        int result = 0;
        int max_weight = 0;

        pq.add(new Node(1,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if(visited[current.from]){
                continue;
            }
            visited[current.from]=true;
            result+=current.price;
            max_weight=Math.max(current.price,max_weight);
            count++;

            if (count == houses){
                break;
            }

            for (Node v : arrayList[current.from]){
                if (!visited[v.from]) {
                    pq.add(new Node(v.from, v.price));
                }
            }
        }

        System.out.println(result-max_weight);


    }

    public static class Node implements Comparable<Node>{
        int from;
        int price;

        public Node(int from, int price){
            this.from=from;
            this.price=price;
        }

        @Override
        public int compareTo(Node node) {
            return this.price-node.price;
        }

    }
}
