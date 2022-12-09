//package com.app.teamproject;
//
//import android.content.Context;
//import android.graphics.Matrix;
//import android.graphics.PointF;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.ScaleGestureDetector;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//
//public class Zoominout extends AppCompatActivity implements View.OnClickListener{
//    LinearLayout linear_main;
//    ImageView search, iv_main;
//
//
//    private static Context context;
//    private ScaleGestureDetector mScaleGestureDetector;
//    private float mScaleFactor = 1.0f;
//
//    enum TOUCH_MODE {
//        NONE,   // 터치 안했을 때
//        SINGLE, // 한손가락 터치
//        MULTI   //두손가락 터치
//    }
//    ArrayList<String> arr = new ArrayList<>();
//
//    private MainActivity.TOUCH_MODE touchMode;
//    private Matrix matrix;      //기존 매트릭스
//    private Matrix savedMatrix; //작업 후 이미지에 매핑할 매트릭스
//
//    private PointF startPoint;  //한손가락 터치 이동 포인트
//
//    private PointF midPoint;    //두손가락 터치 시 중신 포인트
//    private float oldDistance;  //터치 시 두손가락 사이의 거리
//
//    private double oldDegree = 0; // 두손가락의 각도
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        MainActivity.context = getApplicationContext();
//        setContentView(R.layout.activity_main);
//
//        matrix = new Matrix();
//        savedMatrix = new Matrix();
//
//        for (int i = 0; i < 111; i++){
//            arr.add(stations[i]);
//        }
//
//    private View.OnTouchListener onTouch = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            int width = ((ViewGroup) v.getParent()).getWidth() - v.getWidth();
//            int height = linear_main.getHeight() - v.getHeight();
//            if (v.equals(iv_main)) {
//                int action = event.getAction();
//                switch (action & MotionEvent.ACTION_MASK) {
//                    case MotionEvent.ACTION_DOWN:
//                        touchMode = MainActivity.TOUCH_MODE.SINGLE;
//
//                        donwSingleEvent(event);
//                        break;
//                    case MotionEvent.ACTION_POINTER_DOWN:
//                        if (event.getPointerCount() == 2) { // 두손가락 터치를 했을 때
//                            touchMode = MainActivity.TOUCH_MODE.MULTI;
//                            downMultiEvent(event);
//                        }
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        if (touchMode == MainActivity.TOUCH_MODE.SINGLE) {
//                            moveSingleEvent(v, event);
//                            if ( v.getX() > 0 && v.getY() > 0 && v.getX() < width && v.getY() < height ) {
//                                v.setX(0);
//                                v.setY(0);
//                            }
//                        } else if (touchMode == MainActivity.TOUCH_MODE.MULTI) {
//                            moveMultiEvent(event);
//                        }
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//                    case MotionEvent.ACTION_POINTER_UP:
//                        touchMode = MainActivity.TOUCH_MODE.NONE;
//                        break;
//                }
//            }
//
//
//            return true;
//        }
//    };
//
//    private PointF getMidPoint(MotionEvent e) {
//
//        float x = (e.getX(0) + e.getX(1)) / 2;
//        float y = (e.getY(0) + e.getY(1)) / 2;
//
//        return new PointF(x, y);
//    }
//
//    private float getDistance(MotionEvent e) {
//        float x = e.getX(0) - e.getX(1);
//        float y = e.getY(0) - e.getY(1);
//        return (float) Math.sqrt(x * x + y * y);
//    }
//
//    private void donwSingleEvent(MotionEvent event) {
//        savedMatrix.set(matrix);
//        startPoint = new PointF(event.getX(), event.getY());
//    }
//
//    private void downMultiEvent(MotionEvent event) {
//        oldDistance = getDistance(event);
//        if (oldDistance > 5f) {
//            savedMatrix.set(matrix);
//            midPoint = getMidPoint(event);
//            double radian = Math.atan2(event.getY() - midPoint.y, event.getX() - midPoint.x);
//            oldDegree = (radian * 180) / Math.PI;
//        }
//    }
//    private void moveSingleEvent(View v, MotionEvent event) {
//
//        float x = event.getX() - startPoint.x;
//        float y = event.getY() - startPoint.y;
//        int width = ((ViewGroup) v.getParent()).getWidth() - v.getWidth();
//        int height = linear_main.getHeight() - v.getHeight();
////        if (x > width && y > height) {
////            x = width;
////            y = height;
////        } else if (x < 0 && y > height) {
////            x = 0;
////            y = height;
////        } else if (x > width && y < 0) {
////            x = width;
////            y = 0;
////        } else if (x < 0 && y < 0) {
////            x = 0;
////            y = 0;
////        } else if (x < 0 || y > width) {
////            if (x < 0) {
////                x = 0;
////                y = event.getRawY() - startPoint.y - v.getHeight();
////            } else {
////                x = width;
////                y = event.getRawY() - startPoint.y - v.getHeight();
////            }
////        } else if (y < 0 || y > height) {
////            if (y < 0) {
////                y = 0;
////            } else {
////                y = height;
////            }
////        }
//
//        matrix.set(savedMatrix);
////        if ( x > 0 && y > 0 && x < width && y < height ) {
////            matrix.postTranslate(x, y);
////        }
////        if (x < 0){
////            x = 0;
////        }
////        if (y < 0) {
////            y = 0;
////        }
//        matrix.postTranslate(x, y);
//        Log.v("matrix", iv_main.getMaxWidth() + " :::::::" + y);
//        if (iv_main.getX() < 0){
//            iv_main.setX(0);
//        }
//        iv_main.setImageMatrix(matrix);
//    }
//
//    private void moveMultiEvent(MotionEvent event) {
//        float newDistance = getDistance(event);
//        if (newDistance > 5f) {
//            matrix.set(savedMatrix);
//            float scale = newDistance / oldDistance;
//            if (scale < 0 ){
//                scale = 1;
//            }
//
//            Log.v("scale", String.valueOf(scale));
//            matrix.postScale(scale, scale, midPoint.x, midPoint.y);
//
//            double nowRadian = Math.atan2(event.getY() - midPoint.y, event.getX() - midPoint.x);
//            matrix.postRotate(0, midPoint.x, midPoint.y);
//
//            iv_main.setImageMatrix(matrix);
//
//        }
//    }
//
//}
