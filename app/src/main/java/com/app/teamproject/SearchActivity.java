package com.app.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    View layout_inform;
    String mode = "MinTime";



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
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

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
            @Override
            public void onClick(View view) {

                String start = edit_start.getText().toString();
                String stopover = edit_stopover.getText().toString();
                String finish = edit_finish.getText().toString();

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

                    driver.inputTimeInfor();
                    if (stopover.equals("")) {
                        time = driver.d.convertTime(driver.d.getLowCost(start, finish));
                        auto_time.setText(Integer.toString(time[0]) + "시간 " + Integer.toString(time[1]) + "분 " + Integer.toString(time[0]) + "초 ");

                        reverse = driver.d.getLowCostRoute(start, finish);
                        tv_route.setText(Arrays.toString(reverse));
                        driver.inputPriceInfor();
                        auto_cost.setText(Integer.toString(driver.d.getCost_minTimeRoute(reverse)));
                    } else {
                        int time_temp = driver.d.getLowCost(start, stopover) + driver.d.getLowCost(stopover, finish);
                        time = driver.d.convertTime(time_temp);
                        auto_time.setText(Integer.toString(time[0]) + "시간 " + Integer.toString(time[1]) + "분 " + Integer.toString(time[0]) + "초 ");
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
                            stopoverText.setVisibility(View.VISIBLE);
                            isStopOverVisible = true;
                        } else {
                            edit_stopover.setVisibility(View.INVISIBLE);
                            tv_stop.setVisibility(View.INVISIBLE);
                            stopoverText.setVisibility(View.INVISIBLE);
                            stopoverText.setText("");
                            isStopOverVisible = false;
                        }
                }
            }
        });

        btnMintime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mode.equals("MinTime")) {
                    mode = "MinTime";
                    String start = edit_start.getText().toString();
                    String stopover = edit_stopover.getText().toString();
                    String finish = edit_finish.getText().toString();
                    if (stopover.equals("")) {
                        time = driver.d.convertTime(driver.d.getLowCost(start, finish));
                        auto_time.setText(Integer.toString(time[0]) + "시간 " + Integer.toString(time[1]) + "분 " + Integer.toString(time[0]) + "초 ");

                        reverse = driver.d.getLowCostRoute(start, finish);
                        tv_route.setText(Arrays.toString(reverse));
                        driver.inputPriceInfor();
                        auto_cost.setText(Integer.toString(driver.d.getCost_minTimeRoute(reverse)));
                    } else {
                        int time_temp = driver.d.getLowCost(start, stopover) + driver.d.getLowCost(stopover, finish);
                        time = driver.d.convertTime(time_temp);
                        auto_time.setText(Integer.toString(time[0]) + "시간 " + Integer.toString(time[1]) + "분 " + Integer.toString(time[0]) + "초 ");
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

        //최소 시간
        btnMincost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mode.equals("MinCost")) {
                    mode = "MinCost";
                    String start = edit_start.getText().toString();
                    String stopover = edit_stopover.getText().toString();
                    String finish = edit_finish.getText().toString();
                    if (stopover.equals("")) {
                        auto_cost.setText(Integer.toString(driver.d.getLowCost(start, finish)));
                        reverse = driver.d.getLowCostRoute(start, finish);
                        tv_route.setText(Arrays.toString(reverse));

                        driver.inputTimeInfor(); // 최소비용 경로의 시간을 구해야 하므로 가중치로 시간을 입력한다.
                        time = driver.d.convertTime(driver.d.getTime_minPriceRoute(reverse));
                        auto_time.setText(Integer.toString(time[0]) + "시간 " + Integer.toString(time[1]) + "분 " + Integer.toString(time[0]) + "초 ");

                    } else {
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
                        auto_time.setText(Integer.toString(time[0]) + "시간 " + Integer.toString(time[1]) + "분 " + Integer.toString(time[0]) + "초 ");

                        stopoverText.setText(stopover);
                        stopoverText.setVisibility(View.VISIBLE);
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