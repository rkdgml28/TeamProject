package com.app.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RoadActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton btnSubway, btnSetting, btnHome, btnRoad, btnStar, btnSearch;
    TextView btn_line1, btn_line2, btn_line3, btn_line4, btn_line5, btn_line6, btn_line7, btn_line8, btn_line9;
    ImageView iv_road , road_search;
    Boolean isAllFabsVisible;
    AutoCompleteTextView road_search_et;

    int numint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road);

        btn_line1 = findViewById(R.id.btn_line1);
        btn_line2 = findViewById(R.id.btn_line2);
        btn_line3 = findViewById(R.id.btn_line3);
        btn_line4 = findViewById(R.id.btn_line4);
        btn_line5 = findViewById(R.id.btn_line5);
        btn_line6 = findViewById(R.id.btn_line6);
        btn_line7 = findViewById(R.id.btn_line7);
        btn_line8 = findViewById(R.id.btn_line8);
        btn_line9 = findViewById(R.id.btn_line9);
        btnSubway = findViewById(R.id.fab_subway);
        iv_road = findViewById(R.id.iv_road);
        iv_road.setVisibility(View.INVISIBLE);

        // FAB button
        btnSetting = findViewById(R.id.fab_setting);
        btnHome = findViewById(R.id.fab_home);
        btnRoad = findViewById(R.id.fab_road);
        btnSearch = findViewById(R.id.fab_search);
        btnStar = findViewById(R.id.fab_star);

        road_search = findViewById(R.id.road_search);
        road_search_et = findViewById(R.id.road_search_et);

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

        btn_line1.setOnClickListener(this);
        btn_line2.setOnClickListener(this);
        btn_line3.setOnClickListener(this);
        btn_line4.setOnClickListener(this);
        btn_line5.setOnClickListener(this);
        btn_line6.setOnClickListener(this);
        btn_line7.setOnClickListener(this);
        btn_line8.setOnClickListener(this);
        btn_line9.setOnClickListener(this);


        road_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = road_search_et.getText().toString();
                numint = Integer.parseInt(search);
                int line = numint/100;

                if (line == 1){
                    iv_road.setImageResource(R.drawable.line1);
                    iv_road.setVisibility(View.VISIBLE);
                }
                if (line == 2){
                    iv_road.setImageResource(R.drawable.line2);
                    iv_road.setVisibility(View.VISIBLE);
                }
                if (line == 3){
                    iv_road.setImageResource(R.drawable.line3);
                    iv_road.setVisibility(View.VISIBLE);
                }
                if (line == 4){
                    iv_road.setImageResource(R.drawable.line4);
                    iv_road.setVisibility(View.VISIBLE);
                }
                if (line == 5){
                    iv_road.setImageResource(R.drawable.line5);
                    iv_road.setVisibility(View.VISIBLE);
                }
                if (line == 6){
                    iv_road.setImageResource(R.drawable.line6);
                    iv_road.setVisibility(View.VISIBLE);
                }
                if (line == 7){
                    iv_road.setImageResource(R.drawable.line7);
                    iv_road.setVisibility(View.VISIBLE);
                }
                if (line == 8){
                    iv_road.setImageResource(R.drawable.line8);
                    iv_road.setVisibility(View.VISIBLE);
                }
                if (line == 9){
                    iv_road.setImageResource(R.drawable.line9);
                    iv_road.setVisibility(View.VISIBLE);
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

            case R.id.btn_line1:
                iv_road.setImageResource(R.drawable.line1);
                iv_road.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_line2:
                iv_road.setImageResource(R.drawable.line2);
                iv_road.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_line3:
                iv_road.setImageResource(R.drawable.line3);
                iv_road.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_line4:
                iv_road.setImageResource(R.drawable.line4);
                iv_road.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_line5:
                iv_road.setImageResource(R.drawable.line5);
                iv_road.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_line6:
                iv_road.setImageResource(R.drawable.line6);
                break;
            case R.id.btn_line7:
                iv_road.setImageResource(R.drawable.line7);
                iv_road.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_line8:
                iv_road.setImageResource(R.drawable.line8);
                iv_road.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_line9:
                iv_road.setImageResource(R.drawable.line9);
                iv_road.setVisibility(View.VISIBLE);
                break;

        }
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu, menu);

    }

    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemLanguage:
                Toast.makeText(this, "language", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemAsk:
                Toast.makeText(this, "ask", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemSetting:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemHelp:
                Toast.makeText(this, "help", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

}