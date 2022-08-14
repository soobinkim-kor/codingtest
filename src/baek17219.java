import java.io.*;
import java.util.HashMap;

public class baek17219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] lines = br.readLine().split(" ");
        HashMap<String,String> hashMap = new HashMap<>();

        for(int i=0;i<Integer.parseInt(lines[0]);i++){
            String[] data = br.readLine().split(" ");
            hashMap.put(data[0],data[1]);
        }

        for(int i=0;i<Integer.parseInt(lines[1]);i++){
            bw.write(hashMap.get(br.readLine()));
            bw.write("\n");
        }

        bw.close();
    }
}
