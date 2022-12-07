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
	private int n; //��� ��
	private int weight[][]; //2���� �迭 weight�� �� ����� ����ġ�� ����
	private String saveRoute[];	
	private String vertex[] = {"101","102","103","104","105","106","107","108","109","110",
            "111","112","113","114","115","116","117","118","119","120",
            "121","122","123",
            "201","202","203","204","205","206","207","208","209","210",
            "211","212","213","214","215","216","217",
            "301","302","303","304","305","306","307","308",
            "401","402","403","404","405","406","407","408","409","410",
            "411","412","413","414","415","416","417",
            "501","502","503","504","505","506","507",
            "601","602","603","604","605","606","607","608","609","610",
            "611","612","613","614","615","616","617","618","619","620",
            "621","622",
            "701","702","703","704","705","706","707",
            "801","802","803","804","805","806",
            "901","902","903","904"};
	
	// ������
	public Test(int n) {
		super();
		this.n = n; //�����ڸ� ���� ��� �� ����
		weight = new int[n][n]; //����ġ�� ������ �迭 weight�� ũ�� ����
		saveRoute = new String[n]; //�̵� ���	}
	}

	//���ڿ��� int������ �ٲ��ش�.
	public int stringToInt(String s) {
		int x = 0;
		for(int i = 0; i < vertex.length; i++) {
			if(vertex[i].equals(s)) {
				x = i;
			}
		}
		return x;
	}
	
	// ����ġ �Է�
	public void input(String v1, String v2, int w) {
		
		// ���ڿ��� �Էµ� ������ v1�� v2�� �ش�Ǵ� ���� �ε��� x1�� x2�� ����
		int x1 = stringToInt(v1);
		int x2 = stringToInt(v2);
		
		// x1���� x2������ ����ġ�� ����
		// �� ��, x1���� x2������ ����ġ�� x2���� x1������ ����ġ�� ����
		// ����ġ�� ��� �Էµ��� �ʾҴٸ� �⺻�� 0
		weight[x1][x2] = w;
		weight[x2][x1] = w;
				
	}
	
	// ����ġ ��ȯ
	public int getWeight(String v1, String v2) {
		int ind_v1 = getIndex(v1);
		int ind_v2 = getIndex(v2);
		
		return weight[ind_v1][ind_v2];
	}
	
	// �ð� ��ȯ��
	public int[] convertTime(int time) {
		int[] convertTime = new int[3];
		convertTime[0] = time / 3600; // ��
		convertTime[1] = (time - (3600 * convertTime[0])) / 60; // ��
		convertTime[2] = (time - (3600 * convertTime[0])) % 60; // ��
		return convertTime;
	}
	
	// ���ڿ� ������
	public String[] getReverse(String str) {
		// ������ �������� ���ڿ� �ڸ���
		String[] arr = str.split(" ");
		
		List<String> list = Arrays.asList(arr); // ���� �迭�� List�� ��ȯ
		Collections.reverse(list); // ����Ʈ�� �Ųٷ� ��ȯ
		String[] reverse = list.toArray(arr); // ����Ʈ�� �迭�� ��ȯ
		
		return reverse;
	}
	
	// �ּҽð� ����� ��� ��ȯ
	public int getCost_minTimeRoute(String[] route) {
		int cost = 0;
		for(int i = 0; i+1 < route.length; i++) {
			cost += getWeight(route[i], route[i+1]);
		}
		return cost;
	}
	
	// �ּҺ�� ����� �ð� ��ȯ
	public int getTime_minPriceRoute(String[] route) {
		int time = 0;
		for(int i = 0; i+1 < route.length; i++) {
			time += getWeight(route[i], route[i+1]);
		}
		return time;
	}
	
	// point1�� point2 ������ �߰��� ã��
	public String middlePoint(String point1, String point2) {
		// �� ������ �ּҽð� ��� �޾ƿ���
		String[] route = getLowCostRoute(point1, point2);
		int station_number = route.length; // ��Ʈ�� �ִ� ���� ����(�湮�� ���� ����)
		String middle = route[(station_number-1)/2];
		
		// �߰������� �����Ѱ�?
		int startToMiddle = getLowCost(point1, middle);
		int middleToEnd = getLowCost(middle, point2);
		int differ = Math.abs(startToMiddle - middleToEnd); // ������~�߰������� �Һ�ð��� �߰�~������������ �Һ�ð��� ���� ����
		
		if(differ >= 400) { //400�� �̻� ���̰� ���ٸ� (�߰������� �������� �ʴٸ�)
			// �߰� ������ �ٽ� ���Ѵ�.
			System.out.println("middle in");
			//1. route�� �����ϰ� middle�� �ε����� ã�´�.
			String[] routeOfPoint1ToPoint2 = getLowCostRoute(point1, point2);
			int indexOfMiddle; //middle�� �ε���
			
			//point1���� point2������ ��Ʈ���� �߰������� �ε��� ã��
			for(indexOfMiddle = 0; indexOfMiddle < routeOfPoint1ToPoint2.length; indexOfMiddle++) {
				if(routeOfPoint1ToPoint2[indexOfMiddle].equals(middle)) {
					break;
				}
			}
			//2. �߰������� �����Ѵ�.
			if((startToMiddle - middleToEnd) > 0) { //�߰������� point2�� �� �����ٸ�
				middle = routeOfPoint1ToPoint2[indexOfMiddle - 1];
			} else if((startToMiddle - middleToEnd) < 0) { // �߰������� point1�� �� �����ٸ� 
				middle = routeOfPoint1ToPoint2[indexOfMiddle + 1];
			}

		}
		return middle;
	}
	
	// �迭 ���
	public void printArray(String[] reverse) {
		for(int j = 0; j < reverse.length; j++) {
			System.out.print(reverse[j] + " ");
		}
		System.out.println();
	}
	
	// ����Ʈ ���
	public void printList(ArrayList<String> list) {
		for(String station : list) {
			System.out.print(station + " ");
		}
		System.out.println();
	}
	
	public String[] getSaveRoute(String departure) {
		boolean[] visited = new boolean[n]; // �� ����� �湮 ����
		int distance[] = new int[n]; // ���� ��忡������ �� �������� �Ÿ�
		
		// ���� ��忡������ �� �������� ��� �Ÿ� �ʱ�ȭ
		for(int i = 0; i < n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		// ���ڿ��� �Էµ� ���� ��带 int�� ����
		int x = stringToInt(departure);
		// ���� ��� x�� �Ÿ��� 0���� �ʱ�ȭ
		distance[x] = 0; 
		// �湮 ����̹Ƿ� �湮 ǥ��
		visited[x] = true;
		
		// ���� ����� ��δ� �ڱ� �ڽ�
		saveRoute[x] = vertex[x];
		
		//���� ��� x�������� ��� i������ �Ÿ��� ����
		for(int i = 0; i < n; i++) {
			
			// �湮���� �ʾҰ� x���� i������ ����ġ�� �����Ѵٸ�, �Ÿ� i�� x���� i������ ����ġ ����
			// �� x���� ������ �������� �Ÿ��� ����
			if(!visited[i] && weight[x][i] != 0) {
				distance[i] = weight[x][i];
				saveRoute[i] = vertex[x]; // ���� ���� ������ ����� ��ο� ���� ��带 ����
			}
		}
		
		for(int i = 0; i < n-1; i++) {
			int minDistance = Integer.MAX_VALUE; // �ִܰŸ� minDistance�� �ϴ� ���� ū ������ ����
			int minVertex = -1; // �� �Ÿ����� �ִ� �ε��� minIndex�� -1�� ����
			
			for(int j = 0; j < n; j++) {
				
				// �湮���� �ʾҰ� �Ÿ��� ������ ��� �߿��� ���� ����� �Ÿ��� ���� ����� ��带 Ž��
				if(!visited[j] && distance[j] != Integer.MAX_VALUE) {
					
					if(distance[j] < minDistance) {
						minDistance = distance[j];
						minVertex = j;
					}
				}
			}	
			
			visited[minVertex] = true; // ���� �ݺ����� ���� ����� ���� ����� ��忡 �湮 ǥ��
			
			for(int j = 0; j < n; j++) {
				
				//�湮���� �ʾҰ� minVertex���� ����ġ�� �����ϴ�(minVertex���� �����) �����
				if(!visited[j] && weight[minVertex][j] != 0) {
					
					//���� �� ��尡 ������ �ִ� �Ÿ����� minVertex�� ����ġ�� ���� ������ ũ�ٸ� �ִܰŸ� ����
					if(distance[j] > distance[minVertex] + weight[minVertex][j]) {
						distance[j] = distance[minVertex] + weight[minVertex][j];
						
						// �ִܰŸ��� ���ŵ� ����� ��ο� minVertex�� �ش��ϴ� ��� ����
						saveRoute[j] = vertex[minVertex]; 
					}
				}
			}
		}
		return saveRoute;
	}
	
	public int[] getDistance(String departure) {
		
		boolean[] visited = new boolean[n]; // �� ����� �湮 ����
		int distance[] = new int[n]; // ���� ��忡������ �� �������� �Ÿ�
		
		// ���� ��忡������ �� �������� ��� �Ÿ� �ʱ�ȭ
		for(int i = 0; i < n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		// ���ڿ��� �Էµ� ���� ��带 int�� ����
		int x = stringToInt(departure);
		// ���� ��� x�� �Ÿ��� 0���� �ʱ�ȭ
		distance[x] = 0; 
		// �湮 ����̹Ƿ� �湮 ǥ��
		visited[x] = true;
		
		// ���� ����� ��δ� �ڱ� �ڽ�
		saveRoute[x] = vertex[x];
		
		//���� ��� x�������� ��� i������ �Ÿ��� ����
		for(int i = 0; i < n; i++) {
			
			// �湮���� �ʾҰ� x���� i������ ����ġ�� �����Ѵٸ�, �Ÿ� i�� x���� i������ ����ġ ����
			// �� x���� ������ �������� �Ÿ��� ����
			if(!visited[i] && weight[x][i] != 0) {
				distance[i] = weight[x][i];
				saveRoute[i] = vertex[x]; // ���� ���� ������ ����� ��ο� ���� ��带 ����
			}
		}
		
		for(int i = 0; i < n-1; i++) {
			int minDistance = Integer.MAX_VALUE; // �ִܰŸ� minDistance�� �ϴ� ���� ū ������ ����
			int minVertex = -1; // �� �Ÿ����� �ִ� �ε��� minIndex�� -1�� ����
			
			for(int j = 0; j < n; j++) {
				
				// �湮���� �ʾҰ� �Ÿ��� ������ ��� �߿��� ���� ����� �Ÿ��� ���� ����� ��带 Ž��
				if(!visited[j] && distance[j] != Integer.MAX_VALUE) {
					
					if(distance[j] < minDistance) {
						minDistance = distance[j];
						minVertex = j;
					}
				}
			}	
			
			visited[minVertex] = true; // ���� �ݺ����� ���� ����� ���� ����� ��忡 �湮 ǥ��
			
			for(int j = 0; j < n; j++) {
				
				//�湮���� �ʾҰ� minVertex���� ����ġ�� �����ϴ�(minVertex���� �����) �����
				if(!visited[j] && weight[minVertex][j] != 0) {
					
					//���� �� ��尡 ������ �ִ� �Ÿ����� minVertex�� ����ġ�� ���� ������ ũ�ٸ� �ִܰŸ� ����
					if(distance[j] > distance[minVertex] + weight[minVertex][j]) {
						distance[j] = distance[minVertex] + weight[minVertex][j];
						
						// �ִܰŸ��� ���ŵ� ����� ��ο� minVertex�� �ش��ϴ� ��� ����
						saveRoute[j] = vertex[minVertex]; 
					}
				}
			}
		}
		return distance;
	}


	public String getList(ArrayList<String> list) {

		String t="";
		for (String station : list) {
			t+=station +" -> ";
		}
		return t;
	}



	// �ε��� ���
	public int getIndex(String arrival) {
		int temp; //��� �迭 �߿��� ������ �������� �´� ����� ��� ���� �ε���
		
		for(temp = 0; temp < n; temp++) {
			if(arrival.equals(vertex[temp])) break;
		}
		return temp;
	}
	
	// �ּҺ�� �ȳ�(���= �ð� or ��)
	public int getLowCost(String departure, String arrival) {
		//���� ������ Ư�� �������� ��� ���
		int temp = getIndex(arrival);
		int[] distance = getDistance(departure);

//		toast = Toast.makeText(MainActivity.ApplicationContext(), "도착하면 알람이 울립니다.\n알람은 5초 뒤에 자동으로 꺼집니다.",Toast.LENGTH_LONG);
//		toast.show();
		//timer run
		TimerTask startTimerTask = new TimerTask() {
			@Override
			public void run() {
				Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				Ringtone rt = RingtoneManager.getRingtone(MainActivity.ApplicationContext(),notification);
				rt.play();
			}
		};
		timer.schedule(startTimerTask,distance[temp]*1000,500);
		TimerTask stopTimerTask = new TimerTask() {
			@Override
			public void run() {
				startTimerTask.cancel();
			}
		};
		timer.schedule(stopTimerTask,distance[temp]*1000+5000);

		return distance[temp];
	}
	
	// �ּҺ�� ��� �ȳ�(���= �ð� or ��)
	public String[] getLowCostRoute(String departure, String arrival) {
		//���� ������ Ư�� �������� ��� ���

		saveRoute = getSaveRoute(departure);
		
		// ���������� ������������ ��θ� �����
		int temp = getIndex(arrival);
		int check = temp;
		String route = "";
		
		while(true) {
			if(saveRoute[check] == vertex[check]) break; //���� ����� ��� break
			route += saveRoute[check] + " ";
			check = stringToInt(saveRoute[check]);
		}
		String[] reverse = getReverse(route);
		
		// reverse�� ����Ʈ�� �ٲپ� ��� �������� ������ �߰��ϱ�
		List<String> list = new ArrayList<>(Arrays.asList(reverse));
		list.add(arrival); // ����Ʈ �������� ������ �߰�
		reverse = new String[list.size()];
		list.toArray(reverse); // ����Ʈ�� �ٽ� �迭�� �ٲ�
		
		return reverse;
	}
}
