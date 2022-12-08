package com.app.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StationActivity  extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton btnSubway, btnSetting, btnHome, btnRoad, btnStar, btnSearch;
    Boolean isAllFabsVisible;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
//
//        btnSubway = findViewById(R.id.fab_subway);
//
//        btnSetting = findViewById(R.id.fab_setting);
//        btnHome = findViewById(R.id.fab_home);
//        btnRoad = findViewById(R.id.fab_road);
//        btnSearch = findViewById(R.id.fab_search);
//        btnStar = findViewById(R.id.fab_star);
//
//        btnSetting.setVisibility(View.GONE);
//        btnHome.setVisibility(View.GONE);
//        btnStar.setVisibility(View.GONE);
//        btnSearch.setVisibility(View.GONE);
//        btnRoad.setVisibility(View.GONE);
//
//        isAllFabsVisible = false;
//
//        btnSubway.setOnClickListener(view -> {
//            if (!isAllFabsVisible) {
//                // when isAllFabsVisible becomes true make all
//                // the action name texts and FABs VISIBLE
//                btnSetting.show();
//                btnHome.show();
//                btnStar.show();
//                btnSearch.show();
//                btnRoad.show();
//                isAllFabsVisible = true;
//            } else {
//                btnSetting.hide();
//                btnHome.hide();
//                btnStar.hide();
//                btnSearch.hide();
//                btnRoad.hide();
//                isAllFabsVisible = false;
//            }
//        });
//
//        btnHome.setOnClickListener(this);
//        btnSetting.setOnClickListener(this);
//        btnSearch.setOnClickListener(this);
//        btnStar.setOnClickListener(this);
//        btnRoad.setOnClickListener(this);
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
