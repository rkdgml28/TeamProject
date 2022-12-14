package com.app.teamproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class BookmarkActivity extends AppCompatActivity implements View.OnClickListener {

    private InputMethodManager imm;
    FloatingActionButton btnSubway, btnSetting, btnHome, btnRoad, btnStar, btnSearch, btnLang;
    Boolean isAllFabsVisible;
    ImageView bookmark_search;
    AutoCompleteTextView autoCompleteTextView;
    private static Context context;

    SharedPreferences spref;
    SharedPreferences.Editor editor;

    private List<String> stations = Arrays.asList("101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123",
            "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211", "212", "213", "214", "215", "216", "217",
            "301", "302", "303", "304", "305", "305", "307", "308",
            "401", "402", "403", "404", "405", "406", "407", "408", "409", "410", "411", "412", "413", "414", "415", "416", "417",
            "501", "502", "503", "504", "505", "506", "507",
            "601", "602", "603", "604", "605", "606", "607", "608", "609", "610", "611", "612", "613", "614", "615", "616", "617", "618", "619", "620", "621", "622",
            "701", "702", "703", "704", "705", "706", "707",
            "801", "802", "803", "804", "805", "806",
            "901", "902", "903", "904");

    RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    BookmarkAdapter mRecyclerAdapter;
    ArrayList<BookmarkStation> mfavStations = new ArrayList<>();
    static ArrayList<String> myStations;

    String target = "";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        BookmarkActivity.context = getApplicationContext();

        myStations = new ArrayList<>();
        bookmark_search = findViewById(R.id.bookmark_search);
        btnSubway = findViewById(R.id.fab_subway);

        // FAB button
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
                btnLang.show();
                isAllFabsVisible = false;
            }
        });

        btnHome.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnStar.setOnClickListener(this);
        btnRoad.setOnClickListener(this);
        btnLang.setOnClickListener(this);



        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stations));

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View clickedView, int position, long id) {
                autoCompleteTextView.setText(((TextView) clickedView).getText().toString());
                //target = ((TextView) clickedView).getText().toString();
            }
        });

        mRecyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        spref = getSharedPreferences("bookmark", MODE_PRIVATE);
        editor = spref.edit();
        String str = spref.getString("stations", "");
        String[] str_stations = str.split(" ");
        for (String s : str_stations){
            myStations.add(s);
        }


        mRecyclerAdapter = new BookmarkAdapter(myStations, this);

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        bookmark_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                target = autoCompleteTextView.getText().toString();
                // ??? ????????? ?????????
                if (stations.contains(target)){
                    // ???????????? ????????? ?????????
                    if (!myStations.contains(target)) {
                        BookmarkActivity.myStations.add(target);
                        mRecyclerAdapter.notifyDataSetChanged();
                        autoCompleteTextView.setText("");
                        imm.hideSoftInputFromWindow(autoCompleteTextView.getWindowToken(), 0);
                    }
                    // ???????????? ????????? ?????????
                    else {
                        Toast.makeText(context, "?????? ??????????????? ???????????? ????????????.", Toast.LENGTH_SHORT).show();
                    }
                }
                // ??? ????????? ?????????
                else{
                    Toast.makeText(context, "?????? ????????????.", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myStations = mRecyclerAdapter.getStationList();
        String stations = "";
        for (String s: myStations){
            stations += s + " ";
        }
        editor.putString("stations", stations);
        editor.commit();
    }
}

