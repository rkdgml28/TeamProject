package com.app.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity implements View.OnClickListener{
    FloatingActionButton btnSubway, btnSetting, btnHome, btnRoad, btnStar, btnSearch;
    Boolean isAllFabsVisible;
    ImageView bookmark_search;

    private List<String> stations = Arrays.asList("101","102","103","104","105","106","107","108","109","110","111","112","113","114","115","116","117","118","119","120","121","122","123",
            "201","202","203","204","205","206","207","208","209","210","211","212","213","214","215","216","217",
            "301","302","303","304","305","305","307","308",
            "401","402","403","404","405","406","407","408","409","410","411","412","413","414","415","416","417",
            "501","502","503","504","505","506","507",
            "601","602","603","604","605","606","607","608","609","610","611","612","613","614","615","616","617","618","619","620","621","622",
            "701","702","703","704","705","706","707",
            "801","802","803","804","805","806",
            "901","902","903","904");

    RecyclerView mRecyclerView;
    BookmarkAdapter mRecyclerAdapter;
    ArrayList mfavStations = new ArrayList<>();
    String target = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);


        bookmark_search = findViewById(R.id.bookmark_search);
        btnSubway = findViewById(R.id.fab_subway);

        // FAB button
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



        final AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,stations));

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View clickedView, int position, long id) {
                autoCompleteTextView.setText(((TextView)clickedView).getText().toString());
                target = ((TextView)clickedView).getText().toString();
//                mfavStations.add(new BookmarkStation(R.drawable.favorite,((TextView)clickedView).getText().toString(),R.drawable.minus));
//                mRecyclerAdapter.notifyDataSetChanged();
                autoCompleteTextView.setText("");
            }
        });

        mRecyclerView = findViewById(R.id.recyclerview);

        /* initiate adapter */
        mRecyclerAdapter= new BookmarkAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.setFavStationList(mfavStations);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mRecyclerAdapter.setOnItemClickListener(new BookmarkAdapter.OnItemClickListener(){

            @Override
            public void onRemoveClick(View v, int position) {
                mfavStations.remove(position);
                mRecyclerAdapter.notifyItemRemoved(position);
            }
        });

        bookmark_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfavStations.add(new BookmarkStation(R.drawable.favorite,target,R.drawable.minus));
                mRecyclerAdapter.notifyDataSetChanged();
                autoCompleteTextView.setText("");
            }
        });


    }
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.fab_home:
                break;
            case R.id.fab_setting:
                this.registerForContextMenu(btnSetting);
                openContextMenu(btnSetting);
                unregisterForContextMenu(btnSetting);
                break;
            case R.id.fab_search:
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.fab_star:
                break;
            case R.id.fab_road:
                intent = new Intent(this, RoadActivity.class);
                startActivity(intent);
                break;
        }
    }
}

