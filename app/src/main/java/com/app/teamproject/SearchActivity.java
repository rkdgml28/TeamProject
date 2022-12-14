package com.app.teamproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton btnSubway, btnSetting, btnHome, btnRoad, btnStar, btnSearch, btnLang;
    Boolean isAllFabsVisible, isStopOverVisible, isInputComplete;
    EditText edit_start, edit_stopover, edit_finish;
    ImageView btn_search, btn_change, img_search_start, img_search_finish;
    Button btnArrivetime, btnMintime, btnMincost, btnMintran;
    CheckBox cb_stopover;
    TextView tv_stop, tv_route, tv_tran, auto_time, auto_cost, auto_tran, startText, stopoverText, arriveText;
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
    List<String> list = Arrays.asList(stations);

    Driver driver = new Driver();
    int[] index;

//    String start;
//    String arrive;
//    String layover;

    String[] reverse;
    int[] time;

    //???????????? ???????????? ??????
    private String getTime() {
        String today = null;

        Date date = new Date();
        // ???????????? ( ????????? ?????????)
        SimpleDateFormat sdformat = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        today = sdformat.format(cal.getTime());

        return today;
    }

    //???????????? ????????? ??????
    private String getCTime() {
        String today = null;

        Date date = new Date();
        // ???????????? ( ????????? ?????????)
        SimpleDateFormat sdformat = new SimpleDateFormat("HH:mm:ss");
        // Java ?????? ?????????
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, time[1]);
        cal.add(Calendar.HOUR, time[0]);
        cal.add(Calendar.SECOND, time[2]);

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

        img_search_start = findViewById(R.id.img_search_start);
        img_search_finish = findViewById(R.id.img_search_finish);

        startText = findViewById(R.id.startText);
        stopoverText = findViewById(R.id.stopoverText);
        arriveText = findViewById(R.id.arriveText);

        tv_stop = findViewById(R.id.tv_stop);
        tv_route = findViewById(R.id.tv_test_route);
        tv_tran = findViewById(R.id.tv_transfer);

        auto_cost = findViewById(R.id.auto_cost);
        auto_time = findViewById(R.id.auto_time);
        auto_tran = findViewById(R.id.auto_transfer);


        AutoCompleteTextView auto_start = findViewById(R.id.edit_start);
        AutoCompleteTextView auto_stop = findViewById(R.id.edit_stopover);
        AutoCompleteTextView auto_finish = findViewById(R.id.edit_finish);

        auto_start.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stations));
        auto_stop.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stations));
        auto_finish.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stations));

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            String start_station = intent.getExtras().getString("start_station");
            String finish_station = intent.getExtras().getString("finish_station");
            if (start_station != null) {
                edit_start.setText(start_station);
            }
            if (finish_station != null) {
                edit_finish.setText(finish_station);
            }
        }

        isStopOverVisible = false;
        isInputComplete = false;

        // FAB button
        btnSubway = findViewById(R.id.fab_subway);
        btnSetting = findViewById(R.id.fab_setting);
        btnHome = findViewById(R.id.fab_home);
        btnRoad = findViewById(R.id.fab_road);
        btnSearch = findViewById(R.id.fab_search);
        btnStar = findViewById(R.id.fab_star);
        btnLang = findViewById(R.id.fab_lang);

        btnSetting.setVisibility(View.GONE);
        btnHome.setVisibility(View.GONE);
        btnStar.setVisibility(View.GONE);
        btnSearch.setVisibility(View.GONE);
        btnRoad.setVisibility(View.GONE);
        btnLang.setVisibility(View.GONE);

        isAllFabsVisible = false;

        //????????? ???????????? ????????????
        btnMintime.setVisibility(View.INVISIBLE);
        btnMincost.setVisibility(View.INVISIBLE);
        btnMintran.setVisibility(View.INVISIBLE);
        auto_tran.setVisibility(View.INVISIBLE);
        tv_tran.setVisibility(View.INVISIBLE);

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
                btnLang.show();
                isAllFabsVisible = true;
            } else {
                btnSetting.hide();
                btnHome.hide();
                btnStar.hide();
                btnSearch.hide();
                btnRoad.hide();
                btnLang.hide();
                isAllFabsVisible = false;
            }
        });

        btnHome.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnStar.setOnClickListener(this);
        btnRoad.setOnClickListener(this);
        btnLang.setOnClickListener(this);

        btnMincost.setOnClickListener(this);
        btnMintime.setOnClickListener(this);
        btnMintran.setOnClickListener(this);

        int color1 = (this).getResources().getColor(R.color.line1);
        int color2 = (this).getResources().getColor(R.color.line2);
        int color3 = (this).getResources().getColor(R.color.line3);
        int color4 = (this).getResources().getColor(R.color.line4);
        int color5 = (this).getResources().getColor(R.color.line5);
        int color6 = (this).getResources().getColor(R.color.line6);
        int color7 = (this).getResources().getColor(R.color.line7);
        int color8 = (this).getResources().getColor(R.color.line8);
        int color9 = (this).getResources().getColor(R.color.line9);


        //????????? ????????? ????????? ??????
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


        // ?????? ?????? ??????
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
                    Toast.makeText(getApplicationContext(), "???????????? ???????????????.", Toast.LENGTH_SHORT).show();
                    isInputComplete = false;
                } else {
                    if (finish.length() == 0) {
                        Toast.makeText(getApplicationContext(), "???????????? ???????????????.", Toast.LENGTH_SHORT).show();
                        isInputComplete = false;
                    } else {
                        if (isStopOverVisible && stopover.length() == 0) {
                            Toast.makeText(getApplicationContext(), "???????????? ???????????????.", Toast.LENGTH_SHORT).show();
                            isInputComplete = false;
                        }
                        else if (!list.contains(start)) {
                            Toast.makeText(getApplicationContext(), "?????? ????????????. (?????????)", Toast.LENGTH_SHORT).show();
                        }
                        else if (!list.contains(finish)){
                            Toast.makeText(getApplicationContext(), "?????? ????????????. (?????????)", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            isInputComplete = true;
                        }
                    }
                }


                if (isInputComplete) {

                    int line_start, line_finish;

                    int numstart = Integer.parseInt(start);
                    line_start = numstart / 100;
                    int numarrive = Integer.parseInt(finish);
                    line_finish = numarrive / 100;

                    if (line_start == 1) {
                        img_search_start.setColorFilter(color1);
                    }
                    if (line_start == 2) {
                        img_search_start.setColorFilter(color2);
                    }
                    if (line_start == 3) {
                        img_search_start.setColorFilter(color3);
                    }
                    if (line_start == 4) {
                        img_search_start.setColorFilter(color4);
                    }
                    if (line_start == 5) {
                        img_search_start.setColorFilter(color5);
                    }
                    if (line_start == 6) {
                        img_search_start.setColorFilter(color6);
                    }
                    if (line_start == 7) {
                        img_search_start.setColorFilter(color7);
                    }
                    if (line_start == 8) {
                        img_search_start.setColorFilter(color8);
                    }
                    if (line_start == 9) {
                        img_search_start.setColorFilter(color9);
                    }

                    if (line_finish == 1) {
                        img_search_finish.setColorFilter(color1);
                    }
                    if (line_finish == 2) {
                        img_search_finish.setColorFilter(color2);
                    }
                    if (line_finish == 3) {
                        img_search_finish.setColorFilter(color3);
                    }
                    if (line_finish == 4) {
                        img_search_finish.setColorFilter(color4);
                    }
                    if (line_finish == 5) {
                        img_search_finish.setColorFilter(color5);
                    }
                    if (line_finish == 6) {
                        img_search_finish.setColorFilter(color6);
                    }
                    if (line_finish == 7) {
                        img_search_finish.setColorFilter(color7);
                    }
                    if (line_finish == 8) {
                        img_search_finish.setColorFilter(color8);
                    }
                    if (line_finish == 9) {
                        img_search_finish.setColorFilter(color9);
                    }

                    startText.setText(start);
                    arriveText.setText(finish);

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

                    // ????????? ?????? ???
                    if (!isStopOverVisible) {
                        stopoverText.setVisibility(View.INVISIBLE);
                        driver.inputTimeInfor();
                        time = driver.d.convertTime(driver.d.getLowCost(start, finish));
                        auto_time.setText(time[0] + "?????? " + time[1] + "??? " + time[2] + "??? ");

                        reverse = driver.d.getLowCostRoute(start, finish);
                        tv_route.setText(Arrays.toString(reverse));
                        driver.inputPriceInfor();
                        auto_cost.setText(Integer.toString(driver.d.getCost_minTimeRoute(reverse)));
                    }
                    // ????????? ?????? ???
                    else {
                        if (!list.contains(stopover)){
                            Toast.makeText(getApplicationContext(), "?????? ????????????. (?????????)", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            stopoverText.setVisibility(View.VISIBLE);
                            driver.inputTimeInfor();
                            int time_temp = driver.d.getLowCost(start, stopover) + driver.d.getLowCost(stopover, finish);
                            time = driver.d.convertTime(time_temp);
                            auto_time.setText(time[0] + "?????? " + time[1] + "??? " + time[2] + "??? ");
                            reverse = driver.d.getLowCostRoute(start, stopover);
                            String route_temp = Arrays.toString(reverse) + "\n";
                            driver.inputPriceInfor(); // ???????????? ????????? ????????? ????????? ????????? ???????????? ????????? ????????????.
                            Log.v("reverse", Arrays.toString(reverse));
                            int cost = driver.d.getCost_minTimeRoute(reverse);

                            driver.inputTimeInfor();
                            reverse = driver.d.getLowCostRoute(stopover, finish);
                            route_temp += Arrays.toString(reverse);
                            driver.inputPriceInfor(); // ???????????? ????????? ????????? ????????? ????????? ???????????? ????????? ????????????.
                            cost += driver.d.getCost_minTimeRoute(reverse);
                            auto_cost.setText(Integer.toString(cost));

                            // ?????? ??????
                            tv_route.setText(route_temp);
                            stopoverText.setText(stopover);
                            stopoverText.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });


        //????????? ??????
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

        //?????? ??????
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

                    tv_tran.setVisibility(View.INVISIBLE);
                    auto_tran.setVisibility(View.INVISIBLE);

                    driver.inputTimeInfor();
                    // ????????? ?????? ???
                    if (stopover.equals("")) {
                        stopoverText.setVisibility(View.INVISIBLE);
                        time = driver.d.convertTime(driver.d.getLowCost(start, finish));
                        auto_time.setText(time[0] + "?????? " + time[1] + "??? " + time[2] + "??? ");

                        reverse = driver.d.getLowCostRoute(start, finish);
                        tv_route.setText(Arrays.toString(reverse));
                        driver.inputPriceInfor();
                        auto_cost.setText(Integer.toString(driver.d.getCost_minTimeRoute(reverse)));
                    }
                    // ????????? ?????? ???
                    else {
                        if (!list.contains(stopover)){
                            Toast.makeText(getApplicationContext(), "?????? ????????????. (?????????)", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //int time_temp = driver.d.getLowCost(start, stopover) + driver.d.getLowCost(stopover, finish);
                            time = driver.d.convertTime(driver.d.getLowCost(start, stopover) + driver.d.getLowCost(stopover, finish));
                            Log.v("mintime time", Arrays.toString(time));
                            auto_time.setText(time[0] + "?????? " + time[1] + "??? " + time[2] + "??? ");
                            reverse = driver.d.getLowCostRoute(start, stopover);
                            String route_temp = Arrays.toString(reverse) + "\n";
                            driver.inputPriceInfor(); // ???????????? ????????? ????????? ????????? ????????? ???????????? ????????? ????????????.
                            int cost = driver.d.getCost_minTimeRoute(reverse);

                            driver.inputTimeInfor();
                            reverse = driver.d.getLowCostRoute(stopover, finish);
                            route_temp += Arrays.toString(Arrays.copyOfRange(reverse, 1, reverse.length));

                            driver.inputPriceInfor(); // ???????????? ????????? ????????? ????????? ????????? ???????????? ????????? ????????????.
                            cost += driver.d.getCost_minTimeRoute(reverse);
                            auto_cost.setText(Integer.toString(cost));
                            // ?????? ??????
                            tv_route.setText(route_temp);
                            stopoverText.setText(stopover);
                            stopoverText.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        //?????? ??????
        btnMincost.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (!mode.equals("MinCost")) {
                    String start = edit_start.getText().toString();
                    String stopover = edit_stopover.getText().toString();
                    String finish = edit_finish.getText().toString();

                    tv_tran.setVisibility(View.INVISIBLE);
                    auto_tran.setVisibility(View.INVISIBLE);

                    mode = "MinCost";
                    btnMincost.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.main_color));
                    btnMintime.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.sub_color4));
                    btnMintran.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.sub_color4));

                    driver.inputPriceInfor();
                    // ????????? ?????? ???
                    if (stopover.equals("")) {
                        stopoverText.setVisibility(View.INVISIBLE);
                        auto_cost.setText(Integer.toString(driver.d.getLowCost(start, finish)));
                        reverse = driver.d.getLowCostRoute(start, finish);
                        tv_route.setText(Arrays.toString(reverse));

                        driver.inputTimeInfor(); // ???????????? ????????? ????????? ????????? ????????? ???????????? ????????? ????????????.
                        time = driver.d.convertTime(driver.d.getTime_minPriceRoute(reverse));
                        auto_time.setText(time[0] + "?????? " + time[1] + "??? " + time[2] + "??? ");

                    }
                    // ????????? ?????? ???
                    else {
                        if (!list.contains(stopover)){
                            Toast.makeText(getApplicationContext(), "?????? ????????????. (?????????)", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            int t = driver.d.getLowCost(start, stopover) + driver.d.getLowCost(stopover, finish);
                            String temp1 = Integer.toString(t);
                            auto_cost.setText(temp1);

                            reverse = driver.d.getLowCostRoute(start, stopover);
                            String route_temp = Arrays.toString(reverse) + "\n";
                            reverse = driver.d.getLowCostRoute(stopover, finish);
                            route_temp += Arrays.toString(reverse);
                            tv_route.setText(route_temp);

                            String[] r1 = driver.d.getLowCostRoute(start, stopover);
                            String[] r2 = driver.d.getLowCostRoute(stopover, finish);

                            driver.inputTimeInfor(); // ???????????? ????????? ????????? ????????? ????????? ???????????? ????????? ????????????.

                            time = driver.d.convertTime(driver.d.getTime_minPriceRoute(r1) + driver.d.getTime_minPriceRoute(r2));
                            Log.v("mincost time", String.valueOf(driver.d.getTime_minPriceRoute(reverse)));
                            auto_time.setText(time[0] + "?????? " + time[1] + "??? " + time[2] + "??? ");

                            stopoverText.setText(stopover);
                            stopoverText.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        //?????? ??????
        btnMintran.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (!mode.equals("MinTran")) {
                    String start = edit_start.getText().toString();
                    String stopover = edit_stopover.getText().toString();
                    String finish = edit_finish.getText().toString();

                    tv_tran.setVisibility(View.VISIBLE);
                    auto_tran.setVisibility(View.VISIBLE);

                    mode = "MinTran";
                    btnMintran.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.main_color));
                    btnMincost.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.sub_color4));
                    btnMintime.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.sub_color4));

                    int transSum = 0; // ??? ?????? ??????
                    String routeSum = "";

                    driver.inputTimeInfor();
                    // ????????? ?????? ???
                    if (stopover.equals("")) {
                        auto_tran.setText(Integer.toString(TransferDij.answer));
                        if (TransferDij.answer == 0) { //???????????? ?????? ???

                            // ?????? ??????
                            driver.inputTimeInfor(); //???????????? ?????? ??????
                            int routeListSize = TransferDij.test.getTransStation(start, finish).size();
                            String route[] = TransferDij.test.getTransStation(start, finish).toArray(new String[routeListSize]);
                            time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
                            auto_time.setText(time[0] + "?????? " + time[1] + "??? " + time[2] + "??? ");

                            // ?????? ??????
                            driver.inputPriceInfor(); //?????? ?????? ??????
                            auto_cost.setText(Integer.toString(driver.d.getCost_minTimeRoute(route)));
                            auto_tran.setText("0");


                        } else if (TransferDij.answer == 1) { //?????? 1??? ??? ???
                            String temp1 = Arrays.asList(TransferDij.test.getTransStation(start, TransferDij.test.getStation(index[1]))).toArray().toString();
                            String temp2 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.test.getStation(index[1]), finish)).toArray().toString();

                            // ?????? - ?????????
                            String transfer = TransferDij.test.getStation(index[1]); // ?????????
                            String routeTemp = "";

                            driver.inputTimeInfor(); //???????????? ?????? ??????
                            int routeListSize = TransferDij.test.getTransStation(start, transfer).size();
                            String route[] = TransferDij.test.getTransStation(start, transfer).toArray(new String[routeListSize]);// ?????????~?????????1 ????????? ????????? ????????? ???????????? ????????? ??????
                            routeTemp += Arrays.toString(route) + "\n";
                            int timetemp = driver.d.getTime_minPriceRoute(route);

                            // ?????????~?????????1????????? ?????? ??????
                            driver.inputPriceInfor(); //?????? ?????? ??????
                            //System.out.println("?????? ??????: " + driver.d.getCost_minTimeRoute(route) + "???");
                            int cost = driver.d.getCost_minTimeRoute(route);


                            // ?????????1 ~ ?????????????????? ??????
                            driver.inputTimeInfor();
                            routeListSize = TransferDij.test.getTransStation(transfer, finish).size();
                            route = TransferDij.test.getTransStation(transfer, finish).toArray(new String[routeListSize]);
                            routeTemp += Arrays.toString(route);
                            Log.v("route list size: ", routeTemp);
                            timetemp += driver.d.getTime_minPriceRoute(route);
                            time = driver.d.convertTime(timetemp);
                            auto_time.setText(time[0] + "?????? " + time[1] + "??? " + time[2] + "??? ");

                            // ?????????1~?????????????????? ?????? ??????
                            driver.inputPriceInfor();
                            cost += driver.d.getCost_minTimeRoute(route);
                            auto_cost.setText(Integer.toString(cost));
                            auto_tran.setText("1");
                            tv_route.setText(routeTemp);

                        } else if (TransferDij.answer == 2) { //?????? 2??? ??? ???
//                                String temp1 = Arrays.asList(TransferDij.test.getTransStation(start, TransferDij.transferStation.get(index[0]))).toString();
//                                String temp2 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.transferStation.get(index[0]), TransferDij.transferStation.get(index[1]))).toString();
//                                String temp3 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.transferStation.get(index[1]), arrive)).toString();
//                                detail_route.setText(temp1 + "\n"+ temp2 + "\n" + temp3);

                            String first_transfer = TransferDij.transferStation.get(index[0]); // ????????? ?????????
                            String sec_transfer = TransferDij.transferStation.get(index[1]); // ????????? ?????????

                            //System.out.println(start + " -> " + first_transfer);
                            //System.out.print("??????????????? ????????? ?????????????????? ??????: ");
                            driver.d.printList(TransferDij.test.getTransStation(start, first_transfer));
                            String l = driver.d.getList(TransferDij.test.getTransStation(start, first_transfer));


                            // ????????? ~ ?????????1????????? ??????
                            driver.inputTimeInfor(); //???????????? ?????? ??????
                            int routeListSize = TransferDij.test.getTransStation(start, first_transfer).size();
                            String route[] = TransferDij.test.getTransStation(start, first_transfer).toArray(new String[routeListSize]);// ?????????~?????????1 ????????? ????????? ????????? ???????????? ????????? ??????
                            //time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
                            int timetemp = driver.d.getTime_minPriceRoute(route);
                            //System.out.println("?????? ??????: " + time[0] + "?????? " + time[1] + "??? " + time[2] + "???");

                            // ????????? ~ ?????????1 ????????? ?????? ??????
                            driver.inputPriceInfor();
                            //System.out.println("?????? ??????: " + driver.d.getCost_minTimeRoute(route) + "???");
                            int cost = driver.d.getCost_minTimeRoute(route);


                            //System.out.println(first_transfer + " -> " + sec_transfer);
                            //System.out.print("????????? ??????????????? ????????? ?????????????????? ??????: ");
                            //detail_route.setText(driver.d.getList(TransferDij.test.getTransStation(first_transfer, sec_transfer)));
                            l += driver.d.getList(TransferDij.test.getTransStation(first_transfer, sec_transfer));

                            // ?????????1 ~ ?????????2????????? ??????
                            driver.inputTimeInfor(); //???????????? ?????? ??????
                            routeListSize = TransferDij.test.getTransStation(first_transfer, sec_transfer).size();
                            route = TransferDij.test.getTransStation(first_transfer, sec_transfer).toArray(new String[routeListSize]);// ?????????~?????????1 ????????? ????????? ????????? ???????????? ????????? ??????
                            timetemp += driver.d.getTime_minPriceRoute(route);
                            //System.out.println("?????? ??????: " + time[0] + "?????? " + time[1] + "??? " + time[2] + "???");

                            driver.inputPriceInfor();
                            //System.out.println("?????? ??????: " + driver.d.getCost_minTimeRoute(route) + "???");
                            cost += driver.d.getCost_minTimeRoute(route);


                            //System.out.println(sec_transfer + " -> " + end);
                            //System.out.print("????????? ??????????????? ?????????????????? ??????: ");

                            //detail_route.setText(driver.d.getList(TransferDij.test.getTransStation(sec_transfer, arrive)));
                            l += driver.d.getList(TransferDij.test.getTransStation(sec_transfer, finish));
                            tv_route.setText(l);


                            // ?????????1 ~ ?????????2????????? ??????
                            driver.inputTimeInfor(); //???????????? ?????? ??????
                            routeListSize = TransferDij.test.getTransStation(sec_transfer, finish).size();
                            route = TransferDij.test.getTransStation(sec_transfer, finish).toArray(new String[routeListSize]);// ?????????~?????????1 ????????? ????????? ????????? ???????????? ????????? ??????
                            //time = driver.d.convertTime(driver.d.getTime_minPriceRoute(route));
                            timetemp += driver.d.getTime_minPriceRoute(route);
                            time = driver.d.convertTime(timetemp);
                            auto_time.setText(time[0] + "?????? " + time[1] + "??? " + time[2] + "??? ");

                            driver.inputPriceInfor();
                            //System.out.println("?????? ??????: " + driver.d.getCost_minTimeRoute(route) + "???");
                            cost += driver.d.getCost_minTimeRoute(route);
                            auto_cost.setText(Integer.toString(cost));
                            auto_tran.setText("2");
                        }
                    }
                    // ????????? ?????? ???
                    else {
                        if (!list.contains(stopover)){
                            Toast.makeText(getApplicationContext(), "?????? ????????????. (?????????)", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            // ????????? -> ?????????  ????????????
                            driver.setFromTo(start, stopover);

                            TransferDij.test.createList();
                            TransferDij.test.addLines();
                            TransferDij.test.addStatons();
                            int[] index1 = TransferDij.bfs();

                            if (TransferDij.answer == 0) { //???????????? ?????? ???
                                routeSum += TransferDij.test.getTransStation(start, stopover) + "\n";
                            } else if (TransferDij.answer == 1) { //?????? 1??? ??? ???
                                String temp1 = Arrays.asList(TransferDij.test.getTransStation(start, TransferDij.test.getStation(index1[0]))).toString();
                                String temp2 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.test.getStation(index1[0]), finish)).toString();
                                routeSum += temp1 + "\n" + temp2;
                                transSum++;
                            } else if (TransferDij.answer == 2) { //?????? 2??? ??? ???
                                String temp1 = Arrays.asList(TransferDij.test.getTransStation(start, TransferDij.transferStation.get(index1[0]))).toString();
                                String temp2 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.transferStation.get(index1[0]), TransferDij.transferStation.get(index1[1]))).toString();
                                String temp3 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.transferStation.get(index1[1]), finish)).toString();
                                routeSum += temp1 + "\n" + temp2 + "\n" + temp3;
                                transSum += 2;

                            }


                            // ?????? -> ?????????
                            driver.setFromTo(stopover, finish);
                            driver.inputTimeInfor();
                            TransferDij.test.createList();
                            TransferDij.test.addLines();
                            TransferDij.test.addStatons();
                            int[] index2 = TransferDij.bfs();

                            // ?????? ??????
                            auto_cost.setText(Integer.toString(TransferDij.answer));

                            if (TransferDij.answer == 0) { //???????????? ?????? ???
                                //detail_route.setText(Arrays.asList(TransferDij.test.getTransStation(start,arrive)).toArray().toString());
                                //routeSum+= Arrays.asList(TransferDij.test.getTransStation(start,arrive)).toArray().toString()+"\n";
                                routeSum += TransferDij.test.getTransStation(stopover, finish);

                            } else if (TransferDij.answer == 1) { //?????? 1??? ??? ???
                                String temp1 = Arrays.asList(TransferDij.test.getTransStation(start, TransferDij.test.getStation(index2[0]))).toString();
                                String temp2 = Arrays.asList(TransferDij.test.getTransStation(TransferDij.test.getStation(index2[0]), finish)).toString();

                                routeSum += temp1 + "\n" + temp2 + "\n";
                                transSum++;

                            } else if (TransferDij.answer == 2) { //?????? 2??? ??? ???
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
            }
        });

        btnArrivetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnArrivetime:
                        Toast.makeText(getApplicationContext(), "????????????: " + getTime() + "\n?????? ????????????: " + getCTime(), Toast.LENGTH_SHORT).show();
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
                intent = new Intent(this, InquiryActivity.class);
                startActivity(intent);
                finish();
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
            case R.id.fab_lang:
                intent = new Intent(this, SetLanguageActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}