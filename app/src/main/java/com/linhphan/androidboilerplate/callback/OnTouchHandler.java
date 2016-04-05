package com.linhphan.androidboilerplate.callback;

import android.view.MotionEvent;
import android.view.View;

import com.linhphan.androidboilerplate.util.Logger;

/**
 * Created by linhphan on 12/21/15.
 */
public class OnTouchHandler implements View.OnTouchListener {

    private final int UN_TOUCH = -1;

    private IOnTouchCallback callback;
    private float x1, y1;
    private float x2, y2;
    private int pointId;

    //======================= constructor ==========================================================
    public OnTouchHandler(IOnTouchCallback callback) {
        this.callback = callback;
        pointId = UN_TOUCH;
    }

    //======================= implemented methods  =================================================
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                pointId = event.getPointerId(event.getActionIndex());
                x1 = event.getX();
                y1 = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:

                break;

            case MotionEvent.ACTION_UP:
                if (pointId == event.getPointerId(event.getActionIndex())) {
                    x2 = event.getX();
                    y2 = event.getY();

                    Direction direction = getDirection(x1, y1, x2, y2);

                    switch (direction) {
                        case UP:
                            if (callback != null) {
                                callback.touchUpCallback();
                            }
                            break;

                        case RIGHT_TO_LEFT:
                            if (callback != null) {
                                callback.touchRightToLeftCallback();
                            }
                            break;

                        case LEFT_TO_RIGHT:
                            if (callback != null){
                                callback.touchLeftToRightRightCallback();
                            }

                            break;

                        case DOWN:
                            if (callback != null){
                                callback.touchDownCallback();
                            }

                        default:
                            break;
                    }
                }
                break;

            case MotionEvent.ACTION_POINTER_DOWN:

                break;

            case MotionEvent.ACTION_POINTER_UP:

                break;
        }
        return true;
    }

    //============= inner methods ==================================================================
    /**
     * determine directions which user has sweep
     *
     * @param x1 x coordinate of the started point
     * @param y1 y coordinate of the started point.
     * @param x2 x coordinate of the ended point
     * @param y2 y coordinate of the ended point
     * @return the direction whether return 0
     */
    private Direction getDirection(float x1, float y1, float x2, float y2) {
        final int MIN_DISTANCE = 100;
        double sweepDistance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        if (sweepDistance >= MIN_DISTANCE) {
            double angle = Math.toDegrees(Math.atan2(y1 - y2, x2 - x1));
            if (angle > 45 && angle <= 135) {
                // top
                Logger.d(this.getClass().getName(), "up");
                return Direction.UP;

            } else if (angle >= 135 && angle < 180 || angle < -135 && angle > -180) {
                // left
                Logger.d(this.getClass().getName(), "left");
                return Direction.RIGHT_TO_LEFT;

            } else if (angle < -45 && angle >= -135) {
                // down
                Logger.d(this.getClass().getName(), "down");
                return Direction.DOWN;

            } else if (angle > -45 && angle <= 45) {
                // right
                Logger.d(this.getClass().getName(), "left to right");
                return Direction.LEFT_TO_RIGHT;
            }
        }

        return Direction.UNKNOWN;
    }

    //================= inner classes ==============================================================
    enum Direction {
        LEFT_TO_RIGHT, UP, RIGHT_TO_LEFT, DOWN, UNKNOWN
    }

    public interface IOnTouchCallback {
        void touchUpCallback();
        void touchRightToLeftCallback();
        void touchLeftToRightRightCallback();
        void touchDownCallback();
    }
}
