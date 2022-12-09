package com.app.teamproject;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class Test {
    Timer timer = new Timer();
    private Toast toast;
    private int n;
    private int weight[][];
    private String saveRoute[];
    private String vertex[] = {"101", "102", "103", "104", "105", "106", "107", "108", "109", "110",
            "111", "112", "113", "114", "115", "116", "117", "118", "119", "120",
            "121", "122", "123",
            "201", "202", "203", "204", "205", "206", "207", "208", "209", "210",
            "211", "212", "213", "214", "215", "216", "217",
            "301", "302", "303", "304", "305", "306", "307", "308",
            "401", "402", "403", "404", "405", "406", "407", "408", "409", "410",
            "411", "412", "413", "414", "415", "416", "417",
            "501", "502", "503", "504", "505", "506", "507",
            "601", "602", "603", "604", "605", "606", "607", "608", "609", "610",
            "611", "612", "613", "614", "615", "616", "617", "618", "619", "620",
            "621", "622",
            "701", "702", "703", "704", "705", "706", "707",
            "801", "802", "803", "804", "805", "806",
            "901", "902", "903", "904"};


    public Test(int n) {
        super();
        this.n = n;
        weight = new int[n][n];
        saveRoute = new String[n];
    }


    public int stringToInt(String s) {
        int x = 0;
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i].equals(s)) {
                x = i;
            }
        }
        return x;
    }


    public void input(String v1, String v2, int w) {


        int x1 = stringToInt(v1);
        int x2 = stringToInt(v2);


        weight[x1][x2] = w;
        weight[x2][x1] = w;

    }


    public int getWeight(String v1, String v2) {
        int ind_v1 = getIndex(v1);
        int ind_v2 = getIndex(v2);

        return weight[ind_v1][ind_v2];
    }


    public int[] convertTime(int time) {
        int[] convertTime = new int[3];
        convertTime[0] = time / 3600;
        convertTime[1] = (time - (3600 * convertTime[0])) / 60;
        convertTime[2] = (time - (3600 * convertTime[0])) % 60;
        return convertTime;
    }


    public String[] getReverse(String str) {

        String[] arr = str.split(" ");

        List<String> list = Arrays.asList(arr);
        Collections.reverse(list);
        String[] reverse = list.toArray(arr);

        return reverse;
    }


    public int getCost_minTimeRoute(String[] route) {
        int cost = 0;
        for (int i = 0; i + 1 < route.length; i++) {
            cost += getWeight(route[i], route[i + 1]);
        }
        return cost;
    }


    public int getTime_minPriceRoute(String[] route) {
        int time = 0;
        for (int i = 0; i + 1 < route.length; i++) {
            time += getWeight(route[i], route[i + 1]);
        }
        return time;
    }


    public String middlePoint(String point1, String point2) {

        String[] route = getLowCostRoute(point1, point2);
        int station_number = route.length;
        String middle = route[(station_number - 1) / 2];


        int startToMiddle = getLowCost(point1, middle);
        int middleToEnd = getLowCost(middle, point2);
        int differ = Math.abs(startToMiddle - middleToEnd);

        if (differ >= 400) {
            System.out.println("middle in");

            String[] routeOfPoint1ToPoint2 = getLowCostRoute(point1, point2);
            int indexOfMiddle;


            for (indexOfMiddle = 0; indexOfMiddle < routeOfPoint1ToPoint2.length; indexOfMiddle++) {
                if (routeOfPoint1ToPoint2[indexOfMiddle].equals(middle)) {
                    break;
                }
            }

            if ((startToMiddle - middleToEnd) > 0) {
                middle = routeOfPoint1ToPoint2[indexOfMiddle - 1];
            } else if ((startToMiddle - middleToEnd) < 0) {
                middle = routeOfPoint1ToPoint2[indexOfMiddle + 1];
            }

        }
        return middle;
    }


    public void printArray(String[] reverse) {
        for (int j = 0; j < reverse.length; j++) {
            System.out.print(reverse[j] + " ");
        }
        System.out.println();
    }


    public void printList(ArrayList<String> list) {
        for (String station : list) {
            System.out.print(station + " ");
        }
        System.out.println();
    }

    public String[] getSaveRoute(String departure) {
        boolean[] visited = new boolean[n];
        int distance[] = new int[n];


        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }


        int x = stringToInt(departure);

        distance[x] = 0;

        visited[x] = true;


        saveRoute[x] = vertex[x];


        for (int i = 0; i < n; i++) {


            if (!visited[i] && weight[x][i] != 0) {
                distance[i] = weight[x][i];
                saveRoute[i] = vertex[x];
            }
        }

        for (int i = 0; i < n - 1; i++) {
            int minDistance = Integer.MAX_VALUE;
            int minVertex = 0;

            for (int j = 0; j < n; j++) {


                if (!visited[j] && distance[j] != Integer.MAX_VALUE) {

                    if (distance[j] < minDistance) {
                        minDistance = distance[j];
                        minVertex = j;
                    }
                }
            }

            visited[minVertex] = true;

            for (int j = 0; j < n; j++) {


                if (!visited[j] && weight[minVertex][j] != 0) {


                    if (distance[j] > distance[minVertex] + weight[minVertex][j]) {
                        distance[j] = distance[minVertex] + weight[minVertex][j];


                        saveRoute[j] = vertex[minVertex];
                    }
                }
            }
        }
        return saveRoute;
    }

    public int[] getDistance(String departure) {

        boolean[] visited = new boolean[n];  //각 꼭지점의 방문 여부
        int distance[] = new int[n]; //시작점에서부터 각 꼭지점까지의 거리

        //시작점 a에서부터 각 꼭지점까지의 모든 거리 초기화
//        for (int i = 0; i < n; i++) {
//            distance[i] = Integer.MAX_VALUE;
//        }
        Arrays.fill(distance, Integer.MAX_VALUE);

        //문자열로 입력된 시작점을 해당되는 숫자 인덱스 x로 바꾸고
        int x = stringToInt(departure);
        //시작점 x의 거리를 0으로 초기화하고,
        distance[x] = 0;
        //방문한 곳이므로 true값 저장
        visited[x] = true;

        // 시작점의 경로는 자기 자신을 저장
        saveRoute[x] = vertex[x];

        //시작점 x에서부터 꼭지점 i까지의 거리를 갱신한다.
        for (int i = 0; i < n; i++) {
            //방문하지 않았고 x에서 i까지의 가중치가 존재한다면, 거리 i에 x에서 i까지의 가중치 저장
            //즉 x에서 인접한 꼭지점까지의 거리를 갱신함.
            //(x와 인접하지 않은 꼭지점에는 Integer.MAX_VALUE가 계속 저장되어 있을 것이다.)
            if (!visited[i] && weight[x][i] != 0) {
                distance[i] = weight[x][i];
                saveRoute[i] = vertex[x]; //★시작점과 인접한 꼭지점의 경로에 시작 꼭지점을 저장
            }
        }

        for (int i = 0; i < n - 1; i++) {
            int minDistance = Integer.MAX_VALUE; //최단거리 minDistance에 일단 가장 큰 정수로 저장
            int minVertex = 0; //그 거리값이 있는 인덱스 minIndex에 -1을 저장해둔다.

            for (int j = 0; j < n; j++) {

                //방문하지 않았고 거리를 갱신한 꼭지점 중에서 가장 가까운 거리와 가장 가까운 꼭지점을 구한다.
                if (!visited[j] && (distance[j] != Integer.MAX_VALUE)) {

                    if (distance[j] < minDistance) {
                        minDistance = distance[j];
                        minVertex = j;
                    }
                }
            }

            visited[minVertex] = true; //위의 반복문을 통해 도출된 가장 가까운 꼭지점에 방문 표시

            for (int j = 0; j < n; j++) {

                //방문하지 않았고 minVertex와의 가중치가 존재하는(minVertex에서 연결된) 꼭지점이라면
                if (!visited[j] && weight[minVertex][j] != 0) {

                    //지금 그 꼭지점이 가지고 있는 거리값이 minVertex와 가중치를 더한 값보다 크다면 최단거리 갱신
                    if (distance[j] > distance[minVertex] + weight[minVertex][j]) {
                        distance[j] = distance[minVertex] + weight[minVertex][j];

                        //최단거리가 갱신된 꼭지점의 경로에 minVertex에 해당하는 꼭지점 저장
                        saveRoute[j] = vertex[minVertex];
                    }
                }
            }
        }
        return distance;
    }


    public String getList(ArrayList<String> list) {

        String t = "";
        for (String station : list) {
            t += station + " -> ";
        }
        return t;
    }



    public int getIndex(String arrival) {
        int temp;

        for (temp = 0; temp < n; temp++) {
            if (arrival.equals(vertex[temp])) break;
        }
        return temp;
    }


    public int getLowCost(String departure, String arrival) {

        int temp = getIndex(arrival);
        int[] distance = getDistance(departure);

//		toast = Toast.makeText(MainActivity.ApplicationContext(), "도착하면 알람이 울립니다.\n알람은 5초 뒤에 자동으로 꺼집니다.",Toast.LENGTH_LONG);
//		toast.show();

        //timer run
//		TimerTask startTimerTask = new TimerTask() {
//			@Override
//			public void run() {
//				Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//				Ringtone rt = RingtoneManager.getRingtone(MainActivity.ApplicationContext(),notification);
//				rt.play();
//			}
//		};
//		timer.schedule(startTimerTask,distance[temp]*1000,500);
//		TimerTask stopTimerTask = new TimerTask() {
//			@Override
//			public void run() {
//				startTimerTask.cancel();
//			}
//		};
//		timer.schedule(stopTimerTask,distance[temp]*1000+5000);

        return distance[temp];
    }


    public String[] getLowCostRoute(String departure, String arrival) {


        saveRoute = getSaveRoute(departure);


        int temp = getIndex(arrival);
        int check = temp;
        String route = "";

        while (true) {
            if (saveRoute[check] == vertex[check]) break;
            route += saveRoute[check] + " ";
            check = stringToInt(saveRoute[check]);
        }
        String[] reverse = getReverse(route);


        List<String> list = new ArrayList<>(Arrays.asList(reverse));
        list.add(arrival);
        reverse = new String[list.size()];
        list.toArray(reverse);

        return reverse;
    }
}
