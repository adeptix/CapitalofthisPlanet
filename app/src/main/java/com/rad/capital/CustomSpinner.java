package com.rad.capital;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomSpinner extends AppCompatSpinner{

    private OnSpinnerEventsListener listener;
    private boolean open = false;


    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSpinner(Context context, int mode) {
        super(context, mode);
    }

    public CustomSpinner(Context context) {
        super(context);
    }


    public interface OnSpinnerEventsListener {
        void onSpinnerOpened();
        void onSpinnerClosed();

    }



    @Override
    public boolean performClick() {

        open = true;
        if (listener != null) {
            listener.onSpinnerOpened();
        }

        return super.performClick();
    }

    public void setSpinnerEventsListener(OnSpinnerEventsListener onSpinnerEventsListener) {
        listener = onSpinnerEventsListener;
    }

    public void performClosedEvent() {
        open = false;
        if (listener != null) {
            listener.onSpinnerClosed();
        }
    }

    public boolean hasBeenOpened() {
        return open;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {

        super.onWindowFocusChanged(hasWindowFocus);
        if (hasBeenOpened() && hasWindowFocus) {
            performClosedEvent();
        }
    }





}
