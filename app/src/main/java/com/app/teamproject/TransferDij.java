package com.app.teamproject;

import java.util.*;

public class TransferDij { //2021 ���� , �迭�� ������ �Ѿ�� ���� �߻� ArrayIndexOutOfBoundsException
	static class Subway {
		int line_num;
		int station_num;
		int transformation_cnt;

		public Subway(int line_num, int station_num, int transformation_cnt) {
			super();
			this.line_num = line_num; //ȣ�� ����(1ȣ��, 2ȣ��...)
			this.station_num = station_num; //�� ����
			this.transformation_cnt = transformation_cnt; //ȯ�°��ɿ� ����
		}
	}
	
	static int N, L;
	static String from, to;
	static int answer = Integer.MAX_VALUE;
	static TransferDij test = new TransferDij(111, 9);
	
	static boolean[] visitedLines;
	static boolean[] visitedStations;

	static List<Integer>[] stations;	// �� ���� ������ �뼱��
	static List<Integer>[] lines; 		// �� �뼱�� ������ ����
	static ArrayList<String> transferStation;// ȯ�¿�
	
	private String vertex[] = {
			"101","102","103","104","105","106","107","108","109","110",
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
	
	private String vertex_lines[] = {
		"101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "101", "-1",
		"101", "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211", "212", "213", "214", "215", "216", "217", "-1",
		"207", "301", "302", "303", "304", "123", "305", "306", "307", "308", "107", "-1",
		"104", "401", "307", "402", "403", "404", "405", "406", "407", "115", "408", "409", "410", "411", "412", "413", "414", "415", "416", "417", "216", "-1",
		"209", "501", "502", "503", "504", "122", "505", "506", "403", "507", "109", "-1",
		"601", "602", "121", "603", "604", "605", "606", "116", "607", "608", "609", "412", "610", "611", "612", "613", "614", "615", "616", "417", "617", "618", "619", "620", "621", "622", "601", "-1",
		"202", "303", "503", "601", "701", "702", "703", "704", "705", "706", "416", "707", "614", "-1",
		"113", "801", "802", "803", "409", "608", "804", "805", "806", "705", "618", "214", "-1",
		"112", "901", "406", "605", "902", "118", "903", "702", "904", "621", "211", "-1"
	};
	
	// ������
	public TransferDij(int node, int line) {
		N = node;
		L = line;
		visitedLines = new boolean[L];
		visitedStations = new boolean[N + 1];
		lines = new ArrayList[L];
		stations = new ArrayList[N + 1];
	}
	
	// vertex, vertex_lines ��
	public int compareVertex_VertexLines(String vertex_lines) {
		int index;
		for(index = 0; index < N; index++) {
			if(vertex_lines.equals(vertex[index])) { // �Ķ���ͷ� �ѱ� ���� �ִ� ���̸�
				return index; // �� ���� �ε��� ��ȯ
			}
		}
		// �Ķ���ͷ� �ѱ� ���� ���� ���̸� -1��ȯ
		return -1;
	}
	
	public String getStation(int index) {
		String station = test.vertex[index];
		return station;
	}
	
	//vertex_line���� �ε����� �´� �� ��ȯ
	public String getStation_inVL(int index) {
		String station = test.vertex_lines[index];
		return station;
	}
	
	// �ε��� ���: vertex
	public int getIndex(String arrival) {
		int temp; //��� �迭 �߿��� ������ �������� �´� ����� ��� ���� �ε���
		
		for(temp = 0; temp < N; temp++) {
			if(vertex[temp].equals(arrival)) break;
		}
		return temp;
	}	
	
	//ȯ�� ��� �ȳ�
	public ArrayList<String> getTransStation(String departure, String arrival) {
		ArrayList<String> transferRoute = new ArrayList<String>();

		List<Integer> temp = new ArrayList<>();	
		List<Integer> temp1 = new ArrayList<>();
		
		//������� ������ ��ġ ����
		for(int i = 0; i < vertex_lines.length; i++) {
			if(vertex_lines[i].equals(departure)) {
				temp.add(i);
			}
		}
		
		for(int j = 0; j < vertex_lines.length; j++) {
			if(vertex_lines[j].equals(arrival)) {
				 temp1.add(j);
			}
		}
		
		//������ ��ġ �� ������� ������ ���� ���̰� ����� ��
		int index = 0;
		int index1 = 1;
		
		for(int n = 0; n < temp.size(); n++) {
			for(int m = 0; m < temp1.size(); m++) {
				//-1�� �ڲ� ���ԵǸ� ��˻� �ϵ��� ���� ��;
				if(Math.abs(temp.get(n)- temp1.get(m)) <27){
					index = temp.get(n); index1 = temp1.get(m);
					break;
				}
			}
		}
				
		if(index > index1) {
			for(int k = index; k >= index1; k--) {
				transferRoute.add(vertex_lines[k]);
			}
		}
		else if(index < index1){
			for(int k = index; k <= index1; k++) {
				transferRoute.add(vertex_lines[k]);
			}
		}
		
		return transferRoute;
	}

	public void addLines(){
		int i1 = 0;
		for (int i = 0; i < L; i++) {
			// �� �뼱�� ������ ���� ������� �־�����.
			while (true) {
				int temp = test.compareVertex_VertexLines(test.vertex_lines[i1]);
				i1++;
				if (temp == -1)
					break;

				lines[i].add(temp);
			}
		}
	}

	public void addStatons(){
		int i1 = 0;
		for (int i = 0; i < L; i++) {
			// �� �뼱�� ������ ���� ������� �־�����.
			while (true) {
				int temp = test.compareVertex_VertexLines(test.vertex_lines[i1]);
				i1++;
				if (temp == -1)
					break;

				stations[temp].add(i);		
			}
		}
	}
	
	public void createList() {
		for (int i = 0; i <= N; i++) {
			stations[i] = new ArrayList<>();
		}
		for (int i = 0; i < L; i++) {
			lines[i] = new ArrayList<>();
		}
	}

	public static int[] bfs() {
		Queue<Subway> queue = new LinkedList<>();
		visitedStations[test.getIndex(from)] = true;
		transferStation = new ArrayList<String>();
		
		int one_line = 0;
		String one_station = "";
		
		for (int line : stations[test.getIndex(from)]) {
			queue.offer(new Subway(line, test.getIndex(from), 0)); // start ���� ������ �뼱���� ����
			visitedLines[line] = true;
		}

		while (!queue.isEmpty()) {
			Subway subway = queue.poll();

			if (subway.station_num == test.getIndex(to)) {
				answer = Math.min(answer, subway.transformation_cnt);
			}

			for (int station : lines[subway.line_num]) { // �ش� ���� ������ ����
				if (!visitedStations[station]) {
					visitedStations[station] = true;
					
					queue.offer(new Subway(subway.line_num, station, subway.transformation_cnt));
					for (int line : stations[station]) { // �ش� ���� ������ �뼱��
						
						if (line != subway.line_num && !visitedLines[line]) {
							// ȯ�� Ƚ���� 1�϶�
							if(subway.transformation_cnt == 0 ) {
								if(stations[station].contains(stations[test.getIndex(to)].get(0)) && stations[station].contains(stations[test.getIndex(from)].get(0))) {
									transferStation.add(test.getStation(station));
									one_line = stations[station].get(0); one_station = test.getStation(station);
								}
							}

							// ȯ�� Ƚ���� 2�� ��
							if (stations[station].contains(stations[test.getIndex(to)].get(0)) || stations[station].contains(stations[test.getIndex(from)].get(0))){
								transferStation.add(test.getStation(station));
							}													
							
							visitedLines[line] = true;							
							queue.offer(new Subway(line, station, subway.transformation_cnt + 1));
						}
					}
				}
			}
		}
		
		int[] index = new int[2];

		if(answer == 1) {
			System.out.println("\n��߿�: " + from + " " + "������: " + to);
			index[0] = one_line;
			index[1] = test.getIndex(one_station);
			
		} else if(answer > 1) {
			int fromLine = stations[test.getIndex(from)].get(0); //��߿��� �����ϴ� ȣ��
			int toLine = stations[test.getIndex(to)].get(0); //�������� �����ϴ� ȣ��
			
			ArrayList<Integer> interSection = new ArrayList<Integer>();

			for(String station : transferStation) {
				if(stations[test.getIndex(station)].remove(Integer.valueOf(fromLine))){
					stations[test.getIndex(station)].remove(Integer.valueOf(fromLine));
				} else if (stations[test.getIndex(station)].remove(Integer.valueOf(toLine))) {
					stations[test.getIndex(station)].remove(Integer.valueOf(toLine));
				}
				interSection.add(stations[test.getIndex(station)].get(0));
			}

			int cnt = 0;
			int last_j = 0;
			int i;
			for(i = 0; i < interSection.size(); i++) {
				for(int j = 0; j < interSection.size(); j++) {
					if(interSection.get(i) == interSection.get(j)) {
						cnt++;
					}
					last_j = j;
				}
				if(cnt == 2) {
					//System.out.println("�ߺ� �߰�: " + interSection.get(i));
					break;
				} else {
					cnt = 0;
				}
			}
			
			index[0] = i;
			index[1] = last_j;

		}
		return index;
		
	}	
}
