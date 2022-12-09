package com.app.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StationActivity  extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton btnSubway, btnSetting, btnHome, btnRoad, btnStar, btnSearch;
    Boolean isAllFabsVisible;
    TextView sta_search, sta_prev, sta_next, search_station, what_line, tv_inform, prestation, nextstation;
    ImageView img_sta, img_prev, img_next;
    View block2, block3, block4, block5;

    int numint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);

        btnSubway = findViewById(R.id.fab_subway);

        btnSetting = findViewById(R.id.fab_setting);
        btnHome = findViewById(R.id.fab_home);
        btnRoad = findViewById(R.id.fab_road);
        btnSearch = findViewById(R.id.fab_search);
        btnStar = findViewById(R.id.fab_star);

        sta_search = findViewById(R.id.sta_search);
        sta_prev = findViewById(R.id.sta_prev);
        sta_next = findViewById(R.id.sta_next);
        search_station = findViewById(R.id.search_station);
        what_line = findViewById(R.id.what_line);
        tv_inform = findViewById(R.id.tv_inform);

        block2 = findViewById(R.id.block2);
        block3 = findViewById(R.id.block3);
        block4 = findViewById(R.id.block4);
        block5 = findViewById(R.id.block5);

        prestation = findViewById(R.id.prestation);
        nextstation = findViewById(R.id.nextstation);

        img_next = findViewById(R.id.img_next);
        img_sta = findViewById(R.id.img_sta);
        img_prev = findViewById(R.id.img_prev);

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

        Intent intent = getIntent();

        String station = intent.getExtras().getString("station");
        sta_search.setText(station);
        search_station.setText(station);

        //검색역 정보띄우기
        numint = Integer.parseInt(station);
        int prev, next, line;

        line = numint/100;
        next = numint + 1;
        prev = numint - 1;

        //이전역이
        if (prev == 100){
            prev = 123;
        }
        if (prev == 200){
            prev = 101;
        }
        if (prev == 300){
            prev = 207;
        }
        if (prev == 304){
            prev = 123;
        }
        if (prev == 400){
            prev = 104;
        }
        if (prev == 401){
            prev = 307;
        }
        if (prev == 407){
            prev = 115;
        }
        if (prev == 500){
            prev = 209;
        }
        if (prev == 506){
            prev = 403;
        }
        if (prev == 600){
            prev = 622;
        }
        if (prev == 602){
            prev = 121;
        }
        if (prev == 606){
            next = 116;
        }
        if (prev == 609){
            prev = 412;
        }
        if (prev == 616){
            next = 417;
        }
        if (prev == 700){
            prev = 601;
        }
        if (prev == 706){
            prev = 416;
        }
        if (prev == 800){
            prev = 113;
        }
        if (prev == 803){
            prev = 608;
        }
        if (prev == 900){
            prev = 112;
        }
        //다음역이
        if (next == 124){
            next = 101;
        }
        if (next == 218){
            next = 0;
        }
        if (next == 305){
            next = 123;
        }
        if (next == 308){
            next = 107;
        }
        if (next == 402){
            next = 307;
        }
        if (next == 408){
            next = 115;
        }
        if (next == 418){
            next = 216;
        }
        if (next == 505){
            next = 122;
        }
        if (next == 507){
            next = 403;
        }
        if (next == 508){
            next = 109;
        }
        if (next == 603){
            next = 121;
        }
        if (next == 607){
            next = 116;
        }
        if (next == 617){
            next = 417;
        }
        if (next == 623){
            next = 601;
        }
        if (next == 707){
            next = 416;
        }
        if (next == 708){
            next = 614;
        }
        if (next == 804){
            next = 409;
        }
        if (next == 807){
            next = 705;
        }
        if (next == 902){
            next = 406;
        }
        if (next == 903){
            next = 119;
        }
        if (next == 904){
            next = 702;
        }
        if (next == 905){
            next = 621;
        }
        if (numint == 101){
            tv_inform.setText("1호선, 2호선으로 환승 가능한 역");
        }else if (numint == 104){
            tv_inform.setText("1호선, 4호선으로 환승 가능한 역");
        }else if (numint == 107){
            tv_inform.setText("1호선,3호선으로 환승 가능한 역");
        }else if (numint == 109){
            tv_inform.setText("1호선, 5호선으로 환승 가능한 역");
        }else if (numint == 112){
            tv_inform.setText("1호선, 9호선으로 환승 가능한 역");
        }else if (numint == 113){
            tv_inform.setText("1호선, 8호선으로 환승 가능한 역");
        }else if (numint == 115){
            tv_inform.setText("1호선, 4호선으로 환승 가능한 역");
        }else if (numint == 116){
            tv_inform.setText("1호선, 6호선으로 환승 가능한 역");
        }else if (numint == 119){
            tv_inform.setText("1호선, 9호선으로 환승 가능한 역");
        }else if (numint == 121){
            tv_inform.setText("1호선, 6호선으로 환승 가능한 역");
        }else if (numint == 122){
            tv_inform.setText("1호선, 5호선으로 환승 가능한 역");
        }else if (numint == 123){
            tv_inform.setText("1호선, 3호선으로 환승 가능한 역");
        }else if (numint == 202){
            tv_inform.setText("2호선, 7호선으로 환승 가능한 역");
        }else if (numint == 207){
            tv_inform.setText("2호선, 3호선으로 환승 가능한 역");
        }else if (numint == 209){
            tv_inform.setText("2호선, 5호선으로 환승 가능한 역");
        }else if (numint == 211){
            tv_inform.setText("2호선, 9호선으로 환승 가능한 역");
        }else if (numint == 214){
            tv_inform.setText("2호선, 8호선으로 환승 가능한 역");
        }else if (numint == 216){
            tv_inform.setText("2호선, 4호선으로 환승 가능한 역");
        }else if (numint == 307){
            tv_inform.setText("3호선, 4호선으로 환승 가능한 역");
        }else if (numint == 303){
            tv_inform.setText("3호선, 7호선으로 환승 가능한 역");
        }else if (numint == 403){
            tv_inform.setText("4호선, 5호선으로 환승 가능한 역");
        }else if (numint == 406){
            tv_inform.setText("4호선, 9호선으로 환승 가능한 역");
        }else if (numint == 409){
            tv_inform.setText("4호선, 8호선으로 환승 가능한 역");
        }else if (numint == 412){
            tv_inform.setText("4호선, 6호선으로 환승 가능한 역");
        }else if (numint == 416){
            tv_inform.setText("4호선, 7호선으로 환승 가능한 역");
        }else if (numint == 417){
            tv_inform.setText("4호선, 6호선으로 환승 가능한 역");
        }else if (numint == 503){
            tv_inform.setText("5호선, 7호선으로 환승 가능한 역");
        }else if (numint == 601){
            tv_inform.setText("6호선, 7호선으로 환승 가능한 역");
        }else if (numint == 605){
            tv_inform.setText("6호선, 9호선으로 환승 가능한 역");
        }else if (numint == 608){
            tv_inform.setText("6호선, 8호선으로 환승 가능한 역");
        }else if (numint == 614){
            tv_inform.setText("6호선, 7호선으로 환승 가능한 역");
        }else if (numint == 618){
            tv_inform.setText("6호선, 8호선으로 환승 가능한 역");
        }else if (numint == 621){
            tv_inform.setText("6호선, 9호선으로 환승 가능한 역");
        }else if (numint == 702){
            tv_inform.setText("7호선, 9호선으로 환승 가능한 역");
        }else if (numint == 705){
            tv_inform.setText("7호선, 8호선으로 환승 가능한 역");
        }else{
            tv_inform.setText("몰랑");
        }

        //호선별 색깔 설정
        int color1 = (this).getResources().getColor(R.color.line1);
        int color2 = (this).getResources().getColor(R.color.line2);
        int color3 = (this).getResources().getColor(R.color.line3);
        int color4 = (this).getResources().getColor(R.color.line4);
        int color5 = (this).getResources().getColor(R.color.line5);
        int color6 = (this).getResources().getColor(R.color.line6);
        int color7 = (this).getResources().getColor(R.color.line7);
        int color8 = (this).getResources().getColor(R.color.line8);
        int color9 = (this).getResources().getColor(R.color.line9);


        if (line == 1){
            img_sta.setColorFilter(color1);
            img_prev.setColorFilter(color1);
            img_next.setColorFilter(color1);
            block2.setBackgroundColor(color1);
            block3.setBackgroundColor(color1);
            block4.setBackgroundColor(color1);
            block5.setBackgroundColor(color1);
        }
        if (line == 2){
            img_sta.setColorFilter(color2);
            img_prev.setColorFilter(color2);
            img_next.setColorFilter(color2);
            block2.setBackgroundColor(color2);
            block3.setBackgroundColor(color2);
            block4.setBackgroundColor(color2);
            block5.setBackgroundColor(color2);
        }
        if (line == 3){
            img_sta.setColorFilter(color3);
            img_prev.setColorFilter(color3);
            img_next.setColorFilter(color3);
            block2.setBackgroundColor(color3);
            block3.setBackgroundColor(color3);
            block4.setBackgroundColor(color3);
            block5.setBackgroundColor(color3);
        }
        if (line == 4){
            img_sta.setColorFilter(color4);
            img_prev.setColorFilter(color4);
            img_next.setColorFilter(color4);
            block2.setBackgroundColor(color4);
            block3.setBackgroundColor(color4);
            block4.setBackgroundColor(color4);
            block5.setBackgroundColor(color4);
        }
        if (line == 5){
            img_sta.setColorFilter(color5);
            img_prev.setColorFilter(color5);
            img_next.setColorFilter(color5);
            block2.setBackgroundColor(color5);
            block3.setBackgroundColor(color5);
            block4.setBackgroundColor(color5);
            block5.setBackgroundColor(color5);
        }
        if (line == 6){
            img_sta.setColorFilter(color6);
            img_prev.setColorFilter(color6);
            img_next.setColorFilter(color6);
            block2.setBackgroundColor(color6);
            block3.setBackgroundColor(color6);
            block4.setBackgroundColor(color6);
            block5.setBackgroundColor(color6);
        }
        if (line == 7){
            img_sta.setColorFilter(color7);
            img_prev.setColorFilter(color7);
            img_next.setColorFilter(color7);
            block2.setBackgroundColor(color7);
            block3.setBackgroundColor(color7);
            block4.setBackgroundColor(color7);
            block5.setBackgroundColor(color7);
        }if (line == 8){
            img_sta.setColorFilter(color8);
            img_prev.setColorFilter(color8);
            img_next.setColorFilter(color8);
            block2.setBackgroundColor(color8);
            block3.setBackgroundColor(color8);
            block4.setBackgroundColor(color8);
            block5.setBackgroundColor(color8);
        }if (line == 9){
            img_sta.setColorFilter(color9);
            img_prev.setColorFilter(color9);
            img_next.setColorFilter(color9);
            block2.setBackgroundColor(color9);
            block3.setBackgroundColor(color9);
            block4.setBackgroundColor(color9);
            block5.setBackgroundColor(color9);
        }

        String numStrline = String.valueOf(line);
        String numStrprev = String.valueOf(prev);
        String numStrnext = String.valueOf(next);

        what_line.setText(numStrline + "호선");
        sta_prev.setText(numStrprev);
        prestation.setText(numStrprev + " 방면");

        if(next == 0){
            sta_next.setText("역없음");
            nextstation.setText("역없음");
        }else{
            sta_next.setText(numStrnext);
            nextstation.setText(numStrnext + " 방면");
        }
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
