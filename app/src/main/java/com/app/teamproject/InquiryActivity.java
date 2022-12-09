package com.app.teamproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InquiryActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton btnSubway, btnSetting, btnHome, btnRoad, btnStar, btnSearch;
    Boolean isAllFabsVisible;;
    TextView checkResult, inputEmail;
    Button sendQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);

        inputEmail = findViewById(R.id.input_email);
        checkResult = findViewById(R.id.check_email_result);
        sendQuestion = findViewById(R.id.question_button);
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

        // "inputTextView" 에 텍스트를 입력받는 뷰를 입력해 주세요.
        inputEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력되는 텍스트에 변화가 있을 때 호출된다.
                // 이메일 형식 체크 결과를 결과 뷰에 세팅
                checkResult.setText(checkEmailForm(inputEmail.getText().toString()));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력이 끝났을 때 호출된다.
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 입력하기 전에 호출된다.
            }
        });

        //클릭 이벤트 기능
        sendQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //클릭 시 발생하는 이벤트
                sendEmail(InquiryActivity.this, "문의드립니다", new String[]{inputEmail.getText().toString()});
            }
        });
    }


    /***
     *  이메일 형식 체크 해주는 기능
     *
     *  @param  inputText - 이메일 형식을 체크하고싶은 텍스트
     *
     *  @return String 이메일 형식에 맞을 때 : "올바른 이메일 입니다."
     *                 이메일 형식에 맞지 않을 때 : "올바른 이메일을 입력해주세요."
     */
    public String checkEmailForm(String inputText){

        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(inputText);

        if(matcher.find()){
            //이메일 형식에 맞을 때
            return "올바른 이메일 입니다.";

        }else{
            //이메일 형식에 맞지 않을 때
            return "올바른 이메일을 입력해주세요.";

        }
    }
    /***
     * 문의 메일 보내기
     *
     * @param context
     * @param title - 메일 제목
     * @param receivers - 받는 사람
     */
    public static void sendEmail(Context context, String title, String[] receivers){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_SUBJECT, title);
        email.putExtra(Intent.EXTRA_EMAIL, receivers);
        email.putExtra(Intent.EXTRA_TEXT, String.format("내용: "));
        email.setType("message/rfc822");
        context.startActivity(email);
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
        }
    }
}