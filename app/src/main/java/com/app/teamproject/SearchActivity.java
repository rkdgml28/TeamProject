package com.app.teamproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton btnSubway, btnSetting, btnHome, btnRoad, btnStar, btnSearch;
    Boolean isAllFabsVisible, isStopOverVisible, isInputComplete;
    EditText edit_start, edit_stopover, edit_finish;
    ImageView btn_search, btn_change;
    Button btnArrivetime, btnMintime, btnMincost, btnMintran;
    CheckBox cb_stopover;
    TextView tv_stop, tv_route, auto_time, auto_cost, auto_tran, startText, stopoverText, arriveText;
    LinearLayout layout_img;
    String mode = "";
    public static Context mContext;
    View layout_inform;



    String[] stations = {"101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123",
                "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211", "212", "213", "214", "215", "216", "217",
                "301", "302", "303", "304", "305", "305", "307", "308",
                "401", "402", "403", "404", "405", "406", "407", "408", "409", "410", "411", "412", "413", "414", "415", "416", "417",
                "501", "502", "503", "504", "505", "506", "507",
                "601", "602", "603", "604", "605", "606", "607", "608", "609", "610", "611", "612", "613", "614", "615", "616", "617", "618", "619", "620", "621", "622",
                "701", "702", "703", "704", "705", "706", "707",
                "801", "802", "803", "804", "805", "806",
                "901", "902", "903", "904"};




    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("hh:mm:ss");

    Driver driver = new Driver();
    int[] index;

    String[] reverse;
    int[] time;

    //현재시간 불러오기 함수
    private String getTime() {
        String today = null;

        Date date = new Date();
        // 포맷변경 ( 년월일 시분초)
        SimpleDateFormat sdformat = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        today = sdformat.format(cal.getTime());

        return today;
    }
    //도착시간 더하기 함수
    private String getCTime() {
        String today = null;

        Date date = new Date();
        // 포맷변경 ( 년월일 시분초)
        SimpleDateFormat sdformat = new SimpleDateFormat("HH:mm:ss");
        // Java 시간 더하기
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, time[1]);
        cal.add(Calendar.HOUR, time[0]);
        today = sdformat.format(cal.getTime());

        return today;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchActivity.mContext = getApplicationContext();
        setContentView(R.layout.activity_search);

        btnArrivetime = findViewById(R.id.btnArrivetime);
        edit_start = findViewById(R.id.edit_start);
        edit_stopover = findViewById(R.id.edit_stopover);
        edit_finish = findViewById(R.id.edit_finish);
        btn_search = findViewById(R.id.btn_search);
        cb_stopover = findViewById(R.id.cb_stopover);
        btn_change = findViewById(R.id.search_change);

        btnMincost = findViewById(R.id.btnMinCost);
        btnMintime = findViewById(R.id.btnMinTime);
        btnMintran = findViewById(R.id.btnMinTran);

        layout_img = findViewById(R.id.layout_img);
        layout_inform = findViewById(R.id.inform);

        startText = findViewById(R.id.startText);
        stopoverText = findViewById(R.id.stopoverText);
        arriveText = findViewById(R.id.arriveText);

        tv_stop = findViewById(R.id.tv_stop);
        tv_route = findViewById(R.id.tv_test_route);

        auto_cost = findViewById(R.id.auto_cost);
        auto_time = findViewById(R.id.auto_time);
        auto_tran = findViewById(R.id.auto_transfer);


        AutoCompleteTextView auto_start = findViewById(R.id.edit_start);
        AutoCompleteTextView auto_stop = findViewById(R.id.edit_stopover);
        AutoCompleteTextView auto_finish = findViewById(R.id.edit_finish);

        auto_start.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stations));
        auto_stop.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stations));
        auto_finish.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stations));

        isStopOverVisible = false;
        isInputComplete = false;

        // FAB button
        btnSubway = findViewById(R.id.fab_subway);
        btnSetting = findViewById(R.id.fab_setting);
        btnHome = findViewById(R.id.fab_home);
        btnRoad = findViewById(R.id.fab_road);
        btnSearch = findViewById(R.id.fab_search);
        btnStar = findViewById(R.id.fab_star);

        btnSetting.setVisibility(View.GONE);
        btnHome.setVisibility(View.GONE);
        btnStar.setVisibility(View.GONE);
        btnSearch.setVisibility(View.GONE);
        btnRoad.setVisibility(View.GONE);

        isAllFabsVisible = false;

        //여기다 밑에것들 안보이게
        btnMintime.setVisibility(View.INVISIBLE);
        btnMincost.setVisibility(View.INVISIBLE);
        btnMintran.setVisibility(View.INVISIBLE);
        layout_img.setVisibility(View.INVISIBLE);
        layout_inform.setVisibility(View.INVISIBLE);

        btnSubway.setOnClickListener(view -> {
            if (!isAllFabsVisible) {
                // when isAllFabsVisible becomes true make all
                // the action name texts and FABs VISIBLE
                btnSetting.show();
                btnHome.show();
                btnStar.show();
                btnSearch.show();
                btnRoad.show();
                isAllFabsVisible = true;
            } else {
                btnSetting.hide();
                btnHome.hide();
                btnStar.hide();
                btnSearch.hide();
                btnRoad.hide();
                isAllFabsVisible = false;
            }
        });

        btnHome.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnStar.setOnClickListener(this);
        btnRoad.setOnClickListener(this);

        btnMincost.setOnClickListener(this);
        btnMintime.setOnClickListener(this);
        btnMintran.setOnClickListener(this);

        //출빌지 도착지 바꾸기 버튼
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.search_change:
                        String changedText1;
                        changedText1 = edit_finish.getText().toString();
                        String changedText2;
                        changedText2 = edit_start.getText().toString();

                        edit_start.setText(changedText1);
                        edit_finish.setText(changedText2);
                }
            }
        });


        // 검색 버튼 클릭
        btn_search.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                String start = edit_start.getText().toString();
                String stopover = edit_stopover.getText().toString();
                String finish = edit_finish.getText().toString();

                btnMintime.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.sub_color4));
                btnMincost.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.sub_color4));
                btnMintran.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.sub_color4));

                if (start.length() == 0) {
                    Toast.makeText(getApplicationContext(), "출발역을 입력하세요.", Toast.LENGTH_SHORT).show();
                    isInputComplete = false;
                } else {
                    if (finish.length() == 0) {
                        Toast.makeText(getApplicationContext(), "도착역을 입력하세요.", Toast.LENGTH_SHORT).show();
                        isInputComplete = false;
                    } else {
                        if (isStopOverVisible && stopover.length() == 0) {
                            Toast.makeText(getApplicationContext(), "경유지를 입력하세요.", Toast.LENGTH_SHORT).show();
                            isInputComplete = false;
                        } else {
                            isInputComplete = true;
                        }
                    }
                }


                if (isInputComplete) {

                    startText.setText(start);
                    arriveText.setText(finish);

                    // 여기다가 요소들 다 visible 해주면 됨
                    btnMintime.setVisibility(View.VISIBLE);
                    btnMincost.setVisibility(View.VISIBLE);
                    btnMintran.setVisibility(View.VISIBLE);
                    layout_img.setVisibility(View.VISIBLE);
                    layout_inform.setVisibility(View.VISIBLE);

                    driver.setFromTo(start, finish);
                    TransferDij.test.createList();
                    TransferDij.test.addLines();
                    TransferDij.test.addStatons();
                    index = TransferDij.bfs();

                    // 경유지 없을 때
                    if (!isStopOverVisible) {
                        stopoverText.setVisibility(View.INVISIBLE);
                        driver.inputTimeInfor();
                        time = driver.d.convertTime(driver.d.getLowCost(start, finish));
                        auto_time.setText(time[0] + "시간 " + time[1] + "분 " + time[2] + "초 ");

                        reverse = driver.d.getLowCostRoute(start, finish);
                        tv_route.setText(Arrays.toString(reverse));
                        driver.inputPriceInfor();
                        auto_cost.setText(Integer.toString(driver.d.getCost_minTimeRoute(reverse)));
                    }
                    // 경유지 있을 때
                    else {
                        stopoverText.setVisibility(View.VISIBLE);
                        driver.inputTimeInfor();
                        int time_temp = driver.d.getLowCost(start, stopover) + driver.d.getLowCost(stopover, finish);
                        time = driver.d.convertTime(time_temp);
                        auto_time.setText(time[0] + "시간 " + time[1] + "분 " + time[2] + "초 ");
                        reverse = driver.d.getLowCostRoute(start, stopover);
                        String route_temp = Arrays.toString(reverse) + "\n";
                        driver.inputPriceInfor(); // 최소시간 경로의 비용을 구해야 하므로 가중치로 비용을 입력한다.
                        Log.v("reverse", Arrays.toString(reverse));
                        int cost = driver.d.getCost_minTimeRoute(reverse);

                        driver.inputTimeInfor();
                        reverse = driver.d.getLowCostRoute(stopover, finish);
                        route_temp += Arrays.toString(reverse);
                        driver.inputPriceInfor(); // 최소시간 경로의 비용을 구해야 하므로 가중치로 비용을 입력한다.
                        cost += driver.d.getCost_minTimeRoute(reverse);
                        auto_cost.setText(Integer.toString(cost));

                        // 비용 부분
                        tv_route.setText(route_temp);
                        stopoverText.setText(stopover);
                        stopoverText.setVisibility(View.VISIBLE);
                    }
                }
            }
        });


        //경유지 선택
        cb_stopover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();

                switch (view.getId()) {
                    case R.id.cb_stopover:

                        if (checked) {
                            edit_stopover.setVisibility(View.VISIBLE);
                            tv_stop.setVisibility(View.VISIBLE);
                            edit_stopover.setText("");
                            isStopOverVisible = true;
                        } else {
                            edit_stopover.setVisibility(View.INVISIBLE);
                            tv_stop.setVisibility(View.INVISIBLE);
                            edit_stopover.setText("");
                            isStopOverVisible = false;
                        }
                }
            }
        });

        //최소 시간
        btnMintime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (!mode.equals("MinTime")) {
                    mode = "MinTime";
                    btnMintime.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.main_color));
                    btnMincost.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.sub_color4));
                    btnMintran.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.sub_color4));
                    String start = edit_start.getText().toString();
                    String stopover = edit_stopover.getText().toString();
                    String finish = edit_finish.getText().toString();
                    driver.inputTimeInfor();
                    // 경유지 없을 때
                    if (stopover.equals("")) {
                        stopoverText.setVisibility(View.INVISIBLE);
                        time = driver.d.convertTime(driver.d.getLowCost(start, finish));
                        auto_time.setText(time[0] + "시간 " + time[1] + "분 " + time[2] + "초 ");

                        reverse = driver.d.getLowCostRoute(start, finish);
                        tv_route.setText(Arrays.toString(reverse));
                        driver.inputPriceInfor();
                        auto_cost.setText(Integer.toString(driver.d.getCost_minTimeRoute(reverse)));
                    }
                    // 경유지 있을 때
                    else {
                        int time_temp = driver.d.getLowCost(start, stopover) + driver.d.getLowCost(stopover, finish);
                        time = driver.d.convertTime(time_temp);
                        auto_time.setText(time[0] + "시간 " + time[1] + "분 " + time[2] + "초 ");
                        reverse = driver.d.getLowCostRoute(start, stopover);
                        String route_temp = Arrays.toString(reverse) + "\n";
                        driver.inputPriceInfor(); // 최소시간 경로의 비용을 구해야 하므로 가중치로 비용을 입력한다.
                        int cost = driver.d.getCost_minTimeRoute(reverse);

                        driver.inputTimeInfor();
                        reverse = driver.d.getLowCostRoute(stopover, finish);
                        route_temp += Arrays.toString(Arrays.copyOfRange(reverse, 1, reverse.length));

                        driver.inputPriceInfor(); // 최소시간 경로의 비용을 구해야 하므로 가중치로 비용을 입력한다.
                        cost += driver.d.getCost_minTimeRoute(reverse);
                        auto_cost.setText(Integer.toString(cost));
                        // 비용 부분
                        tv_route.setText(route_temp);
                        stopoverText.setText(stopover);
                        stopoverText.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        //최소 비용
        btnMincost.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (!mode.equals("MinCost")) {
                    String start = edit_start.getText().toString();
                    String stopover = edit_stopover.getText().toString();
                    String finish = edit_finish.getText().toString();

                    mode = "MinCost";
                    btnMincost.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.main_color));
                    btnMintime.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.sub_color4));
                    btnMintran.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.sub_color4));

                    driver.inputPriceInfor();
                    // 경유지 없을 때
                    if (stopover.equals("")) {
                        stopoverText.setVisibility(View.INVISIBLE);
                        auto_cost.setText(Integer.toString(driver.d.getLowCost(start, finish)));
                        reverse = driver.d.getLowCostRoute(start, finish);
                        tv_route.setText(Arrays.toString(reverse));

                        driver.inputTimeInfor(); // 최소비용 경로의 시간을 구해야 하므로 가중치로 시간을 입력한다.
                        time = driver.d.convertTime(driver.d.getTime_minPriceRoute(reverse));
                        auto_time.setText(time[0] + "시간 " + time[1] + "분 " + time[2] + "초 ");

                    }
                    // 경유지 있을 때
                    else {
                        reverse = driver.d.getLowCostRoute(start, finish);

                        int t = driver.d.getLowCost(start, stopover) + driver.d.getLowCost(stopover, finish);
                        String temp1 = Integer.toString(t);
                        auto_cost.setText(temp1);

                        reverse = driver.d.getLowCostRoute(start, stopover);
                        String route_temp = Arrays.toString(reverse) + "\n";
                        reverse = driver.d.getLowCostRoute(stopover, finish);
                        route_temp += Arrays.toString(reverse);
                        tv_route.setText(route_temp);

                        driver.inputTimeInfor(); // 최소비용 경로의 시간을 구해야 하므로 가중치로 시간을 입력한다.
                        reverse = driver.d.getLowCostRoute(start, stopover);
                        time = driver.d.convertTime(driver.d.getTime_minPriceRoute(reverse) + driver.d.getTime_minPriceRoute(driver.d.getLowCostRoute(stopover, finish)));
                        auto_time.setText(time[0] + "시간 " + time[1] + "분 " + time[2] + "초 ");

                        stopoverText.setText(stopover);
                        stopoverText.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        //최소 환승
        btnMintran.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (!mode.equals("MinTran")) {
                    String start = edit_start.getText().toString();
                    String stopover = edit_stopover.getText().toString();
                    String finish = edit_finish.getText().toString();

                    mode = "MinTran";
                    btnMintran.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.main_color));
                    btnMincost.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.sub_color4));
                    btnMintime.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.sub_color4));

                    int transSum = 0; // 총 환승 횟수
                    String routeSum = "";

                    driver.inputTimeInfor();
                    // 경유지 없을 때
                    if (stopover.equals("")) {
                        auto_tran.setText(Integer.toString(TransferDij.answer));
                        if (TransferDij.answer == 0) { //환승하지 않을 때

                            // 경로 시간
                            driver.inputTimeInfor(); //가중치로 시간 입력
                            int routeListSize = TransferDij.test.getTransStation(start, finish).size();
                            String route[] = TransferDij.test.getTransStation(start, finish).toArray(new String[routeListSize]);
                            time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
                            auto_time.setText(time[0] + "시간 " + time[1] + "분 " + time[2] + "초 ");

                            // 경로 비용
                            driver.inputPriceInfor(); //비용 정보 입력
                            auto_cost.setText(Integer.toString(driver.d.getCost_minTimeRoute(route)));
                            auto_tran.setText("0");


                        } else if (TransferDij.answer == 1) { //환승 1번 할 때
                            String temp1 = Arrays.asList(TransferDij.test.getTransStation(start, TransferDij.test.getStation(index[1]))).toArray().toString();
                            String temp2 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.test.getStation(index[1]), finish)).toArray().toString();

                            // 출발 - 첫번째
                            String transfer = TransferDij.test.getStation(index[1]); // 환승역
                            String routeTemp = "";

                            driver.inputTimeInfor(); //가중치로 시간 입력
                            int routeListSize = TransferDij.test.getTransStation(start, transfer).size();
                            String route[] = TransferDij.test.getTransStation(start, transfer).toArray(new String[routeListSize]);// 출발지~환승역1 까지의 경로가 저장된 리스트를 배열로 변환
                            routeTemp += Arrays.toString(route) + "\n";
                            int timetemp = driver.d.getTime_minPriceRoute(route);

                            // 출발지~환승지1까지의 비용 출력
                            driver.inputPriceInfor(); //비용 정보 입력
                            //System.out.println("경로 비용: " + driver.d.getCost_minTimeRoute(route) + "원");
                            int cost = driver.d.getCost_minTimeRoute(route);


                            // 환승역1 ~ 도착지까지의 시간
                            driver.inputTimeInfor();
                            routeListSize = TransferDij.test.getTransStation(transfer, finish).size();
                            route = TransferDij.test.getTransStation(transfer, finish).toArray(new String[routeListSize]);
                            routeTemp += Arrays.toString(route);
                            Log.v("route list size: ", routeTemp);
                            timetemp += driver.d.getTime_minPriceRoute(route);
                            time = driver.d.convertTime(timetemp);
                            auto_time.setText(time[0] + "시간 " + time[1] + "분 " + time[2] + "초 ");

                            // 환승지1~도착지까지의 비용 출력
                            driver.inputPriceInfor();
                            cost += driver.d.getCost_minTimeRoute(route);
                            auto_cost.setText(Integer.toString(cost));
                            auto_tran.setText("1");
                            tv_route.setText(routeTemp);

                        } else if (TransferDij.answer == 2) { //환승 2번 할 때
//                                String temp1 = Arrays.asList(TransferDij.test.getTransStation(start, TransferDij.transferStation.get(index[0]))).toString();
//                                String temp2 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.transferStation.get(index[0]), TransferDij.transferStation.get(index[1]))).toString();
//                                String temp3 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.transferStation.get(index[1]), arrive)).toString();
//                                detail_route.setText(temp1 + "\n"+ temp2 + "\n" + temp3);

                            String first_transfer = TransferDij.transferStation.get(index[0]); // 첫번재 환승역
                            String sec_transfer = TransferDij.transferStation.get(index[1]); // 두번째 환승역

                            //System.out.println(start + " -> " + first_transfer);
                            //System.out.print("출발지에서 첫번째 환승지까지의 경로: ");
                            driver.d.printList(TransferDij.test.getTransStation(start, first_transfer));
                            String l = driver.d.getList(TransferDij.test.getTransStation(start, first_transfer));


                            // 출발지 ~ 환승역1까지의 시간
                            driver.inputTimeInfor(); //가중치로 시간 입력
                            int routeListSize = TransferDij.test.getTransStation(start, first_transfer).size();
                            String route[] = TransferDij.test.getTransStation(start, first_transfer).toArray(new String[routeListSize]);// 출발지~환승역1 까지의 경로가 저장된 리스트를 배열로 변환
                            //time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
                            int timetemp = driver.d.getTime_minPriceRoute(route);
                            //System.out.println("경로 시간: " + time[0] + "시간 " + time[1] + "분 " + time[2] + "초");

                            // 출발지 ~ 환승역1 까지의 비용 출력
                            driver.inputPriceInfor();
                            //System.out.println("경로 비용: " + driver.d.getCost_minTimeRoute(route) + "원");
                            int cost = driver.d.getCost_minTimeRoute(route);


                            //System.out.println(first_transfer + " -> " + sec_transfer);
                            //System.out.print("첫번째 환승지에서 두번째 환승지까지의 경로: ");
                            //detail_route.setText(driver.d.getList(TransferDij.test.getTransStation(first_transfer, sec_transfer)));
                            l += driver.d.getList(TransferDij.test.getTransStation(first_transfer, sec_transfer));

                            // 환승역1 ~ 환승역2까지의 시간
                            driver.inputTimeInfor(); //가중치로 시간 입력
                            routeListSize = TransferDij.test.getTransStation(first_transfer, sec_transfer).size();
                            route = TransferDij.test.getTransStation(first_transfer, sec_transfer).toArray(new String[routeListSize]);// 출발지~환승역1 까지의 경로가 저장된 리스트를 배열로 변환
                            timetemp += driver.d.getTime_minPriceRoute(route);
                            //System.out.println("경로 시간: " + time[0] + "시간 " + time[1] + "분 " + time[2] + "초");

                            driver.inputPriceInfor();
                            //System.out.println("경로 비용: " + driver.d.getCost_minTimeRoute(route) + "원");
                            cost += driver.d.getCost_minTimeRoute(route);


                            //System.out.println(sec_transfer + " -> " + end);
                            //System.out.print("두번째 환승지에서 목적지까지의 경로: ");

                            //detail_route.setText(driver.d.getList(TransferDij.test.getTransStation(sec_transfer, arrive)));
                            l += driver.d.getList(TransferDij.test.getTransStation(sec_transfer, finish));
                            tv_route.setText(l);


                            // 환승역1 ~ 환승역2까지의 시간
                            driver.inputTimeInfor(); //가중치로 시간 입력
                            routeListSize = TransferDij.test.getTransStation(sec_transfer, finish).size();
                            route = TransferDij.test.getTransStation(sec_transfer, finish).toArray(new String[routeListSize]);// 출발지~환승역1 까지의 경로가 저장된 리스트를 배열로 변환
                            //time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
                            timetemp += driver.d.getTime_minPriceRoute(route);
                            time = driver.d.convertTime(timetemp);
                            auto_time.setText(time[0] + "시간 " + time[1] + "분 " + time[2] + "초 ");

                            driver.inputPriceInfor();
                            //System.out.println("경로 비용: " + driver.d.getCost_minTimeRoute(route) + "원");
                            cost += driver.d.getCost_minTimeRoute(route);
                            auto_cost.setText(Integer.toString(cost));
                            auto_tran.setText("2");
                        }
                    }
                    // 경유지 있을 때
                    else {
                        // 출발역 -> 경유역  최소환승
                        driver.setFromTo(start, stopover);

                        TransferDij.test.createList();
                        TransferDij.test.addLines();
                        TransferDij.test.addStatons();
                        int[] index1 = TransferDij.bfs();

                        if (TransferDij.answer == 0) { //환승하지 않을 때
                            routeSum += TransferDij.test.getTransStation(start, stopover) + "\n";
                        } else if (TransferDij.answer == 1) { //환승 1번 할 때
                            String temp1 = Arrays.asList(TransferDij.test.getTransStation(start, TransferDij.test.getStation(index1[0]))).toString();
                            String temp2 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.test.getStation(index1[0]), finish)).toString();
                            routeSum += temp1 + "\n" + temp2;
                            transSum++;
                        } else if (TransferDij.answer == 2) { //환승 2번 할 때
                            String temp1 = Arrays.asList(TransferDij.test.getTransStation(start, TransferDij.transferStation.get(index1[0]))).toString();
                            String temp2 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.transferStation.get(index1[0]), TransferDij.transferStation.get(index1[1]))).toString();
                            String temp3 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.transferStation.get(index1[1]), finish)).toString();
                            routeSum += temp1 + "\n" + temp2 + "\n" + temp3;
                            transSum += 2;

                        }


                        // 경유 -> 도착역
                        driver.setFromTo(stopover, finish);
                        driver.inputTimeInfor();
                        TransferDij.test.createList();
                        TransferDij.test.addLines();
                        TransferDij.test.addStatons();
                        int[] index2 = TransferDij.bfs();

                        // 환승 횟수
                        auto_cost.setText(Integer.toString(TransferDij.answer));

                        if (TransferDij.answer == 0) { //환승하지 않을 때
                            //detail_route.setText(Arrays.asList(TransferDij.test.getTransStation(start,arrive)).toArray().toString());
                            //routeSum+= Arrays.asList(TransferDij.test.getTransStation(start,arrive)).toArray().toString()+"\n";
                            routeSum += TransferDij.test.getTransStation(stopover, finish);

                        } else if (TransferDij.answer == 1) { //환승 1번 할 때
                            String temp1 = Arrays.asList(TransferDij.test.getTransStation(start, TransferDij.test.getStation(index2[0]))).toString();
                            String temp2 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.test.getStation(index2[0]), finish)).toString();

                            routeSum += temp1 + "\n" + temp2 + "\n";
                            transSum++;

                        } else if (TransferDij.answer == 2) { //환승 2번 할 때
                            String temp1 = Arrays.asList(TransferDij.test.getTransStation(start, TransferDij.transferStation.get(index2[0]))).toString();
                            String temp2 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.transferStation.get(index2[0]), TransferDij.transferStation.get(index2[1]))).toString();
                            String temp3 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.transferStation.get(index2[1]), finish)).toString();
                            //detail_route.setText(temp1 + "\n"+ temp2 + "\n" + temp3);
                            Log.v("route temp", temp1);
                            routeSum += temp1 + "\n" + temp2 + "\n" + temp3 + "\n";
                            transSum += 2;


                        }
                        auto_tran.setText(Integer.toString(transSum));
                        tv_route.setText(routeSum);
                    }
                }
            }
        });

        btnArrivetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnArrivetime:
                        Toast.makeText(getApplicationContext(), "현재시각: " + getTime() + "\n예상 도착시간: " + getCTime(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.fab_home:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.fab_setting:
                this.registerForContextMenu(btnSetting);
                openContextMenu(btnSetting);
                unregisterForContextMenu(btnSetting);
                break;
            case R.id.fab_search:
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.fab_star:
                intent = new Intent(this, BookmarkActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.fab_road:
                intent = new Intent(this, RoadActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}