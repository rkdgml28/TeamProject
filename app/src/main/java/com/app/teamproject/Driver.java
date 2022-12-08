package com.app.teamproject;

public class Driver {
	
	Test d;
	Stations s;
	static String start;
	static String end;
	
	public Driver() {
		d = new Test(111); // ��尡 111���� �׷���
		s = new Stations(111);
	}
	
	//출발, 도착 설정
	public void setFromTo(String from, String to) {
		start = from; end = to;
		TransferDij.from = from; TransferDij.to = to;
	}
	// 시간
	public void inputTimeInfor() {
		d.input("101","102",200); //101 -> 102   시간:200
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

	// 비용
	public void inputPriceInfor() {
		d.input("101","102",200); //101 -> 102  비용:200
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

	public void inputStationInfo(){
		s.input("101", "1호선, 2호선");
		s.input("102", "1호선");
		s.input("103", "1호선");
		s.input("104", "1호선, 4호선");
		s.input("105", "1호선");
		s.input("106", "1호선");
		s.input("107", "1호선, 3호선");
		s.input("108", "1호선");
		s.input("109", "1호선, 5호선");
		s.input("110", "1호선");
		s.input("111", "1호선");
		s.input("112", "1호선, 9호선");
		s.input("113", "1호선");
		s.input("114", "1호선");
		s.input("115", "1호선, 4호선");
		s.input("116", "1호선, 6호선");
		s.input("117", "1호선");
		s.input("118", "1호선");
		s.input("119", "1호선, 9호선");
		s.input("120", "1호선");
		s.input("121", "1호선, 6호선");
		s.input("122", "1호선, 5호선");
		s.input("123", "1호선, 3호선");

		s.input("201", "2호선");
		s.input("202", "2호선, 3호선");
		s.input("203", "2호선");
		s.input("204", "2호선");
		s.input("205", "2호선");
		s.input("206", "2호선");
		s.input("207", "2호선, 3호선");
		s.input("208", "2호선");
		s.input("209", "2호선, 5호선");
		s.input("210", "2호선");
		s.input("211", "2호선, 6호선");
		s.input("212", "2호선");
		s.input("213", "2호선");
		s.input("214", "2호선, 8호선");
		s.input("215", "2호선");
		s.input("216", "2호선, 4호선");
		s.input("217", "2호선");

		s.input("301", "3호선");
		s.input("302", "2호선");
		s.input("303", "2호선, 7호선");
		s.input("304", "2호선");
		s.input("305", "2호선");
		s.input("306", "2호선");
		s.input("307", "2호선, 4호선");
		s.input("308", "2호선");

		s.input("401", "4호선");
		s.input("402", "4호선");
		s.input("403", "4호선, 5호선");
		s.input("404", "4호선");
		s.input("405", "4호선");
		s.input("406", "4호선, 9호선");
		s.input("407", "4호선");
		s.input("408", "4호선");
		s.input("409", "4호선, 8호선");
		s.input("410", "4호선");
		s.input("411", "4호선");
		s.input("412", "4호선, 6호선");
		s.input("413", "4호선");
		s.input("414", "4호선");
		s.input("415", "4호선");
		s.input("416", "4호선, 7호선");
		s.input("417", "4호선, 6호선");

		s.input("501", "5호선");
		s.input("502", "5호선");
		s.input("503", "5호선, 7호선");
		s.input("504", "5호선");
		s.input("505", "5호선");
		s.input("506", "5호선");
		s.input("507", "5호선");

		s.input("601", "6호선, 7호선");
		s.input("602", "6호선");
		s.input("603", "6호선");
		s.input("604", "6호선");
		s.input("605", "6호선, 9호선");
		s.input("606", "6호선");
		s.input("607", "6호선");
		s.input("608", "6호선, 8호선");
		s.input("609", "6호선");
		s.input("610", "6호선");
		s.input("611", "6호선");
		s.input("612", "6호선");
		s.input("613", "6호선");
		s.input("614", "6호선, 7호선");
		s.input("615", "6호선");
		s.input("616", "6호선");
		s.input("617", "6호선");
		s.input("618", "6호선, 8호선");
		s.input("619", "6호선");
		s.input("620", "6호선");
		s.input("621", "6호선, 9호선");
		s.input("622", "6호선");

		s.input("701", "7호선");
		s.input("702", "7호선, 9호선");
		s.input("703", "7호선");
		s.input("704", "7호선");
		s.input("705", "7호선, 8호선");
		s.input("706", "7호선");
		s.input("707", "7호선");

		s.input("801", "8호선");
		s.input("802", "8호선");
		s.input("803", "8호선");
		s.input("804", "8호선");
		s.input("805", "8호선");
		s.input("806", "8호선");

		s.input("901", "9호선");
		s.input("902", "9호선");
		s.input("903", "9호선");
		s.input("904", "9호선");















	}



	public static void main(String[] args) {
		Driver driver = new Driver();

		//환승 블랙리스트
		//305->620
		//505->610

		driver.setFromTo("505", "803");

		// 1. 최소시간 경로
		System.out.println("----------최소시간 경로----------");

		// 1) 가중치로 시간 입력하기
		driver.inputTimeInfor();
		System.out.println(start + " -> " + end);

		// 2). 최소시간 출력
		int[] time = driver.d.convertTime(driver.d.getLowCost(start, end));
		System.out.println("최소시간: " + time[0] + "시간 " + time[1] + "분 " + time[2] + "초");

		// 3). 최소시간 경로 출력
		System.out.print("최소시간 경로: ");
		String[] reverse = driver.d.getLowCostRoute(start, end);
		driver.d.printArray(reverse);

		// 4) 최소시간 경로의 비용 출력
		driver.inputPriceInfor(); // 최소시간 경로의 비용을 구해야 하므로 가중치로 비용을 입력한다.
		int cost = driver.d.getCost_minTimeRoute(reverse);
		System.out.println("최소시간 경로 비용: " + cost + "원");

		System.out.println();

		// 2. 최소비용 경로
		System.out.println("----------최소비용 경로----------");

		// 1) 가중치로 비용 입력하기
		driver.inputPriceInfor();
		System.out.println(start + " -> " + end);

		// 2) 최소 비용 출력
		System.out.println("최소비용: " + driver.d.getLowCost(start, end) + " 원");

		// 3) 최소비용 경로 출력
		System.out.print("최소비용 경로: ");
		reverse = driver.d.getLowCostRoute(start, end);
		driver.d.printArray(reverse);

		// 4) 최소비용 경로의 시간 출력
		driver.inputTimeInfor(); // 최소비용 경로의 시간을 구해야 하므로 가중치로 시간을 입력한다.
		time = driver.d.convertTime(driver.d.getTime_minPriceRoute(reverse));
		System.out.println("최소비용 경로 시간: " + time[0] + "시간 " + time[1] + "분 " + time[2] + "초");

		System.out.println();


		// 4. 최소환승 경로
		System.out.println("----------최소환승 경로----------");

		// 1) 노선도 저장 리스트 생성
		TransferDij.test.createList();
		TransferDij.test.addLines();
		TransferDij.test.addStatons();

		int[] index = TransferDij.bfs();

		// 2) 환승역 출력
		if(TransferDij.answer == 1) {
			System.out.println("첫번째 환승역: " + (index[0]+1) + "호선 " + TransferDij.test.getStation(index[1]));
		} else if(TransferDij.answer > 1){
			System.out.println("출발역: " + start + " " + "도착역: " + end);
			// 첫번째 환승역 라인
			int first_inter_line = TransferDij.stations[driver.d.getIndex(TransferDij.transferStation.get(index[0]))].get(0);
			// 첫번째 환승역
			String first_inter_station = TransferDij.transferStation.get(index[0]);
			// 두번째 환승역 라인
			int sec_inter_line = TransferDij.stations[driver.d.getIndex(TransferDij.transferStation.get(index[1]))].get(0);
			// 두번째 환승역
			String sec_inter_station = TransferDij.transferStation.get(index[1]);

			System.out.println("첫번째 환승역: " + (first_inter_line+1) + "호선 " + first_inter_station + "\n"
					+ "두번째 환승역: " + (sec_inter_line+1) + "호선 " + sec_inter_station);
		}

		// 3) 최소환승 횟수 출력
		if(TransferDij.answer == Integer.MAX_VALUE)
			System.out.println(-1);
		else{
			System.out.println("환승 회수: " + TransferDij.answer + "\n");
		}

		// 4) 최소환승 경로 출력
		if(TransferDij.answer == 0) { //환승하지 않을 때
			System.out.print("경로: ");
			driver.d.printList(TransferDij.test.getTransStation(start, end));

			// 경로 시간
			driver.inputTimeInfor(); //가중치로 시간 입력
			int routeListSize = TransferDij.test.getTransStation(start, end).size();
			String route[] = TransferDij.test.getTransStation(start, end).toArray(new String[routeListSize]);
			time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
			System.out.println("경로 시간: " + time[0] + "시간 " + time[1] + "분 " + time[2] + "초");

			// 경로 비용
			driver.inputPriceInfor(); //비용 정보 입력
			System.out.println("경로 비용: " + driver.d.getCost_minTimeRoute(route) + "원");
		}
		else if(TransferDij.answer == 1) { //환승 1번 할 때
			String transfer = TransferDij.test.getStation(index[1]); // 환승역

			System.out.println(start + " -> " + transfer);
			System.out.print("출발지에서 첫번째 환승지까지의 경로: ");
			driver.d.printList(TransferDij.test.getTransStation(start, transfer));

			// 출발지 ~ 환승역1까지의 시간
			driver.inputTimeInfor(); //가중치로 시간 입력
			int routeListSize = TransferDij.test.getTransStation(start, transfer).size();
			String route[] = TransferDij.test.getTransStation(start, transfer).toArray(new String[routeListSize]);// 출발지~환승역1 까지의 경로가 저장된 리스트를 배열로 변환
			time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
			System.out.println("경로 시간: " + time[0] + "시간 " + time[1] + "분 " + time[2] + "초");

			// 출발지~환승지1까지의 비용 출력
			driver.inputPriceInfor(); //비용 정보 입력
			System.out.println("경로 비용: " + driver.d.getCost_minTimeRoute(route) + "원");

			System.out.println();

			System.out.println(transfer + " -> " + end);
			System.out.print("환승지에서 목적지 까지의 경로: ");
			driver.d.printList(TransferDij.test.getTransStation(transfer, end));

			// 환승역1 ~ 도착지까지의 시간
			driver.inputTimeInfor();
			routeListSize = TransferDij.test.getTransStation(transfer, end).size();
			route = TransferDij.test.getTransStation(transfer, end).toArray(new String[routeListSize]);
			time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
			System.out.println("경로 시간: " + time[0] + "시간 " + time[1] + "분 " + time[2] + "초");

			// 환승지1~도착지까지의 비용 출력
			driver.inputPriceInfor();
			System.out.println("경로 비용: " + driver.d.getCost_minTimeRoute(route) + "원");

		}
		else if(TransferDij.answer == 2) { //환승 2번 할 때
			String first_transfer = TransferDij.transferStation.get(index[0]); // 첫번재 환승역
			String sec_transfer = TransferDij.transferStation.get(index[1]); // 두번째 환승역

			System.out.println(start + " -> " + first_transfer);
			System.out.print("출발지에서 첫번째 환승지까지의 경로: ");
			driver.d.printList(TransferDij.test.getTransStation(start, first_transfer));
			// 출발지 ~ 환승역1까지의 시간
			driver.inputTimeInfor(); //가중치로 시간 입력
			int routeListSize = TransferDij.test.getTransStation(start, first_transfer).size();
			String route[] = TransferDij.test.getTransStation(start, first_transfer).toArray(new String[routeListSize]);// 출발지~환승역1 까지의 경로가 저장된 리스트를 배열로 변환
			time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
			System.out.println("경로 시간: " + time[0] + "시간 " + time[1] + "분 " + time[2] + "초");

			// 출발지 ~ 환승역1 까지의 비용 출력
			driver.inputPriceInfor();
			System.out.println("경로 비용: " + driver.d.getCost_minTimeRoute(route) + "원");

			System.out.println();

			System.out.println(first_transfer + " -> " + sec_transfer);
			System.out.print("첫번째 환승지에서 두번째 환승지까지의 경로: ");
			driver.d.printList(TransferDij.test.getTransStation(first_transfer, sec_transfer));
			// 환승역1 ~ 환승역2까지의 시간
			driver.inputTimeInfor(); //가중치로 시간 입력
			routeListSize = TransferDij.test.getTransStation(first_transfer, sec_transfer).size();
			route = TransferDij.test.getTransStation(first_transfer, sec_transfer).toArray(new String[routeListSize]);// 출발지~환승역1 까지의 경로가 저장된 리스트를 배열로 변환
			time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
			System.out.println("경로 시간: " + time[0] + "시간 " + time[1] + "분 " + time[2] + "초");

			// 환승역1 ~ 환승역2 까지의 비용 출력
			driver.inputPriceInfor();
			System.out.println("경로 비용: " + driver.d.getCost_minTimeRoute(route) + "원");

			System.out.println();

			System.out.println(sec_transfer + " -> " + end);
			System.out.print("두번째 환승지에서 목적지까지의 경로: ");
			driver.d.printList(TransferDij.test.getTransStation(sec_transfer, end));
			// 환승역1 ~ 환승역2까지의 시간
			driver.inputTimeInfor(); //가중치로 시간 입력
			routeListSize = TransferDij.test.getTransStation(sec_transfer, end).size();
			route = TransferDij.test.getTransStation(sec_transfer, end).toArray(new String[routeListSize]);// 출발지~환승역1 까지의 경로가 저장된 리스트를 배열로 변환
			time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
			System.out.println("경로 시간: " + time[0] + "시간 " + time[1] + "분 " + time[2] + "초");

			// 환승지2 ~ 도착지까지의 비용 출력
			driver.inputPriceInfor();
			System.out.println("경로 비용: " + driver.d.getCost_minTimeRoute(route) + "원");

		}
	}
}	