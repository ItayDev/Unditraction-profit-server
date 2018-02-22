/**
 * Ido Nimni 205883655
 * Daniel Kasman 206274946
 * Noy Shmaryahu 206082877
 * Segev Lahav 313381154
 * 89-211-05
 **/
package com.android.safe;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import java.util.Calendar;

/**
 * Detects left and right swipes across a view.
 */
public class OnSwipeTouchListener implements OnTouchListener {

    private final GestureDetector gestureDetector;
    private static final int MAX_CLICK_DURATION = 200;
    private long startClickTime;

    /**
     * this function is the constructor of this class
     * @param context - the context
     */
    public OnSwipeTouchListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    /**
     * this function does nothing
     */
    public void onSwipeLeft() {
    }

    /**
     * this function does nothing
     */
    public void onSwipeRight() {
    }

    /**
     * this function does nothing
     */
    public void onClick() {

    }

    /**
     * this function is the constructor of this class
     * @param v - the view
     * @param event - the motion event
     */
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            // if the motion was down
            case MotionEvent.ACTION_DOWN: {
                startClickTime = Calendar.getInstance().getTimeInMillis();
                break;
            }
            // if the motion was up
            case MotionEvent.ACTION_UP: {
                // the duration of the click
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if(clickDuration < MAX_CLICK_DURATION) {
                    onClick();
                }
            }
        }
        return gestureDetector.onTouchEvent(event);
    }

    /**
     * Detects a swipe.
     */
    private final class GestureListener extends SimpleOnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        /**
         * this function returns true
         * @param e - the motion event
         */
        @Override
        public boolean onDown(MotionEvent e)
        {
            return true;
        }

        /**
         * this function returns true if there was a swipe else false
         * and activates swipe functions or else on click
         * @param e1 - the motion event 1
         * @param e2 - the motion event 2
         * @param velocityX - the velocity on x
         * @param velocityY - the velocity on y
         */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // calculates the distance in X and in Y
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            // check if the distances are in the right size to be a swipe
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) >
                    SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                // check swipe right
                if (distanceX > 0)
                    onSwipeRight();
                else // else swipe left
                    onSwipeLeft();
                return true;
            } else { // else activate on click
                onClick();
            }
            return false;
        }
    }
}