package com.app.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    FloatingActionButton btnSubway, btnSetting, btnHome, btnRoad, btnStar, btnSearch;
    Boolean isAllFabsVisible;
    EditText edit_start, edit_stopover, edit_finish;
    ImageView btn_search, btn_change;
    Button btnArrivetime , btnMintime, btnMincost, btnMintran;
    CheckBox cb_stopover;
    TextView tv_stop, tv_route, auto_time, auto_cost, auto_tran, startText, stopoverText, arriveText;

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("hh:mm:ss");

    Driver driver = new Driver();
    int [] index;

    String start;
    String arrive;
    String layover;

    String[] reverse;
    int[] time;


    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
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

        startText = findViewById(R.id.startText);
        stopoverText = findViewById(R.id.stopoverText);
        arriveText = findViewById(R.id.arriveText);

        tv_stop = findViewById(R.id.tv_stop);
        tv_route = findViewById(R.id.tv_test_route);

        auto_cost = findViewById(R.id.auto_cost);
        auto_time = findViewById(R.id.auto_time);
        auto_tran = findViewById(R.id.auto_transfer);



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

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
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

                if(start.length() == 0){
                    Toast.makeText(getApplicationContext(), "출발역을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                if (finish.length() == 0){
                    Toast.makeText(getApplicationContext(), "도착역을 입력하세요.", Toast.LENGTH_SHORT).show();
                }

                startText.setText(start);
                arriveText.setText(finish);
                driver.setFromTo(start, finish);

                TransferDij.test.createList();
                TransferDij.test.addLines();
                TransferDij.test.addStatons();
                index = TransferDij.bfs();

                driver.inputTimeInfor();
                if (stopover.equals("")){
                    time = driver.d.convertTime(driver.d.getLowCost(start,finish));
                    auto_time.setText(Integer.toString(time[0])+"시간 "+Integer.toString(time[1])+"분 "+Integer.toString(time[0])+"초 ");

                    reverse = driver.d.getLowCostRoute(start, finish);
                    tv_route.setText(Arrays.toString(reverse));
                    driver.inputPriceInfor();
                    auto_cost.setText(Integer.toString(driver.d.getCost_minTimeRoute(reverse)));
                }
                else{
                    int time_temp = driver.d.getLowCost(start,stopover)+ driver.d.getLowCost(stopover,finish);
                    time = driver.d.convertTime(time_temp);
                    auto_time.setText(Integer.toString(time[0])+"시간 "+Integer.toString(time[1])+"분 "+Integer.toString(time[0])+"초 ");
                    reverse = driver.d.getLowCostRoute(start,stopover);
                    String route_temp =Arrays.toString(reverse)+"\n";
                    driver.inputPriceInfor(); // 최소시간 경로의 비용을 구해야 하므로 가중치로 비용을 입력한다.

                    int cost = driver.d.getCost_minTimeRoute(reverse);
                    driver.inputTimeInfor();
                    reverse = driver.d.getLowCostRoute(stopover,finish);
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
        });

        //경유지 선택
        cb_stopover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();

                switch (view.getId()){
                    case R.id.cb_stopover:

                        if (checked){
                            edit_stopover.setVisibility(View.VISIBLE);
                            tv_stop.setVisibility(View.VISIBLE);
                        }else{
                            edit_stopover.setVisibility(View.INVISIBLE);
                            tv_stop.setVisibility(View.INVISIBLE);
                        }
                }
            }
        });

        btnArrivetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnArrivetime:
                        Toast.makeText(getApplicationContext(), "현재시각: " + getTime() + "\n예상 도착시간: " + getTime() , Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        Intent intent;
        driver.setFromTo(start,arrive);

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