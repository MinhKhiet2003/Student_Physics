package com.example.baitaplonquanlysinhvienhnue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

abstract class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    private int swipedPosition = -1;
    private UserAdapter adapter;

    OnSwipeTouchListener(Context context, UserAdapter adapter) {
        gestureDetector = new GestureDetector(context, new GestureListener());
        this.adapter = this.adapter; // Gán giá trị cho biến adapter
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public abstract void onSwipeLeft();

    public abstract void onSwipeRight();

    public abstract void onTouchDown();

    public abstract void onTouchUp();

    @SuppressLint("ClickableViewAccessibility")
    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight();
                    } else {
                        onSwipeLeft();
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public void setSwipedPosition(int position) {
        swipedPosition = position;
    }

//    public int getSwipedPosition() {
//        return swipedPosition;
//    }
}
