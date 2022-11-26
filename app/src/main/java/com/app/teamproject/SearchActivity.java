package com.app.teamproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    EditText edit_start, edit_stopover, edit_finish;
    ImageView btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edit_start = findViewById(R.id.edit_start);
        edit_stopover = findViewById(R.id.edit_stopover);
        edit_finish = findViewById(R.id.edit_finish);
        btn_search = findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start = edit_start.getText().toString();
                String stopover = edit_start.getText().toString();
                String finish = edit_start.getText().toString();

                if(start.length() == 0){
                    Toast.makeText(getApplicationContext(), "출발역을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else if (finish.length() == 0){
                    Toast.makeText(getApplicationContext(), "도착역을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}