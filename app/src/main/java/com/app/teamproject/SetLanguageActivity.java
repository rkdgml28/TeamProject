package com.app.teamproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class SetLanguageActivity extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton btnSubway, btnSetting, btnHome, btnRoad, btnStar, btnSearch, btnLang;
    Boolean isAllFabsVisible;;
    Button korean_btn;
    Button english_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_set_language);

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
        btnSubway = findViewById(R.id.fab_subway);

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

        korean_btn = findViewById(R.id.page1_btn);
        english_btn = findViewById(R.id.page2_btn);


        korean_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Locale locale = new Locale("ko");
                Locale.setDefault(locale);

                Configuration configuration = new Configuration();
                configuration.locale = locale;

                getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());


                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Lang", "ko");
                editor.apply();

                Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);
            }
        });

        english_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Locale locale = new Locale("en");
                Locale.setDefault(locale);

                Configuration configuration = new Configuration();
                configuration.locale = locale;

                getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Lang", "en");
                editor.apply();

                Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);
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