
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
* 단체 손님 받는 호텔
* 단체 손님은 인원수 만큼의 인접한 객실을 받는다.
* 최대한 많은 손님이 방을 배정받을 수 있게 해서, 객실 이용률을 목표치 이상으로 해야 함
* 목표 : 호텔의 객실 이용률이 목표치 이상이 되도록 예약을 관리하거나 객실을 배정하면서, 객실 수가 많은 예약은 최대한 거절하지 않아야 합니다.
* 호텔 객실 번호 : 층+호호호
* 체크아웃 시 당일에 바로 체크인 가능
*
예약 제약 사항
각 예약 요청은 고유한 정수 id를 갖고 있습니다.
예약 요청은 다음 정보를 포함하고 있습니다.
요청 id
객실 수
체크인 날짜
체크아웃 날짜
요청 id는 고유한 6자리 정수입니다.
체크인 날짜는 예약 요청이 들어온 날짜로부터 최소 1일 뒤, 최대 21일 뒤입니다.
*
*
예약 관리
각 예약 요청의 답변 기한까지 예약을 승낙할지 거절할지 정해 답변해야 합니다.
예약 요청의 답변 기한 = min(예약 요청이 들어온 날짜 + 14 , 체크인 날짜 - 1)
기한까지 답변을 하지 않은 예약은 거절한 것으로 처리됩니다.
*
*
객실 배정
승낙한 예약 요청의 체크인 날짜가 되면 손님이 호텔에 도착합니다.
도착한 손님이 예약한 객실 수만큼의 비어있는 객실을 배정해야 합니다. 이때 객실들은 같은 층에서 인접한 연속된 객실이어야 합니다.
시나리오
*
*
점수
점수는 시나리오별로 다음과 같이 계산됩니다.

구분	공식
정확성 점수	U * 80
효율성 점수	Σ V / T1 * 10
페널티 점수	Σ P / T2 * 5000 + Σ A / T2 * 900 + R / T3 * 40
총점	max(0, 500 + 정확성 점수 + 효율성 점수 - 페널티 점수)
※ 객실 이용률 = Σ (객실 이용 날짜 수) / (호텔의 객실 수 * 시뮬레이션 길이) * 100

※ U = (min(객실 이용률, 목표치) / 목표치)^2

※ V = 예약 요청의 (답변 기한 - 답변 날짜)

※ T1 = 모든 예약 요청의 (답변 기한 - 요청 날짜)

※ P = 승낙했으나 객실 배정에 실패한 예약의 객실 수

※ A = 거절한 예약 요청의 객실 수

※ T2 = 모든 예약 요청의 객실 수

※ R = 예약 요청을 거절한 횟수

※ T3 = 모든 예약 요청의 수
* */


public class ApiTest {
    static String authToken = "db65a85bcec418826bd31308f03dbdb2";

    // Start API
    /*
    *   Request
        POST /start
        X-Auth-Token: {X-Auth-Token}
        Content-Type: application/json
    *
    *
    *   {
          "auth_key": "1fd74321-d314-4885-b5ae-3e72126e75cc",
          "problem": 1,
          "day": 1
        }
    * */
    public static JSONObject postStart(String baseUrl, String path, int problem) throws IOException, ParseException {
        URL url = new URL(baseUrl+path);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-type", "application/json");
        httpURLConnection.setRequestProperty("X-Auth-Token",authToken);
        httpURLConnection.setDoOutput(true);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("problem",problem);
        OutputStreamWriter osw = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
        osw.write(jsonObject.toString());
        osw.flush();
        osw.close();
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
            while(br.ready()) {
                sb.append(br.readLine());
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return (JSONObject) new JSONParser().parse(sb.toString());

    }

    // 해당 날짜의 예약 받아오기
    public static List<Reservation> getNewRequest(String baseUrl, String path, String auth_key, int date)throws IOException, ParseException{
        URL url = new URL(baseUrl+path);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Content-type", "application/json");
        httpURLConnection.setRequestProperty("Authorization",auth_key);
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
            while(br.ready()) {
                sb.append(br.readLine());
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(sb.toString());
        JSONArray jsonArray = (JSONArray) jsonObject.get("reservations_info");
        int size = jsonArray.size();
        List<Reservation> reservations = new ArrayList<>();
        //int[][] data = new int[size][4];
        for(int i=0;i<size;i++){
            JSONObject reserve = (JSONObject) jsonArray.get(i);
            int id = Long.valueOf((long)reserve.get("id")).intValue();
            int amount = Long.valueOf((long) reserve.get("amount")).intValue();
            int check_in_date = Long.valueOf((long) reserve.get("check_in_date")).intValue();
            int check_out_date = Long.valueOf((long) reserve.get("check_out_date")).intValue();
            Reservation reservation = new Reservation(id,amount,check_in_date,check_out_date,date);
            reservations.add(reservation);
        }
        return reservations;
    }

    public static void putReply(String baseUrl, String auth_key, Queue<Reservation> accepted, List<Reservation> refused )throws IOException, ParseException{
        URL url = new URL(baseUrl+"/reply");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setRequestProperty("Content-type", "application/json");
        httpURLConnection.setRequestProperty("Authorization",auth_key);
        httpURLConnection.setDoOutput(true);
        JSONArray map = new JSONArray();


        for(Reservation reservation : accepted){
            JSONObject json = new JSONObject();
            json.put("id",reservation.getId());
            json.put("reply","accepted");
            map.add(json);
        }


        for(Reservation reservation : refused){
            JSONObject json = new JSONObject();
            json.put("id",reservation.getId());
            json.put("reply","refused");
            map.add(json);
        }
        JSONObject finalObject = new JSONObject();
        finalObject.put("replies",map);

        OutputStreamWriter osw = new OutputStreamWriter(httpURLConnection.getOutputStream(), StandardCharsets.UTF_8);
        osw.write(finalObject.toString());
        osw.flush();
        osw.close();
        System.out.println(finalObject);
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
            while(br.ready()) {
                sb.append(br.readLine());
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println(sb);
        //JSONObject jsonObject = (JSONObject) new JSONParser().parse(sb.toString());
        //JSONArray jsonArray = (JSONArray) jsonObject.get("reservations_info");
    }

    public static JSONObject getScore(String baseUrl, String auth_key)throws IOException, ParseException{
        URL url = new URL(baseUrl+"/score");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Content-type", "application/json");
        httpURLConnection.setRequestProperty("Authorization",auth_key);
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
            while(br.ready()) {
                sb.append(br.readLine());
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(sb.toString());
        System.out.println(jsonObject);
        return jsonObject;
    }

    public static void simulate(String baseUrl, String auth_key, List<int[]> completed)throws IOException{
        URL url = new URL(baseUrl+"/simulate");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setRequestProperty("Content-type", "application/json");
        httpURLConnection.setRequestProperty("Authorization",auth_key);
        httpURLConnection.setDoOutput(true);
        JSONArray jsonArray = new JSONArray();
        for(int[] data : completed){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(data[0],data[1]);
            jsonArray.add(jsonObject);
        }
        JSONObject finalJson = new JSONObject();
        finalJson.put("room_assign",jsonArray);
        OutputStreamWriter osw = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
        osw.write(finalJson.toString());
        osw.flush();
        osw.close();
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
            while(br.ready()) {
                sb.append(br.readLine());
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("***************************DAY: "+sb);
    }

    public static void main(String[] args) throws IOException, ParseException {
        String baseUrl = "https://68ecj67379.execute-api.ap-northeast-2.amazonaws.com/api";
        String path = "/start";
        JSONObject jsonObject = postStart(baseUrl, path, 1);
        String auth_key = (String) jsonObject.get("auth_key");
        int[][] building;

        building = new int[3][20];

        // 오늘 받은 예약
        PriorityQueue<Reservation> tbd = new PriorityQueue<>();     // 예약 받을지 말지 고민

        // 보류 중인 예약
        List<Reservation> waiting = new ArrayList<>();

        // 수락 된 예약 ( d-day 가 되면 배정해야함 )
        Queue<Reservation> accepted = new LinkedList<>();


        for (int day = 1; day <= 200; day++) {      // 1일 부터 시작

            // 오늘 배정 해야 되는 예약
            List<Reservation> placed = new ArrayList<>();

            // 거절 된 예약
            List<Reservation> refused = new ArrayList<>();

            // 체크아웃
            for(int i=0;i<building.length;i++){
                for(int j=0;j<building[0].length;j++){
                    if(building[i][j]<=day){
                        building[i][j]=0;
                    }
                }
            }

            // 오늘 배정해야 되는거 처리
            while(!accepted.isEmpty()){
                Reservation reservation = accepted.peek();
                if(reservation.getCheckIn()==day){
                    Reservation reservation1 = accepted.poll();
                    placed.add(reservation1);
                }else{
                    break;
                }
            }

            // 오늘 들어온 예약
            List<Reservation> response = getNewRequest(baseUrl, "/new_requests", auth_key,day);
            for(Reservation res : response){    // 이미 배정된 날짜에 절대 예약 불가일 경우
                if(canPlace(building,res)){
                    tbd.addAll(response);
                }else{
                    refused.add(res);
                }
            }


            // 예약 받을지 말지 결정
            // 최대한 방 많이, 최대한 길게 쓸 수 있는 예약 부터 받음
            while(!tbd.isEmpty()){
                Reservation newReservation = tbd.poll();
                if(score(newReservation)>1){
                    accepted.add(newReservation);

                }else{
                    refused.add(newReservation);
                }
            }


            putReply(baseUrl,auth_key,accepted,refused);

            // 오늘 배정 완료 해야되는 애들 중에서 중요도 큰 순으로 배정
            // pq에 넣고?
            PriorityQueue<Reservation> placing = new PriorityQueue<>(placed);

            //HashMap<Integer,Integer> completed = new LinkedHashMap();
            List<int[]> completed = new ArrayList<>();
            while(!placing.isEmpty()){
                Reservation reservation = placing.poll();
                int[] p = isPlaceable(building,reservation.getRooms());
                if(p[0]!=-1){ // 배정 가능
                    completed.add(new int[]{reservation.getId(),createRoomNo(p[0],p[1])});
                    //completed.put(reservation.getId(),Integer.parseInt(createRoomNo(p[0],p[1])));
                    for(int i=0;i<reservation.getRooms();i++){
                        building[p[0]][i]=reservation.getCheckOut();
                    }
                }
            }


            simulate(baseUrl,auth_key,completed);
        }
    }

    public static double score(Reservation reservation){
        int date = reservation.checkOut-reservation.checkIn+1;
        return (double)date/reservation.rooms;
    }

    public static boolean canPlace(int[][] building, Reservation reservation){
        int checkIn = reservation.getCheckIn();
        int rooms = reservation.getRooms();
        for(int i=0;i<building.length;i++){
            int count=0;
            for(int j=0;j<building[0].length;j++){
                if(building[i][j]<=checkIn){
                    count++;
                    if(count==rooms){
                        return true;
                    }
                }else{
                    count=0;
                }
            }
        }
        return false;
    }
    public static int[] isPlaceable(int[][] building, int rooms){
        for(int i=0;i<building.length;i++){
            int count=0;
            for(int j=0;j<building[0].length;j++){
                if(building[i][j]==0){
                    count++;
                    if(count==rooms-1){
                        return new int[]{i,j};
                    }
                }else{
                    count=0;
                }
            }
        }
        return new int[]{-1,-1};
    }

    // 객실 제조 메소드
    public static int createRoomNo(int floor, int num){
        String n = String.valueOf(num);
        StringBuilder sb = new StringBuilder();
        sb.append(floor);
        if(n.length()==1){
            sb.append("00");
        }else if(n.length()==2){
            sb.append("0");
        }
        sb.append(n);
        return Integer.parseInt(sb.toString());
    }

    public static class Reservation implements Comparable<Reservation> {
        public int id;
        public int rooms;
        public int checkIn;
        public int checkOut;

        public int day;
        public Reservation(int id, int rooms, int checkIn, int checkOut, int day){
            this.id = id;
            this.rooms = rooms;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.day = day;
        }

        public int getId() {
            return id;
        }

        public int getRooms() {
            return rooms;
        }

        public int getCheckIn() {
            return checkIn;
        }

        public int getCheckOut() {
            return checkOut;
        }


        @Override
        public int compareTo(Reservation o) {
            if(score(this)<score(o)){
                return -1;
            }else if(score(this)>score(o)){
                return 1;
            }else{
                return o.rooms-this.rooms;
            }
        }

        @Override
        public String toString() {
            return "Reservation{" +
                    "id=" + id +
                    ", rooms=" + rooms +
                    ", checkIn=" + checkIn +
                    ", checkOut=" + checkOut +
                    '}';
        }
    }
}
