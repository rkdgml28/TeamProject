package com.app.teamproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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

    private static final String BOOKMARK = "bookmark";
    private static final String BOOKMARK_JSON = "bookmark_json";


    FloatingActionButton btnSubway, btnSetting, btnHome, btnRoad, btnStar, btnSearch;
    Boolean isAllFabsVisible;
    ImageView bookmark_search;
    AutoCompleteTextView autoCompleteTextView;
    private static Context context;

//    SharedPreferences spref;
//    SharedPreferences.Editor editor;
//    JSONArray arr = new JSONArray();



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
    ArrayList<String> myStations = new ArrayList<>();

    String target = "";



    SharedPreferences pref;
    SharedPreferences.Editor editor;

    String saveStr;


//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState); // 반드시 호출해 주세요.
//        String data = target;
//        ArrayList<String> saveStations = myStations;
//        // 추가로 자료를 저장하는 코드는 여기에 작성 하세요.
//    }
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        // 추가로 자료를 복원하는 코드는 여기에 작성하세요.
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        BookmarkActivity.context = getApplicationContext();



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

        /* initiate adapter */
        mRecyclerAdapter = new BookmarkAdapter(myStations, this);

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.setFavStationList(mfavStations);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        // 1. Shared Preference 초기화
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        // 2. 저장해둔 값 불러오기 ("식별값", 초기값) -> 식별값과 초기값은 직접 원하는 이름과 값으로 작성.
        saveStr = pref.getString("MyStr", "_");   // String 불러오기 (저장해둔 값 없으면 초기값인 _으로 불러옴)

        bookmark_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                target = autoCompleteTextView.getText().toString();

                if (!myStations.contains(target)) {
                    mfavStations.add(new BookmarkStation(R.drawable.favorite, target, R.drawable.minus));
                    myStations.add(target);
                    mRecyclerAdapter.notifyDataSetChanged();
                    mfavStations.add(new BookmarkStation(R.drawable.favorite, saveStr, R.drawable.minus));


                    if (myStations != null) {
                        for (String value : myStations) {
                            Log.v("Get json : ", value);

                            editor.putString("MyStr", value);
                            editor.apply(); // 저장
                            System.out.println(saveStr);
                        }
                    }
                } else {
                    Toast.makeText(context, "이미 즐겨찾기에 추가되어 있습니다.", Toast.LENGTH_SHORT).show();
//                ArrayList<String> list = getStringArrayPref(BOOKMARK_JSON);
//                ArrayList<String> l = new ArrayList<>();
//                if (list != null) {
//                    for (String value : list) {
//                        Log.v("Get json : ", value);
//                        l.add(value);
//                    }
//                }
//                l.add(target);
//                Log.v("target", target);
//                setStringArrayPref(context, BOOKMARK_JSON, l);
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
                break;
            case R.id.fab_road:
                intent = new Intent(this, RoadActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    private void setStringArrayPref(Context context, String key, ArrayList<String> values) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }
        editor.apply();
    }

    public ArrayList<String> getStringArrayPref(String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);
        ArrayList<String> urls = new ArrayList<String>();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }
}

