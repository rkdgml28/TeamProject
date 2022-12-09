package com.app.teamproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton btnSubway, btnSetting, btnHome, btnRoad, btnStar, btnSearch;
    Button btn101, btn102, btn103, btn104, btn105, btn106, btn107, btn108, btn109, btn110, btn111, btn112, btn113, btn114, btn115, btn116, btn117, btn118, btn119, btn120, btn121, btn122, btn123,
    btn201, btn202, btn203, btn204, btn205, btn206, btn207, btn208, btn209, btn210, btn211, btn212, btn213, btn214, btn215, btn216, btn217,
    btn301, btn302, btn303, btn304, btn305, btn306, btn307, btn308,
    btn401, btn402, btn403, btn404, btn405, btn406, btn407, btn408, btn409, btn410, btn411, btn412, btn413, btn414, btn415, btn416, btn417,
    btn501, btn502, btn503, btn504, btn505, btn506, btn507,
    btn601, btn602, btn603, btn604, btn605, btn606, btn607, btn608, btn609, btn610, btn611, btn612, btn613, btn614, btn615, btn616, btn617, btn618, btn619, btn620, btn621, btn622,
    btn701, btn702, btn703, btn704, btn705, btn706, btn707,
    btn801, btn802, btn803, btn804, btn805, btn806,
    btn901, btn902, btn903, btn904;
    ImageView search, iv_main;
    TextView search_et;
    Boolean isAllFabsVisible;
    LinearLayout linear_main;

    String[] stations = {"101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123",
            "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211", "212", "213", "214", "215", "216", "217",
            "301", "302", "303", "304", "305", "305", "307", "308",
            "401", "402", "403", "404", "405", "406", "407", "408", "409", "410", "411", "412", "413", "414", "415", "416", "417",
            "501", "502", "503", "504", "505", "506", "507",
            "601", "602", "603", "604", "605", "606", "607", "608", "609", "610", "611", "612", "613", "614", "615", "616", "617", "618", "619", "620", "621", "622",
            "701", "702", "703", "704", "705", "706", "707",
            "801", "802", "803", "804", "805", "806",
            "901", "902", "903", "904"};

    ArrayList<String> arr = new ArrayList<>();

    private static Context context;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;

    enum TOUCH_MODE {
        NONE,   // 터치 안했을 때
        SINGLE, // 한손가락 터치
        MULTI   //두손가락 터치
    }

    private TOUCH_MODE touchMode;
    private Matrix matrix;      //기존 매트릭스
    private Matrix savedMatrix; //작업 후 이미지에 매핑할 매트릭스

    private PointF startPoint;  //한손가락 터치 이동 포인트

    private PointF midPoint;    //두손가락 터치 시 중신 포인트
    private float oldDistance;  //터치 시 두손가락 사이의 거리

    private double oldDegree = 0; // 두손가락의 각도


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.context = getApplicationContext();
        setContentView(R.layout.activity_main);

        matrix = new Matrix();
        savedMatrix = new Matrix();

        for (int i = 0; i < 111; i++){
            arr.add(stations[i]);
        }

        btn101 = findViewById(R.id.btn101);btn102 = findViewById(R.id.btn102);btn103 = findViewById(R.id.btn104);btn104 = findViewById(R.id.btn104);
        btn105 = findViewById(R.id.btn105);btn106 = findViewById(R.id.btn106);btn107 = findViewById(R.id.btn108);btn108 = findViewById(R.id.btn108);
        btn109 = findViewById(R.id.btn109);btn110 = findViewById(R.id.btn110);btn111 = findViewById(R.id.btn111);btn112 = findViewById(R.id.btn112);
        btn113 = findViewById(R.id.btn113);btn114 = findViewById(R.id.btn114);btn115 = findViewById(R.id.btn115);btn116 = findViewById(R.id.btn116);
        btn117 = findViewById(R.id.btn117);btn118 = findViewById(R.id.btn118);btn119 = findViewById(R.id.btn119);btn120 = findViewById(R.id.btn120);
        btn121 = findViewById(R.id.btn121);btn122 = findViewById(R.id.btn122);btn123 = findViewById(R.id.btn123);
        btn201 = findViewById(R.id.btn201);btn202 = findViewById(R.id.btn202);btn203 = findViewById(R.id.btn203);btn204 = findViewById(R.id.btn204);
        btn205 = findViewById(R.id.btn205);btn206 = findViewById(R.id.btn206);btn207 = findViewById(R.id.btn207);btn208 = findViewById(R.id.btn208);
        btn209 = findViewById(R.id.btn209);btn210 = findViewById(R.id.btn210);btn211 = findViewById(R.id.btn211);btn212 = findViewById(R.id.btn212);
        btn213 = findViewById(R.id.btn213);btn214 = findViewById(R.id.btn214);btn215 = findViewById(R.id.btn215);btn216 = findViewById(R.id.btn216);
        btn217 = findViewById(R.id.btn217);
        btn301 = findViewById(R.id.btn301); btn302 = findViewById(R.id.btn302); btn303 = findViewById(R.id.btn303); btn304 = findViewById(R.id.btn304);
        btn305 = findViewById(R.id.btn305); btn306 = findViewById(R.id.btn306); btn307 = findViewById(R.id.btn307); btn308 = findViewById(R.id.btn308);
        btn401 = findViewById(R.id.btn401);btn402 = findViewById(R.id.btn402);btn403 = findViewById(R.id.btn403);btn404 = findViewById(R.id.btn404);
        btn405 = findViewById(R.id.btn405);btn406 = findViewById(R.id.btn406);btn407 = findViewById(R.id.btn407);btn408 = findViewById(R.id.btn408);
        btn409 = findViewById(R.id.btn409);btn410 = findViewById(R.id.btn410);btn411 = findViewById(R.id.btn411);btn412 = findViewById(R.id.btn412);
        btn413 = findViewById(R.id.btn413);btn414 = findViewById(R.id.btn414);btn415 = findViewById(R.id.btn415);btn416 = findViewById(R.id.btn416);
        btn417 = findViewById(R.id.btn417);
        btn501 = findViewById(R.id.btn501);btn502 = findViewById(R.id.btn502);btn503 = findViewById(R.id.btn503);btn504 = findViewById(R.id.btn504);
        btn505 = findViewById(R.id.btn505);btn506 = findViewById(R.id.btn506);btn507 = findViewById(R.id.btn507);
        btn601 = findViewById(R.id.btn601);btn602 = findViewById(R.id.btn602);btn603 = findViewById(R.id.btn603);btn604 = findViewById(R.id.btn604);
        btn605 = findViewById(R.id.btn605);btn606 = findViewById(R.id.btn606);btn607 = findViewById(R.id.btn607);btn608 = findViewById(R.id.btn608);
        btn609 = findViewById(R.id.btn609);btn610 = findViewById(R.id.btn610);btn611 = findViewById(R.id.btn611);btn612 = findViewById(R.id.btn612);
        btn613 = findViewById(R.id.btn613);btn614 = findViewById(R.id.btn614);btn615 = findViewById(R.id.btn615);btn616 = findViewById(R.id.btn616);
        btn617 = findViewById(R.id.btn617);btn618 = findViewById(R.id.btn618);btn619 = findViewById(R.id.btn619);btn620 = findViewById(R.id.btn620);
        btn621 = findViewById(R.id.btn621);btn622 = findViewById(R.id.btn622);
        btn701 = findViewById(R.id.btn701);btn702 = findViewById(R.id.btn702);btn703 = findViewById(R.id.btn703);btn704 = findViewById(R.id.btn704);
        btn705 = findViewById(R.id.btn705);btn706 = findViewById(R.id.btn706);btn707 = findViewById(R.id.btn707);
        btn801 = findViewById(R.id.btn801); btn802 = findViewById(R.id.btn802); btn803 = findViewById(R.id.btn803); btn804 = findViewById(R.id.btn804);
        btn805 = findViewById(R.id.btn805); btn806 = findViewById(R.id.btn806);
        btn901 = findViewById(R.id.btn901); btn902 = findViewById(R.id.btn902); btn903 = findViewById(R.id.btn903); btn904 = findViewById(R.id.btn904);

        btn101.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, StationActivity.class);
                startActivity(intent);
            }
        });

        linear_main = findViewById(R.id.linear_main);
        search_et = findViewById(R.id.main_search_et);
        search = findViewById(R.id.main_search);
        iv_main = findViewById(R.id.iv_main);

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

        AutoCompleteTextView main_search = findViewById(R.id.main_search_et);
        main_search.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stations));

        // 스케일제스쳐 디텍터 인스턴스
        iv_main.setOnTouchListener(onTouch);
        iv_main.setScaleType(ImageView.ScaleType.MATRIX);
//        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        search.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (search_et.getText().toString().equals("")){
                    Toast.makeText(context, "역을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else if(!arr.contains(search_et.getText().toString())){
                    Toast.makeText(context, "없는 역입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    String s = search_et.getText().toString();
                    Intent intent = new Intent(context, StationActivity.class);
                    intent.putExtra("station", s);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
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
                intent = new Intent(this, BookmarkActivity.class);
                startActivity(intent);
                break;
            case R.id.fab_road:
                intent = new Intent(this, RoadActivity.class);
                startActivity(intent);
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

    public static Context ApplicationContext() {
        return MainActivity.context;
    }

    float oldXvalue;
    float oldYvalue;

//    public boolean onTouchEvent(View v, MotionEvent event) {
//        //변수로 선언해 놓은 ScaleGestureDetector
//        mScaleGestureDetector.onTouchEvent(event);
//        return true;
//    }

    private View.OnTouchListener onTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int width = ((ViewGroup) v.getParent()).getWidth() - v.getWidth();
            int height = linear_main.getHeight() - v.getHeight();
            if (v.equals(iv_main)) {
                int action = event.getAction();
                switch (action & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        touchMode = TOUCH_MODE.SINGLE;

                        donwSingleEvent(event);
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if (event.getPointerCount() == 2) { // 두손가락 터치를 했을 때
                            touchMode = TOUCH_MODE.MULTI;
                            downMultiEvent(event);
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (touchMode == TOUCH_MODE.SINGLE) {
                            moveSingleEvent(v, event);
                            if ( v.getX() > 0 && v.getY() > 0 && v.getX() < width && v.getY() < height ) {
                                v.setX(0);
                                v.setY(0);
                            }
                        } else if (touchMode == TOUCH_MODE.MULTI) {
                            moveMultiEvent(event);
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        touchMode = TOUCH_MODE.NONE;
                        break;
                }
            }


            return true;
        }
    };

    private PointF getMidPoint(MotionEvent e) {

        float x = (e.getX(0) + e.getX(1)) / 2;
        float y = (e.getY(0) + e.getY(1)) / 2;

        return new PointF(x, y);
    }

    private float getDistance(MotionEvent e) {
        float x = e.getX(0) - e.getX(1);
        float y = e.getY(0) - e.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void donwSingleEvent(MotionEvent event) {
        savedMatrix.set(matrix);
        startPoint = new PointF(event.getX(), event.getY());
    }

    private void downMultiEvent(MotionEvent event) {
        oldDistance = getDistance(event);
        if (oldDistance > 5f) {
            savedMatrix.set(matrix);
            midPoint = getMidPoint(event);
            double radian = Math.atan2(event.getY() - midPoint.y, event.getX() - midPoint.x);
            oldDegree = (radian * 180) / Math.PI;
        }
    }
    private void moveSingleEvent(View v, MotionEvent event) {

        float x = event.getX() - startPoint.x;
        float y = event.getY() - startPoint.y;
        int width = ((ViewGroup) v.getParent()).getWidth() - v.getWidth();
        int height = linear_main.getHeight() - v.getHeight();
//        if (x > width && y > height) {
//            x = width;
//            y = height;
//        } else if (x < 0 && y > height) {
//            x = 0;
//            y = height;
//        } else if (x > width && y < 0) {
//            x = width;
//            y = 0;
//        } else if (x < 0 && y < 0) {
//            x = 0;
//            y = 0;
//        } else if (x < 0 || y > width) {
//            if (x < 0) {
//                x = 0;
//                y = event.getRawY() - startPoint.y - v.getHeight();
//            } else {
//                x = width;
//                y = event.getRawY() - startPoint.y - v.getHeight();
//            }
//        } else if (y < 0 || y > height) {
//            if (y < 0) {
//                y = 0;
//            } else {
//                y = height;
//            }
//        }

        matrix.set(savedMatrix);
//        if ( x > 0 && y > 0 && x < width && y < height ) {
//            matrix.postTranslate(x, y);
//        }
//        if (x < 0){
//            x = 0;
//        }
//        if (y < 0) {
//            y = 0;
//        }
        matrix.postTranslate(x, y);
        Log.v("matrix", iv_main.getMaxWidth() + " :::::::" + y);
        if (iv_main.getX() < 0){
            iv_main.setX(0);
        }
        iv_main.setImageMatrix(matrix);
    }

    private void moveMultiEvent(MotionEvent event) {
        float newDistance = getDistance(event);
        if (newDistance > 5f) {
            matrix.set(savedMatrix);
            float scale = newDistance / oldDistance;
            if (scale < 0 ){
                scale = 1;
            }

            Log.v("scale", String.valueOf(scale));
            matrix.postScale(scale, scale, midPoint.x, midPoint.y);

            double nowRadian = Math.atan2(event.getY() - midPoint.y, event.getX() - midPoint.x);
            matrix.postRotate(0, midPoint.x, midPoint.y);

            iv_main.setImageMatrix(matrix);

        }
    }

}
