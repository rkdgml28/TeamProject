package com.app.teamproject;

public class Driver {
	
	Test d;
	static String start;
	static String end;
	
	public Driver() {
		d = new Test(111); // ��尡 111���� �׷���	
	}
	
	//���, ���� ����
	public void setFromTo(String from, String to) {
		start = from; end = to;
		TransferDij.from = from; TransferDij.to = to;
	}
	
	// ���� ����ġ: �ð�(��)
	public void inputTimeInfor() {
		d.input("101","102",200); //101 -> 102�� �ð�:200
		d.input("102","103",300);
		d.input("103","104",1000);
		d.input("104","105",500);
		d.input("105","106",150);
		d.input("106","107",320);
		d.input("107","108",400);
		d.input("108","109",800);
		d.input("109","110",900);
		d.input("110","111",500); 
		//line11
		d.input("111","112",1000);
		d.input("112","113",2000);
		d.input("113","114",500);
		d.input("114","115",220);
		d.input("115","116",230);
		d.input("116","117",300);
		d.input("117","118",500);
		d.input("118","119",480);
		d.input("119","120",500);
		d.input("120","121",400);
		//line21
		d.input("121","122",900);
		d.input("122","123",300);
		d.input("123","101",480);
		d.input("101","201",1000);
		d.input("201","202",250);
		d.input("202","203",480);
		d.input("203","204",400);
		d.input("204","205",250);
		d.input("205","206",500);
		d.input("206","207",320);
		//line31
		d.input("207","208",250);
		d.input("208","209",300);
		d.input("209","210",150);
		d.input("210","211",900);
		d.input("211","212",320);
		d.input("212","213",150);
		d.input("213","214",500);
		d.input("214","215",210);
		d.input("215","216",150);
		d.input("216","217",500);
		//line41
		d.input("207","301",300);
		d.input("301","302",300);
		d.input("302","303",480);
		d.input("303","304",400);
		d.input("304","123",250);
		d.input("123","305",300);
		d.input("305","306",250);
		d.input("306","307",900);
		d.input("307","308",480);
		d.input("308","107",400);
		//line51
		d.input("104","401",1000);
		d.input("401","307",150);
		d.input("307","402",300);
		d.input("402","403",210);
		d.input("403","404",320);
		d.input("404","405",210);
		d.input("405","406",500);
		d.input("406","407",300);
		d.input("407","115",320);
		d.input("115","408",480);
		//line61
		d.input("408","409",300);
		d.input("409","410",480);
		d.input("410","411",300);
		d.input("411","412",900);
		d.input("412","413",400);
		d.input("413","414",430);
		d.input("414","415",150);
		d.input("415","416",1000);
		d.input("416","417",500);
		d.input("417","216",900);
		//line71
		d.input("209","501",320);
		d.input("501","502",320);
		d.input("502","503",430);
		d.input("503","504",210);
		d.input("504","122",320);
		d.input("122","505",480);
		d.input("505","506",300);
		d.input("506","403",320);
		d.input("403","507",300);
		d.input("507","109",1000);
		//line81
		d.input("601","602",150);
		d.input("602","121",700);
		d.input("121","603",500);
		d.input("603","604",300);
		d.input("604","605",430);
		d.input("605","606",480);
		d.input("606","116",320);
		d.input("116","607",250);
		d.input("607","608",500);
		d.input("608","609",700);
		//line91
		d.input("609","412",320);
		d.input("412","610",1000);
		d.input("610","611",700);
		d.input("611","612",700);
		d.input("612","613",150);
		d.input("613","614",430);
		d.input("614","615",500);
		d.input("615","616",700);
		d.input("616","417",480);
		d.input("417","617",320);
		//line101
		d.input("617","618",300);
		d.input("618","619",250);
		d.input("619","620",700);
		d.input("620","621",320);
		d.input("621","622",480);
		d.input("622","601",150);
		d.input("202","303",1000);
		d.input("303","503",700);
		d.input("503","601",500);
		d.input("601","701",430);
		//line111
		d.input("701","702",150);
		d.input("702","703",600);
		d.input("703","704",700);
		d.input("704","705",250);
		d.input("705","706",600);
		d.input("706","416",300);
		d.input("416","707",430);
		d.input("707","614",480);
		d.input("113","801",600);
		d.input("801","802",1000);
		//line121
		d.input("802","803",700);
		d.input("803","409",600);
		d.input("409","608",500);
		d.input("608","804",700);
		d.input("804","805",150);
		d.input("805","806",210);
		d.input("806","705",600);
		d.input("705","618",250);
		d.input("618","214",700);
		d.input("112","901",600);
		//line131
		d.input("901","406",300);
		d.input("406","605",210);
		d.input("605","902",480);
		d.input("902","119",430);
		d.input("119","903",1000);
		d.input("903","702",150);
		d.input("702","904",500);
		d.input("904","621",250);
		d.input("621","211",300);
		//line140
	}
 	
	// ���� ����ġ: ���(��)
	public void inputPriceInfor() {
		d.input("101","102",200); //101 -> 102�� ��� : 200
		d.input("102","103",300);
		d.input("103","104",500);
		d.input("104","105",340);
		d.input("105","106",450);
		d.input("106","107",120);
		d.input("107","108",650);
		d.input("108","109",200);
		d.input("109","110",430);
		d.input("110","111",120); 
		//line11
		d.input("111","112",890);
		d.input("112","113",800);
		d.input("113","114",700);
		d.input("114","115",540);
		d.input("115","116",330);
		d.input("116","117",280);
		d.input("117","118",800);
		d.input("118","119",1000);
		d.input("119","120",2000);
		d.input("120","121",700);
		//line21
		d.input("121","122",650);
		d.input("122","123",440);
		d.input("123","101",200);
		d.input("101","201",300);
		d.input("201","202",500);
		d.input("202","203",340);
		d.input("203","204",450);
		d.input("204","205",120);
		d.input("205","206",650);
		d.input("206","207",200);
		//line31
		d.input("207","208",430);
		d.input("208","209",120);
		d.input("209","210",890);
		d.input("210","211",800);
		d.input("211","212",700);
		d.input("212","213",540);
		d.input("213","214",330);
		d.input("214","215",280);
		d.input("215","216",800);
		d.input("216","217",1000);
		//line41
		d.input("207","301",2000);
		d.input("301","302",700);
		d.input("302","303",650);
		d.input("303","304",440);
		d.input("304","123",200);
		d.input("123","305",300);
		d.input("305","306",500);
		d.input("306","307",340);
		d.input("307","308",450);
		d.input("308","107",120);
		//line51
		d.input("104","401",650);
		d.input("401","307",200);
		d.input("307","402",430);
		d.input("402","403",120);
		d.input("403","404",890);
		d.input("404","405",800);
		d.input("405","406",700);
		d.input("406","407",540);
		d.input("407","115",330);
		d.input("115","408",280);
		//line61
		d.input("408","409",800);
		d.input("409","410",1000);
		d.input("410","411",2000);
		d.input("411","412",700);
		d.input("412","413",650);
		d.input("413","414",440);
		d.input("414","415",200);
		d.input("415","416",300);
		d.input("416","417",500);
		d.input("417","216",340);
		//line71
		d.input("209","501",450);
		d.input("501","502",120);
		d.input("502","503",650);
		d.input("503","504",200);
		d.input("504","122",430);
		d.input("122","505",120);
		d.input("505","506",890);
		d.input("506","403",800);
		d.input("403","507",700);
		d.input("507","109",540);
		//line81
		d.input("601","602",330);
		d.input("602","121",280);
		d.input("121","603",800);
		d.input("603","604",1000);
		d.input("604","605",2000);
		d.input("605","606",700);
		d.input("606","116",650);
		d.input("116","607",440);
		d.input("607","608",200);
		d.input("608","609",300);
		//line91
		d.input("609","412",500);
		d.input("412","610",340);
		d.input("610","611",450);
		d.input("611","612",120);
		d.input("612","613",650);
		d.input("613","614",200);
		d.input("614","615",430);
		d.input("615","616",120);
		d.input("616","417",890);
		d.input("417","617",800);
		//line101
		d.input("617","618",700);
		d.input("618","619",540);
		d.input("619","620",330);
		d.input("620","621",280);
		d.input("621","622",800);
		d.input("622","601",1000);
		d.input("202","303",2000);
		d.input("303","503",700);
		d.input("503","601",650);
		d.input("601","701",440);
		//line111
		d.input("701","702",200);
		d.input("702","703",300);
		d.input("703","704",500);
		d.input("704","705",340);
		d.input("705","706",450);
		d.input("706","416",120);
		d.input("416","707",650);
		d.input("707","614",200);
		d.input("113","801",430);
		d.input("801","802",120);
		//line121
		d.input("802","803",890);
		d.input("803","409",800);
		d.input("409","608",700);
		d.input("608","804",540);
		d.input("804","805",330);
		d.input("805","806",280);
		d.input("806","705",800);
		d.input("705","618",1000);
		d.input("618","214",2000);
		d.input("112","901",700);
		//line131
		d.input("901","406",650);
		d.input("406","605",440);
		d.input("605","902",280);
		d.input("902","119",800);
		d.input("119","903",1000);
		d.input("903","702",2000);
		d.input("702","904",700);
		d.input("904","621",650);
		d.input("621","211",440);
		//line140
	}
	
	public static void main(String[] args) {
		Driver driver = new Driver();
		
		//ȯ�� ������Ʈ
		//305->620 
		//505->610 
		
		driver.setFromTo("505", "803");
		
		// 1. �ּҽð� ���
		System.out.println("----------�ּҽð� ���----------");
		
		// 1) ����ġ�� �ð� �Է��ϱ�
		driver.inputTimeInfor();
		System.out.println(start + " -> " + end);
		
		// 2). �ּҽð� ���
		int[] time = driver.d.convertTime(driver.d.getLowCost(start, end));
		System.out.println("�ּҽð�: " + time[0] + "�ð� " + time[1] + "�� " + time[2] + "��");
		
		// 3). �ּҽð� ��� ���
		System.out.print("�ּҽð� ���: ");
		String[] reverse = driver.d.getLowCostRoute(start, end);
		driver.d.printArray(reverse);
		
		// 4) �ּҽð� ����� ��� ���
		driver.inputPriceInfor(); // �ּҽð� ����� ����� ���ؾ� �ϹǷ� ����ġ�� ����� �Է��Ѵ�. 
		int cost = driver.d.getCost_minTimeRoute(reverse);
		System.out.println("�ּҽð� ��� ���: " + cost + "��");
		
		System.out.println();
		
		// 2. �ּҺ�� ���
		System.out.println("----------�ּҺ�� ���----------");

		// 1) ����ġ�� ��� �Է��ϱ�
		driver.inputPriceInfor();
		System.out.println(start + " -> " + end);
		
		// 2) �ּ� ��� ���
		System.out.println("�ּҺ��: " + driver.d.getLowCost(start, end) + " ��");
		
		// 3) �ּҺ�� ��� ���
		System.out.print("�ּҺ�� ���: ");
		reverse = driver.d.getLowCostRoute(start, end);
		driver.d.printArray(reverse);

		// 4) �ּҺ�� ����� �ð� ���
		driver.inputTimeInfor(); // �ּҺ�� ����� �ð��� ���ؾ� �ϹǷ� ����ġ�� �ð��� �Է��Ѵ�.
		time = driver.d.convertTime(driver.d.getTime_minPriceRoute(reverse));
		System.out.println("�ּҺ�� ��� �ð�: " + time[0] + "�ð� " + time[1] + "�� " + time[2] + "��");

		System.out.println();
		
		// 3. �߰����� ���(�ð��� �������� �߰������� ����)
		System.out.println("----------�߰����� ���----------");

		// 1) ����ġ�� �ð� �Է��ϱ�
		driver.inputTimeInfor();
		System.out.println(start + " -> " + end);
		
		// 2) �߰��� ���
		String middle = driver.d.middlePoint(start, end);
		System.out.println("�߰�����: " + middle + "\n");
		
		// 3) ��߿� ~ �߰��� ������ �ּҽð� ���
		System.out.println(start + " -> " + middle);
		time = driver.d.convertTime(driver.d.getLowCost(start, middle));
		System.out.println("�ּҽð�: " + time[0] + "�ð� " + time[1] + "�� " + time[2] + "��");
		
		// 4) ��߿� ~ �߰��� ������ �ּҽð� ��� ���
		System.out.print("�ּҽð� ���: ");
		reverse = driver.d.getLowCostRoute(start, middle);
		driver.d.printArray(reverse);
		
		// ��߿� ~ �߰��� ������ �ּҽð� ����� ��� ���
		driver.inputPriceInfor(); // �ּҽð� ����� ����� ���ؾ� �ϹǷ� ����ġ�� ����� �Է��Ѵ�. 
		cost = driver.d.getCost_minTimeRoute(reverse);
		System.out.println("�ּҽð� ��� ���: " + cost + "��");
		
		System.out.println();
		
		// 5) �߰��� ~ ������������ �ּҽð� ���
		driver.inputTimeInfor();
		System.out.println(middle + " -> " + end);
		time = driver.d.convertTime(driver.d.getLowCost(middle, end));
		System.out.println("�ּҽð�: " + time[0] + "�ð� " + time[1] + "�� " + time[2] + "��");
		
		// 6) �߰��� ~ ������������ �ּҽð� ��� ���
		System.out.print("�ּҽð� ���: ");
		reverse = driver.d.getLowCostRoute(middle, end);		
		driver.d.printArray(reverse);
		
		// �߰��� ~ ������ ������ �ּҽð� ����� ��� ���
		driver.inputPriceInfor(); // �ּҽð� ����� ����� ���ؾ� �ϹǷ� ����ġ�� ����� �Է��Ѵ�. 
		cost = driver.d.getCost_minTimeRoute(reverse);
		System.out.println("�ּҽð� ��� ���: " + cost + "��");
		
		System.out.println();
		
		// 4. �ּ�ȯ�� ��� 
		System.out.println("----------�ּ�ȯ�� ���----------");

		// 1) �뼱�� ���� ����Ʈ ����
		TransferDij.test.createList();
		TransferDij.test.addLines();
		TransferDij.test.addStatons();
		
		int[] index = TransferDij.bfs();
		
		// 2) ȯ�¿� ���
		if(TransferDij.answer == 1) {
			System.out.println("ù��° ȯ�¿�: " + (index[0]+1) + "ȣ�� " + TransferDij.test.getStation(index[1]));
		} else if(TransferDij.answer > 1){
			System.out.println("��߿�: " + start + " " + "������: " + end);
			// ù��° ȯ�¿� ����
			int first_inter_line = TransferDij.stations[driver.d.getIndex(TransferDij.transferStation.get(index[0]))].get(0);
			// ù��° ȯ�¿�
			String first_inter_station = TransferDij.transferStation.get(index[0]);
			// �ι�° ȯ�¿� ����
			int sec_inter_line = TransferDij.stations[driver.d.getIndex(TransferDij.transferStation.get(index[1]))].get(0);
			// �ι�° ȯ�¿�
			String sec_inter_station = TransferDij.transferStation.get(index[1]);
			
			System.out.println("ù��° ȯ�¿�: " + (first_inter_line+1) + "ȣ�� " + first_inter_station + "\n" 
							 + "�ι�° ȯ�¿�: " + (sec_inter_line+1) + "ȣ�� " + sec_inter_station);
		}
		
		// 3) �ּ�ȯ�� Ƚ�� ���
		if(TransferDij.answer == Integer.MAX_VALUE)
			System.out.println(-1);
		else{
			System.out.println("ȯ�� ȸ��: " + TransferDij.answer + "\n");
		}
		
		// 4) �ּ�ȯ�� ��� ���
		if(TransferDij.answer == 0) { //ȯ������ ���� ��
			System.out.print("���: ");
			driver.d.printList(TransferDij.test.getTransStation(start, end));
			
			// ��� �ð�
			driver.inputTimeInfor(); //����ġ�� �ð� �Է�
			int routeListSize = TransferDij.test.getTransStation(start, end).size(); 
			String route[] = TransferDij.test.getTransStation(start, end).toArray(new String[routeListSize]);
			time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
			System.out.println("��� �ð�: " + time[0] + "�ð� " + time[1] + "�� " + time[2] + "��");
			
			// ��� ���
			driver.inputPriceInfor(); //��� ���� �Է�
			System.out.println("��� ���: " + driver.d.getCost_minTimeRoute(route) + "��");
		}
		else if(TransferDij.answer == 1) { //ȯ�� 1�� �� ��
			String transfer = TransferDij.test.getStation(index[1]); // ȯ�¿�
			
			System.out.println(start + " -> " + transfer);
			System.out.print("��������� ù��° ȯ���������� ���: ");
			driver.d.printList(TransferDij.test.getTransStation(start, transfer));
			
			// ����� ~ ȯ�¿�1������ �ð�
			driver.inputTimeInfor(); //����ġ�� �ð� �Է�
			int routeListSize = TransferDij.test.getTransStation(start, transfer).size(); 
			String route[] = TransferDij.test.getTransStation(start, transfer).toArray(new String[routeListSize]);// �����~ȯ�¿�1 ������ ��ΰ� ����� ����Ʈ�� �迭�� ��ȯ 
			time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
			System.out.println("��� �ð�: " + time[0] + "�ð� " + time[1] + "�� " + time[2] + "��");
			
			// �����~ȯ����1������ ��� ���
			driver.inputPriceInfor(); //��� ���� �Է�
			System.out.println("��� ���: " + driver.d.getCost_minTimeRoute(route) + "��");
			
			System.out.println();
			
			System.out.println(transfer + " -> " + end);
			System.out.print("ȯ�������� ������ ������ ���: ");
			driver.d.printList(TransferDij.test.getTransStation(transfer, end));
			
			// ȯ�¿�1 ~ ������������ �ð�
			driver.inputTimeInfor();
			routeListSize = TransferDij.test.getTransStation(transfer, end).size();
			route = TransferDij.test.getTransStation(transfer, end).toArray(new String[routeListSize]);
			time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
			System.out.println("��� �ð�: " + time[0] + "�ð� " + time[1] + "�� " + time[2] + "��");
			
			// ȯ����1~������������ ��� ���
			driver.inputPriceInfor();
			System.out.println("��� ���: " + driver.d.getCost_minTimeRoute(route) + "��");

		}
		else if(TransferDij.answer == 2) { //ȯ�� 2�� �� ��
			String first_transfer = TransferDij.transferStation.get(index[0]); // ù���� ȯ�¿�
			String sec_transfer = TransferDij.transferStation.get(index[1]); // �ι�° ȯ�¿�
			
			System.out.println(start + " -> " + first_transfer);
			System.out.print("��������� ù��° ȯ���������� ���: ");
			driver.d.printList(TransferDij.test.getTransStation(start, first_transfer));
			// ����� ~ ȯ�¿�1������ �ð�
			driver.inputTimeInfor(); //����ġ�� �ð� �Է�
			int routeListSize = TransferDij.test.getTransStation(start, first_transfer).size(); 
			String route[] = TransferDij.test.getTransStation(start, first_transfer).toArray(new String[routeListSize]);// �����~ȯ�¿�1 ������ ��ΰ� ����� ����Ʈ�� �迭�� ��ȯ 
			time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
			System.out.println("��� �ð�: " + time[0] + "�ð� " + time[1] + "�� " + time[2] + "��");
			
			// ����� ~ ȯ�¿�1 ������ ��� ���
			driver.inputPriceInfor();
			System.out.println("��� ���: " + driver.d.getCost_minTimeRoute(route) + "��");
			
			System.out.println();

			System.out.println(first_transfer + " -> " + sec_transfer);
			System.out.print("ù��° ȯ�������� �ι�° ȯ���������� ���: ");
			driver.d.printList(TransferDij.test.getTransStation(first_transfer, sec_transfer));
			// ȯ�¿�1 ~ ȯ�¿�2������ �ð�
			driver.inputTimeInfor(); //����ġ�� �ð� �Է�
			routeListSize = TransferDij.test.getTransStation(first_transfer, sec_transfer).size(); 
			route = TransferDij.test.getTransStation(first_transfer, sec_transfer).toArray(new String[routeListSize]);// �����~ȯ�¿�1 ������ ��ΰ� ����� ����Ʈ�� �迭�� ��ȯ 
			time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
			System.out.println("��� �ð�: " + time[0] + "�ð� " + time[1] + "�� " + time[2] + "��");
			
			// ȯ�¿�1 ~ ȯ�¿�2 ������ ��� ���
			driver.inputPriceInfor();
			System.out.println("��� ���: " + driver.d.getCost_minTimeRoute(route) + "��");
						
			System.out.println();

			System.out.println(sec_transfer + " -> " + end);
			System.out.print("�ι�° ȯ�������� ������������ ���: ");
			driver.d.printList(TransferDij.test.getTransStation(sec_transfer, end));
			// ȯ�¿�1 ~ ȯ�¿�2������ �ð�
			driver.inputTimeInfor(); //����ġ�� �ð� �Է�
			routeListSize = TransferDij.test.getTransStation(sec_transfer, end).size(); 
			route = TransferDij.test.getTransStation(sec_transfer, end).toArray(new String[routeListSize]);// �����~ȯ�¿�1 ������ ��ΰ� ����� ����Ʈ�� �迭�� ��ȯ 
			time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
			System.out.println("��� �ð�: " + time[0] + "�ð� " + time[1] + "�� " + time[2] + "��");
			
			// ȯ����2 ~ ������������ ��� ���
			driver.inputPriceInfor();
			System.out.println("��� ���: " + driver.d.getCost_minTimeRoute(route) + "��");
						
		}			
	}		
}	